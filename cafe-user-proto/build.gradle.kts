import com.google.protobuf.gradle.builtins
import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.ofSourceSet
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.proto
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

apply(plugin = "java")
apply(plugin = "idea")
apply(plugin = "com.google.protobuf")

dependencies {
    // protobuf
    implementation("com.google.protobuf:protobuf-kotlin:_")

    // grpc
    implementation("io.grpc:grpc-all:_")
    implementation("io.grpc:grpc-kotlin-stub:_")

    // annotation api
    implementation("javax.annotation:javax.annotation-api:_")

    // reactor
    implementation("io.projectreactor:reactor-core:_")
    implementation("com.salesforce.servicelibs:reactor-grpc-stub:_")
}

val generatedProtoPath = "$projectDir/generated"

protobuf {
    protoc {
        artifact = if (project.hasProperty("protoc_platform")) {
            val protocPlatform = project.findProperty("protoc_platform")
            "com.google.protobuf:protoc:${Version.PROTOC}:$protocPlatform"
        } else {
            "com.google.protobuf:protoc:${Version.PROTOC}"
        }
        // Set the location of generated source code by protoc compiler
        generatedFilesBaseDir = generatedProtoPath
    }
    plugins {
        id("grpc") {
            artifact = if (project.hasProperty("protoc_platform")) {
                val protocPlatform = project.findProperty("protoc_platform")
                "io.grpc:protoc-gen-grpc-java:${Version.PROTOC_GEN_JAVA}:$protocPlatform"
            } else {
                "io.grpc:protoc-gen-grpc-java:${Version.PROTOC_GEN_JAVA}"
            }
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:${Version.PROTOC_GEN_KOTLIN}:jdk7@jar"
        }

        id("reactor") {
            artifact = "com.salesforce.servicelibs:reactor-grpc:${Version.PROTOC_GEN_REACTOR}"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                // Apply the "grpc" plugin whose spec is defined above, without options.
                id("grpc")
                id("reactor")
                id("grpckt")
            }

            it.builtins {
                id("kotlin")
            }
        }
    }
}

tasks.withType<Delete> {
    delete(generatedProtoPath)
}

sourceSets {
    main {
        java {
            srcDirs(
                "$generatedProtoPath/main/java",
                "$generatedProtoPath/main/grpc",
                "$generatedProtoPath/main/grpckt"
            )
        }
        proto {
            srcDir("src/main/proto")
        }
    }
}

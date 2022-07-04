import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    idea
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("com.google.cloud.tools.jib") apply false
    id("com.google.protobuf") apply false

    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.noarg")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")

    repositories {
        mavenCentral()
    }

    dependencies {
        // chore
        if (System.getProperty("os.arch") == "aarch64" && System.getProperty("os.name") == "Mac OS X") {
            runtimeOnly("io.netty:netty-resolver-dns-native-macos:_:osx-aarch_64")
        }
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
            vendor.set(JvmVendorSpec.ADOPTIUM)
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.named<ProcessResources>("processResources") {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }
}

val springProjects = listOf(
    project(":cafe-webflux-memory"),
    project(":cafe-webflux"),
    project(":cafe-webflux-coroutine"),
    project(":cafe-user-grpc-reactor"),
    project(":cafe-user-grpc-kotlin"),
)

configure(springProjects) {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "kotlin-spring")
    apply(plugin = "kotlin-noarg")
}


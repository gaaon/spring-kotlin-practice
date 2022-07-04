dependencies {
    implementation(project(":cafe-user-proto"))

    // spring
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // protobuf
    implementation("com.google.protobuf:protobuf-kotlin:_")

    // grpc
    implementation("io.github.lognet:grpc-spring-boot-starter:_")
    implementation("io.grpc:grpc-all:_")
    implementation("io.grpc:grpc-kotlin-stub:_")

    // reactor
    implementation("io.projectreactor:reactor-core:_")
}

dependencyManagement {
    imports {
        mavenBom("io.grpc:grpc-bom:${Version.GRPC_BOM}")
    }
}

import com.google.cloud.tools.jib.gradle.JibExtension

apply(plugin = "com.google.cloud.tools.jib")

dependencies {
    implementation(project(":cafe-user-proto"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // spring
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-mustache")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
    implementation("org.springframework.cloud:spring-cloud-stream")
    implementation("org.springframework.cloud:spring-cloud-stream-binder-rabbit")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // mysql
    implementation("io.r2dbc:r2dbc-pool")
    implementation("dev.miku:r2dbc-mysql:_")

    // coroutine
    implementation(KotlinX.coroutines.core)
    implementation(KotlinX.coroutines.reactor)

    // mapstruct
    implementation("org.mapstruct:mapstruct:_")
    kapt("org.mapstruct:mapstruct-processor:_")

    // reactor
    implementation("io.projectreactor:reactor-tools:_")

    // micrometer
    implementation("io.micrometer:micrometer-registry-prometheus")

    // protobuf
    implementation("com.google.protobuf:protobuf-kotlin:_")

    // grpc
    implementation("io.grpc:grpc-all:_")
    implementation("io.grpc:grpc-kotlin-stub:_")

    // snakeyaml
    implementation("org.yaml:snakeyaml:1.29")

    /* test */
    // junit
    testImplementation("org.junit.jupiter:junit-jupiter:_")

    // spring
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    // kotest
    testImplementation("io.kotest:kotest-assertions-core:_")
    testImplementation("io.kotest:kotest-runner-junit5:_")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:_")

    // mockk
    testImplementation("io.mockk:mockk:_")
    testImplementation("com.ninja-squad:springmockk:_")

    // faker
    testImplementation("com.github.javafaker:javafaker:_")

    // testcontainer
    testImplementation(platform("org.testcontainers:testcontainers-bom:_"))
    testImplementation("org.testcontainers:mysql")
    testImplementation("org.testcontainers:r2dbc")
    testImplementation("org.testcontainers:mongodb")
    testImplementation("org.testcontainers:testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")

    // r2dbc
    testImplementation("io.r2dbc:r2dbc-h2")
    testImplementation("mysql:mysql-connector-java")

    // jsoup
    testImplementation("org.jsoup:jsoup:_")

}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${Version.SPRING_CLOUD}")
    }
}

configure<JibExtension> {
    extraDirectories {
        setPaths(files("../agents"))
    }

    from {
        image = "eclipse-temurin:17.0.3_7-jre-alpine"
    }

    to {
        image = "ghcr.io/gaaon/spring-kotlin-practice"
        tags = setOf(gitBranch().replace("/", "_"), gitHashLong())
    }

    container {
        environment = mapOf(
            "ENV1" to "test",
            "SENTRY_RELEASE" to gitHashLong(),
        )

        jvmFlags = listOf(
            "-javaagent:/reactor-tools-agent.jar",
            "-server",
            "-Xms1024m",
            "-Xmx1024m",
            "-XX:MetaspaceSize=384m",
            "-XX:MaxMetaspaceSize=384m",
            "-XX:+DisableExplicitGC",
            "-Duser.timezone=Asia/Seoul",
            "-Djava.net.preferIPv4Stack=true",
            "-Djava.net.preferIPv6Addresses=false",
            "-Dsun.net.inetaddr.ttl=0",
            "-Dnetworkaddress.cache.ttl=0",
            "-Dnetworkaddress.cache.negative.ttl=0",
            "-Dsun.net.inetaddr.ttl=0"
        )
    }
}

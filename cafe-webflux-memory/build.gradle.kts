dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // spring
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-mustache")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

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

    /* test */
    // spring
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}



dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${Version.SPRING_CLOUD}")
    }
}

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    java
    idea
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("com.google.cloud.tools.jib") apply false
    id("com.google.protobuf") apply false
    id("jacoco")
    id("io.gitlab.arturbosch.detekt")
    id("org.jlleitschuh.gradle.ktlint")
    id("org.jlleitschuh.gradle.ktlint-idea")

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
    apply(plugin = "jacoco")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    repositories {
        mavenCentral()
    }

    dependencies {
        // chore
        if (System.getProperty("os.arch") == "aarch64" && System.getProperty("os.name") == "Mac OS X") {
            runtimeOnly("io.netty:netty-resolver-dns-native-macos:_:osx-aarch_64")
        }

        /* detekt */
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:_")
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

        testLogging {
            events("passed", "skipped", "failed")
        }
    }

    tasks.named<ProcessResources>("processResources") {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }

    tasks.withType<Detekt>().configureEach {
        reports {
            html.required.set(false)
            xml.required.set(true)
            txt.required.set(false)
        }
    }

    detekt {
        buildUponDefaultConfig = true

        source = objects.fileCollection().from(
            DetektExtension.DEFAULT_SRC_DIR_JAVA,
            "src/test/java",
            DetektExtension.DEFAULT_SRC_DIR_KOTLIN,
            "src/test/kotlin"
        )

        config = files("$rootDir/config/detekt/detekt.yml")

        parallel = false
    }

    jacoco {
        toolVersion = "0.8.7"
    }

    configure<KtlintExtension> {
        version.set("0.45.2")
        android.set(false)
        filter {
            exclude("**/generated/**")
        }
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

rootProject.name = "spring-kotlin-practice"

plugins {
    // See https://jmfayard.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.40.2"
}

include(
    "cafe-webflux-memory",
    "cafe-webflux",
    "cafe-webflux-coroutine",
    "cafe-user-proto",
    "cafe-user-grpc-reactor",
    "cafe-user-grpc-kotlin",
)

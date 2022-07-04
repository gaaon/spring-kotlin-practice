package practice.repository

import org.testcontainers.containers.GenericContainer
import org.testcontainers.utility.DockerImageName

object SharedRedisTestContainer {
    private const val exposedPort = 6379

    private val redisContainer: GenericContainer<*> = GenericContainer<Nothing>(
        DockerImageName.parse("redis:5.0.3-alpine")
    ).withExposedPorts(exposedPort)

    init {
        redisContainer.start()
    }

    fun getHost(): String = redisContainer.host
    fun getPort(): Int = redisContainer.getMappedPort(exposedPort)
}

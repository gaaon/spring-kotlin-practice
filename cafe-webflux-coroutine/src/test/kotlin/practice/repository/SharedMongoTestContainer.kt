package practice.repository

import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName

object SharedMongoTestContainer {
    private val mongodbContainer: MongoDBContainer = MongoDBContainer(
        DockerImageName.parse("mongo:4.4.6")
    )

    init {
        mongodbContainer.start()
    }

    fun getReplicaSetUrl(): String = mongodbContainer.replicaSetUrl
}

fun String.exec(): Process = Runtime.getRuntime().exec(this)
val Process.text
    get() = inputStream.bufferedReader().use { it.readLine() }?.trim() ?: ""

object Version {
    const val SPRING_CLOUD = "2021.0.3"
    const val PROTOC = "3.19.4"
    const val PROTOC_GEN_JAVA = "1.44.1"
    const val PROTOC_GEN_KOTLIN = "1.2.1"
    const val PROTOC_GEN_REACTOR = "1.2.3"
    const val GRPC_BOM = "1.45.0"
}

fun gitHashLong() = "git rev-parse HEAD".exec().text
fun gitBranch() = "git rev-parse --abbrev-ref HEAD".exec().text

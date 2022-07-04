package practice.cafe.grpc.user.grpcservice.model

class CafeUser(
    val id: Long,
    val avatarUrl: String,
    val nickname: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CafeUser) return false

        if (id != other.id) return false
        if (avatarUrl != other.avatarUrl) return false
        if (nickname != other.nickname) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + avatarUrl.hashCode()
        result = 31 * result + nickname.hashCode()
        return result
    }

    override fun toString(): String {
        return "User(id=$id, avatarUrl='$avatarUrl', nickname='$nickname')"
    }
}


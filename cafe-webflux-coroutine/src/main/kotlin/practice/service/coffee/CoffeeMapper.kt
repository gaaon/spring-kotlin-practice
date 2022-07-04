package practice.service.coffee

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers
import practice.cafe.user.rpc.CafeUser
import practice.controller.coffee.model.CoffeeCommentModel
import practice.controller.coffee.model.CoffeeModel
import practice.repository.coffee.record.CoffeeRecord
import practice.repository.comment.document.CoffeeCommentDocument

@Mapper
interface CoffeeMapper {
    companion object {
        val INSTANCE: CoffeeMapper = Mappers.getMapper(CoffeeMapper::class.java)
    }

    @Mappings(
        Mapping(target = ".", source = "coffee"),
        Mapping(target = "rating", source = "rating"),
        Mapping(target = "comments", source = "comments"),
        Mapping(target = "commentsCount", source = "commentsCount"),
        Mapping(target = "stock", source = "stock"),
    )
    fun toCoffeeModel(
        coffee: CoffeeRecord,
        rating: Double,
        comments: List<CoffeeCommentModel>,
        commentsCount: Int,
        stock: Long
    ): CoffeeModel

    @Mappings(
        Mapping(source = "comment.nickname", target = "nickname"),
        Mapping(target = ".", source = "comment"),
        Mapping(target = "avatarUrl", source = "user.avatarUrl"),
    )
    fun toCoffeeCommentModel(
        comment: CoffeeCommentDocument,
        user: CafeUser,
    ): CoffeeCommentModel
}

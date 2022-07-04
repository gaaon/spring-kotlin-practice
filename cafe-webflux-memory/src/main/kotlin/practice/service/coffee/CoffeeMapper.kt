package practice.service.coffee

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers
import practice.controller.coffee.model.CoffeeModel
import practice.service.coffee.domain.Coffee
import practice.service.coffee.domain.CoffeeComment

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
        coffee: Coffee,
        rating: Double,
        comments: List<CoffeeComment>,
        commentsCount: Int,
        stock: Long
    ): CoffeeModel
}

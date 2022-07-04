package practice.controller.menu

import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.reactive.result.view.Rendering
import practice.service.coffee.CoffeeService

@RequestMapping("/pages/menu")
@Controller
class MenuPageController(
    private val coffeeService: CoffeeService,
) {
    @GetMapping(produces = ["text/html"])
    suspend fun menuAll(): Rendering {
        val coffees = coffeeService.findAll().toList().sortedBy { it.id }
        val totalCount = coffees.size

        val model = mapOf(
            "totalCount" to totalCount,
            "coffees" to coffees,
        )

        return Rendering.view("/menu")
            .model(model)
            .build()
    }
}

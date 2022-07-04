package practice.controller.menu

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.reactive.result.view.Rendering
import practice.service.coffee.CoffeeService
import reactor.core.publisher.Mono

@RequestMapping("/pages/menu")
@Controller
class MenuPageController(
    private val coffeeService: CoffeeService,
) {
    @GetMapping(produces = ["text/html"])
    fun menuAll(): Mono<Rendering> {
        return coffeeService.findAll()
            .collectList()
            .map { coffees ->
                val totalCount = coffees.size

                val model = mapOf(
                    "totalCount" to totalCount,
                    "coffees" to coffees,
                )

                Rendering.view("/menu")
                    .model(model)
                    .build()
            }
    }
}

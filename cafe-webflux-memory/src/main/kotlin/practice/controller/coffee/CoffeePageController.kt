package practice.controller.coffee

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.reactive.result.view.Rendering
import practice.service.coffee.CoffeeService
import reactor.core.publisher.Mono

@RequestMapping("/pages/coffees")
@Controller
class CoffeePageController(
    private val coffeeService: CoffeeService,
) {
    @GetMapping("/{id}", produces = ["text/html"])
    fun menuAll(@PathVariable id: Long): Mono<Rendering> {
        return coffeeService.findById(id)
            .map { coffee ->
                val model = mapOf(
                    "coffee" to coffee,
                )

                Rendering.view("/coffee")
                    .model(model)
                    .build()
            }
    }
}

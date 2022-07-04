package practice.controller.coffee

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.reactive.result.view.Rendering
import org.springframework.web.server.ResponseStatusException
import practice.service.coffee.CoffeeService

@RequestMapping("/pages/coffees")
@Controller
class CoffeePageController(
    private val coffeeService: CoffeeService,
) {
    @GetMapping("/{id}", produces = ["text/html"])
    suspend fun menuAll(@PathVariable id: Long): Rendering {
        val coffee = coffeeService.findById(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

        val model = mapOf(
            "coffee" to coffee,
        )

        return Rendering.view("/coffee")
            .model(model)
            .build()
    }
}

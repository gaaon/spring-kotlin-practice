package practice

import com.github.javafaker.Faker
import kotlin.random.Random

object TestHelper {
    private val faker = Faker()

    private val coffeeNames = listOf(
        "에스프레소",
        "아메리카노",
        "아메리카노",
        "카푸치노",
        "카페라떼",
        "카페모카",
        "마끼아또",
        "콘파냐",
        "콜드브루",
    )

    fun randomStringId(): String {
        return randomLongId().toString()
    }

    fun randomLongId(): Long {
        return Random.nextLong(10000, 10000000)
    }

    fun randomOtherLongId(givenId: Long): Long {
        while (true) {
            val id = randomLongId()
            if (id != givenId) return randomLongId()
        }
    }

    fun randomCoffeeName(): String {
        return "${coffeeNames.random()} ${Random.nextInt(0, 10000)}"
    }

    fun randomImageUrl(): String {
        return faker.internet().image()
    }

    fun randomPrice(): Int {
        return Random.nextInt(1000, 10000)
    }

    fun randomSentence(): String {
        return faker.lorem().sentence()
    }

    fun randomStock(): Int {
        return Random.nextInt(10, 1000)
    }

    fun randomQuantity(): Long {
        return Random.nextLong(10, 1000)
    }

    fun randomNickname(): String {
        return faker.name().fullName()
    }

    fun randomRating(): Int {
        return Random.nextInt(1, 6)
    }
}

package ru.otus.otuskotlin.marketplace.api.v1

import ru.otus.otuskotlin.marketplace.api.v1.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ResponseSerializationTest {
    private val response = RecipeCreateResponse(
        requestId = "123",
        recipe = RecipeResponseObject(
            userId = 1,
            name = "Шарлотка",
            description = "Один из самых популярных, вкусных и простых рецептов яблочных пирогов, который актуален круглый год. С этим рецептом шарлотка получится восхитительной, даже если вы печёте её впервые."
        )
    )

    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(response)

        assertContains(json, Regex("\"userId\":\\s*1"))
        assertContains(json, Regex("\"name\":\\s*\"Шарлотка\""))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(response)
        val obj = apiV1Mapper.readValue(json, IResponse::class.java) as RecipeCreateResponse

        assertEquals(response, obj)
    }
}

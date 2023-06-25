package ru.otus.otuskotlin.marketplace.api.v1

import ru.otus.otuskotlin.marketplace.api.v1.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class RequestSerializationTest {
    private val request = RecipeCreateRequest(
        requestId = "123",
        debug = RecipeDebug(
            mode = RecipeRequestDebugMode.STUB,
            stub = RecipeRequestDebugStubs.BAD_TITLE
        ),
        recipe = RecipeCreateObject(
            userId = 1,
            name = "Шарлотка",
            description = "Один из самых популярных, вкусных и простых рецептов яблочных пирогов, который актуален круглый год. С этим рецептом шарлотка получится восхитительной, даже если вы печёте её впервые."
        )
    )

    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(request)

        assertContains(json, Regex("\"userId\":\\s*1"))
        assertContains(json, Regex("\"name\":\\s*\"Шарлотка\""))
        assertContains(json, Regex("\"mode\":\\s*\"stub\""))
        assertContains(json, Regex("\"stub\":\\s*\"badTitle\""))
        assertContains(json, Regex("\"requestType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(request)
        val obj = apiV1Mapper.readValue(json, IRequest::class.java) as RecipeCreateRequest

        assertEquals(request, obj)
    }

    @Test
    fun deserializeNaked() {
        val jsonString = """
            {"requestId": "123"}
        """.trimIndent()
        val obj = apiV1Mapper.readValue(jsonString, RecipeCreateRequest::class.java)

        assertEquals("123", obj.requestId)
    }
}

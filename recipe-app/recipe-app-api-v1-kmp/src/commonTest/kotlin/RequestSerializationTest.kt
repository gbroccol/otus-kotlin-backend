package ru.otus.otuskotlin.marketplace.api.v2

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import ru.otus.otuskotlin.marketplace.api.v2.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class RequestSerializationTest {
    private val request: IRequest = RecipeCreateRequest(
        requestType = "create",
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
//        val json = apiV2Mapper.encodeToString(AdRequestSerializer1, request)
//        val json = apiV2Mapper.encodeToString(RequestSerializers.create, request)
        val json = apiV2Mapper.encodeToString(request)

        println(json)

        assertContains(json, Regex("\"userId\":\\s*1"))
        assertContains(json, Regex("\"name\":\\s*\"Шарлотка\""))
        assertContains(json, Regex("\"mode\":\\s*\"stub\""))
        assertContains(json, Regex("\"stub\":\\s*\"badTitle\""))
        assertContains(json, Regex("\"requestType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiV2Mapper.encodeToString(request)
//        val json = apiV2Mapper.encodeToString(AdRequestSerializer1, request)
//        val json = apiV2Mapper.encodeToString(RequestSerializers.create, request)
//        val obj = apiV2Mapper.decodeFromString(AdRequestSerializer, json) as AdCreateRequest
        val obj = apiV2Mapper.decodeFromString(json) as RecipeCreateRequest

        assertEquals(request, obj)
    }
    @Test
    fun deserializeNaked() {
        val jsonString = """
            {"requestId": "123"}
        """.trimIndent()
        val obj = apiV2Mapper.decodeFromString<RecipeCreateRequest>(jsonString)

        assertEquals("123", obj.requestId)
    }
}

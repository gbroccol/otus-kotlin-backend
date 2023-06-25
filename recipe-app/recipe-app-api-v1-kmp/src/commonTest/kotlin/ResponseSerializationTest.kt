package ru.otus.otuskotlin.marketplace.api.v2

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import ru.otus.otuskotlin.marketplace.api.v2.models.*
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
//        val json = apiV2Mapper.encodeToString(AdRequestSerializer1, request)
//        val json = apiV2Mapper.encodeToString(RequestSerializers.create, request)
        val json = apiV2Mapper.encodeToString(response)

        println(json)

        assertContains(json, Regex("\"userId\":\\s*1"))
        assertContains(json, Regex("\"name\":\\s*\"Шарлотка\""))
    }

    @Test
    fun deserialize() {
        val json = apiV2Mapper.encodeToString(response)
//        val json = apiV2Mapper.encodeToString(AdRequestSerializer1, request)
//        val json = apiV2Mapper.encodeToString(RequestSerializers.create, request)
//        val obj = apiV2Mapper.decodeFromString(AdRequestSerializer, json) as AdCreateRequest
        val obj = apiV2Mapper.decodeFromString(json) as RecipeCreateResponse

        assertEquals(response, obj)
    }
}

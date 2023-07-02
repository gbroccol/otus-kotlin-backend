package ru.otus.otuskotlin.marketplace.api.v2.requests

import ru.otus.otuskotlin.marketplace.api.v2.RecipeRequestSerializer
import ru.otus.otuskotlin.marketplace.api.v2.apiV2Mapper
import ru.otus.otuskotlin.marketplace.api.v2.models.IRequest

fun apiV2RequestSerialize(request: IRequest): String = apiV2Mapper.encodeToString(RecipeRequestSerializer, request)

@Suppress("UNCHECKED_CAST")
fun <T : Any> apiV2RequestDeserialize(json: String): T = apiV2Mapper.decodeFromString(RecipeRequestSerializer, json) as T

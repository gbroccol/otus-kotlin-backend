package ru.otus.otuskotlin.marketplace.api.v2

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import ru.otus.otuskotlin.marketplace.api.v2.models.*

@OptIn(ExperimentalSerializationApi::class)
val apiV2Mapper = Json {
    classDiscriminator = "_"
    encodeDefaults = true
    ignoreUnknownKeys = true

    serializersModule = SerializersModule {
        polymorphicDefaultSerializer(IRequest::class) {
            @Suppress("UNCHECKED_CAST")
            when(it) {
                is RecipeCreateRequest ->  RequestSerializer(RecipeCreateRequest.serializer()) as SerializationStrategy<IRequest>
                is RecipeReadRequest   ->  RequestSerializer(RecipeReadRequest  .serializer()) as SerializationStrategy<IRequest>
                is RecipeUpdateRequest ->  RequestSerializer(RecipeUpdateRequest.serializer()) as SerializationStrategy<IRequest>
                is RecipeDeleteRequest ->  RequestSerializer(RecipeDeleteRequest.serializer()) as SerializationStrategy<IRequest>
                is RecipeSearchRequest ->  RequestSerializer(RecipeSearchRequest.serializer()) as SerializationStrategy<IRequest>
                is RecipeOffersRequest ->  RequestSerializer(RecipeOffersRequest.serializer()) as SerializationStrategy<IRequest>
                else -> null
            }
        }
        polymorphicDefault(IRequest::class) {
            RecipeRequestSerializer
        }
        polymorphicDefaultSerializer(IResponse::class) {
            @Suppress("UNCHECKED_CAST")
            when(it) {
                is RecipeCreateResponse ->  ResponseSerializer(RecipeCreateResponse.serializer()) as SerializationStrategy<IResponse>
                is RecipeReadResponse   ->  ResponseSerializer(RecipeReadResponse  .serializer()) as SerializationStrategy<IResponse>
                is RecipeUpdateResponse ->  ResponseSerializer(RecipeUpdateResponse.serializer()) as SerializationStrategy<IResponse>
                is RecipeDeleteResponse ->  ResponseSerializer(RecipeDeleteResponse.serializer()) as SerializationStrategy<IResponse>
                is RecipeSearchResponse ->  ResponseSerializer(RecipeSearchResponse.serializer()) as SerializationStrategy<IResponse>
                is RecipeOffersResponse ->  ResponseSerializer(RecipeOffersResponse.serializer()) as SerializationStrategy<IResponse>
                is RecipeInitResponse   ->  ResponseSerializer(RecipeInitResponse  .serializer()) as SerializationStrategy<IResponse>
                else -> null
            }
        }
        polymorphicDefault(IResponse::class) {
            RecipeResponseSerializer
        }

        contextual(RecipeRequestSerializer)
        contextual(RecipeResponseSerializer)
    }
}

fun Json.encodeResponse(response: IResponse): String = encodeToString(RecipeResponseSerializer, response)

fun apiV2ResponseSerialize(Response: IResponse): String = apiV2Mapper.encodeToString(RecipeResponseSerializer, Response)

@Suppress("UNCHECKED_CAST")
fun <T : Any> apiV2ResponseDeserialize(json: String): T = apiV2Mapper.decodeFromString(RecipeResponseSerializer, json) as T

fun apiV2RequestSerialize(request: IRequest): String = apiV2Mapper.encodeToString(RecipeRequestSerializer, request)

@Suppress("UNCHECKED_CAST")
fun <T : Any> apiV2RequestDeserialize(json: String): T = apiV2Mapper.decodeFromString(RecipeRequestSerializer, json) as T

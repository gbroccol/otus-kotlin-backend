package ru.otus.otuskotlin.marketplace.api.v2.requests

import kotlinx.serialization.KSerializer
import ru.otus.otuskotlin.marketplace.api.v2.models.RecipeSearchResponse
import ru.otus.otuskotlin.marketplace.api.v2.models.IResponse
import kotlin.reflect.KClass

object SearchResponseStrategy: IResponseStrategy {
    override val discriminator: String = "search"
    override val clazz: KClass<out IResponse> = RecipeSearchResponse::class
    override val serializer: KSerializer<out IResponse> = RecipeSearchResponse.serializer()
    override fun <T : IResponse> fillDiscriminator(req: T): T {
        require(req is RecipeSearchResponse)
        @Suppress("UNCHECKED_CAST")
        return req.copy(responseType = discriminator) as T
    }
}

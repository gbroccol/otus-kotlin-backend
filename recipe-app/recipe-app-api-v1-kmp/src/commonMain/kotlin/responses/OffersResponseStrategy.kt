package ru.otus.otuskotlin.marketplace.api.v2.requests

import kotlinx.serialization.KSerializer
import ru.otus.otuskotlin.marketplace.api.v2.models.RecipeOffersResponse
import ru.otus.otuskotlin.marketplace.api.v2.models.IResponse
import kotlin.reflect.KClass

object OffersResponseStrategy: IResponseStrategy {
    override val discriminator: String = "offers"
    override val clazz: KClass<out IResponse> = RecipeOffersResponse::class
    override val serializer: KSerializer<out IResponse> = RecipeOffersResponse.serializer()
    override fun <T : IResponse> fillDiscriminator(req: T): T {
        require(req is RecipeOffersResponse)
        @Suppress("UNCHECKED_CAST")
        return req.copy(responseType = discriminator) as T
    }
}

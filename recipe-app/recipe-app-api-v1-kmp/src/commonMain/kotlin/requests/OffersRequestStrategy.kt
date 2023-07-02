package ru.otus.otuskotlin.marketplace.api.v2.requests

import kotlinx.serialization.KSerializer
import ru.otus.otuskotlin.marketplace.api.v2.models.RecipeOffersRequest
import ru.otus.otuskotlin.marketplace.api.v2.models.IRequest
import kotlin.reflect.KClass

object OffersRequestStrategy: IRequestStrategy {
    override val discriminator: String = "offers"
    override val clazz: KClass<out IRequest> = RecipeOffersRequest::class
    override val serializer: KSerializer<out IRequest> = RecipeOffersRequest.serializer()
    override fun <T : IRequest> fillDiscriminator(req: T): T {
        require(req is RecipeOffersRequest)
        @Suppress("UNCHECKED_CAST")
        return req.copy(requestType = discriminator) as T
    }
}

package ru.otus.otuskotlin.marketplace.api.v2.requests

import kotlinx.serialization.KSerializer
import ru.otus.otuskotlin.marketplace.api.v2.models.RecipeDeleteRequest
import ru.otus.otuskotlin.marketplace.api.v2.models.IRequest
import kotlin.reflect.KClass

object DeleteRequestStrategy: IRequestStrategy {
    override val discriminator: String = "delete"
    override val clazz: KClass<out IRequest> = RecipeDeleteRequest::class
    override val serializer: KSerializer<out IRequest> = RecipeDeleteRequest.serializer()
    override fun <T : IRequest> fillDiscriminator(req: T): T {
        require(req is RecipeDeleteRequest)
        @Suppress("UNCHECKED_CAST")
        return req.copy(requestType = discriminator) as T
    }
}

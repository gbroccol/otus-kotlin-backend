package ru.otus.otuskotlin.marketplace.api.v2.requests

import kotlinx.serialization.KSerializer
import ru.otus.otuskotlin.marketplace.api.v2.models.RecipeReadRequest
import ru.otus.otuskotlin.marketplace.api.v2.models.IRequest
import kotlin.reflect.KClass

object ReadRequestStrategy: IRequestStrategy {
    override val discriminator: String = "read"
    override val clazz: KClass<out IRequest> = RecipeReadRequest::class
    override val serializer: KSerializer<out IRequest> = RecipeReadRequest.serializer()
    override fun <T : IRequest> fillDiscriminator(req: T): T {
        require(req is RecipeReadRequest)
        @Suppress("UNCHECKED_CAST")
        return req.copy(requestType = discriminator) as T
    }
}

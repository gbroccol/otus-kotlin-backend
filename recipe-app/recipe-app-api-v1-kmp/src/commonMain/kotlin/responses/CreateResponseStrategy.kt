package ru.otus.otuskotlin.marketplace.api.v2.requests

import kotlinx.serialization.KSerializer
import ru.otus.otuskotlin.marketplace.api.v2.models.RecipeCreateResponse
import ru.otus.otuskotlin.marketplace.api.v2.models.IResponse
import kotlin.reflect.KClass

object CreateResponseStrategy: IResponseStrategy {
    override val discriminator: String = "create"
    override val clazz: KClass<out IResponse> = RecipeCreateResponse::class
    override val serializer: KSerializer<out IResponse> = RecipeCreateResponse.serializer()
    override fun <T : IResponse> fillDiscriminator(req: T): T {
        require(req is RecipeCreateResponse)
        @Suppress("UNCHECKED_CAST")
        return req.copy(responseType = discriminator) as T
    }
}

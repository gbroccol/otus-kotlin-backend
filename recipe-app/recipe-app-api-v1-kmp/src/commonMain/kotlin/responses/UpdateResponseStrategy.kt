package ru.otus.otuskotlin.marketplace.api.v2.requests

import kotlinx.serialization.KSerializer
import ru.otus.otuskotlin.marketplace.api.v2.models.RecipeUpdateResponse
import ru.otus.otuskotlin.marketplace.api.v2.models.IResponse
import kotlin.reflect.KClass

object UpdateResponseStrategy: IResponseStrategy {
    override val discriminator: String = "update"
    override val clazz: KClass<out IResponse> = RecipeUpdateResponse::class
    override val serializer: KSerializer<out IResponse> = RecipeUpdateResponse.serializer()
    override fun <T : IResponse> fillDiscriminator(req: T): T {
        require(req is RecipeUpdateResponse)
        @Suppress("UNCHECKED_CAST")
        return req.copy(responseType = discriminator) as T
    }
}

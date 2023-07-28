package com.book.recipe.api.v1.controller

import com.book.recipe.service.RecipeBlockingProcessor
import org.springframework.web.bind.annotation.*
import ru.otus.otuskotlin.marketplace.api.v1.models.*
import ru.otus.otuskotlin.recipe.common.BookContext
import ru.otus.otuskotlin.recipe.mappers.v1.*

@RestController
@RequestMapping("v1/recipe")
class RecipeController(private val processor: RecipeBlockingProcessor) {
    
    @PostMapping("create")
    fun createRecipe(@RequestBody request: RecipeCreateRequest): RecipeCreateResponse {
        val context = BookContext()
        context.fromTransport(request)
        processor.exec(context)
        return context.toTransportCreate()
    }

    @PostMapping("read")
    fun readRecipe(@RequestBody request: RecipeReadRequest): RecipeReadResponse {
        val context = BookContext()
        context.fromTransport(request)
        processor.exec(context)
        return context.toTransportRead()
    }

    @PostMapping("update")
    fun updateRecipe(@RequestBody request: RecipeUpdateRequest): RecipeUpdateResponse {
        val context = BookContext()
        context.fromTransport(request)
        processor.exec(context)
        return context.toTransportUpdate()
    }

    @PostMapping("delete")
    fun deleteRecipe(@RequestBody request: RecipeDeleteRequest): RecipeDeleteResponse {
        val context = BookContext()
        context.fromTransport(request)
        processor.exec(context)
        return context.toTransportDelete()
    }

    @PostMapping("search")
    fun searchRecipe(@RequestBody request: RecipeSearchRequest): RecipeSearchResponse {
        val context = BookContext()
        context.fromTransport(request)
        processor.exec(context)
        return context.toTransportSearch()
    }

    @PostMapping("offers")
    fun searchOffers(@RequestBody request: RecipeOffersRequest): RecipeOffersResponse {
        val context = BookContext()
        context.fromTransport(request)
        processor.exec(context)
        return context.toTransportOffers()
    }
}

//package com.book.recipe.api.v1.controller
//
//import com.book.recipe.service.RecipeBlockingProcessor
//import org.springframework.web.bind.annotation.*
//import ru.otus.otuskotlin.marketplace.api.v1.models.*
//import ru.otus.otuskotlin.recipe.common.BookContext
//import ru.otus.otuskotlin.recipe.mappers.v1.*
//
//@RestController
//@RequestMapping("v1/recipe")
//class OffersController(private val processor: RecipeBlockingProcessor) {
//    @PostMapping("offers")
//    fun searchOffers(@RequestBody request: RecipeOffersRequest): RecipeOffersResponse {
//        val context = BookContext()
//        context.fromTransport(request)
//        processor.exec(context)
//        return context.toTransportOffers()
//    }
//}

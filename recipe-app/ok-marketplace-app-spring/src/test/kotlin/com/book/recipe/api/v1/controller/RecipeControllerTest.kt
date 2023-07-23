package com.book.recipe.api.v1.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import com.book.recipe.service.RecipeBlockingProcessor
import ru.otus.otuskotlin.marketplace.api.v1.models.*
import ru.otus.otuskotlin.recipe.common.BookContext
import ru.otus.otuskotlin.recipe.mappers.v1.*

@WebMvcTest(RecipeController::class)
internal class RecipeControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    @MockBean
    private lateinit var processor: RecipeBlockingProcessor

    @Test
    fun createRecipe() = testStubRecipe(
        "/v1/recipe/create",
        RecipeCreateRequest(),
        BookContext().toTransportCreate()
    )

    @Test
    fun readRecipe() = testStubRecipe(
        "/v1/recipe/read",
        RecipeReadRequest(),
        BookContext().toTransportRead()
    )

    @Test
    fun updateRecipe() = testStubRecipe(
        "/v1/recipe/update",
        RecipeUpdateRequest(),
        BookContext().toTransportUpdate()
    )

    @Test
    fun deleteRecipe() = testStubRecipe(
        "/v1/recipe/delete",
        RecipeDeleteRequest(),
        BookContext().toTransportDelete()
    )

    @Test
    fun searchRecipe() = testStubRecipe(
        "/v1/recipe/search",
        RecipeSearchRequest(),
        BookContext().toTransportSearch()
    )

    @Test
    fun searchOffers() = testStubRecipe(
        "/v1/recipe/offers",
        RecipeOffersRequest(),
        BookContext().toTransportOffers()
    )

    private fun <Req : Any, Res : Any> testStubRecipe(
        url: String,
        requestObj: Req,
        responseObj: Res,
    ) {
        val request = mapper.writeValueAsString(requestObj)
        val response = mapper.writeValueAsString(responseObj)

        mvc.perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
            .andExpect(status().isOk)
            .andExpect(content().json(response))
    }
}

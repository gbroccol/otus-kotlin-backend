package ru.otus.otuskotlin.recipe.mappers.v1

import org.junit.Test
import ru.otus.otuskotlin.marketplace.api.v1.models.*
import ru.otus.otuskotlin.recipe.common.BookContext
import ru.otus.otuskotlin.recipe.common.models.*
import ru.otus.otuskotlin.recipe.common.stubs.BookStubs
import kotlin.test.assertEquals

class MapperTest {

    @Test
    fun fromTransport() {

        val req = RecipeCreateRequest(
            requestId = "1234",
            debug = RecipeDebug(
                mode = RecipeRequestDebugMode.STUB,
                stub = RecipeRequestDebugStubs.SUCCESS,
            ),
            recipe = RecipeCreateObject(
                ownerId = "1",
                name = "Шарлотка",
                description = "desc",
                photo = "photo",
                cookingTime = "01:35",
                activeCookingTime = "00:30",
                portionQnt = 4,
                proteins = 20,
                fats = 15,
                carbohydrates = 60,
                calories = 200,
                spiciness = 1,
                complexityScale = 4,
                createDt = "",
                ingredients = mutableListOf(
                    RecipeIngredient(
                        ingredientId = "1",
                        qnt = 10,
                        unit = IngredientUnit.CT
                    ),
                    RecipeIngredient(
                        ingredientId = "2",
                        qnt = 11,
                        unit = IngredientUnit.G
                    ),
                    RecipeIngredient(
                        ingredientId = "3",
                        qnt = 12,
                        unit = IngredientUnit.ML
                    )
                ),
                steps = mutableListOf(
                    RecipeStep(
                        stepNmb = 0,
                        photo = "photo",
                        description = "description"
                    )
                )
            ),
        )

        val context = BookContext()
        context.fromTransport(req)

        assertEquals(BookCommand.CREATE, context.command)
        assertEquals(BookStubs.SUCCESS, context.stubCase)
        assertEquals(BookWorkMode.STUB, context.workMode)
        assertEquals("1234", context.requestId.asString())
        assertEquals("1", context.recipeRequest.ownerId.asString())
        assertEquals("Шарлотка", context.recipeRequest.name)
        val recipeIngredient: List<BookRecipeIngredient> = context.recipeRequest.ingredients
        assertEquals(3, recipeIngredient.size)
        val recipeSteps: List<BookRecipeStep> = context.recipeRequest.steps
        assertEquals(1, recipeSteps.size)
    }

    @Test
    fun toTransport() {
        val context = BookContext(
            requestId = BookRequestId("1234"),
            command = BookCommand.CREATE,
            recipeResponse = BookRecipe(
                name = "name",
                description = "desc",
            ),
            errors = mutableListOf(
                BookError(
                    code = "err",
                    group = "request",
                    field = "title",
                    message = "wrong title",
                )
            ),
            state = BookState.RUNNING,
        )

        val req = context.toTransportRecipe() as RecipeCreateResponse

        assertEquals("1234", req.requestId)
        assertEquals("name", req.recipe?.name)
        assertEquals("desc", req.recipe?.description)
        assertEquals(1, req.errors?.size)
        assertEquals("err", req.errors?.firstOrNull()?.code)
        assertEquals("request", req.errors?.firstOrNull()?.group)
        assertEquals("title", req.errors?.firstOrNull()?.field)
        assertEquals("wrong title", req.errors?.firstOrNull()?.message)
    }
}

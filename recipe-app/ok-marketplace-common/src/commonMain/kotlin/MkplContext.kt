package ru.otus.otuskotlin.recipe.common

import kotlinx.datetime.Instant
import ru.otus.otuskotlin.recipe.common.models.*
import ru.otus.otuskotlin.recipe.common.stubs.BookStubs

data class MkplContext(
    var command: BookCommand = BookCommand.NONE,
    var state: BookState = BookState.NONE,
    val errors: MutableList<BookError> = mutableListOf(),

    var workMode: BookWorkMode = BookWorkMode.PROD,
    var stubCase: BookStubs = BookStubs.NONE,

    var timeStart: Instant = Instant.NONE,

    /* request */
    var requestId: BookRequestId = BookRequestId.NONE,
    var recipeRequest: BookRecipe = BookRecipe(),
    var recipeFilterRequest: BookRecipeFilter = BookRecipeFilter(),

    /* response */
    var recipeResponse: BookRecipe = BookRecipe(),
    var recipesResponse: MutableList<BookRecipe> = mutableListOf(),
)

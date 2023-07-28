package ru.otus.otuskotlin.recipe.mappers.v1

import ru.otus.otuskotlin.marketplace.api.v1.models.*
import ru.otus.otuskotlin.recipe.common.BookContext
import ru.otus.otuskotlin.recipe.common.models.*
import ru.otus.otuskotlin.recipe.mappers.v1.exceptions.UnknownBookCommand

fun BookContext.toTransportRecipe(): IResponse = when (val cmd = command) {
    BookCommand.CREATE -> toTransportCreate()
    BookCommand.READ -> toTransportRead()
    BookCommand.UPDATE -> toTransportUpdate()
    BookCommand.DELETE -> toTransportDelete()
    BookCommand.SEARCH -> toTransportSearch()
    BookCommand.OFFERS -> toTransportOffers()
    BookCommand.NONE -> throw UnknownBookCommand(cmd)
}

fun BookContext.toTransportCreate() = RecipeCreateResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == BookState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    recipe = recipeResponse.toTransportRecipe()
)

fun BookContext.toTransportRead() = RecipeReadResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == BookState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    recipe = recipeResponse.toTransportRecipe()
)

fun BookContext.toTransportUpdate() = RecipeUpdateResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == BookState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    recipe = recipeResponse.toTransportRecipe()
)

fun BookContext.toTransportDelete() = RecipeDeleteResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == BookState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    recipe = recipeResponse.toTransportRecipe()
)

fun BookContext.toTransportSearch() = RecipeSearchResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == BookState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = this.errors.toTransportErrors(),
    recipes = this.recipesResponse.toTransportRecipe()
)

fun BookContext.toTransportOffers() = RecipeOffersResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == BookState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    recipes = recipesResponse.toTransportRecipe()
)

fun List<BookRecipe>.toTransportRecipe(): List<RecipeResponseObject>? = this
    .map { it.toTransportRecipe() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun BookRecipe.toTransportRecipe(): RecipeResponseObject = RecipeResponseObject(

    ownerId = this.ownerId.asString(),
    recipeId = this.recipeId.asString(),
    name = this.name,
    description = this.description,
    photo = this.photo,
    cookingTime = this.cookingTime,
    activeCookingTime = this.activeCookingTime,
    portionQnt = this.portionQnt,
    proteins = this.proteins,
    fats = this.fats,
    carbohydrates = this.carbohydrates,
    calories = this.calories,
    spiciness = this.spiciness,
    complexityScale = this.complexityScale,
//    createDt =
    ingredients = this.ingredients.toTransportRecipeIngredient(),
    steps = this.steps.toTransportRecipeSteps()
//    lock =
)

private fun List<BookError>.toTransportErrors(): List<Error>? = this
    .map { it.toTransportRecipe() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun BookError.toTransportRecipe() = Error(
    code = code.takeIf { it.isNotBlank() },
    group = group.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
    message = message.takeIf { it.isNotBlank() },
)

private fun List<BookRecipeIngredient>.toTransportRecipeIngredient(): List<RecipeIngredient>? = this
    .map { it.toTransport() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun BookRecipeIngredient.toTransport() = RecipeIngredient(
    ingredientId = this.ingredientId.asLong(),
    qnt = this.qnt,
    unit = this.unit.fromTransport()
)

private fun BookIngredientUnit.fromTransport() = when (this) {
    BookIngredientUnit.G -> IngredientUnit.G
    BookIngredientUnit.ML -> IngredientUnit.ML
    BookIngredientUnit.CT -> IngredientUnit.CT
    else -> { IngredientUnit.CT} // TODO как быть с дефолтом, в доке описывать?
}

private fun List<BookRecipeStep>.toTransportRecipeSteps(): List<RecipeStep>? = this
    .map { it.toTransport() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun BookRecipeStep.toTransport() = RecipeStep(
    stepNmb = this.stepNmb,
    photo = this.photo,
    description = this.description
)
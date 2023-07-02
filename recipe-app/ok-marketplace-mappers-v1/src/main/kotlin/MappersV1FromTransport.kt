package ru.otus.otuskotlin.recipe.mappers.v1

import ru.otus.otuskotlin.marketplace.api.v1.models.*
import ru.otus.otuskotlin.recipe.common.BookContext
import ru.otus.otuskotlin.recipe.common.models.*
import ru.otus.otuskotlin.recipe.common.stubs.BookStubs
import ru.otus.otuskotlin.recipe.mappers.v1.exceptions.UnknownRequestClass

fun BookContext.fromTransport(request: IRequest) = when (request) {
    is RecipeCreateRequest -> fromTransport(request)
    is RecipeReadRequest -> fromTransport(request)
    is RecipeUpdateRequest -> fromTransport(request)
    is RecipeDeleteRequest -> fromTransport(request)
    is RecipeSearchRequest -> fromTransport(request)
    is RecipeOffersRequest -> fromTransport(request)
    else -> throw UnknownRequestClass(request.javaClass)
}

private fun Long?.toIngredientId() = this?.let { BookIngredientId(it) } ?: BookIngredientId.NONE
private fun Long?.toRecipeId() = this?.let { BookRecipeId(it) } ?: BookRecipeId.NONE
private fun Long?.toRecipeWithId() = BookRecipe(recipeId = this.toRecipeId())

private fun Long?.toUserId() = this?.let { BookUserId(it) } ?: BookUserId.NONE

private fun IRequest?.requestId() = this?.requestId?.let { BookRequestId(it) } ?: BookRequestId.NONE

private fun RecipeDebug?.transportToWorkMode(): BookWorkMode = when (this?.mode) {
    RecipeRequestDebugMode.PROD -> BookWorkMode.PROD
    RecipeRequestDebugMode.TEST -> BookWorkMode.TEST
    RecipeRequestDebugMode.STUB -> BookWorkMode.STUB
    null -> BookWorkMode.PROD
}

private fun RecipeDebug?.transportToStubCase(): BookStubs = when (this?.stub) {
    RecipeRequestDebugStubs.SUCCESS -> BookStubs.SUCCESS
    RecipeRequestDebugStubs.NOT_FOUND -> BookStubs.NOT_FOUND
    RecipeRequestDebugStubs.BAD_ID -> BookStubs.BAD_ID
    RecipeRequestDebugStubs.BAD_TITLE -> BookStubs.BAD_TITLE
    RecipeRequestDebugStubs.BAD_DESCRIPTION -> BookStubs.BAD_DESCRIPTION
    RecipeRequestDebugStubs.BAD_VISIBILITY -> BookStubs.BAD_VISIBILITY
    RecipeRequestDebugStubs.CANNOT_DELETE -> BookStubs.CANNOT_DELETE
    RecipeRequestDebugStubs.BAD_SEARCH_STRING -> BookStubs.BAD_SEARCH_STRING
    null -> BookStubs.NONE
}

fun BookContext.fromTransport(request: RecipeCreateRequest) {
    command = BookCommand.CREATE
    requestId = request.requestId()
    recipeRequest = request.recipe?.toInternal() ?: BookRecipe()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun BookContext.fromTransport(request: RecipeReadRequest) {
    command = BookCommand.READ
    requestId = request.requestId()
    recipeRequest = request.recipe?.recipeId.toRecipeWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun BookContext.fromTransport(request: RecipeUpdateRequest) {
    command = BookCommand.UPDATE
    requestId = request.requestId()
    recipeRequest = request.recipe?.toInternal() ?: BookRecipe()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun BookContext.fromTransport(request: RecipeDeleteRequest) {
    command = BookCommand.DELETE
    requestId = request.requestId()
    recipeRequest = request.recipe?.recipeId.toRecipeWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun BookContext.fromTransport(request: RecipeSearchRequest) {
    command = BookCommand.SEARCH
    requestId = request.requestId()
    recipeFilterRequest = request.recipeFilter.toInternal()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun BookContext.fromTransport(request: RecipeOffersRequest) {
    command = BookCommand.OFFERS
    requestId = request.requestId()
    recipeRequest = request.recipe?.recipeId.toRecipeWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

private fun RecipeSearchFilter?.toInternal(): BookRecipeFilter = BookRecipeFilter(
    searchString = this?.searchString ?: ""
)

private fun RecipeCreateObject.toInternal(): BookRecipe = BookRecipe(
    ownerId = this.ownerId.toUserId(),
    name = this.name ?: "",
    description = this.description ?: "",
    photo = this.photo ?: "",
    cookingTime = this.cookingTime ?: "", // ?
    activeCookingTime = this.activeCookingTime ?: "",
    portionQnt = this.portionQnt ?: 0,
    proteins = this.proteins ?: 0,
    fats = this.fats ?: 0,
    carbohydrates = this.carbohydrates ?: 0,
    calories = this.calories ?: 0,
    spiciness = this.spiciness ?: 0,
    complexityScale = this.complexityScale ?: 0,
    ingredients = this.ingredients.fromTransport(),
    steps = this.steps.fromTransportRecipeStep()
)
fun  List<RecipeIngredient>?.fromTransport(): MutableList<BookRecipeIngredient> = this
    ?.map { it.fromTransport() }
    ?.toList()
    .takeIf { it!!.isNotEmpty() } as MutableList<BookRecipeIngredient> // TODO fix !! - не понимаю как без проверок сделать

private fun RecipeIngredient.fromTransport(): BookRecipeIngredient = BookRecipeIngredient(
    ingredientId = this.ingredientId.toIngredientId(),
    qnt = this.qnt ?: 0,
    unit = this.unit?.fromTransport() ?: BookIngredientUnit.NONE
)

private fun IngredientUnit.fromTransport() = when (this) {
    IngredientUnit.G -> BookIngredientUnit.G
    IngredientUnit.ML -> BookIngredientUnit.ML
    IngredientUnit.CT -> BookIngredientUnit.CT
}

fun  List<RecipeStep>?.fromTransportRecipeStep(): MutableList<BookRecipeStep> = this
    ?.map { it.fromTransport() }
    ?.toList()
    .takeIf { it!!.isNotEmpty() } as MutableList<BookRecipeStep> // TODO fix !! - не понимаю как без проверок сделать

private fun RecipeStep.fromTransport(): BookRecipeStep = BookRecipeStep(
    stepNmb = this.stepNmb ?: 0,
    photo = this.photo ?: "",
    description = this.description ?: ""
)

private fun RecipeUpdateObject.toInternal(): BookRecipe = BookRecipe(
    ownerId = this.ownerId.toUserId(),
    name = this.name ?: "",
    description = this.description ?: "",
    photo = this.photo ?: "",
    cookingTime = this.cookingTime ?: "", // ?
    activeCookingTime = this.activeCookingTime ?: "",
    portionQnt = this.portionQnt ?: 0,
    proteins = this.proteins ?: 0,
    fats = this.fats ?: 0,
    carbohydrates = this.carbohydrates ?: 0,
    calories = this.calories ?: 0,
    spiciness = this.spiciness ?: 0,
    complexityScale = this.complexityScale ?: 0,
    ingredients = this.ingredients.fromTransport(),
    steps = this.steps.fromTransportRecipeStep()
)

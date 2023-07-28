package ru.otus.otuskotlin.recipe.common.models

data class BookRecipe(

    var recipeId: BookRecipeId = BookRecipeId.NONE,
    var ownerId: BookUserId = BookUserId.NONE,
    var name: String = "",
    var description: String = "",
    var photo: String = "",
    var cookingTime: String = "",           // TODO interval - обдумать
    var activeCookingTime: String = "",     // TODO interval - обдумать
    var portionQnt: Int = 0,
    var proteins: Int = 0,
    var fats: Int = 0,
    var carbohydrates: Int = 0,
    var calories: Int = 0,
    var spiciness: Int = 0,
    var complexityScale: Int = 0,
    // TODO var createDt: LocalDateTime = LocalDateTime.now(), - обдумать
    val ingredients: MutableList<BookRecipeIngredient> = mutableListOf(),
    val steps: MutableList<BookRecipeStep> = mutableListOf()
)

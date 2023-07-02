package ru.otus.otuskotlin.recipe.common.models

data class BookRecipeIngredient(

    var ingredientId: BookIngredientId = BookIngredientId.NONE,
    var qnt: Int = 0,
    var unit: BookIngredientUnit = BookIngredientUnit.NONE

)

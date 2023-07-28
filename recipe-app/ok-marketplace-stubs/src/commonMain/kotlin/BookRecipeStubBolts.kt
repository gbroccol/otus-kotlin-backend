package ru.otus.otuskotlin.marketplace.stubs

import ru.otus.otuskotlin.recipe.common.models.*

object BookRecipeStubBolts {
    val RECIPE: BookRecipe
        get() = BookRecipe(
            recipeId = BookRecipeId.NONE,
            ownerId = BookUserId.NONE,
            name = "Тут будет название рецепта",
            description = "",
            photo = "",
            cookingTime = "",
            activeCookingTime = "",
            portionQnt = 0,
            proteins = 0,
            fats = 0,
            carbohydrates = 0,
            calories = 0,
            spiciness = 0,
            complexityScale = 0,
            ingredients = mutableListOf(),
            steps = mutableListOf()
        )

    val RECIPE2 = RECIPE.copy(name = "Второй рецепт")
}

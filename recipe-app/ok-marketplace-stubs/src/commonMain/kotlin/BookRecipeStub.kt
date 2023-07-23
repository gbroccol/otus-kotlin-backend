package ru.otus.otuskotlin.marketplace.stubs

import ru.otus.otuskotlin.marketplace.stubs.BookRecipeStubBolts.RECIPE
import ru.otus.otuskotlin.recipe.common.models.BookRecipe
import ru.otus.otuskotlin.recipe.common.models.BookRecipeId

object BookRecipeStub {
    fun get(): BookRecipe = RECIPE.copy()

    fun prepareResult(block: BookRecipe.() -> Unit): BookRecipe = get().apply(block)

    fun prepareSearchList(filter: String) = listOf(
        updateStumbRecipe("d-666-01", "name1", filter),
        updateStumbRecipe("d-666-02", "name2", filter),
        updateStumbRecipe("d-666-03", "name3", filter),
        updateStumbRecipe("d-666-04", "name4", filter),
        updateStumbRecipe("d-666-05", "name5", filter),
        updateStumbRecipe("d-666-06", "name6", filter),
    )

    private fun updateStumbRecipe(id: String, name: String, filter: String) =
    recipeBook(RECIPE,id = id, name = name, filter = filter)

    private fun recipeBook(base: BookRecipe, id: String, name: String, filter: String) = base.copy(
        recipeId = BookRecipeId(id),
        name = "$name $id",
        description = "desc $name $id $filter",
    )

}

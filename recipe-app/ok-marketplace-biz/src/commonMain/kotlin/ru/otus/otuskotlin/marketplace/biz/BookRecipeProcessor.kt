package ru.otus.otuskotlin.marketplace.biz

import ru.otus.otuskotlin.recipe.common.BookContext
import ru.otus.otuskotlin.marketplace.stubs.BookRecipeStub

class BookRecipeProcessor {
    suspend fun exec(ctx: BookContext) {
        ctx.recipeResponse = BookRecipeStub.get()
    }
}

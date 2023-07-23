package com.book.recipe.service

import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import ru.otus.otuskotlin.marketplace.biz.BookRecipeProcessor
import ru.otus.otuskotlin.recipe.common.BookContext

@Service
class RecipeBlockingProcessor {
    private val processor = BookRecipeProcessor()

    fun exec(ctx: BookContext) = runBlocking { processor.exec(ctx) }
}

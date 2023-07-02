package ru.otus.otuskotlin.recipe.common.helpers

import ru.otus.otuskotlin.recipe.common.BookContext
import ru.otus.otuskotlin.recipe.common.models.BookCommand

fun BookContext.isUpdatableCommand() =
    this.command in listOf(BookCommand.CREATE, BookCommand.UPDATE, BookCommand.DELETE)

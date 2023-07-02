package ru.otus.otuskotlin.recipe.common.helpers

import ru.otus.otuskotlin.recipe.common.MkplContext
import ru.otus.otuskotlin.recipe.common.models.BookCommand

fun MkplContext.isUpdatableCommand() =
    this.command in listOf(BookCommand.CREATE, BookCommand.UPDATE, BookCommand.DELETE)

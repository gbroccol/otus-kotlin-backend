package ru.otus.otuskotlin.recipe.common.helpers

import ru.otus.otuskotlin.recipe.common.BookContext
import ru.otus.otuskotlin.recipe.common.models.BookError

fun Throwable.asMkplError(
    code: String = "unknown",
    group: String = "exceptions",
    message: String = this.message ?: "",
) = BookError(
    code = code,
    group = group,
    field = "",
    message = message,
    exception = this,
)

fun BookContext.addError(vararg error: BookError) = errors.addAll(error)

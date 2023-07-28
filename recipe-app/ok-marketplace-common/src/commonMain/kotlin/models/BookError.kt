package ru.otus.otuskotlin.recipe.common.models

data class BookError(
    val code: String = "",
    val group: String = "",
    val field: String = "",
    val message: String = "",
    val exception: Throwable? = null,
)

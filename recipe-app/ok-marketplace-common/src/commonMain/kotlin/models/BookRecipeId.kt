package ru.otus.otuskotlin.recipe.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class BookRecipeId(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = BookRecipeId("0")
    }
}

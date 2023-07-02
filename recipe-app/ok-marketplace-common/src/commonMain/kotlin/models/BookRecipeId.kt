package ru.otus.otuskotlin.recipe.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class BookRecipeId(private val id: Long) {
    fun asString() = id.toString()

    fun asLong() = id

    companion object {
        val NONE = BookRecipeId(0)
    }
}

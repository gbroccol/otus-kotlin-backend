package ru.otus.otuskotlin.recipe.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class BookUserId(private val id: Long) {
    fun asString() = id.toString()

    fun asLong() = id

    companion object {
        val NONE = BookUserId(0)
    }
}

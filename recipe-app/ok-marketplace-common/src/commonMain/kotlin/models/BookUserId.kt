package ru.otus.otuskotlin.recipe.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class BookUserId(private val id: String) {

    fun asString() = id

    companion object {
        val NONE = BookUserId("0")
    }
}

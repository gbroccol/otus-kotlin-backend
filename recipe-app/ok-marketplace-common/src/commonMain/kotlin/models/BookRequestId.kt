package ru.otus.otuskotlin.recipe.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class BookRequestId(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = BookRequestId("")
    }
}

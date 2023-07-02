package ru.otus.otuskotlin.recipe.common.models

data class BookRecipeFilter(
    var searchString: String = "",
    var ownerId: BookUserId = BookUserId.NONE,
    var dealSide: BookDealSide = BookDealSide.NONE,
)

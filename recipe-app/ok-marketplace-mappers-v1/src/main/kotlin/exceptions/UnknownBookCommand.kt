package ru.otus.otuskotlin.recipe.mappers.v1.exceptions

import ru.otus.otuskotlin.recipe.common.models.BookCommand

class UnknownBookCommand(command: BookCommand) : Throwable("Wrong command $command at mapping toTransport stage")

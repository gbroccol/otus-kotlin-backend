package ru.otus.otuskotlin.recipe.mappers.v1.exceptions

class UnknownRequestClass(clazz: Class<*>) : RuntimeException("Class $clazz cannot be mapped to BookContext")

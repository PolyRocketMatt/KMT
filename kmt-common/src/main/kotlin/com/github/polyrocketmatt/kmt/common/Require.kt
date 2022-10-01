package com.github.polyrocketmatt.kmt.common

fun <T> throwIf(instance: T, condition: Boolean, message: String): T {
    if (condition) {
        throw IllegalArgumentException(message)
    }

    return instance
}

fun throwIf(condition: Boolean, message: String) {
    if (condition) {
        throw IllegalArgumentException(message)
    }
}
package com.github.polyrocketmatt.kmt.function.differentiation

import com.github.polyrocketmatt.kmt.function.Function

@FunctionalInterface
interface ExactDifferentiation<T> {

    fun derivative(): Function<T>

}
package com.github.polyrocketmatt.kmt.function.differentiation

import com.github.polyrocketmatt.kmt.function.Function

@FunctionalInterface
interface Differentiable<T> {

    fun derivative(): Function<T>

}
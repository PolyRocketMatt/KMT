package com.github.polyrocketmatt.kmt.function.type

import com.github.polyrocketmatt.kmt.function.Function

abstract class RecursiveFunction<T>(arity: Int) : Function<T>(arity) {

    abstract operator fun get(x: Double, n: Int): T
}

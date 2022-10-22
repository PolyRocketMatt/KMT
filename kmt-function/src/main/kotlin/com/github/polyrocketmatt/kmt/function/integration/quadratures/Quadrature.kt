package com.github.polyrocketmatt.kmt.function.integration.quadratures

import com.github.polyrocketmatt.kmt.function.Function
import com.github.polyrocketmatt.kmt.interval.Interval

@FunctionalInterface
interface Quadrature<T> {

    fun integrate(interval: Interval<Double>, function: Function<T>): DoubleArray
}

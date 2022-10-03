package com.github.polyrocketmatt.kmt.function.integration

import com.github.polyrocketmatt.kmt.function.Function
import com.github.polyrocketmatt.kmt.interval.Interval

@FunctionalInterface
interface Quadrature<T> {

    fun integrate(interval: Interval<T>, function: Function<T>): Array<Double>

}
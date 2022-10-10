package com.github.polyrocketmatt.kmt.function.integration

import com.github.polyrocketmatt.kmt.function.integration.quadratures.Quadrature
import com.github.polyrocketmatt.kmt.interval.Interval

@FunctionalInterface
interface Integrable<T> {

    fun integrate(interval: Interval<Double>, quadrature: Quadrature<T>): DoubleArray

}
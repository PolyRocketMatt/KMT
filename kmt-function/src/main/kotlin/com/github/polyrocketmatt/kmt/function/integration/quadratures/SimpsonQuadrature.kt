package com.github.polyrocketmatt.kmt.function.integration.quadratures

import com.github.polyrocketmatt.kmt.function.Function
import com.github.polyrocketmatt.kmt.interval.Interval

class SimpsonQuadrature<T>() : Quadrature<T> {

    override fun integrate(interval: Interval<Double>, function: Function<T>): DoubleArray {
        val accurateFunction = function.accurate()
        if (interval.count() < 2)
            throw IllegalArgumentException("Range must have at least 2 elements to integrate")
        val buffer = DoubleArray(interval.count() - 1)
        for (i in 0 until interval.count() - 1) {
            val a = interval[i]
            val b = interval[i + 1]
            val factor = (b - a) / 6.0f
            val fA = accurateFunction[a]
            val fB = accurateFunction[b]
            val fMid = 4.0f * accurateFunction[(a + b) / 2.0f]

            buffer[i] = factor * (fA + fMid + fB)
        }

        return buffer
    }
}
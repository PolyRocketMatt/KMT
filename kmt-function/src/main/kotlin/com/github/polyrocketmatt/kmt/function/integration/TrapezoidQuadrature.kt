package com.github.polyrocketmatt.kmt.function.integration

import com.github.polyrocketmatt.kmt.common.DataType
import com.github.polyrocketmatt.kmt.function.variate.Univariate
import com.github.polyrocketmatt.kmt.interval.Interval

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Implementation of the trapezoid quadrature method for numerical integration on
 * a univariate function of type [T].
 *
 * @param T The type of the output of the function.
 */
abstract class TrapezoidQuadrature<T> : Quadrature<T> {

    companion object {

        /**
         * Get a trapezoid quadrature for a univariate function given a datatype.
         *
         * @param T The type of the output of the function.
         * @param type The datatype of the function.
         * @return A trapezoid quadrature for a univariate function of the given datatype.
         */
        @Suppress("UNCHECKED_CAST")
        fun <T> get(type: DataType): TrapezoidQuadrature<T> = when (type) {
            DataType.DOUBLE -> DoubleTrapezoidIntegrator() as TrapezoidQuadrature<T>
            DataType.FLOAT -> FloatTrapezoidIntegrator() as TrapezoidQuadrature<T>
            DataType.INT -> IntTrapezoidIntegrator() as TrapezoidQuadrature<T>
            DataType.SHORT -> ShortTrapezoidIntegrator() as TrapezoidQuadrature<T>
        }
    }
}

private class DoubleTrapezoidIntegrator : TrapezoidQuadrature<Double>() {

    override fun integrate(function: Univariate<Double>, interval: Interval<Double>): Array<Double> {
        if (interval.count() < 2)
            throw IllegalArgumentException("Range must have at least 2 elements to integrate")
        val buffer = DoubleArray(interval.count() - 1)
        for (i in 0 until interval.count() - 1) {
            val a = interval[i]
            val b = interval[i + 1]
            val fA = function.evaluate(a)
            val fB = function.evaluate(b)

            buffer[i] = (b - a) * (fA + fB) / 2
        }

        return buffer.toTypedArray()
    }
}

private class FloatTrapezoidIntegrator : TrapezoidQuadrature<Float>() {

    override fun integrate(function: Univariate<Float>, interval: Interval<Double>): Array<Double> {
        if (interval.count() < 2)
            throw IllegalArgumentException("Range must have at least 2 elements to integrate")
        val buffer = DoubleArray(interval.count() - 1)
        for (i in 0 until interval.count() - 1) {
            val a = interval[i]
            val b = interval[i + 1]
            val fA = function.evaluate(a)
            val fB = function.evaluate(b)

            buffer[i] = (b - a) * (fA + fB) / 2
        }

        return buffer.toTypedArray()
    }
}

private class IntTrapezoidIntegrator : TrapezoidQuadrature<Int>() {

    override fun integrate(function: Univariate<Int>, interval: Interval<Double>): Array<Double> {
        if (interval.count() < 2)
            throw IllegalArgumentException("Range must have at least 2 elements to integrate")
        val buffer = DoubleArray(interval.count() - 1)
        for (i in 0 until interval.count() - 1) {
            val a = interval[i]
            val b = interval[i + 1]
            val fA = function.evaluate(a)
            val fB = function.evaluate(b)

            buffer[i] = (b - a) * (fA + fB) / 2
        }

        return buffer.toTypedArray()
    }
}

private class ShortTrapezoidIntegrator : TrapezoidQuadrature<Short>() {

    override fun integrate(function: Univariate<Short>, interval: Interval<Double>): Array<Double> {
        if (interval.count() < 2)
            throw IllegalArgumentException("Range must have at least 2 elements to integrate")
        val buffer = DoubleArray(interval.count() - 1)
        for (i in 0 until interval.count() - 1) {
            val a = interval[i]
            val b = interval[i + 1]
            val fA = function.evaluate(a)
            val fB = function.evaluate(b)

            buffer[i] = (b - a) * (fA + fB) / 2
        }

        return buffer.toTypedArray()
    }
}

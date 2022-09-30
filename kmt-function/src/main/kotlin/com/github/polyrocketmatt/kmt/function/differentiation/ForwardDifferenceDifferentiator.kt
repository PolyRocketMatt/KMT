/*
 * KMT, Kotlin Math Toolkit
 * Copyright (C) Matthias Kovacic <matthias.kovacic@student.kuleuven.be>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.polyrocketmatt.kmt.function.differentiation

import com.github.polyrocketmatt.kmt.common.DataType
import com.github.polyrocketmatt.kmt.common.fastAbs
import com.github.polyrocketmatt.kmt.function.variate.Univariate
import com.github.polyrocketmatt.kmt.interval.Interval

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Implementation of forward-difference method for numerical differentiation on
 * a univariate function of type [T].
 *
 * @param T The type of the output of the function.
 */
abstract class ForwardDifferenceDifferentiator<T> : Differentiator<T> {

    companion object {

        /**
         * Get a forward-difference differentiator for a univariate function given a datatype.
         *
         * @param T The type of the output of the function.
         * @param type The datatype of the function.
         * @return A forward-difference differentiator for a univariate function of the given datatype.
         */
        @Suppress("UNCHECKED_CAST")
        fun <T> get(type: DataType): ForwardDifferenceDifferentiator<T> = when (type) {
            DataType.DOUBLE -> DoubleForwardDifferenceDifferentiator() as ForwardDifferenceDifferentiator<T>
            DataType.FLOAT -> FloatForwardDifferenceDifferentiator() as ForwardDifferenceDifferentiator<T>
            DataType.INT -> IntForwardDifferenceDifferentiator() as ForwardDifferenceDifferentiator<T>
            DataType.SHORT -> ShortForwardDifferenceDifferentiator() as ForwardDifferenceDifferentiator<T>
        }
    }
}

private class DoubleForwardDifferenceDifferentiator : ForwardDifferenceDifferentiator<Double>() {

    override fun differentiate(function: Univariate<Double>, interval: Interval<Double>): Array<Double> {
        if (interval.count() < 2)
            throw IllegalArgumentException("Range must have at least 2 elements to differentiate using forward-divided differences")
        val buffer = DoubleArray(interval.count() - 1)
        for (i in 0 until interval.count() - 1) {
            val a = interval[i]
            val b = interval[i + 1]
            val diff = (b - a).fastAbs()

            buffer[i] = (function.evaluate(b) - function.evaluate(a)) / diff
        }
        return buffer.toTypedArray()
    }
}

private class FloatForwardDifferenceDifferentiator : ForwardDifferenceDifferentiator<Float>() {

    override fun differentiate(function: Univariate<Float>, interval: Interval<Double>): Array<Double> {
        if (interval.count() < 2)
            throw IllegalArgumentException("Range must have at least 2 elements to differentiate using forward-divided differences")
        val buffer = DoubleArray(interval.count() - 1)
        for (i in 0 until interval.count() - 1) {
            val a = interval[i]
            val b = interval[i + 1]
            val diff = (b - a).fastAbs()

            buffer[i] = (function.evaluate(b) - function.evaluate(a)) / diff
        }
        return buffer.toTypedArray()
    }
}

private class IntForwardDifferenceDifferentiator : ForwardDifferenceDifferentiator<Int>() {

    override fun differentiate(function: Univariate<Int>, interval: Interval<Double>): Array<Double> {
        if (interval.count() < 2)
            throw IllegalArgumentException("Range must have at least 2 elements to differentiate using forward-divided differences")
        val buffer = DoubleArray(interval.count() - 1)
        for (i in 0 until interval.count() - 1) {
            val a = interval[i]
            val b = interval[i + 1]
            val diff = (b - a).fastAbs()

            buffer[i] = (function.evaluate(b) - function.evaluate(a)) / diff
        }
        return buffer.toTypedArray()
    }
}

private class ShortForwardDifferenceDifferentiator : ForwardDifferenceDifferentiator<Short>() {

    override fun differentiate(function: Univariate<Short>, interval: Interval<Double>): Array<Double> {
        if (interval.count() < 2)
            throw IllegalArgumentException("Range must have at least 2 elements to differentiate using forward-divided differences")
        val buffer = DoubleArray(interval.count() - 1)
        for (i in 0 until interval.count() - 1) {
            val a = interval[i]
            val b = interval[i + 1]
            val diff = (b - a).fastAbs()

            buffer[i] = (function.evaluate(b) - function.evaluate(a)) / diff
        }
        return buffer.toTypedArray()
    }
}

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

package com.github.polyrocketmatt.kmt.function.differentiable

import com.github.polyrocketmatt.kmt.function.variate.Univariate
import com.github.polyrocketmatt.kmt.range.Range
import com.github.polyrocketmatt.kmt.utils.DataType
import com.github.polyrocketmatt.kmt.utils.fastAbs

interface ForwardDifferenceDifferentiator<T> : Differentiator<T> {

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun <T> get(type: DataType): ForwardDifferenceDifferentiator<T> = when(type) {
            DataType.DOUBLE -> DoubleForwardDifferenceDifferentiator as ForwardDifferenceDifferentiator<T>
            DataType.FLOAT -> FloatForwardDifferenceDifferentiator as ForwardDifferenceDifferentiator<T>
            DataType.INT -> IntForwardDifferenceDifferentiator as ForwardDifferenceDifferentiator<T>
            DataType.SHORT -> ShortForwardDifferenceDifferentiator as ForwardDifferenceDifferentiator<T>
        }

    }

}

private object DoubleForwardDifferenceDifferentiator : ForwardDifferenceDifferentiator<Double> {

    override fun differentiate(function: Univariate<Double>, range: Range<Double>): Array<Double> {
        if (range.count() < 2)
            throw IllegalArgumentException("Range must have at least 2 elements to differentiate using forward-divided differences")
        val buffer = DoubleArray(range.count() - 1)
        for (i in 0 until range.count() - 1) {
            val a = range[i]
            val b = range[i + 1]
            val diff = (b - a).fastAbs()

            buffer[i] = (function.evaluate(b) - function.evaluate(a)) / diff
        }
        return buffer.toTypedArray()
    }
}

private object FloatForwardDifferenceDifferentiator : ForwardDifferenceDifferentiator<Float> {

    override fun differentiate(function: Univariate<Float>, range: Range<Double>): Array<Double> {
        if (range.count() < 2)
            throw IllegalArgumentException("Range must have at least 2 elements to differentiate using forward-divided differences")
        val buffer = DoubleArray(range.count() - 1)
        for (i in 0 until range.count() - 1) {
            val a = range[i]
            val b = range[i + 1]
            val diff = (b - a).fastAbs()

            buffer[i] = (function.evaluate(b) - function.evaluate(a)) / diff
        }
        return buffer.toTypedArray()
    }
}

private object IntForwardDifferenceDifferentiator : ForwardDifferenceDifferentiator<Int> {

    override fun differentiate(function: Univariate<Int>, range: Range<Double>): Array<Double> {
        if (range.count() < 2)
            throw IllegalArgumentException("Range must have at least 2 elements to differentiate using forward-divided differences")
        val buffer = DoubleArray(range.count() - 1)
        for (i in 0 until range.count() - 1) {
            val a = range[i]
            val b = range[i + 1]
            val diff = (b - a).fastAbs()

            buffer[i] = (function.evaluate(b) - function.evaluate(a)) / diff
        }
        return buffer.toTypedArray()
    }
}

private object ShortForwardDifferenceDifferentiator : ForwardDifferenceDifferentiator<Short> {

    override fun differentiate(function: Univariate<Short>, range: Range<Double>): Array<Double> {
        if (range.count() < 2)
            throw IllegalArgumentException("Range must have at least 2 elements to differentiate using forward-divided differences")
        val buffer = DoubleArray(range.count() - 1)
        for (i in 0 until range.count() - 1) {
            val a = range[i]
            val b = range[i + 1]
            val diff = (b - a).fastAbs()

            buffer[i] = (function.evaluate(b) - function.evaluate(a)) / diff
        }
        return buffer.toTypedArray()
    }
}
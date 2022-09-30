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

package com.github.polyrocketmatt.kmt.function.integration

import com.github.polyrocketmatt.kmt.common.DataType
import com.github.polyrocketmatt.kmt.function.variate.Univariate
import com.github.polyrocketmatt.kmt.interval.Interval

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Implementation of the Simpson's quadrature method for numerical integration on
 * a univariate function of type [T].
 *
 * @param T The type of the output of the function.
 */
abstract class SimpsonIntegrator<T> : Integrator<T> {

    companion object {

        /**
         * Get a Simpson quadrature for a univariate function given a datatype.
         *
         * @param T The type of the output of the function.
         * @param type The datatype of the function.
         * @return A Simpson quadrature for a univariate function of the given datatype.
         */
        @Suppress("UNCHECKED_CAST")
        fun <T> get(type: DataType): SimpsonIntegrator<T> = when(type) {
            DataType.DOUBLE -> DoubleSimpsonIntegrator() as SimpsonIntegrator<T>
            DataType.FLOAT -> FloatSimpsonIntegrator() as SimpsonIntegrator<T>
            DataType.INT -> IntSimpsonIntegrator() as SimpsonIntegrator<T>
            DataType.SHORT -> ShortSimpsonIntegrator() as SimpsonIntegrator<T>
        }

    }

}

private class DoubleSimpsonIntegrator : SimpsonIntegrator<Double>() {

    override fun integrate(function: Univariate<Double>, interval: Interval<Double>): Array<Double> {
        if (interval.count() < 2)
            throw IllegalArgumentException("Range must have at least 2 elements to integrate")
        val buffer = DoubleArray(interval.count() - 1)
        for (i in 0 until interval.count() - 1) {
            val a = interval[i]
            val b = interval[i + 1]
            val factor = (b - a) / 6.0f
            val fA = function.evaluate(a)
            val fB = function.evaluate(b)
            val fMid = 4.0f * function.evaluate((a + b) / 2.0f)

            buffer[i] = factor * (fA + fMid + fB)
        }

        return buffer.toTypedArray()
    }

}

private class FloatSimpsonIntegrator : SimpsonIntegrator<Float>() {

    override fun integrate(function: Univariate<Float>, interval: Interval<Double>): Array<Double> {
        if (interval.count() < 2)
            throw IllegalArgumentException("Range must have at least 2 elements to integrate")
        val buffer = DoubleArray(interval.count() - 1)
        for (i in 0 until interval.count() - 1) {
            val a = interval[i]
            val b = interval[i + 1]
            val factor = (b - a) / 6.0f
            val fA = function.evaluate(a)
            val fB = function.evaluate(b)
            val fMid = 4.0f * function.evaluate((a + b) / 2.0f)

            buffer[i] = factor * (fA + fMid + fB)
        }

        return buffer.toTypedArray()
    }

}

private class IntSimpsonIntegrator : SimpsonIntegrator<Int>() {

    override fun integrate(function: Univariate<Int>, interval: Interval<Double>): Array<Double> {
        if (interval.count() < 2)
            throw IllegalArgumentException("Range must have at least 2 elements to integrate")
        val buffer = DoubleArray(interval.count() - 1)
        for (i in 0 until interval.count() - 1) {
            val a = interval[i]
            val b = interval[i + 1]
            val factor = (b - a) / 6.0f
            val fA = function.evaluate(a)
            val fB = function.evaluate(b)
            val fMid = 4.0f * function.evaluate((a + b) / 2.0f)

            buffer[i] = factor * (fA + fMid + fB)
        }

        return buffer.toTypedArray()
    }

}

private class ShortSimpsonIntegrator : SimpsonIntegrator<Short>() {

    override fun integrate(function: Univariate<Short>, interval: Interval<Double>): Array<Double> {
        if (interval.count() < 2)
            throw IllegalArgumentException("Range must have at least 2 elements to integrate")
        val buffer = DoubleArray(interval.count() - 1)
        for (i in 0 until interval.count() - 1) {
            val a = interval[i]
            val b = interval[i + 1]
            val factor = (b - a) / 6.0f
            val fA = function.evaluate(a)
            val fB = function.evaluate(b)
            val fMid = 4.0f * function.evaluate((a + b) / 2.0f)

            buffer[i] = factor * (fA + fMid + fB)
        }

        return buffer.toTypedArray()
    }

}
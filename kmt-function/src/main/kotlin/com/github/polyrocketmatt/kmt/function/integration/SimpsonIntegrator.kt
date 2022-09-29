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

import com.github.polyrocketmatt.kmt.function.variate.Univariate
import com.github.polyrocketmatt.kmt.range.Range
import com.github.polyrocketmatt.kmt.utils.DataType

interface SimpsonIntegrator<T> : Integrator<T> {

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun <T> get(type: DataType): SimpsonIntegrator<T> = when(type) {
            DataType.DOUBLE -> DoubleSimpsonIntegrator as SimpsonIntegrator<T>
            DataType.FLOAT -> FloatSimpsonIntegrator as SimpsonIntegrator<T>
            DataType.INT -> IntSimpsonIntegrator as SimpsonIntegrator<T>
            DataType.SHORT -> ShortSimpsonIntegrator as SimpsonIntegrator<T>
        }

    }

}

private object DoubleSimpsonIntegrator : SimpsonIntegrator<Double> {

    override fun integrate(function: Univariate<Double>, range: Range<Double>): Array<Double> {
        if (range.count() < 2)
            throw IllegalArgumentException("Range must have at least 2 elements to integrate")
        val buffer = DoubleArray(range.count() - 1)
        for (i in 0 until range.count() - 1) {
            val a = range[i]
            val b = range[i + 1]
            val factor = (b - a) / 6.0f
            val fA = function.evaluate(a)
            val fB = function.evaluate(b)
            val fMid = 4.0f * function.evaluate((a + b) / 2.0f)

            buffer[i] = factor * (fA + fMid + fB)
        }

        return buffer.toTypedArray()
    }

}

private object FloatSimpsonIntegrator : SimpsonIntegrator<Float> {

    override fun integrate(function: Univariate<Float>, range: Range<Double>): Array<Double> {
        if (range.count() < 2)
            throw IllegalArgumentException("Range must have at least 2 elements to integrate")
        val buffer = DoubleArray(range.count() - 1)
        for (i in 0 until range.count() - 1) {
            val a = range[i]
            val b = range[i + 1]
            val factor = (b - a) / 6.0f
            val fA = function.evaluate(a)
            val fB = function.evaluate(b)
            val fMid = 4.0f * function.evaluate((a + b) / 2.0f)

            buffer[i] = factor * (fA + fMid + fB)
        }

        return buffer.toTypedArray()
    }

}

private object IntSimpsonIntegrator : SimpsonIntegrator<Int> {

    override fun integrate(function: Univariate<Int>, range: Range<Double>): Array<Double> {
        if (range.count() < 2)
            throw IllegalArgumentException("Range must have at least 2 elements to integrate")
        val buffer = DoubleArray(range.count() - 1)
        for (i in 0 until range.count() - 1) {
            val a = range[i]
            val b = range[i + 1]
            val factor = (b - a) / 6.0f
            val fA = function.evaluate(a)
            val fB = function.evaluate(b)
            val fMid = 4.0f * function.evaluate((a + b) / 2.0f)

            buffer[i] = factor * (fA + fMid + fB)
        }

        return buffer.toTypedArray()
    }

}

private object ShortSimpsonIntegrator : SimpsonIntegrator<Short> {

    override fun integrate(function: Univariate<Short>, range: Range<Double>): Array<Double> {
        if (range.count() < 2)
            throw IllegalArgumentException("Range must have at least 2 elements to integrate")
        val buffer = DoubleArray(range.count() - 1)
        for (i in 0 until range.count() - 1) {
            val a = range[i]
            val b = range[i + 1]
            val factor = (b - a) / 6.0f
            val fA = function.evaluate(a)
            val fB = function.evaluate(b)
            val fMid = 4.0f * function.evaluate((a + b) / 2.0f)

            buffer[i] = factor * (fA + fMid + fB)
        }

        return buffer.toTypedArray()
    }

}
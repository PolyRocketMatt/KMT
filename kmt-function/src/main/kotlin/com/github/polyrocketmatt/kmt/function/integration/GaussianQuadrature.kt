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
import com.github.polyrocketmatt.kmt.common.dsqrt
import com.github.polyrocketmatt.kmt.function.variate.Univariate
import com.github.polyrocketmatt.kmt.interval.Interval
import com.github.polyrocketmatt.kmt.interval.half.HalfOpenInterval
import kotlin.math.pow

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Implementation of the Gaussian quadrature method for numerical integration on
 * a univariate function of type [T].
 *
 * @param T The type of the output of the function.
 */
abstract class GaussianQuadrature<T>(val rule: GaussianQuadratureRule, val weightFunction: WeightFunction) : Quadrature<T> {

    /**
     * Enum class containing the different Gaussian quadrature rules.
     *
     * @param n The number of points used in the quadrature rule.
     */
    enum class GaussianQuadratureRule(val n: Int) {
        ONE_POINT(1),
        TWO_POINT(2),
        THREE_POINT(3),
        FOUR_POINT(4),
        FIVE_POINT(5)
    }

    /**
     * Enum class containing the different weight functions for the Gaussian
     * quadrature.
     *
     * @param function The weight function.
     */
    //  TODO: Make use of KMT-Common exp function
    enum class WeightFunction(val function: (DoubleArray) -> Double) {
        LEGENDRE({ 1.0 }),
        LAGUERRE({ if (it[0] == 0.0) 0.0 else it[0].pow(it[1]) * kotlin.math.exp(-it[0]) }),
        HERMITE({ kotlin.math.exp(-(it[0] * it[0])) }),
        JACOBI({ (1.0 - it[0]).pow(it[1]) * (1.0 + it[0]).pow(it[2]) }),
    }

    companion object {
        private val ONE_POINT_WEIGHTS = doubleArrayOf(2.0)
        private val ONE_POINT_POINTS = doubleArrayOf(0.0)

        private val TWO_POINT_WEIGHTS = doubleArrayOf(1.0, 1.0)
        private val TWO_POINT_POINTS = doubleArrayOf(-0.5773502691896257, 0.5773502691896257)

        private val THREE_POINT_WEIGHTS = doubleArrayOf(0.8888888888888888, 0.5555555555555556, 0.5555555555555556)
        private val THREE_POINT_POINTS = doubleArrayOf(0.0, -0.7745966692414834, 0.7745966692414834)

        private val FOUR_POINT_WEIGHTS = doubleArrayOf(0.6521451548625461, 0.6521451548625461, 0.3478548451374538, 0.3478548451374538)
        private val FOUR_POINT_POINTS = doubleArrayOf(-0.3399810435848563, 0.3399810435848563, -0.8611363115940526, 0.8611363115940526)

        private val FIVE_POINT_WEIGHTS = doubleArrayOf(0.5688888888888889, 0.4786286704993665, 0.4786286704993665, 0.23692688505618908, 0.23692688505618908)
        private val FIVE_POINT_POINTS = doubleArrayOf(0.0, -0.5384693101056831, 0.5384693101056831, -0.906179845938664, 0.906179845938664)

        internal fun constructQuadrature(rule: GaussianQuadratureRule): Pair<DoubleArray, DoubleArray> = Pair(
            when (rule) {
                GaussianQuadratureRule.ONE_POINT -> ONE_POINT_WEIGHTS
                GaussianQuadratureRule.TWO_POINT -> TWO_POINT_WEIGHTS
                GaussianQuadratureRule.THREE_POINT -> THREE_POINT_WEIGHTS
                GaussianQuadratureRule.FOUR_POINT -> FOUR_POINT_WEIGHTS
                GaussianQuadratureRule.FIVE_POINT -> FIVE_POINT_WEIGHTS
            },
            when (rule) {
                GaussianQuadratureRule.ONE_POINT -> ONE_POINT_POINTS
                GaussianQuadratureRule.TWO_POINT -> TWO_POINT_POINTS
                GaussianQuadratureRule.THREE_POINT -> THREE_POINT_POINTS
                GaussianQuadratureRule.FOUR_POINT -> FOUR_POINT_POINTS
                GaussianQuadratureRule.FIVE_POINT -> FIVE_POINT_POINTS
            }
        )

        /**
         * Get a Gaussian quadrature for a univariate function given a datatype.
         *
         * @param T The type of the output of the function.
         * @param rule The quadrature rule to use.
         * @param weightFunction The weight function to use.
         * @param type The datatype of the function.
         * @return A Gaussian quadrature for a univariate function of the given datatype.
         */
        @Suppress("UNCHECKED_CAST")
        fun <T> get(rule: GaussianQuadratureRule, weightFunction: WeightFunction, type: DataType): GaussianQuadrature<T> = when (type) {
            DataType.DOUBLE -> DoubleGaussianIntegrator(rule, weightFunction) as GaussianQuadrature<T>
            DataType.FLOAT -> FloatGaussianIntegrator(rule, weightFunction) as GaussianQuadrature<T>
            DataType.INT -> IntGaussianIntegrator(rule, weightFunction) as GaussianQuadrature<T>
            DataType.SHORT -> ShortGaussianIntegrator(rule, weightFunction) as GaussianQuadrature<T>
        }
    }
}

private class DoubleGaussianIntegrator(rule: GaussianQuadratureRule, weightFunction: WeightFunction) : GaussianQuadrature<Double>(rule, weightFunction) {

    private fun legendre(function: Univariate<Double>, interval: Interval<Double>): Array<Double> {
        val min = interval.min()
        val max = interval.max()
        val factor = (max - min) / 2.0
        val offset = (max + min) / 2.0
        val quadrature = constructQuadrature(rule)
        val weights = quadrature.first
        val points = quadrature.second
        val result = DoubleArray(rule.n)
        for (i in 0 until rule.n)
            result[i] = factor * weights[i] * function.evaluate(points[i] * factor + offset)
        return result.toTypedArray()
    }

    private fun laguerre(function: Univariate<Double>, interval: Interval<Double>): Array<Double> {
        val max = interval.max()
        if (interval !is HalfOpenInterval<Double>)
            throw IllegalArgumentException("Interval must be half open to integrate using Gauss-Laguerre quadrature")
        if (max != Double.MAX_VALUE)
            throw IllegalArgumentException("Interval must have a maximum value of infinity to integrate using Gauss-Laguerre quadrature")

        val quadrature = constructQuadrature(rule)
        val weights = quadrature.first
        val points = quadrature.second
        val result = DoubleArray(rule.n)
        for (i in 0 until rule.n) {
            val point = points[i]
            val weight = weights[i]
            val value = weights[i] * weightFunction.function(doubleArrayOf(points[i], 3.0)) * function.evaluate(points[i])

            result[i] = value
        }
        return result.toTypedArray()
    }

    override fun integrate(function: Univariate<Double>, interval: Interval<Double>): Array<Double> {
        if (interval.min() == interval.max())
            return arrayOf(0.0)

        return when (weightFunction) {
            WeightFunction.LEGENDRE         -> legendre(function, interval)
            WeightFunction.LAGUERRE         -> laguerre(function, interval)
            else                            -> throw IllegalArgumentException("Weight function not supported")
        }
    }
}

private class FloatGaussianIntegrator(rule: GaussianQuadratureRule, weightFunction: WeightFunction) : GaussianQuadrature<Float>(rule, weightFunction) {

    override fun integrate(function: Univariate<Float>, interval: Interval<Double>): Array<Double> {
        val min = interval.min()
        val max = interval.max()
        if (min == max)
            throw IllegalArgumentException("Interval must have a minimum and maximum value that are not equal to integrate using gaussian quadrature")
        val factor = (max - min) / 2.0
        val offset = (max + min) / 2.0
        val quadrature = constructQuadrature(rule)
        val weights = quadrature.first
        val points = quadrature.second
        val result = DoubleArray(rule.n)
        for (i in 0 until rule.n)
            result[i] = factor * weights[i] * function.evaluate(points[i] * factor + offset)
        return result.toTypedArray()
    }
}

private class IntGaussianIntegrator(rule: GaussianQuadratureRule, weightFunction: WeightFunction) : GaussianQuadrature<Int>(rule, weightFunction) {

    override fun integrate(function: Univariate<Int>, interval: Interval<Double>): Array<Double> {
        val min = interval.min()
        val max = interval.max()
        if (min == max)
            throw IllegalArgumentException("Interval must have a minimum and maximum value that are not equal to integrate using gaussian quadrature")
        val factor = (max - min) / 2.0
        val offset = (max + min) / 2.0
        val quadrature = constructQuadrature(rule)
        val weights = quadrature.first
        val points = quadrature.second
        val result = DoubleArray(rule.n)
        for (i in 0 until rule.n)
            result[i] = factor * weights[i] * function.evaluate(points[i] * factor + offset)
        return result.toTypedArray()
    }
}

private class ShortGaussianIntegrator(rule: GaussianQuadratureRule, weightFunction: WeightFunction) : GaussianQuadrature<Short>(rule, weightFunction) {

    override fun integrate(function: Univariate<Short>, interval: Interval<Double>): Array<Double> {
        val min = interval.min()
        val max = interval.max()
        if (min == max)
            throw IllegalArgumentException("Interval must have a minimum and maximum value that are not equal to integrate using gaussian quadrature")
        val factor = (max - min) / 2.0
        val offset = (max + min) / 2.0
        val quadrature = constructQuadrature(rule)
        val weights = quadrature.first
        val points = quadrature.second
        val result = DoubleArray(rule.n)
        for (i in 0 until rule.n)
            result[i] = factor * weights[i] * function.evaluate(points[i] * factor + offset)
        return result.toTypedArray()
    }
}
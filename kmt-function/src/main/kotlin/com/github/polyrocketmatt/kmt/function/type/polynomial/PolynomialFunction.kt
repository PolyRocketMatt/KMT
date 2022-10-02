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

package com.github.polyrocketmatt.kmt.function.type.polynomial

import com.github.polyrocketmatt.kmt.common.intPow
import com.github.polyrocketmatt.kmt.function.variate.Univariate
import kotlin.math.max

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a polynomial function.
 *
 * @param coefficients The coefficients of the polynomial.
 */
class PolynomialFunction(private vararg val coefficients: Double) : Univariate<Double>() {

    constructor(vararg coefficients: Float) : this(*coefficients.map { it.toDouble() }.toDoubleArray())
    constructor(vararg coefficients: Int) : this(*coefficients.map { it.toDouble() }.toDoubleArray())
    constructor(vararg coefficients: Short) : this(*coefficients.map { it.toDouble() }.toDoubleArray())

    override operator fun get(x: Double): Double = coefficients.foldIndexed(0.0) { index, acc, coefficient -> acc + coefficient * x.intPow(index) }

    override fun evaluate(x: Double): Double = get(x)

    override fun evaluate(x: Float): Double = get(x.toDouble())

    override fun evaluate(x: Int): Double = get(x.toDouble())

    override fun evaluate(x: Short): Double = get(x.toDouble())

    operator fun plus(other: PolynomialFunction): PolynomialFunction {
        val newCoefficients = DoubleArray(max(coefficients.size, other.coefficients.size))
        for (i in newCoefficients.indices)
            newCoefficients[i] = coefficients.getOrElse(i) { 0.0 } + other.coefficients.getOrElse(i) { 0.0 }
        return PolynomialFunction(*newCoefficients)
    }

    operator fun minus(other: PolynomialFunction): PolynomialFunction {
        val newCoefficients = DoubleArray(max(coefficients.size, other.coefficients.size))
        for (i in newCoefficients.indices)
            newCoefficients[i] = coefficients.getOrElse(i) { 0.0 } - other.coefficients.getOrElse(i) { 0.0 }
        return PolynomialFunction(*newCoefficients)
    }

    operator fun times(other: PolynomialFunction): PolynomialFunction {
        val newCoefficients = DoubleArray(max(coefficients.size, other.coefficients.size))
        for (i in newCoefficients.indices)
            newCoefficients[i] = coefficients.getOrElse(i) { 0.0 } * other.coefficients.getOrElse(i) { 0.0 }
        return PolynomialFunction(*newCoefficients)
    }

    operator fun div(other: PolynomialFunction): PolynomialFunction {
        val newCoefficients = DoubleArray(max(coefficients.size, other.coefficients.size))
        for (i in newCoefficients.indices)
            newCoefficients[i] = coefficients.getOrElse(i) { 0.0 } / other.coefficients.getOrElse(i) { 0.0 }
        return PolynomialFunction(*newCoefficients)
    }

}
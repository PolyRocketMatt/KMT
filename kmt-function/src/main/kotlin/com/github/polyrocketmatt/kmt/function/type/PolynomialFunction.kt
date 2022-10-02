package com.github.polyrocketmatt.kmt.function.type

import com.github.polyrocketmatt.kmt.common.intPow
import com.github.polyrocketmatt.kmt.function.variate.Univariate

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

    operator fun get(x: Double): Double = coefficients.foldIndexed(0.0) { index, acc, coefficient -> acc + coefficient * x.intPow(index) }

    override fun evaluate(x: Double): Double = get(x)

    override fun evaluate(x: Float): Double = get(x.toDouble())

    override fun evaluate(x: Int): Double = get(x.toDouble())

    override fun evaluate(x: Short): Double = get(x.toDouble())
}
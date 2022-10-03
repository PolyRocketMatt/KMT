package com.github.polyrocketmatt.kmt.function.type.polynomial

import com.github.polyrocketmatt.kmt.common.decimals
import com.github.polyrocketmatt.kmt.function.differentiation.ExactDifferentiation
import com.github.polyrocketmatt.kmt.function.variate.Univariate

class LegendrePolynomial(n: Int): Univariate<Double>(), ExactDifferentiation<Double> {

    val coefficients = DoubleArray(n + 1)
    private val function: PolynomialFunction

    init {
        //  Compute coefficients of the n-th legendre polynomial
        when (n) {
            0 -> coefficients[0] = 1.0
            1 -> coefficients[1] = 1.0
            else -> {
                //  Generate the coefficients of the polynomial using the recurrence relation, n-1 and n-2 polynomials
                val p1 = LegendrePolynomial(n - 1)
                val p2 = LegendrePolynomial(n - 2)
                val factor = 1.0 / n
                val p1factor = (2.0 * n - 1.0) * factor
                val p2factor = (n - 1.0) * factor

                for (i in 0 until n)
                    coefficients[i + 1] += p1factor * p1.coefficients[i]

                //  TODO: This is numerically unstable, find a better way to do this
                for (i in 0 until n - 1)
                    coefficients[i] -= p2factor * p2.coefficients[i]

                //  Round to 8 decimals max after computing the coefficients to reduce numerical errors
                for (i in 0 until n + 1)
                    coefficients[i] = coefficients[i].decimals(8)
            }
        }

        function = PolynomialFunction(*coefficients)
    }

    override fun get(x: Double): Double = function[x]

    override fun evaluate(x: Double): Double = get(x)

    override fun evaluate(x: Float): Double = evaluate(x.toDouble())

    override fun evaluate(x: Int): Double = evaluate(x.toDouble())

    override fun evaluate(x: Short): Double = evaluate(x.toDouble())

    override fun derivative(): Univariate<Double> = PolynomialFunction(*(coefficients.drop(1).toDoubleArray()))
}
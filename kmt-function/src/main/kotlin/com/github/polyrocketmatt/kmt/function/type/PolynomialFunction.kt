package com.github.polyrocketmatt.kmt.function.type

import com.github.polyrocketmatt.kmt.common.intPow
import com.github.polyrocketmatt.kmt.function.variate.Univariate

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a polynomial function.
 *
 * @param T The type of the output of the function.
 * @param coefficients The coefficients of the polynomial.
 */
abstract class PolynomialFunction<T>(internal vararg val coefficients: T) : Univariate<T>() {

    override fun evaluate(x: Float): T = evaluate(x.toDouble())

    override fun evaluate(x: Int): T = evaluate(x.toDouble())

    override fun evaluate(x: Short): T = evaluate(x.toDouble())

}

/**
 * Polynomial function that operates on doubles.
 *
 * @param coefficients The coefficients of the polynomial.
 */
class SimplePolynomial(vararg coefficients: Double) : PolynomialFunction<Double>(* coefficients.toTypedArray()) {

    override fun evaluate(x: Double): Double = coefficients.reduceIndexed { index, acc, c -> acc + c * x.intPow(index) }

}
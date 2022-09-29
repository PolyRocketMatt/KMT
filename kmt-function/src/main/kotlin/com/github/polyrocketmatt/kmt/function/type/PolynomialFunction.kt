package com.github.polyrocketmatt.kmt.function.type

import com.github.polyrocketmatt.kmt.function.variate.Univariate

class PolynomialFunction<T>(vararg coefficients: T) : Univariate<T>() {

    override fun evaluate(x: Double): T {
        TODO("Not yet implemented")
    }

    override fun evaluate(x: Float): T = evaluate(x.toDouble())

    override fun evaluate(x: Int): T = evaluate(x.toDouble())

    override fun evaluate(x: Short): T = evaluate(x.toDouble())

}
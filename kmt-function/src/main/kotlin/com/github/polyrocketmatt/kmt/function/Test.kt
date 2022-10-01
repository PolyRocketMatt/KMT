package com.github.polyrocketmatt.kmt.function

import com.github.polyrocketmatt.kmt.common.DataType
import com.github.polyrocketmatt.kmt.function.integration.GaussianQuadrature
import com.github.polyrocketmatt.kmt.function.variate.Univariate
import com.github.polyrocketmatt.kmt.interval.half.RightOpenDoubleInterval
import kotlin.math.exp

class Func : Univariate<Double>() {

    override fun evaluate(x: Double): Double = exp(-(x * x))

    override fun evaluate(x: Float): Double = evaluate(x.toDouble())

    override fun evaluate(x: Int): Double = evaluate(x.toDouble())

    override fun evaluate(x: Short): Double = evaluate(x.toDouble())

}

fun main() {
    val func = Func()
    val interval = RightOpenDoubleInterval(0.0, Double.POSITIVE_INFINITY, 0.0001)
    val quadrature = GaussianQuadrature.get<Double>(
        GaussianQuadrature.GaussianQuadratureRule.FIVE_POINT,
        GaussianQuadrature.WeightFunction.LAGUERRE,
        DataType.DOUBLE
    )

    val integrated = func.integrate(interval, quadrature)

    println(integrated.contentToString())
    println(integrated.sum())
}
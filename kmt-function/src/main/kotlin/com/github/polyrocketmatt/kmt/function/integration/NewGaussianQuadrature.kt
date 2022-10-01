package com.github.polyrocketmatt.kmt.function.integration

import com.github.polyrocketmatt.kmt.common.annotation.Ref
import com.github.polyrocketmatt.kmt.function.variate.Univariate
import com.github.polyrocketmatt.kmt.interval.Interval

@Ref("https://www.ams.org/journals/mcom/1969-23-106/S0025-5718-69-99647-1/S0025-5718-69-99647-1.pdf")
abstract class NewGaussianQuadrature<T>(private val n: Int) : Quadrature<T>

private class DoubleGaussianQuadrature(n: Int) : NewGaussianQuadrature<Double>(n) {

    override fun integrate(function: Univariate<Double>, interval: Interval<Double>): Array<Double> {
        TODO("Not yet implemented")
    }
}

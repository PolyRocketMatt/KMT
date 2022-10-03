package com.github.polyrocketmatt.kmt.function.type.polynomial

import com.github.polyrocketmatt.kmt.common.annotation.Ref
import com.github.polyrocketmatt.kmt.function.type.RecursiveFunction

@Ref("https://www.physics.uoguelph.ca/chapter-3-legendre-polynomials")
object LegendreRecursivePolynomial : RecursiveFunction<Double>(1) {

    override fun get(x: Double, n: Int): Double = when (n) {
        0 -> 1.0
        1 -> x
        else -> {
            val p1 = get(x, n - 1)
            val p2 = get(x, n - 2)
            (1.0 / n) * ((2 * n - 1) * x * p1 - (n - 1) * p2)
        }
    }
}
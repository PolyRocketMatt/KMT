package com.github.polyrocketmatt.kmt.function

import com.github.polyrocketmatt.kmt.common.decimals
import com.github.polyrocketmatt.kmt.function.integration.GaussianQuadrature
import com.github.polyrocketmatt.kmt.function.roots.bisection
import com.github.polyrocketmatt.kmt.function.roots.falsePosition
import com.github.polyrocketmatt.kmt.function.roots.newtonRhapson
import com.github.polyrocketmatt.kmt.function.type.polynomial.LegendrePolynomial
import com.github.polyrocketmatt.kmt.function.type.polynomial.PolynomialFunction
import com.github.polyrocketmatt.kmt.interval.closed.rangeTo

fun main() {
    /*
    val function = PolynomialFunction(-2.0, 4.9015, 0.0, 4.918, 17.183)

    println(function[0.0])
    println(function[1.0])
    try { println(function.bisection((-10.01891).rangeTo(5.2820), 100000000).decimals(3)) } catch (e: Exception) { println("Could not find root with Bisection") }
    try { println(function.falsePosition((-10.01891).rangeTo(5.2820), 10000).decimals(3)) } catch (e: Exception) { println("Could not find root with False Position") }
    try { println(function.newtonRhapson(-.5, 10000).decimals(3)) } catch (e: Exception) { println("Could not find root with Newton-Rhapson") }

     */

    val tuples = GaussianQuadrature.rootsAndWeights(5)

    tuples.forEach { println(it) }

}
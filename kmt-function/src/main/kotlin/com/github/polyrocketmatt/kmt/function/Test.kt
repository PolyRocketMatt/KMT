package com.github.polyrocketmatt.kmt.function

import com.github.polyrocketmatt.kmt.common.decimals
import com.github.polyrocketmatt.kmt.function.roots.bisection
import com.github.polyrocketmatt.kmt.function.roots.falsePosition
import com.github.polyrocketmatt.kmt.function.type.polynomial.PolynomialFunction
import com.github.polyrocketmatt.kmt.interval.closed.rangeTo

fun main() {
    val function = PolynomialFunction(-2.0, 4.9015, 0.0, 4.918)

    println(function[0.0])
    println(function[1.0])
    println(function.bisection((-10.01891).rangeTo(5.2820), 100000000).decimals(3))
    println(function.falsePosition((-10.01891).rangeTo(5.2820), 10000).decimals(3))
}
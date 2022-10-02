package com.github.polyrocketmatt.kmt.function.type.composite

import com.github.polyrocketmatt.kmt.function.Function
import kotlin.math.max

class CompositeFunction(private val f: Function<Double>, private val g: Function<Double>) : Function<Double>(max(f.arity, g.arity)) {

    override fun get(x: Double): Double = f[g[x]]

    override fun get(x: Double, y: Double): Double = f[g[x, y]]

    override fun get(vararg x: Double): Double = f[g.get(*x)]
}
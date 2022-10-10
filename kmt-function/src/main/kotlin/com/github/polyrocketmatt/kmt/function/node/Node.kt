package com.github.polyrocketmatt.kmt.function.node

abstract class Node : DifferentiableNode, IntegrableNode {

    abstract operator fun get(x: Double): Double

    abstract operator fun plus(other: Node): Node

    abstract operator fun minus(other: Node): Node

    abstract operator fun times(other: Node): Node

    abstract operator fun div(other: Node): Node

    abstract operator fun unaryMinus(): Node

    abstract fun pow(other: Node): Node

    abstract fun simplify(): Node

    abstract fun string(indent: Int = 0): String

    override fun toString(): String = string()

}
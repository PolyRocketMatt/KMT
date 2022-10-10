package com.github.polyrocketmatt.kmt.function.node

import org.jetbrains.kotlinx.multik.ndarray.operations.Arith
import kotlin.math.log

class LogarithmNode(internal val base: Node, internal val of: Node) : Node() {

    override fun get(x: Double): Double = log(of[x], base[x])

    override fun plus(other: Node): Node {
        TODO("Not yet implemented")
    }

    override fun minus(other: Node): Node {
        TODO("Not yet implemented")
    }

    override fun times(other: Node): Node {
        TODO("Not yet implemented")
    }

    override fun div(other: Node): Node {
        TODO("Not yet implemented")
    }

    override fun unaryMinus(): Node {
        TODO("Not yet implemented")
    }

    override fun pow(other: Node): Node {
        TODO("Not yet implemented")
    }

    override fun simplify(): Node = this

    override fun differentiate(): Node {
        return if (base is ConstantNode && base.value == Math.E)
            of.differentiate() / of
        else {
            //  Rewrite to natural log
            val nominator = LogarithmNode(ConstantNode(Math.E), of)
            val denominator = LogarithmNode(ConstantNode(Math.E), base)

            ArithmeticNode(nominator, denominator, ArithmeticNode.Operator.DIVIDE).differentiate()
        }
    }

    override fun integrate(): Node {
        TODO("Not yet implemented")
    }

    override fun string(indent: Int): String = "    ".repeat(indent) + "LogarithmNode()\n" +
            base.string(indent + 1) + "\n" +
            of.string(indent + 1)
}
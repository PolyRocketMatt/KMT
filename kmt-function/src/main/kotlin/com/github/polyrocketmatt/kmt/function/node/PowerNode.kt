package com.github.polyrocketmatt.kmt.function.node

import kotlin.math.pow

class PowerNode(internal val base: Node, internal val exponent: Node) : Node() {

    override fun get(x: Double): Double = base[x].pow(exponent[x])

    override fun plus(other: Node): Node = ArithmeticNode(this, other, ArithmeticNode.Operator.ADD)

    override fun minus(other: Node): Node = ArithmeticNode(this, other, ArithmeticNode.Operator.SUBTRACT)

    override fun times(other: Node): Node = ArithmeticNode(this, other, ArithmeticNode.Operator.MULTIPLY)

    override fun div(other: Node): Node = ArithmeticNode(this, other, ArithmeticNode.Operator.DIVIDE)

    override fun unaryMinus(): Node = NegateNode(this)

    override fun pow(other: Node): Node = PowerNode(this, other)

    override fun simplify(): Node = when {
        base is ConstantNode && base.value == 0.0 -> ConstantNode(0.0)
        base is ConstantNode && base.value == 1.0 -> ConstantNode(1.0)
        exponent is ConstantNode && exponent.value == 0.0 -> ConstantNode(1.0)
        exponent is ConstantNode && exponent.value == 1.0 -> base.simplify()
        else -> this
    }

    override fun differentiate(): Node = when (exponent) {
        //  Power rule
        is ConstantNode         -> ConstantNode(exponent.value) * PowerNode(base, ConstantNode(exponent.value - 1)).simplify()

        //  Logarithmic Differentiation
        else                    -> {
            //  Logarithm Function
            val function = ArithmeticNode(
                LogarithmNode(ConstantNode(Math.E), base),
                exponent,
                ArithmeticNode.Operator.MULTIPLY
            ).differentiate()

            ArithmeticNode(this, function, ArithmeticNode.Operator.MULTIPLY).simplify()
        }
    }

    override fun integrate(): Node {
        TODO("Not yet implemented")
    }

    override fun string(indent: Int): String = "    ".repeat(indent) + "PowerNode()\n" +
            base.string(indent + 1) + "\n" +
            exponent.string(indent + 1)

}
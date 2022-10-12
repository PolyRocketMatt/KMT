package com.github.polyrocketmatt.kmt.function.node

class ConstantNode(internal val value: Double) : Node() {

    override fun get(x: Double): Double = value

    override fun plus(other: Node): Node = when (other) {
        is ConstantNode -> ConstantNode(this.value + other.value)
        else                        -> ArithmeticNode(this, other, ArithmeticNode.Operator.ADD)
    }

    override fun minus(other: Node): Node = when (other) {
        is ConstantNode -> ConstantNode(this.value - other.value)
        else                        -> ArithmeticNode(this, other, ArithmeticNode.Operator.SUBTRACT)
    }

    override fun times(other: Node): Node = when (other) {
        is ConstantNode -> ConstantNode(this.value * other.value)
        else                        -> ArithmeticNode(this, other, ArithmeticNode.Operator.MULTIPLY)
    }

    override fun div(other: Node): Node = when (other) {
        is ConstantNode -> ConstantNode(this.value / other.value)
        else                        -> ArithmeticNode(this, other, ArithmeticNode.Operator.DIVIDE)
    }

    override fun unaryMinus(): Node = ConstantNode(-value)

    override fun pow(other: Node): Node = PowerNode(this, other)

    override fun simplify(): Node = this

    override fun differentiate(): Node = ConstantNode(0.0)

    override fun integrate(): Node = ArithmeticNode(ConstantNode(value), VariableNode(), ArithmeticNode.Operator.MULTIPLY)

    override fun string(indent: Int): String = "    ".repeat(indent) + "ConstantNode($value)"

}
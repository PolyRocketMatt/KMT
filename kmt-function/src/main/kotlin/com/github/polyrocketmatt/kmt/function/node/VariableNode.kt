package com.github.polyrocketmatt.kmt.function.node

class VariableNode : Node() {

    override fun get(x: Double): Double = x

    override fun plus(other: Node): Node = ArithmeticNode(this, other, ArithmeticNode.Operator.ADD)

    override fun minus(other: Node): Node = ArithmeticNode(this, other, ArithmeticNode.Operator.SUBTRACT)

    override fun times(other: Node): Node = ArithmeticNode(this, other, ArithmeticNode.Operator.MULTIPLY)

    override fun div(other: Node): Node = ArithmeticNode(this, other, ArithmeticNode.Operator.DIVIDE)

    override fun unaryMinus(): Node = NegateNode(this)

    override fun pow(other: Node): Node = PowerNode(this, other)

    override fun simplify(): Node = this

    override fun differentiate(): Node = ConstantNode(1.0)

    override fun integrate(): Node = ArithmeticNode(PowerNode(this, ConstantNode(2.0)), ConstantNode(2.0), ArithmeticNode.Operator.DIVIDE)

    override fun string(indent: Int): String = "    ".repeat(indent) + "VariableNode(x)"

}
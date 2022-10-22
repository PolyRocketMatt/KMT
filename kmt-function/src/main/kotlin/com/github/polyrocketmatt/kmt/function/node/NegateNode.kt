package com.github.polyrocketmatt.kmt.function.node

class NegateNode(internal val node: Node) : Node() {

    override fun get(x: Double): Double = -node[x]

    override fun plus(other: Node): Node = ArithmeticNode(this, other, ArithmeticNode.Operator.ADD)

    override fun minus(other: Node): Node = ArithmeticNode(this, other, ArithmeticNode.Operator.SUBTRACT)

    override fun times(other: Node): Node = ArithmeticNode(this, other, ArithmeticNode.Operator.MULTIPLY)

    override fun div(other: Node): Node = ArithmeticNode(this, other, ArithmeticNode.Operator.DIVIDE)

    override fun unaryMinus(): Node = NegateNode(this)

    override fun pow(other: Node): Node = PowerNode(this, other)

    override fun simplify(): Node = NegateNode(node.simplify())

    override fun differentiate(): Node = ArithmeticNode(ConstantNode(-1.0), node.differentiate(), ArithmeticNode.Operator.MULTIPLY)

    override fun integrate(): Node = ArithmeticNode(ConstantNode(-1.0), node.integrate(), ArithmeticNode.Operator.MULTIPLY)

    override fun string(indent: Int): String = "    ".repeat(indent) + "NegateNode()\n" +
        node.string(indent + 1)
}

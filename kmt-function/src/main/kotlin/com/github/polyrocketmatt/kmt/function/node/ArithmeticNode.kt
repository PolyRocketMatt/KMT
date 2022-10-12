package com.github.polyrocketmatt.kmt.function.node

class ArithmeticNode(internal val left: Node, internal val right: Node, internal val operator: Operator) : Node() {

    enum class Operator(private val operation: (Double, Double) -> Double) {
        ADD({ x, y -> x + y }),
        SUBTRACT({ x, y -> x - y }),
        MULTIPLY({ x, y -> x * y }),
        DIVIDE({ x, y -> x / y });

        operator fun get(x: Double, y: Double): Double = operation(x, y)
    }

    override fun get(x: Double): Double = operator[left[x], right[x]]

    override fun plus(other: Node): Node = ArithmeticNode(this, other, Operator.ADD)

    override fun minus(other: Node): Node = ArithmeticNode(this, other, Operator.SUBTRACT)

        override fun times(other: Node): Node = ArithmeticNode(this, other, Operator.MULTIPLY)

    override fun div(other: Node): Node = ArithmeticNode(this, other, Operator.DIVIDE)

    override fun unaryMinus(): Node = NegateNode(this)

    override fun pow(other: Node): Node = PowerNode(this, other)

    override fun simplify(): Node {
        val left = left.simplify()
        val right = right.simplify()
        return when {
            left is ConstantNode && right is ConstantNode -> ConstantNode(operator[left.value, right.value])
            left is ConstantNode && left.value == 0.0 -> when (operator) {
                Operator.ADD -> right
                Operator.SUBTRACT -> -right
                Operator.MULTIPLY -> ConstantNode(0.0)
                Operator.DIVIDE -> ConstantNode(0.0)
            }
            right is ConstantNode && right.value == 0.0 -> when (operator) {
                Operator.ADD -> left
                Operator.SUBTRACT -> left
                Operator.MULTIPLY -> ConstantNode(0.0)
                Operator.DIVIDE -> throw ArithmeticException("Division by zero")
            }
            left is ConstantNode && left.value == 1.0 -> when (operator) {
                Operator.MULTIPLY -> right
                else -> ArithmeticNode(left, right, operator)
            }
            right is ConstantNode && right.value == 1.0 -> when (operator) {
                Operator.MULTIPLY -> left
                Operator.DIVIDE -> left
                else -> ArithmeticNode(left, right, operator)
            }
            left is VariableNode && right is ArithmeticNode && right.right is VariableNode -> left
            right is VariableNode && left is ArithmeticNode && left.right is VariableNode -> right
            else -> ArithmeticNode(left, right, operator)
        }
    }

    override fun differentiate(): Node {
        val node = when (operator) {
            Operator.ADD -> ArithmeticNode(left.differentiate(), right.differentiate(), Operator.ADD)
            Operator.SUBTRACT -> ArithmeticNode(left.differentiate(), right.differentiate(), Operator.SUBTRACT)
            Operator.MULTIPLY -> {
                if (left is ConstantNode && right is ConstantNode)
                    return ConstantNode(left.value * right.value)
                else if (left is VariableNode && right is ConstantNode)
                    return right
                else if (left is ConstantNode && right is VariableNode)
                    return left
                else {
                    val leftDeriv =
                        if (right !is ConstantNode) ArithmeticNode(left, right.differentiate(), Operator.MULTIPLY) else null
                    val rightDeriv =
                        if (left !is ConstantNode) ArithmeticNode(left.differentiate(), right, Operator.MULTIPLY) else null

                    if (leftDeriv == null && rightDeriv == null)
                        ConstantNode(0.0)
                    else if (leftDeriv == null)
                        rightDeriv!!
                    else if (rightDeriv == null)
                        leftDeriv
                    else
                        ArithmeticNode(leftDeriv, rightDeriv, Operator.ADD)
                }
            }

            Operator.DIVIDE -> {
                if (left is ConstantNode && right is ConstantNode)
                    return ConstantNode(0.0)

                val leftDeriv = if (left !is ConstantNode) ArithmeticNode(left.differentiate(), right, Operator.MULTIPLY) else null
                val rightDeriv = if (right !is ConstantNode) ArithmeticNode(left, right.differentiate(), Operator.MULTIPLY) else null
                val nominator = if (leftDeriv == null && rightDeriv == null)
                    ConstantNode(0.0)
                else if (leftDeriv == null)
                    rightDeriv!!
                else if (rightDeriv == null)
                    leftDeriv
                else
                    ArithmeticNode(leftDeriv, rightDeriv, Operator.SUBTRACT)
                val denominator = PowerNode(right, ConstantNode(2.0))

                ArithmeticNode(nominator, denominator, Operator.DIVIDE)
            }
        }

        return node.simplify()
    }

    override fun integrate(): Node {
        return when (operator) {
            Operator.ADD -> ArithmeticNode(left.integrate(), right.integrate(), Operator.ADD)
            Operator.SUBTRACT -> ArithmeticNode(left.integrate(), right.integrate(), Operator.SUBTRACT)
            Operator.MULTIPLY -> {
                if (left is ConstantNode && right is ConstantNode)
                    ConstantNode(left.value * right.value).integrate()
                else if (left is VariableNode && right is ConstantNode)
                    ArithmeticNode(left.integrate(), right, Operator.MULTIPLY)
                else if (right is VariableNode && left is ConstantNode)
                    ArithmeticNode(left, right.integrate(), Operator.MULTIPLY)
                else {
                    //  Integration by parts
                    val leftDerivative = left.differentiate()
                    val rightIntegrand = right.integrate()
                    val left = ArithmeticNode(left, rightIntegrand, Operator.MULTIPLY)
                    val right = ArithmeticNode(leftDerivative, rightIntegrand, Operator.MULTIPLY).integrate()

                    return ArithmeticNode(left, right, Operator.SUBTRACT)
                }
            }
            Operator.DIVIDE -> {
                throw NotImplementedError("Division is not yet supported")
            }
        }
    }

    private fun isLinear(node: Node): Boolean {
        if (node !is ArithmeticNode)
            return false
        val left = node.left
        val right = node.right

        if (left is ConstantNode &&
                right is ArithmeticNode &&
                right.operator == Operator.MULTIPLY &&
                right.left is ConstantNode &&
                right.right is VariableNode)
            return true
        if (left is ConstantNode &&
                right is ArithmeticNode &&
                right.operator == Operator.MULTIPLY &&
                right.left is VariableNode &&
                right.right is ConstantNode)
            return true
        if (right is ConstantNode &&
                left is ArithmeticNode &&
                left.operator == Operator.MULTIPLY &&
                left.left is ConstantNode &&
                left.right is VariableNode)
            return true
        if (right is ConstantNode &&
                left is ArithmeticNode &&
                left.operator == Operator.MULTIPLY &&
                left.left is VariableNode &&
                left.right is ConstantNode)
            return true
        return false
    }

    override fun string(indent: Int): String = "    ".repeat(indent) + "ArithmeticNode($operator)\n" +
            left.string(indent + 1) + "\n" +
            right.string(indent + 1)

}
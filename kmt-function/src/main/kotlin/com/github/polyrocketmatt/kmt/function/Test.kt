package com.github.polyrocketmatt.kmt.function

import com.github.polyrocketmatt.kmt.function.node.ConstantNode
import com.github.polyrocketmatt.kmt.function.node.VariableNode
import com.github.polyrocketmatt.kmt.function.integration.Integrable
import com.github.polyrocketmatt.kmt.function.integration.quadratures.Quadrature
import com.github.polyrocketmatt.kmt.function.integration.quadratures.SimpsonQuadrature
import com.github.polyrocketmatt.kmt.function.node.ArithmeticNode
import com.github.polyrocketmatt.kmt.function.node.LogarithmNode
import com.github.polyrocketmatt.kmt.function.node.NegateNode
import com.github.polyrocketmatt.kmt.function.node.PowerNode
import com.github.polyrocketmatt.kmt.function.variate.Univariate
import com.github.polyrocketmatt.kmt.interval.Interval
import com.github.polyrocketmatt.kmt.interval.closed.ClosedDoubleInterval
import com.github.polyrocketmatt.kmt.trigonometry.SIN
import com.github.polyrocketmatt.kmt.trigonometry.Trigonometry
import com.sun.source.util.Plugin
import kotlin.math.log

class Func : Univariate<Double>(), Integrable<Double> {

    override fun get(x: Double): Double = x + (x * x)

    override fun accurate(): Function<Double> = this

    override fun evaluate(x: Double): Double = get(x)

    override fun evaluate(x: Float): Double = evaluate(x.toDouble())

    override fun evaluate(x: Int): Double = evaluate(x.toDouble())

    override fun evaluate(x: Short): Double = evaluate(x.toDouble())

    override fun integrate(interval: Interval<Double>, quadrature: Quadrature<Double>): DoubleArray = quadrature.integrate(interval, this)
}

fun main() {
    /*
    val simpleNode = ConstantNode(5.0)
    val varNode = VariableNode()
    val powNode = PowerNode(varNode, ConstantNode(2.0))
    val func = (varNode * simpleNode) + powNode
    val deriv = func.differentiate()
    val simple = deriv.simplify()

    println(func)
    println("-------------------------")
    println(deriv)
    println("-------------------------")
    println(simple)

     */


    /*
    val test1 = LogarithmNode(ArithmeticNode(ConstantNode(2.0), VariableNode(), ArithmeticNode.Operator.MULTIPLY), VariableNode())

    println(test1[5.0])
    println("-------------------------")
    println(test1.differentiate()[5.0])


    println("\n\n")

    val test2 = PowerNode(VariableNode(), ArithmeticNode(VariableNode(), ConstantNode(1.0), ArithmeticNode.Operator.ADD))

    println(test2[5.0])
    println("-------------------------")
    println(test2.differentiate()[5.0])


    println("\n\n")

    val test3 = NegateNode(ArithmeticNode(VariableNode(), ConstantNode(1.0), ArithmeticNode.Operator.ADD))

    println(test3[5.0])
    println("-------------------------")
    println(test3.differentiate()[5.0])


    println("\n\n")

    val left = ArithmeticNode(
        PowerNode(
            VariableNode(),
            ConstantNode(2.0)
        ),
        VariableNode(),
        ArithmeticNode.Operator.ADD
    )
    val right = ArithmeticNode(
        PowerNode(
            VariableNode(),
            ConstantNode(3.0)
        ),
        ConstantNode(1.0),
        ArithmeticNode.Operator.ADD
    )
    val test4 = ArithmeticNode(left, right, ArithmeticNode.Operator.MULTIPLY)

    println(test4[1.0])
    println("-------------------------")
    //println(test4.integrate()[1.0])

    println(test4.integrate())

     */


    val func = LogarithmNode(ArithmeticNode(ConstantNode(2.0), VariableNode(), ArithmeticNode.Operator.MULTIPLY), VariableNode())

    println(func.integrate())
}
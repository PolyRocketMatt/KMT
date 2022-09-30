package com.github.polyrocketmatt.kmt.function.differentiation

import com.github.polyrocketmatt.kmt.common.DataType
import com.github.polyrocketmatt.kmt.function.variate.Univariate
import com.github.polyrocketmatt.kmt.interval.closed.ClosedDoubleInterval
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ForwardDifferenceDifferentiatorTest {

    private class TestFunction : Univariate<Float>() {

        override fun evaluate(x: Double): Float = evaluate(x.toFloat())

        override fun evaluate(x: Float): Float = x * (x + 1)

        override fun evaluate(x: Int): Float = evaluate(x.toFloat())

        override fun evaluate(x: Short): Float = evaluate(x.toFloat())

    }

    private val function = TestFunction()
    private val interval = ClosedDoubleInterval(0.0, 4.0)
    private val intervalSubdivided = ClosedDoubleInterval(0.0, 4.0, 0.1)

    private fun testValueWithTolerance(expected: Double, actual: Double, tolerance: Double) = assertEquals(expected, actual, tolerance)

    @Test
    fun testForwardDivDifferenceDouble() {
        val derivatives = function.differentiate(interval, ForwardDifferenceDifferentiator.get(DataType.DOUBLE))

        assertEquals(1, derivatives.size)
        assertEquals(5.0, derivatives[0])
    }

    @Test
    fun testForwardDivDifferenceDoubleSubdivided() {
        val derivatives = function.differentiate(intervalSubdivided, ForwardDifferenceDifferentiator.get(DataType.DOUBLE))
        val expected = doubleArrayOf(
            1.400,
            2.199,
            3.000,
            3.799,
            4.600,
            5.400,
            6.199,
            7.000,
            7.799,
            8.600
        )

        assertEquals(10, derivatives.size)
        derivatives.forEachIndexed { i, actual -> testValueWithTolerance(expected[i], actual, 0.001) }
    }

    @Test
    fun testForwardDivDifferenceFloat() {
        val derivatives = function.differentiate(interval, ForwardDifferenceDifferentiator.get(DataType.FLOAT))

        assertEquals(1, derivatives.size)
        assertEquals(5.0, derivatives[0])
    }

    @Test
    fun testForwardDivDifferenceFloatSubdivided() {
        val derivatives = function.differentiate(intervalSubdivided, ForwardDifferenceDifferentiator.get(DataType.FLOAT))
        val expected = doubleArrayOf(
            1.400,
            2.199,
            3.000,
            3.799,
            4.600,
            5.400,
            6.199,
            7.000,
            7.799,
            8.600
        )

        assertEquals(10, derivatives.size)
        derivatives.forEachIndexed { i, actual -> testValueWithTolerance(expected[i], actual, 0.001) }
    }

    @Test
    fun testForwardDivDifferenceInt() {
        val derivatives = function.differentiate(interval, ForwardDifferenceDifferentiator.get(DataType.INT))

        assertEquals(1, derivatives.size)
        assertEquals(5.0, derivatives[0])
    }

    @Test
    fun testForwardDivDifferenceIntSubdivided() {
        val derivatives = function.differentiate(intervalSubdivided, ForwardDifferenceDifferentiator.get(DataType.INT))
        val expected = doubleArrayOf(
            0.000,
            2.500,
            2.500,
            5.000,
            5.000,
            5.000,
            5.000,
            7.500,
            7.500,
            10.000
        )

        assertEquals(10, derivatives.size)
        derivatives.forEachIndexed { i, actual -> testValueWithTolerance(expected[i], actual, 0.001) }
    }

    @Test
    fun testForwardDivDifferenceShort() {
        val derivatives = function.differentiate(interval, ForwardDifferenceDifferentiator.get(DataType.SHORT))

        assertEquals(1, derivatives.size)
        assertEquals(5.0, derivatives[0])
    }

    @Test
    fun testForwardDivDifferenceShortSubdivided() {
        val derivatives = function.differentiate(intervalSubdivided, ForwardDifferenceDifferentiator.get(DataType.SHORT))
        val expected = doubleArrayOf(
            0.000,
            2.500,
            2.500,
            5.000,
            5.000,
            5.000,
            5.000,
            7.500,
            7.500,
            10.000
        )

        assertEquals(10, derivatives.size)
        derivatives.forEachIndexed { i, actual -> testValueWithTolerance(expected[i], actual, 0.001) }
    }

}
package com.github.polyrocketmatt.kmt.function.variate

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BivariateTest {

    private class TestFunction : Bivariate<Float>() {

        override fun evaluate(x: Double, y: Double): Float = evaluate(x.toFloat(), y.toFloat())

        override fun evaluate(x: Float, y: Float): Float = x * (y + 1)

        override fun evaluate(x: Int, y: Int): Float = evaluate(x.toFloat(), y.toFloat())

        override fun evaluate(x: Short, y: Short): Float = evaluate(x.toFloat(), y.toFloat())
    }

    @Test
    fun testBivariate() {
        assertEquals(6.0f, TestFunction().evaluate(2.0, 2.0))
        assertEquals(6.0f, TestFunction().evaluate(2.0f, 2.0f))
        assertEquals(6.0f, TestFunction().evaluate(2, 2))
        assertEquals(6.0f, TestFunction().evaluate(2.toShort(), 2.toShort()))
    }
}

package com.github.polyrocketmatt.kmt.function.variate

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnivariateTest {

    private class TestFunction : Univariate<Float>() {

        override fun evaluate(x: Double): Float = evaluate(x.toFloat())

        override fun evaluate(x: Float): Float = x * (x + 1)

        override fun evaluate(x: Int): Float = evaluate(x.toFloat())

        override fun evaluate(x: Short): Float = evaluate(x.toFloat())
    }

    @Test
    fun testUnivariate() {
        assertEquals(6.0f, TestFunction().evaluate(2.0))
        assertEquals(6.0f, TestFunction().evaluate(2.0f))
        assertEquals(6.0f, TestFunction().evaluate(2))
        assertEquals(6.0f, TestFunction().evaluate(2.toShort()))
    }
}

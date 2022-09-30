package com.github.polyrocketmatt.kmt.function.variate

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals

class MultivariateTest {

    private class TestFunction : Multivariate<Float>(4) {

        override fun evaluate(vararg x: Double): Float = evaluate(*(x.map { it.toFloat() }.toFloatArray()))

        override fun evaluate(vararg x: Float): Float {
            if (x.size != 4)
                throw IllegalArgumentException("Invalid number of arguments. Expected 4, got ${x.size}")
            val a = x[0]
            val b = x[1]
            val c = x[2]
            val d = x[3]

            return a * b * c * d
        }

        override fun evaluate(vararg x: Int): Float = evaluate(*(x.map { it.toFloat() }.toFloatArray()))

        override fun evaluate(vararg x: Short): Float = evaluate(*(x.map { it.toFloat() }.toFloatArray()))
    }

    @Test
    fun testMultivariate() {
        assertEquals(24.0f, TestFunction().evaluate(1.0, 2.0, 3.0, 4.0))
        assertEquals(24.0f, TestFunction().evaluate(1.0f, 2.0f, 3.0f, 4.0f))
        assertEquals(24.0f, TestFunction().evaluate(1, 2, 3, 4))
        assertEquals(24.0f, TestFunction().evaluate(1.toShort(), 2.toShort(), 3.toShort(), 4.toShort()))
    }

    @Test
    fun testMultivariateInvalidArity() {
        assertThrows<IllegalArgumentException> { TestFunction().evaluate(1.0, 2.0, 3.0) }
        assertThrows<IllegalArgumentException> { TestFunction().evaluate(1.0f, 2.0f, 3.0f) }
        assertThrows<IllegalArgumentException> { TestFunction().evaluate(1, 2, 3) }
        assertThrows<IllegalArgumentException> { TestFunction().evaluate(1.toShort(), 2.toShort(), 3.toShort()) }
    }
}

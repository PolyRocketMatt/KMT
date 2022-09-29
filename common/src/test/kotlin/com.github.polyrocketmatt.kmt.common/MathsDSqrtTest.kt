package com.github.polyrocketmatt.kmt.common

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class MathsDSqrtTest {

    /**
     * Test the [Int.dsqrt] function.
     */

    @Test
    fun testSqrtInt() {
        assertEquals(0.0, 0.dsqrt())
        assertEquals(5.0, 25.dsqrt())
        assertThrows<IllegalArgumentException> { (-25).dsqrt() }
    }

    /**
     * Test the [Short.dsqrt] function.
     */

    @Test
    fun testSqrtShort() {
        assertEquals(0.0, 0.toShort().dsqrt())
        assertEquals(5.0, 25.toShort().dsqrt())
        assertThrows<IllegalArgumentException> { (-25).toShort().dsqrt() }
    }

    /**
     * Test the [Float.dsqrt] function.
     */

    @Test
    fun testSqrtFloat() {
        assertEquals(0.0, 0.0f.dsqrt())
        assertEquals(5.0, 25.0f.dsqrt())
        assertThrows<IllegalArgumentException> { (-25.0f).dsqrt() }
    }

    /**
     * Test the [Double.dsqrt] function.
     */

    @Test
    fun testSqrtDouble() {
        assertEquals(0.0, 0.0.dsqrt())
        assertEquals(5.0, 25.0.dsqrt())
        assertThrows<IllegalArgumentException> { (-25.0).dsqrt() }
    }

}
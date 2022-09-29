package com.github.polyrocketmatt.kmt.common

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class MathsDSqrtTest {

    /**
     * Test the [Int.dsqrt] function.
     */

    @Test
    fun testSqrtIntZero() {
        assertEquals(0.0, 0.dsqrt())
    }

    @Test
    fun testSqrtInt() {
        assertEquals(5.0, 25.dsqrt())
    }

    @Test
    fun testSqrtIntNegative() {
        assertThrows<IllegalArgumentException> { (-25).dsqrt() }
    }

    /**
     * Test the [Short.dsqrt] function.
     */

    @Test
    fun testSqrtShortZero() {
        assertEquals(0.0, 0.toShort().dsqrt())
    }

    @Test
    fun testSqrtShort() {
        assertEquals(5.0, 25.toShort().dsqrt())
    }

    @Test
    fun testSqrtShortNegative() {
        assertThrows<IllegalArgumentException> { (-25).toShort().dsqrt() }
    }

    /**
     * Test the [Float.dsqrt] function.
     */

    @Test
    fun testSqrtFloatZero() {
        assertEquals(0.0, 0.0f.dsqrt())
    }

    @Test
    fun testSqrtFloat() {
        assertEquals(5.0, 25.0f.dsqrt())
    }

    @Test
    fun testSqrtFloatNegative() {
        assertThrows<IllegalArgumentException> { (-25.0f).dsqrt() }
    }

    /**
     * Test the [Double.dsqrt] function.
     */

    @Test
    fun testSqrtDoubleZero() {
        assertEquals(0.0, 0.0.dsqrt())
    }

    @Test
    fun testSqrtDouble() {
        assertEquals(5.0, 25.0.dsqrt())
    }

    @Test
    fun testSqrtDoubleNegative() {
        assertThrows<IllegalArgumentException> { (-25.0).dsqrt() }
    }

}
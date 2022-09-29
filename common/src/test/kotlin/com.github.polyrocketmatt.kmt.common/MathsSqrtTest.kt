package com.github.polyrocketmatt.kmt.common

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class MathsSqrtTest {

    /**
     * Test the [Int.sqrt] function.
     */

    @Test
    fun testSqrtIntZero() {
        assertEquals(0.0f, 0.sqrt())
    }

    @Test
    fun testSqrtInt() {
        assertEquals(5.0f, 25.sqrt())
    }

    @Test
    fun testSqrtIntNegative() {
        assertThrows<IllegalArgumentException> { (-25).sqrt() }
    }

    /**
     * Test the [Short.sqrt] function.
     */

    @Test
    fun testSqrtShortZero() {
        assertEquals(0.0f, 0.toShort().sqrt())
    }

    @Test
    fun testSqrtShort() {
        assertEquals(5.0f, 25.toShort().sqrt())
    }

    @Test
    fun testSqrtShortNegative() {
        assertThrows<IllegalArgumentException> { (-25).toShort().sqrt() }
    }

    /**
     * Test the [Float.sqrt] function.
     */

    @Test
    fun testSqrtFloatZero() {
        assertEquals(0.0f, 0.0f.sqrt())
    }

    @Test
    fun testSqrtFloat() {
        assertEquals(5.0f, 25.0f.sqrt())
    }

    @Test
    fun testSqrtFloatNegative() {
        assertThrows<IllegalArgumentException> { (-25.0f).sqrt() }
    }

    /**
     * Test the [Double.sqrt] function.
     */

    @Test
    fun testSqrtDoubleZero() {
        assertEquals(0.0f, 0.0.sqrt())
    }

    @Test
    fun testSqrtDouble() {
        assertEquals(5.0f, 25.0.sqrt())
    }

    @Test
    fun testSqrtDoubleNegative() {
        assertThrows<IllegalArgumentException> { (-25.0).sqrt() }
    }

}
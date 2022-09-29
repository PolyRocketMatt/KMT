package com.github.polyrocketmatt.kmt.common

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class MathsSqrtTest {

    /**
     * Test the [Int.sqrt] function.
     */

    @Test
    fun testSqrtInt() {
        assertEquals(0.0f, 0.sqrt())
        assertEquals(5.0f, 25.sqrt())
        assertThrows<IllegalArgumentException> { (-25).sqrt() }
    }

    /**
     * Test the [Short.sqrt] function.
     */

    @Test
    fun testSqrtShort() {
        assertEquals(0.0f, 0.toShort().sqrt())
        assertEquals(5.0f, 25.toShort().sqrt())
        assertThrows<IllegalArgumentException> { (-25).toShort().sqrt() }
    }

    /**
     * Test the [Float.sqrt] function.
     */

    @Test
    fun testSqrtFloat() {
        assertEquals(0.0f, 0.0f.sqrt())
        assertEquals(5.0f, 25.0f.sqrt())
        assertThrows<IllegalArgumentException> { (-25.0f).sqrt() }
    }

    /**
     * Test the [Double.sqrt] function.
     */

    @Test
    fun testSqrtDouble() {
        assertEquals(0.0f, 0.0.sqrt())
        assertEquals(5.0f, 25.0.sqrt())
        assertThrows<IllegalArgumentException> { (-25.0).sqrt() }
    }

}
package com.github.polyrocketmatt.kmt.common

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MathsIsInTest {

    /**
     * Test the [Int.isIn] function.
     */

    @Test
    fun testIsInIntMinMax() {
        val value = 0

        assertEquals(true, value.isIn(0, 0))
    }

    @Test
    fun testIsInIntMin() {
        val value = 0

        assertEquals(true, value.isIn(0, 1))
    }

    @Test
    fun testIsInIntMax() {
        val value = 0

        assertEquals(true, value.isIn(-1, 0))
    }

    @Test
    fun testIsInInt() {
        val value = 0

        assertEquals(true, value.isIn(-1, 1))
    }

    @Test
    fun testIsInIntFalse() {
        val value = 0

        assertEquals(false, value.isIn(1, 2))
    }

    /**
     * Test the [Short.isIn] function.
     */

    @Test
    fun testIsInShortMinMax() {
        val value = 0.toShort()

        assertEquals(true, value.isIn(0, 0))
    }

    @Test
    fun testIsInShortMin() {
        val value = 0.toShort()

        assertEquals(true, value.isIn(0, 1))
    }

    @Test
    fun testIsInShortMax() {
        val value = 0.toShort()

        assertEquals(true, value.isIn(-1, 0))
    }

    @Test
    fun testIsInShort() {
        val value = 0.toShort()

        assertEquals(true, value.isIn(-1, 1))
    }

    @Test
    fun testIsInShortFalse() {
        val value = 0.toShort()

        assertEquals(false, value.isIn(1, 2))
    }

    /**
     * Test the [Float.isIn] function.
     */

    @Test
    fun testIsInFloatMinMax() {
        val value = 0.0f

        assertEquals(true, value.isIn(0.0f, 0.0f))
    }

    @Test
    fun testIsInFloatMin() {
        val value = 0.0f

        assertEquals(true, value.isIn(0.0f, 1.0f))
    }

    @Test
    fun testIsInFloatMax() {
        val value = 0.0f

        assertEquals(true, value.isIn(-1.0f, 0.0f))
    }

    @Test
    fun testIsInFloat() {
        val value = 0.0f

        assertEquals(true, value.isIn(-1.0f, 1.0f))
    }

    @Test
    fun testIsInFloatFalse() {
        val value = 0.0f

        assertEquals(false, value.isIn(1.0f, 2.0f))
    }

    /**
     * Test the [Double.isIn] function.
     */

    @Test
    fun testIsInDoubleMinMax() {
        val value = 0.0

        assertEquals(true, value.isIn(0.0, 0.0))
    }

    @Test
    fun testIsInDoubleMin() {
        val value = 0.0

        assertEquals(true, value.isIn(0.0, 1.0))
    }

    @Test
    fun testIsInDoubleMax() {
        val value = 0.0

        assertEquals(true, value.isIn(-1.0, 0.0))
    }

    @Test
    fun testIsInDouble() {
        val value = 0.0

        assertEquals(true, value.isIn(-1.0, 1.0))
    }

    @Test
    fun testIsInDoubleFalse() {
        val value = 0.0

        assertEquals(false, value.isIn(1.0, 2.0))
    }
}
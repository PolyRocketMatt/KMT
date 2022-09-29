package com.github.polyrocketmatt.kmt.common

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MathsIsInTest {

    /**
     * Test the [Int.isIn] function.
     */

    @Test
    fun testIsInInt() {
        assertEquals(true, 0.isIn(0, 0))
        assertEquals(true, 0.isIn(0, 1))
        assertEquals(true, 0.isIn(-1, 0))
        assertEquals(true, 0.isIn(-1, 1))
        assertEquals(false, 0.isIn(1, 2))
    }

    /**
     * Test the [Int.isIn] function.
     */

    @Test
    fun testIsInShort() {
        assertEquals(true, 0.toShort().isIn(0, 0))
        assertEquals(true, 0.toShort().isIn(0, 1))
        assertEquals(true, 0.toShort().isIn(-1, 0))
        assertEquals(true, 0.toShort().isIn(-1, 1))
        assertEquals(false, 0.toShort().isIn(1, 2))
    }

    /**
     * Test the [Int.isIn] function.
     */

    @Test
    fun testIsInFloat() {
        assertEquals(true, 0.0f.isIn(0.0f, 0.0f))
        assertEquals(true, 0.0f.isIn(0.0f, 1.0f))
        assertEquals(true, 0.0f.isIn(-1.0f, 0.0f))
        assertEquals(true, 0.0f.isIn(-1.0f, 1.0f))
        assertEquals(false, 0.0f.isIn(1.0f, 2.0f))
    }

    /**
     * Test the [Int.isIn] function.
     */

    @Test
    fun testIsInDouble() {
        assertEquals(true, 0.0.isIn(0.0, 0.0))
        assertEquals(true, 0.0.isIn(0.0, 1.0))
        assertEquals(true, 0.0.isIn(-1.0, 0.0))
        assertEquals(true, 0.0.isIn(-1.0, 1.0))
        assertEquals(false, 0.0.isIn(1.0, 2.0))
    }
}
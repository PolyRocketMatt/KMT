package com.github.polyrocketmatt.kmt.common

import kotlin.test.Test
import kotlin.test.assertEquals

class MathsIntpowTest {

    /**
     * Test the [Int.intPow] function.
     */

    @Test
    fun testIntIntPowZeroBoth() {
        assertEquals(1, 0.intPow(0))
    }

    @Test
    fun testIntIntPowZeroExponent() {
        assertEquals(1, 2.intPow(0))
    }

    @Test
    fun testIntIntPowZeroBase() {
        assertEquals(0, 0.intPow(2))
    }

    @Test
    fun testIntIntPow() {
        assertEquals(8, 2.intPow(3))
    }

    /**
     * Test the [Short.intPow] function.
     */

    @Test
    fun testShortIntPowZeroBoth() {
        assertEquals(1.toShort(), 0.toShort().intPow(0))
    }

    @Test
    fun testShortIntPowZeroExponent() {
        assertEquals(1.toShort(), 2.toShort().intPow(0))
    }

    @Test
    fun testShortIntPowZeroBase() {
        assertEquals(0.toShort(), 0.toShort().intPow(2))
    }

    @Test
    fun testShortIntPow() {
        assertEquals(8.toShort(), 2.toShort().intPow(3))
    }

    /**
     * Test the [Float.intPow] function.
     */

    @Test
    fun testFloatIntPowZeroBoth() {
        assertEquals(1.0f, 0.0f.intPow(0))
    }

    @Test
    fun testFloatIntPowZeroExponent() {
        assertEquals(1.0f, 2.0f.intPow(0))
    }

    @Test
    fun testFloatIntPowZeroBase() {
        assertEquals(0.0f, 0.0f.intPow(2))
    }

    @Test
    fun testFloatIntPow() {
        assertEquals(8.0f, 2.0f.intPow(3))
    }

    /**
     * Test the [Double.intPow] function.
     */

    @Test
    fun testDoubleIntPowZeroBoth() {
        assertEquals(1.0, 0.0.intPow(0))
    }

    @Test
    fun testDoubleIntPowZeroExponent() {
        assertEquals(1.0, 2.0.intPow(0))
    }

    @Test
    fun testDoubleIntPowZeroBase() {
        assertEquals(0.0, 0.0.intPow(2))
    }

    @Test
    fun testDoubleIntPow() {
        assertEquals(8.0, 2.0.intPow(3))
    }

}
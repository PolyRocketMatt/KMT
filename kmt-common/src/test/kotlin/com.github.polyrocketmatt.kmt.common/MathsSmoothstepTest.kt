package com.github.polyrocketmatt.kmt.common

import kotlin.test.Test
import kotlin.test.assertEquals

class MathsSmoothstepTest {

    /**
     * Test the [Float.smoothStep] function.
     */

    @Test
    fun testSmoothStepFloat() {
        assertEquals(0.0f, 0.0f.smoothStep())
        assertEquals(1.0f, 1.0f.smoothStep())
        assertEquals(0.104f, 0.2f.smoothStep(), 0.001f)
        assertEquals(0.5f, 0.5f.smoothStep())
    }

    /**
     * Test the [Double.smoothStep] function.
     */

    @Test
    fun testSmoothStepDouble() {
        assertEquals(0.0, 0.0.smoothStep())
        assertEquals(1.0, 1.0.smoothStep())
        assertEquals(0.104, 0.2.smoothStep(), 0.001)
        assertEquals(0.5, 0.5.smoothStep())
    }
}

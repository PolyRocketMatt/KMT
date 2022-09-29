package com.github.polyrocketmatt.kmt.common

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MathsSmootherstepTest {

    /**
     * Test the [Float.smootherStep] function.
     */

    @Test
    fun testSmoothStepFloat() {
        assertEquals(0.0f, 0.0f.smootherStep())
        assertEquals(1.0f, 1.0f.smootherStep())
        assertEquals(0.0579f, 0.2f.smootherStep(), 0.001f)
        assertEquals(0.5f, 0.5f.smootherStep())
    }

    /**
     * Test the [Double.smootherStep] function.
     */

    @Test
    fun testSmoothStepDouble() {
        assertEquals(0.0, 0.0.smootherStep())
        assertEquals(1.0, 1.0.smootherStep())
        assertEquals(0.0579, 0.2.smootherStep(), 0.001)
        assertEquals(0.5, 0.5.smootherStep())
    }
}

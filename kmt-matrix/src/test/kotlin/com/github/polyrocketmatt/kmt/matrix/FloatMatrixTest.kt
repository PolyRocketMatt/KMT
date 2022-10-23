package com.github.polyrocketmatt.kmt.matrix

import org.junit.jupiter.api.Test

class FloatMatrixTest {

    @Test
    fun testMatrixDimension() {
        val a = Float2x2(floatArrayOf(1f, 0f, 0f, 1f))
        val b = FloatNxN(2, intArrayOf(2, 3), floatArrayOf(
            1f, 2f, 3f,
            3f, 2f, 1f
        ))
        val result = a mult b

        println("YAY")
    }

}
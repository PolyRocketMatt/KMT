package com.github.polyrocketmatt.kmt.matrix

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FloatMatrixTest {

    @Test
    fun testArrayToMatrix() {
        val singular = floatArrayOf(1f)

        assertEquals(
            FloatMatrix(1, intArrayOf(1), floatArrayOf(1f)),
            singular.toMatrix(intArrayOf(1))
        )

        val size = 12
        val array = FloatArray(size) { it.toFloat() }
        val matrix = array.toMatrix(intArrayOf(2, 2, 3))

        assertEquals(
            FloatMatrix(3, intArrayOf(2, 2, 3), floatArrayOf(
                0f, 1f, 2f, 3f,
                4f, 5f, 6f, 7f,
                8f, 9f, 10f, 11f
            )), matrix
        )
    }

}
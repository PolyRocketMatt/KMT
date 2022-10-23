package com.github.polyrocketmatt.kmt.matrix

import org.junit.jupiter.api.Test

class DoubleMatrixTest {

    /*
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

    @Test
    fun testIllegalArrayToMatrix() {
        val size = 11
        val array = FloatArray(size) { it.toFloat() }

        assertThrows<IllegalArgumentException> { array.toMatrix(intArrayOf(2, 2, 3)) }
    }

    @Test
    fun testMatrixToArray() {
        val matrix = FloatMatrix(2, intArrayOf(2, 3), floatArrayOf(
            0f, 1f, 2f,
            3f, 4f, 5f
        ))
        val array = matrix.toArray()

        assertContentEquals(floatArrayOf(0f, 1f, 2f, 3f, 4f, 5f), array)
    }

    @Test
    fun testIdentityMatrix() {
        val identity = FloatMatrix.identity(intArrayOf(2, 2))
        val expected = FloatMatrix(2, intArrayOf(2, 2), floatArrayOf(
            1f, 0f,
            0f, 1f
        ))

        assertEquals(expected, identity)
    }

    @Test
    fun testIdentityMatrixNonSquare() {
        val identityA = FloatMatrix.identity(intArrayOf(2, 3))
        val expectedA = FloatMatrix(2, intArrayOf(2, 3), floatArrayOf(
            1f, 0f, 0f,
            0f, 1f, 0f
        ))

        val identityB = FloatMatrix.identity(intArrayOf(4, 2))
        val expectedB = FloatMatrix(2, intArrayOf(4, 2), floatArrayOf(
            1f, 0f,
            0f, 1f,
            0f, 0f,
            0f, 0f
        ))

        assertEquals(expectedA, identityA)
        assertEquals(expectedB, identityB)
    }

    @Test
    fun testInvalidDimensionIdentity() {
        assertThrows<IllegalArgumentException> { FloatMatrix.identity(intArrayOf(2, 2, 2)) }
        assertThrows<IllegalArgumentException> { FloatMatrix.identity(intArrayOf(2)) }
    }

     */

    @Test
    fun testRowEchelonForm() {
        val matrix = DoubleMatrix(intArrayOf(2, 3), doubleArrayOf(
            1.0, 2.0, 3.0,
            4.0, 5.0, 6.0
        )).ref()

        val expected = DoubleMatrix(intArrayOf(2, 3), doubleArrayOf(
            1.0, 0.0, -1.0,
            0.0, -1.0, -2.0
        ))
    }

    @Test
    fun testRowReducedEchelonForm() {
        val matrix = DoubleMatrix(intArrayOf(3, 3), doubleArrayOf(
            1.0, 2.0, 3.0,
            4.0, 5.0, 6.0,
            7.0, 8.0, 9.0
        )).rref()

        val expected = DoubleMatrix(intArrayOf(2, 3), doubleArrayOf(
            1.0, 0.0, -1.0,
            0.0, -1.0, -2.0
        ))
    }

    @Test
    fun testRowReducedEchelonFormSparse() {
        val matrix = DoubleMatrix(intArrayOf(4, 5), doubleArrayOf(
            0.02, 0.01, 0.0, 0.0, 0.02,
            1.0, 2.0, 1.0, 0.0, 1.0,
            0.0, 1.0, 2.0, 1.0, 4.0,
            0.0, 0.0, 100.0, 200.0, 800.0
        )).rref()


        val expected = DoubleMatrix(intArrayOf(4, 5), doubleArrayOf(
            1.0, 0.0, 0.0, 0.0, 1.0,
            0.0, 1.0, 0.0, 0.0, 0.0,
            0.0, 0.0, 1.0, 0.0, 0.0,
            0.0, 0.0, 0.0, 1.0, 4.0
        ))

    }

    @Test
    fun superTest() {
        val t = Float2x2(floatArrayOf(1f, 2f, 3f, 4f))
        val t2 = 2.0f
        val sum = t + t2

        println(sum)
    }

}
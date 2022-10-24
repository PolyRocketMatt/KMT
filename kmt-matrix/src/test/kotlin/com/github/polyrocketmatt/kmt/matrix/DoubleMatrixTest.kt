package com.github.polyrocketmatt.kmt.matrix

import org.junit.jupiter.api.Test

class DoubleMatrixTest {

    @Test
    fun testRowEchelonForm() {
        val matrix = DoubleMatrix(
            intArrayOf(2, 3),
            doubleArrayOf(
                1.0, 2.0, 3.0,
                4.0, 5.0, 6.0
            )
        ).ref()

        val expected = DoubleMatrix(
            intArrayOf(2, 3),
            doubleArrayOf(
                1.0, 0.0, -1.0,
                0.0, -1.0, -2.0
            )
        )
    }

    @Test
    fun testRowReducedEchelonForm() {
        val matrix = DoubleMatrix(
            intArrayOf(3, 3),
            doubleArrayOf(
                1.0, 2.0, 3.0,
                4.0, 5.0, 6.0,
                7.0, 8.0, 9.0
            )
        ).rref()

        val expected = DoubleMatrix(
            intArrayOf(2, 3),
            doubleArrayOf(
                1.0, 0.0, -1.0,
                0.0, -1.0, -2.0
            )
        )
    }

    @Test
    fun testRowReducedEchelonFormSparse() {
        val matrix = DoubleMatrix(
            intArrayOf(4, 5),
            doubleArrayOf(
                0.02, 0.01, 0.0, 0.0, 0.02,
                1.0, 2.0, 1.0, 0.0, 1.0,
                0.0, 1.0, 2.0, 1.0, 4.0,
                0.0, 0.0, 100.0, 200.0, 800.0
            )
        ).rref()

        val expected = DoubleMatrix(
            intArrayOf(4, 5),
            doubleArrayOf(
                1.0, 0.0, 0.0, 0.0, 1.0,
                0.0, 1.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 1.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 1.0, 4.0
            )
        )
    }

    @Test
    fun refTest() {
        val matrix = DoubleMatrix(
            intArrayOf(3, 3),
            doubleArrayOf(
                1.0, 2.0, 3.0,
                4.0, 5.0, 6.0,
                7.0, 2.0, 9.0
            )
        ).inverse()

        println(matrix)
    }
}

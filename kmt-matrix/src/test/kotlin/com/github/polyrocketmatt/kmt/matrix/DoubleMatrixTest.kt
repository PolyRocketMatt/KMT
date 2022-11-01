package com.github.polyrocketmatt.kmt.matrix

import com.github.polyrocketmatt.kmt.common.storage.Tuple
import com.github.polyrocketmatt.kmt.common.storage.tupleOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals

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
    fun inverseTest() {
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

    @Test
    fun testSingleSolution() {
        val matrix = doubleMatrixOf(
            intArrayOf(2, 3),
            3.0, -7.0, -2.0,
            -3.0, 5.0, 1.0,
        )

        assertEquals(tupleOf(0.5, 0.5), matrix.solve())
    }

    @Test
    fun testNoSolution() {
        val matrix = doubleMatrixOf(
            intArrayOf(3, 3),
            3.0, -7.0, -2.0,
            -3.0, 5.0, 1.0,
            -1.0, 10.0, 2.0
        )

        assertThrows<IllegalArgumentException> { matrix.solve() }
    }

    @Test
    fun testInfinitelyManySolution() {
        val matrix = doubleMatrixOf(
            intArrayOf(2, 3),
            2.0, 3.0, 5.0,
            6.0, 9.0, 15.0,
        )

        assertThrows<IllegalArgumentException> { matrix.solve() }
    }

    @Test
    fun testQRDecomposition() {
        val shape = intArrayOf(3, 3)
        val matrix = doubleMatrixOf(
            shape,
            1.0, 1.0, 0.0,
            1.0, 0.0, 1.0,
            0.0, 1.0, 1.0
        )

        val qr = matrix.qrDecomposition(QRFactorizationMethod.GRAM_SCHMIDT)
        val q = doubleMatrixOf(
            shape,
            0.707106781187, 0.408248290463, -0.57735026919,
            0.707106781187, -0.408248290464, 0.577350269189,
            0.0, 0.816496580927, 0.577350269191
        )
        val r = doubleMatrixOf(
            shape,
            1.414213562373, 0.707106781187, 0.707106781187,
            0.0, 1.224744871392, 0.408248290463,
            0.0, 0.0, 1.154700538379
        )

        for (i in 0 until shape[0])
            for (j in 0 until shape[1]) {
                assertEquals(q[i, j], qr.first[i, j], 1e-8)
                assertEquals(r[i, j], qr.second[i, j], 1e-8)
            }
    }

    @Test
    fun testLUDecomposition() {
        val matrix = doubleMatrixOf(
            intArrayOf(4, 2),
            1.0, 2.0,
            3.0, 4.0,
            2.0, 5.0,
            4.0, 6.0
        )

        val lu = matrix.luDecomposition()
        val l = doubleMatrixOf(
            intArrayOf(4, 4),
            1.0, 0.0, 0.0, 0.0,
            3.0, 1.0, 0.0, 0.0,
            2.0, -0.5, 1.0, 0.0,
            4.0, 1.0, 0.0, 1.0
        )
        val u = doubleMatrixOf(
            intArrayOf(4, 2),
            1.0, 2.0,
            0.0, -2.0,
            0.0, 0.0,
            0.0, 0.0
        )

        for (i in 0 until l.shape[0])
            for (j in 0 until l.shape[1])
                assertEquals(l[i, j], lu.first[i, j], 1e-8)
        for (i in 0 until u.shape[0])
            for (j in 0 until u.shape[1])
                assertEquals(u[i, j], lu.second[i, j], 1e-8)
    }

}

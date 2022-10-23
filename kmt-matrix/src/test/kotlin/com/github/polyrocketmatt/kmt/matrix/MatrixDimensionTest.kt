package com.github.polyrocketmatt.kmt.matrix

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class MatrixDimensionTest {

    @Test
    fun testDimensions() {
        val dimensionA = Dimension(1)
        val dimensionB = Dimension(2)

        assertFalse(dimensionA == dimensionB)
    }

}
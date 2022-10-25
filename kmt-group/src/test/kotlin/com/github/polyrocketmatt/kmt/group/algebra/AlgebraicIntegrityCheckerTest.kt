package com.github.polyrocketmatt.kmt.group.algebra

import com.github.polyrocketmatt.kmt.common.intPow
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AlgebraicIntegrityCheckerTest {

    private val function = { a: Int, b: Int -> a.intPow(b) * a }
    private val addition = { a: Int, b: Int -> a + b }
    private val subtraction = { a: Int, b: Int -> a - b }
    private val multiplication = { a: Int, b: Int -> a * b }

    @Test
    fun testIsCommutative() {
        assertTrue { isCommutative(1, 2, addition) }
        assertTrue { isCommutative(1, 2, multiplication) }
        assertFalse { isCommutative(1, 2, subtraction) }
        assertFalse { isCommutative(1, 2, function) }
    }

    @Test
    fun testIsAssociative() {
        assertTrue { isAssociative(1, 2, 3, addition) }
        assertTrue { isAssociative(1, 2, 3, multiplication) }
        assertTrue { isAssociative(1, 2, 3, function) }
        assertFalse { isAssociative(1, 2, 3, subtraction) }
    }

    @Test
    fun testIsIdentity() {
        assertTrue { isIdentity(1, 0, addition) }
        assertFalse { isIdentity(2, 1, function) }
    }

    @Test
    fun testIsInverse() {
        assertTrue { isInverse(1, -1, 0, addition) }
        assertFalse { isInverse(1, -1, 0, subtraction) }
        assertFalse { isInverse(1, -1, 1, multiplication) }
    }

}
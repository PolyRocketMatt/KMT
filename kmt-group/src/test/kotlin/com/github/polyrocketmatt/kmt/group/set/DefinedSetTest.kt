package com.github.polyrocketmatt.kmt.group.set

import com.github.polyrocketmatt.kmt.common.dsqrt
import com.github.polyrocketmatt.kmt.common.sqrt
import com.github.polyrocketmatt.kmt.complex.Complex
import com.github.polyrocketmatt.kmt.group.set.DefinedSet.Companion.COMPLEX
import com.github.polyrocketmatt.kmt.group.set.DefinedSet.Companion.INTEGERS
import com.github.polyrocketmatt.kmt.group.set.DefinedSet.Companion.NATURAL
import com.github.polyrocketmatt.kmt.group.set.DefinedSet.Companion.REAL_DOUBLES
import com.github.polyrocketmatt.kmt.group.set.DefinedSet.Companion.REAL_FLOATS
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.math.PI
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class DefinedSetTest {

    private val set = DefinedSet<Int>({ it > 5 }, isEmpty = false, isSingleton = false)

    @Test
    fun testIsEmpty() {
        assertFalse { set.isEmpty() }
    }

    @Test
    fun testIsSingleton() {
        assertFalse { set.isSingleton() }
    }

    @Test
    fun testContains() {
        assertFalse { set.contains(5) }
        assertTrue { set.contains(6) }
    }

    @Test
    fun testIsSubset() {
        val subsetA = SimpleSet(1, 2, 6, 7)
        val subsetB = SimpleSet(6, 7)

        assertFalse { set.isSubset(subsetA) }
        assertTrue { set.isSubset(subsetB) }
    }

    @Test
    fun testMapIfContains() {
        val map = { it: Int -> it * 2 }

        assertEquals(12, set.mapIfContains(6, map))
        assertNull(set.mapIfContains(5, map))
    }

    @Test
    fun testCardinality() {
        assertThrows<UnsupportedOperationException> { set.card() }
    }

    @Test
    fun testMap() {
        assertThrows<UnsupportedOperationException> { set.map { it: Int -> it * 2 } }
    }

    @Test
    fun testPredefinedSets() {
        //  Natural numbers
        assertTrue { NATURAL.contains(1) }
        assertTrue { NATURAL.contains(0) }
        assertFalse { NATURAL.contains(-1) }

        //  Integers
        assertTrue { INTEGERS.contains(1) }
        assertTrue { INTEGERS.contains(0) }
        assertTrue { INTEGERS.contains(-1) }

        //  Real Floats
        assertTrue { REAL_FLOATS.contains(1.0f) }
        assertTrue { REAL_FLOATS.contains(0.0f) }
        assertTrue { REAL_FLOATS.contains(-1.0f) }
        assertTrue { REAL_FLOATS.contains(2.0f.sqrt()) }
        assertTrue { REAL_FLOATS.contains(PI.toFloat()) }

        //  Real Floats
        assertTrue { REAL_DOUBLES.contains(1.0) }
        assertTrue { REAL_DOUBLES.contains(0.0) }
        assertTrue { REAL_DOUBLES.contains(-1.0) }
        assertTrue { REAL_DOUBLES.contains(2.0f.dsqrt()) }
        assertTrue { REAL_DOUBLES.contains(PI) }

        //  Complex
        assertTrue { COMPLEX.contains(Complex(0.0)) }
        assertTrue { COMPLEX.contains(Complex(0.0, -1.0)) }
        assertTrue { COMPLEX.contains(Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)) }
    }

}
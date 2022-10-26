package com.github.polyrocketmatt.kmt.group.algebra

import com.github.polyrocketmatt.kmt.group.set.SimpleSet
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Tests the [Ring] algebra by using an additive and multiplicative modulus 7 group.
 */
class RingTest {

    private val identity = 0
    private val elements = listOf(0, 1, 2, 3, 4, 5, 6)
    private val set = SimpleSet(elements)
    private val modAdd = { a: Int, b: Int -> (a + b) % 7 }
    private val modMult = { a: Int, b: Int -> (a * b) % 7 }
    private val inverseMap = { i: Int -> inverse(i) }
    private val add = { a: Int, b: Int -> a + b }

    private fun inverse(i: Int): Int {
        for (x in elements)
            if (modAdd(i, x) == identity)
                return x
        return 0
    }

    @Test
    fun testRingConstructors() {
        val ringA = Ring(identity, inverseMap, modAdd, modMult)
        val ringB = Ring(identity, inverseMap, modAdd, modMult, elements)
        val ringC = Ring(identity, inverseMap, modAdd, modMult, *elements.toTypedArray())
        val ringD = Ring(identity, inverseMap, modAdd, modMult, set)

        assertTrue { ringA.isEmpty() }
        assertTrue { ringB == ringC && ringC == ringD }
    }

    @Test
    fun testRingIntegrity() {
        val ringAddMultMod = Ring(identity, inverseMap, modAdd, modMult, elements)
        val ringAdd = Ring(identity, inverseMap, add, add, elements)

        assertDoesNotThrow { ringAddMultMod.checkIntegrity() }
        assertThrows<IllegalArgumentException> { ringAdd.checkIntegrity() }
    }

    @Test
    fun testRingOperation() {
        val ring = Ring(identity, inverseMap, modAdd, modMult, elements)

        assertEquals(2, ring.add(6, 3))
        assertEquals(2, ring[6, 3])
        assertEquals(1, ring.multiply(2, 4))
    }

    @Test
    fun testRingIdentity() {
        val ring = Ring(identity, inverseMap, modAdd, modMult, elements)

        assertEquals(identity, ring.identity())
    }

    @Test
    fun testRingInverse() {
        val ring = Ring(identity, inverseMap, modAdd, modMult, elements)

        assertEquals(4, ring.inverse(3))
    }
}
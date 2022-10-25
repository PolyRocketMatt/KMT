package com.github.polyrocketmatt.kmt.group.algebra

import com.github.polyrocketmatt.kmt.group.set.SimpleSet
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Tests the [Monoid] algebra by using a modulus 10 group.
 */
class MonoidTest {

    private val identity = 0
    private val elements = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    private val set = SimpleSet(elements)
    private val modAdd = { a: Int, b: Int -> (a + b) % 10 }
    private val add = { a: Int, b: Int -> a + b }

    @Test
    fun testMonoidConstructors() {
        val monoidA = Monoid(identity, modAdd)
        val monoidB = Monoid(identity, modAdd, elements)
        val monoidC = Monoid(identity, modAdd, *elements.toTypedArray())
        val monoidD = Monoid(identity, modAdd, set)

        assertTrue { monoidA.isEmpty() }
        assertTrue { monoidB == monoidC && monoidC == monoidD }
    }

    @Test
    fun testMonoidIntegrity() {
        val monoidMod = Monoid(identity, modAdd, elements)
        val monoidAdd = Monoid(identity, add, elements)

        assertDoesNotThrow { monoidMod.checkIntegrity() }
        assertThrows<IllegalArgumentException> { monoidAdd.checkIntegrity() }
    }

    @Test
    fun testMonoidOperation() {
        val monoid = Monoid(identity, modAdd, elements)

        assertTrue { monoid[1, 2] == 3 }
        assertTrue { monoid[1, 9] == 0 }
        assertTrue { monoid[9, 9] == 8 }
        assertFalse { monoid[1, 2] == 4 }
    }

    @Test
    fun testMonoidIdentity() {
        val monoid = Monoid(identity, modAdd, elements)

        assertTrue { monoid.identity() == identity }
    }
}

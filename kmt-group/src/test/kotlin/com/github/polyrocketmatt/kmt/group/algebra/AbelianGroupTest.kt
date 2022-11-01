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
 * Tests the [AbelianGroup] algebra by using a modulus 7 group.
 */
class AbelianGroupTest {

    private val identity = 1
    private val elements = listOf(1, 2, 3, 4, 5, 6)
    private val set = SimpleSet(elements)
    private val modMult = { a: Int, b: Int -> (a * b) % 7 }
    private val inverseMap = { i: Int -> inverse(i) }
    private val add = { a: Int, b: Int -> a + b }

    private fun inverse(i: Int): Int {
        for (x in elements)
            if (modMult(i, x) == identity)
                return x
        return 0
    }

    @Test
    fun testGroupConstructors() {
        val groupA = AbelianGroup(identity, inverseMap, modMult)
        val groupB = AbelianGroup(identity, inverseMap, modMult, elements)
        val groupC = AbelianGroup(identity, inverseMap, modMult, *elements.toTypedArray())
        val groupD = AbelianGroup(identity, inverseMap, modMult, set)

        assertTrue { groupA.isEmpty() }
        assertTrue { groupB == groupC && groupC == groupD }
    }

    @Test
    fun testGroupIntegrity() {
        val groupMod = AbelianGroup(identity, inverseMap, modMult, elements)
        val groupAdd = AbelianGroup(identity, inverseMap, add, elements)

        assertDoesNotThrow { groupMod.checkIntegrity() }
        assertThrows<IllegalArgumentException> { groupAdd.checkIntegrity() }
    }

    @Test
    fun testGroupOperation() {
        val group = AbelianGroup(identity, inverseMap, modMult, elements)

        assertEquals(2, group[1, 2])
        assertEquals(5, group[2, 6])
        assertEquals(4, group[2, 2])
    }

    @Test
    fun testGroupIdentity() {
        val group = AbelianGroup(identity, inverseMap, modMult, elements)

        assertEquals(identity, group.identity())
    }

    @Test
    fun testGroupInverse() {
        val group = AbelianGroup(identity, inverseMap, modMult, elements)

        assertEquals(5, group.inverse(3))
    }
}

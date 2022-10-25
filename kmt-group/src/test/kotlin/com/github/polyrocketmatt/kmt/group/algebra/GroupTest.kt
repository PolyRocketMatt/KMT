package com.github.polyrocketmatt.kmt.group.algebra

import com.github.polyrocketmatt.kmt.group.set.SimpleSet
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Tests the [Group] algebra by using a modulus 7 group.
 */
class GroupTest {

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
        val groupA = Group(identity, inverseMap, modMult)
        val groupB = Group(identity, inverseMap, modMult, elements)
        val groupC = Group(identity, inverseMap, modMult, *elements.toTypedArray())
        val groupD = Group(identity, inverseMap, modMult, set)

        assertTrue { groupA.isEmpty() }
        assertTrue { groupB == groupC && groupC == groupD }
    }

    @Test
    fun testGroupIntegrity() {
        val groupMod = Group(identity, inverseMap, modMult, elements)
        val groupAdd = Group(identity, inverseMap, add, elements)

        assertDoesNotThrow { groupMod.checkIntegrity() }
        assertThrows<IllegalArgumentException> { groupAdd.checkIntegrity() }
    }

    @Test
    fun testGroupOperation() {
        val group = Group(identity, inverseMap, modMult, elements)

        assertTrue { group[1, 2] == 2 }
        assertTrue { group[2, 6] == 5 }
        assertFalse { group[1, 2] == 4 }
    }

    @Test
    fun testGroupIdentity() {
        val group = Group(identity, inverseMap, modMult, elements)

        assertTrue { group.identity() == identity }
    }

    @Test
    fun testGroupInverse() {
        val group = Group(identity, inverseMap, modMult, elements)

        assertTrue { group.inverse(3) == 5 }
    }
}

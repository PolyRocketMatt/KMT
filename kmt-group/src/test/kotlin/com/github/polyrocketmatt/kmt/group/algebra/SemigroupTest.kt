package com.github.polyrocketmatt.kmt.group.algebra

import com.github.polyrocketmatt.kmt.group.set.SimpleSet
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Tests the [Semigroup] algebra by using a modulus 10 group.
 */
class SemigroupTest {

    private val elements = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    private val set = SimpleSet(elements)
    private val modAdd = { a: Int, b: Int -> (a + b) % 10 }
    private val add = { a: Int, b: Int -> a + b }

    @Test
    fun testSemigroupConstructors() {
        val sgroupA = Semigroup(modAdd)
        val sgroupB = Semigroup(modAdd, elements)
        val sgroupC = Semigroup(modAdd, *elements.toTypedArray())
        val sgroupD = Semigroup(modAdd, set)

        assertTrue { sgroupA.isEmpty() }
        assertTrue { sgroupB == sgroupC && sgroupC == sgroupD }
    }

    @Test
    fun testSemigroupIntegrity() {
        val sgroupMod = Semigroup(modAdd, elements)
        val sgroupAdd = Semigroup(add, elements)

        assertDoesNotThrow { sgroupMod.checkIntegrity() }
        assertThrows<IllegalArgumentException> { sgroupAdd.checkIntegrity() }
    }

    @Test
    fun testSemigroupOperation() {
        val sgroup = Semigroup(modAdd, elements)

        assertTrue { sgroup[1, 2] == 3 }
        assertTrue { sgroup[1, 9] == 0 }
        assertTrue { sgroup[9, 9] == 8 }
        assertFalse { sgroup[1, 2] == 4 }
    }
}

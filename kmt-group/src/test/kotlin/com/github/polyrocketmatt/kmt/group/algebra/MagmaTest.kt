package com.github.polyrocketmatt.kmt.group.algebra

import com.github.polyrocketmatt.kmt.group.set.SimpleSet
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MagmaTest {

    private val elements = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    private val set = SimpleSet(elements)
    private val modAdd = { a: Int, b: Int -> (a + b) % 10 }
    private val add = { a: Int, b: Int -> a + b }

    @Test
    fun testMagmaConstructors() {
        val magmaA = Magma(modAdd)
        val magmaB = Magma(modAdd, elements)
        val magmaC = Magma(modAdd, *elements.toTypedArray())
        val magmaD = Magma(modAdd, set)

        assertTrue { magmaA.isEmpty() }
        assertTrue { magmaB == magmaC && magmaC == magmaD }
    }

    @Test
    fun testMagmaIntegrity() {
        val magmaMod = Magma(modAdd, elements)
        val magmaAdd = Magma(add, elements)

        assertDoesNotThrow { magmaMod.checkIntegrity() }
        assertThrows<IllegalArgumentException> { magmaAdd.checkIntegrity() }
    }

    @Test
    fun testMagmaOperation() {
        val magma = Magma(modAdd, elements)

        assertTrue { magma[1, 2] == 3 }
        assertTrue { magma[1, 9] == 0 }
        assertTrue { magma[9, 9] == 8 }
        assertFalse { magma[1, 2] == 4 }
    }

}
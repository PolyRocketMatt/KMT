package com.github.polyrocketmatt.kmt.group.algebra

import com.github.polyrocketmatt.kmt.group.set.SimpleSet
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Tests the [Field] algebra by using an additive and multiplicative modulus 7 group.
 */
class FieldTest {

    private val additiveIdentity = 0
    private val multiplicativeIdentity = 1
    private val elements = listOf(0, 1, 2, 3, 4, 5, 6)
    private val set = SimpleSet(elements)
    private val modAdd = { a: Int, b: Int -> (a + b) % 7 }
    private val modMult = { a: Int, b: Int -> (a * b) % 7 }
    private val additiveInverseMap = { i: Int -> additiveInverse(i) }
    private val multiplicativeInverseMap = { i: Int -> multiplicativeInverse(i) }
    private val add = { a: Int, b: Int -> a + b }

    private fun additiveInverse(i: Int): Int {
        for (x in elements)
            if (modAdd(i, x) == additiveIdentity)
                return x
        return 0
    }

    private fun multiplicativeInverse(i: Int): Int {
        for (x in (elements - additiveIdentity))
            if (modMult(i, x) == multiplicativeIdentity)
                return x
        return 0
    }

    @Test
    fun testFieldConstructors() {
        val fieldA = Field(additiveIdentity, multiplicativeIdentity, additiveInverseMap, multiplicativeInverseMap, modAdd, modMult)
        val fieldB = Field(additiveIdentity, multiplicativeIdentity, additiveInverseMap, multiplicativeInverseMap, modAdd, modMult, elements)
        val fieldC = Field(additiveIdentity, multiplicativeIdentity, additiveInverseMap, multiplicativeInverseMap, modAdd, modMult, *elements.toTypedArray())
        val fieldD = Field(additiveIdentity, multiplicativeIdentity, additiveInverseMap, multiplicativeInverseMap, modAdd, modMult, set)

        assertTrue { fieldA.isEmpty() }
        assertTrue { fieldB == fieldC && fieldC == fieldD }
    }

    @Test
    fun testFieldIntegrity() {
        val fieldAddMultMod = Field(additiveIdentity, multiplicativeIdentity, additiveInverseMap, multiplicativeInverseMap, modAdd, modMult, elements)
        val fieldAdd = Field(additiveIdentity, multiplicativeIdentity, additiveInverseMap, multiplicativeInverseMap, add, add, elements)

        assertDoesNotThrow { fieldAddMultMod.checkIntegrity() }
        assertThrows<IllegalArgumentException> { fieldAdd.checkIntegrity() }
    }

    @Test
    fun testFieldOperation() {
        val field = Field(additiveIdentity, multiplicativeIdentity, additiveInverseMap, multiplicativeInverseMap, modAdd, modMult, elements)

        assertEquals(2, field.add(6, 3))
        assertEquals(2, field[6, 3])
        assertEquals(1, field.multiply(2, 4))
    }

    @Test
    fun testFieldIdentities() {
        val field = Field(additiveIdentity, multiplicativeIdentity, additiveInverseMap, multiplicativeInverseMap, modAdd, modMult, elements)

        assertEquals(additiveIdentity, field.additiveIdentity())
        assertEquals(multiplicativeIdentity, field.multiplicativeIdentity())
    }

    @Test
    fun testRingInverse() {
        val field = Field(additiveIdentity, multiplicativeIdentity, additiveInverseMap, multiplicativeInverseMap, modAdd, modMult, elements)

        assertEquals(4, field.additiveInverse(3))
        assertEquals(3, field.multiplicativeInverse(5))
    }

}
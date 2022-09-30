package com.github.polyrocketmatt.kmt.function.type

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SimplePolynomialTest {

    private val polynomial = SimplePolynomial(2.0, 1.5, 0.5)

    @Test
    fun testSimplePolynomialEvaluationDouble() {
        assertEquals(2.0, polynomial.evaluate(0.0))
        assertEquals(4.0, polynomial.evaluate(1.0))
        assertEquals(7.0, polynomial.evaluate(2.0))
    }

    @Test
    fun testSimplePolynomialEvaluationGetDouble() {
        assertEquals(2.0, polynomial[0.0])
        assertEquals(4.0, polynomial[1.0])
        assertEquals(7.0, polynomial[2.0])
    }

    @Test
    fun testSimplePolynomialEvaluationFloat() {
        assertEquals(2.0, polynomial.evaluate(0.0f))
        assertEquals(4.0, polynomial.evaluate(1.0f))
        assertEquals(7.0, polynomial.evaluate(2.0f))
    }

    @Test
    fun testSimplePolynomialEvaluationGetFloat() {
        assertEquals(2.0, polynomial[0.0f])
        assertEquals(4.0, polynomial[1.0f])
        assertEquals(7.0, polynomial[2.0f])
    }

    @Test
    fun testSimplePolynomialEvaluationInt() {
        assertEquals(2.0, polynomial.evaluate(0))
        assertEquals(4.0, polynomial.evaluate(1))
        assertEquals(7.0, polynomial.evaluate(2))
    }

    @Test
    fun testSimplePolynomialEvaluationGetInt() {
        assertEquals(2.0, polynomial[0])
        assertEquals(4.0, polynomial[1])
        assertEquals(7.0, polynomial[2])
    }

    @Test
    fun testSimplePolynomialEvaluationShort() {
        assertEquals(2.0, polynomial.evaluate(0.toShort()))
        assertEquals(4.0, polynomial.evaluate(1.toShort()))
        assertEquals(7.0, polynomial.evaluate(2.toShort()))
    }

    @Test
    fun testSimplePolynomialEvaluationGetShort() {
        assertEquals(2.0, polynomial[0.toShort()])
        assertEquals(4.0, polynomial[1.toShort()])
        assertEquals(7.0, polynomial[2.toShort()])
    }
}

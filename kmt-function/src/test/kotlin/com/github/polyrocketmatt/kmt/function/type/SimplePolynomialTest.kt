package com.github.polyrocketmatt.kmt.function.type

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SimplePolynomialTest {

    private val polynomial = SimplePolynomial(2.0, 1.5, 0.5)

    @Test
    fun testSimplePolynomialEvaluation() {
        assertEquals(2.0, polynomial.evaluate(0.0))
        assertEquals(4.0, polynomial.evaluate(1.0))
        assertEquals(7.0, polynomial.evaluate(2.0))
    }

    @Test
    fun testSimplePolynomialEvaluationGet() {
        assertEquals(2.0, polynomial[0.0])
        assertEquals(4.0, polynomial[1.0])
        assertEquals(7.0, polynomial[2.0])
    }
}

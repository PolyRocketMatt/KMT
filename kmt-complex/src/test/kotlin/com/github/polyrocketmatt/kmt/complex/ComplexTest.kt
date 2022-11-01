/*
 * KMT, Kotlin Math Toolkit
 * Copyright (C) Matthias Kovacic <matthias.kovacic@student.kuleuven.be>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.polyrocketmatt.kmt.complex

import org.junit.jupiter.api.Test
import kotlin.math.PI
import kotlin.math.sqrt
import kotlin.test.assertEquals

class ComplexTest {

    @Test
    fun testPrimaryConstructor() {
        val a = Complex(1.0, -2.0)

        assertEquals(1.0, a.real())
        assertEquals(-2.0, a.imaginary())
    }

    @Test
    fun testSecondaryConstructor() {
        val a = Complex(1.0)

        assertEquals(1.0, a.real())
        assertEquals(0.0, a.imaginary())
    }

    @Test
    fun testMagnitude() {
        val a = Complex(1.0, -2.0)
        val magnitude = a.magnitude()

        assertEquals(sqrt(5.0), magnitude)
    }

    @Test
    fun testTheta() {
        val a = Complex(2.0, 2.0)
        val theta = a.theta()

        assertEquals(PI / 4.0, theta, 1e-10)
    }

    @Test
    fun testMagnitudeSq() {
        val a = Complex(1.0, -2.0)
        val magnitudeSq = a.magnitudeSq()

        assertEquals(5.0, magnitudeSq)
    }

    @Test
    fun testConjugate() {
        val a = Complex(1.0, -2.0)
        val conjugate = a.conjugate()

        assertEquals(1.0, conjugate.real())
        assertEquals(2.0, conjugate.imaginary())
    }

    @Test
    fun testReciprocal() {
        val a = Complex(1.0, -2.0)
        val reciprocal = a.reciprocal()

        assertEquals(1.0 / 5.0, reciprocal.real())
        assertEquals(2.0 / 5.0, reciprocal.imaginary())
    }

    @Test
    fun testAddition() {
        val a = Complex(1.0, -2.0)
        val b = Complex(2.0, 3.0)
        val c = a + b

        assertEquals(3.0, c.real())
        assertEquals(1.0, c.imaginary())
    }

    @Test
    fun testSubtraction() {
        val a = Complex(1.0, -2.0)
        val b = Complex(2.0, 3.0)
        val c = a - b

        assertEquals(-1.0, c.real())
        assertEquals(-5.0, c.imaginary())
    }

    @Test
    fun testMultiplication() {
        val a = Complex(1.0, -2.0)
        val b = Complex(2.0, 3.0)
        val c = a * b

        assertEquals(8.0, c.real())
        assertEquals(-1.0, c.imaginary())
    }

    @Test
    fun testDivision() {
        val a = Complex(1.0, -2.0)
        val b = Complex(2.0, 3.0)
        val c = a / b

        assertEquals(-0.3076923076923077, c.real())
        assertEquals(-0.07692307692307693, c.imaginary())
    }
}

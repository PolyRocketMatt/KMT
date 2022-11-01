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

package com.github.polyrocketmatt.kmt.common

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class MathsDSqrtTest {

    /**
     * Test the [Int.dsqrt] function.
     */

    @Test
    fun testSqrtInt() {
        assertEquals(0.0, 0.dsqrt())
        assertEquals(5.0, 25.dsqrt())
        assertThrows<IllegalArgumentException> { (-25).dsqrt() }
    }

    /**
     * Test the [Short.dsqrt] function.
     */

    @Test
    fun testSqrtShort() {
        assertEquals(0.0, 0.toShort().dsqrt())
        assertEquals(5.0, 25.toShort().dsqrt())
        assertThrows<IllegalArgumentException> { (-25).toShort().dsqrt() }
    }

    /**
     * Test the [Float.dsqrt] function.
     */

    @Test
    fun testSqrtFloat() {
        assertEquals(0.0, 0.0f.dsqrt())
        assertEquals(5.0, 25.0f.dsqrt())
        assertThrows<IllegalArgumentException> { (-25.0f).dsqrt() }
    }

    /**
     * Test the [Double.dsqrt] function.
     */

    @Test
    fun testSqrtDouble() {
        assertEquals(0.0, 0.0.dsqrt())
        assertEquals(5.0, 25.0.dsqrt())
        assertThrows<IllegalArgumentException> { (-25.0).dsqrt() }
    }
}

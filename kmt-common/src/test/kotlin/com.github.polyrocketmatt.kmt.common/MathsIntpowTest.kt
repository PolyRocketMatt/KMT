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

import kotlin.test.Test
import kotlin.test.assertEquals

class MathsIntpowTest {

    /**
     * Test the [Int.intPow] function.
     */

    @Test
    fun testIntIntPow() {
        assertEquals(1, 0.intPow(0))
        assertEquals(1, 2.intPow(0))
        assertEquals(0, 0.intPow(2))
        assertEquals(8, 2.intPow(3))
    }

    /**
     * Test the [Short.intPow] function.
     */

    @Test
    fun testShortIntPow() {
        assertEquals(1.toShort(), 0.toShort().intPow(0))
        assertEquals(1.toShort(), 2.toShort().intPow(0))
        assertEquals(0.toShort(), 0.toShort().intPow(2))
        assertEquals(8.toShort(), 2.toShort().intPow(3))
    }

    /**
     * Test the [Float.intPow] function.
     */

    @Test
    fun testFloatIntPow() {
        assertEquals(1.0f, 0.0f.intPow(0))
        assertEquals(1.0f, 2.0f.intPow(0))
        assertEquals(0.0f, 0.0f.intPow(2))
        assertEquals(8.0f, 2.0f.intPow(3))
    }

    /**
     * Test the [Double.intPow] function.
     */

    @Test
    fun testDoubleIntPow() {
        assertEquals(1.0, 0.0.intPow(0))
        assertEquals(1.0, 2.0.intPow(0))
        assertEquals(0.0, 0.0.intPow(2))
        assertEquals(8.0, 2.0.intPow(3))
    }
}

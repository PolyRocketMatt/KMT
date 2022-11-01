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
import kotlin.test.assertEquals

class MathsIsInTest {

    /**
     * Test the [Int.isIn] function.
     */

    @Test
    fun testIsInInt() {
        assertEquals(true, 0.isIn(0, 0))
        assertEquals(true, 0.isIn(0, 1))
        assertEquals(true, 0.isIn(-1, 0))
        assertEquals(true, 0.isIn(-1, 1))
        assertEquals(false, 0.isIn(1, 2))
    }

    /**
     * Test the [Int.isIn] function.
     */

    @Test
    fun testIsInShort() {
        assertEquals(true, 0.toShort().isIn(0, 0))
        assertEquals(true, 0.toShort().isIn(0, 1))
        assertEquals(true, 0.toShort().isIn(-1, 0))
        assertEquals(true, 0.toShort().isIn(-1, 1))
        assertEquals(false, 0.toShort().isIn(1, 2))
    }

    /**
     * Test the [Int.isIn] function.
     */

    @Test
    fun testIsInFloat() {
        assertEquals(true, 0.0f.isIn(0.0f, 0.0f))
        assertEquals(true, 0.0f.isIn(0.0f, 1.0f))
        assertEquals(true, 0.0f.isIn(-1.0f, 0.0f))
        assertEquals(true, 0.0f.isIn(-1.0f, 1.0f))
        assertEquals(false, 0.0f.isIn(1.0f, 2.0f))
    }

    /**
     * Test the [Int.isIn] function.
     */

    @Test
    fun testIsInDouble() {
        assertEquals(true, 0.0.isIn(0.0, 0.0))
        assertEquals(true, 0.0.isIn(0.0, 1.0))
        assertEquals(true, 0.0.isIn(-1.0, 0.0))
        assertEquals(true, 0.0.isIn(-1.0, 1.0))
        assertEquals(false, 0.0.isIn(1.0, 2.0))
    }
}

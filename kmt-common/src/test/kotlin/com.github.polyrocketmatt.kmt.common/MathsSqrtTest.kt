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

class MathsSqrtTest {

    /**
     * Test the [Int.sqrt] function.
     */

    @Test
    fun testSqrtInt() {
        assertEquals(0.0f, 0.sqrt())
        assertEquals(5.0f, 25.sqrt())
        assertThrows<IllegalArgumentException> { (-25).sqrt() }
    }

    /**
     * Test the [Short.sqrt] function.
     */

    @Test
    fun testSqrtShort() {
        assertEquals(0.0f, 0.toShort().sqrt())
        assertEquals(5.0f, 25.toShort().sqrt())
        assertThrows<IllegalArgumentException> { (-25).toShort().sqrt() }
    }

    /**
     * Test the [Float.sqrt] function.
     */

    @Test
    fun testSqrtFloat() {
        assertEquals(0.0f, 0.0f.sqrt())
        assertEquals(5.0f, 25.0f.sqrt())
        assertThrows<IllegalArgumentException> { (-25.0f).sqrt() }
    }

    /**
     * Test the [Double.sqrt] function.
     */

    @Test
    fun testSqrtDouble() {
        assertEquals(0.0f, 0.0.sqrt())
        assertEquals(5.0f, 25.0.sqrt())
        assertThrows<IllegalArgumentException> { (-25.0).sqrt() }
    }
}

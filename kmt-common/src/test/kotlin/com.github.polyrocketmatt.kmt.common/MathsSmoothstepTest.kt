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

class MathsSmoothstepTest {

    /**
     * Test the [Float.smoothStep] function.
     */

    @Test
    fun testSmoothStepFloat() {
        assertEquals(0.0f, 0.0f.smoothStep())
        assertEquals(1.0f, 1.0f.smoothStep())
        assertEquals(0.104f, 0.2f.smoothStep(), 0.001f)
        assertEquals(0.5f, 0.5f.smoothStep())
    }

    /**
     * Test the [Double.smoothStep] function.
     */

    @Test
    fun testSmoothStepDouble() {
        assertEquals(0.0, 0.0.smoothStep())
        assertEquals(1.0, 1.0.smoothStep())
        assertEquals(0.104, 0.2.smoothStep(), 0.001)
        assertEquals(0.5, 0.5.smoothStep())
    }
}

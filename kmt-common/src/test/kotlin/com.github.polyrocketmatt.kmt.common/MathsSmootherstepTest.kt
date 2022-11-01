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

class MathsSmootherstepTest {

    /**
     * Test the [Float.smootherStep] function.
     */

    @Test
    fun testSmoothStepFloat() {
        assertEquals(0.0f, 0.0f.smootherStep())
        assertEquals(1.0f, 1.0f.smootherStep())
        assertEquals(0.0579f, 0.2f.smootherStep(), 0.001f)
        assertEquals(0.5f, 0.5f.smootherStep())
    }

    /**
     * Test the [Double.smootherStep] function.
     */

    @Test
    fun testSmoothStepDouble() {
        assertEquals(0.0, 0.0.smootherStep())
        assertEquals(1.0, 1.0.smootherStep())
        assertEquals(0.0579, 0.2.smootherStep(), 0.001)
        assertEquals(0.5, 0.5.smootherStep())
    }
}

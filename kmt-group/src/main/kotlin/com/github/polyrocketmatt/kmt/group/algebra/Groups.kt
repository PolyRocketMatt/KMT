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

package com.github.polyrocketmatt.kmt.group.algebra

import com.github.polyrocketmatt.kmt.group.set.DefinedSet

class Groups {

    companion object {
        val INTEGERS = Group(0, { a: Int -> -a }, { a: Int, b: Int -> a + b}, DefinedSet.INTEGERS)
        val REAL_DOUBLES_ADDITION = Group(0.0, { a: Double -> -a }, { a: Double, b: Double -> a + b}, DefinedSet.REAL_DOUBLES)
        val REAL_FLOATS_ADDITION = Group(0.0f, { a: Float -> -a }, { a: Float, b: Float -> a + b}, DefinedSet.REAL_FLOATS)
        val REAL_DOUBLES_MULTIPLICATION = Group(1.0, { a: Double -> 1.0 / a }, { a: Double, b: Double -> a * b}, DefinedSet.REAL_DOUBLES)
        val REAL_FLOATS_MULTIPLICATION = Group(1.0f, { a: Float -> 1.0f / a }, { a: Float, b: Float -> a * b}, DefinedSet.REAL_FLOATS)
    }

}

class IntegersModAddition(n: Int) : Group<Int>(0, { a: Int -> n - a }, { a: Int, b: Int -> (a + b) % n }, IntArray(n).toSet())
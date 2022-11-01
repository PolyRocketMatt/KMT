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

/**
 * Find the index of the element in the array, given the condition function.
 *
 * @param condition The condition function to check.
 * @return The index of the element, or -1 if not found.
 */
fun Array<Complex>.indexByCondition(base: Complex, condition: (cIdx: Int, current: Complex, idx: Int, value: Complex) -> Boolean): Int {
    if (this.isEmpty())
        return -1

    var index = -1
    var element = base
    for (i in 0 until this.size) {
        if (condition(index, element, i, this[i])) {
            element = this[i]
            index = i
        }
    }

    return index
}
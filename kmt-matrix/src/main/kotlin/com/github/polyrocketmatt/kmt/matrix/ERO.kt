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

package com.github.polyrocketmatt.kmt.matrix

/**
 * @author Matthias Kovacic
 * @since 0.0.8
 *
 * Represents an elementary row operation.
 */
enum class ERO {

    SWAP,
    MULTIPLY,
    ADD

}

/**
 * Data class used to hold information about a single elementary row operation.
 *
 * @param type The type of the elementary row operation.
 * @param row1 The first row involved in the operation.
 * @param row2 The second row involved in the operation.
 * @param scalar The scalar used in the operation.
 */
data class ElementaryOperation<T>(
    val type: ERO,
    val row1: Int,
    val row2: Int,
    val scalar: T
)
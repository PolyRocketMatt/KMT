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
 * Represents the dimension of a matrix.
 */
interface MatrixDimension {
    val dimension: Int
}

/**
 * Represents a dimension of n.
 */
open class Dimension(override val dimension: Int) : MatrixDimension {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Dimension) return false
        if (dimension != other.dimension) return false

        return true
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

}

/**
 * Represents a dimension of 4.
 */
object Dimension4 : Dimension(4), MatrixDimension

/**
 * Represents a dimension of 3.
 */
object Dimension3 : Dimension(3), MatrixDimension

/**
 * Represents a dimension of 2.
 */
object Dimension2 : Dimension(2), MatrixDimension

/**
 * Represents a dimension of 1.
 */
object Dimension1 : Dimension(1), MatrixDimension

/**
 * Get a dimension object from a given dimension.
 *
 * @param dimension The dimension to get the object for
 * @return The dimension object for the given dimension
 */
@Suppress("NOTHING_TO_INLINE")
inline fun dimensionOf(dimension: Int): MatrixDimension = when(dimension) {
    1 -> Dimension1
    2 -> Dimension2
    3 -> Dimension3
    4 -> Dimension4
    else -> Dimension(dimension)
}
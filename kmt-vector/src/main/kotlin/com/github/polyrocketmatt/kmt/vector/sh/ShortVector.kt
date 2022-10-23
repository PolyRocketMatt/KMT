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

package com.github.polyrocketmatt.kmt.vector.sh

import com.github.polyrocketmatt.kmt.matrix.BooleanMatrix
import com.github.polyrocketmatt.kmt.matrix.Matrix
import com.github.polyrocketmatt.kmt.matrix.ShortMatrix
import com.github.polyrocketmatt.kmt.vector.Vector
import com.github.polyrocketmatt.kmt.vector.bl.BooleanVector
import com.github.polyrocketmatt.kmt.vector.db.DoubleVector
import com.github.polyrocketmatt.kmt.vector.fl.FloatVector
import com.github.polyrocketmatt.kmt.vector.it.IntVector

fun ShortVector.float() = this.asFloat()
fun ShortVector.double() = this.asDouble()
fun ShortVector.int() = this.asInt()

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a mutable, n-dimensional vector of shorts.
 */
interface ShortVector : Vector<Short>, Matrix<Short> {

    /**
     * Get the vector as a floating point vector.
     *
     * @return The vector as a floating point vector.
     */
    fun asFloat(): FloatVector

    /**
     * Get the vector as a double vector.
     *
     * @return The vector as a double vector.
     */
    fun asDouble(): DoubleVector

    /**
     * Get the vector as an integer vector.
     *
     * @return The vector as an integer vector.
     */
    fun asInt(): IntVector

    /**
     * Get the vector as a boolean vector.
     *
     * @return The vector as a boolean vector.
     */
    fun asBoolean(): BooleanVector

    /**
     * Get the vector as a short row matrix.
     *
     * @return The vector as a short row matrix.
     */
    fun asRowMatrix(): ShortMatrix

    /**
     * Get the vector as a short column matrix.
     *
     * @return The vector as a short column matrix.
     */
    fun asColumnMatrix(): ShortMatrix
}

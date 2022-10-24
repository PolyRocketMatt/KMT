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

interface Matrix<T> {

    operator fun get(i: Int): T
    operator fun get(row: Int, col: Int): T

    operator fun set(i: Int, value: T)
    operator fun set(row: Int, col: Int, value: T)

    operator fun plus(value: T): Matrix<T>
    operator fun minus(value: T): Matrix<T>
    operator fun times(value: T): Matrix<T>
    operator fun div(value: T): Matrix<T>

    operator fun plusAssign(value: T)
    operator fun minusAssign(value: T)
    operator fun timesAssign(value: T)
    operator fun divAssign(value: T)

    /**
     * Compute the transpose of this matrix.
     *
     * @return The transpose of this matrix
     */
    fun transpose(): Matrix<T>

    /**
     * Compute the trace of this matrix.
     *
     * @return The trace of this matrix
     */
    fun trace(): T

    /**
     * Compute the diagonal of this matrix.
     *
     * @return The diagonal of this matrix (as a matrix).
     */
    fun diag(): Matrix<T>

    /**
     * Concatenate another matrix horizontally with this matrix.
     *
     * @param other The matrix to concatenate with this matrix.
     * @return The horizontally concatenated matrix.
     */
    infix fun concatHorizontal(other: Matrix<T>): Matrix<T>

    /**
     * Concatenate another matrix vertically with this matrix.
     *
     * @param other The matrix to concatenate with this matrix.
     * @return The vertically concatenated matrix.
     */
    infix fun concatVertical(other: Matrix<T>): Matrix<T>

    /**
     * Check if this matrix is a scalar.
     *
     * @return True if this matrix is a scalar, false otherwise.
     */
    fun isScalar(): Boolean

    /**
     * Check if this matrix is a square matrix.
     *
     * @return True if this matrix is a square matrix, false otherwise.
     */
    fun isSquare(): Boolean
}

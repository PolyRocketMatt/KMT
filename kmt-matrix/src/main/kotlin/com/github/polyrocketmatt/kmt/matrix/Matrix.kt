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

    /**
     * Get the shape of the matrix.
     *
     * @return the shape of the matrix
     */
    fun shape(): IntArray

    /**
     * Get the i-th element of the matrix.
     *
     * @param i the index of the element
     * @return the i-th element of the matrix
     */
    operator fun get(i: Int): T

    /**
     * Get the element at the given row and column in the matrix.
     *
     * @param row the row of the element
     * @param col the column of the element
     * @return the element at the given row and column in the matrix
     */
    operator fun get(row: Int, col: Int): T

    /**
     * Set the i-th element of the matrix.
     *
     * @param i the index of the element
     * @param value the new value of the element
     */
    operator fun set(i: Int, value: T)

    /**
     * Set the element at the given row and column in the matrix.
     *
     * @param row the row of the element
     * @param col the column of the element
     * @param value the new value of the element
     */
    operator fun set(row: Int, col: Int, value: T)

    /**
     * Element-wise addition of this matrix and the given matrix.
     *
     * @param other The matrix to add to this matrix
     * @return The sum of this matrix and the given matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    operator fun plus(other: Matrix<T>): Matrix<T>

    /**
     * Element-wise subtraction of this matrix and the given matrix.
     *
     * @param other The matrix to subtract from this matrix
     * @return The difference of this matrix and the given matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    operator fun minus(other: Matrix<T>): Matrix<T>

    /**
     * Element-wise multiplication of this matrix and the given matrix.
     *
     * @param other The matrix to multiply with this matrix
     * @return The product of this matrix and the given matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    operator fun times(other: Matrix<T>): Matrix<T>

    /**
     * Element-wise division of this matrix and the given matrix.
     *
     * @param other The matrix to divide this matrix with
     * @return The quotient of this matrix and the given matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    operator fun div(other: Matrix<T>): Matrix<T>

    /**
     * Element-wise addition of this matrix and the given matrix.
     *
     * @param other The matrix to add to this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    operator fun plusAssign(other: Matrix<T>)

    /**
     * Element-wise subtraction of this matrix and the given matrix.
     *
     * @param other The matrix to subtract from this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    operator fun minusAssign(other: Matrix<T>)

    /**
     * Element-wise multiplication of this matrix and the given matrix.
     *
     * @param other The matrix to multiply with this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    operator fun timesAssign(other: Matrix<T>)

    /**
     * Element-wise division of this matrix and the given matrix.
     *
     * @param other The matrix to divide to this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    operator fun divAssign(other: Matrix<T>)

    /**
     * Scalar addition of this matrix and the given value.
     *
     * @param value The value to add to this matrix
     * @return The sum of this matrix and the given value
     */
    operator fun plus(value: T): Matrix<T>

    /**
     * Scalar subtraction of this matrix and the given value.
     *
     * @param value The value to subtract from this matrix
     * @return The difference of this matrix and the given value
     */
    operator fun minus(value: T): Matrix<T>

    /**
     * Scalar multiplication of this matrix and the given value.
     *
     * @param value The value to multiply with this matrix
     * @return The product of this matrix and the given value
     */
    operator fun times(value: T): Matrix<T>

    /**
     * Scalar division of this matrix and the given value.
     *
     * @param value The value to divide with this matrix
     * @return The quotient of this matrix and the given value
     */
    operator fun div(value: T): Matrix<T>

    /**
     * Scalar addition of this matrix and the given value.
     *
     * @param value The value to add to this matrix
     */
    operator fun plusAssign(value: T)

    /**
     * Scalar subtraction of this matrix and the given value.
     *
     * @param value The value to subtract from this matrix
     */
    operator fun minusAssign(value: T)

    /**
     * Scalar multiplication of this matrix and the given value.
     *
     * @param value The value to multiply with this matrix
     */
    operator fun timesAssign(value: T)

    /**
     * Scalar division of this matrix and the given value.
     *
     * @param value The value to divide with this matrix
     */
    operator fun divAssign(value: T)

    /**
     * Invert the elements of this matrix.
     *
     * @return The inverted matrix
     */
    operator fun unaryMinus(): Matrix<T>
}

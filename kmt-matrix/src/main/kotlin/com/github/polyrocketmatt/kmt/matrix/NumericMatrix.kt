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

import com.github.polyrocketmatt.kmt.common.storage.Tuple

/**
 * @author Matthias Kovacic
 * @since 0.0.8
 *
 * Represents a numerical matrix that can be used in numerical calculations.
 *
 * @param T The type of the matrix elements.
 * @param K The type of the matrix that is returned after performing an operation.
 */
interface NumericMatrix<T, K> : GeneralMatrix<T> {

    /**
     * Performs the elementary row operation of swapping two rows.
     *
     * @param row1 The first row to swap.
     * @param row2 The second row to swap.
     * @return The new matrix with the rows swapped.
     */
    fun swapRow(row1: Int, row2: Int)

    /**
     * Performs the elementary row operation of multiplying a row by a scalar.
     *
     * @param row The row to multiply.
     * @param scalar The scalar to multiply the row by.
     * @return The new matrix with the row multiplied.
     */
    fun multiplyRow(row: Int, scalar: T)

    /**
     * Performs the elementary row operation of adding a multiple of one row to another.
     *
     * @param row1 The row to add to.
     * @param row2 The row to add.
     * @param scalar The scalar to multiply the row by.
     * @return The new matrix with the row added.
     */
    fun addRow(row1: Int, row2: Int, scalar: T)

    /**
     * Performs the (ordered) list of elementary row operations on the matrix.
     *
     * @param operations The list of operations to perform.
     * @return The new matrix with the operations performed.
     */
    fun operate(operations: List<ElementaryOperation<K>>): Matrix<K>

    /**
     * Compute the row echelon form of this matrix using Gaussian elimination
     * with partial pivoting since this is considered to be numerically stable.
     *
     * @return The row echelon form of this matrix.
     */
    fun ref(): Matrix<K>

    /**
     * Compute the row-reduced echelon form of this matrix using Gaussian elimination
     * with partial pivoting since this is considered to be numerically stable.
     *
     * @return The row-reduced echelon form of this matrix.
     */
    fun rref(): Matrix<K>

    /**
     * Compute the LU-decomposition of this matrix where the lower triangle matrix is 1
     * on the diagonal.
     *
     * @return The LU-decomposition of this matrix.
     */
    fun luDecomposition(): Pair<Matrix<K>, Matrix<K>>

    /**
     * Compute the determinant of this matrix.
     *
     * @return The determinant of this matrix.
     */
    fun determinant(): K

    /**
     * Check if the matrix is invertible.
     *
     * @return True if the matrix is invertible (the determinant is 0), false otherwise.
     */
    fun isInvertible(): Boolean

    /**
     * Compute the inverse of this matrix.
     *
     * @return The inverse of this matrix.
     * @throws ArithmeticException If the matrix is not square.
     * @throws IllegalArgumentException If the matrix is not invertible.
     */
    fun inverse(): Matrix<K>

    /**
     * Get the rank of the matrix.
     *
     * @return The rank of the matrix.
     */
    fun rank(): Int

    /**
     * Get the nullity of the matrix.
     *
     * @return The nullity of the matrix.
     */
    fun nullity(): Int

    /**
     * Check if the rows of the matrix are linearly independent.
     *
     * @return True if the rows are linearly independent, false otherwise.
     */
    fun linearlyIndependentRows(): Boolean

    /**
     * Check if the columns of the matrix are linearly independent.
     *
     * @return True if the columns are linearly independent, false otherwise.
     */
    fun linearlyIndependentColumns(): Boolean

    /**
     * Get the norm of the matrix depending on the given type of norm.
     *
     * @param type The type of norm to compute.
     * @return The norm of the matrix.
     */
    fun norm(type: NormType): K

    fun eigenvalues(): Array<K>

    fun eigenvectors(): Array<Tuple<K>>

}

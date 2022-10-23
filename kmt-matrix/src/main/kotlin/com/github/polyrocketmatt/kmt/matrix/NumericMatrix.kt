package com.github.polyrocketmatt.kmt.matrix

/**
 * @author Matthias Kovacic
 * @since 0.0.8
 *
 * Represents a numerical matrix that can be used in numerical calculations.
 */
interface NumericMatrix<T, K> : Matrix<T> {

    /**
     * Performs the elementary row operation of swapping two rows.
     *
     * @param row1 The first row to swap.
     * @param row2 The second row to swap.
     * @return The new matrix with the rows swapped.
     */
    fun swapRow(row1: Int, row2: Int): Matrix<K>

    /**
     * Performs the elementary row operation of multiplying a row by a scalar.
     *
     * @param row The row to multiply.
     * @param scalar The scalar to multiply the row by.
     * @return The new matrix with the row multiplied.
     */
    fun multiplyRow(row: Int, scalar: T): Matrix<K>

    /**
     * Performs the elementary row operation of adding a multiple of one row to another.
     *
     * @param row1 The row to add to.
     * @param row2 The row to add.
     * @param scalar The scalar to multiply the row by.
     * @return The new matrix with the row added.
     */
    fun addRow(row1: Int, row2: Int, scalar: T): Matrix<K>

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
     * Solve the system of linear equations represented by this matrix.
     *
     * @return The solution to the system of linear equations represented by this matrix.
     */
    fun solve(): Matrix<K>

}
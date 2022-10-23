package com.github.polyrocketmatt.kmt.matrix

/**
 * @author Matthias Kovacic
 * @since 0.0.8
 *
 * Represents a numerical matrix that can be used in numerical calculations.
 */
interface NumericMatrix<T> : Matrix<T> {

    /**
     * Compute the row echelon form of this matrix using Gaussian elimination
     * with partial pivoting since this is considered to be numerically stable.
     *
     * @return The row echelon form of this matrix.
     */
    fun ref(): NumericMatrix<Double>

    /**
     * Compute the row-reduced echelon form of this matrix using Gaussian elimination
     * with partial pivoting since this is considered to be numerically stable.
     *
     * @return The row-reduced echelon form of this matrix.
     */
    fun rref(): NumericMatrix<Double>

    /**
     * Solve the system of linear equations represented by this matrix.
     *
     * @return The solution to the system of linear equations represented by this matrix.
     */
    fun solve(): NumericMatrix<Double>

}
package com.github.polyrocketmatt.kmt.matrix

interface GeneralMatrix<T> : Matrix<T> {

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
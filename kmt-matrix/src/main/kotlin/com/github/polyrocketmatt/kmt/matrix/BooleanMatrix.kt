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
import com.github.polyrocketmatt.kmt.common.utils.complies
import kotlin.IllegalArgumentException

typealias BMatrix = BooleanMatrix

/**
 * Get a matrix with the given shape from the given array.
 *
 * @param shape The shape of the matrix
 * @return A matrix with the given shape and the given array as its data
 * @throws IllegalStateException If the array does not comply with the given shape
 */
fun Array<Boolean>.toMatrix(shape: IntArray): BooleanMatrix {
    val elements = shape.reduce { acc, i -> acc * i }
    shape.complies(
        {
            "Incorrect array size for shape ${shape.joinToString("x") { "$it" }}. " +
                "Expected $elements, found ${this.size}"
        },
        { this.size == elements }
    )
    return BooleanMatrix(shape, this.toBooleanArray())
}

/**
 * Get a matrix with the given shape from the given array.
 *
 * @param shape The shape of the matrix
 * @return A matrix with the given shape and the given array as its data
 * @throws IllegalStateException If the array does not comply with the given shape
 */
fun BooleanArray.toMatrix(shape: IntArray): BooleanMatrix {
    val elements = shape.reduce { acc, i -> acc * i }
    shape.complies(
        {
            "Incorrect array size for shape ${shape.joinToString("x") { "$it" }}. " +
                "Expected $elements, found ${this.size}"
        },
        { this.size == elements }
    )
    return BooleanMatrix(shape, this)
}

/**
 * Get an array of floating-point numbers from the given matrix.
 *
 * @return The array of floating-point numbers with data from the given matrix
 */
fun BooleanMatrix.toArray(): BooleanArray = this.data.toBooleanArray()

/**
 * @author Matthias Kovacic
 * @since 0.0.8
 *
 * Represents a matrix of a given shape holding
 * floating-point values.
 *
 * @param shape The shape of the matrix
 *
 * TODO: Fix mult
 */
open class BooleanMatrix(
    val shape: IntArray,
    matrix: BooleanArray,
) : Tuple<Boolean>(BooleanArray(shape.reduce { acc, i -> acc * i }).toTypedArray()),
    GeneralMatrix<Boolean> {

    constructor(matrix: BooleanArray) : this(intArrayOf(matrix.size), matrix)
    constructor(shape: IntArray) : this(shape, BooleanArray(shape.reduce { acc, i -> acc * i }) { false })
    constructor(shape: IntArray, value: Boolean) : this(shape, BooleanArray(shape.reduce { acc, i -> acc * i }) { value })
    constructor(shape: IntArray, matrix: Array<Boolean>) : this(shape, matrix.toBooleanArray())

    init {
        val shapeSize = shape.reduce { acc, i -> acc * i }

        complies("Data must contain $shapeSize elements for a matrix of size ${shape.joinToString("x") { "$it" }}") { data.size == size }

        matrix.forEachIndexed { i, value -> data[i] = value }
    }

    open fun rows(): Array<BooleanArray> {
        val rows = Array(shape[0]) { BooleanArray(shape[1]) }
        for (j in 0 until shape[1])
            for (i in 0 until shape[0])
                rows[i][j] = this[i, j]
        return rows
    }

    open fun columns(): Array<BooleanArray> {
        val columns = Array(shape[1]) { BooleanArray(shape[0]) }
        for (i in 0 until shape[0])
            for (j in 0 until shape[1])
                columns[j][i] = this[i, j]
        return columns
    }

    open fun row(idx: Int): BooleanArray {
        complies("Index $idx is out of bounds for ${shape[0]} rows") { idx in 0 until shape[0] }

        val row = BooleanArray(shape[1])
        for (j in 0 until shape[1])
            row[j] = this[idx, j]
        return row
    }

    open fun column(idx: Int): BooleanArray {
        complies("Index $idx is out of bounds for ${shape[1]} columns") { idx in 0 until shape[1] }

        val column = BooleanArray(shape[0])
        for (i in 0 until shape[0])
            column[i] = this[i, idx]
        return column
    }

    override fun shape(): IntArray = shape

    override operator fun get(i: Int): Boolean = data[i]

    override operator fun get(row: Int, col: Int): Boolean = data[row * shape[1] + col]

    override operator fun set(i: Int, value: Boolean) { data[i] = value }

    override operator fun set(row: Int, col: Int, value: Boolean) { data[row * shape[1] + col] = value }

    /**
     * Element-wise addition of this matrix and the given matrix.
     *
     * @param other The matrix to add to this matrix
     * @return The sum of this matrix and the given matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun plus(other: BooleanMatrix): BooleanMatrix {
        isCompliantMatrix(other)

        val matrix = BooleanMatrix(shape)
        data.forEachIndexed { i, value -> matrix[i] = value || other[i] }
        return matrix
    }

    override fun plus(other: Matrix<Boolean>): BooleanMatrix = plus(other as BooleanMatrix)

    /**
     * Element-wise subtraction of this matrix and the given matrix.
     *
     * @param other The matrix to subtract from this matrix
     * @throws UnsupportedOperationException Boolean matrices cannot be subtracted
     */
    open operator fun minus(other: BooleanMatrix): BooleanMatrix = throw UnsupportedOperationException("Cannot subtract boolean matrices")

    override fun minus(other: Matrix<Boolean>): BooleanMatrix = minus(other as BooleanMatrix)

    /**
     * Element-wise multiplication of this matrix and the given matrix.
     *
     * @param other The matrix to multiply with this matrix
     * @return The product of this matrix and the given matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun times(other: BooleanMatrix): BooleanMatrix {
        isCompliantMatrix(other)

        val matrix = BooleanMatrix(shape)
        data.forEachIndexed { i, value -> matrix[i] = value && other[i] }
        return matrix
    }

    override fun times(other: Matrix<Boolean>): BooleanMatrix = times(other as BooleanMatrix)

    /**
     * Element-wise division of this matrix and the given matrix.
     *
     * @param other The matrix to divide this matrix with
     * @throws UnsupportedOperationException Boolean matrices cannot be divided
     */
    open operator fun div(other: BooleanMatrix): BooleanMatrix = throw UnsupportedOperationException("Cannot divide boolean matrices")

    override fun div(other: Matrix<Boolean>): BooleanMatrix = div(other as BooleanMatrix)

    /**
     * Element-wise addition of this matrix and the given matrix.
     *
     * @param other The matrix to add to this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun plusAssign(other: BooleanMatrix) {
        isCompliantMatrix(other)
        data.forEachIndexed { i, term -> data[i] = data[i] || term }
    }

    override fun plusAssign(other: Matrix<Boolean>) = plusAssign(other as BooleanMatrix)

    /**
     * Element-wise subtraction of this matrix and the given matrix.
     *
     * @param other The matrix to subtract from this matrix
     * @throws UnsupportedOperationException Boolean matrices cannot be subtracted
     */
    open operator fun minusAssign(other: BooleanMatrix) {
        throw UnsupportedOperationException("Cannot subtract boolean matrices")
    }

    override fun minusAssign(other: Matrix<Boolean>) = minusAssign(other as BooleanMatrix)

    /**
     * Element-wise multiplication of this matrix and the given matrix.
     *
     * @param other The matrix to multiply to this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun timesAssign(other: BooleanMatrix) {
        isCompliantMatrix(other)
        data.forEachIndexed { i, factor -> data[i] = data[i] && factor }
    }

    override fun timesAssign(other: Matrix<Boolean>) = timesAssign(other as BooleanMatrix)

    /**
     * Element-wise division of this matrix and the given matrix.
     *
     * @param other The matrix to divide to this matrix
     * @throws UnsupportedOperationException Boolean matrices cannot be divided
     */
    open operator fun divAssign(other: BooleanMatrix) {
        throw UnsupportedOperationException("Cannot divide boolean matrices")
    }

    override fun divAssign(other: Matrix<Boolean>) = divAssign(other as BooleanMatrix)

    override fun plus(value: Boolean): BooleanMatrix {
        val matrix = BooleanMatrix(shape)
        data.forEachIndexed { i, term -> matrix[i] = data[i] || term }
        return matrix
    }

    override fun minus(value: Boolean): BooleanMatrix = throw UnsupportedOperationException("Cannot subtract boolean from boolean matrix")

    override fun times(value: Boolean): BooleanMatrix {
        val matrix = BooleanMatrix(shape)
        data.forEachIndexed { i, factor -> matrix[i] = data[i] && factor }
        return matrix
    }

    override fun div(value: Boolean): BooleanMatrix = throw UnsupportedOperationException("Cannot divide boolean matrix by boolean")

    override fun plusAssign(value: Boolean) = data.forEachIndexed { i, term -> data[i] = data[i] || term }

    override fun minusAssign(value: Boolean) = throw UnsupportedOperationException("Cannot subtract boolean from boolean matrix")

    override fun timesAssign(value: Boolean) = data.forEachIndexed { i, factor -> data[i] = data[i] && factor }

    override fun divAssign(value: Boolean) = throw UnsupportedOperationException("Cannot divide boolean from boolean matrix")

    override fun unaryMinus(): BooleanMatrix = BooleanMatrix(shape, data.map { !it }.toBooleanArray())

    /**
     * Multiply this matrix with the given matrix. The matrices must have
     * a valid shape for multiplication and must be of dimension 2.
     *
     * @param other The matrix to multiply with this matrix
     * @return The result of the multiplication
     * @throws IllegalArgumentException If the matrices are not of dimension 2
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open infix fun mult(other: BooleanMatrix): BooleanMatrix {
        other.complies("Cannot multiply matrices of sizes ${shapeToString()} and ${other.shapeToString()}") { it.shape[0] == shape[1] }

        if (other.shape[0] != shape[1])
            throw IllegalArgumentException("Cannot multiply matrices of sizes ${shapeToString()} and ${other.shapeToString()}")

        val result = BooleanMatrix(intArrayOf(shape[0], other.shape[1]))

        //  Multiplying rows of first matrix with columns of second matrix
        val r1 = shape[0]
        val c1 = shape[1]
        val c = other.shape[1]
        for (i in 0 until r1)
            for (j in 0 until c)
                for (k in 0 until c1)
                    result.data[i * c + j] = result.data[i * c + j] || (data[i * r1 + k] && other.data[k * c + j])
        return result
    }

    open override fun transpose(): BooleanMatrix {
        val matrix = BooleanMatrix(intArrayOf(shape[1], shape[0]))
        for (i in 0 until shape[0])
            for (j in 0 until shape[1])
                matrix[j, i] = this[i, j]
        return matrix
    }
    override fun trace(): Boolean = diag().reduce { acc, b -> acc || b }
    override fun diag(): BooleanMatrix {
        val diag = BooleanMatrix(intArrayOf(1, shape[1]))
        for (i in 0 until shape[1])
            diag[i] = this[i, i]
        return diag
    }

    override infix fun concatHorizontal(other: Matrix<Boolean>): BooleanMatrix {
        val matrix = (other.complies("Other matrix must be of type BooleanMatrix") { other is BooleanMatrix } as BooleanMatrix)
            .complies({ "Rows must match to concatenate horizontally. Expected ${shape[0]}, found ${it.shape[0]}" }, { it.shape[0] == shape[0] })
        val offset = shape[0]
        val result = BooleanMatrix(intArrayOf(offset, shape[1] + matrix.shape[1]))
        for (j in 0 until shape[1]) for (i in 0 until offset)
            result[i * result.shape[1] + j] = this[i, j]
        for (j in 0 until shape[1]) for (i in 0 until offset)
            result[offset + i * result.shape[1] + j] = matrix[i, j]
        return result
    }
    override infix fun concatVertical(other: Matrix<Boolean>): BooleanMatrix {
        val matrix = (other.complies("Other matrix must be of type BooleanMatrix") { other is BooleanMatrix } as BooleanMatrix)
            .complies({ "Columns must match to concatenate vertically. Expected ${shape[1]}, found ${it.shape[1]}" }, { it.shape[1] == shape[1] })
        val offset = shape[1]
        val indexOffset = shape[0] * offset
        val result = BooleanMatrix(intArrayOf(shape[0] + matrix.shape[0], offset))
        for (i in 0 until indexOffset)
            result[i] = this[i]
        for (i in 0 until matrix.shape[0] * offset)
            result[indexOffset + i] = matrix[i]
        return result
    }

    override fun isScalar(): Boolean = data.size == 1

    override fun isSquare(): Boolean = shape[0] == shape[1]

    internal fun shapeToString(): String = shape.joinToString("x") { "$it" }

    internal fun isCompliantMatrix(other: BooleanMatrix) =
        other
            .complies({ "Other is of type ${it::class.java}, expected ${this::class.java}" }, { it::class.java == this::class.java })
            .also { it -> it.complies({ "Shape does not match. Expected ${shapeToString()}, found ${other.shapeToString()}" }, { it.shape.contentEquals(this.shape) }) }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FloatMatrix) return false

        if (!shape.contentEquals(other.shape)) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = shape.contentHashCode()
        result = 31 * result + data.contentHashCode()
        return result
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (i in 0 until shape[0]) {
            sb.append("| ")
            sb.append(row(i).joinToString(" "))
            sb.append(" |\n")
        }
        return sb.toString()
    }
}

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

typealias SMatrix = ShortMatrix

/**
 * Get a matrix with the given shape from the given array.
 *
 * @param shape The shape of the matrix
 * @return A matrix with the given shape and the given array as its data
 * @throws IllegalStateException If the array does not comply with the given shape
 */
fun Array<Short>.toMatrix(shape: IntArray): ShortMatrix {
    val elements = shape.reduce { acc, i -> acc * i }
    shape.complies(
        {
            "Incorrect array size for shape ${shape.joinToString("x") { "$it" }}. " +
                "Expected $elements, found ${this.size}"
        },
        { this.size == elements }
    )
    return ShortMatrix(shape, this.toShortArray())
}

/**
 * Get a matrix with the given shape from the given array.
 *
 * @param shape The shape of the matrix
 * @return A matrix with the given shape and the given array as its data
 * @throws IllegalStateException If the array does not comply with the given shape
 */
fun ShortArray.toMatrix(shape: IntArray): ShortMatrix {
    val elements = shape.reduce { acc, i -> acc * i }
    shape.complies(
        {
            "Incorrect array size for shape ${shape.joinToString("x") { "$it" }}. " +
                "Expected $elements, found ${this.size}"
        },
        { this.size == elements }
    )
    return ShortMatrix(shape, this)
}

/**
 * Get an array of floating-point numbers from the given matrix.
 *
 * @return The array of floating-point numbers with data from the given matrix
 */
fun ShortMatrix.toArray(): ShortArray = this.data.toShortArray()

/**
 * @author Matthias Kovacic
 * @since 0.0.8
 *
 * Represents a matrix of a given dimension and shape holding
 * floating-point values.
 *
 * @param shape The shape of the matrix
 *
 * TODO: Fix mult
 */
open class ShortMatrix(
    val shape: IntArray,
    matrix: ShortArray
) : Tuple<Short>(ShortArray(shape.reduce { acc, i -> acc * i }).toTypedArray()),
    NumericMatrix<Short, Double> {

    companion object {
        fun identity(shape: IntArray): ShortMatrix {
            shape.complies("Identity matrix only exists for dimension 2") { it.size == 2 }

            val matrix = ShortMatrix(shape)
            val min = shape.min()

            for (i in 0 until min)
                matrix[i * shape[1] + i] = 1

            return matrix
        }
    }

    constructor(matrix: ShortArray) : this(intArrayOf(matrix.size), matrix)
    constructor(shape: IntArray) : this(shape, ShortArray(shape.reduce { acc, i -> acc * i }) { 0.toShort() })
    constructor(shape: IntArray, value: Short) : this(shape, ShortArray(shape.reduce { acc, i -> acc * i }) { value })
    constructor(matrix: Array<Short>) : this(matrix.toShortArray())

    init {
        val shapeSize = shape.reduce { acc, i -> acc * i }

        complies("Data must contain $shapeSize elements for a matrix of size ${shape.joinToString("x") { "$it" }}") { data.size == size }

        matrix.forEachIndexed { i, value -> data[i] = value }
    }

    open fun rows(): Array<ShortArray> {
        val rows = Array(shape[0]) { ShortArray(shape[1]) }
        for (j in 0 until shape[1])
            for (i in 0 until shape[0])
                rows[i][j] = this[i, j]
        return rows
    }

    open fun columns(): Array<ShortArray> {
        val columns = Array(shape[1]) { ShortArray(shape[0]) }
        for (i in 0 until shape[0])
            for (j in 0 until shape[1])
                columns[j][i] = this[i, j]
        return columns
    }

    open fun row(idx: Int): ShortArray {
        complies("Index $idx is out of bounds for ${shape[0]} rows") { idx in 0 until shape[0] }

        val row = ShortArray(shape[1])
        for (j in 0 until shape[1])
            row[j] = this[idx, j]
        return row
    }

    open fun column(idx: Int): ShortArray {
        complies("Index $idx is out of bounds for ${shape[1]} columns") { idx in 0 until shape[1] }

        val column = ShortArray(shape[0])
        for (i in 0 until shape[0])
            column[i] = this[i, idx]
        return column
    }

    override operator fun get(i: Int): Short = data[i]
    override operator fun get(row: Int, col: Int): Short = data[row * shape[1] + col]

    override operator fun set(i: Int, value: Short) { data[i] = value }
    override operator fun set(row: Int, col: Int, value: Short) { data[row * shape[1] + col] = value }

    /**
     * Element-wise addition of this matrix and the given matrix.
     *
     * @param other The matrix to add to this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun plus(other: ShortMatrix): ShortMatrix {
        isCompliantMatrix(other)
        val matrix = ShortMatrix(shape)
        data.forEachIndexed { i, value -> matrix[i] = (value + other[i]).toShort() }
        return matrix
    }

    /**
     * Element-wise subtraction of this matrix and the given matrix.
     *
     * @param other The matrix to subtract from this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun minus(other: ShortMatrix): ShortMatrix {
        isCompliantMatrix(other)
        val matrix = ShortMatrix(shape)
        data.forEachIndexed { i, value -> matrix[i] = (value - other[i]).toShort() }
        return matrix
    }

    /**
     * Element-wise multiplication of this matrix and the given matrix.
     *
     * @param other The matrix to multiply with this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun times(other: ShortMatrix): ShortMatrix {
        isCompliantMatrix(other)
        val matrix = ShortMatrix(shape)
        data.forEachIndexed { i, value -> matrix[i] = (value * other[i]).toShort() }
        return matrix
    }

    /**
     * Element-wise division of this matrix and the given matrix.
     *
     * @param other The matrix to divide this matrix with
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun div(other: ShortMatrix): ShortMatrix {
        isCompliantMatrix(other)
        val matrix = ShortMatrix(shape)
        data.forEachIndexed { i, value -> matrix[i] = (value / other[i]).toShort() }
        return matrix
    }

    /**
     * Element-wise addition of this matrix and the given matrix.
     *
     * @param other The matrix to add to this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun plusAssign(other: ShortMatrix) {
        isCompliantMatrix(other)
        data.forEachIndexed { i, term -> data[i] = (data[i] + term).toShort() }
    }

    /**
     * Element-wise subtraction of this matrix and the given matrix.
     *
     * @param other The matrix to subtract from this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun minusAssign(other: ShortMatrix) {
        isCompliantMatrix(other)
        data.forEachIndexed { i, term -> data[i] = (data[i] - term).toShort() }
    }

    /**
     * Element-wise multiplication of this matrix and the given matrix.
     *
     * @param other The matrix to multiply to this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun timesAssign(other: ShortMatrix) {
        isCompliantMatrix(other)
        data.forEachIndexed { i, factor -> data[i] = (data[i] * factor).toShort() }
    }

    /**
     * Element-wise division of this matrix and the given matrix.
     *
     * @param other The matrix to divide to this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun divAssign(other: ShortMatrix) {
        isCompliantMatrix(other)
        data.forEachIndexed { i, factor -> data[i] = (data[i] / factor).toShort() }
    }

    /**
     * Scalar addition of this matrix and the given value.
     *
     * @param value The value to add to this matrix
     */
    override fun plus(value: Short): ShortMatrix {
        val matrix = ShortMatrix(shape)
        data.forEachIndexed { i, term -> matrix[i] = (data[i] + term).toShort() }
        return matrix
    }

    /**
     * Scalar subtraction of this matrix and the given value.
     *
     * @param value The value to subtract from this matrix
     */
    override fun minus(value: Short): ShortMatrix {
        val matrix = ShortMatrix(shape)
        data.forEachIndexed { i, term -> matrix[i] = (data[i] - term).toShort() }
        return matrix
    }

    /**
     * Scalar multiplication of this matrix and the given value.
     *
     * @param value The value to multiply to this matrix
     */
    override fun times(value: Short): ShortMatrix {
        val matrix = ShortMatrix(shape)
        data.forEachIndexed { i, factor -> matrix[i] = (data[i] * factor).toShort() }
        return matrix
    }

    /**
     * Scalar division of this matrix and the given value.
     *
     * @param value The value to divide with this matrix
     */
    override fun div(value: Short): ShortMatrix {
        val matrix = ShortMatrix(shape)
        data.forEachIndexed { i, factor -> matrix[i] = (data[i] / factor).toShort() }
        return matrix
    }

    /**
     * Scalar addition of this matrix and the given value.
     *
     * @param value The value to add to this matrix
     */
    override fun plusAssign(value: Short) = data.forEachIndexed { i, term -> data[i] = (data[i] + term).toShort() }

    /**
     * Scalar subtraction of this matrix and the given value.
     *
     * @param value The value to subtract from this matrix
     */
    override fun minusAssign(value: Short) = data.forEachIndexed { i, term -> data[i] = (data[i] - term).toShort() }

    /**
     * Scalar multiplication of this matrix and the given value.
     *
     * @param value The value to multiply to this matrix
     */
    override fun timesAssign(value: Short) = data.forEachIndexed { i, factor -> data[i] = (data[i] * factor).toShort() }

    /**
     * Scalar division of this matrix and the given value.
     *
     * @param value The value to divide with this matrix
     */
    override fun divAssign(value: Short) = data.forEachIndexed { i, factor -> data[i] = (data[i] / factor).toShort() }

    /**
     * Multiply this matrix with the given matrix. The matrices must have
     * a valid shape for multiplication and must be of dimension 2.
     *
     * @param other The matrix to multiply with this matrix
     * @return The result of the multiplication
     * @throws IllegalArgumentException If the matrices are not of dimension 2
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open infix fun mult(other: ShortMatrix): ShortMatrix {
        other.complies("Cannot multiply matrices of sizes ${shapeToString()} and ${other.shapeToString()}") { it.shape[0] == shape[1] }

        //  Multiplying rows of first matrix with columns of second matrix
        val result = ShortMatrix(intArrayOf(shape[0], other.shape[1]))
        val r1 = shape[0]
        val c1 = shape[1]
        val c = other.shape[1]
        for (i in 0 until r1)
            for (j in 0 until c)
                for (k in 0 until c1)
                    result.data[i * c + j] = (result.data[i * c + j] + data[i * r1 + k] * other.data[k * c + j]).toShort()
        return result
    }

    override fun transpose(): ShortMatrix {
        val matrix = ShortMatrix(intArrayOf(shape[1], shape[0]))
        for (i in 0 until shape[0])
            for (j in 0 until shape[1])
                matrix[j, i] = this[i, j]
        return matrix
    }

    override fun trace(): Short = diag().sum().toShort()

    override fun diag(): ShortMatrix {
        val diag = ShortMatrix(intArrayOf(1, shape[1]))
        for (i in 0 until shape[1])
            diag[i] = this[i, i]
        return diag
    }

    override infix fun concatHorizontal(other: Matrix<Short>): ShortMatrix {
        val matrix = (other.complies("Other matrix must be of type ShortMatrix") { other is ShortMatrix } as ShortMatrix)
            .complies({ "Rows must match to concatenate horizontally. Expected ${shape[0]}, found ${it.shape[0]}" }, { it.shape[0] == shape[0] })
        val offset = shape[0]
        val result = ShortMatrix(intArrayOf(offset, shape[1] + matrix.shape[1]))
        for (j in 0 until shape[1]) for (i in 0 until offset)
            result[i * result.shape[1] + j] = this[i, j]
        for (j in 0 until shape[1]) for (i in 0 until offset)
            result[offset + i * result.shape[1] + j] = matrix[i, j]
        return result
    }
    override infix fun concatVertical(other: Matrix<Short>): ShortMatrix {
        val matrix = (other.complies("Other matrix must be of type ShortMatrix") { other is ShortMatrix } as ShortMatrix)
            .complies({ "Columns must match to concatenate vertically. Expected ${shape[1]}, found ${it.shape[1]}" }, { it.shape[1] == shape[1] })
        val offset = shape[1]
        val indexOffset = shape[0] * offset
        val result = ShortMatrix(intArrayOf(shape[0] + matrix.shape[0], offset))
        for (i in 0 until indexOffset)
            result[i] = this[i]
        for (i in 0 until matrix.shape[0] * offset)
            result[indexOffset + i] = matrix[i]
        return result
    }

    override fun isScalar(): Boolean = data.size == 1

    override fun isSquare(): Boolean = shape[0] == shape[1]

    override fun swapRow(row1: Int, row2: Int) {
        val rowIndex1 = row1 * shape[1]
        val rowIndex2 = row2 * shape[1]
        val tmp = data.copyOfRange(rowIndex1, rowIndex1 + shape[1])

        data.copyInto(data, rowIndex1, rowIndex2, rowIndex2 + shape[1])
        tmp.copyInto(data, rowIndex2, 0, shape[1])
    }

    override fun multiplyRow(row: Int, scalar: Short) {
        val rowIndex = row * shape[1]
        for (i in 0 until shape[1])
            data[rowIndex + i] = (data[rowIndex + i] * scalar).toShort()
    }

    override fun addRow(row1: Int, row2: Int, scalar: Short) {
        val rowIndex1 = row1 * shape[1]
        val rowIndex2 = row2 * shape[1]
        for (i in 0 until shape[1])
            data[i + rowIndex1] = (data[i + rowIndex1] + (data[i + rowIndex2] * scalar)).toShort()
    }

    override fun operate(operations: List<ElementaryOperation<Double>>): DoubleMatrix = toDoubleMatrix().operate(operations)

    override fun ref(): DoubleMatrix = toDoubleMatrix().ref()
    override fun rref(): DoubleMatrix = toDoubleMatrix().rref()

    override fun determinant(): Double = ref().diag().reduce { acc, d -> acc * d }

    override fun isInvertible(): Boolean = determinant() != 0.0

    override fun inverse(): DoubleMatrix = toDoubleMatrix().inverse()

    override fun rank(): Int = toDoubleMatrix().rank()

    override fun nullity(): Int = shape[1] - rank()

    override fun linearlyIndependentRows(): Boolean {
        TODO("Not yet implemented")
    }

    override fun linearlyIndependentColumns(): Boolean {
        TODO("Not yet implemented")
    }

    fun toDoubleMatrix(): DoubleMatrix = DoubleMatrix(shape, data.map { it.toDouble() }.toDoubleArray())
    fun toFloatMatrix(): FloatMatrix = FloatMatrix(shape, data.map { it.toFloat() }.toFloatArray())
    fun toIntMatrix(): IntMatrix = IntMatrix(shape, data.map { it.toInt() }.toIntArray())

    internal fun shapeToString(): String = shape.joinToString("x") { "$it" }

    internal fun isCompliantMatrix(other: ShortMatrix) =
        other
            .complies({ "Other is of type ${it::class.java}, expected ${this::class.java}" }, { it::class.java == this::class.java })
            .also { it -> it.complies({ "Shape does not match. Expected ${shapeToString()}, found ${other.shapeToString()}" }, { it.shape.contentEquals(this.shape) }) }

    override fun copyOf(): ShortMatrix = ShortMatrix(shape, data.copyOf().toShortArray())

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

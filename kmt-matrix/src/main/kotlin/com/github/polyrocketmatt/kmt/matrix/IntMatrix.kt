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

typealias IMatrix = IntMatrix

/**
 * Get a matrix with the given shape from the given array.
 *
 * @param shape The shape of the matrix
 * @return A matrix with the given shape and the given array as its data
 * @throws IllegalStateException If the array does not comply with the given shape
 */
fun Array<Int>.toMatrix(shape: IntArray): IntMatrix {
    val elements = shape.reduce { acc, i -> acc * i }
    shape.complies(
        {
            "Incorrect array size for shape ${shape.joinToString("x") { "$it" }}. " +
                "Expected $elements, found ${this.size}"
        },
        { this.size == elements }
    )
    return IntMatrix(shape, this.toIntArray())
}

/**
 * Get a matrix with the given shape from the given array.
 *
 * @param shape The shape of the matrix
 * @return A matrix with the given shape and the given array as its data
 * @throws IllegalStateException If the array does not comply with the given shape
 */
fun IntArray.toMatrix(shape: IntArray): IntMatrix {
    val elements = shape.reduce { acc, i -> acc * i }
    shape.complies(
        {
            "Incorrect array size for shape ${shape.joinToString("x") { "$it" }}. " +
                "Expected $elements, found ${this.size}"
        },
        { this.size == elements }
    )
    return IntMatrix(shape, this)
}

/**
 * Get an array of floating-point numbers from the given matrix.
 *
 * @return The array of floating-point numbers with data from the given matrix
 */
fun IntMatrix.toArray(): IntArray = this.data.toIntArray()

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
open class IntMatrix(
    val shape: IntArray,
    matrix: IntArray
) : Tuple<Int>(IntArray(shape.reduce { acc, i -> acc * i }).toTypedArray()),
    NumericMatrix<Int, Double> {

    companion object {
        fun identity(shape: IntArray): IntMatrix {
            shape.complies("Identity matrix only exists for dimension 2") { it.size == 2 }

            val matrix = IntMatrix(shape)
            val min = shape.min()

            for (i in 0 until min)
                matrix[i * shape[1] + i] = 1

            return matrix
        }
    }

    constructor(matrix: IntArray) : this(intArrayOf(matrix.size), matrix)
    constructor(shape: IntArray, isShape: Boolean = true) : this(shape, IntArray(shape.reduce { acc, i -> acc * i }) { 0 })
    constructor(shape: IntArray, value: Int) : this(shape, IntArray(shape.reduce { acc, i -> acc * i }) { value })
    constructor(matrix: Array<Int>) : this(matrix.toIntArray())
    constructor(shape: IntArray, matrix: Array<Int>) : this(shape, matrix.toIntArray())

    init {
        val shapeSize = shape.reduce { acc, i -> acc * i }

        complies("Data must contain $shapeSize elements for a matrix of size ${shape.joinToString("x") { "$it" }}") { data.size == size }

        matrix.forEachIndexed { i, value -> data[i] = value }
    }

    open fun rows(): Array<IntArray> {
        val rows = Array(shape[0]) { IntArray(shape[1]) }
        for (j in 0 until shape[1])
            for (i in 0 until shape[0])
                rows[i][j] = this[i, j]
        return rows
    }

    open fun columns(): Array<IntArray> {
        val columns = Array(shape[1]) { IntArray(shape[0]) }
        for (i in 0 until shape[0])
            for (j in 0 until shape[1])
                columns[j][i] = this[i, j]
        return columns
    }

    open fun row(idx: Int): IntArray {
        complies("Index $idx is out of bounds for ${shape[0]} rows") { idx in 0 until shape[0] }

        val row = IntArray(shape[1])
        for (j in 0 until shape[1])
            row[j] = this[idx, j]
        return row
    }

    open fun column(idx: Int): IntArray {
        complies("Index $idx is out of bounds for ${shape[1]} columns") { idx in 0 until shape[1] }

        val column = IntArray(shape[0])
        for (i in 0 until shape[0])
            column[i] = this[i, idx]
        return column
    }

    override fun shape(): IntArray = shape

    override operator fun get(i: Int): Int = data[i]

    override operator fun get(row: Int, col: Int): Int = data[row * shape[1] + col]

    override operator fun set(i: Int, value: Int) { data[i] = value }

    override operator fun set(row: Int, col: Int, value: Int) { data[row * shape[1] + col] = value }

    /**
     * Element-wise addition of this matrix and the given matrix.
     *
     * @param other The matrix to add to this matrix
     * @return The sum of this matrix and the given matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun plus(other: IntMatrix): IntMatrix {
        isCompliantMatrix(other)
        val matrix = IntMatrix(shape)
        data.forEachIndexed { i, value -> matrix[i] = value + other[i] }
        return matrix
    }

    override operator fun plus(other: Matrix<Int>): IntMatrix = plus(other as IntMatrix)

    /**
     * Element-wise subtraction of this matrix and the given matrix.
     *
     * @param other The matrix to subtract from this matrix
     * @return The difference of this matrix and the given matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun minus(other: IntMatrix): IntMatrix {
        isCompliantMatrix(other)
        val matrix = IntMatrix(shape)
        data.forEachIndexed { i, value -> matrix[i] = value - other[i] }
        return matrix
    }

    override operator fun minus(other: Matrix<Int>): IntMatrix = minus(other as IntMatrix)

    /**
     * Element-wise multiplication of this matrix and the given matrix.
     *
     * @param other The matrix to multiply with this matrix
     * @return The product of this matrix and the given matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun times(other: IntMatrix): IntMatrix {
        isCompliantMatrix(other)
        val matrix = IntMatrix(shape)
        data.forEachIndexed { i, value -> matrix[i] = value * other[i] }
        return matrix
    }

    override operator fun times(other: Matrix<Int>): IntMatrix = times(other as IntMatrix)

    /**
     * Element-wise division of this matrix and the given matrix.
     *
     * @param other The matrix to divide this matrix with
     * @return The quotient of this matrix and the given matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun div(other: IntMatrix): IntMatrix {
        isCompliantMatrix(other)
        val matrix = IntMatrix(shape)
        data.forEachIndexed { i, value -> matrix[i] = value / other[i] }
        return matrix
    }

    override operator fun div(other: Matrix<Int>): IntMatrix = div(other as IntMatrix)

    /**
     * Element-wise addition of this matrix and the given matrix.
     *
     * @param other The matrix to add to this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun plusAssign(other: IntMatrix) {
        isCompliantMatrix(other)
        data.forEachIndexed { i, term -> data[i] = data[i] + term }
    }

    override operator fun plusAssign(other: Matrix<Int>) = plusAssign(other as IntMatrix)

    /**
     * Element-wise subtraction of this matrix and the given matrix.
     *
     * @param other The matrix to subtract from this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun minusAssign(other: IntMatrix) {
        isCompliantMatrix(other)
        data.forEachIndexed { i, term -> data[i] = data[i] - term }
    }

    override operator fun minusAssign(other: Matrix<Int>) = minusAssign(other as IntMatrix)

    /**
     * Element-wise multiplication of this matrix and the given matrix.
     *
     * @param other The matrix to multiply to this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun timesAssign(other: IntMatrix) {
        isCompliantMatrix(other)
        data.forEachIndexed { i, factor -> data[i] = data[i] * factor }
    }

    override operator fun timesAssign(other: Matrix<Int>) = timesAssign(other as IntMatrix)

    /**
     * Element-wise division of this matrix and the given matrix.
     *
     * @param other The matrix to divide to this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun divAssign(other: IntMatrix) {
        isCompliantMatrix(other)
        data.forEachIndexed { i, factor -> data[i] = data[i] / factor }
    }

    override operator fun divAssign(other: Matrix<Int>) = divAssign(other as IntMatrix)

    override operator fun plus(value: Int): IntMatrix {
        val matrix = IntMatrix(shape)
        data.forEachIndexed { i, term -> matrix[i] = data[i] + term }
        return matrix
    }

    override operator fun minus(value: Int): IntMatrix {
        val matrix = IntMatrix(shape)
        data.forEachIndexed { i, term -> matrix[i] = data[i] - term }
        return matrix
    }

    override operator fun times(value: Int): IntMatrix {
        val matrix = IntMatrix(shape)
        data.forEachIndexed { i, factor -> matrix[i] = data[i] * factor }
        return matrix
    }

    override operator fun div(value: Int): IntMatrix {
        val matrix = IntMatrix(shape)
        data.forEachIndexed { i, factor -> matrix[i] = data[i] / factor }
        return matrix
    }

    override operator fun plusAssign(value: Int) = data.forEachIndexed { i, term -> data[i] = data[i] + term }

    override operator fun minusAssign(value: Int) = data.forEachIndexed { i, term -> data[i] = data[i] - term }

    override operator fun timesAssign(value: Int) = data.forEachIndexed { i, factor -> data[i] = data[i] * factor }

    override operator fun divAssign(value: Int) = data.forEachIndexed { i, factor -> data[i] = data[i] / factor }

    override operator fun unaryMinus(): Matrix<Int> = times(-1)

    /**
     * Multiply this matrix with the given matrix. The matrices must have
     * a valid shape for multiplication and must be of dimension 2.
     *
     * @param other The matrix to multiply with this matrix
     * @return The result of the multiplication
     * @throws IllegalArgumentException If the matrices are not of dimension 2
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open infix fun mult(other: IntMatrix): IntMatrix {
        other.complies("Cannot multiply matrices of sizes ${shapeToString()} and ${other.shapeToString()}") { it.shape[0] == shape[1] }

        //  Multiplying rows of first matrix with columns of second matrix
        val result = IntMatrix(intArrayOf(shape[0], other.shape[1]))
        val r1 = shape[0]
        val c1 = shape[1]
        val c = other.shape[1]
        for (i in 0 until r1)
            for (j in 0 until c)
                for (k in 0 until c1)
                    result.data[i * c + j] += data[i * r1 + k] * other.data[k * c + j]
        return result
    }

    override fun transpose(): IntMatrix {
        val matrix = IntMatrix(intArrayOf(shape[1], shape[0]))
        for (i in 0 until shape[0])
            for (j in 0 until shape[1])
                matrix[j, i] = this[i, j]
        return matrix
    }

    override fun trace(): Int = diag().sum()

    override fun diag(): IntMatrix {
        val diag = IntMatrix(intArrayOf(1, shape[1]))
        for (i in 0 until shape[1])
            diag[i] = this[i, i]
        return diag
    }

    override infix fun concatHorizontal(other: Matrix<Int>): IntMatrix {
        val matrix = (other.complies("Other matrix must be of type IntMatrix") { other is IntMatrix } as IntMatrix)
            .complies({ "Rows must match to concatenate horizontally. Expected ${shape[0]}, found ${it.shape[0]}" }, { it.shape[0] == shape[0] })
        val offset = shape[0]
        val result = IntMatrix(intArrayOf(offset, shape[1] + matrix.shape[1]))
        for (j in 0 until shape[1]) for (i in 0 until offset)
            result[i * result.shape[1] + j] = this[i, j]
        for (j in 0 until shape[1]) for (i in 0 until offset)
            result[offset + i * result.shape[1] + j] = matrix[i, j]
        return result
    }
    override infix fun concatVertical(other: Matrix<Int>): IntMatrix {
        val matrix = (other.complies("Other matrix must be of type IntMatrix") { other is IntMatrix } as IntMatrix)
            .complies({ "Columns must match to concatenate vertically. Expected ${shape[1]}, found ${it.shape[1]}" }, { it.shape[1] == shape[1] })
        val offset = shape[1]
        val indexOffset = shape[0] * offset
        val result = IntMatrix(intArrayOf(shape[0] + matrix.shape[0], offset))
        for (i in 0 until indexOffset)
            result[i] = this[i]
        for (i in 0 until matrix.shape[0] * offset)
            result[indexOffset + i] = matrix[i]
        return result
    }

    override fun isScalar(): Boolean = data.size == 1

    override fun isSquare(): Boolean = shape[0] == shape[1]

    override fun isOrthogonal(): Boolean {
        TODO("Not yet implemented")
    }

    override fun swapRow(row1: Int, row2: Int) {
        val rowIndex1 = row1 * shape[1]
        val rowIndex2 = row2 * shape[1]
        val tmp = data.copyOfRange(rowIndex1, rowIndex1 + shape[1])

        data.copyInto(data, rowIndex1, rowIndex2, rowIndex2 + shape[1])
        tmp.copyInto(data, rowIndex2, 0, shape[1])
    }

    override fun multiplyRow(row: Int, scalar: Int) {
        val rowIndex = row * shape[1]
        for (i in 0 until shape[1])
            data[rowIndex + i] *= scalar
    }

    override fun addRow(row1: Int, row2: Int, scalar: Int) {
        val rowIndex1 = row1 * shape[1]
        val rowIndex2 = row2 * shape[1]
        for (i in 0 until shape[1])
            data[i + rowIndex1] += (data[i + rowIndex2] * scalar)
    }

    override fun operate(operations: List<ElementaryOperation<Double>>): DoubleMatrix = toDoubleMatrix().operate(operations)

    override fun ref(): DoubleMatrix = toDoubleMatrix().ref()

    override fun rref(): DoubleMatrix = toDoubleMatrix().rref()

    override fun solve(): Tuple<Double> = toDoubleMatrix().solve()

    override fun luDecomposition(): Pair<DoubleMatrix, DoubleMatrix> = toDoubleMatrix().luDecomposition()

    override fun qrDecomposition(method: QRFactorizationMethod): Pair<DoubleMatrix, DoubleMatrix> = toDoubleMatrix().qrDecomposition(method)

    override fun determinant(): Double = ref().diag().reduce { acc, i -> acc * i }

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

    override fun norm(type: NormType): Double = toDoubleMatrix().norm(type)

    override fun eigenvalues(): Array<Double> {
        TODO("Not yet implemented")
    }

    override fun eigenvectors(): Array<Tuple<Double>> {
        TODO("Not yet implemented")
    }

    fun toDoubleMatrix(): DoubleMatrix = DoubleMatrix(shape, data.map { it.toDouble() }.toDoubleArray())
    fun toFloatMatrix(): FloatMatrix = FloatMatrix(shape, data.map { it.toFloat() }.toFloatArray())
    fun toShortMatrix(): ShortMatrix = ShortMatrix(shape, data.map { it.toShort() }.toShortArray())

    internal fun shapeToString(): String = shape.joinToString("x") { "$it" }

    internal fun isCompliantMatrix(other: IntMatrix) =
        other
            .complies({ "Other is of type ${it::class.java}, expected ${this::class.java}" }, { it::class.java == this::class.java })
            .also { it -> it.complies({ "Shape does not match. Expected ${shapeToString()}, found ${other.shapeToString()}" }, { it.shape.contentEquals(this.shape) }) }

    override fun copyOf(): IntMatrix = IntMatrix(shape, data.copyOf().toIntArray())

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

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

import com.github.polyrocketmatt.kmt.common.decimals
import com.github.polyrocketmatt.kmt.common.fastAbs
import com.github.polyrocketmatt.kmt.common.storage.Tuple
import com.github.polyrocketmatt.kmt.common.utils.complies
import com.github.polyrocketmatt.kmt.common.utils.indexByCondition
import java.util.Stack
import kotlin.IllegalArgumentException
import kotlin.math.min

typealias FMatrix = FloatMatrix

/**
 * Get a matrix with the given shape from the given array.
 *
 * @param shape The shape of the matrix
 * @return A matrix with the given shape and the given array as its data
 * @throws IllegalStateException If the array does not comply with the given shape
 */
fun Array<Float>.toMatrix(shape: IntArray): FloatMatrix {
    val elements = shape.reduce { acc, i -> acc * i }
    shape.complies(
        {
            "Incorrect array size for shape ${shape.joinToString("x") { "$it" }}. " +
                "Expected $elements, found ${this.size}"
        },
        { this.size == elements }
    )
    return FloatMatrix(shape, this.toFloatArray())
}

/**
 * Get a matrix with the given shape from the given array.
 *
 * @param shape The shape of the matrix
 * @return A matrix with the given shape and the given array as its data
 * @throws IllegalStateException If the array does not comply with the given shape
 */
fun FloatArray.toMatrix(shape: IntArray): FloatMatrix {
    val elements = shape.reduce { acc, i -> acc * i }
    shape.complies(
        {
            "Incorrect array size for shape ${shape.joinToString("x") { "$it" }}. " +
                "Expected $elements, found ${this.size}"
        },
        { this.size == elements }
    )
    return FloatMatrix(shape, this)
}

/**
 * Get an array of floating-point numbers from the given matrix.
 *
 * @return The array of floating-point numbers with data from the given matrix
 */
fun FloatMatrix.toArray(): FloatArray = this.data.toFloatArray()

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
open class FloatMatrix(
    val shape: IntArray,
    matrix: FloatArray
) : Tuple<Float>(FloatArray(shape.reduce { acc, i -> acc * i }).toTypedArray()),
    NumericMatrix<Float, Float> {

    companion object {
        fun identity(shape: IntArray): FloatMatrix {
            shape.complies("Identity matrix only exists for dimension 2") { it.size == 2 }

            val matrix = FloatMatrix(shape)
            val min = shape.min()

            for (i in 0 until min)
                matrix[i * shape[1] + i] = 1f

            return matrix
        }
    }

    private val operations = Stack<ElementaryOperation<Float>>()

    constructor(matrix: FloatArray) : this(intArrayOf(matrix.size), matrix)
    constructor(shape: IntArray) : this(shape, FloatArray(shape.reduce { acc, i -> acc * i }) { 0.0f })
    constructor(shape: IntArray, value: Float) : this(shape, FloatArray(shape.reduce { acc, i -> acc * i }) { value })
    constructor(matrix: Array<Float>) : this(matrix.toFloatArray())
    constructor(shape: IntArray, matrix: Array<Float>) : this(shape, matrix.toFloatArray())

    init {
        val shapeSize = shape.reduce { acc, i -> acc * i }

        complies("Data must contain $shapeSize elements for a matrix of size ${shape.joinToString("x") { "$it" }}") { data.size == size }

        matrix.forEachIndexed { i, value -> data[i] = value }
    }

    open fun rows(): Array<FloatArray> {
        val rows = Array(shape[0]) { FloatArray(shape[1]) }
        for (j in 0 until shape[1])
            for (i in 0 until shape[0])
                rows[i][j] = this[i, j]
        return rows
    }

    open fun columns(): Array<FloatArray> {
        val columns = Array(shape[1]) { FloatArray(shape[0]) }
        for (i in 0 until shape[0])
            for (j in 0 until shape[1])
                columns[j][i] = this[i, j]
        return columns
    }

    open fun row(idx: Int): FloatArray {
        complies("Index $idx is out of bounds for ${shape[0]} rows") { idx in 0 until shape[0] }

        val row = FloatArray(shape[1])
        for (j in 0 until shape[1])
            row[j] = this[idx, j]
        return row
    }

    open fun column(idx: Int): FloatArray {
        complies("Index $idx is out of bounds for ${shape[1]} columns") { idx in 0 until shape[1] }

        val column = FloatArray(shape[0])
        for (i in 0 until shape[0])
            column[i] = this[i, idx]
        return column
    }

    override operator fun get(i: Int): Float = data[i]
    override operator fun get(row: Int, col: Int): Float = data[row * shape[1] + col]

    override operator fun set(i: Int, value: Float) { data[i] = value }
    override operator fun set(row: Int, col: Int, value: Float) { data[row * shape[1] + col] = value }

    /**
     * Element-wise addition of this matrix and the given matrix.
     *
     * @param other The matrix to add to this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun plus(other: FloatMatrix): FloatMatrix {
        isCompliantMatrix(other)
        val matrix = FloatMatrix(shape)
        data.forEachIndexed { i, value -> matrix[i] = value + other[i] }
        return matrix
    }

    /**
     * Element-wise subtraction of this matrix and the given matrix.
     *
     * @param other The matrix to subtract from this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun minus(other: FloatMatrix): FloatMatrix {
        isCompliantMatrix(other)
        val matrix = FloatMatrix(shape)
        data.forEachIndexed { i, value -> matrix[i] = value - other[i] }
        return matrix
    }

    /**
     * Element-wise multiplication of this matrix and the given matrix.
     *
     * @param other The matrix to multiply with this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun times(other: FloatMatrix): FloatMatrix {
        isCompliantMatrix(other)
        val matrix = FloatMatrix(shape)
        data.forEachIndexed { i, value -> matrix[i] = value * other[i] }
        return matrix
    }

    /**
     * Element-wise division of this matrix and the given matrix.
     *
     * @param other The matrix to divide this matrix with
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun div(other: FloatMatrix): FloatMatrix {
        isCompliantMatrix(other)
        val matrix = FloatMatrix(shape)
        data.forEachIndexed { i, value -> matrix[i] = value / other[i] }
        return matrix
    }

    /**
     * Element-wise addition of this matrix and the given matrix.
     *
     * @param other The matrix to add to this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun plusAssign(other: FloatMatrix) {
        isCompliantMatrix(other)
        data.forEachIndexed { i, term -> data[i] = data[i] + term }
    }

    /**
     * Element-wise subtraction of this matrix and the given matrix.
     *
     * @param other The matrix to subtract from this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun minusAssign(other: FloatMatrix) {
        isCompliantMatrix(other)
        data.forEachIndexed { i, term -> data[i] = data[i] - term }
    }

    /**
     * Element-wise multiplication of this matrix and the given matrix.
     *
     * @param other The matrix to multiply to this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun timesAssign(other: FloatMatrix) {
        isCompliantMatrix(other)
        data.forEachIndexed { i, factor -> data[i] = data[i] * factor }
    }

    /**
     * Element-wise division of this matrix and the given matrix.
     *
     * @param other The matrix to divide to this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun divAssign(other: FloatMatrix) {
        isCompliantMatrix(other)
        data.forEachIndexed { i, factor -> data[i] = data[i] / factor }
    }

    /**
     * Scalar addition of this matrix and the given value.
     *
     * @param value The value to add to this matrix
     */
    override fun plus(value: Float): FloatMatrix {
        val matrix = FloatMatrix(shape)
        data.forEachIndexed { i, term -> matrix[i] = data[i] + term }
        return matrix
    }

    /**
     * Scalar subtraction of this matrix and the given value.
     *
     * @param value The value to subtract from this matrix
     */
    override fun minus(value: Float): FloatMatrix {
        val matrix = FloatMatrix(shape)
        data.forEachIndexed { i, term -> matrix[i] = data[i] - term }
        return matrix
    }

    /**
     * Scalar multiplication of this matrix and the given value.
     *
     * @param value The value to multiply to this matrix
     */
    override fun times(value: Float): FloatMatrix {
        val matrix = FloatMatrix(shape)
        data.forEachIndexed { i, factor -> matrix[i] = data[i] * factor }
        return matrix
    }

    /**
     * Scalar division of this matrix and the given value.
     *
     * @param value The value to divide with this matrix
     */
    override fun div(value: Float): FloatMatrix {
        val matrix = FloatMatrix(shape)
        data.forEachIndexed { i, factor -> matrix[i] = data[i] / factor }
        return matrix
    }

    /**
     * Scalar addition of this matrix and the given value.
     *
     * @param value The value to add to this matrix
     */
    override fun plusAssign(value: Float) = data.forEachIndexed { i, term -> data[i] = data[i] + term }

    /**
     * Scalar subtraction of this matrix and the given value.
     *
     * @param value The value to subtract from this matrix
     */
    override fun minusAssign(value: Float) = data.forEachIndexed { i, term -> data[i] = data[i] - term }

    /**
     * Scalar multiplication of this matrix and the given value.
     *
     * @param value The value to multiply to this matrix
     */
    override fun timesAssign(value: Float) = data.forEachIndexed { i, factor -> data[i] = data[i] * factor }

    /**
     * Scalar division of this matrix and the given value.
     *
     * @param value The value to divide with this matrix
     */
    override fun divAssign(value: Float) = data.forEachIndexed { i, factor -> data[i] = data[i] / factor }

    /**
     * Multiply this matrix with the given matrix. The matrices must have
     * a valid shape for multiplication and must be of dimension 2.
     *
     * @param other The matrix to multiply with this matrix
     * @return The result of the multiplication
     * @throws IllegalArgumentException If the matrices are not of dimension 2
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open infix fun mult(other: FloatMatrix): FloatMatrix {
        other.complies("Cannot multiply matrices of sizes ${shapeToString()} and ${other.shapeToString()}") { it.shape[0] == shape[1] }

        //  Multiplying rows of first matrix with columns of second matrix
        val result = FloatMatrix(intArrayOf(shape[0], other.shape[1]))
        val r1 = shape[0]
        val c1 = shape[1]
        val c = other.shape[1]
        for (i in 0 until r1)
            for (j in 0 until c)
                for (k in 0 until c1)
                    result.data[i * c + j] += data[i * r1 + k] * other.data[k * c + j]
        return result
    }

    override fun transpose(): FloatMatrix {
        val matrix = FloatMatrix(intArrayOf(shape[1], shape[0]))
        for (i in 0 until shape[0])
            for (j in 0 until shape[1])
                matrix[j, i] = this[i, j]
        return matrix
    }

    override fun trace(): Float = diag().sum()

    override fun diag(): FloatMatrix {
        val diag = FloatMatrix(intArrayOf(1, shape[1]))
        for (i in 0 until shape[1])
            diag[i] = this[i, i]
        return diag
    }

    override infix fun concatHorizontal(other: Matrix<Float>): FloatMatrix {
        val matrix = (other.complies("Other matrix must be of type FloatMatrix") { other is FloatMatrix } as FloatMatrix)
            .complies({ "Rows must match to concatenate horizontally. Expected ${shape[0]}, found ${it.shape[0]}" }, { it.shape[0] == shape[0] })
        val offset = shape[0]
        val result = FloatMatrix(intArrayOf(offset, shape[1] + matrix.shape[1]))
        for (j in 0 until shape[1]) for (i in 0 until offset)
            result[i * result.shape[1] + j] = this[i, j]
        for (j in 0 until shape[1]) for (i in 0 until offset)
            result[offset + i * result.shape[1] + j] = matrix[i, j]
        return result
    }

    override infix fun concatVertical(other: Matrix<Float>): FloatMatrix {
        val matrix = (other.complies("Other matrix must be of type FloatMatrix") { other is FloatMatrix } as FloatMatrix)
            .complies({ "Columns must match to concatenate vertically. Expected ${shape[1]}, found ${it.shape[1]}" }, { it.shape[1] == shape[1] })
        val offset = shape[1]
        val indexOffset = shape[0] * offset
        val result = FloatMatrix(intArrayOf(shape[0] + matrix.shape[0], offset))
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

    override fun multiplyRow(row: Int, scalar: Float) {
        val rowIndex = row * shape[1]
        for (i in 0 until shape[1])
            data[rowIndex + i] *= scalar
    }
    override fun addRow(row1: Int, row2: Int, scalar: Float) {
        val rowIndex1 = row1 * shape[1]
        val rowIndex2 = row2 * shape[1]
        for (i in 0 until shape[1])
            data[i + rowIndex1] += (data[i + rowIndex2] * scalar)
    }

    override fun operate(operations: List<ElementaryOperation<Float>>): FloatMatrix {
        val result = copyOf()
        operations.forEach {
            when (it.type) {
                ERO.SWAP -> result.swapRow(it.row1, it.row2)
                ERO.MULTIPLY -> result.multiplyRow(it.row1, it.scalar)
                ERO.ADD -> result.addRow(it.row1, it.row2, it.scalar)
            }
        }

        result.clean()
        return result
    }

    override fun ref(): FloatMatrix {
        val result = copyOf()
        result.operations.clear()

        var currentRow = 0
        for ((currentColumn, _) in (0 until min(shape[0], shape[1])).withIndex()) {
            //  Find index of value with the highest absolute value in current column
            val col = result.column(currentColumn).copyOfRange(currentColumn, shape[0])
            val pivotIndex = col.indexByCondition(Float.MIN_VALUE) { _, current, _, value -> current.fastAbs() < value.fastAbs() }
            val pivot = col[pivotIndex]

            //  The index also gives the row to swap to the top
            result.swapRow(currentRow, pivotIndex + currentColumn)
            result.operations.push(ElementaryOperation(ERO.SWAP, currentRow, pivotIndex + currentColumn, 0.0f))

            //  Update the current row
            currentRow++

            //  Gaussian elimination step
            for (i in currentRow until shape[0]) {
                //  Solve for k, the scalar to multiply the pivot row with to get 0 in the current column
                val k = -result.column(currentColumn)[i] / pivot

                //  Add the pivot row multiplied with k to the current row (which is the index of the current column)
                result.addRow(i, currentColumn, k)
                result.operations.push(ElementaryOperation(ERO.ADD, i, currentColumn, k))
            }
        }

        result.clean()
        return result
    }

    override fun rref(): FloatMatrix {
        //  We first bring the matrix in row echelon form
        val result = ref()
        result.clean()

        for (i in shape[0] - 1 downTo 0) {
            //  Find the row with a 1.0 in the current column
            val col = result.row(i)
            val columnIndex = col.indexOfFirst { it != 0.0f }

            //  Found 0-row
            if (columnIndex == -1)
                continue

            //  Make sure there is a 1.0 in the current column
            if (col[columnIndex] != 1.0f) {
                result.multiplyRow(i, 1.0f / col[columnIndex])
                result.operations.push(ElementaryOperation(ERO.MULTIPLY, i, 0, 1.0f / col[columnIndex]))
            }

            //  For all rows above, make a 0 in the current column
            for (j in i - 1 downTo 0) {
                val k = -result.column(columnIndex)[j]

                result.addRow(j, i, k)
                result.operations.push(ElementaryOperation(ERO.ADD, j, i, k))
            }
        }

        result.clean()
        return result
    }

    override fun determinant(): Float = ref().diag().reduce { acc, d -> acc * d }

    override fun isInvertible(): Boolean = determinant() != 0.0f

    override fun inverse(): FloatMatrix {
        complies("Non-square matrix does not have an inverse") { isSquare() }
        complies("Matrix does not have an inverse, since the determinant is 0") { isInvertible() }

        val offset = shape[0]
        val augmented = (copyOf() concatHorizontal identity(intArrayOf(offset, offset)))
        val operations = rref().operations
        val rref = augmented.operate(operations)

        //  Extract the right half of the matrix
        val inverse = FloatMatrix(intArrayOf(offset, offset))
        for (j in 0 until shape[1]) for (i in 0 until offset)
            inverse[i * inverse.shape[1] + j] = rref[i, j + offset]
        return inverse
    }

    override fun rank(): Int {
        //  The rank of a matrix is the dimension of the column space.
        //  To find the dimension of the column space we reduce the matrix to reduced row echelon form
        //  and count the number of rows that are not all 0.
        val rref = rref()
        var rank = 0
        for (i in 0 until rref.shape[0]) {
            val row = rref.row(i)
            if (row.any { it != 0.0f })
                rank++
        }

        return rank
    }

    override fun nullity(): Int = shape[1] - rank()

    override fun linearlyIndependentRows(): Boolean {
        TODO("Not yet implemented")
    }

    override fun linearlyIndependentColumns(): Boolean {
        TODO("Not yet implemented")
    }

    open fun toDoubleMatrix(): DoubleMatrix = DoubleMatrix(shape, data.map { it.toDouble() }.toDoubleArray())
    open fun toIntMatrix(): IntMatrix = IntMatrix(shape, data.map { it.toInt() }.toIntArray())
    open fun toShortMatrix(): ShortMatrix = ShortMatrix(shape, data.map { it.toInt().toShort() }.toShortArray())

    internal fun shapeToString(): String = shape.joinToString("x") { "$it" }

    internal fun isCompliantMatrix(other: FloatMatrix) =
        other
            .complies({ "Other is of type ${it::class.java}, expected ${this::class.java}" }, { it::class.java == this::class.java })
            .also { it -> it.complies({ "Shape does not match. Expected ${shapeToString()}, found ${other.shapeToString()}" }, { it.shape.contentEquals(this.shape) }) }

    internal fun clean() {
        //  Decide if to clean up tiny values
        data.forEachIndexed { i, value -> if (value < 1e12) data[i] = value.decimals(12) }
    }

    override fun copyOf(): FloatMatrix = FloatMatrix(shape, data.copyOf().toFloatArray())

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

package com.github.polyrocketmatt.kmt.matrix

import com.github.polyrocketmatt.kmt.common.storage.Tuple
import com.github.polyrocketmatt.kmt.common.utils.complies
import kotlin.IllegalArgumentException

typealias IMatrix = IntMatrix
typealias I2x2 = Int2x2
typealias I3x3 = Int3x3
typealias I4x4 = Int4x4

/**
 * Get a matrix with the given shape from the given array.
 *
 * @param shape The shape of the matrix
 * @return A matrix with the given shape and the given array as its data
 * @throws IllegalStateException If the array does not comply with the given shape
 */
fun Array<Int>.toMatrix(shape: IntArray): IntMatrix {
    val elements = shape.reduce { acc, i -> acc * i }
    shape.complies({ "Incorrect array size for shape ${shape.joinToString("x") { "$it" }}. " +
            "Expected ${elements}, found ${this.size}" },
        { this.size == elements })
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
    shape.complies({ "Incorrect array size for shape ${shape.joinToString("x") { "$it" }}. " +
            "Expected ${elements}, found ${this.size}" },
        { this.size == elements })
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
) : Tuple<Int>(IntArray(shape.reduce { acc, i -> acc * i  }).toTypedArray()),
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

    override operator fun get(i: Int): Int = data[i]
    override operator fun get(row: Int, col: Int): Int = data[row * shape[1] + col]

    override operator fun set(i: Int, value: Int) { data[i] = value }
    override operator fun set(row: Int, col: Int, value: Int) { data[row * shape[1] + col] = value }

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

    /**
     * Scalar addition of this matrix and the given value.
     *
     * @param value The value to add to this matrix
     */
    override fun plus(value: Int): IntMatrix {
        val matrix = IntMatrix(shape)
        data.forEachIndexed { i, term -> matrix[i] = data[i] + term }
        return matrix
    }

    /**
     * Scalar subtraction of this matrix and the given value.
     *
     * @param value The value to subtract from this matrix
     */
    override fun minus(value: Int): IntMatrix {
        val matrix = IntMatrix(shape)
        data.forEachIndexed { i, term -> matrix[i] = data[i] - term }
        return matrix
    }

    /**
     * Scalar multiplication of this matrix and the given value.
     *
     * @param value The value to multiply to this matrix
     */
    override fun times(value: Int): IntMatrix {
        val matrix = IntMatrix(shape)
        data.forEachIndexed { i, factor -> matrix[i] = data[i] * factor }
        return matrix
    }

    /**
     * Scalar division of this matrix and the given value.
     *
     * @param value The value to divide with this matrix
     */
    override fun div(value: Int): IntMatrix {
        val matrix = IntMatrix(shape)
        data.forEachIndexed { i, factor -> matrix[i] = data[i] / factor }
        return matrix
    }

    /**
     * Scalar addition of this matrix and the given value.
     *
     * @param value The value to add to this matrix
     */
    override fun plusAssign(value: Int) = data.forEachIndexed { i, term -> data[i] = data[i] + term }

    /**
     * Scalar subtraction of this matrix and the given value.
     *
     * @param value The value to subtract from this matrix
     */
    override fun minusAssign(value: Int) = data.forEachIndexed { i, term -> data[i] = data[i] - term }

    /**
     * Scalar multiplication of this matrix and the given value.
     *
     * @param value The value to multiply to this matrix
     */
    override fun timesAssign(value: Int) = data.forEachIndexed { i, factor -> data[i] = data[i] * factor }

    /**
     * Scalar division of this matrix and the given value.
     *
     * @param value The value to divide with this matrix
     */
    override fun divAssign(value: Int) = data.forEachIndexed { i, factor -> data[i] = data[i] / factor }

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

    open override fun transpose(): IntMatrix {
        val matrix = IntMatrix(intArrayOf(shape[1], shape[0]))
        for (i in 0 until shape[0])
            for (j in 0 until shape[1])
                matrix[j, i] = this[i, j]
        return matrix
    }

    override fun swapRow(row1: Int, row2: Int): DoubleMatrix {
        TODO("Not yet implemented")
    }
    override fun multiplyRow(row: Int, scalar: Int): DoubleMatrix {
        TODO("Not yet implemented")
    }
    override fun addRow(row1: Int, row2: Int, scalar: Int): DoubleMatrix {
        TODO("Not yet implemented")
    }

    override fun ref(): DoubleMatrix = toDoubleMatrix().ref()
    override fun rref(): DoubleMatrix = toDoubleMatrix().rref()

    fun isScalar(): Boolean = data.size == 1
    fun isSquare(): Boolean = shape[0] == shape[1]

    fun toDoubleMatrix(): DoubleMatrix = DoubleMatrix(shape, data.map { it.toDouble() }.toDoubleArray())
    fun toFloatMatrix(): FloatMatrix = FloatMatrix(shape, data.map { it.toFloat() }.toFloatArray())
    fun toShortMatrix(): ShortMatrix = ShortMatrix(shape, data.map { it.toShort() }.toShortArray())

    internal fun shapeToString(): String = shape.joinToString("x") { "$it" }

    internal fun isCompliantMatrix(other: IntMatrix) =
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

/**
 * Represents a 2x2 matrix of a given dimension and shape holding
 * floating-point values.
 *
 * @param matrix The matrix data
 */
class Int2x2(matrix: IntArray) : IntMatrix(intArrayOf(2, 2), matrix) {

    companion object {
        val IDENTITY = Int2x2(intArrayOf(1, 0, 0, 1))
    }

    constructor() : this(IntArray(4) { 0 })
    constructor(value: Int) : this(IntArray(4) { value })

    override fun rows(): Array<IntArray> = arrayOf(
        intArrayOf(data[0], data[1]),
        intArrayOf(data[2], data[3])
    )

    override fun columns(): Array<IntArray> = arrayOf(
        intArrayOf(data[0], data[2]),
        intArrayOf(data[1], data[3])
    )

    init {
        complies("Data must contain 4 elements for a matrix of size 2x2") { data.size == 4 }
        data.forEachIndexed { i, value -> data[i] = value }
    }

    override fun transpose(): Int2x2 = Int2x2(intArrayOf(
        data[0], data[2],
        data[1], data[3]
    ))

}

/**
 * Represents a 3x3 matrix of a given dimension and shape holding
 * floating-point values.
 *
 * @param matrix The matrix data
 */
class Int3x3(matrix: IntArray) : IntMatrix(intArrayOf(3, 3), matrix) {

    companion object {
        val IDENTITY = Int3x3(intArrayOf(
            1, 0, 0,
            0, 1, 0,
            0, 0, 1
        ))
    }

    constructor() : this(IntArray(9) { 0 })
    constructor(value: Int) : this(IntArray(9) { value })

    override fun rows(): Array<IntArray> = arrayOf(
        intArrayOf(data[0], data[1], data[2]),
        intArrayOf(data[3], data[4], data[5]),
        intArrayOf(data[6], data[7], data[8])
    )

    override fun columns(): Array<IntArray> = arrayOf(
        intArrayOf(data[0], data[3], data[6]),
        intArrayOf(data[1], data[4], data[7]),
        intArrayOf(data[2], data[5], data[8])
    )

    init {
        complies("Data must contain 9 elements for a matrix of size 3x3") { data.size == 9 }
        matrix.forEachIndexed { i, value -> data[i] = value }
    }

    override fun transpose(): Int3x3 = Int3x3(intArrayOf(
        data[0], data[3], data[6],
        data[1], data[4], data[7],
        data[2], data[5], data[8]
    ))

}

/**
 * Represents a 4x4 matrix of a given dimension and shape holding
 * floating-point values.
 *
 * @param matrix The matrix data
 */
class Int4x4(matrix: IntArray) : IntMatrix(intArrayOf(4, 4), matrix) {

    companion object {
        val IDENTITY = Int4x4(intArrayOf(
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
        )
        )
    }

    constructor() : this(IntArray(16) { 0 })
    constructor(value: Int) : this(IntArray(16) { value })

    override fun rows(): Array<IntArray> = arrayOf(
        intArrayOf(data[0], data[1], data[2], data[3]),
        intArrayOf(data[4], data[5], data[6], data[7]),
        intArrayOf(data[8], data[9], data[10], data[11]),
        intArrayOf(data[12], data[13], data[14], data[15])
    )

    override fun columns(): Array<IntArray> = arrayOf(
        intArrayOf(data[0], data[4], data[8], data[12]),
        intArrayOf(data[1], data[5], data[9], data[13]),
        intArrayOf(data[2], data[6], data[10], data[14]),
        intArrayOf(data[3], data[7], data[11], data[15])
    )

    init {
        complies("Data must contain 16 elements for a matrix of size 4x4") { data.size == 16 }
        matrix.forEachIndexed { i, value -> data[i] = value }
    }

    override fun transpose(): Int4x4 = Int4x4(intArrayOf(
        data[0], data[4], data[8], data[12],
        data[1], data[5], data[9], data[13],
        data[2], data[6], data[10], data[14],
        data[3], data[7], data[11], data[15]
    ))

}
package com.github.polyrocketmatt.kmt.matrix

import com.github.polyrocketmatt.kmt.common.storage.Tuple
import com.github.polyrocketmatt.kmt.common.utils.complies
import kotlin.IllegalArgumentException

typealias BMatrix = BooleanMatrix
typealias B2x2 = Boolean2x2
typealias B3x3 = Boolean3x3
typealias B4x4 = Boolean4x4

/**
 * Get a matrix with the given shape from the given array.
 *
 * @param shape The shape of the matrix
 * @return A matrix with the given shape and the given array as its data
 * @throws IllegalStateException If the array does not comply with the given shape
 */
fun Array<Boolean>.toMatrix(shape: IntArray): BooleanMatrix {
    val elements = shape.reduce { acc, i -> acc * i }
    shape.complies({ "Incorrect array size for shape ${shape.joinToString("x") { "$it" }}. " +
            "Expected ${elements}, found ${this.size}" },
        { this.size == elements })
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
    shape.complies({ "Incorrect array size for shape ${shape.joinToString("x") { "$it" }}. " +
            "Expected ${elements}, found ${this.size}" },
        { this.size == elements })
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
) : Tuple<Boolean>(BooleanArray(shape.reduce { acc, i -> acc * i  }).toTypedArray()), Matrix<Boolean> {

    constructor(matrix: BooleanArray) : this(intArrayOf(matrix.size), matrix)
    constructor(shape: IntArray) : this(shape, BooleanArray(shape.reduce { acc, i -> acc * i }) { false })
    constructor(shape: IntArray, value: Boolean) : this(shape, BooleanArray(shape.reduce { acc, i -> acc * i }) { value })

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

    override fun get(i: Int): Boolean = data[i]
    override fun get(row: Int, col: Int): Boolean = data[row * shape[1] + col]

    override fun set(i: Int, value: Boolean) { data[i] = value }
    override fun set(row: Int, col: Int, value: Boolean) { data[row * shape[1] + col] = value }

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

    /**
     * Element-wise subtraction of this matrix and the given matrix.
     *
     * @param other The matrix to subtract from this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun minusAssign(other: BooleanMatrix) {
        throw UnsupportedOperationException("Cannot subtract boolean matrices")
    }

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

    /**
     * Element-wise division of this matrix and the given matrix.
     *
     * @param other The matrix to divide to this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun divAssign(other: DoubleMatrix) {
        throw UnsupportedOperationException("Cannot divide boolean matrices")
    }

    /**
     * Scalar addition of this matrix and the given value.
     *
     * @param value The value to add to this matrix
     */
    override fun plus(value: Boolean): BooleanMatrix {
        val matrix = BooleanMatrix(shape)
        data.forEachIndexed { i, term -> matrix[i] = data[i] || term }
        return matrix
    }

    /**
     * Scalar subtraction of this matrix and the given value.
     *
     * @param value The value to subtract from this matrix
     */
    override fun minus(value: Boolean): BooleanMatrix = throw UnsupportedOperationException("Cannot subtract boolean from boolean matrix")

    /**
     * Scalar multiplication of this matrix and the given value.
     *
     * @param value The value to multiply to this matrix
     */
    override fun times(value: Boolean): BooleanMatrix {
        val matrix = BooleanMatrix(shape)
        data.forEachIndexed { i, factor -> matrix[i] = data[i] && factor }
        return matrix
    }

    /**
     * Scalar division of this matrix and the given value.
     *
     * @param value The value to divide with this matrix
     */
    override fun div(value: Boolean): BooleanMatrix = throw UnsupportedOperationException("Cannot divide boolean matrix by boolean")

    /**
     * Scalar addition of this matrix and the given value.
     *
     * @param value The value to add to this matrix
     */
    override fun plusAssign(value: Boolean) = data.forEachIndexed { i, term -> data[i] = data[i] || term }

    /**
     * Scalar subtraction of this matrix and the given value.
     *
     * @param value The value to subtract from this matrix
     */
    override fun minusAssign(value: Boolean) = throw UnsupportedOperationException("Cannot subtract boolean from boolean matrix")

    /**
     * Scalar multiplication of this matrix and the given value.
     *
     * @param value The value to multiply to this matrix
     */
    override fun timesAssign(value: Boolean) = data.forEachIndexed { i, factor -> data[i] = data[i] && factor }

    /**
     * Scalar division of this matrix and the given value.
     *
     * @param value The value to divide with this matrix
     */
    override fun divAssign(value: Boolean) = throw UnsupportedOperationException("Cannot divide boolean from boolean matrix")

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

    fun isScalar(): Boolean = data.size == 1
    fun isSquare(): Boolean = shape[0] == shape[1]

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

}

/**
 * Represents a 2x2 matrix of a given dimension and shape holding
 * floating-point values.
 *
 * @param matrix The matrix data
 */
class Boolean2x2(matrix: BooleanArray) : BooleanMatrix(intArrayOf(2, 2)) {

    constructor() : this(BooleanArray(4) { false })
    constructor(value: Boolean) : this(BooleanArray(4) { value })

    override fun rows(): Array<BooleanArray> = arrayOf(
        booleanArrayOf(data[0], data[1]),
        booleanArrayOf(data[2], data[3])
    )

    override fun columns(): Array<BooleanArray> = arrayOf(
        booleanArrayOf(data[0], data[2]),
        booleanArrayOf(data[1], data[3])
    )

    init {
        complies("Data must contain 4 elements for a matrix of size 2x2") { data.size == 4 }
        data.forEachIndexed { i, value -> data[i] = value }
    }

    override fun mult(other: BooleanMatrix): BooleanMatrix {
        other.complies("Cannot multiply matrices of sizes ${shapeToString()} and ${other.shapeToString()}") { it.shape[0] == 2 }

        val result = BooleanMatrix(intArrayOf(shape[0], other.shape[1]))

        //  Multiplying rows of first matrix with columns of second matrix
        val c = other.shape[1]
        for (i in 0 until 2)
            for (j in 0 until c)
                for (k in 0 until 2)
                    result.data[i * c + j] = result.data[i * c + j] || (data[i * 2 + k] && other.data[k * c + j])
        return result
    }

    override fun transpose(): Boolean2x2 = Boolean2x2(booleanArrayOf(
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
class Boolean3x3(matrix: BooleanArray) : BooleanMatrix(intArrayOf(3, 3)) {

    constructor() : this(BooleanArray(9) { false })
    constructor(value: Boolean) : this(BooleanArray(9) { value })

    override fun rows(): Array<BooleanArray> = arrayOf(
        booleanArrayOf(data[0], data[1], data[2]),
        booleanArrayOf(data[3], data[4], data[5]),
        booleanArrayOf(data[6], data[7], data[8])
    )

    override fun columns(): Array<BooleanArray> = arrayOf(
        booleanArrayOf(data[0], data[3], data[6]),
        booleanArrayOf(data[1], data[4], data[7]),
        booleanArrayOf(data[2], data[5], data[8])
    )

    init {
        complies("Data must contain 9 elements for a matrix of size 3x3") { data.size == 9 }
        matrix.forEachIndexed { i, value -> data[i] = value }
    }

    override fun mult(other: BooleanMatrix): BooleanMatrix {
        other.complies("Cannot multiply matrices of sizes ${shapeToString()} and ${other.shapeToString()}") { it.shape[0] == 3 }

        val result = BooleanMatrix(intArrayOf(shape[0], other.shape[1]))

        //  Multiplying rows of first matrix with columns of second matrix
        val c = other.shape[1]
        for (i in 0 until 3)
            for (j in 0 until c)
                for (k in 0 until 3)
                    result.data[i * c + j] = result.data[i * c + j] || (data[i * 3 + k] && other.data[k * c + j])
        return result
    }

    override fun transpose(): Boolean3x3 = Boolean3x3(booleanArrayOf(
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
class Boolean4x4(matrix: BooleanArray) : BooleanMatrix(intArrayOf(4, 4)) {

    constructor() : this(BooleanArray(16) { false })
    constructor(value: Boolean) : this(BooleanArray(16) { value })

    override fun rows(): Array<BooleanArray> = arrayOf(
        booleanArrayOf(data[0], data[1], data[2], data[3]),
        booleanArrayOf(data[4], data[5], data[6], data[7]),
        booleanArrayOf(data[8], data[9], data[10], data[11]),
        booleanArrayOf(data[12], data[13], data[14], data[15])
    )

    override fun columns(): Array<BooleanArray> = arrayOf(
        booleanArrayOf(data[0], data[4], data[8], data[12]),
        booleanArrayOf(data[1], data[5], data[9], data[13]),
        booleanArrayOf(data[2], data[6], data[10], data[14]),
        booleanArrayOf(data[3], data[7], data[11], data[15])
    )

    init {
        complies("Data must contain 16 elements for a matrix of size 4x4") { data.size == 16 }
        matrix.forEachIndexed { i, value -> data[i] = value }
    }

    override fun mult(other: BooleanMatrix): BooleanMatrix {
        other.complies("Cannot multiply matrices of sizes ${shapeToString()} and ${other.shapeToString()}") { it.shape[0] == 4 }

        val result = BooleanMatrix(intArrayOf(shape[0], other.shape[1]))

        //  Multiplying rows of first matrix with columns of second matrix
        val c = other.shape[1]
        for (i in 0 until 4)
            for (j in 0 until c)
                for (k in 0 until 4)
                    result.data[i * c + j] = result.data[i * c + j] || (data[i * 4 + k] && other.data[k * c + j])
        return result
    }

    override fun transpose(): Boolean4x4 = Boolean4x4(booleanArrayOf(
        data[0], data[4], data[8], data[12],
        data[1], data[5], data[9], data[13],
        data[2], data[6], data[10], data[14],
        data[3], data[7], data[11], data[15]
    ))

}
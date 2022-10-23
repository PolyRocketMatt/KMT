package com.github.polyrocketmatt.kmt.matrix

import com.github.polyrocketmatt.kmt.common.storage.Tuple
import com.github.polyrocketmatt.kmt.common.utils.complies
import kotlin.IllegalArgumentException

typealias DMatrix = DoubleMatrix
typealias D2x2 = Double2x2
typealias D3x3 = Double3x3
typealias D4x4 = Double4x4

/**
 * Get a matrix with the given shape from the given array.
 *
 * @param shape The shape of the matrix
 * @return A matrix with the given shape and the given array as its data
 * @throws IllegalStateException If the array does not comply with the given shape
 */
fun Array<Double>.toMatrix(shape: IntArray): DoubleMatrix {
    val elements = shape.reduce { acc, i -> acc * i }
    shape.complies({ "Incorrect array size for shape ${shape.joinToString("x") { "$it" }}. " +
            "Expected ${elements}, found ${this.size}" },
        { this.size == elements })
    return DoubleMatrix(shape, this.toDoubleArray())
}

/**
 * Get a matrix with the given shape from the given array.
 *
 * @param shape The shape of the matrix
 * @return A matrix with the given shape and the given array as its data
 * @throws IllegalStateException If the array does not comply with the given shape
 */
fun DoubleArray.toMatrix(shape: IntArray): DoubleMatrix {
    val elements = shape.reduce { acc, i -> acc * i }
    shape.complies({ "Incorrect array size for shape ${shape.joinToString("x") { "$it" }}. " +
            "Expected ${elements}, found ${this.size}" },
        { this.size == elements })
    return DoubleMatrix(shape, this)
}

/**
 * Get an array of floating-point numbers from the given matrix.
 *
 * @return The array of floating-point numbers with data from the given matrix
 */
fun DoubleMatrix.toArray(): DoubleArray = this.data.toDoubleArray()

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
open class DoubleMatrix(
    val shape: IntArray,
    matrix: DoubleArray
) : Tuple<Double>(DoubleArray(shape.reduce { acc, i -> acc * i  }).toTypedArray()), Matrix<Double>, NumericMatrix<Double> {

    companion object {
        fun identity(shape: IntArray): DoubleMatrix {
            shape.complies("Identity matrix only exists for dimension 2") { it.size == 2 }

            val matrix = DoubleMatrix(shape)
            val min = shape.min()

            for (i in 0 until min)
                matrix[i * shape[1] + i] = 1.0

            return matrix
        }
    }

    constructor(matrix: DoubleArray) : this(intArrayOf(matrix.size), matrix)
    constructor(shape: IntArray) : this(shape, DoubleArray(shape.reduce { acc, i -> acc * i }) { 0.0 })
    constructor(shape: IntArray, value: Double) : this(shape, DoubleArray(shape.reduce { acc, i -> acc * i }) { value })

    init {
        val shapeSize = shape.reduce { acc, i -> acc * i }

        complies("Data must contain $shapeSize elements for a matrix of size ${shape.joinToString("x") { "$it" }}") { data.size == size }

        matrix.forEachIndexed { i, value -> data[i] = value }
    }

    open fun rows(): Array<DoubleArray> {
        val rows = Array(shape[0]) { DoubleArray(shape[1]) }
        for (j in 0 until shape[1])
            for (i in 0 until shape[0])
                rows[i][j] = this[i, j]
        return rows
    }

    open fun columns(): Array<DoubleArray> {
        val columns = Array(shape[1]) { DoubleArray(shape[0]) }
        for (i in 0 until shape[0])
            for (j in 0 until shape[1])
                columns[j][i] = this[i, j]
        return columns
    }

    override fun get(i: Int): Double = data[i]
    override fun get(row: Int, col: Int): Double = data[row * shape[1] + col]

    override fun set(i: Int, value: Double) { data[i] = value }
    override fun set(row: Int, col: Int, value: Double) { data[row * shape[1] + col] = value }

    /**
     * Element-wise addition of this matrix and the given matrix.
     *
     * @param other The matrix to add to this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun plusAssign(other: DoubleMatrix) {
        isCompliantMatrix(other)
        data.forEachIndexed { i, term -> data[i] = data[i] + term }
    }

    /**
     * Element-wise subtraction of this matrix and the given matrix.
     *
     * @param other The matrix to subtract from this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun minusAssign(other: DoubleMatrix) {
        isCompliantMatrix(other)
        data.forEachIndexed { i, term -> data[i] = data[i] - term }
    }

    /**
     * Element-wise multiplication of this matrix and the given matrix.
     *
     * @param other The matrix to multiply to this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun timesAssign(other: DoubleMatrix) {
        isCompliantMatrix(other)
        data.forEachIndexed { i, factor -> data[i] = data[i] * factor }
    }

    /**
     * Element-wise division of this matrix and the given matrix.
     *
     * @param other The matrix to divide to this matrix
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open operator fun divAssign(other: DoubleMatrix) {
        isCompliantMatrix(other)
        data.forEachIndexed { i, factor -> data[i] = data[i] / factor }
    }

    /**
     * Scalar addition of this matrix and the given value.
     *
     * @param value The value to add to this matrix
     */
    override fun plus(value: Double): DoubleMatrix {
        val matrix = DoubleMatrix(shape)
        data.forEachIndexed { i, term -> matrix[i] = data[i] + term }
        return matrix
    }

    /**
     * Scalar subtraction of this matrix and the given value.
     *
     * @param value The value to subtract from this matrix
     */
    override fun minus(value: Double): DoubleMatrix {
        val matrix = DoubleMatrix(shape)
        data.forEachIndexed { i, term -> matrix[i] = data[i] - term }
        return matrix
    }

    /**
     * Scalar multiplication of this matrix and the given value.
     *
     * @param value The value to multiply to this matrix
     */
    override fun times(value: Double): DoubleMatrix {
        val matrix = DoubleMatrix(shape)
        data.forEachIndexed { i, factor -> matrix[i] = data[i] * factor }
        return matrix
    }

    /**
     * Scalar division of this matrix and the given value.
     *
     * @param value The value to divide with this matrix
     */
    override fun div(value: Double): DoubleMatrix {
        val matrix = DoubleMatrix(shape)
        data.forEachIndexed { i, factor -> matrix[i] = data[i] / factor }
        return matrix
    }

    /**
     * Scalar addition of this matrix and the given value.
     *
     * @param value The value to add to this matrix
     */
    override fun plusAssign(value: Double) = data.forEachIndexed { i, term -> data[i] = data[i] + term }

    /**
     * Scalar subtraction of this matrix and the given value.
     *
     * @param value The value to subtract from this matrix
     */
    override fun minusAssign(value: Double) = data.forEachIndexed { i, term -> data[i] = data[i] - term }

    /**
     * Scalar multiplication of this matrix and the given value.
     *
     * @param value The value to multiply to this matrix
     */
    override fun timesAssign(value: Double) = data.forEachIndexed { i, factor -> data[i] = data[i] * factor }

    /**
     * Scalar division of this matrix and the given value.
     *
     * @param value The value to divide with this matrix
     */
    override fun divAssign(value: Double) = data.forEachIndexed { i, factor -> data[i] = data[i] / factor }

    open override fun transpose(): DoubleMatrix {
        val matrix = DoubleMatrix(intArrayOf(shape[1], shape[0]))
        for (i in 0 until shape[0])
            for (j in 0 until shape[1])
                matrix[j, i] = this[i, j]
        return matrix
    }

    /**
     * Multiply this matrix with the given matrix. The matrices must have
     * a valid shape for multiplication and must be of dimension 2.
     *
     * @param other The matrix to multiply with this matrix
     * @return The result of the multiplication
     * @throws IllegalArgumentException If the matrices are not of dimension 2
     * @throws IllegalArgumentException If the given matrix is not of the same shape as this matrix
     */
    open infix fun mult(other: DoubleMatrix): DoubleMatrix {
        other.complies("Cannot multiply matrices of sizes ${shapeToString()} and ${other.shapeToString()}") { it.shape[0] == shape[1] }

        if (other.shape[0] != shape[1])
            throw IllegalArgumentException("Cannot multiply matrices of sizes ${shapeToString()} and ${other.shapeToString()}")

        val result = DoubleMatrix(intArrayOf(shape[0], other.shape[1]))

        //  Multiplying rows of first matrix with columns of second matrix
        val r1 = shape[0]
        val c1 = shape[1]
        val c = other.shape[1]
        for (i in 0 until r1)
            for (j in 0 until c)
                for (k in 0 until c1)
                    result.data[i * c + j] += data[i * r1 + k] * other.data[k * c + j]
        return result
    }

    internal fun shapeToString(): String = shape.joinToString("x") { "$it" }

    internal fun isCompliantMatrix(other: DoubleMatrix) =
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
class Double2x2(matrix: DoubleArray) : DoubleMatrix(intArrayOf(2, 2)) {

    companion object {
        val IDENTITY = Double2x2(doubleArrayOf(1.0, 0.0, 0.0, 1.0))
    }

    constructor() : this(DoubleArray(4) { 0.0 })
    constructor(value: Double) : this(DoubleArray(4) { value })

    override fun rows(): Array<DoubleArray> = arrayOf(
        doubleArrayOf(data[0], data[1]),
        doubleArrayOf(data[2], data[3])
    )

    override fun columns(): Array<DoubleArray> = arrayOf(
        doubleArrayOf(data[0], data[2]),
        doubleArrayOf(data[1], data[3])
    )

    init {
        complies("Data must contain 4 elements for a matrix of size 2x2") { data.size == 4 }
        data.forEachIndexed { i, value -> data[i] = value }
    }

    override fun mult(other: DoubleMatrix): DoubleMatrix {
        other.complies("Cannot multiply matrices of sizes ${shapeToString()} and ${other.shapeToString()}") { it.shape[0] == 2 }


        val result = DoubleMatrix(intArrayOf(shape[0], other.shape[1]))

        //  Multiplying rows of first matrix with columns of second matrix
        val c = other.shape[1]
        for (i in 0 until 2)
            for (j in 0 until c)
                for (k in 0 until 2)
                    result.data[i * c + j] += data[i * 2 + k] * other.data[k * c + j]
        return result
    }

    override fun transpose(): DoubleMatrix = Double2x2(doubleArrayOf(
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
class Double3x3(matrix: DoubleArray) : DoubleMatrix(intArrayOf(3, 3)) {

    companion object {
        val IDENTITY = Double3x3(doubleArrayOf(
            1.0, 0.0, 0.0,
            0.0, 1.0, 0.0,
            0.0, 0.0, 1.0
        ))
    }

    constructor() : this(DoubleArray(9) { 0.0 })
    constructor(value: Double) : this(DoubleArray(9) { value })

    override fun rows(): Array<DoubleArray> = arrayOf(
        doubleArrayOf(data[0], data[1], data[2]),
        doubleArrayOf(data[3], data[4], data[5]),
        doubleArrayOf(data[6], data[7], data[8])
    )

    override fun columns(): Array<DoubleArray> = arrayOf(
        doubleArrayOf(data[0], data[3], data[6]),
        doubleArrayOf(data[1], data[4], data[7]),
        doubleArrayOf(data[2], data[5], data[8])
    )

    init {
        complies("Data must contain 9 elements for a matrix of size 3x3") { data.size == 4 }
        matrix.forEachIndexed { i, value -> data[i] = value }
    }

    override fun mult(other: DoubleMatrix): DoubleMatrix {
        other.complies("Cannot multiply matrices of sizes ${shapeToString()} and ${other.shapeToString()}") { it.shape[0] == 3 }

        val result = DoubleMatrix(intArrayOf(shape[0], other.shape[1]))

        //  Multiplying rows of first matrix with columns of second matrix
        val c = other.shape[1]
        for (i in 0 until 3)
            for (j in 0 until c)
                for (k in 0 until 3)
                    result.data[i * c + j] += data[i * 3 + k] * other.data[k * c + j]
        return result
    }

    override fun transpose(): DoubleMatrix = Double3x3(doubleArrayOf(
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
class Double4x4(matrix: DoubleArray) : DoubleMatrix(intArrayOf(4, 4)) {

    companion object {
        val IDENTITY = Double4x4(doubleArrayOf(
            1.0, 0.0, 0.0, 0.0,
            0.0, 1.0, 0.0, 0.0,
            0.0, 0.0, 1.0, 0.0,
            0.0, 0.0, 0.0, 1.0
        )
        )
    }

    constructor() : this(DoubleArray(16) { 0.0 })
    constructor(value: Double) : this(DoubleArray(16) { value })

    override fun rows(): Array<DoubleArray> = arrayOf(
        doubleArrayOf(data[0], data[1], data[2], data[3]),
        doubleArrayOf(data[4], data[5], data[6], data[7]),
        doubleArrayOf(data[8], data[9], data[10], data[11]),
        doubleArrayOf(data[12], data[13], data[14], data[15])
    )

    override fun columns(): Array<DoubleArray> = arrayOf(
        doubleArrayOf(data[0], data[4], data[8], data[12]),
        doubleArrayOf(data[1], data[5], data[9], data[13]),
        doubleArrayOf(data[2], data[6], data[10], data[14]),
        doubleArrayOf(data[3], data[7], data[11], data[15])
    )

    init {
        complies("Data must contain 16 elements for a matrix of size 4x4") { data.size == 4 }
        matrix.forEachIndexed { i, value -> data[i] = value }
    }

    override fun mult(other: DoubleMatrix): DoubleMatrix {
        other.complies("Cannot multiply matrices of sizes ${shapeToString()} and ${other.shapeToString()}") { it.shape[0] == 4 }

        val result = DoubleMatrix(intArrayOf(shape[0], other.shape[1]))

        //  Multiplying rows of first matrix with columns of second matrix
        val c = other.shape[1]
        for (i in 0 until 4)
            for (j in 0 until c)
                for (k in 0 until 4)
                    result.data[i * c + j] += data[i * 4 + k] * other.data[k * c + j]
        return result
    }

    override fun transpose(): DoubleMatrix = Double4x4(doubleArrayOf(
        data[0], data[4], data[8], data[12],
        data[1], data[5], data[9], data[13],
        data[2], data[6], data[10], data[14],
        data[3], data[7], data[11], data[15]
    ))

}
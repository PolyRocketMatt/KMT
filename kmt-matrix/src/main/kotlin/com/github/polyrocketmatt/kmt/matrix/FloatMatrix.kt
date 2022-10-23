package com.github.polyrocketmatt.kmt.matrix

import com.github.polyrocketmatt.kmt.common.storage.Tuple
import com.github.polyrocketmatt.kmt.common.utils.complies
import kotlin.IllegalArgumentException

typealias FMatrix = FloatMatrix
typealias F2x2 = Float2x2
typealias F3x3 = Float3x3
typealias F4x4 = Float4x4

/**
 * Get a matrix with the given shape from the given array.
 *
 * @param shape The shape of the matrix
 * @return A matrix with the given shape and the given array as its data
 * @throws IllegalStateException If the array does not comply with the given shape
 */
fun Array<Float>.toMatrix(shape: IntArray): FloatMatrix {
    val elements = shape.reduce { acc, i -> acc * i }
    shape.complies({ "Incorrect array size for shape ${shape.joinToString("x") { "$it" }}. " +
            "Expected ${elements}, found ${this.size}" },
        { this.size == elements })
    return FloatMatrix(shape.size, shape, this.toFloatArray())
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
    shape.complies({ "Incorrect array size for shape ${shape.joinToString("x") { "$it" }}. " +
            "Expected ${elements}, found ${this.size}" },
        { this.size == elements })
    return FloatMatrix(shape.size, shape, this)
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
 * Represents a matrix of a given dimension and shape holding
 * floating-point values.
 *
 * @param dimension The dimension of the matrix
 * @param shape The shape of the matrix
 *
 * TODO: Fix mult
 */
open class FloatMatrix(
    override val dimension: Int,
    internal val shape: IntArray,
    matrix: FloatArray
) : Tuple<Float>(FloatArray(shape.reduce { acc, i -> acc * i  }).toTypedArray()), MatrixDimension, Matrix<Float> {

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

    constructor(shape: IntArray) : this(shape.size, shape, FloatArray(shape.reduce { acc, i -> acc * i }) { 0.0f })
    constructor(shape: IntArray, value: Float) : this(shape.size, shape, FloatArray(shape.reduce { acc, i -> acc * i }) { value })

    constructor(dimension: Int, shape: IntArray) : this(dimension, shape, FloatArray(shape.reduce { acc, i -> acc * i }) { 0.0f })
    constructor(dimension: Int, shape: IntArray, value: Float) : this(dimension, shape, FloatArray(shape.reduce { acc, i -> acc * i }) { value })

    init {
        val shapeSize = shape.reduce { acc, i -> acc * i }

        complies("Data must contain $shapeSize elements for a matrix of size ${shape.joinToString("x") { "$it" }}") { data.size == size }
        matrix.forEachIndexed { i, value -> data[i] = value }
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
        complies("Cannot multiply matrices with dimension higher than 2") { this.dimension == 2 && other.dimension == 2 }
        other.complies("Cannot multiply matrices of sizes ${shapeToString()} and ${other.shapeToString()}") { it.shape[0] == shape[1] }

        if (other.shape[0] != shape[1])
            throw IllegalArgumentException("Cannot multiply matrices of sizes ${shapeToString()} and ${other.shapeToString()}")

        val result = FloatMatrix(2, intArrayOf(shape[0], other.shape[1]))

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

    internal fun isCompliantMatrix(other: FloatMatrix) =
        other
            .complies({ "Other is of type ${it::class.java}, expected ${this::class.java}" }, { it::class.java == this::class.java })
            .also { it -> it.complies({ "Shape does not match. Expected ${shapeToString()}, found ${other.shapeToString()}" }, { it.shape.contentEquals(this.shape) }) }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FloatMatrix) return false

        if (dimension != other.dimension) return false
        if (!shape.contentEquals(other.shape)) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = dimension
        result = 31 * result + shape.contentHashCode()
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
class Float2x2(matrix: FloatArray) : FloatMatrix(2, intArrayOf(2, 2)) {

    companion object {
        val IDENTITY = Float2x2(floatArrayOf(1f, 0f, 0f, 1f))
    }

    constructor() : this(FloatArray(4) { 0.0f })
    constructor(value: Float) : this(FloatArray(4) { value })

    fun rows(): Array<FloatArray> = arrayOf(
        floatArrayOf(data[0], data[1]),
        floatArrayOf(data[2], data[3])
    )

    fun columns(): Array<FloatArray> = arrayOf(
        floatArrayOf(data[0], data[2]),
        floatArrayOf(data[1], data[3])
    )

    init {
        complies("Data must contain 4 elements for a matrix of size 2x2") { data.size == 4 }
        data.forEachIndexed { i, value -> data[i] = value }
    }

    override fun mult(other: FloatMatrix): FloatMatrix {
        other.complies("Cannot multiply matrices of sizes ${shapeToString()} and ${other.shapeToString()}") { it.shape[0] == 2 }

        val result = FloatMatrix(2, intArrayOf(shape[0], other.shape[1]))

        //  Multiplying rows of first matrix with columns of second matrix
        val c = other.shape[1]
        for (i in 0 until 2)
            for (j in 0 until c)
                for (k in 0 until 2)
                    result.data[i * c + j] += data[i * 2 + k] * other.data[k * c + j]
        return result
    }

}

/**
 * Represents a 3x3 matrix of a given dimension and shape holding
 * floating-point values.
 *
 * @param matrix The matrix data
 */
class Float3x3(matrix: FloatArray) : FloatMatrix(2, intArrayOf(3, 3)) {

    companion object {
        val IDENTITY = Float3x3(floatArrayOf(
            1f, 0f, 0f,
            0f, 1f, 0f,
            0f, 0f, 1f
        ))
    }

    constructor() : this(FloatArray(9) { 0.0f })
    constructor(value: Float) : this(FloatArray(9) { value })

    fun rows(): Array<FloatArray> = arrayOf(
        floatArrayOf(data[0], data[1], data[2]),
        floatArrayOf(data[3], data[4], data[5]),
        floatArrayOf(data[6], data[7], data[8])
    )

    fun columns(): Array<FloatArray> = arrayOf(
        floatArrayOf(data[0], data[3], data[6]),
        floatArrayOf(data[1], data[4], data[7]),
        floatArrayOf(data[2], data[5], data[8])
    )

    init {
        complies("Data must contain 9 elements for a matrix of size 2x2") { data.size == 4 }
        matrix.forEachIndexed { i, value -> data[i] = value }
    }

    override fun mult(other: FloatMatrix): FloatMatrix {
        other.complies("Cannot multiply matrices of sizes ${shapeToString()} and ${other.shapeToString()}") { it.shape[0] == 3 }

        val result = FloatMatrix(3, intArrayOf(shape[0], other.shape[1]))

        //  Multiplying rows of first matrix with columns of second matrix
        val c = other.shape[1]
        for (i in 0 until 3)
            for (j in 0 until c)
                for (k in 0 until 3)
                    result.data[i * c + j] += data[i * 3 + k] * other.data[k * c + j]
        return result
    }

}

/**
 * Represents a 4x4 matrix of a given dimension and shape holding
 * floating-point values.
 *
 * @param matrix The matrix data
 */
class Float4x4(matrix: FloatArray) : FloatMatrix(2, intArrayOf(4, 4)) {

    companion object {
        val IDENTITY = Float4x4(floatArrayOf(
            1f, 0f, 0f, 0f,
            0f, 1f, 0f, 0f,
            0f, 0f, 1f, 0f,
            0f, 0f, 0f, 1f
        ))
    }

    constructor() : this(FloatArray(16) { 0.0f })
    constructor(value: Float) : this(FloatArray(16) { value })

    fun rows(): Array<FloatArray> = arrayOf(
        floatArrayOf(data[0], data[1], data[2], data[3]),
        floatArrayOf(data[4], data[5], data[6], data[7]),
        floatArrayOf(data[8], data[9], data[10], data[11]),
        floatArrayOf(data[12], data[13], data[14], data[15])
    )

    fun columns(): Array<FloatArray> = arrayOf(
        floatArrayOf(data[0], data[4], data[8], data[12]),
        floatArrayOf(data[1], data[5], data[9], data[13]),
        floatArrayOf(data[2], data[6], data[10], data[14]),
        floatArrayOf(data[3], data[7], data[11], data[15])
    )

    init {
        complies("Data must contain 16 elements for a matrix of size 2x2") { data.size == 4 }
        matrix.forEachIndexed { i, value -> data[i] = value }
    }

    override fun mult(other: FloatMatrix): FloatMatrix {
        other.complies("Cannot multiply matrices of sizes ${shapeToString()} and ${other.shapeToString()}") { it.shape[0] == 4 }

        val result = FloatMatrix(4, intArrayOf(shape[0], other.shape[1]))

        //  Multiplying rows of first matrix with columns of second matrix
        val c = other.shape[1]
        for (i in 0 until 4)
            for (j in 0 until c)
                for (k in 0 until 4)
                    result.data[i * c + j] += data[i * 4 + k] * other.data[k * c + j]
        return result
    }

}
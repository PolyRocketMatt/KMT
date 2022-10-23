package com.github.polyrocketmatt.kmt.matrix

import com.github.polyrocketmatt.kmt.common.storage.MemoryStorage
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
    shape.complies({ "Incorrect array size for shape ${shape.joinToString("x") { "$it" }}. " +
            "Expected ${elements}, found ${this.size}" },
        { this.size == elements })
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
) : Tuple<Float>(FloatArray(shape.reduce { acc, i -> acc * i  }).toTypedArray()),
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

    constructor(matrix: FloatArray) : this(intArrayOf(matrix.size), matrix)
    constructor(shape: IntArray) : this(shape, FloatArray(shape.reduce { acc, i -> acc * i }) { 0.0f })
    constructor(shape: IntArray, value: Float) : this(shape, FloatArray(shape.reduce { acc, i -> acc * i }) { value })
    constructor(matrix: Array<Float>) : this(matrix.toFloatArray())

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

    open override fun transpose(): FloatMatrix {
        val matrix = FloatMatrix(intArrayOf(shape[1], shape[0]))
        for (i in 0 until shape[0])
            for (j in 0 until shape[1])
                matrix[j, i] = this[i, j]
        return matrix
    }

    override fun swapRow(row1: Int, row2: Int): FloatMatrix {
        TODO("Not yet implemented")
    }
    override fun multiplyRow(row: Int, scalar: Float): FloatMatrix {
        TODO("Not yet implemented")
    }
    override fun addRow(row1: Int, row2: Int, scalar: Float): FloatMatrix {
        TODO("Not yet implemented")
    }

    override fun ref(): FloatMatrix {
        TODO("Not yet implemented")
    }
    override fun rref(): FloatMatrix {
        TODO("Not yet implemented")
    }

    fun isScalar(): Boolean = data.size == 1
    fun isSquare(): Boolean = shape[0] == shape[1]

    open fun toDoubleMatrix(): DoubleMatrix = DoubleMatrix(shape, data.map { it.toDouble() }.toDoubleArray())
    open fun toIntMatrix(): IntMatrix = IntMatrix(shape, data.map { it.toInt() }.toIntArray())
    open fun toShortMatrix(): ShortMatrix = ShortMatrix(shape, data.map { it.toInt().toShort() }.toShortArray())

    internal fun shapeToString(): String = shape.joinToString("x") { "$it" }

    internal fun isCompliantMatrix(other: FloatMatrix) =
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
class Float2x2(matrix: FloatArray) : FloatMatrix(intArrayOf(2, 2), matrix) {

    companion object {
        val IDENTITY = Float2x2(floatArrayOf(1f, 0f, 0f, 1f))
    }

    constructor() : this(FloatArray(4) { 0.0f })
    constructor(value: Float) : this(FloatArray(4) { value })
    constructor(matrix: Array<Float>) : this(matrix.toFloatArray())

    override fun plus(value: Float): Float2x2 {
        val matrix = Float2x2()
        data.forEachIndexed { index, f -> matrix.data[index] = f + value }
        return matrix
    }
    override fun minus(value: Float): Float2x2 {
        val matrix = Float2x2()
        data.forEachIndexed { index, f -> matrix.data[index] = f - value }
        return matrix
    }
    override fun times(value: Float): Float2x2 {
        val matrix = Float2x2()
        data.forEachIndexed { index, f -> matrix.data[index] = f * value }
        return matrix
    }
    override fun div(value: Float): Float2x2 {
        val matrix = Float2x2()
        data.forEachIndexed { index, f -> matrix.data[index] = f / value }
        return matrix
    }

    override fun transpose(): Float2x2 = Float2x2(floatArrayOf(
        data[0], data[2],
        data[1], data[3]
    ))

    override fun addRow(row1: Int, row2: Int, scalar: Float): Float2x2 = super.addRow(row1, row2, scalar) as Float2x2
    override fun multiplyRow(row: Int, scalar: Float): Float2x2 = super.multiplyRow(row, scalar) as Float2x2
    override fun swapRow(row1: Int, row2: Int): Float2x2 = super.swapRow(row1, row2) as Float2x2

    override fun ref(): Float2x2 = super.ref() as Float2x2
    override fun rref(): Float2x2 = super.rref() as Float2x2

    override fun toDoubleMatrix(): Double2x2 = Double2x2(data.map { it.toDouble() }.toDoubleArray())
    override fun toIntMatrix(): Int2x2 = Int2x2(data.map { it.toInt() }.toIntArray())
    override fun toShortMatrix(): Short2x2 = Short2x2(data.map { it.toInt().toShort() }.toShortArray())

    override fun copyOf(): Float2x2 = Float2x2(data.copyOf())

}

/**
 * Represents a 3x3 matrix of a given dimension and shape holding
 * floating-point values.
 *
 * @param matrix The matrix data
 */
class Float3x3(matrix: FloatArray) : FloatMatrix(intArrayOf(3, 3), matrix) {

    companion object {
        val IDENTITY = Float3x3(floatArrayOf(
            1f, 0f, 0f,
            0f, 1f, 0f,
            0f, 0f, 1f
        ))
    }

    constructor() : this(FloatArray(9) { 0.0f })
    constructor(value: Float) : this(FloatArray(9) { value })
    constructor(matrix: Array<Float>) : this(matrix.toFloatArray())

    override fun plus(value: Float): Float3x3 {
        val matrix = Float3x3()
        data.forEachIndexed { index, f -> matrix.data[index] = f + value }
        return matrix
    }
    override fun minus(value: Float): Float3x3 {
        val matrix = Float3x3()
        data.forEachIndexed { index, f -> matrix.data[index] = f - value }
        return matrix
    }
    override fun times(value: Float): Float3x3 {
        val matrix = Float3x3()
        data.forEachIndexed { index, f -> matrix.data[index] = f * value }
        return matrix
    }
    override fun div(value: Float): Float3x3 {
        val matrix = Float3x3()
        data.forEachIndexed { index, f -> matrix.data[index] = f / value }
        return matrix
    }

    override fun transpose(): Float3x3 = Float3x3(floatArrayOf(
        data[0], data[3], data[6],
        data[1], data[4], data[7],
        data[2], data[5], data[8]
    ))

    override fun addRow(row1: Int, row2: Int, scalar: Float): Float3x3 = super.addRow(row1, row2, scalar) as Float3x3
    override fun multiplyRow(row: Int, scalar: Float): Float3x3 = super.multiplyRow(row, scalar) as Float3x3
    override fun swapRow(row1: Int, row2: Int): Float3x3 = super.swapRow(row1, row2) as Float3x3

    override fun ref(): Float3x3 = super.ref() as Float3x3
    override fun rref(): Float3x3 = super.rref() as Float3x3

    override fun toDoubleMatrix(): Double3x3 = Double3x3(data.map { it.toDouble() }.toDoubleArray())
    override fun toIntMatrix(): Int3x3 = Int3x3(data.map { it.toInt() }.toIntArray())
    override fun toShortMatrix(): Short3x3 = Short3x3(data.map { it.toInt().toShort() }.toShortArray())

    override fun copyOf(): Float3x3 = Float3x3(data.copyOf())

}

/**
 * Represents a 4x4 matrix of a given dimension and shape holding
 * floating-point values.
 *
 * @param matrix The matrix data
 */
class Float4x4(matrix: FloatArray) : FloatMatrix(intArrayOf(4, 4), matrix) {

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
    constructor(matrix: Array<Float>) : this(matrix.toFloatArray())

    override fun plus(value: Float): Float4x4 {
        val matrix = Float4x4()
        data.forEachIndexed { index, f -> matrix.data[index] = f + value }
        return matrix
    }
    override fun minus(value: Float): Float4x4 {
        val matrix = Float4x4()
        data.forEachIndexed { index, f -> matrix.data[index] = f - value }
        return matrix
    }
    override fun times(value: Float): Float4x4 {
        val matrix = Float4x4()
        data.forEachIndexed { index, f -> matrix.data[index] = f * value }
        return matrix
    }
    override fun div(value: Float): Float4x4 {
        val matrix = Float4x4()
        data.forEachIndexed { index, f -> matrix.data[index] = f / value }
        return matrix
    }

    override fun transpose(): Float4x4 = Float4x4(floatArrayOf(
        data[0], data[4], data[8], data[12],
        data[1], data[5], data[9], data[13],
        data[2], data[6], data[10], data[14],
        data[3], data[7], data[11], data[15]
    ))

    override fun addRow(row1: Int, row2: Int, scalar: Float): Float4x4 = super.addRow(row1, row2, scalar) as Float4x4
    override fun multiplyRow(row: Int, scalar: Float): Float4x4 = super.multiplyRow(row, scalar) as Float4x4
    override fun swapRow(row1: Int, row2: Int): Float4x4 = super.swapRow(row1, row2) as Float4x4

    override fun ref(): Float4x4 = super.ref() as Float4x4
    override fun rref(): Float4x4 = super.rref() as Float4x4

    override fun toDoubleMatrix(): Double4x4 = Double4x4(data.map { it.toDouble() }.toDoubleArray())
    override fun toIntMatrix(): Int4x4 = Int4x4(data.map { it.toInt() }.toIntArray())
    override fun toShortMatrix(): Short4x4 = Short4x4(data.map { it.toInt().toShort() }.toShortArray())

    override fun copyOf(): Float4x4 = Float4x4(data.copyOf())

}
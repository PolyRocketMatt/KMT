package com.github.polyrocketmatt.kmt.matrix

import com.github.polyrocketmatt.kmt.common.decimals
import com.github.polyrocketmatt.kmt.common.fastAbs
import com.github.polyrocketmatt.kmt.common.storage.Tuple
import com.github.polyrocketmatt.kmt.common.utils.complies
import com.github.polyrocketmatt.kmt.common.utils.indexByCondition
import kotlin.IllegalArgumentException
import kotlin.math.min

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
) : Tuple<Double>(DoubleArray(shape.reduce { acc, i -> acc * i  }).toTypedArray()),
    NumericMatrix<Double, Double> {

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
    constructor(matrix: Array<Double>) : this(matrix.toDoubleArray())

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

    open fun row(idx: Int): DoubleArray {
        complies("Index $idx is out of bounds for ${shape[0]} rows") { idx in 0 until shape[0] }

        val row = DoubleArray(shape[1])
        for (j in 0 until shape[1])
            row[j] = this[idx, j]
        return row
    }

    open fun column(idx: Int): DoubleArray {
        complies("Index $idx is out of bounds for ${shape[1]} columns") { idx in 0 until shape[1] }

        val column = DoubleArray(shape[0])
        for (i in 0 until shape[0])
            column[i] = this[i, idx]
        return column
    }

    override operator fun get(i: Int): Double = data[i]
    override operator fun get(row: Int, col: Int): Double = data[row * shape[1] + col]

    override operator fun set(i: Int, value: Double) { println("OK"); data[i] = value.decimals(12) }
    override operator fun set(row: Int, col: Int, value: Double) { println("OK"); data[row * shape[1] + col] = value.decimals(12) }

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

        //  Multiplying rows of first matrix with columns of second matrix
        val result = DoubleMatrix(intArrayOf(shape[0], other.shape[1]))
        val r1 = shape[0]
        val c1 = shape[1]
        val c = other.shape[1]
        for (i in 0 until r1)
            for (j in 0 until c)
                for (k in 0 until c1)
                    result.data[i * c + j] += data[i * r1 + k] * other.data[k * c + j]
        return result
    }

    open override fun transpose(): DoubleMatrix {
        val matrix = DoubleMatrix(intArrayOf(shape[1], shape[0]))
        for (i in 0 until shape[0])
            for (j in 0 until shape[1])
                matrix[j, i] = this[i, j]
        return matrix
    }

    override fun swapRow(row1: Int, row2: Int): DoubleMatrix {
        val rowIndex1 = row1 * shape[1]
        val rowIndex2 = row2 * shape[1]
        val tmp = data.copyOfRange(rowIndex1, rowIndex1 + shape[1])

        data.copyInto(data, rowIndex1, rowIndex2, rowIndex2 + shape[1])
        tmp.copyInto(data, rowIndex2, 0, shape[1])

        return this
    }

    override fun multiplyRow(row: Int, scalar: Double): DoubleMatrix {
        val rowIndex = row * shape[1]
        for (i in 0 until shape[1])
            data[rowIndex + i] *= scalar
        return this
    }

    override fun addRow(row1: Int, row2: Int, scalar: Double): DoubleMatrix {
        val rowIndex1 = row1 * shape[1]
        val rowIndex2 = row2 * shape[1]
        for (i in 0 until shape[1])
            data[i + rowIndex1] += (data[i + rowIndex2] * scalar)
        return this
    }

    override fun ref(): DoubleMatrix {
        var currentRow = 0
        for ((currentColumn, _) in (0 until min(shape[0], shape[1])).withIndex()) {
            //  Find index of value with the highest absolute value in current column
            val col = column(currentColumn).copyOfRange(currentColumn, shape[0])
            val pivotIndex = col.indexByCondition(Double.MIN_VALUE) { _, current, _, value -> current.fastAbs() < value.fastAbs() }
            val pivot = col[pivotIndex]

            //  The index also gives the row to swap to the top
            swapRow(currentRow, pivotIndex + currentColumn)

            //  Update the current row
            currentRow++

            //  Gaussian elimination step
            for (i in currentRow until shape[0]) {
                //  Solve for k, the scalar to multiply the pivot row with to get 0 in the current column
                val k = -column(currentColumn)[i] / pivot

                //  Add the pivot row multiplied with k to the current row (which is the index of the current column)
                addRow(i, currentColumn, k)
            }
        }

        return this
    }

    override fun rref(): DoubleMatrix {
        //  We first bring the matrix in row echelon form
        ref()

        //  Decide if to clean up tiny values
        val smallest = data.min()
        if (smallest < 1e-12)
            data.forEachIndexed { i, value -> data[i] = value.decimals(12) }

        for (i in shape[0] - 1 downTo 0) {
            //  Find the row with a 1.0 in the current column
            val col = row(i)
            val columnIndex = col.indexOfFirst { it != 0.0 }

            //  Found 0-row
            if (columnIndex == -1)
                continue

            //  Make sure there is a 1.0 in the current column
            if (col[columnIndex] != 1.0)
                multiplyRow(i, 1.0 / col[columnIndex])

            //  For all rows above, make a 0 in the current column
            for (j in i - 1 downTo 0) {
                val k = -column(columnIndex)[j]

                addRow(j, i, k)
            }
        }

        return this
    }

    fun isScalar(): Boolean = data.size == 1
    fun isSquare(): Boolean = shape[0] == shape[1]

    open fun toFloatMatrix(): FloatMatrix = FloatMatrix(shape, data.map { it.toFloat() }.toFloatArray())
    open fun toIntMatrix(): IntMatrix = IntMatrix(shape, data.map { it.toInt() }.toIntArray())
    open fun toShortMatrix(): ShortMatrix = ShortMatrix(shape, data.map { it.toInt().toShort() }.toShortArray())

    internal fun shapeToString(): String = shape.joinToString("x") { "$it" }

    internal fun isCompliantMatrix(other: DoubleMatrix) =
        other
            .complies({ "Other is of type ${it::class.java}, expected ${this::class.java}" }, { it::class.java == this::class.java })
            .also { it -> it.complies({ "Shape does not match. Expected ${shapeToString()}, found ${other.shapeToString()}" }, { it.shape.contentEquals(this.shape) }) }

    override fun copyOf(): DoubleMatrix = DoubleMatrix(shape, data.copyOf().toDoubleArray())

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
class Double2x2(matrix: DoubleArray) : DoubleMatrix(intArrayOf(2, 2), matrix) {

    companion object {
        val IDENTITY = Double2x2(doubleArrayOf(1.0, 0.0, 0.0, 1.0))
    }

    constructor() : this(DoubleArray(4) { 0.0 })
    constructor(value: Double) : this(DoubleArray(4) { value })
    constructor(matrix: Array<Double>) : this(matrix.toDoubleArray())

    override fun plus(value: Double): Double2x2 = super.plus(value) as Double2x2
    override fun minus(value: Double): Double2x2 = super.minus(value) as Double2x2
    override fun times(value: Double): Double2x2 = super.times(value) as Double2x2
    override fun div(value: Double): Double2x2 = super.div(value) as Double2x2

    override fun transpose(): DoubleMatrix = Double2x2(doubleArrayOf(
        data[0], data[2],
        data[1], data[3]
    ))

    override fun addRow(row1: Int, row2: Int, scalar: Double): Double2x2 = super.addRow(row1, row2, scalar) as Double2x2
    override fun multiplyRow(row: Int, scalar: Double): Double2x2 = super.multiplyRow(row, scalar) as Double2x2
    override fun swapRow(row1: Int, row2: Int): Double2x2 = super.swapRow(row1, row2) as Double2x2

    override fun ref(): Double2x2 = super.ref() as Double2x2
    override fun rref(): Double2x2 = super.rref() as Double2x2

    override fun toFloatMatrix(): Float2x2 = Float2x2(data.map { it.toFloat() }.toFloatArray())
    override fun toIntMatrix(): Int2x2 = Int2x2(data.map { it.toInt() }.toIntArray())
    override fun toShortMatrix(): Short2x2 = Short2x2(data.map { it.toInt().toShort() }.toShortArray())

    override fun copyOf(): Double2x2 = Double2x2(data.copyOf())

}

/**
 * Represents a 3x3 matrix of a given dimension and shape holding
 * floating-point values.
 *
 * @param matrix The matrix data
 */
class Double3x3(matrix: DoubleArray) : DoubleMatrix(intArrayOf(3, 3), matrix) {

    companion object {
        val IDENTITY = Double3x3(doubleArrayOf(
            1.0, 0.0, 0.0,
            0.0, 1.0, 0.0,
            0.0, 0.0, 1.0
        ))
    }

    constructor() : this(DoubleArray(9) { 0.0 })
    constructor(value: Double) : this(DoubleArray(9) { value })
    constructor(matrix: Array<Double>) : this(matrix.toDoubleArray())

    override fun plus(value: Double): Double3x3 = super.plus(value) as Double3x3
    override fun minus(value: Double): Double3x3 = super.minus(value) as Double3x3
    override fun times(value: Double): Double3x3 = super.times(value) as Double3x3
    override fun div(value: Double): Double3x3 = super.div(value) as Double3x3


    override fun transpose(): DoubleMatrix = Double3x3(doubleArrayOf(
        data[0], data[3], data[6],
        data[1], data[4], data[7],
        data[2], data[5], data[8]
    ))

    override fun addRow(row1: Int, row2: Int, scalar: Double): Double3x3 = super.addRow(row1, row2, scalar) as Double3x3
    override fun multiplyRow(row: Int, scalar: Double): Double3x3 = super.multiplyRow(row, scalar) as Double3x3
    override fun swapRow(row1: Int, row2: Int): Double3x3 = super.swapRow(row1, row2) as Double3x3

    override fun ref(): Double3x3 = super.ref() as Double3x3
    override fun rref(): Double3x3 = super.rref() as Double3x3

    override fun toFloatMatrix(): Float3x3 = Float3x3(data.map { it.toFloat() }.toFloatArray())
    override fun toIntMatrix(): Int3x3 = Int3x3(data.map { it.toInt() }.toIntArray())
    override fun toShortMatrix(): Short3x3 = Short3x3(data.map { it.toInt().toShort() }.toShortArray())

    override fun copyOf(): Double3x3 = Double3x3(data.copyOf())

}

/**
 * Represents a 4x4 matrix of a given dimension and shape holding
 * floating-point values.
 *
 * @param matrix The matrix data
 */
class Double4x4(matrix: DoubleArray) : DoubleMatrix(intArrayOf(4, 4), matrix) {

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
    constructor(matrix: Array<Double>) : this(matrix.toDoubleArray())

    override fun plus(value: Double): Double4x4 = super.plus(value) as Double4x4
    override fun minus(value: Double): Double4x4 = super.minus(value) as Double4x4
    override fun times(value: Double): Double4x4 = super.times(value) as Double4x4
    override fun div(value: Double): Double4x4 = super.div(value) as Double4x4


    override fun transpose(): DoubleMatrix = Double4x4(doubleArrayOf(
        data[0], data[4], data[8], data[12],
        data[1], data[5], data[9], data[13],
        data[2], data[6], data[10], data[14],
        data[3], data[7], data[11], data[15]
    ))

    override fun addRow(row1: Int, row2: Int, scalar: Double): Double4x4 = super.addRow(row1, row2, scalar) as Double4x4
    override fun multiplyRow(row: Int, scalar: Double): Double4x4 = super.multiplyRow(row, scalar) as Double4x4
    override fun swapRow(row1: Int, row2: Int): Double4x4 = super.swapRow(row1, row2) as Double4x4

    override fun ref(): Double4x4 = super.ref() as Double4x4
    override fun rref(): Double4x4 = super.rref() as Double4x4

    override fun toFloatMatrix(): Float4x4 = Float4x4(data.map { it.toFloat() }.toFloatArray())
    override fun toIntMatrix(): Int4x4 = Int4x4(data.map { it.toInt() }.toIntArray())
    override fun toShortMatrix(): Short4x4 = Short4x4(data.map { it.toInt().toShort() }.toShortArray())

    override fun copyOf(): Double4x4 = Double4x4(data.copyOf())

}
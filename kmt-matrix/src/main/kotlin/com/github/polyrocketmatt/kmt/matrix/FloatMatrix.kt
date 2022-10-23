package com.github.polyrocketmatt.kmt.matrix

import com.github.polyrocketmatt.kmt.common.storage.Tuple
import com.github.polyrocketmatt.kmt.common.utils.complies
import kotlin.IllegalArgumentException

abstract class FloatMatrix(
    override val dimension: Int,
    internal val shape: IntArray
) : Tuple<Float>(FloatArray(shape.reduce { acc, i -> acc * i  }).toTypedArray()), MatrixDimension {

    abstract operator fun plusAssign(other: FloatMatrix)
    abstract operator fun minusAssign(other: FloatMatrix)
    abstract operator fun timesAssign(other: FloatMatrix)
    abstract operator fun divAssign(other: FloatMatrix)

    abstract infix fun mult(other: FloatMatrix): FloatMatrix

    internal fun shapeToString(): String = shape.joinToString("x") { "$it" }

    internal fun isCompliantMatrix(other: FloatMatrix) =
        other.complies({ "Other is of type ${it::class.java}, expected ${this::class.java}" }, { it::class.java == this::class.java })

}

class FloatNxN(dimension: Int, shape: IntArray, matrix: FloatArray) : FloatMatrix(dimension, shape) {

    constructor(dimension: Int, shape: IntArray) : this(dimension, shape, FloatArray(shape.reduce { acc, i -> acc * i }) { 0.0f })
    constructor(dimension: Int, shape: IntArray, value: Float) : this(dimension, shape, FloatArray(shape.reduce { acc, i -> acc * i }) { value })

    init {
        complies("Data must contain $size elements for a matrix of size ${shape.joinToString("x") { "$it" }}") { data.size == size }
        matrix.forEachIndexed { i, value -> data[i] = value }
    }

    override fun plusAssign(other: FloatMatrix) {
        TODO("Not yet implemented")
    }

    override fun minusAssign(other: FloatMatrix) {
        TODO("Not yet implemented")
    }

    override fun timesAssign(other: FloatMatrix) {
        TODO("Not yet implemented")
    }

    override fun divAssign(other: FloatMatrix) {
        TODO("Not yet implemented")
    }

    override fun mult(other: FloatMatrix): FloatMatrix {
        TODO("Not yet implemented")
    }
}

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
        matrix.forEachIndexed { i, value -> data[i] = value }
    }

    override fun plusAssign(other: FloatMatrix) {
        isCompliantMatrix(other)
        other.forEachIndexed { i, term -> data[i] = data[i] + term }
    }

    override fun minusAssign(other: FloatMatrix) {
        isCompliantMatrix(other)
        other.forEachIndexed { i, term -> data[i] = data[i] - term }
    }

    override fun timesAssign(other: FloatMatrix) {
        isCompliantMatrix(other)
        other.forEachIndexed { i, factor -> data[i] = data[i] * factor }
    }

    override fun divAssign(other: FloatMatrix) {
        isCompliantMatrix(other)
        other.forEachIndexed { i, factor -> data[i] = data[i] / factor }
    }

    override fun mult(other: FloatMatrix): FloatNxN {
        if (other.shape[0] != 2)
            throw IllegalArgumentException("Cannot multiply matrices of sizes ${shapeToString()} and ${other.shapeToString()}")

        val result = FloatNxN(2, intArrayOf(shape[0], other.shape[1]))

        //  Multiplying rows of first matrix with columns of second matrix
        val c = other.shape[1]
        for (i in 0 until 2)
            for (j in 0 until c)
                for (k in 0 until 2)
                    result.data[i * c + j] += data[i * 2 + k] * other.data[k * c + j]
        return result
    }

}

class Float3x3(matrix: FloatArray) : FloatMatrix(2, intArrayOf(3, 3)) {

    companion object {
        val IDENTITY = Float2x2(floatArrayOf(
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
        complies("Data must contain 4 elements for a matrix of size 2x2") { data.size == 4 }
        matrix.forEachIndexed { i, value -> data[i] = value }
    }

    override fun plusAssign(other: FloatMatrix) {
        isCompliantMatrix(other)
        other.forEachIndexed { i, term -> data[i] = data[i] + term }
    }

    override fun minusAssign(other: FloatMatrix) {
        isCompliantMatrix(other)
        other.forEachIndexed { i, term -> data[i] = data[i] - term }
    }

    override fun timesAssign(other: FloatMatrix) {
        isCompliantMatrix(other)
        other.forEachIndexed { i, factor -> data[i] = data[i] * factor }
    }

    override fun divAssign(other: FloatMatrix) {
        isCompliantMatrix(other)
        other.forEachIndexed { i, factor -> data[i] = data[i] / factor }
    }

    override fun mult(other: FloatMatrix): FloatNxN {
        if (other.shape[0] != 3)
            throw IllegalArgumentException("Cannot multiply matrices of sizes ${shapeToString()} and ${other.shapeToString()}")

        val result = FloatNxN(3, intArrayOf(shape[0], other.shape[1]))

        //  Multiplying rows of first matrix with columns of second matrix
        val c = other.shape[1]
        for (i in 0 until 3)
            for (j in 0 until c)
                for (k in 0 until 3)
                    result.data[i * c + j] += data[i * 3 + k] * other.data[k * c + j]
        return result
    }

}

class Float4x4(matrix: FloatArray) : FloatMatrix(2, intArrayOf(4, 4)) {

    companion object {
        val IDENTITY = Float2x2(floatArrayOf(
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
        complies("Data must contain 4 elements for a matrix of size 2x2") { data.size == 4 }
        matrix.forEachIndexed { i, value -> data[i] = value }
    }

    override fun plusAssign(other: FloatMatrix) {
        isCompliantMatrix(other)
        other.forEachIndexed { i, term -> data[i] = data[i] + term }
    }

    override fun minusAssign(other: FloatMatrix) {
        isCompliantMatrix(other)
        other.forEachIndexed { i, term -> data[i] = data[i] - term }
    }

    override fun timesAssign(other: FloatMatrix) {
        isCompliantMatrix(other)
        other.forEachIndexed { i, factor -> data[i] = data[i] * factor }
    }

    override fun divAssign(other: FloatMatrix) {
        isCompliantMatrix(other)
        other.forEachIndexed { i, factor -> data[i] = data[i] / factor }
    }

    override fun mult(other: FloatMatrix): FloatNxN {
        if (other.shape[0] != 4)
            throw IllegalArgumentException("Cannot multiply matrices of sizes ${shapeToString()} and ${other.shapeToString()}")

        val result = FloatNxN(4, intArrayOf(shape[0], other.shape[1]))

        //  Multiplying rows of first matrix with columns of second matrix
        val c = other.shape[1]
        for (i in 0 until 4)
            for (j in 0 until c)
                for (k in 0 until 4)
                    result.data[i * c + j] += data[i * 4 + k] * other.data[k * c + j]
        return result
    }

}
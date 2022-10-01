package com.github.polyrocketmatt.kmt.matrix.fl

import com.github.polyrocketmatt.kmt.common.throwIf
import com.github.polyrocketmatt.kmt.matrix.Matrix
import com.github.polyrocketmatt.kmt.vector.fl.Float2
import com.github.polyrocketmatt.kmt.vector.it.Int2
import com.github.polyrocketmatt.kmt.vector.it.IntVector

class Float22(default: Float) : FloatMatrix() {

    companion object {
        val IDENTITY = Float22(0.0f).where { index ->
            if (index !is Int2)
                throw IllegalArgumentException("Index must be Int2")

            if (index.x == index.y)
                1.0f
            else
                0.0f
        }
    }

    private val data = Array(2) { Float2(default) }

    operator fun get(x: Int) = data[x]
    operator fun set(x: Int, y: Int, value: Float) { data[x][y] = value }

    override fun where(predicate: (IntVector) -> Float): Float22 {
        data.forEachIndexed { x, float2 ->
            run {
                float2[0] = predicate(Int2(x, 0))
                float2[1] = predicate(Int2(x, 1))
            }
        }

        return this
    }

    override fun plus(other: Matrix<Float>): Float22 = throwIf(
        Float22(0.0f).where { index ->
        if (index !is Int2)
            throw IllegalArgumentException("Index must be Int2")
        return@where this[index.x][index.y] + (other as Float22)[index.x][index.y]
    }, other !is Float22, "Cannot add Float22 to ${other.javaClass.simpleName}")

    override fun minus(other: Matrix<Float>): Float22 = throwIf(
        Float22(0.0f).where { index ->
            if (index !is Int2)
                throw IllegalArgumentException("Index must be Int2")
            return@where this[index.x][index.y] - (other as Float22)[index.x][index.y]
        }, other !is Float22, "Cannot add Float22 to ${other.javaClass.simpleName}")

    override fun times(other: Matrix<Float>): Float22 = throwIf(
        Float22(0.0f).where { index ->
            if (index !is Int2)
                throw IllegalArgumentException("Index must be Int2")
            return@where this[index.x][index.y] * (other as Float22)[index.x][index.y]
        }, other !is Float22, "Cannot add Float22 to ${other.javaClass.simpleName}")

    override fun div(other: Matrix<Float>): Float22 = throwIf(
        Float22(0.0f).where { index ->
            if (index !is Int2)
                throw IllegalArgumentException("Index must be Int2")
            return@where this[index.x][index.y] / (other as Float22)[index.x][index.y]
        }, other !is Float22, "Cannot add Float22 to ${other.javaClass.simpleName}")

    override fun plusAssign(other: Matrix<Float>) {
        if (other !is Float22)
            throw IllegalArgumentException("Cannot add Float22 to ${other.javaClass.simpleName}")

        for (y in 0..1) for (x in 0..1)
            this[x][y] += other[x][y]
    }

    override fun minusAssign(other: Matrix<Float>) {
        if (other !is Float22)
            throw IllegalArgumentException("Cannot add Float22 to ${other.javaClass.simpleName}")

        for (y in 0..1) for (x in 0..1)
            this[x][y] -= other[x][y]
    }

    override fun timesAssign(other: Matrix<Float>) {
        if (other !is Float22)
            throw IllegalArgumentException("Cannot add Float22 to ${other.javaClass.simpleName}")

        for (y in 0..1) for (x in 0..1)
            this[x][y] *= other[x][y]
    }

    override fun divAssign(other: Matrix<Float>) {
        if (other !is Float22)
            throw IllegalArgumentException("Cannot add Float22 to ${other.javaClass.simpleName}")

        for (y in 0..1) for (x in 0..1)
            this[x][y] /= other[x][y]
    }

    override fun transpose(): Matrix<Float> = Float22(0.0f).where { index ->
        if (index !is Int2)
            throw IllegalArgumentException("Index must be Int2")
        return@where this[index.y][index.x]
    }
}
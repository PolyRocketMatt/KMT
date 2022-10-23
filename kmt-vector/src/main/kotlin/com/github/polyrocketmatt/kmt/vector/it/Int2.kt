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

package com.github.polyrocketmatt.kmt.vector.it

import com.github.polyrocketmatt.kmt.common.dsqrt
import com.github.polyrocketmatt.kmt.common.fastAbs
import com.github.polyrocketmatt.kmt.common.intPow
import com.github.polyrocketmatt.kmt.common.sqrt
import com.github.polyrocketmatt.kmt.common.storage.Tuple2
import com.github.polyrocketmatt.kmt.common.utils.complies
import com.github.polyrocketmatt.kmt.matrix.IntMatrix
import com.github.polyrocketmatt.kmt.matrix.Matrix
import com.github.polyrocketmatt.kmt.matrix.toMatrix
import com.github.polyrocketmatt.kmt.trigonometry.COS
import com.github.polyrocketmatt.kmt.trigonometry.SIN
import com.github.polyrocketmatt.kmt.trigonometry.TAN
import com.github.polyrocketmatt.kmt.vector.Swizzle2
import com.github.polyrocketmatt.kmt.vector.Vector
import com.github.polyrocketmatt.kmt.vector.bl.Bool2
import com.github.polyrocketmatt.kmt.vector.db.Double2
import com.github.polyrocketmatt.kmt.vector.fl.Float2
import com.github.polyrocketmatt.kmt.vector.sh.Short2

/**
 * Convert an int matrix to an int vector.
 *
 * @return An int vector whose components are the elements of the matrix.
 * @throws IllegalArgumentException if the matrix is not a 2x1 or 1x2 matrix.
 */
fun IntMatrix.toInt2(): Int2 {
    complies("Cannot create a Int2 from a IntMatrix with ${this.data.size} elements!") { this.data.size == 2 }
    return Int2(this.data[0], this.data[1])
}

operator fun Int.plus(other: Int2): Int2 = Int2(this + other.x, this + other.y)
operator fun Int.minus(other: Int2): Int2 = Int2(this - other.x, this - other.y)
operator fun Int.times(other: Int2): Int2 = Int2(this * other.x, this * other.y)
operator fun Int.div(other: Int2): Int2 = Int2(this / other.x, this / other.y)

operator fun Float.plus(other: Int2): Float2 = Float2(this + other.x, this + other.y)
operator fun Float.minus(other: Int2): Float2 = Float2(this - other.x, this - other.y)
operator fun Float.times(other: Int2): Float2 = Float2(this * other.x, this * other.y)
operator fun Float.div(other: Int2): Float2 = Float2(this / other.x, this / other.y)

operator fun Double.plus(other: Int2): Double2 = Double2(this + other.x, this + other.y)
operator fun Double.minus(other: Int2): Double2 = Double2(this - other.x, this - other.y)
operator fun Double.times(other: Int2): Double2 = Double2(this * other.x, this * other.y)
operator fun Double.div(other: Int2): Double2 = Double2(this / other.x, this / other.y)

operator fun Short.plus(other: Int2): Int2 = Int2(this + other.x, this + other.y)
operator fun Short.minus(other: Int2): Int2 = Int2(this - other.x, this - other.y)
operator fun Short.times(other: Int2): Int2 = Int2(this * other.x, this * other.y)
operator fun Short.div(other: Int2): Int2 = Int2(this / other.x, this / other.y)

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a 2-dimensional vector of integers.
 *
 * @param x The x component of the vector.
 * @param y The y component of the vector.
 */
class Int2(x: Int, y: Int) : Tuple2<Int>(arrayOf(x, y)), IntVector, Swizzle2 {

    constructor() : this(0, 0)
    constructor(other: Int2) : this(other.x, other.y)
    constructor(x: Int) : this(x, x)

    operator fun plus(other: Int2) = Int2(x + other.x, y + other.y)
    operator fun minus(other: Int2) = Int2(x - other.x, y - other.y)
    operator fun times(other: Int2) = Int2(x * other.x, y * other.y)
    operator fun div(other: Int2) = Int2(x / other.x, y / other.y)

    operator fun plus(other: Float2) = Float2(x + other.x, y + other.y)
    operator fun minus(other: Float2) = Float2(x - other.x, y - other.y)
    operator fun times(other: Float2) = Float2(x * other.x, y * other.y)
    operator fun div(other: Float2) = Float2(x / other.x, y / other.y)

    operator fun plus(other: Double2) = Double2(x + other.x, y + other.y)
    operator fun minus(other: Double2) = Double2(x - other.x, y - other.y)
    operator fun times(other: Double2) = Double2(x * other.x, y * other.y)
    operator fun div(other: Double2) = Double2(x / other.x, y / other.y)

    operator fun plus(other: Short2) = Int2(x + other.x, y + other.y)
    operator fun minus(other: Short2) = Int2(x - other.x, y - other.y)
    operator fun times(other: Short2) = Int2(x * other.x, y * other.y)
    operator fun div(other: Short2) = Int2(x / other.x, y / other.y)

    operator fun plusAssign(other: Int2) { x += other.x; y += other.y }
    operator fun minusAssign(other: Int2) { x -= other.x; y -= other.y }
    operator fun timesAssign(other: Int2) { x *= other.x; y *= other.y }
    operator fun divAssign(other: Int2) { x /= other.x; y /= other.y }

    override operator fun plus(value: Int) = Int2(x + value, y + value)
    override operator fun minus(value: Int) = Int2(x - value, y - value)
    override operator fun times(value: Int) = Int2(x * value, y * value)
    override operator fun div(value: Int) = Int2(x / value, y / value)

    operator fun plus(value: Float) = Float2(x + value, y + value)
    operator fun minus(value: Float) = Float2(x - value, y - value)
    operator fun times(value: Float) = Float2(x * value, y * value)
    operator fun div(value: Float) = Float2(x / value, y / value)

    operator fun plus(value: Double) = Double2(x + value, y + value)
    operator fun minus(value: Double) = Double2(x - value, y - value)
    operator fun times(value: Double) = Double2(x * value, y * value)
    operator fun div(value: Double) = Double2(x / value, y / value)

    operator fun plus(value: Short) = Int2(x + value, y + value)
    operator fun minus(value: Short) = Int2(x - value, y - value)
    operator fun times(value: Short) = Int2(x * value, y * value)
    operator fun div(value: Short) = Int2(x / value, y / value)

    override operator fun plusAssign(value: Int) { x += value; y += value }
    override operator fun minusAssign(value: Int) { x -= value; y -= value }
    override operator fun timesAssign(value: Int) { x *= value; y *= value }
    override operator fun divAssign(value: Int) { x /= value; y /= value }

    override fun length(): Float = (x * x + y * y).sqrt()
    override fun lengthDouble(): Double = (x * x + y * y).dsqrt()
    override fun lengthSq(): Int = x * x + y * y
    override fun max(): Int = if (x > y) x else y
    override fun min(): Int = if (x < y) x else y
    override fun sum(): Int = x + y
    override fun diff(): Int = x - y
    override fun product(): Int = x * y
    override fun div(): Int = x / y
    override fun normalized(): Float2 = Float2(x / length(), y / length())

    override fun dist(other: Vector<Int>): Float {
        if (other is Int2) {
            val dx = x - other.x
            val dy = y - other.y
            return (dx * dx + dy * dy).sqrt()
        } else
            throw IllegalArgumentException("Other must be a Int2")
    }
    override fun distSq(other: Vector<Int>): Float {
        if (other is Int2) {
            val dx = x - other.x
            val dy = y - other.y
            return (dx * dx + dy * dy).toFloat()
        } else
            throw IllegalArgumentException("Other must be a Int2")
    }
    override fun dot(other: Vector<Int>): Float {
        if (other is Int2)
            return (x * other.x + y * other.y).toFloat()
        else
            throw IllegalArgumentException("Other must be a Int2")
    }
    override fun sdot(): Int = x * x + y * y
    override fun unaryMinus(): Int2 = Int2(-x, -y)

    override fun abs(): Int2 = Int2(x.fastAbs(), y.fastAbs())
    override fun avg(): Float = (x + y) / 2.0f
    override fun min(other: Vector<Int>): Int2 {
        return if (other is Int2)
            Int2(if (x < other.x) x else other.x, if (y < other.y) y else other.y)
        else
            throw IllegalArgumentException("Other must be a Int2")
    }
    override fun max(other: Vector<Int>): Int2 {
        return if (other is Int2)
            Int2(if (x > other.x) x else other.x, if (y > other.y) y else other.y)
        else
            throw IllegalArgumentException("Other must be a Int2")
    }
    override fun isIn(min: Int, max: Int): Bool2 = Bool2(x in min..max, y in min..max)
    override fun intPow(n: Int): Int2 = Int2(x.intPow(n), y.intPow(n))

    override fun sin(): Double2 = Double2(SIN[x], SIN[y])
    override fun cos(): Double2 = Double2(COS[x], COS[y])
    override fun tan(): Double2 = Double2(TAN[x], TAN[y])

    override fun asFloat(): Float2 = Float2(x.toFloat(), y.toFloat())
    override fun asDouble(): Double2 = Double2(x.toDouble(), y.toDouble())
    override fun asShort(): Short2 = Short2(x.toShort(), y.toShort())
    override fun asBoolean(): Bool2 = Bool2(x != 0, y != 0)
    override fun asRowMatrix(): IntMatrix = data.toMatrix(intArrayOf(1, 2))
    override fun asColumnMatrix(): IntMatrix = data.toMatrix(intArrayOf(2, 1))

    override fun xy(): Int2 = this
    override fun yx(): Int2 = Int2(y, x)
    override fun xx(): Int2 = Int2(x, x)
    override fun yy(): Int2 = Int2(y, y)

    override fun copyOf(): Int2 = Int2(x, y)

    override fun get(i: Int): Int = data[i]
    override fun get(row: Int, col: Int): Int = throw UnsupportedOperationException("Int2 is considered a vector")

    override fun set(i: Int, value: Int) = when (i) {
        0 -> x = value
        1 -> y = value
        else -> throw IndexOutOfBoundsException("Index $i is out of bounds for Int2")
    }
    override fun set(row: Int, col: Int, value: Int) = throw UnsupportedOperationException("Int2 is considered a vector")

    override fun transpose(): Int2 = this
}

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

package com.github.polyrocketmatt.kmt.vector

import com.github.polyrocketmatt.kmt.common.dsqrt
import com.github.polyrocketmatt.kmt.common.fastAbs
import com.github.polyrocketmatt.kmt.common.intPow
import com.github.polyrocketmatt.kmt.common.sqrt
import com.github.polyrocketmatt.kmt.common.storage.Tuple2
import com.github.polyrocketmatt.kmt.common.storage.Tuple3
import com.github.polyrocketmatt.kmt.common.storage.Tuple4
import com.github.polyrocketmatt.kmt.common.utils.complies
import com.github.polyrocketmatt.kmt.matrix.IntMatrix
import com.github.polyrocketmatt.kmt.matrix.Matrix
import com.github.polyrocketmatt.kmt.matrix.toMatrix
import com.github.polyrocketmatt.kmt.trigonometry.COS
import com.github.polyrocketmatt.kmt.trigonometry.SIN
import com.github.polyrocketmatt.kmt.trigonometry.TAN

fun IntVector.float() = this.asFloat()
fun IntVector.double() = this.asDouble()
fun IntVector.short() = this.asShort()

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

/**
 * Convert an int matrix to an int vector.
 *
 * @return An int vector whose components are the elements of the matrix.
 * @throws IllegalArgumentException if the matrix is not a 3x1 or 1x3 matrix.
 */
fun IntMatrix.toInt3(): Int3 {
    complies("Cannot create a Int3 from a IntMatrix with ${this.data.size} elements!") { this.data.size == 3 }
    return Int3(this.data[0], this.data[1], this.data[2])
}

/**
 * Convert an int matrix to an int vector.
 *
 * @return An int vector whose components are the elements of the matrix.
 * @throws IllegalArgumentException if the matrix does not contain 4 elements.
 */
fun IntMatrix.toInt4(): Int4 {
    complies("Cannot create a Int4 from a IntMatrix with ${this.data.size} elements!") { this.data.size == 4 }
    return Int4(this.data[0], this.data[1], this.data[2], this.data[3])
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

operator fun Int.plus(other: Int3): Int3 = Int3(this + other.x, this + other.y, this + other.z)
operator fun Int.minus(other: Int3): Int3 = Int3(this - other.x, this - other.y, this - other.z)
operator fun Int.times(other: Int3): Int3 = Int3(this * other.x, this * other.y, this * other.z)
operator fun Int.div(other: Int3): Int3 = Int3(this / other.x, this / other.y, this / other.z)

operator fun Float.plus(other: Int3): Float3 = Float3(this + other.x, this + other.y, this + other.z)
operator fun Float.minus(other: Int3): Float3 = Float3(this - other.x, this - other.y, this - other.z)
operator fun Float.times(other: Int3): Float3 = Float3(this * other.x, this * other.y, this * other.z)
operator fun Float.div(other: Int3): Float3 = Float3(this / other.x, this / other.y, this / other.z)

operator fun Double.plus(other: Int3): Double3 = Double3(this + other.x, this + other.y, this + other.z)
operator fun Double.minus(other: Int3): Double3 = Double3(this - other.x, this - other.y, this - other.z)
operator fun Double.times(other: Int3): Double3 = Double3(this * other.x, this * other.y, this * other.z)
operator fun Double.div(other: Int3): Double3 = Double3(this / other.x, this / other.y, this / other.z)

operator fun Short.plus(other: Int3): Int3 = Int3(this + other.x, this + other.y, this + other.z)
operator fun Short.minus(other: Int3): Int3 = Int3(this - other.x, this - other.y, this - other.z)
operator fun Short.times(other: Int3): Int3 = Int3(this * other.x, this * other.y, this * other.z)
operator fun Short.div(other: Int3): Int3 = Int3(this / other.x, this / other.y, this / other.z)

operator fun Int.plus(other: Int4): Int4 = Int4(this + other.x, this + other.y, this + other.z, this + other.w)
operator fun Int.minus(other: Int4): Int4 = Int4(this - other.x, this - other.y, this - other.z, this - other.w)
operator fun Int.times(other: Int4): Int4 = Int4(this * other.x, this * other.y, this * other.z, this * other.w)
operator fun Int.div(other: Int4): Int4 = Int4(this / other.x, this / other.y, this / other.z, this / other.w)

operator fun Float.plus(other: Int4): Float4 = Float4(this + other.x, this + other.y, this + other.z, this + other.w)
operator fun Float.minus(other: Int4): Float4 = Float4(this - other.x, this - other.y, this - other.z, this - other.w)
operator fun Float.times(other: Int4): Float4 = Float4(this * other.x, this * other.y, this * other.z, this * other.w)
operator fun Float.div(other: Int4): Float4 = Float4(this / other.x, this / other.y, this / other.z, this / other.w)

operator fun Double.plus(other: Int4): Double4 = Double4(this + other.x, this + other.y, this + other.z, this + other.w)
operator fun Double.minus(other: Int4): Double4 = Double4(this - other.x, this - other.y, this - other.z, this - other.w)
operator fun Double.times(other: Int4): Double4 = Double4(this * other.x, this * other.y, this * other.z, this * other.w)
operator fun Double.div(other: Int4): Double4 = Double4(this / other.x, this / other.y, this / other.z, this / other.w)

operator fun Short.plus(other: Int4): Int4 = Int4(this + other.x, this + other.y, this + other.z, this + other.w)
operator fun Short.minus(other: Int4): Int4 = Int4(this - other.x, this - other.y, this - other.z, this - other.w)
operator fun Short.times(other: Int4): Int4 = Int4(this * other.x, this * other.y, this * other.z, this * other.w)
operator fun Short.div(other: Int4): Int4 = Int4(this / other.x, this / other.y, this / other.z, this / other.w)

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents an n-dimensional vector of integers.
 */
interface IntVector : Vector<Int>, Matrix<Int> {

    /**
     * Get the vector as a floating point vector.
     *
     * @return The vector as a floating point vector.
     */
    fun asFloat(): FloatVector

    /**
     * Get the vector as a double vector.
     *
     * @return The vector as a double vector.
     */
    fun asDouble(): DoubleVector

    /**
     * Get the vector as a short vector.
     *
     * @return The vector as a short vector.
     */
    fun asShort(): ShortVector

    /**
     * Get the vector as a boolean vector.
     *
     * @return The vector as a boolean vector.
     */
    fun asBoolean(): BooleanVector

    /**
     * Get the vector as an int row matrix.
     *
     * @return The vector as an int row matrix.
     */
    fun asRowMatrix(): IntMatrix

    /**
     * Get the vector as an int column matrix.
     *
     * @return The vector as an int column matrix.
     */
    fun asColumnMatrix(): IntMatrix
}

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
    override fun dot(other: Vector<Int>): Int {
        if (other is Int2)
            return x * other.x + y * other.y
        else
            throw IllegalArgumentException("Other must be a Int2")
    }
    override fun isOrthogonal(other: Vector<Int>): Boolean = dot(other) == 0
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

    @Deprecated("Use operator instead", ReplaceWith("vector[i]"))
    override fun get(i: Int): Int = data[i]
    override fun get(row: Int, col: Int): Int = throw UnsupportedOperationException("Int2 is considered a vector")

    @Deprecated("Use operator instead", ReplaceWith("vector[i] = value"))
    override fun set(i: Int, value: Int) = when (i) {
        0 -> x = value
        1 -> y = value
        else -> throw IndexOutOfBoundsException("Index $i is out of bounds for Int2")
    }
    override fun set(row: Int, col: Int, value: Int) = throw UnsupportedOperationException("Int2 is considered a vector")
}

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a 3-dimensional vector of integers.
 *
 * @param x The x component of the vector.
 * @param y The y component of the vector.
 * @param z The z component of the vector.
 */
class Int3(x: Int, y: Int, z: Int) : Tuple3<Int>(arrayOf(x, y, z)), IntVector, Swizzle3 {

    constructor() : this(0, 0, 0)
    constructor(other: Int3) : this(other.x, other.y, other.z)
    constructor(x: Int) : this(x, x, x)

    operator fun plus(other: Int3) = Int3(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Int3) = Int3(x - other.x, y - other.y, z - other.z)
    operator fun times(other: Int3) = Int3(x * other.x, y * other.y, z * other.z)
    operator fun div(other: Int3) = Int3(x / other.x, y / other.y, z / other.z)

    operator fun plus(other: Float3) = Float3(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Float3) = Float3(x - other.x, y - other.y, z - other.z)
    operator fun times(other: Float3) = Float3(x * other.x, y * other.y, z * other.z)
    operator fun div(other: Float3) = Float3(x / other.x, y / other.y, z / other.z)

    operator fun plus(other: Double3) = Double3(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Double3) = Double3(x - other.x, y - other.y, z - other.z)
    operator fun times(other: Double3) = Double3(x * other.x, y * other.y, z * other.z)
    operator fun div(other: Double3) = Double3(x / other.x, y / other.y, z / other.z)

    operator fun plus(other: Short3) = Int3(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Short3) = Int3(x - other.x, y - other.y, z - other.z)
    operator fun times(other: Short3) = Int3(x * other.x, y * other.y, z * other.z)
    operator fun div(other: Short3) = Int3(x / other.x, y / other.y, z / other.z)

    operator fun plusAssign(other: Int3) { x += other.x; y += other.y; z += other.z }
    operator fun minusAssign(other: Int3) { x -= other.x; y -= other.y; z -= other.z }
    operator fun timesAssign(other: Int3) { x *= other.x; y *= other.y; z *= other.z }
    operator fun divAssign(other: Int3) { x /= other.x; y /= other.y; z /= other.z }

    override operator fun plus(value: Int) = Int3(x + value, y + value, z + value)
    override operator fun minus(value: Int) = Int3(x - value, y - value, z - value)
    override operator fun times(value: Int) = Int3(x * value, y * value, z * value)
    override operator fun div(value: Int) = Int3(x / value, y / value, z / value)

    operator fun plus(value: Float) = Float3(x + value, y + value, z + value)
    operator fun minus(value: Float) = Float3(x - value, y - value, z - value)
    operator fun times(value: Float) = Float3(x * value, y * value, z * value)
    operator fun div(value: Float) = Float3(x / value, y / value, z / value)

    operator fun plus(value: Double) = Double3(x + value, y + value, z + value)
    operator fun minus(value: Double) = Double3(x - value, y - value, z - value)
    operator fun times(value: Double) = Double3(x * value, y * value, z * value)
    operator fun div(value: Double) = Double3(x / value, y / value, z / value)

    operator fun plus(value: Short) = Int3(x + value, y + value, z + value)
    operator fun minus(value: Short) = Int3(x - value, y - value, z - value)
    operator fun times(value: Short) = Int3(x * value, y * value, z * value)
    operator fun div(value: Short) = Int3(x / value, y / value, z / value)

    override operator fun plusAssign(value: Int) { x += value; y += value; z += value }
    override operator fun minusAssign(value: Int) { x -= value; y -= value; z -= value }
    override operator fun timesAssign(value: Int) { x *= value; y *= value; z *= value }
    override operator fun divAssign(value: Int) { x /= value; y /= value; z /= value }

    override fun length(): Float = (x * x + y * y + z * z).sqrt()
    override fun lengthDouble(): Double = (x * x + y * y + z * z).dsqrt()
    override fun lengthSq(): Int = x * x + y * y + z * z
    override fun max(): Int = if (x > y && x > z) x else if (y > z) y else z
    override fun min(): Int = if (x < y && x < z) x else if (y < z) y else z
    override fun sum(): Int = x + y + z
    override fun diff(): Int = x - y - z
    override fun product(): Int = x * y * z
    override fun div(): Int = x / y / z
    override fun normalized(): Float3 = Float3(x / length(), y / length(), z / length())

    override fun dist(other: Vector<Int>): Float {
        if (other is Int3) {
            val dx = x - other.x
            val dy = y - other.y
            val dz = z - other.z
            return (dx * dx + dy * dy + dz * dz).sqrt()
        } else
            throw IllegalArgumentException("Other must be a Int3")
    }
    override fun distSq(other: Vector<Int>): Float {
        if (other is Int3) {
            val dx = x - other.x
            val dy = y - other.y
            val dz = z - other.z
            return (dx * dx + dy * dy + dz * dz).toFloat()
        } else
            throw IllegalArgumentException("Other must be a Int3")
    }
    override fun dot(other: Vector<Int>): Int {
        if (other is Int3) {
            return x * other.x + y * other.y + z * other.z
        } else
            throw IllegalArgumentException("Other must be a Int3")
    }
    override fun isOrthogonal(other: Vector<Int>): Boolean = dot(other) == 0
    override fun sdot(): Int = x * x + y * y + z * z
    override fun unaryMinus(): Int3 = Int3(-x, -y, -z)
    fun cross(other: Double3): Double3 = Double3(
        y * other.z - z * other.y,
        z * other.x - x * other.z,
        x * other.y - y * other.x
    )

    override fun abs(): Int3 = Int3(x.fastAbs(), y.fastAbs(), z.fastAbs())
    override fun avg(): Float = (x + y + z).toFloat() / 3f
    override fun min(other: Vector<Int>): Int3 {
        if (other is Int3) {
            return Int3(
                if (x < other.x) x else other.x,
                if (y < other.y) y else other.y,
                if (z < other.z) z else other.z
            )
        } else
            throw IllegalArgumentException("Other must be a Int3")
    }
    override fun max(other: Vector<Int>): Int3 {
        if (other is Int3) {
            return Int3(
                if (x > other.x) x else other.x,
                if (y > other.y) y else other.y,
                if (z > other.z) z else other.z
            )
        } else
            throw IllegalArgumentException("Other must be a Int3")
    }
    override fun isIn(min: Int, max: Int): Bool3 = Bool3(x in min..max, y in min..max, z in min..max)
    override fun intPow(n: Int): Int3 = Int3(x.intPow(n), y.intPow(n), z.intPow(n))

    override fun sin(): Double3 = Double3(SIN[x], SIN[y], SIN[z])
    override fun cos(): Double3 = Double3(COS[x], COS[y], COS[z])
    override fun tan(): Double3 = Double3(TAN[x], TAN[y], TAN[z])

    override fun asFloat(): Float3 = Float3(x.toFloat(), y.toFloat(), z.toFloat())
    override fun asDouble(): Double3 = Double3(x.toDouble(), y.toDouble(), z.toDouble())
    override fun asShort(): Short3 = Short3(x.toShort(), y.toShort(), z.toShort())
    override fun asBoolean(): Bool3 = Bool3(x != 0, y != 0, z != 0)
    override fun asRowMatrix(): IntMatrix = data.toMatrix(intArrayOf(1, 3))
    override fun asColumnMatrix(): IntMatrix = data.toMatrix(intArrayOf(3, 1))

    override fun xy(): Int2 = Int2(x, y)
    override fun yz(): Int2 = Int2(y, z)
    override fun xz(): Int2 = Int2(x, z)
    override fun yx(): Int2 = Int2(y, x)
    override fun zy(): Int2 = Int2(z, y)
    override fun zx(): Int2 = Int2(z, x)
    override fun xyz(): Int3 = Int3(x, y, z)
    override fun xzy(): Int3 = Int3(x, z, y)
    override fun yxz(): Int3 = Int3(y, x, z)
    override fun yzx(): Int3 = Int3(y, z, x)
    override fun zxy(): Int3 = Int3(z, x, y)
    override fun zyx(): Int3 = Int3(z, y, x)
    override fun xxx(): Int3 = Int3(x, x, x)
    override fun yyy(): Int3 = Int3(y, y, y)
    override fun zzz(): Int3 = Int3(z, z, z)

    override fun copyOf(): Int3 = Int3(x, y, z)

    @Deprecated("Use operator instead", ReplaceWith("vector[i]"))
    override fun get(i: Int): Int = data[i]
    override fun get(row: Int, col: Int): Int = throw UnsupportedOperationException("Int3 is considered a vector")

    @Deprecated("Use operator instead", ReplaceWith("vector[i] = value"))
    override fun set(i: Int, value: Int) = when (i) {
        0 -> x = value
        1 -> y = value
        2 -> z = value
        else -> throw IndexOutOfBoundsException("Index $i is out of bounds for Int3")
    }
    override fun set(row: Int, col: Int, value: Int) = throw UnsupportedOperationException("Int3 is considered a vector")
}

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a 4-dimensional vector of integers.
 *
 * @param x The x component of the vector.
 * @param y The y component of the vector.
 * @param z The z component of the vector.
 * @param w The w component of the vector.
 */
class Int4(x: Int, y: Int, z: Int, w: Int) : Tuple4<Int>(arrayOf(x, y, z, w)), IntVector, Swizzle4 {

    constructor() : this(0, 0, 0, 0)
    constructor(other: Int4) : this(other.x, other.y, other.z, other.w)
    constructor(x: Int) : this(x, x, x, x)

    operator fun plus(other: Int4) = Int4(x + other.x, y + other.y, z + other.z, w + other.w)
    operator fun minus(other: Int4) = Int4(x - other.x, y - other.y, z - other.z, w - other.w)
    operator fun times(other: Int4) = Int4(x * other.x, y * other.y, z * other.z, w * other.w)
    operator fun div(other: Int4) = Int4(x / other.x, y / other.y, z / other.z, w / other.w)

    operator fun plus(other: Float4) = Float4(x + other.x, y + other.y, z + other.z, w + other.w)
    operator fun minus(other: Float4) = Float4(x - other.x, y - other.y, z - other.z, w - other.w)
    operator fun times(other: Float4) = Float4(x * other.x, y * other.y, z * other.z, w * other.w)
    operator fun div(other: Float4) = Float4(x / other.x, y / other.y, z / other.z, w / other.w)

    operator fun plus(other: Double4) = Double4(x + other.x, y + other.y, z + other.z, w + other.w)
    operator fun minus(other: Double4) = Double4(x - other.x, y - other.y, z - other.z, w - other.w)
    operator fun times(other: Double4) = Double4(x * other.x, y * other.y, z * other.z, w * other.w)
    operator fun div(other: Double4) = Double4(x / other.x, y / other.y, z / other.z, w / other.w)

    operator fun plus(other: Short4) = Int4(x + other.x, y + other.y, z + other.z, w + other.w)
    operator fun minus(other: Short4) = Int4(x - other.x, y - other.y, z - other.z, w - other.w)
    operator fun times(other: Short4) = Int4(x * other.x, y * other.y, z * other.z, w * other.w)
    operator fun div(other: Short4) = Int4(x / other.x, y / other.y, z / other.z, w / other.w)

    operator fun plusAssign(other: Int4) { x += other.x; y += other.y; z += other.z; w += other.w }
    operator fun minusAssign(other: Int4) { x -= other.x; y -= other.y; z -= other.z; w -= other.w }
    operator fun timesAssign(other: Int4) { x *= other.x; y *= other.y; z *= other.z; w *= other.w }
    operator fun divAssign(other: Int4) { x /= other.x; y /= other.y; z /= other.z; w /= other.w }

    override operator fun plus(value: Int) = Int4(x + value, y + value, z + value, w + value)
    override operator fun minus(value: Int) = Int4(x - value, y - value, z - value, w - value)
    override operator fun times(value: Int) = Int4(x * value, y * value, z * value, w * value)
    override operator fun div(value: Int) = Int4(x / value, y / value, z / value, w / value)

    operator fun plus(value: Float) = Float4(x + value, y + value, z + value, w + value)
    operator fun minus(value: Float) = Float4(x - value, y - value, z - value, w - value)
    operator fun times(value: Float) = Float4(x * value, y * value, z * value, w * value)
    operator fun div(value: Float) = Float4(x / value, y / value, z / value, w / value)

    operator fun plus(value: Double) = Double4(x + value, y + value, z + value, w + value)
    operator fun minus(value: Double) = Double4(x - value, y - value, z - value, w - value)
    operator fun times(value: Double) = Double4(x * value, y * value, z * value, w * value)
    operator fun div(value: Double) = Double4(x / value, y / value, z / value, w / value)

    operator fun plus(value: Short) = Int4(x + value, y + value, z + value, w + value)
    operator fun minus(value: Short) = Int4(x - value, y - value, z - value, w - value)
    operator fun times(value: Short) = Int4(x * value, y * value, z * value, w * value)
    operator fun div(value: Short) = Int4(x / value, y / value, z / value, w / value)

    override operator fun plusAssign(value: Int) { x += value; y += value; z += value; w += value }
    override operator fun minusAssign(value: Int) { x -= value; y -= value; z -= value; w -= value }
    override operator fun timesAssign(value: Int) { x *= value; y *= value; z *= value; w *= value }
    override operator fun divAssign(value: Int) { x /= value; y /= value; z /= value; w /= value }

    override fun length(): Float = (x * x + y * y + z * z).sqrt()
    override fun lengthDouble(): Double = (x * x + y * y + z * z).dsqrt()
    override fun lengthSq(): Int = x * x + y * y + z * z
    override fun max(): Int = if (x > y && x > z && x > w) x else if (y > z && y > w) y else if (z > w) z else w
    override fun min(): Int = if (x < y && x < z && x < w) x else if (y < z && y < w) y else if (z < w) z else w
    override fun sum(): Int = x + y + z + w
    override fun diff(): Int = x - y - z - w
    override fun product(): Int = x * y * z * w
    override fun div(): Int = x / y / z / w
    override fun normalized(): Float4 = Float4(x / length(), y / length(), z / length(), w / length())

    override fun dist(other: Vector<Int>): Float {
        if (other is Int4) {
            val dx = x - other.x
            val dy = y - other.y
            val dz = z - other.z
            val dw = w - other.w
            return (dx * dx + dy * dy + dz * dz + dw * dw).sqrt()
        } else
            throw IllegalArgumentException("Other must be a Int4")
    }
    override fun distSq(other: Vector<Int>): Float {
        if (other is Int4) {
            val dx = x - other.x
            val dy = y - other.y
            val dz = z - other.z
            val dw = w - other.w
            return (dx * dx + dy * dy + dz * dz + dw * dw).toFloat()
        } else
            throw IllegalArgumentException("Other must be a Int4")
    }
    override fun dot(other: Vector<Int>): Int {
        if (other is Int4) {
            return x * other.x + y * other.y + z * other.z + w * other.w
        } else
            throw IllegalArgumentException("Other must be a Int4")
    }
    override fun isOrthogonal(other: Vector<Int>): Boolean = dot(other) == 0
    override fun sdot(): Int = x * x + y * y + z * z + w * w
    override fun unaryMinus(): Int4 = Int4(-x, -y, -z, -w)

    override fun abs(): Int4 = Int4(x.fastAbs(), y.fastAbs(), z.fastAbs(), w.fastAbs())
    override fun avg(): Float = (x + y + z + w).toFloat() / 4f
    override fun min(other: Vector<Int>): Int4 {
        if (other is Int4) {
            return Int4(
                if (x < other.x) x else other.x,
                if (y < other.y) y else other.y,
                if (z < other.z) z else other.z,
                if (w < other.w) w else other.w
            )
        } else
            throw IllegalArgumentException("Other must be a Int4")
    }
    override fun max(other: Vector<Int>): Int4 {
        if (other is Int4) {
            return Int4(
                if (x > other.x) x else other.x,
                if (y > other.y) y else other.y,
                if (z > other.z) z else other.z,
                if (w > other.w) w else other.w
            )
        } else
            throw IllegalArgumentException("Other must be a Int4")
    }
    override fun isIn(min: Int, max: Int): Bool4 = Bool4(x in min..max, y in min..max, z in min..max, w in min..max)
    override fun intPow(n: Int): Int4 = Int4(x.intPow(n), y.intPow(n), z.intPow(n), w.intPow(n))

    override fun sin(): Double4 = Double4(SIN[x], SIN[y], SIN[z], SIN[w])
    override fun cos(): Double4 = Double4(COS[x], COS[y], COS[z], COS[w])
    override fun tan(): Double4 = Double4(TAN[x], TAN[y], TAN[z], TAN[w])

    override fun asFloat(): Float4 = Float4(x.toFloat(), y.toFloat(), z.toFloat(), w.toFloat())
    override fun asDouble(): Double4 = Double4(x.toDouble(), y.toDouble(), z.toDouble(), w.toDouble())
    override fun asShort(): Short4 = Short4(x.toShort(), y.toShort(), z.toShort(), w.toShort())
    override fun asBoolean(): Bool4 = Bool4(x != 0, y != 0, z != 0, w != 0)
    override fun asRowMatrix(): IntMatrix = data.toMatrix(intArrayOf(1, 4))
    override fun asColumnMatrix(): IntMatrix = data.toMatrix(intArrayOf(4, 1))

    override fun xy(): Int2 = Int2(x, y)
    override fun xz(): Int2 = Int2(x, z)
    override fun xw(): Int2 = Int2(x, w)
    override fun yx(): Int2 = Int2(y, x)
    override fun yz(): Int2 = Int2(y, z)
    override fun yw(): Int2 = Int2(y, w)
    override fun zx(): Int2 = Int2(z, x)
    override fun zy(): Int2 = Int2(z, y)
    override fun zw(): Int2 = Int2(z, w)
    override fun wx(): Int2 = Int2(w, x)
    override fun wy(): Int2 = Int2(w, y)
    override fun wz(): Int2 = Int2(w, z)
    override fun xyz(): Int3 = Int3(x, y, z)
    override fun xyw(): Int3 = Int3(x, y, w)
    override fun xzy(): Int3 = Int3(x, z, y)
    override fun xzw(): Int3 = Int3(x, z, w)
    override fun xwy(): Int3 = Int3(x, w, y)
    override fun xwz(): Int3 = Int3(x, w, z)
    override fun yxz(): Int3 = Int3(y, x, z)
    override fun yxw(): Int3 = Int3(y, x, w)
    override fun yzx(): Int3 = Int3(y, z, x)
    override fun yzw(): Int3 = Int3(y, z, w)
    override fun ywx(): Int3 = Int3(y, w, x)
    override fun ywz(): Int3 = Int3(y, w, z)
    override fun zxy(): Int3 = Int3(z, x, y)
    override fun zxw(): Int3 = Int3(z, x, w)
    override fun zyx(): Int3 = Int3(z, y, x)
    override fun zyw(): Int3 = Int3(z, y, w)
    override fun zwx(): Int3 = Int3(z, w, x)
    override fun zwy(): Int3 = Int3(z, w, y)
    override fun wxy(): Int3 = Int3(w, x, y)
    override fun wxz(): Int3 = Int3(w, x, z)
    override fun wyx(): Int3 = Int3(w, y, x)
    override fun wyz(): Int3 = Int3(w, y, z)
    override fun wzx(): Int3 = Int3(w, z, x)
    override fun wzy(): Int3 = Int3(w, z, y)
    override fun xyzw(): Int4 = this
    override fun xywz(): Int4 = Int4(x, y, w, z)
    override fun xzyw(): Int4 = Int4(x, z, y, w)
    override fun xzwy(): Int4 = Int4(x, z, w, y)
    override fun xwyz(): Int4 = Int4(x, w, y, z)
    override fun xwzy(): Int4 = Int4(x, w, z, y)
    override fun yxzw(): Int4 = Int4(y, x, z, w)
    override fun yxwz(): Int4 = Int4(y, x, w, z)
    override fun yzxw(): Int4 = Int4(y, z, x, w)
    override fun yzwx(): Int4 = Int4(y, z, w, x)
    override fun ywxz(): Int4 = Int4(y, w, x, z)
    override fun ywzx(): Int4 = Int4(y, w, z, x)
    override fun zxyw(): Int4 = Int4(z, x, y, w)
    override fun zxwy(): Int4 = Int4(z, x, w, y)
    override fun zyxw(): Int4 = Int4(z, y, x, w)
    override fun zywx(): Int4 = Int4(z, y, w, x)
    override fun zwxy(): Int4 = Int4(z, w, x, y)
    override fun zwyx(): Int4 = Int4(z, w, y, x)
    override fun wxyz(): Int4 = Int4(w, x, y, z)
    override fun wxzy(): Int4 = Int4(w, x, z, y)
    override fun wyxz(): Int4 = Int4(w, y, x, z)
    override fun wyzx(): Int4 = Int4(w, y, z, x)
    override fun wzxy(): Int4 = Int4(w, z, x, y)
    override fun wzyx(): Int4 = Int4(w, z, y, x)
    override fun xxxx(): Int4 = Int4(x, x, x, x)
    override fun yyyy(): Int4 = Int4(y, y, y, y)
    override fun zzzz(): Int4 = Int4(z, z, z, z)
    override fun wwww(): Int4 = Int4(w, w, w, w)

    override fun copyOf(): Int4 = Int4(x, y, z, w)

    @Deprecated("Use operator instead", ReplaceWith("vector[i]"))
    override fun get(i: Int): Int = data[i]
    override fun get(row: Int, col: Int): Int = throw UnsupportedOperationException("Int4 is considered a vector")

    @Deprecated("Use operator instead", ReplaceWith("vector[i] = value"))
    override fun set(i: Int, value: Int) = when (i) {
        0 -> x = value
        1 -> y = value
        2 -> z = value
        3 -> w = value
        else -> throw IndexOutOfBoundsException("Index $i is out of bounds for Int4")
    }
    override fun set(row: Int, col: Int, value: Int) = throw UnsupportedOperationException("Int4 is considered a vector")
}

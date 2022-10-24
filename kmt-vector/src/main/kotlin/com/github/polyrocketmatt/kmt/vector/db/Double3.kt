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

package com.github.polyrocketmatt.kmt.vector.db

import com.github.polyrocketmatt.kmt.common.clip
import com.github.polyrocketmatt.kmt.common.dsqrt
import com.github.polyrocketmatt.kmt.common.fastAbs
import com.github.polyrocketmatt.kmt.common.fastCeil
import com.github.polyrocketmatt.kmt.common.intPow
import com.github.polyrocketmatt.kmt.common.lerp
import com.github.polyrocketmatt.kmt.common.smoothStep
import com.github.polyrocketmatt.kmt.common.smootherStep
import com.github.polyrocketmatt.kmt.common.sqrt
import com.github.polyrocketmatt.kmt.common.storage.Tuple3
import com.github.polyrocketmatt.kmt.common.utils.complies
import com.github.polyrocketmatt.kmt.matrix.DoubleMatrix
import com.github.polyrocketmatt.kmt.matrix.Matrix
import com.github.polyrocketmatt.kmt.matrix.toMatrix
import com.github.polyrocketmatt.kmt.trigonometry.COS
import com.github.polyrocketmatt.kmt.trigonometry.SIN
import com.github.polyrocketmatt.kmt.trigonometry.TAN
import com.github.polyrocketmatt.kmt.vector.Swizzle3
import com.github.polyrocketmatt.kmt.vector.Vector
import com.github.polyrocketmatt.kmt.vector.bl.Bool3
import com.github.polyrocketmatt.kmt.vector.fl.Float3
import com.github.polyrocketmatt.kmt.vector.it.Int3
import com.github.polyrocketmatt.kmt.vector.sh.Short3

/**
 * Convert a double matrix to a double vector.
 *
 * @return A double vector whose components are the elements of the matrix.
 * @throws IllegalArgumentException if the matrix is not a 3x1 or 1x3 matrix.
 */
fun DoubleMatrix.toDouble3(): Double3 {
    complies("Cannot create a Double3 from a DoubleMatrix with ${this.data.size} elements!") { this.data.size == 3 }
    return Double3(this.data[0], this.data[1], this.data[2])
}

operator fun Int.plus(other: Double3): Double3 = Double3(this + other.x, this + other.y, this + other.z)
operator fun Int.minus(other: Double3): Double3 = Double3(this - other.x, this - other.y, this - other.z)
operator fun Int.times(other: Double3): Double3 = Double3(this * other.x, this * other.y, this * other.z)
operator fun Int.div(other: Double3): Double3 = Double3(this / other.x, this / other.y, this / other.z)

operator fun Float.plus(other: Double3): Double3 = Double3(this + other.x, this + other.y, this + other.z)
operator fun Float.minus(other: Double3): Double3 = Double3(this - other.x, this - other.y, this - other.z)
operator fun Float.times(other: Double3): Double3 = Double3(this * other.x, this * other.y, this * other.z)
operator fun Float.div(other: Double3): Double3 = Double3(this / other.x, this / other.y, this / other.z)

operator fun Double.plus(other: Double3): Double3 = Double3(this + other.x, this + other.y, this + other.z)
operator fun Double.minus(other: Double3): Double3 = Double3(this - other.x, this - other.y, this - other.z)
operator fun Double.times(other: Double3): Double3 = Double3(this * other.x, this * other.y, this * other.z)
operator fun Double.div(other: Double3): Double3 = Double3(this / other.x, this / other.y, this / other.z)

operator fun Short.plus(other: Double3): Double3 = Double3(this + other.x, this + other.y, this + other.z)
operator fun Short.minus(other: Double3): Double3 = Double3(this - other.x, this - other.y, this - other.z)
operator fun Short.times(other: Double3): Double3 = Double3(this * other.x, this * other.y, this * other.z)
operator fun Short.div(other: Double3): Double3 = Double3(this / other.x, this / other.y, this / other.z)

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a 3-dimensional vector of doubles.
 *
 * @param x The x component of the vector.
 * @param y The y component of the vector.
 * @param z The z component of the vector.
 */
class Double3(x: Double, y: Double, z: Double) : Tuple3<Double>(arrayOf(x, y, z)), DoubleVector, Swizzle3 {

    constructor() : this(0.0, 0.0, 0.0)
    constructor(other: Double3) : this(other.x, other.y, other.z)
    constructor(x: Double) : this(x, x, x)

    operator fun plus(other: Int3) = Double3(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Int3) = Double3(x - other.x, y - other.y, z - other.z)
    operator fun times(other: Int3) = Double3(x * other.x, y * other.y, z * other.z)
    operator fun div(other: Int3) = Double3(x / other.x, y / other.y, z / other.z)

    operator fun plus(other: Float3) = Double3(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Float3) = Double3(x - other.x, y - other.y, z - other.z)
    operator fun times(other: Float3) = Double3(x * other.x, y * other.y, z * other.z)
    operator fun div(other: Float3) = Double3(x / other.x, y / other.y, z / other.z)

    operator fun plus(other: Double3) = Double3(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Double3) = Double3(x - other.x, y - other.y, z - other.z)
    operator fun times(other: Double3) = Double3(x * other.x, y * other.y, z * other.z)
    operator fun div(other: Double3) = Double3(x / other.x, y / other.y, z / other.z)

    operator fun plus(other: Short3) = Double3(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Short3) = Double3(x - other.x, y - other.y, z - other.z)
    operator fun times(other: Short3) = Double3(x * other.x, y * other.y, z * other.z)
    operator fun div(other: Short3) = Double3(x / other.x, y / other.y, z / other.z)

    operator fun plusAssign(other: Double3) { x += other.x; y += other.y; z += other.z }
    operator fun minusAssign(other: Double3) { x -= other.x; y -= other.y; z -= other.z }
    operator fun timesAssign(other: Double3) { x *= other.x; y *= other.y; z *= other.z }
    operator fun divAssign(other: Double3) { x /= other.x; y /= other.y; z /= other.z }

    operator fun plus(value: Int) = Double3(x + value, y + value, z + value)
    operator fun minus(value: Int) = Double3(x - value, y - value, z - value)
    operator fun times(value: Int) = Double3(x * value, y * value, z * value)
    operator fun div(value: Int) = Double3(x / value, y / value, z / value)

    operator fun plus(value: Float) = Double3(x + value, y + value, z + value)
    operator fun minus(value: Float) = Double3(x - value, y - value, z - value)
    operator fun times(value: Float) = Double3(x * value, y * value, z * value)
    operator fun div(value: Float) = Double3(x / value, y / value, z / value)

    override operator fun plus(value: Double) = Double3(x + value, y + value, z + value)
    override operator fun minus(value: Double) = Double3(x - value, y - value, z - value)
    override operator fun times(value: Double) = Double3(x * value, y * value, z * value)
    override operator fun div(value: Double) = Double3(x / value, y / value, z / value)

    operator fun plus(value: Short) = Double3(x + value, y + value, z + value)
    operator fun minus(value: Short) = Double3(x - value, y - value, z - value)
    operator fun times(value: Short) = Double3(x * value, y * value, z * value)
    operator fun div(value: Short) = Double3(x / value, y / value, z / value)

    override operator fun plusAssign(value: Double) { x += value; y += value; z += value }
    override operator fun minusAssign(value: Double) { x -= value; y -= value; z -= value }
    override operator fun timesAssign(value: Double) { x *= value; y *= value; z *= value }
    override operator fun divAssign(value: Double) { x /= value; y /= value; z /= value }

    override fun length(): Float = (x * x + y * y + z * z).sqrt()
    override fun lengthDouble(): Double = (x * x + y * y + z * z).dsqrt()
    override fun lengthSq(): Double = x * x + y * y + z * z
    override fun max(): Double = if (x > y && x > z) x else if (y > z) y else z
    override fun min(): Double = if (x < y && x < z) x else if (y < z) y else z
    override fun sum(): Double = x + y + z
    override fun diff(): Double = x - y - z
    override fun product(): Double = x * y * z
    override fun div(): Double = x / y / z
    override fun normalized(): Float3 = Double3(x / length(), y / length(), z / length()).asFloat()

    override fun dist(other: Vector<Double>): Float {
        if (other is Double3) {
            val dx = x - other.x
            val dy = y - other.y
            val dz = z - other.z
            return (dx * dx + dy * dy + dz * dz).sqrt()
        } else
            throw IllegalArgumentException("Other must be a Double3")
    }
    override fun distSq(other: Vector<Double>): Float {
        if (other is Double3) {
            val dx = x - other.x
            val dy = y - other.y
            val dz = z - other.z
            return (dx * dx + dy * dy + dz * dz).toFloat()
        } else
            throw IllegalArgumentException("Other must be a Double3")
    }
    override fun dot(other: Vector<Double>): Float {
        if (other is Double3) {
            return (x * other.x + y * other.y + z * other.z).toFloat()
        } else
            throw IllegalArgumentException("Other must be a Double3")
    }
    override fun sdot(): Double = x * x + y * y + z * z
    override fun unaryMinus(): Double3 = Double3(-x, -y, -z)
    fun cross(other: Double3): Double3 = Double3(
        y * other.z - z * other.y,
        z * other.x - x * other.z,
        x * other.y - y * other.x
    )

    override fun abs(): Double3 = Double3(x.fastAbs(), y.fastAbs(), z.fastAbs())
    override fun avg(): Float = (x + y + z).toFloat() / 3f
    override fun min(other: Vector<Double>): Double3 {
        if (other is Double3) {
            return Double3(
                if (x < other.x) x else other.x,
                if (y < other.y) y else other.y,
                if (z < other.z) z else other.z
            )
        } else
            throw IllegalArgumentException("Other must be a Double3")
    }
    override fun max(other: Vector<Double>): Double3 {
        if (other is Double3) {
            return Double3(
                if (x > other.x) x else other.x,
                if (y > other.y) y else other.y,
                if (z > other.z) z else other.z
            )
        } else
            throw IllegalArgumentException("Other must be a Double3")
    }
    override fun isIn(min: Double, max: Double): Bool3 = Bool3(x in min..max, y in min..max, z in min..max)
    override fun intPow(n: Int): Double3 = Double3(x.intPow(n), y.intPow(n), z.intPow(n))

    override fun smoothStep(): Double3 = Double3(x.smoothStep(), y.smoothStep(), z.smoothStep())
    override fun smootherStep(): Double3 = Double3(x.smootherStep(), y.smootherStep(), z.smootherStep())
    override fun lerp(min: Double, max: Double): Double3 = Double3(x.lerp(min, max), y.lerp(min, max), z.lerp(min, max))
    override fun clip(min: Double, max: Double): Double3 = Double3(x.clip(min, max), y.clip(min, max), z.clip(min, max))

    override fun sin(): Double3 = Double3(SIN[x], SIN[y], SIN[z])
    override fun cos(): Double3 = Double3(COS[x], COS[y], COS[z])
    override fun tan(): Double3 = Double3(TAN[x], TAN[y], TAN[z])

    override fun floor(): Int3 = Int3(x.toInt(), y.toInt(), z.toInt())
    override fun ceil(): Int3 = Int3(x.fastCeil(), y.fastCeil(), z.fastCeil())
    override fun fract(): Double3 = Double3(x - x.toInt(), y - y.toInt(), z - z.toInt())

    override fun asFloat(): Float3 = Float3(x.toFloat(), y.toFloat(), z.toFloat())
    override fun asInt(): Int3 = Int3(x.toInt(), y.toInt(), z.toInt())
    override fun asShort(): Short3 = Short3(x.toInt().toShort(), y.toInt().toShort(), z.toInt().toShort())
    override fun asBoolean(): Bool3 = Bool3(x != 0.0, y != 0.0, z != 0.0)
    override fun asRowMatrix(): DoubleMatrix = data.toMatrix(intArrayOf(1, 3))
    override fun asColumnMatrix(): DoubleMatrix = data.toMatrix(intArrayOf(3, 1))

    override fun xy(): Double2 = Double2(x, y)
    override fun yz(): Double2 = Double2(y, z)
    override fun xz(): Double2 = Double2(x, z)
    override fun yx(): Double2 = Double2(y, x)
    override fun zy(): Double2 = Double2(z, y)
    override fun zx(): Double2 = Double2(z, x)
    override fun xyz(): Double3 = Double3(x, y, z)
    override fun xzy(): Double3 = Double3(x, z, y)
    override fun yxz(): Double3 = Double3(y, x, z)
    override fun yzx(): Double3 = Double3(y, z, x)
    override fun zxy(): Double3 = Double3(z, x, y)
    override fun zyx(): Double3 = Double3(z, y, x)
    override fun xxx(): Double3 = Double3(x, x, x)
    override fun yyy(): Double3 = Double3(y, y, y)
    override fun zzz(): Double3 = Double3(z, z, z)

    override fun copyOf(): Double3 = Double3(x, y, z)

    @Deprecated("Use operator instead", ReplaceWith("vector[i]"))
    override fun get(i: Int): Double = data[i]
    override fun get(row: Int, col: Int): Double = throw UnsupportedOperationException("Double3 is considered a vector")

    @Deprecated("Use operator instead", ReplaceWith("vector[i] = value"))
    override fun set(i: Int, value: Double) = when (i) {
        0 -> x = value
        1 -> y = value
        2 -> z = value
        else -> throw IndexOutOfBoundsException("Index $i is out of bounds for Double3")
    }
    override fun set(row: Int, col: Int, value: Double) = throw UnsupportedOperationException("Double3 is considered a vector")

    override fun transpose(): Double3 = this

    override fun trace(): Double = throw UnsupportedOperationException("Cannot get trace of a double vector")

    override fun diag(): Matrix<Double> = throw UnsupportedOperationException("Cannot get diagonal of a double vector")

    override fun concatHorizontal(other: Matrix<Double>): Matrix<Double> = throw UnsupportedOperationException("Cannot concatenate a double vector horizontally")

    override fun concatVertical(other: Matrix<Double>): Matrix<Double> = throw UnsupportedOperationException("Cannot concatenate a double vector vertically")

    override fun isScalar(): Boolean = false

    override fun isSquare(): Boolean = false
}

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

package com.github.polyrocketmatt.kmt.vector.sh

import com.github.polyrocketmatt.kmt.common.dsqrt
import com.github.polyrocketmatt.kmt.common.fastAbs
import com.github.polyrocketmatt.kmt.common.intPow
import com.github.polyrocketmatt.kmt.common.sqrt
import com.github.polyrocketmatt.kmt.common.storage.Tuple3
import com.github.polyrocketmatt.kmt.common.utils.complies
import com.github.polyrocketmatt.kmt.matrix.ShortMatrix
import com.github.polyrocketmatt.kmt.matrix.toMatrix
import com.github.polyrocketmatt.kmt.trigonometry.COS
import com.github.polyrocketmatt.kmt.trigonometry.SIN
import com.github.polyrocketmatt.kmt.trigonometry.TAN
import com.github.polyrocketmatt.kmt.vector.Swizzle3
import com.github.polyrocketmatt.kmt.vector.Vector
import com.github.polyrocketmatt.kmt.vector.bl.Bool3
import com.github.polyrocketmatt.kmt.vector.db.Double3
import com.github.polyrocketmatt.kmt.vector.fl.Float3
import com.github.polyrocketmatt.kmt.vector.it.Int3

/**
 * Convert a short matrix to a short vector.
 *
 * @return A short vector whose components are the elements of the matrix.
 * @throws IllegalArgumentException if the matrix is not a 3x1 or 1x3 matrix.
 */
fun ShortMatrix.toShort3(): Short3 {
    complies("Cannot create a Short3 from a ShortMatrix with ${this.data.size} elements!") { this.data.size == 3 }
    return Short3(this.data[0], this.data[1], this.data[2])
}

operator fun Int.plus(other: Short3): Int3 = Int3(this + other.x, this + other.y, this + other.z)
operator fun Int.minus(other: Short3): Int3 = Int3(this - other.x, this - other.y, this - other.z)
operator fun Int.times(other: Short3): Int3 = Int3(this * other.x, this * other.y, this * other.z)
operator fun Int.div(other: Short3): Int3 = Int3(this / other.x, this / other.y, this / other.z)

operator fun Float.plus(other: Short3): Float3 = Float3(this + other.x, this + other.y, this + other.z)
operator fun Float.minus(other: Short3): Float3 = Float3(this - other.x, this - other.y, this - other.z)
operator fun Float.times(other: Short3): Float3 = Float3(this * other.x, this * other.y, this * other.z)
operator fun Float.div(other: Short3): Float3 = Float3(this / other.x, this / other.y, this / other.z)

operator fun Double.plus(other: Short3): Double3 = Double3(this + other.x, this + other.y, this + other.z)
operator fun Double.minus(other: Short3): Double3 = Double3(this - other.x, this - other.y, this - other.z)
operator fun Double.times(other: Short3): Double3 = Double3(this * other.x, this * other.y, this * other.z)
operator fun Double.div(other: Short3): Double3 = Double3(this / other.x, this / other.y, this / other.z)

operator fun Short.plus(other: Short3): Short3 = Int3(this + other.x, this + other.y, this + other.z).asShort()
operator fun Short.minus(other: Short3): Short3 = Int3(this - other.x, this - other.y, this - other.z).asShort()
operator fun Short.times(other: Short3): Short3 = Int3(this * other.x, this * other.y, this * other.z).asShort()
operator fun Short.div(other: Short3): Short3 = Int3(this / other.x, this / other.y, this / other.z).asShort()

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a 3-dimensional vector of shorts.
 *
 * @param x The x component of the vector.
 * @param y The y component of the vector.
 * @param z The z component of the vector.
 */
class Short3(x: Short, y: Short, z: Short) : Tuple3<Short>(arrayOf(x, y, z)), ShortVector, Swizzle3 {

    constructor() : this(0, 0, 0)
    constructor(other: Short3) : this(other.x, other.y, other.z)
    constructor(x: Short) : this(x, x, x)

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

    operator fun plus(other: Short3) = Int3(x + other.x, y + other.y, z + other.z).asShort()
    operator fun minus(other: Short3) = Int3(x - other.x, y - other.y, z - other.z).asShort()
    operator fun times(other: Short3) = Int3(x * other.x, y * other.y, z * other.z).asShort()
    operator fun div(other: Short3) = Int3(x / other.x, y / other.y, z / other.z).asShort()

    operator fun plusAssign(other: Short3) { x = (x + other.x).toShort(); y = (y + other.y).toShort(); z = (z + other.z).toShort() }
    operator fun minusAssign(other: Short3) { x = (x - other.x).toShort(); y = (y - other.y).toShort(); z = (z - other.z).toShort() }
    operator fun timesAssign(other: Short3) { x = (x * other.x).toShort(); y = (y * other.y).toShort(); z = (z * other.z).toShort() }
    operator fun divAssign(other: Short3) { x = (x / other.x).toShort(); y = (y / other.y).toShort(); z = (z / other.z).toShort() }

    operator fun plus(value: Int) = Int3(x + value, y + value, z + value)
    operator fun minus(value: Int) = Int3(x - value, y - value, z - value)
    operator fun times(value: Int) = Int3(x * value, y * value, z * value)
    operator fun div(value: Int) = Int3(x / value, y / value, z / value)

    operator fun plus(value: Float) = Float3(x + value, y + value, z + value)
    operator fun minus(value: Float) = Float3(x - value, y - value, z - value)
    operator fun times(value: Float) = Float3(x * value, y * value, z * value)
    operator fun div(value: Float) = Float3(x / value, y / value, z / value)

    operator fun plus(value: Double) = Double3(x + value, y + value, z + value)
    operator fun minus(value: Double) = Double3(x - value, y - value, z - value)
    operator fun times(value: Double) = Double3(x * value, y * value, z * value)
    operator fun div(value: Double) = Double3(x / value, y / value, z / value)

    override operator fun plus(value: Short) = Int3(x + value, y + value, z + value).asShort()
    override operator fun minus(value: Short) = Int3(x - value, y - value, z - value).asShort()
    override operator fun times(value: Short) = Int3(x * value, y * value, z * value).asShort()
    override operator fun div(value: Short) = Int3(x / value, y / value, z / value).asShort()

    override operator fun plusAssign(value: Short) { x = (x + value).toShort(); y = (y + value).toShort(); z = (z + value).toShort() }
    override operator fun minusAssign(value: Short) { x = (x - value).toShort(); y = (y - value).toShort(); z = (z - value).toShort() }
    override operator fun timesAssign(value: Short) { x = (x * value).toShort(); y = (y * value).toShort(); z = (z * value).toShort() }
    override operator fun divAssign(value: Short) { x = (x / value).toShort(); y = (y / value).toShort(); z = (z / value).toShort() }

    override fun length(): Float = (x * x + y * y + z * z).sqrt()
    override fun lengthDouble(): Double = (x * x + y * y + z * z).dsqrt()
    override fun lengthSq(): Short = (x * x + y * y + z * z).toShort()
    override fun max(): Short = if (x > y && x > z) x else if (y > z) y else z
    override fun min(): Short = if (x < y && x < z) x else if (y < z) y else z
    override fun sum(): Short = (x + y + z).toShort()
    override fun diff(): Short = (x - y - z).toShort()
    override fun product(): Short = (x * y * z).toShort()
    override fun div(): Short = (x / y / z).toShort()
    override fun normalized(): Float3 = Float3(x / length(), y / length(), z / length())

    override fun dist(other: Vector<Short>): Float {
        if (other is Short3) {
            val dx = x - other.x
            val dy = y - other.y
            val dz = z - other.z
            return (dx * dx + dy * dy + dz * dz).sqrt()
        } else
            throw IllegalArgumentException("Other must be a Short3")
    }
    override fun distSq(other: Vector<Short>): Float {
        if (other is Short3) {
            val dx = x - other.x
            val dy = y - other.y
            val dz = z - other.z
            return (dx * dx + dy * dy + dz * dz).toFloat()
        } else
            throw IllegalArgumentException("Other must be a Short3")
    }
    override fun dot(other: Vector<Short>): Float {
        if (other is Short3) {
            return (x * other.x + y * other.y + z * other.z).toFloat()
        } else
            throw IllegalArgumentException("Other must be a Short3")
    }
    override fun sdot(): Short = (x * x + y * y + z * z).toShort()
    override fun unaryMinus(): Short3 = Int3(-x, -y, -z).asShort()
    fun cross(other: Short3): Short3 = Int3(
        y * other.z - z * other.y,
        z * other.x - x * other.z,
        x * other.y - y * other.x
    ).asShort()

    override fun abs(): Short3 = Short3(x.fastAbs(), y.fastAbs(), z.fastAbs())
    override fun avg(): Float = (x + y + z).toFloat() / 3f
    override fun min(other: Vector<Short>): Short3 {
        if (other is Short3) {
            return Short3(
                if (x < other.x) x else other.x,
                if (y < other.y) y else other.y,
                if (z < other.z) z else other.z
            )
        } else
            throw IllegalArgumentException("Other must be a Short3")
    }
    override fun max(other: Vector<Short>): Short3 {
        if (other is Short3) {
            return Short3(
                if (x > other.x) x else other.x,
                if (y > other.y) y else other.y,
                if (z > other.z) z else other.z
            )
        } else
            throw IllegalArgumentException("Other must be a Short3")
    }
    override fun isIn(min: Short, max: Short): Bool3 = Bool3(x in min..max, y in min..max, z in min..max)
    override fun intPow(n: Int): Short3 = Short3(x.intPow(n), y.intPow(n), z.intPow(n))

    override fun sin(): Double3 = Double3(SIN[x], SIN[y], SIN[z])
    override fun cos(): Double3 = Double3(COS[x], COS[y], COS[z])
    override fun tan(): Double3 = Double3(TAN[x], TAN[y], TAN[z])

    override fun asFloat(): Float3 = Float3(x.toFloat(), y.toFloat(), z.toFloat())
    override fun asDouble(): Double3 = Double3(x.toDouble(), y.toDouble(), z.toDouble())
    override fun asInt(): Int3 = Int3(x.toInt(), y.toInt(), z.toInt())
    override fun asBoolean(): Bool3 = Bool3(x != 0.toShort(), y != 0.toShort(), z != 0.toShort())
    override fun asRowMatrix(): ShortMatrix = data.toMatrix(intArrayOf(1, 3))
    override fun asColumnMatrix(): ShortMatrix = data.toMatrix(intArrayOf(3, 1))

    override fun xy(): Short2 = Short2(x, y)
    override fun yz(): Short2 = Short2(y, z)
    override fun xz(): Short2 = Short2(x, z)
    override fun yx(): Short2 = Short2(y, x)
    override fun zy(): Short2 = Short2(z, y)
    override fun zx(): Short2 = Short2(z, x)
    override fun xyz(): Short3 = Short3(x, y, z)
    override fun xzy(): Short3 = Short3(x, z, y)
    override fun yxz(): Short3 = Short3(y, x, z)
    override fun yzx(): Short3 = Short3(y, z, x)
    override fun zxy(): Short3 = Short3(z, x, y)
    override fun zyx(): Short3 = Short3(z, y, x)
    override fun xxx(): Short3 = Short3(x, x, x)
    override fun yyy(): Short3 = Short3(y, y, y)
    override fun zzz(): Short3 = Short3(z, z, z)

    override fun copyOf(): Short3 = Short3(x, y, z)
}

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
import com.github.polyrocketmatt.kmt.trigonometry.COS
import com.github.polyrocketmatt.kmt.trigonometry.SIN
import com.github.polyrocketmatt.kmt.trigonometry.TAN
import com.github.polyrocketmatt.kmt.vector.Swizzle3
import com.github.polyrocketmatt.kmt.vector.Vector
import com.github.polyrocketmatt.kmt.vector.bl.Bool3
import com.github.polyrocketmatt.kmt.vector.db.Double3
import com.github.polyrocketmatt.kmt.vector.fl.Float3
import com.github.polyrocketmatt.kmt.vector.sh.Short3

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
data class Int3(var x: Int, var y: Int, var z: Int) : IntVector(), Swizzle3 {

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

    operator fun plus(other: Int) = Int3(x + other, y + other, z + other)
    operator fun minus(other: Int) = Int3(x - other, y - other, z - other)
    operator fun times(other: Int) = Int3(x * other, y * other, z * other)
    operator fun div(other: Int) = Int3(x / other, y / other, z / other)

    operator fun plus(other: Float) = Float3(x + other, y + other, z + other)
    operator fun minus(other: Float) = Float3(x - other, y - other, z - other)
    operator fun times(other: Float) = Float3(x * other, y * other, z * other)
    operator fun div(other: Float) = Float3(x / other, y / other, z / other)

    operator fun plus(other: Double) = Double3(x + other, y + other, z + other)
    operator fun minus(other: Double) = Double3(x - other, y - other, z - other)
    operator fun times(other: Double) = Double3(x * other, y * other, z * other)
    operator fun div(other: Double) = Double3(x / other, y / other, z / other)

    operator fun plus(other: Short) = Int3(x + other, y + other, z + other)
    operator fun minus(other: Short) = Int3(x - other, y - other, z - other)
    operator fun times(other: Short) = Int3(x * other, y * other, z * other)
    operator fun div(other: Short) = Int3(x / other, y / other, z / other)

    operator fun plusAssign(other: Int) { x += other; y += other; z += other }
    operator fun minusAssign(other: Int) { x -= other; y -= other; z -= other }
    operator fun timesAssign(other: Int) { x *= other; y *= other; z *= other }
    operator fun divAssign(other: Int) { x /= other; y /= other; z /= other }

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

    operator fun get(i: Int): Int = when (i) {
        0 -> x
        1 -> y
        2 -> z
        else -> throw IndexOutOfBoundsException()
    }
    operator fun set(i: Int, value: Int) {
        when (i) {
            0 -> x = value
            1 -> y = value
            2 -> z = value
            else -> throw IndexOutOfBoundsException()
        }
    }

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
    override fun dot(other: Vector<Int>): Float {
        if (other is Int3) {
            return (x * other.x + y * other.y + z * other.z).toFloat()
        } else
            throw IllegalArgumentException("Other must be a Int3")
    }
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
}
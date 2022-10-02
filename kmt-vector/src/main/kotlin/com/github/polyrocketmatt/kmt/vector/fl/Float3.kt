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

package com.github.polyrocketmatt.kmt.vector.fl

import com.github.polyrocketmatt.kmt.common.clip
import com.github.polyrocketmatt.kmt.common.dsqrt
import com.github.polyrocketmatt.kmt.common.fastAbs
import com.github.polyrocketmatt.kmt.common.fastCeil
import com.github.polyrocketmatt.kmt.common.intPow
import com.github.polyrocketmatt.kmt.common.lerp
import com.github.polyrocketmatt.kmt.common.smoothStep
import com.github.polyrocketmatt.kmt.common.smootherStep
import com.github.polyrocketmatt.kmt.common.sqrt
import com.github.polyrocketmatt.kmt.common.storage.MemoryStorage
import com.github.polyrocketmatt.kmt.common.storage.Tuple3
import com.github.polyrocketmatt.kmt.trigonometry.COS
import com.github.polyrocketmatt.kmt.trigonometry.SIN
import com.github.polyrocketmatt.kmt.trigonometry.TAN
import com.github.polyrocketmatt.kmt.vector.Swizzle3
import com.github.polyrocketmatt.kmt.vector.Vector
import com.github.polyrocketmatt.kmt.vector.bl.Bool3
import com.github.polyrocketmatt.kmt.vector.db.Double3
import com.github.polyrocketmatt.kmt.vector.it.Int2
import com.github.polyrocketmatt.kmt.vector.it.Int3
import com.github.polyrocketmatt.kmt.vector.sh.Short2
import com.github.polyrocketmatt.kmt.vector.sh.Short3

operator fun Int.plus(other: Float3): Float3 = Float3(this + other.x, this + other.y, this + other.z)
operator fun Int.minus(other: Float3): Float3 = Float3(this - other.x, this - other.y, this - other.z)
operator fun Int.times(other: Float3): Float3 = Float3(this * other.x, this * other.y, this * other.z)
operator fun Int.div(other: Float3): Float3 = Float3(this / other.x, this / other.y, this / other.z)

operator fun Float.plus(other: Float3): Float3 = Float3(this + other.x, this + other.y, this + other.z)
operator fun Float.minus(other: Float3): Float3 = Float3(this - other.x, this - other.y, this - other.z)
operator fun Float.times(other: Float3): Float3 = Float3(this * other.x, this * other.y, this * other.z)
operator fun Float.div(other: Float3): Float3 = Float3(this / other.x, this / other.y, this / other.z)

operator fun Double.plus(other: Float3): Double3 = Double3(this + other.x, this + other.y, this + other.z)
operator fun Double.minus(other: Float3): Double3 = Double3(this - other.x, this - other.y, this - other.z)
operator fun Double.times(other: Float3): Double3 = Double3(this * other.x, this * other.y, this * other.z)
operator fun Double.div(other: Float3): Double3 = Double3(this / other.x, this / other.y, this / other.z)

operator fun Short.plus(other: Float3): Float3 = Float3(this + other.x, this + other.y, this + other.z)
operator fun Short.minus(other: Float3): Float3 = Float3(this - other.x, this - other.y, this - other.z)
operator fun Short.times(other: Float3): Float3 = Float3(this * other.x, this * other.y, this * other.z)
operator fun Short.div(other: Float3): Float3 = Float3(this / other.x, this / other.y, this / other.z)

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a 3-dimensional vector of floating-point numbers.
 *
 * @param x The x component of the vector.
 * @param y The y component of the vector.
 * @param z The z component of the vector.
 */
class Float3(x: Float, y: Float, z: Float) : Tuple3<Float>(arrayOf(x, y, z)), FloatVector, Swizzle3 {

    constructor() : this(0f, 0f, 0f)
    constructor(other: Float3) : this(other.x, other.y, other.z)
    constructor(x: Float) : this(x, x, x)

    operator fun plus(other: Int3) = Float3(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Int3) = Float3(x - other.x, y - other.y, z - other.z)
    operator fun times(other: Int3) = Float3(x * other.x, y * other.y, z * other.z)
    operator fun div(other: Int3) = Float3(x / other.x, y / other.y, z / other.z)

    operator fun plus(other: Float3) = Float3(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Float3) = Float3(x - other.x, y - other.y, z - other.z)
    operator fun times(other: Float3) = Float3(x * other.x, y * other.y, z * other.z)
    operator fun div(other: Float3) = Float3(x / other.x, y / other.y, z / other.z)

    operator fun plus(other: Double3) = Double3(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Double3) = Double3(x - other.x, y - other.y, z - other.z)
    operator fun times(other: Double3) = Double3(x * other.x, y * other.y, z * other.z)
    operator fun div(other: Double3) = Double3(x / other.x, y / other.y, z / other.z)

    operator fun plus(other: Short3) = Float3(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Short3) = Float3(x - other.x, y - other.y, z - other.z)
    operator fun times(other: Short3) = Float3(x * other.x, y * other.y, z * other.z)
    operator fun div(other: Short3) = Float3(x / other.x, y / other.y, z / other.z)

    operator fun plusAssign(other: Float3) { x += other.x; y += other.y; z += other.z }
    operator fun minusAssign(other: Float3) { x -= other.x; y -= other.y; z -= other.z }
    operator fun timesAssign(other: Float3) { x *= other.x; y *= other.y; z *= other.z }
    operator fun divAssign(other: Float3) { x /= other.x; y /= other.y; z /= other.z }

    operator fun plus(other: Int) = Float3(x + other, y + other, z + other)
    operator fun minus(other: Int) = Float3(x - other, y - other, z - other)
    operator fun times(other: Int) = Float3(x * other, y * other, z * other)
    operator fun div(other: Int) = Float3(x / other, y / other, z / other)

    operator fun plus(other: Float) = Float3(x + other, y + other, z + other)
    operator fun minus(other: Float) = Float3(x - other, y - other, z - other)
    operator fun times(other: Float) = Float3(x * other, y * other, z * other)
    operator fun div(other: Float) = Float3(x / other, y / other, z / other)

    operator fun plus(other: Double) = Double3(x + other, y + other, z + other)
    operator fun minus(other: Double) = Double3(x - other, y - other, z - other)
    operator fun times(other: Double) = Double3(x * other, y * other, z * other)
    operator fun div(other: Double) = Double3(x / other, y / other, z / other)

    operator fun plus(other: Short) = Float3(x + other, y + other, z + other)
    operator fun minus(other: Short) = Float3(x - other, y - other, z - other)
    operator fun times(other: Short) = Float3(x * other, y * other, z * other)
    operator fun div(other: Short) = Float3(x / other, y / other, z / other)

    operator fun plusAssign(other: Float) { x += other; y += other; z += other }
    operator fun minusAssign(other: Float) { x -= other; y -= other; z -= other }
    operator fun timesAssign(other: Float) { x *= other; y *= other; z *= other }
    operator fun divAssign(other: Float) { x /= other; y /= other; z /= other }

    override fun length(): Float = (x * x + y * y + z * z).sqrt()
    override fun lengthDouble(): Double = (x * x + y * y + z * z).dsqrt()
    override fun lengthSq(): Float = x * x + y * y + z * z
    override fun max(): Float = if (x > y && x > z) x else if (y > z) y else z
    override fun min(): Float = if (x < y && x < z) x else if (y < z) y else z
    override fun sum(): Float = x + y + z
    override fun diff(): Float = x - y - z
    override fun product(): Float = x * y * z
    override fun div(): Float = x / y / z
    override fun normalized(): Float3 = Float3(x / length(), y / length(), z / length())

    override fun dist(other: Vector<Float>): Float {
        if (other is Float3) {
            val dx = x - other.x
            val dy = y - other.y
            val dz = z - other.z
            return (dx * dx + dy * dy + dz * dz).sqrt()
        } else
            throw IllegalArgumentException("Other must be a Float3")
    }
    override fun distSq(other: Vector<Float>): Float {
        if (other is Float3) {
            val dx = x - other.x
            val dy = y - other.y
            val dz = z - other.z
            return dx * dx + dy * dy + dz * dz
        } else
            throw IllegalArgumentException("Other must be a Float3")
    }
    override fun dot(other: Vector<Float>): Float {
        if (other is Float3) {
            return x * other.x + y * other.y + z * other.z
        } else
            throw IllegalArgumentException("Other must be a Float3")
    }
    override fun sdot(): Float = x * x + y * y + z * z
    override fun unaryMinus(): Float3 = Float3(-x, -y, -z)
    fun cross(other: Float3): Float3 = Float3(
        y * other.z - z * other.y,
        z * other.x - x * other.z,
        x * other.y - y * other.x
    )

    override fun abs(): Float3 = Float3(x.fastAbs(), y.fastAbs(), z.fastAbs())
    override fun avg(): Float = (x + y + z) / 3f
    override fun min(other: Vector<Float>): Float3 {
        if (other is Float3) {
            return Float3(
                if (x < other.x) x else other.x,
                if (y < other.y) y else other.y,
                if (z < other.z) z else other.z
            )
        } else
            throw IllegalArgumentException("Other must be a Float3")
    }
    override fun max(other: Vector<Float>): Float3 {
        if (other is Float3) {
            return Float3(
                if (x > other.x) x else other.x,
                if (y > other.y) y else other.y,
                if (z > other.z) z else other.z
            )
        } else
            throw IllegalArgumentException("Other must be a Float3")
    }
    override fun isIn(min: Float, max: Float): Bool3 = Bool3(x in min..max, y in min..max, z in min..max)
    override fun intPow(n: Int): Float3 = Float3(x.intPow(n), y.intPow(n), z.intPow(n))

    override fun smoothStep(): Float3 = Float3(x.smoothStep(), y.smoothStep(), z.smoothStep())
    override fun smootherStep(): Float3 = Float3(x.smootherStep(), y.smootherStep(), z.smootherStep())
    override fun lerp(min: Float, max: Float): Float3 = Float3(x.lerp(min, max), y.lerp(min, max), z.lerp(min, max))
    override fun clip(min: Float, max: Float): Float3 = Float3(x.clip(min, max), y.clip(min, max), z.clip(min, max))

    override fun sin(): Double3 = Double3(SIN[x], SIN[y], SIN[z])
    override fun cos(): Double3 = Double3(COS[x], COS[y], COS[z])
    override fun tan(): Double3 = Double3(TAN[x], TAN[y], TAN[z])

    override fun floor(): Int3 = Int3(x.toInt(), y.toInt(), z.toInt())
    override fun ceil(): Int3 = Int3(x.fastCeil(), y.fastCeil(), z.fastCeil())
    override fun fract(): Float3 = Float3(x - x.toInt(), y - y.toInt(), z - z.toInt())

    override fun asDouble(): Double3 = Double3(x.toDouble(), y.toDouble(), z.toDouble())
    override fun asInt(): Int2 = Int2(x.toInt(), y.toInt())
    override fun asShort(): Short2 = Short2(x.toInt().toShort(), y.toInt().toShort())
    override fun asBoolean(): Bool3 = Bool3(x != 0.0f, y != 0.0f, z != 0.0f)

    override fun xy(): Float2 = Float2(x, y)
    override fun yz(): Float2 = Float2(y, z)
    override fun xz(): Float2 = Float2(x, z)
    override fun yx(): Float2 = Float2(y, x)
    override fun zy(): Float2 = Float2(z, y)
    override fun zx(): Float2 = Float2(z, x)
    override fun xyz(): Float3 = Float3(x, y, z)
    override fun xzy(): Float3 = Float3(x, z, y)
    override fun yxz(): Float3 = Float3(y, x, z)
    override fun yzx(): Float3 = Float3(y, z, x)
    override fun zxy(): Float3 = Float3(z, x, y)
    override fun zyx(): Float3 = Float3(z, y, x)
    override fun xxx(): Float3 = Float3(x, x, x)
    override fun yyy(): Float3 = Float3(y, y, y)
    override fun zzz(): Float3 = Float3(z, z, z)

    override fun copyOf(): Float3 = Float3(x, y, z)

}

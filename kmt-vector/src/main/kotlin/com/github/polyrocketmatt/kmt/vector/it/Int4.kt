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
import com.github.polyrocketmatt.kmt.common.storage.Tuple4
import com.github.polyrocketmatt.kmt.trigonometry.COS
import com.github.polyrocketmatt.kmt.trigonometry.SIN
import com.github.polyrocketmatt.kmt.trigonometry.TAN
import com.github.polyrocketmatt.kmt.vector.Swizzle4
import com.github.polyrocketmatt.kmt.vector.Vector
import com.github.polyrocketmatt.kmt.vector.bl.Bool4
import com.github.polyrocketmatt.kmt.vector.db.Double4
import com.github.polyrocketmatt.kmt.vector.fl.Float4
import com.github.polyrocketmatt.kmt.vector.sh.Short4

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

    operator fun plus(other: Int) = Int4(x + other, y + other, z + other, w + other)
    operator fun minus(other: Int) = Int4(x - other, y - other, z - other, w - other)
    operator fun times(other: Int) = Int4(x * other, y * other, z * other, w * other)
    operator fun div(other: Int) = Int4(x / other, y / other, z / other, w / other)

    operator fun plus(other: Float) = Float4(x + other, y + other, z + other, w + other)
    operator fun minus(other: Float) = Float4(x - other, y - other, z - other, w - other)
    operator fun times(other: Float) = Float4(x * other, y * other, z * other, w * other)
    operator fun div(other: Float) = Float4(x / other, y / other, z / other, w / other)

    operator fun plus(other: Double) = Double4(x + other, y + other, z + other, w + other)
    operator fun minus(other: Double) = Double4(x - other, y - other, z - other, w - other)
    operator fun times(other: Double) = Double4(x * other, y * other, z * other, w * other)
    operator fun div(other: Double) = Double4(x / other, y / other, z / other, w / other)

    operator fun plus(other: Short) = Int4(x + other, y + other, z + other, w + other)
    operator fun minus(other: Short) = Int4(x - other, y - other, z - other, w - other)
    operator fun times(other: Short) = Int4(x * other, y * other, z * other, w * other)
    operator fun div(other: Short) = Int4(x / other, y / other, z / other, w / other)

    operator fun plusAssign(other: Int) { x += other; y += other; z += other; w += other }
    operator fun minusAssign(other: Int) { x -= other; y -= other; z -= other; w -= other }
    operator fun timesAssign(other: Int) { x *= other; y *= other; z *= other; w *= other }
    operator fun divAssign(other: Int) { x /= other; y /= other; z /= other; w /= other }

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
    override fun dot(other: Vector<Int>): Float {
        if (other is Int4) {
            return (x * other.x + y * other.y + z * other.z + w * other.w).toFloat()
        } else
            throw IllegalArgumentException("Other must be a Int4")
    }
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
}

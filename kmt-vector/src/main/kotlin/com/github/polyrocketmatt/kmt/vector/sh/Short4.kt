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
import com.github.polyrocketmatt.kmt.common.storage.Tuple4
import com.github.polyrocketmatt.kmt.common.utils.complies
import com.github.polyrocketmatt.kmt.matrix.ShortMatrix
import com.github.polyrocketmatt.kmt.matrix.toMatrix
import com.github.polyrocketmatt.kmt.trigonometry.COS
import com.github.polyrocketmatt.kmt.trigonometry.SIN
import com.github.polyrocketmatt.kmt.trigonometry.TAN
import com.github.polyrocketmatt.kmt.vector.Swizzle4
import com.github.polyrocketmatt.kmt.vector.Vector
import com.github.polyrocketmatt.kmt.vector.bl.Bool4
import com.github.polyrocketmatt.kmt.vector.db.Double4
import com.github.polyrocketmatt.kmt.vector.fl.Float4
import com.github.polyrocketmatt.kmt.vector.it.Int4

/**
 * Convert a short matrix to a short vector.
 *
 * @return A short vector whose components are the elements of the matrix.
 * @throws IllegalArgumentException if the matrix does not contain 4 elements.
 */
fun ShortMatrix.toShort4(): Short4 {
    complies("Cannot create a Short4 from a ShortMatrix with ${this.data.size} elements!") { this.data.size == 4 }
    return Short4(this.data[0], this.data[1], this.data[2], this.data[3])
}

operator fun Int.plus(other: Short4): Int4 = Int4(this + other.x, this + other.y, this + other.z, this + other.w)
operator fun Int.minus(other: Short4): Int4 = Int4(this - other.x, this - other.y, this - other.z, this - other.w)
operator fun Int.times(other: Short4): Int4 = Int4(this * other.x, this * other.y, this * other.z, this * other.w)
operator fun Int.div(other: Short4): Int4 = Int4(this / other.x, this / other.y, this / other.z, this / other.w)

operator fun Float.plus(other: Short4): Float4 = Float4(this + other.x, this + other.y, this + other.z, this + other.w)
operator fun Float.minus(other: Short4): Float4 = Float4(this - other.x, this - other.y, this - other.z, this - other.w)
operator fun Float.times(other: Short4): Float4 = Float4(this * other.x, this * other.y, this * other.z, this * other.w)
operator fun Float.div(other: Short4): Float4 = Float4(this / other.x, this / other.y, this / other.z, this / other.w)

operator fun Double.plus(other: Short4): Double4 = Double4(this + other.x, this + other.y, this + other.z, this + other.w)
operator fun Double.minus(other: Short4): Double4 = Double4(this - other.x, this - other.y, this - other.z, this - other.w)
operator fun Double.times(other: Short4): Double4 = Double4(this * other.x, this * other.y, this * other.z, this * other.w)
operator fun Double.div(other: Short4): Double4 = Double4(this / other.x, this / other.y, this / other.z, this / other.w)

operator fun Short.plus(other: Short4): Short4 = Int4(this + other.x, this + other.y, this + other.z, this + other.w).asShort()
operator fun Short.minus(other: Short4): Short4 = Int4(this - other.x, this - other.y, this - other.z, this - other.w).asShort()
operator fun Short.times(other: Short4): Short4 = Int4(this * other.x, this * other.y, this * other.z, this * other.w).asShort()
operator fun Short.div(other: Short4): Short4 = Int4(this / other.x, this / other.y, this / other.z, this / other.w).asShort()

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a 4-dimensional vector of shorts.
 *
 * @param x The x component of the vector.
 * @param y The y component of the vector.
 * @param z The z component of the vector.
 * @param w The w component of the vector.
 */
class Short4(x: Short, y: Short, z: Short, w: Short) : Tuple4<Short>(arrayOf(x, y, z, w)), ShortVector, Swizzle4 {

    constructor() : this(0, 0, 0, 0)
    constructor(other: Short4) : this(other.x, other.y, other.z, other.w)
    constructor(x: Short) : this(x, x, x, x)

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

    operator fun plus(other: Short4) = Int4(x + other.x, y + other.y, z + other.z, w + other.w).asShort()
    operator fun minus(other: Short4) = Int4(x - other.x, y - other.y, z - other.z, w - other.w).asShort()
    operator fun times(other: Short4) = Int4(x * other.x, y * other.y, z * other.z, w * other.w).asShort()
    operator fun div(other: Short4) = Int4(x / other.x, y / other.y, z / other.z, w / other.w).asShort()

    operator fun plusAssign(other: Short4) { x = (x + other.x).toShort(); y = (y + other.y).toShort(); z = (z + other.z).toShort(); w = (w + other.w).toShort() }
    operator fun minusAssign(other: Short4) { x = (x - other.x).toShort(); y = (y - other.y).toShort(); z = (z - other.z).toShort(); w = (w - other.w).toShort() }
    operator fun timesAssign(other: Short4) { x = (x * other.x).toShort(); y = (y * other.y).toShort(); z = (z * other.z).toShort(); w = (w * other.w).toShort() }
    operator fun divAssign(other: Short4) { x = (x / other.x).toShort(); y = (y / other.y).toShort(); z = (z / other.z).toShort(); w = (w / other.w).toShort() }

    operator fun plus(value: Int) = Int4(x + value, y + value, z + value, w + value)
    operator fun minus(value: Int) = Int4(x - value, y - value, z - value, w - value)
    operator fun times(value: Int) = Int4(x * value, y * value, z * value, w * value)
    operator fun div(value: Int) = Int4(x / value, y / value, z / value, w / value)

    operator fun plus(value: Float) = Float4(x + value, y + value, z + value, w + value)
    operator fun minus(value: Float) = Float4(x - value, y - value, z - value, w - value)
    operator fun times(value: Float) = Float4(x * value, y * value, z * value, w * value)
    operator fun div(value: Float) = Float4(x / value, y / value, z / value, w / value)

    operator fun plus(value: Double) = Double4(x + value, y + value, z + value, w + value)
    operator fun minus(value: Double) = Double4(x - value, y - value, z - value, w - value)
    operator fun times(value: Double) = Double4(x * value, y * value, z * value, w * value)
    operator fun div(value: Double) = Double4(x / value, y / value, z / value, w / value)

    override operator fun plus(value: Short) = Int4(x + value, y + value, z + value, w + value).asShort()
    override operator fun minus(value: Short) = Int4(x - value, y - value, z - value, w - value).asShort()
    override operator fun times(value: Short) = Int4(x * value, y * value, z * value, w * value).asShort()
    override operator fun div(value: Short) = Int4(x / value, y / value, z / value, w / value).asShort()

    override operator fun plusAssign(value: Short) { x = (x + value).toShort(); y = (y + value).toShort(); z = (z + value).toShort(); w = (w + value).toShort() }
    override operator fun minusAssign(value: Short) { x = (x - value).toShort(); y = (y - value).toShort(); z = (z - value).toShort(); w = (w - value).toShort() }
    override operator fun timesAssign(value: Short) { x = (x * value).toShort(); y = (y * value).toShort(); z = (z * value).toShort(); w = (w * value).toShort() }
    override operator fun divAssign(value: Short) { x = (x / value).toShort(); y = (y / value).toShort(); z = (z / value).toShort(); w = (w / value).toShort() }

    override fun length(): Float = (x * x + y * y + z * z).sqrt()
    override fun lengthDouble(): Double = (x * x + y * y + z * z).dsqrt()
    override fun lengthSq(): Short = (x * x + y * y + z * z).toShort()
    override fun max(): Short = if (x > y && x > z && x > w) x else if (y > z && y > w) y else if (z > w) z else w
    override fun min(): Short = if (x < y && x < z && x < w) x else if (y < z && y < w) y else if (z < w) z else w
    override fun sum(): Short = (x + y + z + w).toShort()
    override fun diff(): Short = (x - y - z - w).toShort()
    override fun product(): Short = (x * y * z * w).toShort()
    override fun div(): Short = (x / y / z / w).toShort()
    override fun normalized(): Float4 = Float4(x / length(), y / length(), z / length(), w / length())

    override fun dist(other: Vector<Short>): Float {
        if (other is Short4) {
            val dx = x - other.x
            val dy = y - other.y
            val dz = z - other.z
            val dw = w - other.w
            return (dx * dx + dy * dy + dz * dz + dw * dw).sqrt()
        } else
            throw IllegalArgumentException("Other must be a Short4")
    }
    override fun distSq(other: Vector<Short>): Float {
        if (other is Short4) {
            val dx = x - other.x
            val dy = y - other.y
            val dz = z - other.z
            val dw = w - other.w
            return (dx * dx + dy * dy + dz * dz + dw * dw).toFloat()
        } else
            throw IllegalArgumentException("Other must be a Short4")
    }
    override fun dot(other: Vector<Short>): Float {
        if (other is Short4) {
            return (x * other.x + y * other.y + z * other.z + w * other.w).toFloat()
        } else
            throw IllegalArgumentException("Other must be a Short4")
    }
    override fun sdot(): Short = (x * x + y * y + z * z + w * w).toShort()
    override fun unaryMinus(): Short4 = Int4(-x, -y, -z, -w).asShort()

    override fun abs(): Short4 = Short4(x.fastAbs(), y.fastAbs(), z.fastAbs(), w.fastAbs())
    override fun avg(): Float = (x + y + z + w).toFloat() / 4f
    override fun min(other: Vector<Short>): Short4 {
        if (other is Short4) {
            return Short4(
                if (x < other.x) x else other.x,
                if (y < other.y) y else other.y,
                if (z < other.z) z else other.z,
                if (w < other.w) w else other.w
            )
        } else
            throw IllegalArgumentException("Other must be a Short4")
    }
    override fun max(other: Vector<Short>): Short4 {
        if (other is Short4) {
            return Short4(
                if (x > other.x) x else other.x,
                if (y > other.y) y else other.y,
                if (z > other.z) z else other.z,
                if (w > other.w) w else other.w
            )
        } else
            throw IllegalArgumentException("Other must be a Short4")
    }
    override fun isIn(min: Short, max: Short): Bool4 = Bool4(x in min..max, y in min..max, z in min..max, w in min..max)
    override fun intPow(n: Int): Short4 = Short4(x.intPow(n), y.intPow(n), z.intPow(n), w.intPow(n))

    override fun sin(): Double4 = Double4(SIN[x], SIN[y], SIN[z], SIN[w])
    override fun cos(): Double4 = Double4(COS[x], COS[y], COS[z], COS[w])
    override fun tan(): Double4 = Double4(TAN[x], TAN[y], TAN[z], TAN[w])

    override fun asFloat(): Float4 = Float4(x.toFloat(), y.toFloat(), z.toFloat(), w.toFloat())
    override fun asDouble(): Double4 = Double4(x.toDouble(), y.toDouble(), z.toDouble(), w.toDouble())
    override fun asInt(): Int4 = Int4(x.toInt(), y.toInt(), z.toInt(), w.toInt())
    override fun asBoolean(): Bool4 = Bool4(x != 0.toShort(), y != 0.toShort(), z != 0.toShort(), w != 0.toShort())
    override fun asRowMatrix(): ShortMatrix = data.toMatrix(intArrayOf(1, 4))
    override fun asColumnMatrix(): ShortMatrix = data.toMatrix(intArrayOf(4, 1))

    override fun xy(): Short2 = Short2(x, y)
    override fun xz(): Short2 = Short2(x, z)
    override fun xw(): Short2 = Short2(x, w)
    override fun yx(): Short2 = Short2(y, x)
    override fun yz(): Short2 = Short2(y, z)
    override fun yw(): Short2 = Short2(y, w)
    override fun zx(): Short2 = Short2(z, x)
    override fun zy(): Short2 = Short2(z, y)
    override fun zw(): Short2 = Short2(z, w)
    override fun wx(): Short2 = Short2(w, x)
    override fun wy(): Short2 = Short2(w, y)
    override fun wz(): Short2 = Short2(w, z)
    override fun xyz(): Short3 = Short3(x, y, z)
    override fun xyw(): Short3 = Short3(x, y, w)
    override fun xzy(): Short3 = Short3(x, z, y)
    override fun xzw(): Short3 = Short3(x, z, w)
    override fun xwy(): Short3 = Short3(x, w, y)
    override fun xwz(): Short3 = Short3(x, w, z)
    override fun yxz(): Short3 = Short3(y, x, z)
    override fun yxw(): Short3 = Short3(y, x, w)
    override fun yzx(): Short3 = Short3(y, z, x)
    override fun yzw(): Short3 = Short3(y, z, w)
    override fun ywx(): Short3 = Short3(y, w, x)
    override fun ywz(): Short3 = Short3(y, w, z)
    override fun zxy(): Short3 = Short3(z, x, y)
    override fun zxw(): Short3 = Short3(z, x, w)
    override fun zyx(): Short3 = Short3(z, y, x)
    override fun zyw(): Short3 = Short3(z, y, w)
    override fun zwx(): Short3 = Short3(z, w, x)
    override fun zwy(): Short3 = Short3(z, w, y)
    override fun wxy(): Short3 = Short3(w, x, y)
    override fun wxz(): Short3 = Short3(w, x, z)
    override fun wyx(): Short3 = Short3(w, y, x)
    override fun wyz(): Short3 = Short3(w, y, z)
    override fun wzx(): Short3 = Short3(w, z, x)
    override fun wzy(): Short3 = Short3(w, z, y)
    override fun xyzw(): Short4 = this
    override fun xywz(): Short4 = Short4(x, y, w, z)
    override fun xzyw(): Short4 = Short4(x, z, y, w)
    override fun xzwy(): Short4 = Short4(x, z, w, y)
    override fun xwyz(): Short4 = Short4(x, w, y, z)
    override fun xwzy(): Short4 = Short4(x, w, z, y)
    override fun yxzw(): Short4 = Short4(y, x, z, w)
    override fun yxwz(): Short4 = Short4(y, x, w, z)
    override fun yzxw(): Short4 = Short4(y, z, x, w)
    override fun yzwx(): Short4 = Short4(y, z, w, x)
    override fun ywxz(): Short4 = Short4(y, w, x, z)
    override fun ywzx(): Short4 = Short4(y, w, z, x)
    override fun zxyw(): Short4 = Short4(z, x, y, w)
    override fun zxwy(): Short4 = Short4(z, x, w, y)
    override fun zyxw(): Short4 = Short4(z, y, x, w)
    override fun zywx(): Short4 = Short4(z, y, w, x)
    override fun zwxy(): Short4 = Short4(z, w, x, y)
    override fun zwyx(): Short4 = Short4(z, w, y, x)
    override fun wxyz(): Short4 = Short4(w, x, y, z)
    override fun wxzy(): Short4 = Short4(w, x, z, y)
    override fun wyxz(): Short4 = Short4(w, y, x, z)
    override fun wyzx(): Short4 = Short4(w, y, z, x)
    override fun wzxy(): Short4 = Short4(w, z, x, y)
    override fun wzyx(): Short4 = Short4(w, z, y, x)
    override fun xxxx(): Short4 = Short4(x, x, x, x)
    override fun yyyy(): Short4 = Short4(y, y, y, y)
    override fun zzzz(): Short4 = Short4(z, z, z, z)
    override fun wwww(): Short4 = Short4(w, w, w, w)

    override fun copyOf(): Short4 = Short4(x, y, z, w)
}

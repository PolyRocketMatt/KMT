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
import com.github.polyrocketmatt.kmt.common.storage.Tuple4
import com.github.polyrocketmatt.kmt.common.utils.complies
import com.github.polyrocketmatt.kmt.matrix.FloatMatrix
import com.github.polyrocketmatt.kmt.matrix.Matrix
import com.github.polyrocketmatt.kmt.matrix.toMatrix
import com.github.polyrocketmatt.kmt.trigonometry.COS
import com.github.polyrocketmatt.kmt.trigonometry.SIN
import com.github.polyrocketmatt.kmt.trigonometry.TAN
import com.github.polyrocketmatt.kmt.vector.Swizzle4
import com.github.polyrocketmatt.kmt.vector.Vector
import com.github.polyrocketmatt.kmt.vector.bl.Bool4
import com.github.polyrocketmatt.kmt.vector.db.Double4
import com.github.polyrocketmatt.kmt.vector.it.Int4
import com.github.polyrocketmatt.kmt.vector.sh.Short4

/**
 * Convert a floating-point matrix to a floating-point vector.
 *
 * @return A floating-point vector whose components are the elements of the matrix.
 * @throws IllegalArgumentException if the matrix does not contain 4 elements.
 */
fun FloatMatrix.toFloat4(): Float4 {
    complies("Cannot create a Float4 from a FloatMatrix with ${this.data.size} elements!") { this.data.size == 4 }
    return Float4(this.data[0], this.data[1], this.data[2], this.data[3])
}

operator fun Int.plus(other: Float4): Float4 = Float4(this + other.x, this + other.y, this + other.z, this + other.w)
operator fun Int.minus(other: Float4): Float4 = Float4(this - other.x, this - other.y, this - other.z, this - other.w)
operator fun Int.times(other: Float4): Float4 = Float4(this * other.x, this * other.y, this * other.z, this * other.w)
operator fun Int.div(other: Float4): Float4 = Float4(this / other.x, this / other.y, this / other.z, this / other.w)

operator fun Float.plus(other: Float4): Float4 = Float4(this + other.x, this + other.y, this + other.z, this + other.w)
operator fun Float.minus(other: Float4): Float4 = Float4(this - other.x, this - other.y, this - other.z, this - other.w)
operator fun Float.times(other: Float4): Float4 = Float4(this * other.x, this * other.y, this * other.z, this * other.w)
operator fun Float.div(other: Float4): Float4 = Float4(this / other.x, this / other.y, this / other.z, this / other.w)

operator fun Double.plus(other: Float4): Double4 = Double4(this + other.x, this + other.y, this + other.z, this + other.w)
operator fun Double.minus(other: Float4): Double4 = Double4(this - other.x, this - other.y, this - other.z, this - other.w)
operator fun Double.times(other: Float4): Double4 = Double4(this * other.x, this * other.y, this * other.z, this * other.w)
operator fun Double.div(other: Float4): Double4 = Double4(this / other.x, this / other.y, this / other.z, this / other.w)

operator fun Short.plus(other: Float4): Float4 = Float4(this + other.x, this + other.y, this + other.z, this + other.w)
operator fun Short.minus(other: Float4): Float4 = Float4(this - other.x, this - other.y, this - other.z, this - other.w)
operator fun Short.times(other: Float4): Float4 = Float4(this * other.x, this * other.y, this * other.z, this * other.w)
operator fun Short.div(other: Float4): Float4 = Float4(this / other.x, this / other.y, this / other.z, this / other.w)

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a 4-dimensional vector of floating-point numbers.
 *
 * @param x The x component of the vector.
 * @param y The y component of the vector.
 * @param z The z component of the vector.
 * @param w The w component of the vector.
 */
class Float4(x: Float, y: Float, z: Float, w: Float) : Tuple4<Float>(arrayOf(x, y, z, w)), FloatVector, Swizzle4 {

    constructor() : this(0.0f, 0.0f, 0.0f, 0.0f)
    constructor(other: Float4) : this(other.x, other.y, other.z, other.w)
    constructor(x: Float) : this(x, x, x, x)

    operator fun plus(other: Int4) = Float4(x + other.x, y + other.y, z + other.z, w + other.w)
    operator fun minus(other: Int4) = Float4(x - other.x, y - other.y, z - other.z, w - other.w)
    operator fun times(other: Int4) = Float4(x * other.x, y * other.y, z * other.z, w * other.w)
    operator fun div(other: Int4) = Float4(x / other.x, y / other.y, z / other.z, w / other.w)

    operator fun plus(other: Float4) = Float4(x + other.x, y + other.y, z + other.z, w + other.w)
    operator fun minus(other: Float4) = Float4(x - other.x, y - other.y, z - other.z, w - other.w)
    operator fun times(other: Float4) = Float4(x * other.x, y * other.y, z * other.z, w * other.w)
    operator fun div(other: Float4) = Float4(x / other.x, y / other.y, z / other.z, w / other.w)

    operator fun plus(other: Double4) = Double4(x + other.x, y + other.y, z + other.z, w + other.w)
    operator fun minus(other: Double4) = Double4(x - other.x, y - other.y, z - other.z, w - other.w)
    operator fun times(other: Double4) = Double4(x * other.x, y * other.y, z * other.z, w * other.w)
    operator fun div(other: Double4) = Double4(x / other.x, y / other.y, z / other.z, w / other.w)

    operator fun plus(other: Short4) = Float4(x + other.x, y + other.y, z + other.z, w + other.w)
    operator fun minus(other: Short4) = Float4(x - other.x, y - other.y, z - other.z, w - other.w)
    operator fun times(other: Short4) = Float4(x * other.x, y * other.y, z * other.z, w * other.w)
    operator fun div(other: Short4) = Float4(x / other.x, y / other.y, z / other.z, w / other.w)

    operator fun plusAssign(other: Float4) { x += other.x; y += other.y; z += other.z; w += other.w }
    operator fun minusAssign(other: Float4) { x -= other.x; y -= other.y; z -= other.z; w -= other.w }
    operator fun timesAssign(other: Float4) { x *= other.x; y *= other.y; z *= other.z; w *= other.w }
    operator fun divAssign(other: Float4) { x /= other.x; y /= other.y; z /= other.z; w /= other.w }

    operator fun plus(value: Int) = Float4(x + value, y + value, z + value, w + value)
    operator fun minus(value: Int) = Float4(x - value, y - value, z - value, w - value)
    operator fun times(value: Int) = Float4(x * value, y * value, z * value, w * value)
    operator fun div(value: Int) = Float4(x / value, y / value, z / value, w / value)

    override operator fun plus(value: Float) = Float4(x + value, y + value, z + value, w + value)
    override operator fun minus(value: Float) = Float4(x - value, y - value, z - value, w - value)
    override operator fun times(value: Float) = Float4(x * value, y * value, z * value, w * value)
    override operator fun div(value: Float) = Float4(x / value, y / value, z / value, w / value)

    operator fun plus(value: Double) = Double4(x + value, y + value, z + value, w + value)
    operator fun minus(value: Double) = Double4(x - value, y - value, z - value, w - value)
    operator fun times(value: Double) = Double4(x * value, y * value, z * value, w * value)
    operator fun div(value: Double) = Double4(x / value, y / value, z / value, w / value)

    operator fun plus(value: Short) = Float4(x + value, y + value, z + value, w + value)
    operator fun minus(value: Short) = Float4(x - value, y - value, z - value, w - value)
    operator fun times(value: Short) = Float4(x * value, y * value, z * value, w * value)
    operator fun div(value: Short) = Float4(x / value, y / value, z / value, w / value)

    override operator fun plusAssign(value: Float) { x += value; y += value; z += value; w += value }
    override operator fun minusAssign(value: Float) { x -= value; y -= value; z -= value; w -= value }
    override operator fun timesAssign(value: Float) { x *= value; y *= value; z *= value; w *= value }
    override operator fun divAssign(value: Float) { x /= value; y /= value; z /= value; w /= value }

    override fun length(): Float = (x * x + y * y + z * z).sqrt()
    override fun lengthDouble(): Double = (x * x + y * y + z * z).dsqrt()
    override fun lengthSq(): Float = x * x + y * y + z * z
    override fun max(): Float = if (x > y && x > z && x > w) x else if (y > z && y > w) y else if (z > w) z else w
    override fun min(): Float = if (x < y && x < z && x < w) x else if (y < z && y < w) y else if (z < w) z else w
    override fun sum(): Float = x + y + z + w
    override fun diff(): Float = x - y - z - w
    override fun product(): Float = x * y * z * w
    override fun div(): Float = x / y / z / w
    override fun normalized(): Float4 = Float4(x / length(), y / length(), z / length(), w / length())

    override fun dist(other: Vector<Float>): Float {
        if (other is Float4) {
            val dx = x - other.x
            val dy = y - other.y
            val dz = z - other.z
            val dw = w - other.w
            return (dx * dx + dy * dy + dz * dz + dw * dw).sqrt()
        } else
            throw IllegalArgumentException("Other must be a Float4")
    }
    override fun distSq(other: Vector<Float>): Float {
        if (other is Float4) {
            val dx = x - other.x
            val dy = y - other.y
            val dz = z - other.z
            val dw = w - other.w
            return dx * dx + dy * dy + dz * dz + dw * dw
        } else
            throw IllegalArgumentException("Other must be a Float4")
    }
    override fun dot(other: Vector<Float>): Float {
        if (other is Float4) {
            return x * other.x + y * other.y + z * other.z + w * other.w
        } else
            throw IllegalArgumentException("Other must be a Float4")
    }
    override fun sdot(): Float = x * x + y * y + z * z + w * w
    override fun unaryMinus(): Float4 = Float4(-x, -y, -z, -w)

    override fun abs(): Float4 = Float4(x.fastAbs(), y.fastAbs(), z.fastAbs(), w.fastAbs())
    override fun avg(): Float = (x + y + z + w) / 4f
    override fun min(other: Vector<Float>): Float4 {
        if (other is Float4) {
            return Float4(
                if (x < other.x) x else other.x,
                if (y < other.y) y else other.y,
                if (z < other.z) z else other.z,
                if (w < other.w) w else other.w
            )
        } else
            throw IllegalArgumentException("Other must be a Float4")
    }
    override fun max(other: Vector<Float>): Float4 {
        if (other is Float4) {
            return Float4(
                if (x > other.x) x else other.x,
                if (y > other.y) y else other.y,
                if (z > other.z) z else other.z,
                if (w > other.w) w else other.w
            )
        } else
            throw IllegalArgumentException("Other must be a Float4")
    }
    override fun isIn(min: Float, max: Float): Bool4 = Bool4(x in min..max, y in min..max, z in min..max, w in min..max)
    override fun intPow(n: Int): Float4 = Float4(x.intPow(n), y.intPow(n), z.intPow(n), w.intPow(n))

    override fun smoothStep(): Float4 = Float4(x.smoothStep(), y.smoothStep(), z.smoothStep(), w.smoothStep())
    override fun smootherStep(): Float4 = Float4(x.smootherStep(), y.smootherStep(), z.smootherStep(), w.smootherStep())
    override fun lerp(min: Float, max: Float): Float4 = Float4(x.lerp(min, max), y.lerp(min, max), z.lerp(min, max), w.lerp(min, max))
    override fun clip(min: Float, max: Float): Float4 = Float4(x.clip(min, max), y.clip(min, max), z.clip(min, max), w.clip(min, max))

    override fun sin(): Double4 = Double4(SIN[x], SIN[y], SIN[z], SIN[w])
    override fun cos(): Double4 = Double4(COS[x], COS[y], COS[z], COS[w])
    override fun tan(): Double4 = Double4(TAN[x], TAN[y], TAN[z], TAN[w])

    override fun floor(): Int4 = Int4(x.toInt(), y.toInt(), z.toInt(), w.toInt())
    override fun ceil(): Int4 = Int4(x.fastCeil(), y.fastCeil(), z.fastCeil(), w.fastCeil())
    override fun fract(): Float4 = Float4(x - x.toInt(), y - y.toInt(), z - z.toInt(), w - w.toInt())

    override fun asDouble(): Double4 = Double4(x.toDouble(), y.toDouble(), z.toDouble(), w.toDouble())
    override fun asInt(): Int4 = Int4(x.toInt(), y.toInt(), z.toInt(), w.toInt())
    override fun asShort(): Short4 = Short4(x.toInt().toShort(), y.toInt().toShort(), z.toInt().toShort(), w.toInt().toShort())
    override fun asBoolean(): Bool4 = Bool4(x != 0.0f, y != 0.0f, z != 0.0f, w != 0.0f)
    override fun asRowMatrix(): FloatMatrix = data.toMatrix(intArrayOf(1, 4))
    override fun asColumnMatrix(): FloatMatrix = data.toMatrix(intArrayOf(4, 1))

    override fun xy(): Float2 = Float2(x, y)
    override fun xz(): Float2 = Float2(x, z)
    override fun xw(): Float2 = Float2(x, w)
    override fun yx(): Float2 = Float2(y, x)
    override fun yz(): Float2 = Float2(y, z)
    override fun yw(): Float2 = Float2(y, w)
    override fun zx(): Float2 = Float2(z, x)
    override fun zy(): Float2 = Float2(z, y)
    override fun zw(): Float2 = Float2(z, w)
    override fun wx(): Float2 = Float2(w, x)
    override fun wy(): Float2 = Float2(w, y)
    override fun wz(): Float2 = Float2(w, z)
    override fun xyz(): Float3 = Float3(x, y, z)
    override fun xyw(): Float3 = Float3(x, y, w)
    override fun xzy(): Float3 = Float3(x, z, y)
    override fun xzw(): Float3 = Float3(x, z, w)
    override fun xwy(): Float3 = Float3(x, w, y)
    override fun xwz(): Float3 = Float3(x, w, z)
    override fun yxz(): Float3 = Float3(y, x, z)
    override fun yxw(): Float3 = Float3(y, x, w)
    override fun yzx(): Float3 = Float3(y, z, x)
    override fun yzw(): Float3 = Float3(y, z, w)
    override fun ywx(): Float3 = Float3(y, w, x)
    override fun ywz(): Float3 = Float3(y, w, z)
    override fun zxy(): Float3 = Float3(z, x, y)
    override fun zxw(): Float3 = Float3(z, x, w)
    override fun zyx(): Float3 = Float3(z, y, x)
    override fun zyw(): Float3 = Float3(z, y, w)
    override fun zwx(): Float3 = Float3(z, w, x)
    override fun zwy(): Float3 = Float3(z, w, y)
    override fun wxy(): Float3 = Float3(w, x, y)
    override fun wxz(): Float3 = Float3(w, x, z)
    override fun wyx(): Float3 = Float3(w, y, x)
    override fun wyz(): Float3 = Float3(w, y, z)
    override fun wzx(): Float3 = Float3(w, z, x)
    override fun wzy(): Float3 = Float3(w, z, y)
    override fun xyzw(): Float4 = this
    override fun xywz(): Float4 = Float4(x, y, w, z)
    override fun xzyw(): Float4 = Float4(x, z, y, w)
    override fun xzwy(): Float4 = Float4(x, z, w, y)
    override fun xwyz(): Float4 = Float4(x, w, y, z)
    override fun xwzy(): Float4 = Float4(x, w, z, y)
    override fun yxzw(): Float4 = Float4(y, x, z, w)
    override fun yxwz(): Float4 = Float4(y, x, w, z)
    override fun yzxw(): Float4 = Float4(y, z, x, w)
    override fun yzwx(): Float4 = Float4(y, z, w, x)
    override fun ywxz(): Float4 = Float4(y, w, x, z)
    override fun ywzx(): Float4 = Float4(y, w, z, x)
    override fun zxyw(): Float4 = Float4(z, x, y, w)
    override fun zxwy(): Float4 = Float4(z, x, w, y)
    override fun zyxw(): Float4 = Float4(z, y, x, w)
    override fun zywx(): Float4 = Float4(z, y, w, x)
    override fun zwxy(): Float4 = Float4(z, w, x, y)
    override fun zwyx(): Float4 = Float4(z, w, y, x)
    override fun wxyz(): Float4 = Float4(w, x, y, z)
    override fun wxzy(): Float4 = Float4(w, x, z, y)
    override fun wyxz(): Float4 = Float4(w, y, x, z)
    override fun wyzx(): Float4 = Float4(w, y, z, x)
    override fun wzxy(): Float4 = Float4(w, z, x, y)
    override fun wzyx(): Float4 = Float4(w, z, y, x)
    override fun xxxx(): Float4 = Float4(x, x, x, x)
    override fun yyyy(): Float4 = Float4(y, y, y, y)
    override fun zzzz(): Float4 = Float4(z, z, z, z)
    override fun wwww(): Float4 = Float4(w, w, w, w)

    override fun copyOf(): Float4 = Float4(x, y, z, w)

    @Deprecated("Use operator instead", ReplaceWith("vector[i]"))
    override fun get(i: Int): Float = data[i]
    override fun get(row: Int, col: Int): Float = throw UnsupportedOperationException("Float4 is considered a vector")

    @Deprecated("Use operator instead", ReplaceWith("vector[i] = value"))
    override fun set(i: Int, value: Float) = when (i) {
        0 -> x = value
        1 -> y = value
        2 -> z = value
        3 -> w = value
        else -> throw IndexOutOfBoundsException("Index $i is out of bounds for Float4")
    }
    override fun set(row: Int, col: Int, value: Float) = throw UnsupportedOperationException("Float4 is considered a vector")
}

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
import com.github.polyrocketmatt.kmt.common.storage.Tuple4
import com.github.polyrocketmatt.kmt.common.utils.complies
import com.github.polyrocketmatt.kmt.matrix.DoubleMatrix
import com.github.polyrocketmatt.kmt.matrix.toMatrix
import com.github.polyrocketmatt.kmt.trigonometry.COS
import com.github.polyrocketmatt.kmt.trigonometry.SIN
import com.github.polyrocketmatt.kmt.trigonometry.TAN
import com.github.polyrocketmatt.kmt.vector.Swizzle4
import com.github.polyrocketmatt.kmt.vector.Vector
import com.github.polyrocketmatt.kmt.vector.bl.Bool4
import com.github.polyrocketmatt.kmt.vector.fl.Float4
import com.github.polyrocketmatt.kmt.vector.it.Int4
import com.github.polyrocketmatt.kmt.vector.sh.Short4

/**
 * Convert a double matrix to a double vector.
 *
 * @return A double vector whose components are the elements of the matrix.
 * @throws IllegalArgumentException if the matrix does not contain 4 elements.
 */
fun DoubleMatrix.toDouble4(): Double4 {
    complies("Cannot create a Double4 from a DoubleMatrix with ${this.data.size} elements!") { this.data.size == 4 }
    return Double4(this.data[0], this.data[1], this.data[2], this.data[3])
}

operator fun Int.plus(other: Double4): Double4 = Double4(this + other.x, this + other.y, this + other.z, this + other.w)
operator fun Int.minus(other: Double4): Double4 = Double4(this - other.x, this - other.y, this - other.z, this - other.w)
operator fun Int.times(other: Double4): Double4 = Double4(this * other.x, this * other.y, this * other.z, this * other.w)
operator fun Int.div(other: Double4): Double4 = Double4(this / other.x, this / other.y, this / other.z, this / other.w)

operator fun Float.plus(other: Double4): Double4 = Double4(this + other.x, this + other.y, this + other.z, this + other.w)
operator fun Float.minus(other: Double4): Double4 = Double4(this - other.x, this - other.y, this - other.z, this - other.w)
operator fun Float.times(other: Double4): Double4 = Double4(this * other.x, this * other.y, this * other.z, this * other.w)
operator fun Float.div(other: Double4): Double4 = Double4(this / other.x, this / other.y, this / other.z, this / other.w)

operator fun Double.plus(other: Double4): Double4 = Double4(this + other.x, this + other.y, this + other.z, this + other.w)
operator fun Double.minus(other: Double4): Double4 = Double4(this - other.x, this - other.y, this - other.z, this - other.w)
operator fun Double.times(other: Double4): Double4 = Double4(this * other.x, this * other.y, this * other.z, this * other.w)
operator fun Double.div(other: Double4): Double4 = Double4(this / other.x, this / other.y, this / other.z, this / other.w)

operator fun Short.plus(other: Double4): Double4 = Double4(this + other.x, this + other.y, this + other.z, this + other.w)
operator fun Short.minus(other: Double4): Double4 = Double4(this - other.x, this - other.y, this - other.z, this - other.w)
operator fun Short.times(other: Double4): Double4 = Double4(this * other.x, this * other.y, this * other.z, this * other.w)
operator fun Short.div(other: Double4): Double4 = Double4(this / other.x, this / other.y, this / other.z, this / other.w)

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a 4-dimensional vector of doubles.
 *
 * @param x The x component of the vector.
 * @param y The y component of the vector.
 * @param z The z component of the vector.
 * @param w The w component of the vector.
 */
class Double4(x: Double, y: Double, z: Double, w: Double) : Tuple4<Double>(arrayOf(x, y, z, w)), DoubleVector, Swizzle4 {

    constructor() : this(0.0, 0.0, 0.0, 0.0)
    constructor(other: Double4) : this(other.x, other.y, other.z, other.w)
    constructor(x: Double) : this(x, x, x, x)

    operator fun plus(other: Int4) = Double4(x + other.x, y + other.y, z + other.z, w + other.w)
    operator fun minus(other: Int4) = Double4(x - other.x, y - other.y, z - other.z, w - other.w)
    operator fun times(other: Int4) = Double4(x * other.x, y * other.y, z * other.z, w * other.w)
    operator fun div(other: Int4) = Double4(x / other.x, y / other.y, z / other.z, w / other.w)

    operator fun plus(other: Float4) = Double4(x + other.x, y + other.y, z + other.z, w + other.w)
    operator fun minus(other: Float4) = Double4(x - other.x, y - other.y, z - other.z, w - other.w)
    operator fun times(other: Float4) = Double4(x * other.x, y * other.y, z * other.z, w * other.w)
    operator fun div(other: Float4) = Double4(x / other.x, y / other.y, z / other.z, w / other.w)

    operator fun plus(other: Double4) = Double4(x + other.x, y + other.y, z + other.z, w + other.w)
    operator fun minus(other: Double4) = Double4(x - other.x, y - other.y, z - other.z, w - other.w)
    operator fun times(other: Double4) = Double4(x * other.x, y * other.y, z * other.z, w * other.w)
    operator fun div(other: Double4) = Double4(x / other.x, y / other.y, z / other.z, w / other.w)

    operator fun plus(other: Short4) = Double4(x + other.x, y + other.y, z + other.z, w + other.w)
    operator fun minus(other: Short4) = Double4(x - other.x, y - other.y, z - other.z, w - other.w)
    operator fun times(other: Short4) = Double4(x * other.x, y * other.y, z * other.z, w * other.w)
    operator fun div(other: Short4) = Double4(x / other.x, y / other.y, z / other.z, w / other.w)

    operator fun plusAssign(other: Double4) { x += other.x; y += other.y; z += other.z; w += other.w }
    operator fun minusAssign(other: Double4) { x -= other.x; y -= other.y; z -= other.z; w -= other.w }
    operator fun timesAssign(other: Double4) { x *= other.x; y *= other.y; z *= other.z; w *= other.w }
    operator fun divAssign(other: Double4) { x /= other.x; y /= other.y; z /= other.z; w /= other.w }

    operator fun plus(value: Int) = Double4(x + value, y + value, z + value, w + value)
    operator fun minus(value: Int) = Double4(x - value, y - value, z - value, w - value)
    operator fun times(value: Int) = Double4(x * value, y * value, z * value, w * value)
    operator fun div(value: Int) = Double4(x / value, y / value, z / value, w / value)

    operator fun plus(value: Float) = Double4(x + value, y + value, z + value, w + value)
    operator fun minus(value: Float) = Double4(x - value, y - value, z - value, w - value)
    operator fun times(value: Float) = Double4(x * value, y * value, z * value, w * value)
    operator fun div(value: Float) = Double4(x / value, y / value, z / value, w / value)

    override operator fun plus(value: Double) = Double4(x + value, y + value, z + value, w + value)
    override operator fun minus(value: Double) = Double4(x - value, y - value, z - value, w - value)
    override operator fun times(value: Double) = Double4(x * value, y * value, z * value, w * value)
    override operator fun div(value: Double) = Double4(x / value, y / value, z / value, w / value)

    operator fun plus(value: Short) = Double4(x + value, y + value, z + value, w + value)
    operator fun minus(value: Short) = Double4(x - value, y - value, z - value, w - value)
    operator fun times(value: Short) = Double4(x * value, y * value, z * value, w * value)
    operator fun div(value: Short) = Double4(x / value, y / value, z / value, w / value)

    override operator fun plusAssign(value: Double) { x += value; y += value; z += value; w += value }
    override operator fun minusAssign(value: Double) { x -= value; y -= value; z -= value; w -= value }
    override operator fun timesAssign(value: Double) { x *= value; y *= value; z *= value; w *= value }
    override operator fun divAssign(value: Double) { x /= value; y /= value; z /= value; w /= value }

    override fun length(): Float = (x * x + y * y + z * z).sqrt()
    override fun lengthDouble(): Double = (x * x + y * y + z * z).dsqrt()
    override fun lengthSq(): Double = x * x + y * y + z * z
    override fun max(): Double = if (x > y && x > z && x > w) x else if (y > z && y > w) y else if (z > w) z else w
    override fun min(): Double = if (x < y && x < z && x < w) x else if (y < z && y < w) y else if (z < w) z else w
    override fun sum(): Double = x + y + z + w
    override fun diff(): Double = x - y - z - w
    override fun product(): Double = x * y * z * w
    override fun div(): Double = x / y / z / w
    override fun normalized(): Float4 = Double4(x / length(), y / length(), z / length(), w / length()).asFloat()

    override fun dist(other: Vector<Double>): Float {
        if (other is Double4) {
            val dx = x - other.x
            val dy = y - other.y
            val dz = z - other.z
            val dw = w - other.w
            return (dx * dx + dy * dy + dz * dz + dw * dw).sqrt()
        } else
            throw IllegalArgumentException("Other must be a Double4")
    }
    override fun distSq(other: Vector<Double>): Float {
        if (other is Double4) {
            val dx = x - other.x
            val dy = y - other.y
            val dz = z - other.z
            val dw = w - other.w
            return (dx * dx + dy * dy + dz * dz + dw * dw).toFloat()
        } else
            throw IllegalArgumentException("Other must be a Double4")
    }
    override fun dot(other: Vector<Double>): Float {
        if (other is Double4) {
            return (x * other.x + y * other.y + z * other.z + w * other.w).toFloat()
        } else
            throw IllegalArgumentException("Other must be a Double4")
    }
    override fun sdot(): Double = x * x + y * y + z * z + w * w
    override fun unaryMinus(): Double4 = Double4(-x, -y, -z, -w)

    override fun abs(): Double4 = Double4(x.fastAbs(), y.fastAbs(), z.fastAbs(), w.fastAbs())
    override fun avg(): Float = ((x + y + z + w) / 4.0).toFloat()
    override fun min(other: Vector<Double>): Double4 {
        if (other is Double4) {
            return Double4(
                if (x < other.x) x else other.x,
                if (y < other.y) y else other.y,
                if (z < other.z) z else other.z,
                if (w < other.w) w else other.w
            )
        } else
            throw IllegalArgumentException("Other must be a Double4")
    }
    override fun max(other: Vector<Double>): Double4 {
        if (other is Double4) {
            return Double4(
                if (x > other.x) x else other.x,
                if (y > other.y) y else other.y,
                if (z > other.z) z else other.z,
                if (w > other.w) w else other.w
            )
        } else
            throw IllegalArgumentException("Other must be a Double4")
    }
    override fun isIn(min: Double, max: Double): Bool4 = Bool4(x in min..max, y in min..max, z in min..max, w in min..max)
    override fun intPow(n: Int): Double4 = Double4(x.intPow(n), y.intPow(n), z.intPow(n), w.intPow(n))

    override fun smoothStep(): Double4 = Double4(x.smoothStep(), y.smoothStep(), z.smoothStep(), w.smoothStep())
    override fun smootherStep(): Double4 = Double4(x.smootherStep(), y.smootherStep(), z.smootherStep(), w.smootherStep())
    override fun lerp(min: Double, max: Double): Double4 = Double4(x.lerp(min, max), y.lerp(min, max), z.lerp(min, max), w.lerp(min, max))
    override fun clip(min: Double, max: Double): Double4 = Double4(x.clip(min, max), y.clip(min, max), z.clip(min, max), w.clip(min, max))

    override fun sin(): Double4 = Double4(SIN[x], SIN[y], SIN[z], SIN[w])
    override fun cos(): Double4 = Double4(COS[x], COS[y], COS[z], COS[w])
    override fun tan(): Double4 = Double4(TAN[x], TAN[y], TAN[z], TAN[w])

    override fun floor(): Int4 = Int4(x.toInt(), y.toInt(), z.toInt(), w.toInt())
    override fun ceil(): Int4 = Int4(x.fastCeil(), y.fastCeil(), z.fastCeil(), w.fastCeil())
    override fun fract(): Double4 = Double4(x - x.toInt(), y - y.toInt(), z - z.toInt(), w - w.toInt())

    override fun asFloat(): Float4 = Float4(x.toFloat(), y.toFloat(), z.toFloat(), w.toFloat())
    override fun asInt(): Int4 = Int4(x.toInt(), y.toInt(), z.toInt(), w.toInt())
    override fun asShort(): Short4 = Short4(x.toInt().toShort(), y.toInt().toShort(), z.toInt().toShort(), w.toInt().toShort())
    override fun asBoolean(): Bool4 = Bool4(x != 0.0, y != 0.0, z != 0.0, w != 0.0)
    override fun asRowMatrix(): DoubleMatrix = data.toMatrix(intArrayOf(1, 4))
    override fun asColumnMatrix(): DoubleMatrix = data.toMatrix(intArrayOf(4, 1))

    override fun xy(): Double2 = Double2(x, y)
    override fun xz(): Double2 = Double2(x, z)
    override fun xw(): Double2 = Double2(x, w)
    override fun yx(): Double2 = Double2(y, x)
    override fun yz(): Double2 = Double2(y, z)
    override fun yw(): Double2 = Double2(y, w)
    override fun zx(): Double2 = Double2(z, x)
    override fun zy(): Double2 = Double2(z, y)
    override fun zw(): Double2 = Double2(z, w)
    override fun wx(): Double2 = Double2(w, x)
    override fun wy(): Double2 = Double2(w, y)
    override fun wz(): Double2 = Double2(w, z)
    override fun xyz(): Double3 = Double3(x, y, z)
    override fun xyw(): Double3 = Double3(x, y, w)
    override fun xzy(): Double3 = Double3(x, z, y)
    override fun xzw(): Double3 = Double3(x, z, w)
    override fun xwy(): Double3 = Double3(x, w, y)
    override fun xwz(): Double3 = Double3(x, w, z)
    override fun yxz(): Double3 = Double3(y, x, z)
    override fun yxw(): Double3 = Double3(y, x, w)
    override fun yzx(): Double3 = Double3(y, z, x)
    override fun yzw(): Double3 = Double3(y, z, w)
    override fun ywx(): Double3 = Double3(y, w, x)
    override fun ywz(): Double3 = Double3(y, w, z)
    override fun zxy(): Double3 = Double3(z, x, y)
    override fun zxw(): Double3 = Double3(z, x, w)
    override fun zyx(): Double3 = Double3(z, y, x)
    override fun zyw(): Double3 = Double3(z, y, w)
    override fun zwx(): Double3 = Double3(z, w, x)
    override fun zwy(): Double3 = Double3(z, w, y)
    override fun wxy(): Double3 = Double3(w, x, y)
    override fun wxz(): Double3 = Double3(w, x, z)
    override fun wyx(): Double3 = Double3(w, y, x)
    override fun wyz(): Double3 = Double3(w, y, z)
    override fun wzx(): Double3 = Double3(w, z, x)
    override fun wzy(): Double3 = Double3(w, z, y)
    override fun xyzw(): Double4 = this
    override fun xywz(): Double4 = Double4(x, y, w, z)
    override fun xzyw(): Double4 = Double4(x, z, y, w)
    override fun xzwy(): Double4 = Double4(x, z, w, y)
    override fun xwyz(): Double4 = Double4(x, w, y, z)
    override fun xwzy(): Double4 = Double4(x, w, z, y)
    override fun yxzw(): Double4 = Double4(y, x, z, w)
    override fun yxwz(): Double4 = Double4(y, x, w, z)
    override fun yzxw(): Double4 = Double4(y, z, x, w)
    override fun yzwx(): Double4 = Double4(y, z, w, x)
    override fun ywxz(): Double4 = Double4(y, w, x, z)
    override fun ywzx(): Double4 = Double4(y, w, z, x)
    override fun zxyw(): Double4 = Double4(z, x, y, w)
    override fun zxwy(): Double4 = Double4(z, x, w, y)
    override fun zyxw(): Double4 = Double4(z, y, x, w)
    override fun zywx(): Double4 = Double4(z, y, w, x)
    override fun zwxy(): Double4 = Double4(z, w, x, y)
    override fun zwyx(): Double4 = Double4(z, w, y, x)
    override fun wxyz(): Double4 = Double4(w, x, y, z)
    override fun wxzy(): Double4 = Double4(w, x, z, y)
    override fun wyxz(): Double4 = Double4(w, y, x, z)
    override fun wyzx(): Double4 = Double4(w, y, z, x)
    override fun wzxy(): Double4 = Double4(w, z, x, y)
    override fun wzyx(): Double4 = Double4(w, z, y, x)
    override fun xxxx(): Double4 = Double4(x, x, x, x)
    override fun yyyy(): Double4 = Double4(y, y, y, y)
    override fun zzzz(): Double4 = Double4(z, z, z, z)
    override fun wwww(): Double4 = Double4(w, w, w, w)

    override fun copyOf(): Double4 = Double4(x, y, z, w)

    @Deprecated("Use operator instead", ReplaceWith("vector[i]"))
    override fun get(i: Int): Double = data[i]
    override fun get(row: Int, col: Int): Double = throw UnsupportedOperationException("Double4 is considered a vector")

    @Deprecated("Use operator instead", ReplaceWith("vector[i] = value"))
    override fun set(i: Int, value: Double) = when (i) {
        0 -> x = value
        1 -> y = value
        2 -> z = value
        3 -> w = value
        else -> throw IndexOutOfBoundsException("Index $i is out of bounds for Double4")
    }
    override fun set(row: Int, col: Int, value: Double) = throw UnsupportedOperationException("Double4 is considered a vector")

    override fun transpose(): Double4 = this
}

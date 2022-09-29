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
import com.github.polyrocketmatt.kmt.trigonometry.COS
import com.github.polyrocketmatt.kmt.trigonometry.SIN
import com.github.polyrocketmatt.kmt.trigonometry.TAN
import com.github.polyrocketmatt.kmt.vector.Swizzle2
import com.github.polyrocketmatt.kmt.vector.Vector
import com.github.polyrocketmatt.kmt.vector.bl.Bool2
import com.github.polyrocketmatt.kmt.vector.db.Double2
import com.github.polyrocketmatt.kmt.vector.it.Int2
import com.github.polyrocketmatt.kmt.vector.sh.Short2
import com.github.polyrocketmatt.kmt.vector.sh.ShortVector

operator fun Int.plus(other: Float2): Float2 = Float2(this + other.x, this + other.y)
operator fun Int.minus(other: Float2): Float2 = Float2(this - other.x, this - other.y)
operator fun Int.times(other: Float2): Float2 = Float2(this * other.x, this * other.y)
operator fun Int.div(other: Float2): Float2 = Float2(this / other.x, this / other.y)

operator fun Float.plus(other: Float2): Float2 = Float2(this + other.x, this + other.y)
operator fun Float.minus(other: Float2): Float2 = Float2(this - other.x, this - other.y)
operator fun Float.times(other: Float2): Float2 = Float2(this * other.x, this * other.y)
operator fun Float.div(other: Float2): Float2 = Float2(this / other.x, this / other.y)

operator fun Double.plus(other: Float2): Double2 = Double2(this + other.x, this + other.y)
operator fun Double.minus(other: Float2): Double2 = Double2(this - other.x, this - other.y)
operator fun Double.times(other: Float2): Double2 = Double2(this * other.x, this * other.y)
operator fun Double.div(other: Float2): Double2 = Double2(this / other.x, this / other.y)

operator fun Short.plus(other: Float2): Float2 = Float2(this + other.x, this + other.y)
operator fun Short.minus(other: Float2): Float2 = Float2(this - other.x, this - other.y)
operator fun Short.times(other: Float2): Float2 = Float2(this * other.x, this * other.y)
operator fun Short.div(other: Float2): Float2 = Float2(this / other.x, this / other.y)

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a 2-dimensional vector of floating-point numbers.
 *
 * @param x The x component of the vector.
 * @param y The y component of the vector.
 */
data class Float2(var x: Float, var y: Float) : FloatVector(), Swizzle2 {

    constructor() : this(0f, 0f)
    constructor(other: Float2) : this(other.x, other.y)
    constructor(x: Float) : this(x, x)

    operator fun plus(other: Float2) = Float2(x + other.x, y + other.y)
    operator fun minus(other: Float2) = Float2(x - other.x, y - other.y)
    operator fun times(other: Float2) = Float2(x * other.x, y * other.y)
    operator fun div(other: Float2) = Float2(x / other.x, y / other.y)

    operator fun plus(other: Int2) = Float2(x + other.x, y + other.y)
    operator fun minus(other: Int2) = Float2(x - other.x, y - other.y)
    operator fun times(other: Int2) = Float2(x * other.x, y * other.y)
    operator fun div(other: Int2) = Float2(x / other.x, y / other.y)

    operator fun plus(other: Double2) = Double2(x + other.x, y + other.y)
    operator fun minus(other: Double2) = Double2(x - other.x, y - other.y)
    operator fun times(other: Double2) = Double2(x * other.x, y * other.y)
    operator fun div(other: Double2) = Double2(x / other.x, y / other.y)

    operator fun plus(other: Short2) = Float2(x + other.x, y + other.y)
    operator fun minus(other: Short2) = Float2(x - other.x, y - other.y)
    operator fun times(other: Short2) = Float2(x * other.x, y * other.y)
    operator fun div(other: Short2) = Float2(x / other.x, y / other.y)

    operator fun plusAssign(other: Float2) { x += other.x; y += other.y }
    operator fun minusAssign(other: Float2) { x -= other.x; y -= other.y }
    operator fun timesAssign(other: Float2) { x *= other.x; y *= other.y }
    operator fun divAssign(other: Float2) { x /= other.x; y /= other.y }

    operator fun plus(other: Int) = Float2(x + other, y + other)
    operator fun minus(other: Int) = Float2(x - other, y - other)
    operator fun times(other: Int) = Float2(x * other, y * other)
    operator fun div(other: Int) = Float2(x / other, y / other)

    operator fun plus(other: Float) = Float2(x + other, y + other)
    operator fun minus(other: Float) = Float2(x - other, y - other)
    operator fun times(other: Float) = Float2(x * other, y * other)
    operator fun div(other: Float) = Float2(x / other, y / other)

    operator fun plus(other: Double) = Double2(x + other, y + other)
    operator fun minus(other: Double) = Double2(x - other, y - other)
    operator fun times(other: Double) = Double2(x * other, y * other)
    operator fun div(other: Double) = Double2(x / other, y / other)

    operator fun plus(other: Short) = Float2(x + other, y + other)
    operator fun minus(other: Short) = Float2(x - other, y - other)
    operator fun times(other: Short) = Float2(x * other, y * other)
    operator fun div(other: Short) = Float2(x / other, y / other)

    operator fun plusAssign(other: Float) { x += other; y += other }
    operator fun minusAssign(other: Float) { x -= other; y -= other }
    operator fun timesAssign(other: Float) { x *= other; y *= other }
    operator fun divAssign(other: Float) { x /= other; y /= other }

    override fun length(): Float = (x * x + y * y).sqrt()
    override fun lengthDouble(): Double = (x * x + y * y).dsqrt()
    override fun lengthSq(): Float = x * x + y * y
    override fun max(): Float = if (x > y) x else y
    override fun min(): Float = if (x < y) x else y
    override fun sum(): Float = x + y
    override fun diff(): Float = x - y
    override fun product(): Float = x * y
    override fun div(): Float = x / y
    override fun normalized(): Float2 = Float2(x / length(), y / length())

    operator fun get(i: Int): Float = when (i) {
        0 -> x
        1 -> y
        else -> throw IndexOutOfBoundsException()
    }
    operator fun set(i: Int, value: Float) {
        when (i) {
            0 -> x = value
            1 -> y = value
            else -> throw IndexOutOfBoundsException()
        }
    }

    override fun dist(other: Vector<Float>): Float {
        if (other is Float2) {
            val dx = x - other.x
            val dy = y - other.y
            return (dx * dx + dy * dy).sqrt()
        } else
            throw IllegalArgumentException("Other must be a Float2")
    }
    override fun distSq(other: Vector<Float>): Float {
        if (other is Float2) {
            val dx = x - other.x
            val dy = y - other.y
            return dx * dx + dy * dy
        } else
            throw IllegalArgumentException("Other must be a Float2")
    }
    override fun dot(other: Vector<Float>): Float {
        if (other is Float2)
            return x * other.x + y * other.y
        else
            throw IllegalArgumentException("Other must be a Float2")
    }
    override fun sdot(): Float = x * x + y * y
    override fun unaryMinus(): Float2 = Float2(-x, -y)

    override fun abs(): Float2 = Float2(x.fastAbs(), y.fastAbs())
    override fun avg(): Float = (x + y) / 2f
    override fun min(other: Vector<Float>): Float2 {
        return if (other is Float2)
            Float2(if (x < other.x) x else other.x, if (y < other.y) y else other.y)
        else
            throw IllegalArgumentException("Other must be a Float2")
    }
    override fun max(other: Vector<Float>): Float2 {
        return if (other is Float2)
            Float2(if (x > other.x) x else other.x, if (y > other.y) y else other.y)
        else
            throw IllegalArgumentException("Other must be a Float2")
    }
    override fun isIn(min: Float, max: Float): Bool2 = Bool2(x in min..max, y in min..max)
    override fun intPow(n: Int): Float2 = Float2(x.intPow(n), y.intPow(n))

    override fun smoothStep(): Float2 = Float2(x.smoothStep(), y.smoothStep())
    override fun smootherStep(): Float2 = Float2(x.smootherStep(), y.smootherStep())
    override fun lerp(min: Float, max: Float): Float2 = Float2(x.lerp(min, max), y.lerp(min, max))
    override fun clip(min: Float, max: Float): Float2 = Float2(x.clip(min, max), y.clip(min, max))

    override fun sin(): Double2 = Double2(SIN[x], SIN[y])
    override fun cos(): Double2 = Double2(COS[x], COS[y])
    override fun tan(): Double2 = Double2(TAN[x], TAN[y])

    override fun floor(): Int2 = Int2(x.toInt(), y.toInt())
    override fun ceil(): Int2 = Int2(x.fastCeil(), y.fastCeil())
    override fun fract(): Float2 = Float2(x - x.toInt(), y - y.toInt())

    override fun asDouble(): Double2 = Double2(x.toDouble(), y.toDouble())
    override fun asInt(): Int2 = Int2(x.toInt(), y.toInt())
    override fun asShort(): ShortVector = Short2(x.toInt().toShort(), y.toInt().toShort())
    override fun asBoolean(): Bool2 = Bool2(x != 0.0f, y != 0.0f)

    override fun xy(): Float2 = this
    override fun yx(): Float2 = Float2(y, x)
    override fun xx(): Float2 = Float2(x, x)
    override fun yy(): Float2 = Float2(y, y)
}

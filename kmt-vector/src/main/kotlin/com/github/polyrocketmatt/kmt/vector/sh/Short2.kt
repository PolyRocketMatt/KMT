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
import com.github.polyrocketmatt.kmt.common.storage.Tuple2
import com.github.polyrocketmatt.kmt.trigonometry.COS
import com.github.polyrocketmatt.kmt.trigonometry.SIN
import com.github.polyrocketmatt.kmt.trigonometry.TAN
import com.github.polyrocketmatt.kmt.vector.Swizzle2
import com.github.polyrocketmatt.kmt.vector.Vector
import com.github.polyrocketmatt.kmt.vector.bl.Bool2
import com.github.polyrocketmatt.kmt.vector.db.Double2
import com.github.polyrocketmatt.kmt.vector.fl.Float2
import com.github.polyrocketmatt.kmt.vector.it.Int2

operator fun Int.plus(other: Short2): Int2 = Int2(this + other.x, this + other.y)
operator fun Int.minus(other: Short2): Int2 = Int2(this - other.x, this - other.y)
operator fun Int.times(other: Short2): Int2 = Int2(this * other.x, this * other.y)
operator fun Int.div(other: Short2): Int2 = Int2(this / other.x, this / other.y)

operator fun Float.plus(other: Short2): Float2 = Float2(this + other.x, this + other.y)
operator fun Float.minus(other: Short2): Float2 = Float2(this - other.x, this - other.y)
operator fun Float.times(other: Short2): Float2 = Float2(this * other.x, this * other.y)
operator fun Float.div(other: Short2): Float2 = Float2(this / other.x, this / other.y)

operator fun Double.plus(other: Short2): Double2 = Double2(this + other.x, this + other.y)
operator fun Double.minus(other: Short2): Double2 = Double2(this - other.x, this - other.y)
operator fun Double.times(other: Short2): Double2 = Double2(this * other.x, this * other.y)
operator fun Double.div(other: Short2): Double2 = Double2(this / other.x, this / other.y)

operator fun Short.plus(other: Short2): Short2 = Int2(this + other.x, this + other.y).asShort()
operator fun Short.minus(other: Short2): Short2 = Int2(this - other.x, this - other.y).asShort()
operator fun Short.times(other: Short2): Short2 = Int2(this * other.x, this * other.y).asShort()
operator fun Short.div(other: Short2): Short2 = Int2(this / other.x, this / other.y).asShort()

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a 2-dimensional vector of shorts.
 *
 * @param x The x component of the vector.
 * @param y The y component of the vector.
 */
class Short2(x: Short, y: Short) : Tuple2<Short>(arrayOf(x, y)), ShortVector, Swizzle2 {

    constructor() : this(0, 0)
    constructor(other: Short2) : this(other.x, other.y)
    constructor(x: Short) : this(x, x)

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

    operator fun plus(other: Short2) = Int2(x + other.x, y + other.y).asShort()
    operator fun minus(other: Short2) = Int2(x - other.x, y - other.y).asShort()
    operator fun times(other: Short2) = Int2(x * other.x, y * other.y).asShort()
    operator fun div(other: Short2) = Int2(x / other.x, y / other.y).asShort()

    operator fun plusAssign(other: Short2) { x = (x + other.x.toInt()).toShort(); y = (y + other.y.toInt()).toShort() }
    operator fun minusAssign(other: Short2) { x = (x - other.x.toInt()).toShort(); y = (y - other.y.toInt()).toShort() }
    operator fun timesAssign(other: Short2) { x = (x * other.x.toInt()).toShort(); y = (y * other.y.toInt()).toShort() }
    operator fun divAssign(other: Short2) { x = (x / other.x.toInt()).toShort(); y = (y / other.y.toInt()).toShort() }

    operator fun plus(other: Int) = Int2(x + other, y + other)
    operator fun minus(other: Int) = Int2(x - other, y - other)
    operator fun times(other: Int) = Int2(x * other, y * other)
    operator fun div(other: Int) = Int2(x / other, y / other)

    operator fun plus(other: Float) = Float2(x + other, y + other)
    operator fun minus(other: Float) = Float2(x - other, y - other)
    operator fun times(other: Float) = Float2(x * other, y * other)
    operator fun div(other: Float) = Float2(x / other, y / other)

    operator fun plus(other: Double) = Double2(x + other, y + other)
    operator fun minus(other: Double) = Double2(x - other, y - other)
    operator fun times(other: Double) = Double2(x * other, y * other)
    operator fun div(other: Double) = Double2(x / other, y / other)

    operator fun plus(other: Short) = Int2(x + other, y + other).asShort()
    operator fun minus(other: Short) = Int2(x - other, y - other).asShort()
    operator fun times(other: Short) = Int2(x * other, y * other).asShort()
    operator fun div(other: Short) = Int2(x / other, y / other).asShort()

    operator fun plusAssign(other: Short) { x = (x + other).toShort(); y = (y + other).toShort() }
    operator fun minusAssign(other: Short) { x = (x - other).toShort(); y = (y - other).toShort() }
    operator fun timesAssign(other: Short) { x = (x * other).toShort(); y = (y * other).toShort() }
    operator fun divAssign(other: Short) { x = (x / other).toShort(); y = (y / other).toShort() }

    override fun length(): Float = (x * x + y * y).sqrt()
    override fun lengthDouble(): Double = (x * x + y * y).dsqrt()
    override fun lengthSq(): Short = (x * x + y * y).toShort()
    override fun max(): Short = if (x > y) x else y
    override fun min(): Short = if (x < y) x else y
    override fun sum(): Short = (x + y).toShort()
    override fun diff(): Short = (x - y).toShort()
    override fun product(): Short = (x * y).toShort()
    override fun div(): Short = (x / y).toShort()
    override fun normalized(): Float2 = Float2(x / length(), y / length())

    override fun dist(other: Vector<Short>): Float {
        if (other is Short2) {
            val dx = x - other.x
            val dy = y - other.y
            return (dx * dx + dy * dy).sqrt()
        } else
            throw IllegalArgumentException("Other must be a Short2")
    }
    override fun distSq(other: Vector<Short>): Float {
        if (other is Short2) {
            val dx = x - other.x
            val dy = y - other.y
            return (dx * dx + dy * dy).toFloat()
        } else
            throw IllegalArgumentException("Other must be a Short2")
    }
    override fun dot(other: Vector<Short>): Float {
        if (other is Short2)
            return (x * other.x + y * other.y).toFloat()
        else
            throw IllegalArgumentException("Other must be a Short2")
    }
    override fun sdot(): Short = (x * x + y * y).toShort()
    override fun unaryMinus(): Short2 = Int2(-x, -y).asShort()

    override fun abs(): Short2 = Short2(x.fastAbs(), y.fastAbs())
    override fun avg(): Float = (x + y) / 2.0f
    override fun min(other: Vector<Short>): Short2 {
        return if (other is Short2)
            Short2(if (x < other.x) x else other.x, if (y < other.y) y else other.y)
        else
            throw IllegalArgumentException("Other must be a Short2")
    }
    override fun max(other: Vector<Short>): Short2 {
        return if (other is Short2)
            Short2(if (x > other.x) x else other.x, if (y > other.y) y else other.y)
        else
            throw IllegalArgumentException("Other must be a Short2")
    }
    override fun isIn(min: Short, max: Short): Bool2 = Bool2(x in min..max, y in min..max,)
    override fun intPow(n: Int): Short2 = Short2(x.intPow(n), y.intPow(n))

    override fun sin(): Double2 = Double2(SIN[x], SIN[y])
    override fun cos(): Double2 = Double2(COS[x], COS[y])
    override fun tan(): Double2 = Double2(TAN[x], TAN[y])

    override fun asFloat(): Float2 = Float2(x.toFloat(), y.toFloat())
    override fun asDouble(): Double2 = Double2(x.toDouble(), y.toDouble())
    override fun asInt(): Int2 = Int2(x.toInt(), y.toInt())
    override fun asBoolean(): Bool2 = Bool2(x != 0.toShort(), y != 0.toShort())

    override fun xy(): Short2 = this
    override fun yx(): Short2 = Short2(y, x)
    override fun xx(): Short2 = Short2(x, x)
    override fun yy(): Short2 = Short2(y, y)
}

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
import com.github.polyrocketmatt.kmt.common.storage.MemoryStorage
import com.github.polyrocketmatt.kmt.common.storage.Tuple2
import com.github.polyrocketmatt.kmt.trigonometry.COS
import com.github.polyrocketmatt.kmt.trigonometry.SIN
import com.github.polyrocketmatt.kmt.trigonometry.TAN
import com.github.polyrocketmatt.kmt.vector.Swizzle2
import com.github.polyrocketmatt.kmt.vector.Vector
import com.github.polyrocketmatt.kmt.vector.bl.Bool2
import com.github.polyrocketmatt.kmt.vector.fl.Float2
import com.github.polyrocketmatt.kmt.vector.it.Int2
import com.github.polyrocketmatt.kmt.vector.sh.Short2

operator fun Int.plus(other: Double2): Double2 = Double2(this + other.x, this + other.y)
operator fun Int.minus(other: Double2): Double2 = Double2(this - other.x, this - other.y)
operator fun Int.times(other: Double2): Double2 = Double2(this * other.x, this * other.y)
operator fun Int.div(other: Double2): Double2 = Double2(this / other.x, this / other.y)

operator fun Float.plus(other: Double2): Double2 = Double2(this + other.x, this + other.y)
operator fun Float.minus(other: Double2): Double2 = Double2(this - other.x, this - other.y)
operator fun Float.times(other: Double2): Double2 = Double2(this * other.x, this * other.y)
operator fun Float.div(other: Double2): Double2 = Double2(this / other.x, this / other.y)

operator fun Double.plus(other: Double2): Double2 = Double2(this + other.x, this + other.y)
operator fun Double.minus(other: Double2): Double2 = Double2(this - other.x, this - other.y)
operator fun Double.times(other: Double2): Double2 = Double2(this * other.x, this * other.y)
operator fun Double.div(other: Double2): Double2 = Double2(this / other.x, this / other.y)

operator fun Short.plus(other: Double2): Double2 = Double2(this + other.x, this + other.y)
operator fun Short.minus(other: Double2): Double2 = Double2(this - other.x, this - other.y)
operator fun Short.times(other: Double2): Double2 = Double2(this * other.x, this * other.y)
operator fun Short.div(other: Double2): Double2 = Double2(this / other.x, this / other.y)

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a 2-dimensional vector of doubles.
 *
 * @param x The x component of the vector.
 * @param y The y component of the vector.
 */
class Double2(x: Double, y: Double) : Tuple2<Double>(arrayOf(x, y)), DoubleVector, Swizzle2 {

    constructor() : this(0.0, 0.0)
    constructor(other: Double2) : this(other.x, other.y)
    constructor(x: Double) : this(x, x)

    operator fun plus(other: Double2) = Double2(x + other.x, y + other.y)
    operator fun minus(other: Double2) = Double2(x - other.x, y - other.y)
    operator fun times(other: Double2) = Double2(x * other.x, y * other.y)
    operator fun div(other: Double2) = Double2(x / other.x, y / other.y)

    operator fun plus(other: Float2) = Double2(x + other.x, y + other.y)
    operator fun minus(other: Float2) = Double2(x - other.x, y - other.y)
    operator fun times(other: Float2) = Double2(x * other.x, y * other.y)
    operator fun div(other: Float2) = Double2(x / other.x, y / other.y)

    operator fun plus(other: Int2) = Double2(x + other.x, y + other.y)
    operator fun minus(other: Int2) = Double2(x - other.x, y - other.y)
    operator fun times(other: Int2) = Double2(x * other.x, y * other.y)
    operator fun div(other: Int2) = Double2(x / other.x, y / other.y)

    operator fun plus(other: Short2) = Double2(x + other.x, y + other.y)
    operator fun minus(other: Short2) = Double2(x - other.x, y - other.y)
    operator fun times(other: Short2) = Double2(x * other.x, y * other.y)
    operator fun div(other: Short2) = Double2(x / other.x, y / other.y)

    operator fun plusAssign(other: Double2) { x += other.x; y += other.y }
    operator fun minusAssign(other: Double2) { x -= other.x; y -= other.y }
    operator fun timesAssign(other: Double2) { x *= other.x; y *= other.y }
    operator fun divAssign(other: Double2) { x /= other.x; y /= other.y }

    operator fun plus(other: Int) = Double2(x + other, y + other)
    operator fun minus(other: Int) = Double2(x - other, y - other)
    operator fun times(other: Int) = Double2(x * other, y * other)
    operator fun div(other: Int) = Double2(x / other, y / other)

    operator fun plus(other: Float) = Double2(x + other, y + other)
    operator fun minus(other: Float) = Double2(x - other, y - other)
    operator fun times(other: Float) = Double2(x * other, y * other)
    operator fun div(other: Float) = Double2(x / other, y / other)

    operator fun plus(other: Double) = Double2(x + other, y + other)
    operator fun minus(other: Double) = Double2(x - other, y - other)
    operator fun times(other: Double) = Double2(x * other, y * other)
    operator fun div(other: Double) = Double2(x / other, y / other)

    operator fun plus(other: Short) = Double2(x + other, y + other)
    operator fun minus(other: Short) = Double2(x - other, y - other)
    operator fun times(other: Short) = Double2(x * other, y * other)
    operator fun div(other: Short) = Double2(x / other, y / other)

    operator fun plusAssign(other: Double) { x += other; y += other }
    operator fun minusAssign(other: Double) { x -= other; y -= other }
    operator fun timesAssign(other: Double) { x *= other; y *= other }
    operator fun divAssign(other: Double) { x /= other; y /= other }

    override fun length(): Float = (x * x + y * y).sqrt()
    override fun lengthDouble(): Double = (x * x + y * y).dsqrt()
    override fun lengthSq(): Double = x * x + y * y
    override fun max(): Double = if (x > y) x else y
    override fun min(): Double = if (x < y) x else y
    override fun sum(): Double = x + y
    override fun diff(): Double = x - y
    override fun product(): Double = x * y
    override fun div(): Double = x / y
    override fun normalized(): Float2 = Double2(x / length(), y / length()).asFloat()

    override fun dist(other: Vector<Double>): Float {
        if (other is Double2) {
            val dx = x - other.x
            val dy = y - other.y
            return (dx * dx + dy * dy).sqrt()
        } else
            throw IllegalArgumentException("Other must be a Double2")
    }
    override fun distSq(other: Vector<Double>): Float {
        if (other is Double2) {
            val dx = x - other.x
            val dy = y - other.y
            return (dx * dx + dy * dy).toFloat()
        } else
            throw IllegalArgumentException("Other must be a Double2")
    }
    override fun dot(other: Vector<Double>): Float {
        if (other is Double2) {
            return (x * other.x + y * other.y).toFloat()
        } else
            throw IllegalArgumentException("Other must be a Double2")
    }
    override fun sdot(): Double = x * x + y * y
    override fun unaryMinus(): Double2 = Double2(-x, -y)

    override fun abs(): Double2 = Double2(x.fastAbs(), y.fastAbs())
    override fun avg(): Float = ((x + y) / 2.0).toFloat()
    override fun min(other: Vector<Double>): Double2 {
        return if (other is Double2)
            Double2(if (x < other.x) x else other.x, if (y < other.y) y else other.y)
        else
            throw IllegalArgumentException("Other must be a Double2")
    }
    override fun max(other: Vector<Double>): Double2 {
        return if (other is Double2)
            Double2(if (x > other.x) x else other.x, if (y > other.y) y else other.y)
        else
            throw IllegalArgumentException("Other must be a Double2")
    }
    override fun isIn(min: Double, max: Double): Bool2 = Bool2(x in min..max, y in min..max)
    override fun intPow(n: Int): Double2 = Double2(x.intPow(n), y.intPow(n))

    override fun smoothStep(): Double2 = Double2(x.smoothStep(), y.smoothStep())
    override fun smootherStep(): Double2 = Double2(x.smootherStep(), y.smootherStep())
    override fun lerp(min: Double, max: Double): Double2 = Double2(x.lerp(min, max), y.lerp(min, max))
    override fun clip(min: Double, max: Double): Double2 = Double2(x.clip(min, max), y.clip(min, max))

    override fun sin(): Double2 = Double2(SIN[x], SIN[y])
    override fun cos(): Double2 = Double2(COS[x], COS[y])
    override fun tan(): Double2 = Double2(TAN[x], TAN[y])

    override fun floor(): Int2 = Int2(x.toInt(), y.toInt())
    override fun ceil(): Int2 = Int2(x.fastCeil(), y.fastCeil())
    override fun fract(): Double2 = Double2(x - x.toInt(), y - y.toInt())

    override fun asFloat(): Float2 = Float2(x.toFloat(), y.toFloat())
    override fun asInt(): Int2 = Int2(x.toInt(), y.toInt())
    override fun asShort(): Short2 = Short2(x.toInt().toShort(), y.toInt().toShort())
    override fun asBoolean(): Bool2 = Bool2(x != 0.0, y != 0.0)

    override fun xy(): Double2 = this
    override fun yx(): Double2 = Double2(y, x)
    override fun xx(): Double2 = Double2(x, x)
    override fun yy(): Double2 = Double2(y, y)

    override fun copyOf(): Double2 = Double2(x, y)
}

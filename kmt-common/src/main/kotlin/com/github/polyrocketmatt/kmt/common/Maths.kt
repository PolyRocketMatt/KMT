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

package com.github.polyrocketmatt.kmt.common

import java.math.BigInteger
import kotlin.math.round
fun summation() {}
fun product() {}

/**
 * Round a double to the specified number of decimal places.
 *
 * @param n The number of decimal places to round to.
 * @return The rounded double.
 */
fun Double.decimals(n: Int): Double {
    var multiplier = 1.0
    repeat(n) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

/**
 * Round a floating-point number to the specified number of decimal places.
 *
 * @param n The number of decimal places to round to.
 * @return The rounded floating-point number.
 */
fun Float.decimals(n: Int): Float {
    var multiplier = 1.0f
    repeat(n) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

/**
 * Check if the given integer is within a range.
 *
 * @param min The lower bound.
 * @param max The upper bound.
 * @return True if the integer is within this range (inclusive).
 */
fun Int.isIn(min: Int, max: Int): Boolean = this in min..max

/**
 * Check if the given short is within a range.
 *
 * @param min The lower bound.
 * @param max The upper bound.
 * @return True if the short is within this range (inclusive).
 */
fun Short.isIn(min: Short, max: Short): Boolean = this in min..max

/**
 * Check if the given float is within a range.
 *
 * @param min The lower bound.
 * @param max The upper bound.
 * @return True if the float is within this range (inclusive).
 */
fun Float.isIn(min: Float, max: Float): Boolean = this in min..max

/**
 * Check if the given double is within a range.
 *
 * @param min The lower bound.
 * @param max The upper bound.
 * @return True if the double is within this range (inclusive).
 */
fun Double.isIn(min: Double, max: Double): Boolean = this in min..max

/**
 * Gives the square root of the value.
 *
 * @return The square root of the value.
 */
fun Int.sqrt(): Float {
    if (this < 0)
        throw IllegalArgumentException("Cannot take the square root of a negative number!")
    if (this == 0)
        return 0.0f

    var t: Float
    var sqrt: Float = this / 2.0f

    do {
        t = sqrt
        sqrt = (t + (this / t)) / 2.0f
    } while ((t - sqrt) != 0.0f)

    return sqrt
}

/**
 * Gives the square root of the value.
 *
 * @return The square root of the value.
 */
fun Short.sqrt(): Float {
    if (this < 0)
        throw IllegalArgumentException("Cannot take the square root of a negative number!")
    if (this == 0.toShort())
        return 0.0f

    var t: Float
    var sqrt: Float = this / 2.0f

    do {
        t = sqrt
        sqrt = (t + (this / t)) / 2.0f
    } while ((t - sqrt) != 0.0f)

    return sqrt
}

/**
 * Gives the square root of the value.
 *
 * @return The square root of the value.
 */
fun Float.sqrt(): Float {
    if (this < 0.0f)
        throw IllegalArgumentException("Cannot take the square root of a negative number!")
    if (this == 0.0f)
        return 0.0f

    var t: Float
    var sqrt: Float = this / 2

    do {
        t = sqrt
        sqrt = (t + (this / t)) / 2
    } while ((t - sqrt) != 0.0f)

    return sqrt
}

/**
 * Gives the square root of the value.
 *
 * @return The square root of the value.
 */
fun Double.sqrt(): Float {
    if (this < 0.0)
        throw IllegalArgumentException("Cannot take the square root of a negative number!")
    if (this == 0.0)
        return 0.0f

    var t: Double
    var sqrt: Double = this / 2

    do {
        t = sqrt
        sqrt = (t + (this / t)) / 2
    } while ((t - sqrt) != 0.0)

    return sqrt.toFloat()
}

/**
 * Gives the square root of the value.
 *
 * @return The square root of the value.
 */
fun Int.dsqrt(): Double {
    if (this < 0)
        throw IllegalArgumentException("Cannot take the square root of a negative number!")
    if (this == 0)
        return 0.0

    var t: Double
    var sqrt: Double = this / 2.0

    do {
        t = sqrt
        sqrt = (t + (this / t)) / 2.0
    } while ((t - sqrt) != 0.0)

    return sqrt
}

/**
 * Gives the square root of the value.
 *
 * @return The square root of the value.
 */
fun Short.dsqrt(): Double {
    if (this < 0)
        throw IllegalArgumentException("Cannot take the square root of a negative number!")
    if (this == 0.toShort())
        return 0.0

    var t: Double
    var sqrt: Double = this / 2.0

    do {
        t = sqrt
        sqrt = (t + (this / t)) / 2.0
    } while ((t - sqrt) != 0.0)

    return sqrt
}

/**
 * Gives the square root of the value.
 *
 * @return The square root of the value.
 */
fun Float.dsqrt(): Double {
    if (this < 0)
        throw IllegalArgumentException("Cannot take the square root of a negative number!")
    if (this == 0.0f)
        return 0.0

    var t: Double
    var sqrt: Double = this / 2.0

    do {
        t = sqrt
        sqrt = (t + (this / t)) / 2
    } while ((t - sqrt) != 0.0)

    return sqrt
}

/**
 * Gives the square root of the value.
 *
 * @return The square root of the value.
 */
fun Double.dsqrt(): Double {
    if (this < 0)
        throw IllegalArgumentException("Cannot take the square root of a negative number!")
    if (this == 0.0)
        return 0.0

    var t: Double
    var sqrt: Double = this / 2

    do {
        t = sqrt
        sqrt = (t + (this / t)) / 2
    } while ((t - sqrt) != 0.0)

    return sqrt
}

/**
 * Gives the n-th integer power of the value.
 *
 * @param n The exponent.
 * @return The value raised to the n-th power.
 */
fun Int.intPow(n: Int): Int {
    return if (this == 0 && n == 0) 1
    else if (this == 0) 0
    else if (n == 0) 1
    else {
        var res = this
        for (i in 0 until (n - 1))
            res *= this
        res
    }
}

/**
 * Gives the n-th integer power of the value.
 *
 * @param n The exponent.
 * @return The value raised to the n-th power.
 */
fun Short.intPow(n: Int): Short {
    return if (this == 0.toShort() && n == 0) 1
    else if (this == 0.toShort()) 0
    else if (n == 0) 1
    else {
        var res = this
        for (i in 0 until (n - 1))
            res = (res * this).toShort()
        res
    }
}

/**
 * Gives the n-th integer power of the value.
 *
 * @param n The exponent.
 * @return The value raised to the n-th power.
 */
fun Float.intPow(n: Int): Float {
    return if (this == 0.0f && n == 0) 1.0f
    else if (this == 0.0f) 0.0f
    else if (n == 0) 1.0f
    else {
        var res = this
        for (i in 0 until (n - 1))
            res *= this
        res
    }
}

/**
 * Gives the n-th integer power of the value.
 *
 * @param n The exponent.
 * @return The value raised to the n-th power.
 */
fun Double.intPow(n: Int): Double {
    return if (this == 0.0 && n == 0) 1.0
    else if (this == 0.0) 0.0
    else if (n == 0) 1.0
    else {
        var res = this
        for (i in 0 until (n - 1))
            res *= this
        res
    }
}

fun Int.factorial(): BigInteger {
    if (this < 0)
        throw IllegalArgumentException("Cannot take the factorial of a negative number!")
    if (this == 0)
        return BigInteger.ONE

    var factorial = BigInteger.ONE
    for (i in 0 until this)
        factorial = factorial.multiply(BigInteger.valueOf((i + 1).toLong()))
    return factorial
}

//  TODO: Implement Gamma function
fun Float.factorial(n: Int) = NotImplementedError("Not implemented yet!")

//  TODO: Implement Gamma function
fun Double.factorial(n: Int) = NotImplementedError("Not implemented yet!")

/**
 * SmoothStep function for a floating point value that is
 * expected to be within [0, 1].
 *
 * @return The smooth-stepped value.
 */
fun Float.smoothStep(): Float = this * this * (3.0f - 2.0f * this)

/**
 * SmootherStep function for a floating point value that is
 * expected to be within [0, 1].
 *
 * @return The smoother-stepped value.
 */
fun Float.smootherStep(): Float = this * this * this * (this * (this * 6.0f - 15.0f) + 10.0f)

/**
 * SmoothStep function for a double value that is
 * expected to be within [0, 1].
 *
 * @return The smooth-stepped value.
 */
fun Double.smoothStep(): Double = this * this * (3.0 - 2.0 * this)

/**
 * SmootherStep function for a double value that is
 * expected to be within [0, 1].
 *
 * @return The smoother-stepped value.
 */
fun Double.smootherStep(): Double = this * this * this * (this * (this * 6.0 - 15.0) + 10.0)

/**
 * Linear interpolation for the value to be within [a, b].
 *
 * @param min The lower bound.
 * @param max The upper bound.
 * @return The linearly interpolated value.
 */
fun Float.lerp(min: Float, max: Float): Float = min + this * (max - min)

/**
 * Linear interpolation for the value to be within [a, b].
 *
 * @param min The lower bound.
 * @param max The upper bound.
 * @return The linearly interpolated value.
 */
fun Double.lerp(min: Double, max: Double): Double = min + this * (max - min)

/**
 * Normalize the value to be within [0, 1].
 *
 * @param min The lower bound.
 * @param max The upper bound.
 * @return The normalized value.
 */
fun Float.normalize(min: Float, max: Float): Float = (this - min) / (max - min)

/**
 * Normalize the value to be within [0, 1].
 *
 * @param min The lower bound.
 * @param max The upper bound.
 * @return The normalized value.
 */
fun Double.normalize(min: Double, max: Double): Double = (this - min) / (max - min)

/**
 * Clip a value to be within [min, max].
 *
 * @param min The lower bound.
 * @param max The upper bound.
 * @return This if value ∈ [min, max], min if this < min and max if this > max.
 */
fun Int.clip(min: Int, max: Int) = if (this < min) min else if (this > max) max else this

/**
 * Clip a value to be within [min, max].
 *
 * @param min The lower bound.
 * @param max The upper bound.
 * @return This if value ∈ [min, max], min if this < min and max if this > max.
 */
fun Short.clip(min: Short, max: Short) = if (this < min) min else if (this > max) max else this

/**
 * Clip a value to be within [min, max].
 *
 * @param min The lower bound.
 * @param max The upper bound.
 * @return This if value ∈ [min, max], min if this < min and max if this > max.
 */
fun Float.clip(min: Float, max: Float) = if (this < min) min else if (this > max) max else this

/**
 * Clip a value to be within [min, max].
 *
 * @param min The lower bound.
 * @param max The upper bound.
 * @return This if value ∈ [min, max], min if this < min and max if this > max.
 */
fun Double.clip(min: Double, max: Double) = if (this < min) min else if (this > max) max else this

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Provides non-extended variants of the available maths functions.
 */
class Maths {

    companion object {

        /**
         * Gives the square root of the value.
         *
         * @param value The value to take the square root of.
         * @return The square root of the value.
         */
        fun sqrt(value: Int): Float {
            if (value <= 0)
                return 0.0f

            var t: Float
            var sqrt: Float = value / 2.0f

            do {
                t = sqrt
                sqrt = (t + (value / t)) / 2.0f
            } while ((t - sqrt) != 0.0f)

            return sqrt
        }

        /**
         * Gives the square root of the value.
         *
         * @param value The value to take the square root of.
         * @return The square root of the value.
         */
        fun sqrt(value: Short): Float {
            if (value <= 0)
                return 0.0f

            var t: Float
            var sqrt: Float = value / 2.0f

            do {
                t = sqrt
                sqrt = (t + (value / t)) / 2.0f
            } while ((t - sqrt) != 0.0f)

            return sqrt
        }

        /**
         * Gives the square root of the value.
         *
         * @param value The value to take the square root of.
         * @return The square root of the value.
         */
        fun sqrt(value: Float): Float {
            if (value <= 0.0f)
                return 0.0f

            var t: Float
            var sqrt: Float = value / 2

            do {
                t = sqrt
                sqrt = (t + (value / t)) / 2
            } while ((t - sqrt) != 0.0f)

            return sqrt
        }

        /**
         * Gives the square root of the value.
         *
         * @param value The value to take the square root of.
         * @return The square root of the value.
         */
        fun sqrt(value: Double): Double {
            if (value <= 0.0)
                return 0.0

            var t: Double
            var sqrt: Double = value / 2

            do {
                t = sqrt
                sqrt = (t + (value / t)) / 2
            } while ((t - sqrt) != 0.0)

            return sqrt
        }

        /**
         * Gives the n-th integer power of the value.
         *
         * @param value The value to raise to the n-th power.
         * @param n The exponent.
         * @return The value raised to the n-th power.
         */
        fun intPow(value: Int, n: Int): Int {
            return if (value == 0 && n == 0) 0
            else if (value == 0) 0
            else if (n == 0) 1
            else {
                var res = value
                for (i in 0 until (n - 1))
                    res *= value
                res
            }
        }

        /**
         * Gives the n-th integer power of the value.
         *
         * @param value The value to raise to the n-th power.
         * @param n The exponent.
         * @return The value raised to the n-th power.
         */
        fun intPow(value: Short, n: Int): Short {
            return if (value == 0.toShort() && n == 0) 0
            else if (value == 0.toShort()) 0
            else if (n == 0) 1
            else {
                var res = value
                for (i in 0 until (n - 1))
                    res = (res * value).toShort()
                res
            }
        }

        /**
         * Gives the n-th integer power of the value.
         *
         * @param value The value to raise to the n-th power.
         * @param n The exponent.
         * @return The value raised to the n-th power.
         */
        fun intPow(value: Float, n: Int): Float {
            return if (value == 0.0f && n == 0) Float.NaN
            else if (value == 0.0f) 0.0f
            else if (n == 0) 1.0f
            else {
                var res = value
                for (i in 0 until (n - 1))
                    res *= value
                res
            }
        }

        /**
         * Gives the n-th integer power of the value.
         *
         * @param value The value to raise to the n-th power.
         * @param n The exponent.
         * @return The value raised to the n-th power.
         */
        fun intPow(value: Double, n: Int): Double {
            return if (value == 0.0 && n == 0) Double.NaN
            else if (value == 0.0) 0.0
            else if (n == 0) 1.0
            else {
                var res = value
                for (i in 0 until (n - 1))
                    res *= value
                res
            }
        }

        /**
         * SmoothStep function for a floating point value that is
         * expected to be within [0, 1].
         *
         * @param value The value to smooth-step.
         * @return The smooth-stepped value.
         */
        fun smoothStep(value: Float): Float = value * value * (3.0f - 2.0f * value)

        /**
         * SmootherStep function for a floating point value that is
         * expected to be within [0, 1].
         *
         * @param value The value to smoother-step.
         * @return The smoother-stepped value.
         */
        fun smootherStep(value: Float): Float = value * value * value * (value * (value * 6.0f - 15.0f) + 10.0f)

        /**
         * SmoothStep function for a double value that is
         * expected to be within [0, 1].
         *
         * @param value The value to smooth-step.
         * @return The smooth-stepped value.
         */
        fun smoothStep(value: Double): Double = value * value * (3.0 - 2.0 * value)

        /**
         * SmootherStep function for a double value that is
         * expected to be within [0, 1].
         *
         * @param value The value to smoother-step.
         * @return The smoother-stepped value.
         */
        fun smootherStep(value: Double): Double = value * value * value * (value * (value * 6.0 - 15.0) + 10.0)

        /**
         * Normalize the value to be within [0, 1].
         *
         * @param value The value to normalize.
         * @param min The lower bound.
         * @param max The upper bound.
         * @return The normalized value.
         */
        fun normalize(value: Float, min: Float, max: Float): Float = (value - min) / (max - min)

        /**
         * Normalize the value to be within [0, 1].
         *
         * @param value The value to normalize.
         * @param min The lower bound.
         * @param max The upper bound.
         * @return The normalized value.
         */
        fun normalize(value: Double, min: Double, max: Double): Double = (value - min) / (max - min)

        /**
         * Clip a value to be within [min, max].
         *
         * @param value The value to clip.
         * @param min The lower bound.
         * @param max The upper bound.
         * @return This if value ∈ [min, max], min if this < min and max if this > max.
         */
        fun clip(value: Int, min: Int, max: Int) = if (value < min) min else if (value > max) max else value

        /**
         * Clip a value to be within [min, max].
         *
         * @param value The value to clip.
         * @param min The lower bound.
         * @param max The upper bound.
         * @return This if value ∈ [min, max], min if this < min and max if this > max.
         */
        fun clip(value: Short, min: Short, max: Short) = if (value < min) min else if (value > max) max else value

        /**
         * Clip a value to be within [min, max].
         *
         * @param value The value to clip.
         * @param min The lower bound.
         * @param max The upper bound.
         * @return This if value ∈ [min, max], min if this < min and max if this > max.
         */
        fun clip(value: Float, min: Float, max: Float) = if (value < min) min else if (value > max) max else value

        /**
         * Clip a value to be within [min, max].
         *
         * @param value The value to clip.
         * @param min The lower bound.
         * @param max The upper bound.
         * @return This if value ∈ [min, max], min if this < min and max if this > max.
         */
        fun clip(value: Double, min: Double, max: Double) = if (value < min) min else if (value > max) max else value
    }
}

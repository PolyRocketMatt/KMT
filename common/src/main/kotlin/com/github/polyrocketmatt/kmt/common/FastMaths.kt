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

/**
 * Gives the absolute value of an integer value.
 *
 * @return The absolute value of the value.
 */
fun Int.fastAbs(): Int = if (this < 0) -this else this

/**
 * Gives the absolute value of a short value.
 *
 * @return The absolute value of the value.
 */
fun Short.fastAbs(): Short = if (this < 0) (-this).toShort() else this

/**
 * Gives the absolute value of a floating point value.
 *
 * @return The absolute value of the value.
 */
fun Float.fastAbs(): Float = if (this < 0.0f) -this else this

/**
 * Gives the absolute value of a double value.
 *
 * @return The absolute value of the value.
 */
fun Double.fastAbs(): Double = if (this < 0.0) -this else this

/**
 * Gives the floored value of a floating point value.
 *
 * @return The floored value of the value.
 */
fun Float.fastFloor(): Int = if (this >= 0.0f) this.toInt() else this.toInt() - 1

/**
 * Gives the floored value of a double value.
 *
 * @return The floored value of the value.
 */
fun Double.fastFloor(): Int = if (this >= 0.0) this.toInt() else this.toInt() - 1

/**
 * Gives the floored value of a floating point value.
 *
 * @return The floored value of the value.
 */
fun Float.fastCeil(): Int = if (this >= 0.0f) this.toInt() + 1 else this.toInt()

/**
 * Gives the floored value of a double value.
 *
 * @return The floored value of the value.
 */
fun Double.fastCeil(): Int = if (this >= 0.0) this.toInt() + 1 else this.toInt()

/**
 * Gives the rounded value of a floating point value.
 *
 * @return The rounded value of the value.
 */
fun Float.fastRound(): Int = if (this >= 0.0f) (this + 0.5f).toInt() else (this - 0.5f).toInt()

/**
 * Gives the rounded value of a double value.
 *
 * @return The rounded value of the value.
 */
fun Double.fastRound(): Int = if (this >= 0.0) (this + 0.5).toInt() else (this - 0.5).toInt()

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Class containing extensions and methods for fast mathematical operations.
 */
class FastMath {

    /**
     * Gives the absolute value of an integer value.
     *
     * @param value The value to take the absolute value of.
     * @return The absolute value of the value.
     */
    fun fastAbs(value: Int): Int = if (value < 0) -value else value

    /**
     * Gives the absolute value of a short value.
     *
     * @param value The value to take the absolute value of.
     * @return The absolute value of the value.
     */
    fun fastAbs(value: Short): Short = if (value < 0) (-value).toShort() else value

    /**
     * Gives the absolute value of a floating point value.
     *
     * @param value The value to take the absolute value of.
     * @return The absolute value of the value.
     */
    fun fastAbs(value: Float): Float = if (value < 0.0f) -value else value

    /**
     * Gives the absolute value of a double value.
     *
     * @param value The value to take the absolute value of.
     * @return The absolute value of the value.
     */
    fun fastAbs(value: Double): Double = if (value < 0.0) -value else value

    /**
     * Gives the floored value of a floating point value.
     *
     * @param value The value to floor.
     * @return The floored value of the value.
     */
    fun fastFloor(value: Float): Int = if (value >= 0.0f) value.toInt() else value.toInt() - 1

    /**
     * Gives the floored value of a double value.
     *
     * @param value The value to floor.
     * @return The floored value of the value.
     */
    fun fastFloor(value: Double): Int = if (value >= 0.0) value.toInt() else value.toInt() - 1

    /**
     * Gives the rounded value of a floating point value.
     *
     * @param value The value to round.
     * @return The rounded value of the value.
     */
    fun fastRound(value: Float): Int = if (value >= 0.0f) (value + 0.5f).toInt() else (value - 0.5f).toInt()

    /**
     * Gives the rounded value of a double value.
     *
     * @param value The value to round.
     * @return The rounded value of the value.
     */
    fun fastRound(value: Double): Int = if (value >= 0.0) (value + 0.5).toInt() else (value - 0.5).toInt()
}

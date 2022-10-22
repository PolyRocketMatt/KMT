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

package com.github.polyrocketmatt.kmt.interval.open

import com.github.polyrocketmatt.kmt.interval.closed.ClosedFloatInterval

/**
 * Creates an open interval from the given floating-point number to another floating-point number
 * with the provided accuracy.
 *
 * @param other The other floating-point number to create the interval with.
 * @param accuracy The accuracy of the interval.
 * @return An open interval from this floating-point number to the other floating-point number.
 */
fun Float.rangeTo(other: Float, accuracy: Float = 0.001f): OpenFloatInterval = OpenFloatInterval(this, other, accuracy)

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents an open interval of floating-point numbers between start and end.
 *
 * @param start The minimum value of the range.
 * @param end The maximum value of the range.
 * @param accuracy The accuracy of the range.
 */
class OpenFloatInterval(private val start: Float, private val end: Float, private val accuracy: Float) : ClosedFloatInterval(start, end, accuracy), OpenInterval<Float> {

    override fun isIn(value: Float): Boolean = value > start && value < end

    override fun withoutEdge(): ClosedFloatInterval = ClosedFloatInterval(start + accuracy, end - accuracy, accuracy)
}

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

package com.github.polyrocketmatt.kmt.interval.half

import com.github.polyrocketmatt.kmt.interval.closed.ClosedFloatInterval

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a right half-open interval of floating-point numbers between start and end.
 *
 * @param start The minimum value of the range.
 * @param end The maximum value of the range.
 * @param accuracy The accuracy of the range.
 */
class RightOpenFloatInterval(private val start: Float, private val end: Float, private val accuracy: Float) : ClosedFloatInterval(start, end, accuracy), HalfOpenInterval<Float> {

    override fun isIn(value: Float): Boolean = value >= start && value < end

    override fun withoutEdge(): ClosedFloatInterval = ClosedFloatInterval(start, end - accuracy, accuracy)
}

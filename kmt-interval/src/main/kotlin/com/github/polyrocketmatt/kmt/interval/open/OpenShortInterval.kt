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

import com.github.polyrocketmatt.kmt.interval.closed.ClosedShortInterval

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents an open interval of integer numbers between start and end.
 *
 * @param start The minimum value of the range.
 * @param end The maximum value of the range.
 */
class OpenShortInterval(private val start: Short, private val end: Short) : ClosedShortInterval(start, end), OpenInterval<Short> {

    override fun isIn(value: Short): Boolean = value in (start + 1) until end

    override fun withoutEdge(): ClosedShortInterval = ClosedShortInterval((start + 1).toShort(), (end - 1).toShort())
}

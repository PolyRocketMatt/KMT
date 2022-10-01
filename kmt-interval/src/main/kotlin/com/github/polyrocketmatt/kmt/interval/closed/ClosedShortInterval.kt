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

package com.github.polyrocketmatt.kmt.interval.closed

import com.github.polyrocketmatt.kmt.common.fastAbs

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a closed interval of integer numbers between start and end.
 *
 * @param start The minimum value of the range.
 * @param end The maximum value of the range.
 */
open class ClosedShortInterval(private val start: Short, private val end: Short) : ClosedInterval<Short> {

    private val values: ShortArray = ShortArray((end - start).fastAbs() + 1) { if (end > start) (it + start).toShort() else (it + end).toShort() }

    open override fun isIn(value: Short): Boolean = value in start..end

    override fun get(index: Int): Short = values[index]

    override fun min(): Short = start

    override fun max(): Short = end

    override fun median(): Short = values[values.size / 2]

    override fun avg(): Short = (values.sum() / values.size).toShort()

    override fun sum(): Short = values.sum().toShort()

    override fun product(): Short = (values.fold(1) { acc, i -> acc * i }).toShort()

    override fun count(): Int = values.size

    override fun <K> map(transform: (Short) -> K): List<K> = values.map(transform)

    override fun <K> mapIndexed(transform: (Int, Short) -> K): List<K> = values.mapIndexed(transform)

    override fun forEach(action: (Short) -> Unit) = values.forEach(action)

    override fun forEachIndexed(action: (Int, Short) -> Unit) = values.forEachIndexed(action)

    override fun asArray(): Array<Short> = values.toTypedArray()

    override fun asList(): List<Short> = values.toList()

    override fun asSet(): Set<Short> = values.toSet()

    override fun asSequence(): Sequence<Short> = values.asSequence()

    override fun asIterable(): Iterable<Short> = values.asIterable()
}

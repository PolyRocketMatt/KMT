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
 * Represents an exclusive range (closed interval) of integer numbers between start and end.
 *
 * @param start The minimum value of the range.
 * @param end The maximum value of the range.
 */
open class ClosedIntInterval(private val start: Int, private val end: Int): ClosedInterval<Int> {

    private val values: IntArray = IntArray((end - start).fastAbs() + 1) { if (end > start ) it + start else it + end }

    open override fun isIn(value: Int): Boolean = value in start..end

    override fun get(index: Int): Int = values[index]

    override fun min(): Int = start

    override fun max(): Int = end

    override fun median(): Int = values[values.size / 2]

    override fun avg(): Int = values.sum() / values.size

    override fun sum(): Int = values.sum()

    override fun product(): Int = values.fold(1) { acc, i -> acc * i }

    override fun count(): Int = values.size

    override fun <K> map(transform: (Int) -> K): List<K> = values.map(transform)

    override fun <K> mapIndexed(transform: (Int, Int) -> K): List<K> = values.mapIndexed(transform)

    override fun forEach(action: (Int) -> Unit) = values.forEach(action)

    override fun forEachIndexed(action: (Int, Int) -> Unit) = values.forEachIndexed(action)

    override fun asArray(): Array<Int> = values.toTypedArray()

    override fun asList(): List<Int> = values.toList()

    override fun asSet(): Set<Int> = values.toSet()

    override fun asSequence(): Sequence<Int> = values.asSequence()

    override fun asIterable(): Iterable<Int> = values.asIterable()
}
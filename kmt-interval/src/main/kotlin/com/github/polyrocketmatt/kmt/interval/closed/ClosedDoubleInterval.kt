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

import com.github.polyrocketmatt.kmt.common.decimals
import com.github.polyrocketmatt.kmt.common.fastAbs
import com.github.polyrocketmatt.kmt.common.utils.decimalPlaces

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a closed interval of double numbers between start and end.
 *
 * @param start The minimum value of the range.
 * @param end The maximum value of the range.
 * @param accuracy The accuracy of the range.
 */
open class ClosedDoubleInterval(private var start: Double, private var end: Double, accuracy: Double = 1.0) : ClosedInterval<Double> {

    private val values: DoubleArray = DoubleArray((1.0 / accuracy).toInt() + 1)

    init {
        val decimals = accuracy.decimalPlaces()
        if (start == Double.NEGATIVE_INFINITY)
            start = -Double.MAX_VALUE
        if (end == Double.POSITIVE_INFINITY)
            end = Double.MAX_VALUE

        val tMin = if (start < end) start else end
        val diff = (end - start).fastAbs()
        val step = diff / (1.0 / accuracy)
        var i = 0.0
        var idx = 0
        while (i <= diff) {
            values[idx] = (i + tMin).decimals(decimals)
            idx++
            i += step
        }
    }

    open override fun isIn(value: Double): Boolean = value in start..end

    override fun get(index: Int): Double = values[index]

    override fun min(): Double = start

    override fun max(): Double = end

    override fun median(): Double = values[values.size / 2]

    override fun avg(): Double = values.sum() / values.size

    override fun sum(): Double = values.sum()

    override fun product(): Double = values.fold(1.0) { acc, i -> acc * i }

    override fun count(): Int = values.size

    override fun <K> map(transform: (Double) -> K): List<K> = values.map(transform)

    override fun <K> mapIndexed(transform: (Int, Double) -> K): List<K> = values.mapIndexed(transform)

    override fun forEach(action: (Double) -> Unit) = values.forEach(action)

    override fun forEachIndexed(action: (Int, Double) -> Unit) = values.forEachIndexed(action)

    override fun asArray(): Array<Double> = values.toTypedArray()

    override fun asList(): List<Double> = values.toList()

    override fun asSet(): Set<Double> = values.toSet()

    override fun asSequence(): Sequence<Double> = values.asSequence()

    override fun asIterable(): Iterable<Double> = values.asIterable()
}

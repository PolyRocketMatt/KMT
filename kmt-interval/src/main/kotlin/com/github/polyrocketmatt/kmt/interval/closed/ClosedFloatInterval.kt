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
import kotlin.math.roundToInt

/**
 * Creates a closed interval from the given floating-point number to another floating-point number
 * with the provided accuracy.
 *
 * @param other The other floating-point number to create the interval with.
 * @param accuracy The accuracy of the interval.
 * @return A closed interval from this floating-point number to the other floating-point number.
 */
fun Float.rangeTo(other: Float, accuracy: Float = 0.001f): ClosedFloatInterval = ClosedFloatInterval(this, other, accuracy)

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a closed interval of floating point numbers between start and end.
 *
 * @param start The minimum value of the range.
 * @param end The maximum value of the range.
 * @param accuracy The accuracy of the range.
 */
open class ClosedFloatInterval(private var start: Float, private var end: Float, accuracy: Float = 1.0f) : ClosedInterval<Float> {

    private val values: FloatArray = FloatArray((1.0 / accuracy.toDouble()).roundToInt() + 1)

    init {
        val decimals = accuracy.decimalPlaces()
        if (start == Float.NEGATIVE_INFINITY)
            start = -Float.MAX_VALUE
        if (end == Float.POSITIVE_INFINITY)
            end = Float.MAX_VALUE

        val tMin = if (start < end) start else end
        val diff = (end - start).fastAbs()
        val step = diff / (1.0f / accuracy)
        var i = 0.0f
        var idx = 0
        while (i <= diff) {
            values[idx] = (i + tMin).decimals(decimals)
            idx++
            i += step
        }
    }

    open override fun isIn(value: Float): Boolean = value in start..end

    override fun get(index: Int): Float = values[index]

    override fun min(): Float = start

    override fun max(): Float = end

    override fun median(): Float = values[values.size / 2]

    override fun avg(): Float = values.sum() / values.size

    override fun sum(): Float = values.sum()

    override fun product(): Float = values.fold(1.0f) { acc, i -> acc * i }

    override fun count(): Int = values.size

    override fun <K> map(transform: (Float) -> K): List<K> = values.map(transform)

    override fun <K> mapIndexed(transform: (Int, Float) -> K): List<K> = values.mapIndexed(transform)

    override fun forEach(action: (Float) -> Unit) = values.forEach(action)

    override fun forEachIndexed(action: (Int, Float) -> Unit) = values.forEachIndexed(action)

    override fun asArray(): Array<Float> = values.toTypedArray()

    override fun asList(): List<Float> = values.toList()

    override fun asSet(): Set<Float> = values.toSet()

    override fun asSequence(): Sequence<Float> = values.asSequence()

    override fun asIterable(): Iterable<Float> = values.asIterable()
}

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

package com.github.polyrocketmatt.kmt.interval

/**
 * Check if the given value is contained within the interval.
 *
 * @param range The interval to check.
 * @return True if the value is contained within the interval, false otherwise.
 */
infix fun <T> T.within(range: Interval<T>): Boolean = range.isIn(this)

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents an interval of values.
 *
 * @param <T> The data type of the range.
 */
interface Interval<T> {

    /**
     * Check if the given value is within the interval.
     *
     * @param value The value to check.
     * @return True if the value is within the interval, false otherwise.
     */
    fun isIn(value: T): Boolean

    /**
     * Get the element at the given index.
     *
     * @param index The index of the element.
     * @return The element at the given index.
     */
    operator fun get(index: Int): T

    /**
     * Get the minimal value within the interval.
     *
     * @return The minimal value within the interval.
     */
    fun min(): T

    /**
     * Get the maximal value within the interval.
     *
     * @return The maximal value within the interval.
     */
    fun max(): T

    /**
     * Get the median value of the interval.
     *
     * @return The median value of the interval.
     */
    fun median(): T

    /**
     * Get the average value of the interval.
     *
     * @return The average value of the interval.
     */
    fun avg(): T

    /**
     * Get the sum of the values of the interval.
     *
     * @return The sum of the values of the interval.
     */
    fun sum(): T

    /**
     * Get the product of the values of the interval.
     *
     * @return The product of the values of the interval.
     */
    fun product(): T

    /**
     * Get the amount of values of the interval.
     *
     * @return The amount of values of the interval.
     */
    fun count(): Int

    /**
     * Map the values of the interval to a new interval using the transform function.
     *
     * @param transform The transform function.
     * @return The new interval transformed using the transform function.
     */
    fun <K> map(transform: (T) -> K): List<K>

    /**
     * Map the values of the interval to a new interval using the transform function
     * according to their index.
     *
     * @param transform The transform function.
     * @return The new interval transformed using the transform function.
     */
    fun <K> mapIndexed(transform: (Int, T) -> K): List<K>

    /**
     * Perform an action on each value of the interval.
     *
     * @param action The action to perform.
     */
    fun forEach(action: (T) -> Unit)

    /**
     * Perform an action on each value of the interval according to their index.
     *
     * @param action The action to perform.
     */
    fun forEachIndexed(action: (Int, T) -> Unit)

    /**
     * Get the interval as an array of type [T].
     *
     * @return The interval as an array of type [T].
     */
    fun asArray(): Array<T>

    /**
     * Get the interval as a list of type [T].
     *
     * @return The interval as a list of type [T].
     */
    fun asList(): List<T>

    /**
     * Get the interval as a set of type [T].
     *
     * @return The interval as a set.
     */
    fun asSet(): Set<T>

    /**
     * Get the interval as a sequence of type [T].
     *
     * @return The interval as a sequence of type [T].
     */
    fun asSequence(): Sequence<T>

    /**
     * Get the interval as an iterable of type [T].
     *
     * @return The interval as an iterable of type [T].
     */
    fun asIterable(): Iterable<T>
}

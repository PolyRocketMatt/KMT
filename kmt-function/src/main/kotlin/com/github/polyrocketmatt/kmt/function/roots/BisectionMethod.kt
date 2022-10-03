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

package com.github.polyrocketmatt.kmt.function.roots

import com.github.polyrocketmatt.kmt.common.fastAbs
import com.github.polyrocketmatt.kmt.function.Function
import com.github.polyrocketmatt.kmt.interval.Interval
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

private const val INTERVAL_EPSILON = 1e-24
private const val ROOT_EPSILON = 1e-12

/**
 * Compute the root of a function using the bisection method on an interval.
 *
 * @param interval The interval to search for a root in.
 * @param steps The maximum number of steps to take.
 * @return The root of the function if one is found.
 * @throws IllegalArgumentException If the interval is invalid.
 * @throws IllegalStateException If the function does not have a root in the interval or if
 * the root isn't found within the given number of steps.
 */
@JvmName("bisectionDouble")
fun Function<Double>.bisection(interval: Interval<Double>, steps: Int = 10000): Double {
    val prod = get(interval.min()) * get(interval.max())
    if (get(interval.min()) * get(interval.max()) >= 0.0)
        throw IllegalArgumentException("The interval must evaluate to opposite signs at the endpoints")
    var a = interval.min()
    var b = interval.max()
    var c = a
    var i = 0
    while ((b - a) >= INTERVAL_EPSILON && i < steps) {
        c = (a + b) / 2.0
        if (get(c).fastAbs() <= ROOT_EPSILON)
            return c
        if (get(c) * get(a) < 0.0)
            b = c
        else
            a = c
        i++
    }

    throw IllegalStateException("Could not find a root of the function")
}

/**
 * Compute the root of a function using the bisection method on an interval.
 *
 * @param interval The interval to search for a root in.
 * @param steps The maximum number of steps to take.
 * @return The root of the function if one is found.
 * @throws IllegalArgumentException If the interval is invalid.
 * @throws IllegalStateException If the function does not have a root in the interval or if
 * the root isn't found within the given number of steps.
 */
@JvmName("bisectionFloat")
fun Function<Float>.bisection(interval: Interval<Float>, steps: Int = 10000): Double {
    if (get(interval.min().toDouble()) * get(interval.max().toDouble()) >= 0.0f)
        throw IllegalArgumentException("The interval must evaluate to opposite signs at the endpoints")
    var a = interval.min()
    var b = interval.max()
    var c = a
    var i = 0
    while ((b - a) >= INTERVAL_EPSILON && i < steps) {
        c = (a + b) / 2.0f
        if (get(c.toDouble()).fastAbs() <= ROOT_EPSILON)
            return c.toDouble()
        if (get(c.toDouble()) * get(a.toDouble()) < 0.0f)
            b = c
        else
            a = c
        i++
    }

    throw IllegalStateException("Could not find a root of the function")
}
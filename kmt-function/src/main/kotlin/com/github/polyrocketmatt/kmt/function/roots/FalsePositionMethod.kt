package com.github.polyrocketmatt.kmt.function.roots

import com.github.polyrocketmatt.kmt.common.fastAbs
import com.github.polyrocketmatt.kmt.function.Function
import com.github.polyrocketmatt.kmt.interval.Interval
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

private const val ROOT_EPSILON = 1e-12

/**
 * Compute the root of a function using the false-position method on an interval.
 *
 * @param interval The interval to search for a root in.
 * @param steps The maximum number of steps to take.
 * @return The root of the function if one is found.
 * @throws IllegalArgumentException If the interval is invalid.
 * @throws IllegalStateException If the function does not have a root in the interval or if
 * the root isn't found within the given number of steps.
 */
@JvmName("falsePositionDouble")
fun Function<Double>.falsePosition(interval: Interval<Double>, steps: Int = 10000): Double {
    if (get(interval.min()) * get(interval.max()) >= 0.0)
        throw IllegalArgumentException("The interval must evaluate to opposite signs at the endpoints")
    var a = interval.min()
    var b = interval.max()
    var c = a
    for (i in 0 until steps) {
        c = (a * get(b) - b * get(a)) / (get(b) - get(a))

        if (get(c).fastAbs() <= ROOT_EPSILON)
            return c
        else if (get(c) * get(a) < 0)
            b = c
        else
            a = c
    }

    throw IllegalStateException("Could not find a root of the function")
}

/**
 * Compute the root of a function using the false-position method on an interval.
 *
 * @param interval The interval to search for a root in.
 * @param steps The maximum number of steps to take.
 * @return The root of the function if one is found.
 * @throws IllegalArgumentException If the interval is invalid.
 * @throws IllegalStateException If the function does not have a root in the interval or if
 * the root isn't found within the given number of steps.
 */
@JvmName("falsePositionFloat")
fun Function<Float>.falsePosition(interval: Interval<Float>, steps: Int = 10000): Double {
    if (get(interval.min().toDouble()) * get(interval.max().toDouble()) >= 0.0)
        throw IllegalArgumentException("The interval must evaluate to opposite signs at the endpoints")
    var a = interval.min().toDouble()
    var b = interval.max().toDouble()
    var c = a
    for (i in 0 until steps) {
        c = (a * get(b) - b * get(a)) / (get(b) - get(a))

        if (get(c) == 0.0f)
            return c
        else if (get(c) * get(a) < 0)
            b = c
        else
            a = c
    }

    throw IllegalStateException("Could not find a root of the function")
}
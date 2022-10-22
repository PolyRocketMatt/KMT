package com.github.polyrocketmatt.kmt.function.roots

import com.github.polyrocketmatt.kmt.common.fastAbs
import com.github.polyrocketmatt.kmt.function.Function
import com.github.polyrocketmatt.kmt.function.differentiation.Differentiable
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

private const val ROOT_EPSILON = 1e-12

/**
 * Compute the root of a function using the Newton-Rhapson method on an initial guess.
 *
 * @param guess The initial guess for the root.
 * @param steps The maximum number of steps to take.
 * @return The root of the function if one is found.
 * @throws IllegalArgumentException If the interval is invalid.
 * @throws IllegalStateException If the function does not have a root in the interval or if
 * the root isn't found within the given number of steps.
 */
@JvmName("falsePositionDouble")
@Suppress("UNCHECKED_CAST")
fun Function<Double>.newtonRhapson(guess: Double, steps: Int = 10000): Double {
    if (this !is Differentiable<*>)
        throw IllegalStateException("Function must be exactly differentiable to use Newton-Rhapson method")
    val derivative = this.derivative() as Function<Double>
    var x = guess
    var h = get(x) / derivative[x]
    if (x == h)
        throw IllegalStateException("Division by zero is expected")

    var i = 0
    while (h.fastAbs() >= ROOT_EPSILON && i < steps) {
        h = get(x) / derivative[x]
        x -= h
        i++
    }

    println("Newton-Rhapson found root after $i steps")

    return x
}

/**
 * Compute the root of a function using the Newton-Rhapson method on an initial guess.
 *
 * @param guess The initial guess for the root.
 * @param steps The maximum number of steps to take.
 * @return The root of the function if one is found.
 * @throws IllegalArgumentException If the interval is invalid.
 * @throws IllegalStateException If the function does not have a root in the interval or if
 * the root isn't found within the given number of steps.
 */
@JvmName("falsePositionDouble")
@Suppress("UNCHECKED_CAST")
fun Function<Float>.newtonRhapson(guess: Float, steps: Int = 10000): Double {
    if (this !is Differentiable<*>)
        throw IllegalStateException("Function must be exactly differentiable to use Newton-Rhapson method")
    val derivative = this.derivative() as Function<Double>
    var x = guess.toDouble()
    var h = get(x) / derivative[x]
    var i = 0
    while (h.fastAbs() >= ROOT_EPSILON && i < steps) {
        h = get(x) / derivative[x]
        x -= h
        i++
    }

    return h
}

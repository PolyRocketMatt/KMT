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

package com.github.polyrocketmatt.kmt.function.variate

import com.github.polyrocketmatt.kmt.function.Function

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a multi-variate function (arity n) that is evaluable.
 *
 * @param T The type of the output of the function.
 */
abstract class Multivariate<T>(arity: Int) : Function<T>(arity) {

    override operator fun get(x: Double): T = throw UnsupportedOperationException("Multivariate functions do not support single argument")

    override fun get(x: Double, y: Double): T = throw UnsupportedOperationException("Multivariate functions do not support two arguments")

    override fun get(vararg x: Double): T = evaluate(*x)

    /**
     * Evaluates the function at the given inputs.
     *
     * @param x The inputs to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(vararg x: Double): T

    /**
     * Evaluates the function at the given inputs.
     *
     * @param x The inputs to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(vararg x: Float): T

    /**
     * Evaluates the function at the given inputs.
     *
     * @param x The inputs to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(vararg x: Int): T

    /**
     * Evaluates the function at the given inputs.
     *
     * @param x The inputs to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(vararg x: Short): T
}

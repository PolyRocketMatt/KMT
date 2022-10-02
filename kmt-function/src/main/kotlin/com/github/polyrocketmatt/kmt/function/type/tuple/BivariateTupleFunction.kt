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

package com.github.polyrocketmatt.kmt.function.type.tuple

import com.github.polyrocketmatt.kmt.common.storage.Tuple

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a bi-variate function (arity 2) that returns tuples.
 *
 * @param T The type of the output of the function.
 */
abstract class BivariateTupleFunction<T> : TupleFunction<T>(2) {

    override operator fun get(x: Double): Tuple<T> = throw UnsupportedOperationException("Bivariate functions do not support single argument")

    override fun get(x: Double, y: Double): Tuple<T> = evaluate(x, y)

    override fun get(vararg x: Double): Tuple<T> = throw UnsupportedOperationException("Univariate functions do not support multiple arguments")

    /**
     * Evaluates the function at the given inputs.
     *
     * @param x The first input to evaluate the function at.
     * @param y The second input to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(x: Double, y: Double): Tuple<T>

    /**
     * Evaluates the function at the given inputs.
     *
     * @param x The first input to evaluate the function at.
     * @param y The second input to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(x: Float, y: Float): Tuple<T>

    /**
     * Evaluates the function at the given inputs.
     *
     * @param x The first input to evaluate the function at.
     * @param y The second input to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(x: Int, y: Int): Tuple<T>

    /**
     * Evaluates the function at the given inputs.
     *
     * @param x The first input to evaluate the function at.
     * @param y The second input to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(x: Short, y: Short): Tuple<T>
}

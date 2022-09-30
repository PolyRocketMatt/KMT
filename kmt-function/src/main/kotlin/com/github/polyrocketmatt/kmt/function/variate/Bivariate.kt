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
 * Represents a bi-variate function (arity 2) that is evaluable.
 *
 * @param T The type of the output of the function.
 */
abstract class Bivariate<T> : Function<T>(2) {

    /**
     * Evaluates the function at the given inputs.
     *
     * @param x The first input to evaluate the function at.
     * @param y The second input to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(x: Double, y: Double): T

    /**
     * Evaluates the function at the given inputs.
     *
     * @param x The first input to evaluate the function at.
     * @param y The second input to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(x: Float, y: Float): T

    /**
     * Evaluates the function at the given inputs.
     *
     * @param x The first input to evaluate the function at.
     * @param y The second input to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(x: Int, y: Int): T

    /**
     * Evaluates the function at the given inputs.
     *
     * @param x The first input to evaluate the function at.
     * @param y The second input to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(x: Short, y: Short): T
}

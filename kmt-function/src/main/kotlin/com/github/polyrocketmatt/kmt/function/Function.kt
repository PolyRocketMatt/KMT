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

package com.github.polyrocketmatt.kmt.function

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a mathematical function.
 *
 * @param T The type of the output of the function.
 * @param arity The arity of the function.
 */
open class Function<T>(val arity: Int) {

    open operator fun get(x: Double): T = throw UnsupportedOperationException("Function is not defined for $x")
    open operator fun get(x: Double, y: Double): T = throw UnsupportedOperationException("Function is not defined for $x,$y")
    open operator fun get(vararg x: Double): T = throw UnsupportedOperationException("Function is not defined for ${x.size} arguments")

    internal open fun accurate(): Function<Double> = throw UnsupportedOperationException("Accurate function is not defined")

}

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

package com.github.polyrocketmatt.kmt.function.type.composite

import com.github.polyrocketmatt.kmt.function.Function
import kotlin.math.max

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a composite function of two functions (f o g).
 *
 * @param f The first function.
 * @param g The second function.
 */
class CompositeFunction(private val f: Function<Double>, private val g: Function<Double>) : Function<Double>(max(f.arity, g.arity)) {

    override fun get(x: Double): Double = f[g[x]]

    override fun get(x: Double, y: Double): Double = f[g[x, y]]

    override fun get(vararg x: Double): Double = f[g.get(*x)]
}

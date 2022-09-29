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
import com.github.polyrocketmatt.kmt.function.differentiable.Differentiable
import com.github.polyrocketmatt.kmt.function.differentiable.Differentiator
import com.github.polyrocketmatt.kmt.function.integration.Integratable
import com.github.polyrocketmatt.kmt.function.integration.Integrator
import com.github.polyrocketmatt.kmt.range.Range

abstract class Univariate<T> : Function<T>(1), Integratable<T>, Differentiable<T> {

    abstract fun evaluate(x: Double): T
    abstract fun evaluate(x: Float): T
    abstract fun evaluate(x: Int): T
    abstract fun evaluate(x: Short): T

    override fun integrate(range: Range<Double>, integrator: Integrator<T>): Array<Double> = integrator.integrate(this, range)

    override fun differentiate(range: Range<Double>, differentiator: Differentiator<T>): Array<Double> = differentiator.differentiate(this, range)

}
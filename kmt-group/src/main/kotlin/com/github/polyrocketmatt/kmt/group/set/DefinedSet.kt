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

package com.github.polyrocketmatt.kmt.group.set

import com.github.polyrocketmatt.kmt.complex.Complex

/**
 * @author Matthias Kovacic
 * @since 0.1.0
 *
 * Represents a set where the provided function is used to determine
 * if an element is contained in the set.
 *
 * @param T The type of the elements in the set.
 * @param isMember The function that determines if an element is contained in the set.
 */
class DefinedSet<T>(
    private val isMember: (T) -> Boolean,
    private val isEmpty: Boolean,
    private val isSingleton: Boolean,
    private val isInfinite: Boolean
) : SimpleSet<T>(isInfinite = isInfinite) {

    constructor(isMember: (T) -> Boolean) : this(isMember, false, false, true)

    companion object {
        val NATURAL = DefinedSet<Int> { it >= 0 }
        val INTEGERS = DefinedSet<Int> { true }
        val REAL_DOUBLES = DefinedSet<Double> { true }
        val REAL_FLOATS = DefinedSet<Float> { true }
        val COMPLEX = DefinedSet<Complex> { true }
    }

    override fun isEmpty(): Boolean = isEmpty

    override fun isSingleton(): Boolean = isSingleton

    override fun isInfinite(): Boolean = isInfinite

    override fun card(): Int = if (isEmpty) 0 else if (isSingleton) 1 else throw UnsupportedOperationException("Cannot decide if a set is empty since elements are not statically defined")

    override fun contains(element: T): Boolean = isMember(element)

    override fun isSubset(set: SimpleSet<T>): Boolean = set.all { isMember(it) }

    override fun <K> map(map: (T) -> K): SimpleSet<K> = throw UnsupportedOperationException("Cannot map a set since elements are not statically defined")

    fun <K> mapIfContains(element: T, map: (T) -> K): K? = if (contains(element)) map(element) else null
}

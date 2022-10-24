package com.github.polyrocketmatt.group

import com.github.polyrocketmatt.kmt.complex.Complex

/**
 * @author Matthias Kovacic
 * @since 0.1.0
 *
 * Represents a set where the provided function is used to determine
 * if an element is contained in the set.
 *
 * @param T The type of the elements in the set.
 * @param contains The function that determines if an element is contained in the set.
 */
class DefinedSet<T>(private val contains: (T) -> Boolean) : SimpleSet<T>() {

    companion object {
        val NATURAL = DefinedSet<Int> { it >= 0 }
        val INTEGERS = DefinedSet<Int> { true }
        val REAL_DOUBLES = DefinedSet<Double> { true }
        val REAL_FLOATS = DefinedSet<Float> { true }
        val COMPLEX = DefinedSet<Complex> { true }
    }

    override fun isEmpty(): Boolean = throw UnsupportedOperationException("Cannot decide if a set is empty since elements are not statically defined")

    override fun isSingleton(): Boolean = throw UnsupportedOperationException("Cannot decide if a set is empty since elements are not statically defined")

    override fun contains(element: T): Boolean = contains(element)

    override fun isSubSet(set: SimpleSet<T>): Boolean = set.elements.all { contains(it) }

}

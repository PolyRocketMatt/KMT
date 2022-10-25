package com.github.polyrocketmatt.group.set

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
    private val isSingleton: Boolean
) : SimpleSet<T>() {

    constructor(isMember: (T) -> Boolean) : this(isMember, false, false)

    companion object {
        val NATURAL = DefinedSet<Int> { it >= 0 }
        val INTEGERS = DefinedSet<Int> { true }
        val REAL_DOUBLES = DefinedSet<Double> { true }
        val REAL_FLOATS = DefinedSet<Float> { true }
        val COMPLEX = DefinedSet<Complex> { true }
    }

    override fun isEmpty(): Boolean = isEmpty

    override fun isSingleton(): Boolean = isSingleton

    override fun card(): Int = throw UnsupportedOperationException("Cannot decide if a set is empty since elements are not statically defined")

    override fun contains(element: T): Boolean = isMember(element)

    override fun isSubSet(set: SimpleSet<T>): Boolean = set.all { isMember(it) }

    override fun <K> map(map: (T) -> K): SimpleSet<K> = throw UnsupportedOperationException("Cannot map a set since elements are not statically defined")

    fun <K> mapIfContains(element: T, map: (T) -> K): K? = if (contains(element)) map(element) else null
}

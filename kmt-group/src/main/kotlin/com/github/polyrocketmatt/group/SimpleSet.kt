package com.github.polyrocketmatt.group

import com.github.polyrocketmatt.kmt.common.annotation.Ref

/**
 * @author Matthias Kovacic
 * @since 0.1.0
 *
 * Represents a (mathematical) set.
 *
 * @param <T> The type of the set.
 * @param elements The objects contained within to the set.
 */
@Ref("https://en.wikipedia.org/wiki/Set_(mathematics)")
open class SimpleSet<T>(val elements: Set<T>) {

    constructor() : this(emptySet())
    constructor(vararg elements: T) : this(elements.toSet())
    constructor(elements: Collection<T>) : this(elements.toSet())

    /**
     * Check if the set is empty.
     *
     * @return True if the set is empty, false otherwise.
     */
    open fun isEmpty(): Boolean = elements.isEmpty()

    /**
     * Check if the set is a singleton.
     *
     * @return True if the set contains one element, false otherwise.
     */
    open fun isSingleton(): Boolean = elements.size == 1

    /**
     * Check if an element is a member of the set.
     *
     * @param element The element to check.
     * @return True if the element is a member of the set, false otherwise.
     */
    open fun contains(element: T): Boolean = element in elements

    /**
     * Check if the set is a subset of another set.
     *
     * @param set The set to check against.
     * @return True if the set is a subset of the other set, false otherwise.
     */
    open fun isSubSet(set: SimpleSet<T>): Boolean = elements.containsAll(set.elements)

}
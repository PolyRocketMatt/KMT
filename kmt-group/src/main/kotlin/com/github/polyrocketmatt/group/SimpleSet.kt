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
class SimpleSet<T>(private val elements: Set<T>) {

    /**
     * Check if the set is empty.
     *
     * @return True if the set is empty, false otherwise.
     */
    fun isEmpty(): Boolean = elements.isEmpty()

    /**
     * Check if the set is a singleton.
     *
     * @return True if the set contains one element, false otherwise.
     */
    fun isSingleton(): Boolean = elements.size == 1

    /**
     * Check if an element is a member of the set.
     *
     * @param element The element to check.
     * @return True if the element is a member of the set, false otherwise.
     */
    fun contains(element: T): Boolean = element in elements

    /**
     * Check if the set is a subset of another set.
     *
     * @param set The set to check against.
     * @return True if the set is a subset of the other set, false otherwise.
     */
    fun isSubSet(set: SimpleSet<T>): Boolean = elements.containsAll(set.elements)

}
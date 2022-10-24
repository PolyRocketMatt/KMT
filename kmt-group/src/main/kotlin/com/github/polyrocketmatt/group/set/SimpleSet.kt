package com.github.polyrocketmatt.group.set

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
open class SimpleSet<T>(private val elements: Set<T>) :
    Iterable<T>,
    Mapping<T> {

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
     * Get the cardinality (size) of the set.
     *
     * @return The cardinality of the set.
     */
    open fun card(): Int = elements.size

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

    /**
     * Compute the union of this set and another set.
     *
     * @param set The set to compute the union with.
     * @return The union of this set and the other set.
     */
    open fun union(set: SimpleSet<T>): SimpleSet<T> = SimpleSet(elements + set.elements)

    /**
     * Compute the intersection of this set and another set.
     *
     * @param set The set to compute the intersection with.
     * @return The intersection of this set and the other set.
     */
    open fun intersection(set: SimpleSet<T>): SimpleSet<T> = SimpleSet(elements.intersect(set.elements))

    /**
     * Compute the difference of this set and another set.
     *
     * @param set The set to compute the difference with.
     * @return The difference of this set and the other set.
     */
    open fun difference(set: SimpleSet<T>): SimpleSet<T> = SimpleSet(elements - set.elements)

    /**
     * Compute the symmetric difference of this set and another set.
     *
     * @param set The set to compute the symmetric difference with.
     * @return The symmetric difference of this set and the other set.
     */
    open fun symmetricDifference(set: SimpleSet<T>): SimpleSet<T> = union(set).difference(intersection(set))

    /**
     * Compute the cartesian product of this set and another set.
     *
     * @param set The set to compute the cartesian product with.
     * @return The cartesian product of this set and the other set.
     */
    open fun <K> cartesianProduct(set: SimpleSet<K>): SimpleSet<Pair<T, K>> {
        val result = mutableSetOf<Pair<T, K>>()
        for (element in elements)
            for (other in set.elements)
                result.add(element to other)
        return SimpleSet(result)
    }

    /**
     * Compute the complement of this set given a universe set.
     *
     * @param universe The universe set to compute the complement with.
     * @return The complement of this set.
     */
    open fun complement(universe: SimpleSet<T>): SimpleSet<T> = universe.difference(this)

    override fun iterator(): Iterator<T> = elements.iterator()

    open override fun <K> map(map: (T) -> K): SimpleSet<K> = SimpleSet(elements.map(map))

}
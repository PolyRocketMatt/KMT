package com.github.polyrocketmatt.kmt.group.group

import com.github.polyrocketmatt.kmt.common.utils.complies
import com.github.polyrocketmatt.kmt.group.set.SimpleSet

/**
 * @author Matthias Kovacic
 * @since 0.1.0
 *
 * Represents an algebra on a set of elements.
 *
 * @param T The type of the elements in the algebra.
 * @param elements The elements of the algebra.
 */
abstract class Algebra<T>(elements: Set<T>) : SimpleSet<T>(elements) {

    constructor() : this(emptySet())
    constructor(vararg elements: T) : this(elements.toSet())
    constructor(elements: Collection<T>) : this(elements.toSet())

    abstract fun checkIntegrity()

}

/**
 * @author Matthias Kovacic
 * @since 0.1.0
 *
 * Represents a magma for a set of elements. A magma has the following properties:
 * - Closure
 *
 * @param T The type of the elements in the magma.
 * @param operation The binary operation of the magma.
 * @param elements The elements of the magma.
 */
open class Magma<T>(
    private val operation: (a: T, b: T) -> T,
    elements: Set<T>
) : Algebra<T>(elements) {

    constructor(operation: (a: T, b: T) -> T) : this(operation, emptySet())
    constructor(operation: (a: T, b: T) -> T, vararg elements: T) : this(operation, elements.toSet())
    constructor(operation: (a: T, b: T) -> T, elements: Collection<T>) : this(operation, elements.toSet())

    override fun checkIntegrity() {
        //  Check if the operation is closed
        for (a in elements) for (b in elements)
            complies("The binary operation is not closed for all elements in the Magma") { operation(a, b) in elements }
    }

    /**
     * Get the result of the operation on two elements.
     *
     * @param a The first element.
     * @param b The second element.
     * @return The result of the operation on the two elements.
     * @throws IllegalArgumentException If any of the two elements is not a member of the group.
     */
    open operator fun get(a: T, b: T): T {
        complies("The first element to retrieve the inverse for is not a member of the set") { contains(a) }
        complies("The second element to retrieve the inverse for is not a member of the set") { contains(b) }

        return operation(a, b)
    }

}

/**
 * @author Matthias Kovacic
 * @since 0.1.0
 *
 * Represents a semigroup for a set of elements. A semigroup has the following properties:
 * - Associativity
 * - Closure
 *
 * @param T The type of the elements in the semigroup.
 * @param operation The binary operation of the semigroup.
 * @param elements The elements of the semigroup.
 */
open class Semigroup<T>(
    private val operation: (a: T, b: T) -> T,
    elements: Set<T>
) : Magma<T>(operation, elements) {

    constructor(operation: (a: T, b: T) -> T) : this(operation, emptySet())
    constructor(operation: (a: T, b: T) -> T, vararg elements: T) : this(operation, elements.toSet())
    constructor(operation: (a: T, b: T) -> T, elements: Collection<T>) : this(operation, elements.toSet())

    override fun checkIntegrity() {
        //  Check if the operation is closed
        for (a in elements) for (b in elements)
            complies("The binary operation is not closed for all elements in the Semigroup") { operation(a, b) in elements }

        //  Check associative property
        for (a in elements) for (b in elements) for (c in elements)
            complies("The binary operation is not associative for all elements in the Semigroup") { isAssociative(a, b, c, operation) }
    }

    /**
     * Get the result of the operation on two elements.
     *
     * @param a The first element.
     * @param b The second element.
     * @return The result of the operation on the two elements.
     * @throws IllegalArgumentException If any of the two elements is not a member of the group.
     */
    override operator fun get(a: T, b: T): T {
        complies("The first element to retrieve the inverse for is not a member of the set") { contains(a) }
        complies("The second element to retrieve the inverse for is not a member of the set") { contains(b) }

        return operation(a, b)
    }

}

/**
 * @author Matthias Kovacic
 * @since 0.1.0
 *
 * Represents a monoid for a set of elements. A monoid has the following properties:
 * - Associativity
 * - Closure
 * - Identity
 *
 * @param T The type of the elements in the monoid.
 * @param identity The identity element of the monoid.
 * @param operation The binary operation of the monoid.
 * @param elements The elements of the monoid.
 */
open class Monoid<T>(
    private val identity: T,
    private val operation: (a: T, b: T) -> T,
    elements: Set<T>
) : Semigroup<T>(operation, elements) {

    constructor(identity: T, operation: (a: T, b: T) -> T) : this(identity, operation, emptySet())
    constructor(identity: T, operation: (a: T, b: T) -> T, vararg elements: T) : this(identity, operation, elements.toSet())
    constructor(identity: T, operation: (a: T, b: T) -> T, elements: Collection<T>) : this(identity, operation, elements.toSet())

    override fun checkIntegrity() {
        //  Check if the operation is closed
        for (a in elements) for (b in elements)
            complies("The binary operation is not closed for all elements in the Monoid") { operation(a, b) in elements }

        //  Check associative property
        for (a in elements) for (b in elements) for (c in elements)
            complies("The binary operation is not associative for all elements in the Monoid") { isAssociative(a, b, c, operation) }

        //  Check identity
        for (a in elements)
            complies("The identity element is not an identity for all elements in the Monoid") { isIdentity(a, identity, operation) }
    }

    /**
     * Get the result of the operation on two elements.
     *
     * @param a The first element.
     * @param b The second element.
     * @return The result of the operation on the two elements.
     * @throws IllegalArgumentException If any of the two elements is not a member of the group.
     */
    override operator fun get(a: T, b: T): T {
        complies("The first element to retrieve the inverse for is not a member of the set") { contains(a) }
        complies("The second element to retrieve the inverse for is not a member of the set") { contains(b) }

        return operation(a, b)
    }

    open fun identity(): T = identity

}

/**
 * @author Matthias Kovacic
 * @since 0.1.0
 *
 * Represents a group for a set of elements. A group has the following properties:
 * - Associativity
 * - Closure
 * - Identity
 * - Inverse
 *
 * @param T The type of the elements in the group.
 * @param identity The identity element of the group.
 * @param inverseMap The inverse operation of the group.
 * @param operation The binary operation of the group.
 * @param elements The elements of the group.
 */
class Group<T>(
    private val identity: T,
    private val inverseMap: (a: T) -> T,
    private val operation: (a: T, b: T) -> T,
    elements: Set<T>
) : Monoid<T>(identity, operation, elements) {

    constructor(identity: T, inverseMap: (a: T) -> T, operation: (a: T, b: T) -> T) : this(identity, inverseMap, operation, emptySet())
    constructor(identity: T, inverseMap: (a: T) -> T, operation: (a: T, b: T) -> T, vararg elements: T) : this(identity, inverseMap, operation, elements.toSet())
    constructor(identity: T, inverseMap: (a: T) -> T, operation: (a: T, b: T) -> T, elements: Collection<T>) : this(identity, inverseMap, operation, elements.toSet())

    override fun checkIntegrity() {
        //  Check if the operation is closed
        for (a in elements) for (b in elements)
            complies("The binary operation is not closed for all elements in the Group") { operation(a, b) in elements }

        //  Check associative property
        for (a in elements) for (b in elements) for (c in elements)
            complies("The binary operation is not associative for all elements in the Group") { isAssociative(a, b, c, operation) }

        //  Check identity
        for (a in elements)
            complies("The identity element is not an identity for all elements in the Group") { isIdentity(a, identity, operation) }

        //  Check identity
        for (a in elements)
            complies("The inverse is not guaranteed for all elements in the Group") { isInverse(a, inverse(a), operation) }
    }

    /**
     * Get the result of the operation on two elements.
     *
     * @param a The first element.
     * @param b The second element.
     * @return The result of the operation on the two elements.
     * @throws IllegalArgumentException If any of the two elements is not a member of the group.
     */
    override operator fun get(a: T, b: T): T {
        complies("The first element to retrieve the inverse for is not a member of the set") { contains(a) }
        complies("The second element to retrieve the inverse for is not a member of the set") { contains(b) }

        return operation(a, b)
    }

    /**
     * Get the identity element of the group.
     *
     * @return The identity element of the group.
     */
    override fun identity(): T = identity

    /**
     * Get the inverse of an element.
     *
     * @param element The element to get the inverse of.
     * @return The inverse of the element.
     * @throws IllegalArgumentException If the element is not a member of the group.
     */
    open fun inverse(element: T): T {
        complies("The element to retrieve the inverse for is not a member of the set") { contains(element) }
        return inverseMap(element)
    }

}


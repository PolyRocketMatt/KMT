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
class Magma<T>(
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
class Semigroup<T>(
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

        //  Check associative property
        for (a in elements) for (b in elements) for (c in elements)
            complies("The binary operation is not associative for all elements in the Monoid") { isAssociative(a, b, c, operation) }
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
class Monoid<T>(
    private val identity: T,
    private val operation: (a: T, b: T) -> T,
    elements: Set<T>
) : Algebra<T>(elements) {

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
    }

    operator fun get(a: T, b: T): T = operation(a, b)

    fun identity(): T = identity

}


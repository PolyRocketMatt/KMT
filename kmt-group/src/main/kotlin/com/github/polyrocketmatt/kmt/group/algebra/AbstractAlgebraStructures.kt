package com.github.polyrocketmatt.kmt.group.algebra

import com.github.polyrocketmatt.kmt.common.utils.complies
import com.github.polyrocketmatt.kmt.group.set.SimpleSet

/**
 * @author Matthias Kovacic
 * @since 0.1.0
 *
 * Represents an algebra on a set of elements.
 *
 * @param T The type of the elements in the algebra.
 * @param set The set of the algebra.
 */
abstract class Algebra<T>(set: SimpleSet<T>) : SimpleSet<T>(set.elements, set.isInfinite()) {

    abstract fun checkIntegrity()

}

/**
 * @author Matthias Kovacic
 * @since 0.1.0
 *
 * Represents a magma for a set of elements. A magma has the following properties:
 * - Closure
 *
 * The binary operation is closed on the set of elements.
 *
 * @param T The type of the elements in the magma.
 * @param operation The binary operation of the magma.
 * @param set The set of the magma.
 */
open class Magma<T>(
    private val operation: (a: T, b: T) -> T,
    set: SimpleSet<T>
) : Algebra<T>(set) {

    constructor(operation: (a: T, b: T) -> T) : this(operation, emptySet())
    constructor(operation: (a: T, b: T) -> T, vararg elements: T) : this(operation, elements.toSet())
    constructor(operation: (a: T, b: T) -> T, elements: Collection<T>) : this(operation, elements.toSet())
    constructor(operation: (a: T, b: T) -> T, set: Set<T>) : this(operation, SimpleSet(set))

    override fun checkIntegrity() {
        complies("Cannot check the integrity of a set with infinitely many elements") { !isInfinite() }

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
 * The binary operation is closed on the set of elements.
 *
 * @param T The type of the elements in the semigroup.
 * @param operation The binary operation of the semigroup.
 * @param set The set of the semigroup.
 */
open class Semigroup<T>(
    private val operation: (a: T, b: T) -> T,
    set: SimpleSet<T>
) : Magma<T>(operation, set) {

    constructor(operation: (a: T, b: T) -> T) : this(operation, emptySet())
    constructor(operation: (a: T, b: T) -> T, vararg elements: T) : this(operation, elements.toSet())
    constructor(operation: (a: T, b: T) -> T, elements: Collection<T>) : this(operation, elements.toSet())
    constructor(operation: (a: T, b: T) -> T, set: Set<T>) : this(operation, SimpleSet(set))

    override fun checkIntegrity() {
        complies("Cannot check the integrity of a set with infinitely many elements") { !isInfinite() }

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
 * The binary operation is closed on the set of elements.
 *
 * @param T The type of the elements in the monoid.
 * @param identity The identity element of the monoid.
 * @param operation The binary operation of the monoid.
 * @param set The set of the monoid.
 */
open class Monoid<T>(
    private val identity: T,
    private val operation: (a: T, b: T) -> T,
    set: SimpleSet<T>
) : Semigroup<T>(operation, set) {

    constructor(identity: T, operation: (a: T, b: T) -> T) : this(identity, operation, emptySet())
    constructor(identity: T, operation: (a: T, b: T) -> T, vararg elements: T) : this(identity, operation, elements.toSet())
    constructor(identity: T, operation: (a: T, b: T) -> T, elements: Collection<T>) : this(identity, operation, elements.toSet())
    constructor(identity: T, operation: (a: T, b: T) -> T, set: Set<T>) : this(identity, operation, SimpleSet(set))

    override fun checkIntegrity() {
        complies("Cannot check the integrity of a set with infinitely many elements") { !isInfinite() }

        //  Check identity in the set
        complies("Identity is not a member of the Monoid") { elements.contains(identity) }

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
 * The binary operation is closed on the set of elements.
 *
 * @param T The type of the elements in the group.
 * @param identity The identity element of the group.
 * @param inverseMap The inverse operation of the group.
 * @param operation The binary operation of the group.
 * @param set The set of the group.
 */
open class Group<T>(
    private val identity: T,
    private val inverseMap: (a: T) -> T,
    private val operation: (a: T, b: T) -> T,
    set: SimpleSet<T>
) : Monoid<T>(identity, operation, set) {

    constructor(identity: T, inverseMap: (a: T) -> T, operation: (a: T, b: T) -> T) : this(identity, inverseMap, operation, emptySet())
    constructor(identity: T, inverseMap: (a: T) -> T, operation: (a: T, b: T) -> T, vararg elements: T) : this(identity, inverseMap, operation, elements.toSet())
    constructor(identity: T, inverseMap: (a: T) -> T, operation: (a: T, b: T) -> T, elements: Collection<T>) : this(identity, inverseMap, operation, elements.toSet())
    constructor(identity: T, inverseMap: (a: T) -> T, operation: (a: T, b: T) -> T, set: Set<T>) : this(identity, inverseMap, operation, SimpleSet(set))

    override fun checkIntegrity() {
        println(isInfinite())
        complies("Cannot check the integrity of a set with infinitely many elements") { !isInfinite() }

        //  Check identity in the set
        complies("Identity is not a member of the Group") { elements.contains(identity) }

        //  Check if the operation is closed
        for (a in elements) for (b in elements)
            complies("The binary operation is not closed for all elements in the Group") { operation(a, b) in elements }

        //  Check associative property
        for (a in elements) for (b in elements) for (c in elements)
            complies("The binary operation is not associative for all elements in the Group") { isAssociative(a, b, c, operation) }

        //  Check identity & Inverse
        for (a in elements) {
            complies("The identity element is not an identity for all elements in the Abelian Group") { isIdentity(a, identity, operation) }
            complies("The inverse is not guaranteed for all elements in the Abelian Group") { isInverse(a, inverse(a), identity, operation) }
        }
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
    fun inverse(element: T): T {
        complies("The element to retrieve the inverse for is not a member of the set") { contains(element) }
        return inverseMap(element)
    }
}

/**
 * @author Matthias Kovacic
 * @since 0.1.0
 *
 * Represents a group for a set of elements. A group has the following properties:
 * - Associativity
 * - Commutativity
 * - Closure
 * - Identity
 * - Inverse
 *
 * The binary operation is closed on the set of elements.
 *
 * @param T The type of the elements in the group.
 * @param identity The identity element of the group.
 * @param inverseMap The inverse operation of the group.
 * @param operation The binary operation of the group.
 * @param set The set of the group.
 */
open class AbelianGroup<T>(
    private val identity: T,
    private val inverseMap: (a: T) -> T,
    private val operation: (a: T, b: T) -> T,
    set: SimpleSet<T>
) : Monoid<T>(identity, operation, set) {

    constructor(identity: T, inverseMap: (a: T) -> T, operation: (a: T, b: T) -> T) : this(identity, inverseMap, operation, emptySet())
    constructor(identity: T, inverseMap: (a: T) -> T, operation: (a: T, b: T) -> T, vararg elements: T) : this(identity, inverseMap, operation, elements.toSet())
    constructor(identity: T, inverseMap: (a: T) -> T, operation: (a: T, b: T) -> T, elements: Collection<T>) : this(identity, inverseMap, operation, elements.toSet())
    constructor(identity: T, inverseMap: (a: T) -> T, operation: (a: T, b: T) -> T, set: Set<T>) : this(identity, inverseMap, operation, SimpleSet(set))

    override fun checkIntegrity() {
        complies("Cannot check the integrity of a set with infinitely many elements") { !isInfinite() }

        //  Check identity in the set
        complies("Identity is not a member of the Abelian Group") { elements.contains(identity) }

        //  Check if the operation is closed & commutative property
        for (a in elements) for (b in elements) {
            complies("The binary operation is not closed for all elements in the Abelian Group") { operation(a, b) in elements }
            complies("The binary operation is not commutative for all elements in the Abelian Group") { isCommutative(a, b, operation) }
        }

        //  Check associative property
        for (a in elements) for (b in elements) for (c in elements)
            complies("The binary operation is not associative for all elements in the Abelian Group") { isAssociative(a, b, c, operation) }

        //  Check identity & Inverse
        for (a in elements) {
            complies("The identity element is not an identity for all elements in the Abelian Group") { isIdentity(a, identity, operation) }
            complies("The inverse is not guaranteed for all elements in the Abelian Group") { isInverse(a, inverse(a), identity, operation) }
        }
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

/**
 * @author Matthias Kovacic
 * @since 0.1.0
 *
 * Represents a ring for a set of elements. A ring has the following properties:
 * - Associativity
 * - Commutativity
 * - Closure
 * - Identity
 * - Inverse
 *
 * Both binary operations are closed on the set of elements.
 *
 * @param T The type of the elements in the ring.
 * @param identity The identity element of the ring.
 * @param inverseMap The inverse operation of the ring.
 * @param primary The primary binary operation of the ring.
 * @param secondary The secondary binary of the ring.
 * @param set The set of the ring.
 */
class Ring<T>(
    private val identity: T,
    private val inverseMap: (a: T) -> T,
    private val primary: (a: T, b: T) -> T,
    private val secondary: (a: T, b: T) -> T,
    set: SimpleSet<T>
) : AbelianGroup<T>(identity, inverseMap, primary, set) {

    constructor(identity: T, inverseMap: (a: T) -> T, primary: (a: T, b: T) -> T, secondary: (a: T, b: T) -> T)
            : this(identity, inverseMap, primary, secondary, emptySet())
    constructor(identity: T, inverseMap: (a: T) -> T, primary: (a: T, b: T) -> T, secondary: (a: T, b: T) -> T, vararg elements: T)
            : this(identity, inverseMap, primary, secondary, elements.toSet())
    constructor(identity: T, inverseMap: (a: T) -> T, primary: (a: T, b: T) -> T, secondary: (a: T, b: T) -> T, elements: Collection<T>)
            : this(identity, inverseMap, primary, secondary, elements.toSet())
    constructor(identity: T, inverseMap: (a: T) -> T, primary: (a: T, b: T) -> T, secondary: (a: T, b: T) -> T, set: Set<T>)
            : this(identity, inverseMap, primary, secondary, SimpleSet(set))

    override fun checkIntegrity() {
        complies("Cannot check the integrity of a set with infinitely many elements") { !isInfinite() }

        //  Check identity in the set
        complies("Identity is not a member of the Ring") { elements.contains(identity) }

        //  Abelian group under addition
        super.checkIntegrity()

        //  Check if multiplication is closed
        for (a in elements) for (b in elements) {
            complies("Multiplication is not closed for all elements in the Ring") { secondary(a, b) in elements }

            //  Check distributivity & associativity
            for (c in elements) {
                complies("Multiplication is not associative for all elements in the Ring") { isAssociative(a, b, c, secondary) }
                complies("Addition is not distributive over multiplication for all elements in the Ring") { isLeftDistributive(a, b, c, primary, secondary) }
                complies("Addition is not distributive over multiplication for all elements in the Ring") { isRightDistributive(a, b, c, primary, secondary) }
            }
        }
    }

    /**
     * Get the result of the primary binary operation on two elements.
     *
     * @param a The first element.
     * @param b The second element.
     * @return The result of the primary binary operation on the two elements.
     * @throws IllegalArgumentException If any of the two elements is not a member of the group.
     */
    fun primary(a: T, b: T): T {
        complies("The first element to retrieve the inverse for is not a member of the set") { contains(a) }
        complies("The second element to retrieve the inverse for is not a member of the set") { contains(b) }

        return primary(a, b)
    }

    /**
     * Get the result of the secondary binary operation on two elements.
     *
     * @param a The first element.
     * @param b The second element.
     * @return The result of the secondary binary operation on the two elements.
     * @throws IllegalArgumentException If any of the two elements is not a member of the group.
     */
    fun secondary(a: T, b: T): T {
        complies("The first element to retrieve the inverse for is not a member of the set") { contains(a) }
        complies("The second element to retrieve the inverse for is not a member of the set") { contains(b) }

        return secondary(a, b)
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
    override fun inverse(element: T): T {
        complies("The element to retrieve the inverse for is not a member of the set") { contains(element) }

        return inverseMap(element)
    }
}

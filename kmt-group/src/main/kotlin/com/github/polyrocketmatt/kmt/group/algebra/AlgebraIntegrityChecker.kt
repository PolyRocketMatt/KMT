package com.github.polyrocketmatt.kmt.group.algebra

/**
 * Check if a given operation is commutative.
 *
 * @param T the type of the elements the operation is defined on.
 * @param a the first element.
 * @param b the second element.
 * @param op the operation.
 * @return true if the operation is commutative, false otherwise.
 */
fun <T> isCommutative(a: T, b: T, op: (T, T) -> T): Boolean = op(a, b) == op(b, a)

/**
 * Check if a given operation is associative.
 *
 * @param T the type of the elements the operation is defined on.
 * @param a the first element.
 * @param b the second element.
 * @param c the third element.
 * @param op the operation.
 * @return true if the operation is associative, false otherwise.
 */
fun <T> isAssociative(a: T, b: T, c: T, op: (T, T) -> T): Boolean = op(op(a, b), c) == op(a, op(b, c))

/**
 * Check if a given element is the identity element of a given operation.
 *
 * @param T the type of the elements the operation is defined on.
 * @param a the element.
 * @param id the identity.
 * @param op the operation.
 * @return true if the element is the identity, false otherwise.
 */
fun <T> isIdentity(a: T, id: T, op: (T, T) -> T): Boolean = op(a, id) == a && op(id, a) == a

/**
 * Check if a given element is the inverse of another given element of a given operation.
 *
 * @param T the type of the elements the operation is defined on.
 * @param a the element.
 * @param b the inverse.
 * @param id the identity.
 * @param op the operation.
 * @return true if the element is the inverse, false otherwise.
 */
fun <T> isInverse(a: T, b: T, id: T, op: (T, T) -> T): Boolean = op(a, b) == id
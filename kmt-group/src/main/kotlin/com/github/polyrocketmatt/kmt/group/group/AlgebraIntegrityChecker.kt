package com.github.polyrocketmatt.kmt.group.group

/**
 * Check if a given operation is commutative.
 *
 * @param T the type of the elements the operation is defined on.
 * @param a the first element.
 * @param b the second element.
 * @param op the operation.
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
 */
fun <T> isAssociative(a: T, b: T, c: T, op: (T, T) -> T): Boolean = op(op(a, b), c) == op(a, op(b, c))
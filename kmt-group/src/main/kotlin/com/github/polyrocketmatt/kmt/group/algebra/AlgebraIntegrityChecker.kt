/*
 * KMT, Kotlin Math Toolkit
 * Copyright (C) Matthias Kovacic <matthias.kovacic@student.kuleuven.be>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.polyrocketmatt.kmt.group.algebra

/**
 * Check if a given operation is commutative.
 *
 * @param T The type of the elements the operation is defined on.
 * @param a The first element.
 * @param b The second element.
 * @param op The operation.
 * @return True if the operation is commutative, false otherwise.
 */
fun <T> isCommutative(a: T, b: T, op: (T, T) -> T): Boolean = op(a, b) == op(b, a)

/**
 * Check if a given operation is associative.
 *
 * @param T The type of the elements the operation is defined on.
 * @param a The first element.
 * @param b The second element.
 * @param c The third element.
 * @param op The operation.
 * @return True if the operation is associative, false otherwise.
 */
fun <T> isAssociative(a: T, b: T, c: T, op: (T, T) -> T): Boolean = op(op(a, b), c) == op(a, op(b, c))

/**
 * Check if a given element is the identity element of a given operation.
 *
 * @param T The type of the elements the operation is defined on.
 * @param a The element.
 * @param id The identity.
 * @param op The operation.
 * @return True if the element is the identity, false otherwise.
 */
fun <T> isIdentity(a: T, id: T, op: (T, T) -> T): Boolean = op(a, id) == a && op(id, a) == a

/**
 * Check if a given element is the inverse of another given element of a given operation.
 *
 * @param T The type of the elements the operation is defined on.
 * @param a The element.
 * @param b The inverse.
 * @param id The identity.
 * @param op The operation.
 * @return True if the element is the inverse, false otherwise.
 */
fun <T> isInverse(a: T, b: T, id: T, op: (T, T) -> T): Boolean = op(a, b) == id

/**
 * Check if the given elements satisfy left distributivity of the given operations.
 *
 * @param T The type of the elements the operation is defined on.
 * @param a The first element.
 * @param b The second element.
 * @param c The third element.
 * @param inner The inner operation.
 * @param outer The outer operation.
 * @return True if the elements satisfy left distributivity, false otherwise.
 */
fun <T> isLeftDistributive(a: T, b: T, c: T, inner: (T, T) -> T, outer: (T, T) -> T): Boolean =
    outer(a, inner(b, c)) == inner(outer(a, b), outer(a, c))

/**
 * Check if the given elements satisfy right distributivity of the given operations.
 *
 * @param T The type of the elements the operation is defined on.
 * @param a The first element.
 * @param b The second element.
 * @param c The third element.
 * @param inner The inner operation.
 * @param outer The outer operation.
 * @return True if the elements satisfy right distributivity, false otherwise.
 */
fun <T> isRightDistributive(a: T, b: T, c: T, inner: (T, T) -> T, outer: (T, T) -> T): Boolean =
    outer(inner(b, c), a) == inner(outer(b, a), outer(c, a))

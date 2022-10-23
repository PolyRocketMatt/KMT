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


package com.github.polyrocketmatt.kmt.common.utils

import java.lang.ClassCastException
import java.lang.IllegalStateException

/**
 * Check if a given condition is met on an object of type [T].
 *
 * @param condition The condition to check
 * @return The object itself if the condition is met
 * @throws IllegalStateException If the condition is not met
 */
fun <T> T.complies(condition: (T) -> Boolean): T {
    if (!condition(this))
        throw IllegalStateException()
    return this
}

/**
 * Check if a given condition is met on an object of type [T].
 *
 * @param condition The condition to check
 * @param cause The cause if the condition fails
 * @return The object itself if the condition is met
 * @throws IllegalStateException If the condition is not met
 */
fun <T> T.complies(cause: String, condition: (T) -> Boolean): T {
    if (!condition(this))
        throw IllegalStateException(cause)
    return this
}

/**
 * Check if a given condition is met on an object of type [T].
 *
 * @param condition The condition to check
 * @param cause The cause if the condition fails
 * @return The object itself if the condition is met
 * @throws IllegalStateException If the condition is not met
 */
fun <T> T.complies(cause: (T) -> String, condition: (T) -> Boolean): T {
    if (!condition(this))
        throw IllegalStateException(cause(this))
    return this
}

/**
 * Cast an object that is of type [A] to type [B] if possible.
 *
 * @param obj The object to cast
 * @return The object cast to type [B]
 * @throws ClassCastException If the object cannot be cast to type [B]
 */
inline fun <T, A : T, reified B : T> castIfType(obj: A): B =
    if (obj is B) obj else throw ClassCastException("Object is not of type ${B::class.simpleName}")
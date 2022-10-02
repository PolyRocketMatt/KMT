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

package com.github.polyrocketmatt.kmt.vector

import com.github.polyrocketmatt.kmt.vector.db.DoubleVector

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a vector based on a datatype [T].
 */
interface Vector<T> {

    /**
     * Get the length of the vector.
     *
     * @return The length of the vector.
     */
    fun length(): Float

    /**
     * Get the length of the vector as a double.
     *
     * @return The length of the vector as a double.
     */
    fun lengthDouble(): Double

    /**
     * Get the squared length of the vector.
     *
     * @return The squared length of the vector.
     */
    fun lengthSq(): T

    /**
     * Get the maximum component of the vector.
     *
     * @return The maximum component of the vector.
     */
    fun max(): T

    /**
     * Get the minimum component of the vector.
     *
     * @return The minimum component of the vector.
     */
    fun min(): T

    /**
     * Get the sum of all components of the vector.
     *
     * @return The sum of all components of the vector.
     */
    fun sum(): T

    /**
     * Get the difference of all components of the vector.
     *
     * @return The difference of all components of the vector.
     */
    fun diff(): T

    /**
     * Get the product of all components of the vector.
     *
     * @return The product of all components of the vector.
     */
    fun product(): T

    /**
     * Get the quotient of all components of the vector.
     *
     * @return The quotient of all components of the vector.
     */
    fun div(): T

    /**
     * Get the normalized version of the vector.
     *
     * @return The normalized version of the vector.
     */
    fun normalized(): Vector<Float>

    /**
     * Get the distance to another vector.
     *
     * @param other The other vector.
     * @return The distance to the other vector.
     */
    fun dist(other: Vector<T>): Float

    /**
     * Get the squared distance to another vector.
     *
     * @param other The other vector.
     * @return The squared distance to the other vector.
     */
    fun distSq(other: Vector<T>): Float

    /**
     * Get the dot product of the vector and another vector.
     *
     * @param other The other vector.
     * @return The dot product of the vector and the other vector.
     */
    fun dot(other: Vector<T>): Float

    /**
     * Get the dor product of the vector with itself.
     *
     * @return The dot product of the vector with itself.
     */
    fun sdot(): T

    /**
     * Get the inverted version of the vector.
     *
     * @return The inverted version of the vector.
     */
    operator fun unaryMinus(): Vector<T>

    /**
     * Get vector with the absolute values of all components.
     *
     * @return The vector with the absolute values of all components.
     */
    fun abs(): Vector<T>

    /**
     * Get the average of all components of the vector.
     *
     * @return The average of all components of the vector.
     */
    fun avg(): Float

    /**
     * Get the vector with minimal components of the vector and another vector.
     *
     * @param other The other vector.
     * @return The vector with minimal components of the vector and the other vector.
     */
    fun min(other: Vector<T>): Vector<T>

    /**
     * Get the vector with maximal components of the vector and another vector.
     *
     * @param other The other vector.
     * @return The vector with maximal components of the vector and the other vector.
     */
    fun max(other: Vector<T>): Vector<T>

    /**
     * Check if the given vector's components are within a range.
     *
     * @param min The lower bound.
     * @param max The upper bound.
     * @return A boolean vector where each component is true if the
     * corresponding component of this vector is within the range.
     */
    fun isIn(min: T, max: T): Vector<Boolean>

    /**
     * Raises the given vector's components to the n-th integer power.
     *
     * @param n The exponent.
     * @return The vector where each component is raised to the n-th power.
     */
    fun intPow(n: Int): Vector<T>

    /**
     * Get the sine of all components of the vector.
     *
     * @return The sine of all components of the vector.
     */
    fun sin(): DoubleVector

    /**
     * Get the cosine of all components of the vector.
     *
     * @return The cosine of all components of the vector.
     */
    fun cos(): DoubleVector

    /**
     * Get the tangent of all components of the vector.
     *
     * @return The tangent of all components of the vector.
     */
    fun tan(): DoubleVector
}

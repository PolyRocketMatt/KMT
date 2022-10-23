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

package com.github.polyrocketmatt.kmt.vector.db

import com.github.polyrocketmatt.kmt.matrix.Matrix
import com.github.polyrocketmatt.kmt.vector.Vector
import com.github.polyrocketmatt.kmt.vector.bl.BooleanVector
import com.github.polyrocketmatt.kmt.vector.fl.FloatVector
import com.github.polyrocketmatt.kmt.vector.it.IntVector
import com.github.polyrocketmatt.kmt.vector.sh.ShortVector

fun DoubleVector.float() = this.asFloat()
fun DoubleVector.int() = this.asInt()
fun DoubleVector.short() = this.asShort()

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents an n-dimensional vector of doubles.
 */
interface DoubleVector : Vector<Double>, Matrix<Double> {

    /**
     * Get a vector with floored components.
     *
     * @return A vector with floored components.
     */
    fun floor(): IntVector

    /**
     * Get a vector with ceiled components.
     *
     * @return A vector with ceiled components.
     */
    fun ceil(): IntVector

    /**
     * Get a vector with fractional components.
     *
     * @return A vector with fractional components.
     */
    fun fract(): DoubleVector

    /**
     * SmoothStep function for a double vector's components which are
     * expected to be within [0, 1].
     *
     * @return The smooth-stepped vector.
     */
    fun smoothStep(): DoubleVector

    /**
     * SmootherStep function for a double vector's components which are
     * expected to be within [0, 1].
     *
     * @return The smoother-stepped vector.
     */
    fun smootherStep(): DoubleVector

    /**
     * Linearly interpolate a vector's components to be within [a, b].
     *
     * @param min The lower bound.
     * @param max The upper bound.
     * @return The linearly interpolated vector.
     */
    fun lerp(min: Double, max: Double): DoubleVector

    /**
     * Clip a vector with components to be within [a, b].
     *
     * @param min The lower bound.
     * @param max The upper bound.
     * @return The clipped vector.
     */
    fun clip(min: Double, max: Double): DoubleVector

    /**
     * Get the vector as a floating point vector.
     *
     * @return The vector as a floating point vector.
     */
    fun asFloat(): FloatVector

    /**
     * Get the vector as an integer vector.
     *
     * @return The vector as an integer vector.
     */
    fun asInt(): IntVector

    /**
     * Get the vector as a short vector.
     *
     * @return The vector as a short vector.
     */
    fun asShort(): ShortVector

    /**
     * Get the vector as a boolean vector.
     *
     * @return The vector as a boolean vector.
     */
    fun asBoolean(): BooleanVector
}

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

package com.github.polyrocketmatt.kmt.vector.fl

import com.github.polyrocketmatt.kmt.matrix.Matrix
import com.github.polyrocketmatt.kmt.vector.Vector
import com.github.polyrocketmatt.kmt.vector.bl.BooleanVector
import com.github.polyrocketmatt.kmt.vector.db.DoubleVector
import com.github.polyrocketmatt.kmt.vector.it.IntVector
import com.github.polyrocketmatt.kmt.vector.sh.ShortVector

fun FloatVector.double() = this.asDouble()
fun FloatVector.int() = this.asInt()
fun FloatVector.short() = this.asShort()

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents an n-dimensional vector of floating-point numbers.
 */
interface FloatVector : Vector<Float>, Matrix<Float> {

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
    fun fract(): FloatVector

    /**
     * SmoothStep function for a double vector's components which are
     * expected to be within [0, 1].
     *
     * @return The smooth-stepped vector.
     */
    fun smoothStep(): FloatVector

    /**
     * SmootherStep function for a double vector's components which are
     * expected to be within [0, 1].
     *
     * @return The smoother-stepped vector.
     */
    fun smootherStep(): FloatVector

    /**
     * Linearly interpolate a vector's components to be within [a, b].
     *
     * @param min The lower bound.
     * @param max The upper bound.
     * @return The linearly interpolated vector.
     */
    fun lerp(min: Float, max: Float): FloatVector

    /**
     * Clip a vector with components to be within [a, b].
     *
     * @param min The lower bound.
     * @param max The upper bound.
     * @return The clipped vector.
     */
    fun clip(min: Float, max: Float): FloatVector

    /**
     * Get the vector as a double vector.
     *
     * @return The vector as a double vector.
     */
    fun asDouble(): DoubleVector

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

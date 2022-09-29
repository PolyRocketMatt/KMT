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

package com.github.polyrocketmatt.kmt.trigonometry

import com.github.polyrocketmatt.kmt.trigonometry.Trigonometry.Companion.PRECISION
import kotlin.math.sin

class Trigonometry {

    companion object {

        internal var PRECISION = 100
            set(value) {
                field = value
                SIN.recompute()
            }

        /**
         * Set the precision for the trigonometry tables.
         *
         * @param precision The precision of the trigonometry tables.
         */
        fun setPrecision(precision: Int) {
            PRECISION = precision
        }

    }

}

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Sine-table for fast sine calculation.
 */
object SIN {

    private val modulus: Int = 360 * PRECISION
    private val table: DoubleArray = DoubleArray(modulus)

    init {
        recompute()
    }

    internal fun recompute() {
        for (i in 0 until modulus)
            table[i] = sin(i * Math.PI / (PRECISION * 180.0f))
    }

    private fun sinLookup(i: Int): Double = if (i >= 0) table[i % modulus] else -table[-i % modulus]

    /**
     * Get the sine of the angle in degrees.
     *
     * @param angle The angle in degrees.
     * @return The sine of the angle.
     */
    operator fun get(angle: Double): Double = sinLookup((angle * PRECISION + 0.5).toInt())

    /**
     * Get the sine of the angle in degrees.
     *
     * @param angle The angle in degrees.
     * @return The sine of the angle.
     */
    operator fun get(angle: Float): Double = sinLookup((angle * PRECISION + 0.5).toInt())

    /**
     * Get the sine of the angle in degrees.
     *
     * @param angle The angle in degrees.
     * @return The sine of the angle.
     */
    operator fun get(angle: Int): Double = sinLookup((angle * PRECISION + 0.5).toInt())

    /**
     * Get the sine of the angle in degrees.
     *
     * @param angle The angle in degrees.
     * @return The sine of the angle.
     */
    operator fun get(angle: Short): Double = sinLookup((angle * PRECISION + 0.5).toInt())

    /**
     * Get the sine of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The sine of the angle.
     */
    fun rad(angle: Double): Double = sinLookup((angle * (180.0f / Math.PI)).toInt())

    /**
     * Get the sine of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The sine of the angle.
     */
    fun rad(angle: Float): Double = sinLookup((angle * (180.0f / Math.PI)).toInt())

    /**
     * Get the sine of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The sine of the angle.
     */
    fun rad(angle: Int): Double = sinLookup((angle * (180.0f / Math.PI)).toInt())

    /**
     * Get the sine of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The sine of the angle.
     */
    fun rad(angle: Short): Double = sinLookup((angle * (180.0f / Math.PI)).toInt())

}

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Cosine-table for fast cosine calculation.
 */
object COS {

    /**
     * Get the cosine of the angle in radians.
     *
     * @param angle The angle in degrees.
     * @return The cosine of the angle.
     */
    operator fun get(angle: Double): Double = SIN[angle + 90.0]

    /**
     * Get the cosine of the angle in radians.
     *
     * @param angle The angle in degrees.
     * @return The cosine of the angle.
     */
    operator fun get(angle: Float): Double = SIN[angle + 90.0]

    /**
     * Get the cosine of the angle in radians.
     *
     * @param angle The angle in degrees.
     * @return The cosine of the angle.
     */
    operator fun get(angle: Int): Double = SIN[angle + 90.0]

    /**
     * Get the cosine of the angle in radians.
     *
     * @param angle The angle in degrees.
     * @return The cosine of the angle.
     */
    operator fun get(angle: Short): Double = SIN[angle + 90.0]

    /**
     * Get the cosine of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The cosine of the angle.
     */
    fun rad(angle: Double): Double = SIN[angle * (180.0f / Math.PI)]

    /**
     * Get the cosine of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The cosine of the angle.
     */
    fun rad(angle: Float): Double = SIN[angle * (180.0f / Math.PI)]

    /**
     * Get the cosine of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The cosine of the angle.
     */
    fun rad(angle: Int): Double = SIN[angle * (180.0f / Math.PI)]

    /**
     * Get the cosine of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The cosine of the angle.
     */
    fun rad(angle: Short): Double = SIN[angle * (180.0f / Math.PI)]

}

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Tangent-table for fast tangent calculation.
 */
object TAN {

    /**
     * Get the tangent of the angle in radians.
     *
     * @param angle The angle in degrees.
     * @return The tangent of the angle.
     */
    operator fun get(angle: Double): Double = SIN[angle] / COS[angle]

    /**
     * Get the tangent of the angle in radians.
     *
     * @param angle The angle in degrees.
     * @return The tangent of the angle.
     */
    operator fun get(angle: Float): Double = SIN[angle] / COS[angle]

    /**
     * Get the tangent of the angle in radians.
     *
     * @param angle The angle in degrees.
     * @return The tangent of the angle.
     */
    operator fun get(angle: Int): Double = SIN[angle] / COS[angle]

    /**
     * Get the tangent of the angle in radians.
     *
     * @param angle The angle in degrees.
     * @return The tangent of the angle.
     */
    operator fun get(angle: Short): Double = SIN[angle] / COS[angle]

    /**
     * Get the tangent of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The tangent of the angle.
     */
    fun rad(angle: Double): Double = SIN[angle * (180.0f / Math.PI)] / COS[angle * (180.0f / Math.PI)]

    /**
     * Get the tangent of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The tangent of the angle.
     */
    fun rad(angle: Float): Double = SIN[angle * (180.0f / Math.PI)] / COS[angle * (180.0f / Math.PI)]

    /**
     * Get the tangent of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The tangent of the angle.
     */
    fun rad(angle: Int): Double = SIN[angle * (180.0f / Math.PI)] / COS[angle * (180.0f / Math.PI)]

    /**
     * Get the tangent of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The tangent of the angle.
     */
    fun rad(angle: Short): Double = SIN[angle * (180.0f / Math.PI)] / COS[angle * (180.0f / Math.PI)]

}
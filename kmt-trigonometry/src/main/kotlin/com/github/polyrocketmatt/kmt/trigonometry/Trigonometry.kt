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

import com.github.polyrocketmatt.kmt.common.decimals
import com.github.polyrocketmatt.kmt.trigonometry.Trigonometry.Companion.DECIMALS
import com.github.polyrocketmatt.kmt.trigonometry.Trigonometry.Companion.PRECISION
import com.github.polyrocketmatt.kmt.trigonometry.Trigonometry.Companion.RAD_TO_DEG
import kotlin.math.sin

/**
 * @author Matthias Kovacic
 * @since 0.0.8
 *
 * Represents a trigonometric base function.
 */
interface TrigonometricBaseFunction {

    /**
     * Calculates the trigonometric function of the given angle in radians.
     *
     * @param angle the angle in radians
     * @return the result of the trigonometric function
     */
    operator fun get(angle: Double): Double

    /**
     * Calculates the trigonometric function of the given angle in radians.
     *
     * @param angle the angle in radians
     * @return the result of the trigonometric function
     */
    operator fun get(angle: Float): Double

    /**
     * Calculates the trigonometric function of the given angle in radians.
     *
     * @param angle the angle in radians
     * @return the result of the trigonometric function
     */
    operator fun get(angle: Int): Double

    /**
     * Calculates the trigonometric function of the given angle in radians.
     *
     * @param angle the angle in radians
     * @return the result of the trigonometric function
     */
    operator fun get(angle: Short): Double
}

class Trigonometry {

    companion object {

        internal var PRECISION = 100
            set(value) {
                field = value
                SIN.recompute()
            }

        internal var DECIMALS = 8
            set(value) {
                field = value
                SIN.recompute()
            }

        internal const val RAD_TO_DEG = 180.0 / Math.PI

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
object SIN : TrigonometricBaseFunction {

    private val modulus: Int = 360 * PRECISION
    private val multiplier: Double = PRECISION * RAD_TO_DEG
    private val table: DoubleArray = DoubleArray(modulus)

    init {
        recompute()
    }

    internal fun recompute() {
        for (i in 0 until modulus)
            table[i] = sin(i * Math.PI / (PRECISION * 180.0f)).decimals(DECIMALS)
    }

    private fun sinLookup(i: Int): Double = if (i >= 0) table[i % modulus] else -table[-i % modulus]

    /**
     * Get the sine of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The sine of the angle.
     */
    override operator fun get(angle: Double): Double = sinLookup((angle * multiplier + 0.5).toInt())

    /**
     * Get the sine of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The sine of the angle.
     */
    override operator fun get(angle: Float): Double = sinLookup((angle * multiplier + 0.5).toInt())

    /**
     * Get the sine of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The sine of the angle.
     */
    override operator fun get(angle: Int): Double = sinLookup((angle * multiplier + 0.5).toInt())

    /**
     * Get the sine of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The sine of the angle.
     */
    override operator fun get(angle: Short): Double = sinLookup((angle * multiplier + 0.5).toInt())
}

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Cosine-table for fast cosine calculation.
 */
object COS : TrigonometricBaseFunction {

    /**
     * Get the cosine of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The cosine of the angle.
     */
    override operator fun get(angle: Double): Double = SIN[angle + 90.0]

    /**
     * Get the cosine of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The cosine of the angle.
     */
    override operator fun get(angle: Float): Double = SIN[angle + 90.0]

    /**
     * Get the cosine of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The cosine of the angle.
     */
    override operator fun get(angle: Int): Double = SIN[angle + 90]

    /**
     * Get the cosine of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The cosine of the angle.
     */
    override operator fun get(angle: Short): Double = SIN[angle + 90]
}

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Tangent-table for fast tangent calculation.
 */
object TAN : TrigonometricBaseFunction {

    /**
     * Get the tangent of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The tangent of the angle.
     */
    override operator fun get(angle: Double): Double = SIN[angle] / SIN[angle + 90.0]

    /**
     * Get the tangent of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The tangent of the angle.
     */
    override operator fun get(angle: Float): Double = SIN[angle] / SIN[angle + 90.0]

    /**
     * Get the tangent of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The tangent of the angle.
     */
    override operator fun get(angle: Int): Double = SIN[angle] / SIN[angle + 90.0]

    /**
     * Get the tangent of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The tangent of the angle.
     */
    override operator fun get(angle: Short): Double = SIN[angle] / SIN[angle + 90.0]
}

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Cotangent-table for fast cotangent calculation.
 */
object COT : TrigonometricBaseFunction {

    /**
     * Get the cotangent of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The cotangent of the angle.
     */
    override operator fun get(angle: Double): Double = SIN[angle + 90.0] / SIN[angle]

    /**
     * Get the cotangent of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The cotangent of the angle.
     */
    override operator fun get(angle: Float): Double = SIN[angle + 90.0] / SIN[angle]

    /**
     * Get the cotangent of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The cotangent of the angle.
     */
    override operator fun get(angle: Int): Double = SIN[angle + 90.0] / SIN[angle]

    /**
     * Get the cotangent of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The cotangent of the angle.
     */
    override operator fun get(angle: Short): Double = SIN[angle + 90.0] / SIN[angle]
}

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Secant-table for fast secant calculation.
 */
object SEC : TrigonometricBaseFunction {

    /**
     * Get the secant of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The secant of the angle.
     */
    override operator fun get(angle: Double): Double = 1.0 / SIN[angle + 90.0]

    /**
     * Get the secant of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The secant of the angle.
     */
    override operator fun get(angle: Float): Double = 1.0 / SIN[angle + 90.0]

    /**
     * Get the secant of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The secant of the angle.
     */
    override operator fun get(angle: Int): Double = 1.0 / SIN[angle + 90.0]

    /**
     * Get the secant of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The secant of the angle.
     */
    override operator fun get(angle: Short): Double = 1.0 / SIN[angle + 90.0]
}

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Cosecant-table for fast cosecant calculation.
 */
object COSEC : TrigonometricBaseFunction {

    /**
     * Get the cosecant of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The cosecant of the angle.
     */
    override operator fun get(angle: Double): Double = 1.0 / SIN[angle]

    /**
     * Get the cosecant of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The cosecant of the angle.
     */
    override operator fun get(angle: Float): Double = 1.0 / SIN[angle]

    /**
     * Get the cosecant of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The cosecant of the angle.
     */
    override operator fun get(angle: Int): Double = 1.0 / SIN[angle]

    /**
     * Get the cosecant of the angle in radians.
     *
     * @param angle The angle in radians.
     * @return The cosecant of the angle.
     */
    override operator fun get(angle: Short): Double = 1.0 / SIN[angle]
}

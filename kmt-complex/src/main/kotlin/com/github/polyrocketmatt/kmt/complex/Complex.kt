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

package com.github.polyrocketmatt.kmt.complex

import kotlin.math.atan2
import kotlin.math.sqrt

/**
 * @author Matthias Kovacic
 * @since 0.1.0
 *
 * Represents a complex number.
 *
 * @param re The real part of the complex number.
 * @param im The imaginary part of the complex number.
 */
data class Complex(
    private val re: Double,
    private val im: Double
) {

    constructor(re: Double) : this(re, 0.0)

    /**
     * Get the real part of the complex number.
     *
     * @return The real part of the complex number.
     */
    fun real(): Double = re

    /**
     * Get the imaginary part of the complex number.
     *
     * @return The imaginary part of the complex number.
     */
    fun imaginary(): Double = im

    /**
     * Get the magnitude of the complex number.
     *
     * @return The magnitude of the complex number.
     */
    fun magnitude(): Double = sqrt(re * re + im * im)

    /**
     * Get the angle between the vector (re, im) represented by the
     * complex number and the x-axis.
     */
    fun theta(): Double = atan2(im, re)

    /**
     * Get the squared magnitude of the complex number.
     *
     * @return The squared magnitude of the complex number.
     */
    fun magnitudeSq(): Double = re * re + im * im

    /**
     * Get the conjugate of the complex number.
     *
     * @return The conjugate of the complex number.
     */
    fun conjugate(): Complex = Complex(re, -im)

    /**
     * Get the reciprocal of the complex number.
     *
     * @return The reciprocal of the complex number.
     */
    fun reciprocal(): Complex = Complex(re / magnitudeSq(), -im / magnitudeSq())

    /**
     * Add two complex numbers together.
     *
     * @param other The other complex number to add.
     * @return The sum of the two complex numbers.
     */
    operator fun plus(other: Complex): Complex = Complex(re + other.re, im + other.im)

    /**
     * Subtract a complex number from another.
     *
     * @param other The other complex number to subtract.
     * @return The difference of the two complex numbers.
     */
    operator fun minus(other: Complex): Complex = Complex(re - other.re, im - other.im)

    /**
     * Multiply two complex numbers together.
     *
     * @param other The other complex number to multiply.
     * @return The product of the two complex numbers.
     */
    operator fun times(other: Complex): Complex = Complex(
        re * other.re - im * other.im,
        re * other.im + im * other.re
    )

    /**
     * Divide a complex number by another.
     *
     * @param other The other complex number to divide by.
     * @return The quotient of the two complex numbers.
     */
    operator fun div(other: Complex): Complex = Complex(
        (re * other.re + im * other.im) / (other.re * other.re + other.im * other.im),
        (im * other.re + re * other.im) / (other.re * other.re + other.im * other.im)
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Complex) return false

        if (re != other.re) return false
        if (im != other.im) return false

        return true
    }

    override fun hashCode(): Int {
        var result = re.hashCode()
        result = 31 * result + im.hashCode()
        return result
    }
}

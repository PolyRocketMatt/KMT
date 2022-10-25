package com.github.polyrocketmatt.kmt.complex

import com.github.polyrocketmatt.kmt.common.dsqrt
import kotlin.math.atan2

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
    val re: Double,
    val im: Double
) {

    constructor(re: Double) : this(re, 0.0)

    /**
     * Get the magnitude of the complex number.
     *
     * @return The magnitude of the complex number.
     */
    fun magnitude(): Double = (re * re + im * im).dsqrt()

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
    operator fun times(other: Complex): Complex = Complex(re * other.re - im * other.im, re * other.im + im * other.re)

    /**
     * Divide a complex number by another.
     *
     * @param other The other complex number to divide by.
     * @return The quotient of the two complex numbers.
     */
    operator fun div(other: Complex): Complex = Complex(
        (re * other.re + im * other.im) / (re * re + other.re * other.re),
        (other.re * im - re * other.im) / (re * re + other.re * other.re)
    )
}

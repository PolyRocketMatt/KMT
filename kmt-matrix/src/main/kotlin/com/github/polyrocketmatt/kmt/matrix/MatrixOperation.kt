package com.github.polyrocketmatt.kmt.matrix

/**
 * @author Matthias Kovacic
 * @since 0.1.1
 *
 * Represents different norms.
 */
enum class NormType {
    ONE_NORM,
    TWO_NORM,
    INFINITY_NORM,
    FROBENIUS_NORM,
    MAX_NORM
}

enum class QRFactorizationMethod {
    GRAM_SCHMIDT,
    GIVENS,
    PIVOTING
}
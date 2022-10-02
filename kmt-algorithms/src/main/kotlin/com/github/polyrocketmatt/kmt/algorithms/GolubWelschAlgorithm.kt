package com.github.polyrocketmatt.kmt.algorithms

private class GolubWelschAlgorithm(
    val alpha: Double,
    val degree: Int
) : Algorithm<Double> {

    init {
        if (alpha < -1.0)
            throw IllegalArgumentException("Alpha must be greater than -1.0 for Golub-Welsch algorithm")
        if (degree < 2)
            throw IllegalArgumentException("Degree must be greater than 1 for Golub-Welsch algorithm")
    }

    companion object {

        fun with(alpha: Double, degree: Int) = GolubWelschAlgorithm(alpha, degree)
    }

    //  TODO: Use kmt-matrix library
    override fun run(): Double = throw NotImplementedError("Not implemented yet")
}

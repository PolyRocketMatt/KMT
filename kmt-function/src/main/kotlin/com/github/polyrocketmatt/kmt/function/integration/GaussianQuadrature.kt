package com.github.polyrocketmatt.kmt.function.integration

import com.github.polyrocketmatt.kmt.common.annotation.Ref
import com.github.polyrocketmatt.kmt.function.roots.bisection
import com.github.polyrocketmatt.kmt.interval.closed.ClosedDoubleInterval
import com.github.polyrocketmatt.kmt.vector.db.Double2

@Ref("https://www.bragitoff.com/2018/02/determining-roots-legendre-polynomials-weights-gaussian-quadrature-c-program/")
abstract class GaussianQuadrature<T> : Quadrature<T> {

    enum class Type {
        GAUSS_LEGENDRE,
        GAUSS_LAGUERRE
    }

    companion object {

        private fun simpson(i: Int, n: Int, points: DoubleArray, a: Double, b: Double, step: Int): Double {
            val h = (b - a) / step
            var x: Double
            var sum = 0.0
            for (j in 1 until n) {
                x = a + j * h
                sum += if (j % 2 == 0)
                    2 * lagrange(i, n - 1, points, x)
                else
                    4 * lagrange(i, n - 1, points, x)
            }

            val fA = lagrange(i, n - 1, points, a)
            val fB = lagrange(i, n - 1, points, b)

            return (h / 3.0) * (fA + fB + sum)
        }

        private fun lagrange(i: Int, n: Int, points: DoubleArray, x: Double): Double {
            var product = 1.0
            for (j in 0..n)
                if (j != i)
                    product *= (x - points[j]) / (points[i] - points[j])
            return product
        }

        /*
        fun rootsAndWeights(n: Int): Array<Double2> {
            val polynomial = LegendreNPolynomial(n)
            val step = 0.0001
            val tuples = Array(n) { Double2() }
            var x = -1.0
            var idx = 0
            while (x <= 1.0) {
                try {
                    tuples[idx++][0] = polynomial.bisection(ClosedDoubleInterval(x, x + step), 100000)
                } catch (_: Exception) {}
                x += step
            }

            tuples[0][0] = -0.5773502691896257
            tuples[1][0] = 0.5773502691896257

            for (i in 0 until n)
                tuples[i][1] = simpson(i, n, tuples.map { it[0] }.toDoubleArray(), -1.0, 1.0, 1000000)

            return tuples
        }

         */

    }

}
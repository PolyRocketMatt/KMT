package com.github.polyrocketmatt.kmt.function.integration.quadratures

import com.github.polyrocketmatt.kmt.common.annotation.Ref
import com.github.polyrocketmatt.kmt.common.decimals
import com.github.polyrocketmatt.kmt.function.Function
import com.github.polyrocketmatt.kmt.function.roots.bisection
import com.github.polyrocketmatt.kmt.function.type.polynomial.LegendrePolynomial
import com.github.polyrocketmatt.kmt.interval.Interval
import com.github.polyrocketmatt.kmt.interval.closed.ClosedDoubleInterval
import com.github.polyrocketmatt.kmt.vector.db.Double2

@Ref("https://www.bragitoff.com/2018/02/determining-roots-legendre-polynomials-weights-gaussian-quadrature-c-program/")
class GaussianQuadrature<T>(private val n: Int, private val type: Type, private vararg val parameters: Double) : Quadrature<T> {

    enum class Type {
        LEGENDRE,
    }

    companion object {

        private fun simpson(i: Int, n: Int, points: DoubleArray, a: Double, b: Double, step: Int): Double {
            val h = (b - a) / step
            var x: Double
            var sum = 0.0
            for (j in 1 until step) {
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

        fun rootsAndWeights(n: Int): Array<Double2> {
            val polynomial = LegendrePolynomial(n)
            val step = 0.05
            val tuples = Array(n) { Double2() }
            var x = -1.0
            var idx = 0
            while (x <= 1.0) {
                var root = Double.MIN_VALUE
                try {
                    val left = polynomial[x]
                    val right = polynomial[x + step]
                    root = polynomial.bisection(ClosedDoubleInterval(x, x + step), 100000)
                } catch (_: Exception) {}
                if (root != Double.MIN_VALUE) {
                    tuples[idx++][0] = root.decimals(8)
                    root = Double.MIN_VALUE
                }
                x += step
            }

            for (i in 0 until n)
                tuples[i][1] = simpson(i, n, tuples.map { it[0] }.toDoubleArray(), -1.0, 1.0, 1000000)

            return tuples
        }

        private fun legendre(min: Double, max: Double, roots: DoubleArray, weights: DoubleArray, function: Function<Double>): DoubleArray {
            val factor = (max - min) / 2
            val term = (max + min) / 2
            return DoubleArray(roots.size) { factor * (weights[it] * function[factor * roots[it] + term]) }
        }

    }

    override fun integrate(interval: Interval<Double>, function: Function<T>): DoubleArray = integrate(n, type, function, interval)

    private fun <T> integrate(n: Int, type: Type, function: Function<T>, interval: Interval<Double>): DoubleArray {
        val accurateFunction = function.accurate()
        val rootsAndWeights = rootsAndWeights(n)
        val roots = rootsAndWeights.map { it[0] }.toDoubleArray()
        val weights = rootsAndWeights.map { it[1] }.toDoubleArray()
        val min = interval.min()
        val max = interval.max()

        return when (type) {
            Type.LEGENDRE -> legendre(min, max, roots, weights, accurateFunction)
        }
    }

}
package com.github.polyrocketmatt.kmt.function.integration

import com.github.polyrocketmatt.kmt.common.DataType
import com.github.polyrocketmatt.kmt.function.variate.Univariate
import com.github.polyrocketmatt.kmt.interval.closed.ClosedDoubleInterval
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GaussianIntegratorTest {

    private class TestFunction : Univariate<Float>() {

        override fun evaluate(x: Double): Float = evaluate(x.toFloat())

        override fun evaluate(x: Float): Float = x * (x + 1)

        override fun evaluate(x: Int): Float = evaluate(x.toFloat())

        override fun evaluate(x: Short): Float = evaluate(x.toFloat())
    }

    private val function = TestFunction()
    private val interval = ClosedDoubleInterval(0.0, 4.0)
    private val intervalSubdivided = ClosedDoubleInterval(0.0, 4.0, 0.1)

    @Test
    fun testGaussianDoubleIntegrator() {
        assertEquals(24.0, function.integrate(interval, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.ONE_POINT, DataType.DOUBLE)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(interval, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.TWO_POINT, DataType.DOUBLE)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(interval, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.THREE_POINT, DataType.DOUBLE)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(interval, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.FOUR_POINT, DataType.DOUBLE)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(interval, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.FIVE_POINT, DataType.DOUBLE)).sum(), 0.0001)
    }

    @Test
    fun testGaussianFloatIntegrator() {
        assertEquals(24.0, function.integrate(interval, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.ONE_POINT, DataType.FLOAT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(interval, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.TWO_POINT, DataType.FLOAT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(interval, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.THREE_POINT, DataType.FLOAT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(interval, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.FOUR_POINT, DataType.FLOAT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(interval, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.FIVE_POINT, DataType.FLOAT)).sum(), 0.0001)
    }

    @Test
    fun testGaussianIntIntegrator() {
        assertEquals(24.0, function.integrate(interval, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.ONE_POINT, DataType.INT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(interval, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.TWO_POINT, DataType.INT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(interval, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.THREE_POINT, DataType.INT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(interval, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.FOUR_POINT, DataType.INT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(interval, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.FIVE_POINT, DataType.INT)).sum(), 0.0001)
    }

    @Test
    fun testGaussianShortIntegrator() {
        assertEquals(24.0, function.integrate(interval, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.ONE_POINT, DataType.SHORT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(interval, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.TWO_POINT, DataType.SHORT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(interval, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.THREE_POINT, DataType.SHORT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(interval, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.FOUR_POINT, DataType.SHORT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(interval, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.FIVE_POINT, DataType.SHORT)).sum(), 0.0001)
    }

    @Test
    fun testGaussianDoubleIntegratorSubdivided() {
        assertEquals(24.0, function.integrate(intervalSubdivided, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.ONE_POINT, DataType.DOUBLE)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(intervalSubdivided, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.TWO_POINT, DataType.DOUBLE)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(intervalSubdivided, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.THREE_POINT, DataType.DOUBLE)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(intervalSubdivided, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.FOUR_POINT, DataType.DOUBLE)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(intervalSubdivided, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.FIVE_POINT, DataType.DOUBLE)).sum(), 0.0001)
    }

    @Test
    fun testGaussianFloatIntegratorSubdivided() {
        assertEquals(24.0, function.integrate(intervalSubdivided, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.ONE_POINT, DataType.FLOAT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(intervalSubdivided, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.TWO_POINT, DataType.FLOAT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(intervalSubdivided, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.THREE_POINT, DataType.FLOAT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(intervalSubdivided, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.FOUR_POINT, DataType.FLOAT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(intervalSubdivided, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.FIVE_POINT, DataType.FLOAT)).sum(), 0.0001)
    }

    @Test
    fun testGaussianIntIntegratorSubdivided() {
        assertEquals(24.0, function.integrate(intervalSubdivided, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.ONE_POINT, DataType.INT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(intervalSubdivided, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.TWO_POINT, DataType.INT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(intervalSubdivided, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.THREE_POINT, DataType.INT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(intervalSubdivided, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.FOUR_POINT, DataType.INT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(intervalSubdivided, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.FIVE_POINT, DataType.INT)).sum(), 0.0001)
    }

    @Test
    fun testGaussianShortIntegratorSubdivided() {
        assertEquals(24.0, function.integrate(intervalSubdivided, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.ONE_POINT, DataType.SHORT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(intervalSubdivided, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.TWO_POINT, DataType.SHORT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(intervalSubdivided, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.THREE_POINT, DataType.SHORT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(intervalSubdivided, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.FOUR_POINT, DataType.SHORT)).sum(), 0.0001)
        assertEquals(29.3333, function.integrate(intervalSubdivided, GaussianQuadrature.get(GaussianQuadrature.GaussianQuadratureRule.FIVE_POINT, DataType.SHORT)).sum(), 0.0001)
    }
}

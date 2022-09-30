package com.github.polyrocketmatt.kmt.function.integration

import com.github.polyrocketmatt.kmt.common.DataType
import com.github.polyrocketmatt.kmt.function.variate.Univariate
import com.github.polyrocketmatt.kmt.interval.closed.ClosedDoubleInterval
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TrapezoidIntegratorTest {

    private class TestFunction : Univariate<Float>() {

        override fun evaluate(x: Double): Float = evaluate(x.toFloat())

        override fun evaluate(x: Float): Float = x * (x + 1)

        override fun evaluate(x: Int): Float = evaluate(x.toFloat())

        override fun evaluate(x: Short): Float = evaluate(x.toFloat())

    }

    private val function = TestFunction()
    private val interval = ClosedDoubleInterval(0.0, 4.0)
    private val subdividedInterval = ClosedDoubleInterval(0.0, 4.0, 0.1)

    @Test
    fun testGaussianDoubleIntegrator() = assertEquals(40.0, function.integrate(interval, TrapezoidIntegrator.get(DataType.DOUBLE)).sum(), 0.001)

    @Test
    fun testGaussianFloatIntegrator() = assertEquals(40.0, function.integrate(interval, TrapezoidIntegrator.get(DataType.FLOAT)).sum(), 0.001)

    @Test
    fun testGaussianIntIntegrator() = assertEquals(40.0, function.integrate(interval, TrapezoidIntegrator.get(DataType.INT)).sum(), 0.001)

    @Test
    fun testGaussianShortIntegrator() = assertEquals(40.0, function.integrate(interval, TrapezoidIntegrator.get(DataType.SHORT)).sum(), 0.001)

    @Test
    fun testGaussianDoubleIntegratorSubdivided() = assertEquals(29.439, function.integrate(subdividedInterval, TrapezoidIntegrator.get(DataType.DOUBLE)).sum(), 0.001)

    @Test
    fun testGaussianFloatIntegratorSubdivided() = assertEquals(29.439, function.integrate(subdividedInterval, TrapezoidIntegrator.get(DataType.FLOAT)).sum(), 0.001)

    @Test
    fun testGaussianIntIntegratorSubdivided() = assertEquals(27.999, function.integrate(subdividedInterval, TrapezoidIntegrator.get(DataType.INT)).sum(), 0.001)

    @Test
    fun testGaussianShortIntegratorSubdivided() = assertEquals(27.999, function.integrate(subdividedInterval, TrapezoidIntegrator.get(DataType.SHORT)).sum(), 0.001)

}
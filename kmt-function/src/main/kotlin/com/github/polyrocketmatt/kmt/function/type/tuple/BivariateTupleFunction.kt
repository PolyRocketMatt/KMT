package com.github.polyrocketmatt.kmt.function.type.tuple

import com.github.polyrocketmatt.kmt.common.storage.Tuple

abstract class BivariateTupleFunction<T> : TupleFunction<T>(2) {

    override operator fun get(x: Double): Tuple<T> = throw UnsupportedOperationException("Bivariate functions do not support single argument")

    override fun get(x: Double, y: Double): Tuple<T> = evaluate(x, y)

    override fun get(vararg x: Double): Tuple<T> = throw UnsupportedOperationException("Univariate functions do not support multiple arguments")

    /**
     * Evaluates the function at the given inputs.
     *
     * @param x The first input to evaluate the function at.
     * @param y The second input to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(x: Double, y: Double): Tuple<T>

    /**
     * Evaluates the function at the given inputs.
     *
     * @param x The first input to evaluate the function at.
     * @param y The second input to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(x: Float, y: Float): Tuple<T>

    /**
     * Evaluates the function at the given inputs.
     *
     * @param x The first input to evaluate the function at.
     * @param y The second input to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(x: Int, y: Int): Tuple<T>

    /**
     * Evaluates the function at the given inputs.
     *
     * @param x The first input to evaluate the function at.
     * @param y The second input to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(x: Short, y: Short): Tuple<T>

}
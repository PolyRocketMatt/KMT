package com.github.polyrocketmatt.kmt.function.type.tuple

import com.github.polyrocketmatt.kmt.common.storage.Tuple

abstract class UnivariateTupleFunction<T> : TupleFunction<T>(1) {

    override operator fun get(x: Double): Tuple<T> = evaluate(x)

    override fun get(x: Double, y: Double): Tuple<T>  = throw UnsupportedOperationException("Univariate functions do not support two arguments")

    override fun get(vararg x: Double): Tuple<T>  = throw UnsupportedOperationException("Univariate functions do not support multiple arguments")

    /**
     * Evaluates the function at the given input.
     *
     * @param x The input to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(x: Double): Tuple<T>

    /**
     * Evaluates the function at the given input.
     *
     * @param x The input to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(x: Float): Tuple<T>

    /**
     * Evaluates the function at the given input.
     *
     * @param x The input to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(x: Int): Tuple<T>

    /**
     * Evaluates the function at the given input.
     *
     * @param x The input to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(x: Short): Tuple<T>

}
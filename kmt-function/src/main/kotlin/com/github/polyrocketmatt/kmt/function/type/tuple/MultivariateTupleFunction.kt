package com.github.polyrocketmatt.kmt.function.type.tuple

import com.github.polyrocketmatt.kmt.common.storage.Tuple

abstract class MultivariateTupleFunction<T>(arity: Int) : TupleFunction<T>(arity) {

    override operator fun get(x: Double): Tuple<T> = throw UnsupportedOperationException("Bivariate functions do not support single arguments")

    override fun get(x: Double, y: Double): Tuple<T> = throw UnsupportedOperationException("Bivariate functions do not support two arguments")

    override fun get(vararg x: Double): Tuple<T> = evaluate(*x)

    /**
     * Evaluates the function at the given inputs.
     *
     * @param x The inputs to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(vararg x: Double): Tuple<T>

    /**
     * Evaluates the function at the given inputs.
     *
     * @param x The inputs to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(vararg x: Float): Tuple<T>

    /**
     * Evaluates the function at the given inputs.
     *
     * @param x The inputs to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(vararg x: Int): Tuple<T>

    /**
     * Evaluates the function at the given inputs.
     *
     * @param x The inputs to evaluate the function at.
     * @return The output of the function.
     */
    abstract fun evaluate(vararg x: Short): Tuple<T>

}
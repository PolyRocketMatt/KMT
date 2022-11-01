package com.github.polyrocketmatt.kmt.matrix

import com.github.polyrocketmatt.kmt.group.algebra.AbelianGroup
import com.github.polyrocketmatt.kmt.group.set.DefinedSet

class MatrixAlgebra {

    companion object {
        @Suppress("UNCHECKED_CAST")
        inline fun <reified T> groupAddition(shape: IntArray, noinline isMember: (Matrix<T>) -> Boolean): AbelianGroup<Matrix<T>> =
            AbelianGroup(
                createNeutralAdditionMatrix(shape, T::class.java) as Matrix<T>,
                { a: Matrix<T> -> -a },
                { a: Matrix<T>, b: Matrix<T> -> a + b },
                DefinedSet(isMember)
            )

        inline fun <reified T> groupAddition(shape: IntArray, neutral: Matrix<T>, noinline isMember: (Matrix<T>) -> Boolean): AbelianGroup<Matrix<T>> =
            AbelianGroup(
                neutral,
                { a: Matrix<T> -> -a },
                { a: Matrix<T>, b: Matrix<T> -> a + b },
                DefinedSet(isMember)
            )

        @Suppress("UNCHECKED_CAST")
        inline fun <reified T, K> generalLinearGroup(n: Int): AbelianGroup<NumericMatrix<T, K>> =
            AbelianGroup(
                createNeutralMultiplicationMatrix(intArrayOf(n, n), T::class.java) as NumericMatrix<T, K>,
                { a: NumericMatrix<T, K> -> a.inverse() as NumericMatrix<T, K> },
                { a: NumericMatrix<T, K>, b: NumericMatrix<T, K> -> (a * b) as NumericMatrix<T, K> },
                DefinedSet { it.isInvertible() }
            )

        fun <T> createNeutralAdditionMatrix(shape: IntArray, clazz: Class<T>): Matrix<*> = when(clazz) {
            Double::class.java      -> DoubleMatrix(shape, 0.0)
            Float::class.java       -> FloatMatrix(shape, 0.0f)
            Int::class.java         -> IntMatrix(shape, 0)
            Short::class.java       -> ShortMatrix(shape, 0)
            else                    -> throw IllegalArgumentException("Cannot create neutral addition matrix for type ${clazz.simpleName}")
        }

        fun <T> createNeutralMultiplicationMatrix(shape: IntArray, clazz: Class<T>): Matrix<*> = when(clazz) {
            Double::class.java      -> DoubleMatrix.identity(shape)
            Float::class.java       -> FloatMatrix.identity(shape)
            Int::class.java         -> IntMatrix.identity(shape)
            Short::class.java       -> ShortMatrix.identity(shape)
            else                    -> throw IllegalArgumentException("Cannot create neutral multiplication matrix for type ${clazz.simpleName}")
        }
    }

}
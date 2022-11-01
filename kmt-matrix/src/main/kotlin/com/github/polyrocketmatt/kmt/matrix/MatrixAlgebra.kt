/*
 * KMT, Kotlin Math Toolkit
 * Copyright (C) Matthias Kovacic <matthias.kovacic@student.kuleuven.be>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.polyrocketmatt.kmt.matrix

import com.github.polyrocketmatt.kmt.group.algebra.AbelianGroup
import com.github.polyrocketmatt.kmt.group.set.DefinedSet

/**
 * @author Matthias Kovacic
 * @since 0.1.1
 *
 * Utilities for creating algebra's of matrices.
 */
class MatrixAlgebra {

    companion object {
        /**
         * Create an abelian group of matrices under addition.
         *
         * @param T The type of the elements of the matrices.
         * @param shape The shape of the matrices.
         * @param isMember A function that determines whether a given element is a member of the abelian group.
         * @return An abelian group of matrices under addition.
         */
        @Suppress("UNCHECKED_CAST")
        inline fun <reified T> groupAddition(shape: IntArray, noinline isMember: (Matrix<T>) -> Boolean): AbelianGroup<Matrix<T>> =
            AbelianGroup(
                createNeutralAdditionMatrix(shape, T::class.java) as Matrix<T>,
                { a: Matrix<T> -> -a },
                { a: Matrix<T>, b: Matrix<T> -> a + b },
                DefinedSet(isMember)
            )

        /**
         * Create an abelian group of matrices under addition.
         *
         * @param T The type of the elements of the matrices.
         * @param shape The shape of the matrices.
         * @param neutral The neutral element of the abelian group.
         * @param isMember A function that determines whether a given element is a member of the abelian group.
         * @return An abelian group of matrices under addition.
         */
        inline fun <reified T> groupAddition(shape: IntArray, neutral: Matrix<T>, noinline isMember: (Matrix<T>) -> Boolean): AbelianGroup<Matrix<T>> =
            AbelianGroup(
                neutral,
                { a: Matrix<T> -> -a },
                { a: Matrix<T>, b: Matrix<T> -> a + b },
                DefinedSet(isMember)
            )

        /**
         * Create an abelian group of matrices under multiplication.
         *
         * @param T The type of the elements of the matrices.
         * @param n The shape of the (n x n) matrices.
         * @return An abelian group of matrices under multiplication.
         */
        @Suppress("UNCHECKED_CAST")
        inline fun <reified T, K> generalLinearGroup(n: Int): AbelianGroup<NumericMatrix<T, K>> =
            AbelianGroup(
                createNeutralMultiplicationMatrix(intArrayOf(n, n), T::class.java) as NumericMatrix<T, K>,
                { a: NumericMatrix<T, K> -> a.inverse() as NumericMatrix<T, K> },
                { a: NumericMatrix<T, K>, b: NumericMatrix<T, K> -> (a * b) as NumericMatrix<T, K> },
                DefinedSet { it.isInvertible() }
            )

        /**
         * Create a neutral matrix under addition.
         *
         * @param T The type of the elements of the matrix.
         * @param shape The shape of the matrix.
         * @param clazz The class of the elements of the matrix.
         * @return A neutral matrix under addition.
         */
        fun <T> createNeutralAdditionMatrix(shape: IntArray, clazz: Class<T>): Matrix<*> = when(clazz) {
            Double::class.java      -> DoubleMatrix(shape, 0.0)
            Float::class.java       -> FloatMatrix(shape, 0.0f)
            Int::class.java         -> IntMatrix(shape, 0)
            Short::class.java       -> ShortMatrix(shape, 0)
            else                    -> throw IllegalArgumentException("Cannot create neutral addition matrix for type ${clazz.simpleName}")
        }

        /**
         * Create a neutral matrix under multiplication.
         *
         * @param T The type of the elements of the matrix.
         * @param shape The shape of the matrix.
         * @param clazz The class of the elements of the matrix.
         * @return A neutral matrix under multiplication.
         */
        fun <T> createNeutralMultiplicationMatrix(shape: IntArray, clazz: Class<T>): Matrix<*> = when(clazz) {
            Double::class.java      -> DoubleMatrix.identity(shape)
            Float::class.java       -> FloatMatrix.identity(shape)
            Int::class.java         -> IntMatrix.identity(shape)
            Short::class.java       -> ShortMatrix.identity(shape)
            else                    -> throw IllegalArgumentException("Cannot create neutral multiplication matrix for type ${clazz.simpleName}")
        }
    }

}
package com.github.polyrocketmatt.kmt.linalg

import com.github.polyrocketmatt.kmt.common.storage.Tuple
import com.github.polyrocketmatt.kmt.common.utils.complies
import com.github.polyrocketmatt.kmt.matrix.DoubleMatrix
import com.github.polyrocketmatt.kmt.matrix.FloatMatrix
import com.github.polyrocketmatt.kmt.matrix.IntMatrix
import com.github.polyrocketmatt.kmt.matrix.ShortMatrix

/**
 * Construct a basis from the given vectors.
 *
 * @param T The type of the vectors.
 * @param vectors The vectors to construct the basis from.
 * @return The constructed basis.
 * @throws IllegalArgumentException If there are no vectors provided.
 * @throws IllegalArgumentException If the vectors are of length 0.
 * @throws IllegalArgumentException If the vectors are not all the same length.
 * @throws IllegalArgumentException If the vectors are not all linearly independent.
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T> constructBasisFrom(vararg vectors: Tuple<T>) {
    if (vectors.isEmpty())
        throw IllegalArgumentException("Basis cannot have 0 vectors")
    if (vectors.any { it.size <= 0 })
        throw IllegalArgumentException("Basis vectors must have at lease length 1")
    if (vectors.any { it.size != vectors[0].size })
        throw IllegalArgumentException("Basis vectors must all be of equal length")

    val basisVectors = mutableListOf<Tuple<T>>()
    for (element in vectors) {
        val elements = (element + basisVectors.toList().flatten()).toTypedArray()
        val matrix = when (T::class.java) {
            Float::class.java -> FloatMatrix(intArrayOf(basisVectors.size + 1, element.size), elements as Array<Float>)
            Double::class.java -> DoubleMatrix(intArrayOf(basisVectors.size + 1, element.size), elements as Array<Double>)
            Int::class.java -> IntMatrix(intArrayOf(basisVectors.size + 1, element.size), elements as Array<Int>)
            Short::class.java -> ShortMatrix(intArrayOf(basisVectors.size + 1, element.size), elements as Array<Short>)
            else -> throw IllegalArgumentException("Basis vectors must be of type Float, Double, Int, or Short")
        }

        if (matrix.rank() == basisVectors.size + 1)
            basisVectors.add(element)
    }
}

/**
 * Construct a basis from the given vectors.
 *
 * @param T The type of the vectors.
 * @param vectors The vectors to construct the basis from.
 * @return The constructed basis.
 * @throws IllegalArgumentException If there are no vectors provided.
 * @throws IllegalArgumentException If the vectors are of length 0.
 * @throws IllegalArgumentException If the vectors are not all the same length.
 * @throws IllegalArgumentException If the vectors are not all linearly independent.
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T> constructBasisFrom(vectors: Collection<Tuple<T>>) {
    if (vectors.isEmpty())
        throw IllegalArgumentException("Basis cannot have 0 vectors")
    if (vectors.any { it.size <= 0 })
        throw IllegalArgumentException("Basis vectors must have at lease length 1")
    if (vectors.any { it.size != vectors.elementAt(0).size })
        throw IllegalArgumentException("Basis vectors must all be of equal length")

    val basisVectors = mutableListOf<Tuple<T>>()
    for (element in vectors) {
        val elements = (element + basisVectors.toList().flatten()).toTypedArray()
        val matrix = when (T::class.java) {
            Float::class.java -> FloatMatrix(intArrayOf(basisVectors.size + 1, element.size), elements as Array<Float>)
            Double::class.java -> DoubleMatrix(intArrayOf(basisVectors.size + 1, element.size), elements as Array<Double>)
            Int::class.java -> IntMatrix(intArrayOf(basisVectors.size + 1, element.size), elements as Array<Int>)
            Short::class.java -> ShortMatrix(intArrayOf(basisVectors.size + 1, element.size), elements as Array<Short>)
            else -> throw IllegalArgumentException("Basis vectors must be of type Float, Double, Int, or Short")
        }

        if (matrix.rank() == basisVectors.size + 1)
            basisVectors.add(element)
    }
}

/**
 * @author Matthias Kovacic
 * @since 0.1.0
 *
 * Represents a basis for a vector space.
 *
 * @param T The type of the basis.
 * @param vectors The vectors that make up the basis.
 */
@Suppress("UNCHECKED_CAST")
class Basis<T : Number>(private vararg val vectors: Tuple<T>) {

    init {
        complies("Basis cannot have 0 vectors") { vectors.isNotEmpty() }
        complies("Basis vectors must have at lease length 1") { vectors.all { it.size > 0 } }
        complies("Basis vectors must all be of equal length") { vectors.all { it.size == vectors[0].size } }

        //  Check linear dependence
        val shape = intArrayOf(vectors.size, vectors[0].size)
        val matrix = when (vectors[0][0]) {
            is Float        -> FloatMatrix(shape, vectors.map { (it.data as Array<Float>).toList() }.flatten().toFloatArray())
            is Double       -> DoubleMatrix(shape, vectors.map { (it.data as Array<Double>).toList() }.flatten().toDoubleArray())
            is Int          -> IntMatrix(shape, vectors.map { (it.data as Array<Int>).toList() }.flatten().toIntArray())
            is Short        -> ShortMatrix(shape, vectors.map { (it.data as Array<Short>).toList() }.flatten().toShortArray())
            else            -> throw IllegalArgumentException("Basis vectors must be of type Float, Double, Int, or Short")
        }

        complies("Basis vectors must be linearly independent!") { matrix.rank() == vectors.size }
    }

    /**
     * Get a vector from the basis.
     *
     * @param index The index of the vector to get.
     * @return The vector at the given index.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    operator fun get(index: Int) = vectors[index]

    /**
     * Get the dimension (number of vectors) in the basis.
     *
     * @return The dimension in the basis.
     */
    fun dimension(): Int = vectors.size

}
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

package com.github.polyrocketmatt.kmt.common.storage

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a storage for data of type [T].
 *
 * @param T The type of data to store.
 */
interface MutableMemoryStorage<T> : Iterable<T> {

    /**
     * The stored data.
     */
    val data: Any

    /**
     * The size of the stored data.
     */
    var size: Int

    /**
     * Get the data at the specified index.
     *
     * @param i The index of the data to get.
     * @return The data at the specified index.
     */
    operator fun get(i: Int): T

    /**
     * Set the data at the specified index.
     *
     * @param i The index of the data to set.
     * @param value The value to set.
     */
    operator fun set(i: Int, value: T)

    /**
     * Get a copy of the storage.
     *
     * @return A copy of the storage.
     */
    fun copyOf(): MutableMemoryStorage<T>

    /**
     * Get the storage as an array of booleans.
     *
     * @return The storage as an array of booleans.
     */
    fun asBooleanArray(): BooleanArray

    /**
     * Get the storage as an array of doubles.
     *
     * @return The storage as an array of doubles.
     */
    fun asDoubleArray(): DoubleArray

    /**
     * Get the storage as an array of floating-point numbers.
     *
     * @return The storage as an array of floating-point numbers.
     */
    fun asFloatArray(): FloatArray

    /**
     * Get the storage as an array of integers.
     *
     * @return The storage as an array of integers.
     */
    fun asIntArray(): IntArray

    /**
     * Get the storage as an array of shorts.
     *
     * @return The storage as an array of shorts.
     */
    fun asShortArray(): ShortArray

    operator fun plusAssign(other: MutableMemoryStorage<T>)
    operator fun minusAssign(other: MutableMemoryStorage<T>)
    operator fun timesAssign(other: MutableMemoryStorage<T>)
    operator fun divAssign(other: MutableMemoryStorage<T>)

    operator fun plus(other: MutableMemoryStorage<T>): MutableMemoryStorage<T>
    operator fun minus(other: MutableMemoryStorage<T>): MutableMemoryStorage<T>
    operator fun times(other: MutableMemoryStorage<T>): MutableMemoryStorage<T>
    operator fun div(other: MutableMemoryStorage<T>): MutableMemoryStorage<T>
}

abstract class MemoryStorage<T> : MutableMemoryStorage<T> {

    abstract var indices: IntRange
    abstract var lastIndex: Int

    abstract override fun copyOf(): MemoryStorage<T>
    abstract override fun asBooleanArray(): BooleanArray
    abstract override fun asDoubleArray(): DoubleArray
    abstract override fun asFloatArray(): FloatArray
    abstract override fun asIntArray(): IntArray
    abstract override fun asShortArray(): ShortArray
}

class BooleanMemoryStorage(override val data: BooleanArray) : MemoryStorage<Boolean>() {

    override var size: Int = data.size
    override var indices: IntRange = 0 until size
    override var lastIndex: Int = data.lastIndex

    override fun copyOf(): MemoryStorage<Boolean> = BooleanMemoryStorage(data.copyOf())

    override fun asBooleanArray(): BooleanArray = data.copyOf()

    override fun asDoubleArray(): DoubleArray = throw UnsupportedOperationException("Cannot convert BooleanMemoryStorage to DoubleArray")

    override fun asFloatArray(): FloatArray = throw UnsupportedOperationException("Cannot convert BooleanMemoryStorage to FloatArray")

    override fun asIntArray(): IntArray = throw UnsupportedOperationException("Cannot convert BooleanMemoryStorage to IntArray")

    override fun asShortArray(): ShortArray = throw UnsupportedOperationException("Cannot convert BooleanMemoryStorage to ShortArray")

    override fun iterator(): Iterator<Boolean> = data.iterator()

    override fun get(i: Int): Boolean = data[i]
    override fun set(i: Int, value: Boolean) { data[i] = value }

    override operator fun plusAssign(other: MutableMemoryStorage<Boolean>) {
        for (i in indices)
            data[i] = data[i] || other[i]
    }

    override fun minusAssign(other: MutableMemoryStorage<Boolean>) = throw UnsupportedOperationException("Subtraction is not supported for BooleanMemoryStorage")

    override fun timesAssign(other: MutableMemoryStorage<Boolean>) {
        for (i in indices)
            data[i] = data[i] && other[i]
    }

    override fun divAssign(other: MutableMemoryStorage<Boolean>) = throw UnsupportedOperationException("Division is not supported for BooleanMemoryStorage")

    override fun plus(other: MutableMemoryStorage<Boolean>): MutableMemoryStorage<Boolean> {
        val result = BooleanMemoryStorage(data.copyOf())
        result += other
        return result
    }

    override fun minus(other: MutableMemoryStorage<Boolean>): MutableMemoryStorage<Boolean> = throw UnsupportedOperationException("Subtraction is not supported for BooleanMemoryStorage")

    override fun times(other: MutableMemoryStorage<Boolean>): MutableMemoryStorage<Boolean> {
        val result = BooleanMemoryStorage(data.copyOf())
        result *= other
        return result
    }

    override fun div(other: MutableMemoryStorage<Boolean>): MutableMemoryStorage<Boolean> = throw UnsupportedOperationException("Division is not supported for BooleanMemoryStorage")

    override fun toString(): String = data.contentToString()
}

class DoubleMemoryStorage(override val data: DoubleArray) : MemoryStorage<Double>() {

    override var size: Int = data.size
    override var indices: IntRange = 0 until size
    override var lastIndex: Int = data.lastIndex

    override fun copyOf(): MemoryStorage<Double> = DoubleMemoryStorage(data.copyOf())

    override fun asBooleanArray(): BooleanArray = throw UnsupportedOperationException("Cannot convert DoubleMemoryStorage to BooleanArray")

    override fun asDoubleArray(): DoubleArray = data.copyOf()

    override fun asFloatArray(): FloatArray = throw UnsupportedOperationException("Cannot convert DoubleMemoryStorage to FloatArray")

    override fun asIntArray(): IntArray = throw UnsupportedOperationException("Cannot convert DoubleMemoryStorage to IntArray")

    override fun asShortArray(): ShortArray = throw UnsupportedOperationException("Cannot convert DoubleMemoryStorage to ShortArray")

    override fun iterator(): Iterator<Double> = data.iterator()

    override fun get(i: Int): Double = data[i]
    override fun set(i: Int, value: Double) { data[i] = value }

    override operator fun plusAssign(other: MutableMemoryStorage<Double>) {
        for (i in indices)
            data[i] = data[i] + other[i]
    }

    override fun minusAssign(other: MutableMemoryStorage<Double>) {
        for (i in indices)
            data[i] = data[i] - other[i]
    }

    override fun timesAssign(other: MutableMemoryStorage<Double>) {
        for (i in indices)
            data[i] = data[i] - other[i]
    }

    override fun divAssign(other: MutableMemoryStorage<Double>) {
        for (i in indices)
            data[i] = data[i] / other[i]
    }

    override fun plus(other: MutableMemoryStorage<Double>): MutableMemoryStorage<Double> {
        val result = DoubleMemoryStorage(data.copyOf())
        result += other
        return result
    }

    override fun minus(other: MutableMemoryStorage<Double>): MutableMemoryStorage<Double> {
        val result = DoubleMemoryStorage(data.copyOf())
        result -= other
        return result
    }

    override fun times(other: MutableMemoryStorage<Double>): MutableMemoryStorage<Double> {
        val result = DoubleMemoryStorage(data.copyOf())
        result *= other
        return result
    }

    override fun div(other: MutableMemoryStorage<Double>): MutableMemoryStorage<Double> {
        val result = DoubleMemoryStorage(data.copyOf())
        result /= other
        return result
    }

    override fun toString(): String = data.contentToString()
}

class FloatMemoryStorage(override val data: FloatArray) : MemoryStorage<Float>() {

    override var size: Int = data.size
    override var indices: IntRange = 0 until size
    override var lastIndex: Int = data.lastIndex

    override fun copyOf(): MemoryStorage<Float> = FloatMemoryStorage(data.copyOf())

    override fun asBooleanArray(): BooleanArray = throw UnsupportedOperationException("Cannot convert FloatMemoryStorage to BooleanArray")

    override fun asDoubleArray(): DoubleArray = throw UnsupportedOperationException("Cannot convert FloatMemoryStorage to DoubleArray")

    override fun asFloatArray(): FloatArray = data.copyOf()

    override fun asIntArray(): IntArray = throw UnsupportedOperationException("Cannot convert FloatMemoryStorage to IntArray")

    override fun asShortArray(): ShortArray = throw UnsupportedOperationException("Cannot convert FloatMemoryStorage to ShortArray")

    override fun iterator(): Iterator<Float> = data.iterator()

    override fun get(i: Int): Float = data[i]
    override fun set(i: Int, value: Float) { data[i] = value }

    override operator fun plusAssign(other: MutableMemoryStorage<Float>) {
        for (i in indices)
            data[i] = data[i] + other[i]
    }

    override fun minusAssign(other: MutableMemoryStorage<Float>) {
        for (i in indices)
            data[i] = data[i] - other[i]
    }

    override fun timesAssign(other: MutableMemoryStorage<Float>) {
        for (i in indices)
            data[i] = data[i] - other[i]
    }

    override fun divAssign(other: MutableMemoryStorage<Float>) {
        for (i in indices)
            data[i] = data[i] / other[i]
    }

    override fun plus(other: MutableMemoryStorage<Float>): MutableMemoryStorage<Float> {
        val result = FloatMemoryStorage(data.copyOf())
        result += other
        return result
    }

    override fun minus(other: MutableMemoryStorage<Float>): MutableMemoryStorage<Float> {
        val result = FloatMemoryStorage(data.copyOf())
        result -= other
        return result
    }

    override fun times(other: MutableMemoryStorage<Float>): MutableMemoryStorage<Float> {
        val result = FloatMemoryStorage(data.copyOf())
        result *= other
        return result
    }

    override fun div(other: MutableMemoryStorage<Float>): MutableMemoryStorage<Float> {
        val result = FloatMemoryStorage(data.copyOf())
        result /= other
        return result
    }

    override fun toString(): String = data.contentToString()
}

class IntMemoryStorage(override val data: IntArray) : MemoryStorage<Int>() {

    override var size: Int = data.size
    override var indices: IntRange = 0 until size
    override var lastIndex: Int = data.lastIndex

    override fun copyOf(): MemoryStorage<Int> = IntMemoryStorage(data.copyOf())

    override fun asBooleanArray(): BooleanArray = throw UnsupportedOperationException("Cannot convert IntMemoryStorage to BooleanArray")

    override fun asDoubleArray(): DoubleArray = throw UnsupportedOperationException("Cannot convert IntMemoryStorage to DoubleArray")

    override fun asFloatArray(): FloatArray = throw UnsupportedOperationException("Cannot convert IntMemoryStorage to FloatArray")

    override fun asIntArray(): IntArray = data.copyOf()

    override fun asShortArray(): ShortArray = throw UnsupportedOperationException("Cannot convert IntMemoryStorage to ShortArray")

    override fun iterator(): Iterator<Int> = data.iterator()

    override fun get(i: Int): Int = data[i]
    override fun set(i: Int, value: Int) { data[i] = value }

    override operator fun plusAssign(other: MutableMemoryStorage<Int>) {
        for (i in indices)
            data[i] = data[i] + other[i]
    }

    override fun minusAssign(other: MutableMemoryStorage<Int>) {
        for (i in indices)
            data[i] = data[i] - other[i]
    }

    override fun timesAssign(other: MutableMemoryStorage<Int>) {
        for (i in indices)
            data[i] = data[i] - other[i]
    }

    override fun divAssign(other: MutableMemoryStorage<Int>) {
        for (i in indices)
            data[i] = data[i] / other[i]
    }

    override fun plus(other: MutableMemoryStorage<Int>): MutableMemoryStorage<Int> {
        val result = IntMemoryStorage(data.copyOf())
        result += other
        return result
    }

    override fun minus(other: MutableMemoryStorage<Int>): MutableMemoryStorage<Int> {
        val result = IntMemoryStorage(data.copyOf())
        result -= other
        return result
    }

    override fun times(other: MutableMemoryStorage<Int>): MutableMemoryStorage<Int> {
        val result = IntMemoryStorage(data.copyOf())
        result *= other
        return result
    }

    override fun div(other: MutableMemoryStorage<Int>): MutableMemoryStorage<Int> {
        val result = IntMemoryStorage(data.copyOf())
        result /= other
        return result
    }

    override fun toString(): String = data.contentToString()
}

class ShortMemoryStorage(override val data: ShortArray) : MemoryStorage<Short>() {

    override var size: Int = data.size
    override var indices: IntRange = 0 until size
    override var lastIndex: Int = data.lastIndex

    override fun copyOf(): MemoryStorage<Short> = ShortMemoryStorage(data.copyOf())

    override fun asBooleanArray(): BooleanArray = throw UnsupportedOperationException("Cannot convert ShortMemoryStorage to BooleanArray")

    override fun asDoubleArray(): DoubleArray = throw UnsupportedOperationException("Cannot convert ShortMemoryStorage to DoubleArray")

    override fun asFloatArray(): FloatArray = throw UnsupportedOperationException("Cannot convert ShortMemoryStorage to FloatArray")

    override fun asIntArray(): IntArray = throw UnsupportedOperationException("Cannot convert ShortMemoryStorage to IntArray")

    override fun asShortArray(): ShortArray = data.copyOf()

    override fun iterator(): Iterator<Short> = data.iterator()

    override fun get(i: Int): Short = data[i]
    override fun set(i: Int, value: Short) { data[i] = value }

    override operator fun plusAssign(other: MutableMemoryStorage<Short>) {
        for (i in indices)
            data[i] = (data[i] + other[i]).toShort()
    }

    override fun minusAssign(other: MutableMemoryStorage<Short>) {
        for (i in indices)
            data[i] = (data[i] - other[i]).toShort()
    }

    override fun timesAssign(other: MutableMemoryStorage<Short>) {
        for (i in indices)
            data[i] = (data[i] - other[i]).toShort()
    }

    override fun divAssign(other: MutableMemoryStorage<Short>) {
        for (i in indices)
            data[i] = (data[i] / other[i]).toShort()
    }

    override fun plus(other: MutableMemoryStorage<Short>): MutableMemoryStorage<Short> {
        val result = ShortMemoryStorage(data.copyOf())
        result += other
        return result
    }

    override fun minus(other: MutableMemoryStorage<Short>): MutableMemoryStorage<Short> {
        val result = ShortMemoryStorage(data.copyOf())
        result -= other
        return result
    }

    override fun times(other: MutableMemoryStorage<Short>): MutableMemoryStorage<Short> {
        val result = ShortMemoryStorage(data.copyOf())
        result *= other
        return result
    }

    override fun div(other: MutableMemoryStorage<Short>): MutableMemoryStorage<Short> {
        val result = ShortMemoryStorage(data.copyOf())
        result /= other
        return result
    }

    override fun toString(): String = data.contentToString()
}

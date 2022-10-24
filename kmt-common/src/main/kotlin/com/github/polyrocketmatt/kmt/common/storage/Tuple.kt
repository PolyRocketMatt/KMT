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
 * Represents a tuple of random size.
 *
 * @param T The type of the data to be stored
 * @param data The data to be stored
 */
open class Tuple<T>(final override val data: Array<T>) : MemoryStorage<T>() {

    override var size: Int = data.size
    override var indices: IntRange = data.indices
    override var lastIndex: Int = data.lastIndex

    override fun copyOf(): MemoryStorage<T> = Tuple(data.copyOf())
    override fun iterator(): Iterator<T> = data.iterator()

    override fun asBooleanArray(): BooleanArray = throw UnsupportedOperationException("Cannot convert BooleanMemoryStorage to BooleanArray")

    override fun asDoubleArray(): DoubleArray = throw UnsupportedOperationException("Cannot convert BooleanMemoryStorage to DoubleArray")

    override fun asFloatArray(): FloatArray = throw UnsupportedOperationException("Cannot convert BooleanMemoryStorage to FloatArray")

    override fun asIntArray(): IntArray = throw UnsupportedOperationException("Cannot convert BooleanMemoryStorage to IntArray")

    override fun asShortArray(): ShortArray = throw UnsupportedOperationException("Cannot convert BooleanMemoryStorage to ShortArray")

    override fun get(i: Int): T = data[i]
    override fun set(i: Int, value: T) { data[i] = value }

    open override fun plusAssign(other: MutableMemoryStorage<T>) = throw UnsupportedOperationException("Addition is not supported for Tuple<${data[0]!!::class.simpleName}>")

    open override fun minusAssign(other: MutableMemoryStorage<T>) = throw UnsupportedOperationException("Subtraction is not supported for Tuple<${data[0]!!::class.simpleName}>")

    open override fun timesAssign(other: MutableMemoryStorage<T>) = throw UnsupportedOperationException("Multiplication is not supported for Tuple<${data[0]!!::class.simpleName}>")

    open override fun divAssign(other: MutableMemoryStorage<T>) = throw UnsupportedOperationException("Division is not supported for Tuple<${data[0]!!::class.simpleName}>")

    open override fun plus(other: MutableMemoryStorage<T>): MutableMemoryStorage<T> = throw UnsupportedOperationException("Addition is not supported for Tuple<${data[0]!!::class.simpleName}>")

    open override fun minus(other: MutableMemoryStorage<T>): MutableMemoryStorage<T> = throw UnsupportedOperationException("Subtraction is not supported for Tuple<${data[0]!!::class.simpleName}>")

    open override fun times(other: MutableMemoryStorage<T>): MutableMemoryStorage<T> = throw UnsupportedOperationException("Multiplication is not supported for Tuple<${data[0]!!::class.simpleName}>")

    open override fun div(other: MutableMemoryStorage<T>): MutableMemoryStorage<T> = throw UnsupportedOperationException("Division is not supported for Tuple<${data[0]!!::class.simpleName}>")

    override fun toString(): String = data.contentToString()
}

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a tuple of 2 elements.
 *
 * @param T The type of the data to be stored.
 */
open class Tuple2<T>(data: Array<T>) : Tuple<T>(data) {

    init { require(data.size == 2) { "Tuple2 must have 2 elements" } }

    var x: T = data[0]
        set(value) {
            field = value
            data[0] = value
        }
    var y: T = data[1]
        set(value) {
            field = value
            data[1] = value
        }
}

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a tuple of 3 elements.
 *
 * @param T The type of the data to be stored.
 */
open class Tuple3<T>(data: Array<T>) : Tuple<T>(data) {

    init { require(data.size == 3) { "Tuple2 must have 2 elements" } }

    var x: T = data[0]
        set(value) {
            field = value
            data[0] = value
        }
    var y: T = data[1]
        set(value) {
            field = value
            data[1] = value
        }
    var z: T = data[2]
        set(value) {
            field = value
            data[2] = value
        }
}

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a tuple of 4 elements.
 *
 * @param T The type of the data to be stored.
 */
open class Tuple4<T>(data: Array<T>) : Tuple<T>(data) {

    init { require(data.size == 4) { "Tuple2 must have 2 elements" } }

    var x: T = data[0]
        set(value) {
            field = value
            data[0] = value
        }
    var y: T = data[1]
        set(value) {
            field = value
            data[1] = value
        }
    var z: T = data[2]
        set(value) {
            field = value
            data[2] = value
        }
    var w: T = data[3]
        set(value) {
            field = value
            data[3] = value
        }
}

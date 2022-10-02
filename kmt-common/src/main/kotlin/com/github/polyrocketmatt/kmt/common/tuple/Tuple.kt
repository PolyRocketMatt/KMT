package com.github.polyrocketmatt.kmt.common.tuple

import com.github.polyrocketmatt.kmt.common.storage.ImmutableMemoryStorage
import com.github.polyrocketmatt.kmt.common.storage.MemoryStorage

open class Tuple<N>(final override val data: Array<N>) : MemoryStorage<N>() {

    override var size: Int = data.size
    override var indices: IntRange = data.indices
    override var lastIndex: Int = data.lastIndex

    override fun copyOf(): MemoryStorage<N> = Tuple(data.copyOf())
    override fun iterator(): Iterator<N> = data.iterator()

    override fun asBooleanArray(): BooleanArray = throw UnsupportedOperationException("Cannot convert BooleanMemoryStorage to BooleanArray")

    override fun asDoubleArray(): DoubleArray = throw UnsupportedOperationException("Cannot convert BooleanMemoryStorage to DoubleArray")

    override fun asFloatArray(): FloatArray = throw UnsupportedOperationException("Cannot convert BooleanMemoryStorage to FloatArray")

    override fun asIntArray(): IntArray = throw UnsupportedOperationException("Cannot convert BooleanMemoryStorage to IntArray")

    override fun asShortArray(): ShortArray = throw UnsupportedOperationException("Cannot convert BooleanMemoryStorage to ShortArray")

    override fun get(i: Int): N = data[i]
    override fun set(i: Int, value: N) { data[i] = value }

    override fun plusAssign(other: ImmutableMemoryStorage<N>) = throw UnsupportedOperationException("Addition is not supported for Tuple<${data[0]!!::class.simpleName}>")

    override fun minusAssign(other: ImmutableMemoryStorage<N>) = throw UnsupportedOperationException("Subtraction is not supported for Tuple<${data[0]!!::class.simpleName}>")

    override fun timesAssign(other: ImmutableMemoryStorage<N>) = throw UnsupportedOperationException("Multiplication is not supported for Tuple<${data[0]!!::class.simpleName}>")

    override fun divAssign(other: ImmutableMemoryStorage<N>) = throw UnsupportedOperationException("Division is not supported for Tuple<${data[0]!!::class.simpleName}>")
}
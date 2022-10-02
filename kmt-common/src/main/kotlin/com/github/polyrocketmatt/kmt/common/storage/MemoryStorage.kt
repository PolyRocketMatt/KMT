package com.github.polyrocketmatt.kmt.common.storage

interface MutableMemoryStorage<T> : Iterable<T> {

    val data: Any
    var size: Int

    operator fun get(i: Int): T
    operator fun set(i: Int, value: T)

    fun copyOf(): MutableMemoryStorage<T>

    fun asBooleanArray(): BooleanArray

    fun asDoubleArray(): DoubleArray

    fun asFloatArray(): FloatArray

    fun asIntArray(): IntArray

    fun asShortArray(): ShortArray

    operator fun plusAssign(other: MutableMemoryStorage<T>)
    operator fun minusAssign(other: MutableMemoryStorage<T>)
    operator fun timesAssign(other: MutableMemoryStorage<T>)
    operator fun divAssign(other: MutableMemoryStorage<T>)

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

}

class FloatMemoryStorage(override val data: FloatArray) : MemoryStorage<Float>() {

    override var size: Int = data.size
    override var indices: IntRange = 0 until size
    override var lastIndex: Int = data.lastIndex

    override fun copyOf(): MemoryStorage<Float> = FloatMemoryStorage(data.copyOf())

    override fun asBooleanArray(): BooleanArray = throw UnsupportedOperationException("Cannot convert DoubleMemoryStorage to BooleanArray")

    override fun asDoubleArray(): DoubleArray = throw UnsupportedOperationException("Cannot convert DoubleMemoryStorage to DoubleArray")

    override fun asFloatArray(): FloatArray = data.copyOf()

    override fun asIntArray(): IntArray = throw UnsupportedOperationException("Cannot convert DoubleMemoryStorage to IntArray")

    override fun asShortArray(): ShortArray = throw UnsupportedOperationException("Cannot convert DoubleMemoryStorage to ShortArray")

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

}

class IntMemoryStorage(override val data: IntArray) : MemoryStorage<Int>() {

    override var size: Int = data.size
    override var indices: IntRange = 0 until size
    override var lastIndex: Int = data.lastIndex

    override fun copyOf(): MemoryStorage<Int> = IntMemoryStorage(data.copyOf())

    override fun asBooleanArray(): BooleanArray = throw UnsupportedOperationException("Cannot convert DoubleMemoryStorage to BooleanArray")

    override fun asDoubleArray(): DoubleArray = throw UnsupportedOperationException("Cannot convert DoubleMemoryStorage to DoubleArray")

    override fun asFloatArray(): FloatArray = throw UnsupportedOperationException("Cannot convert DoubleMemoryStorage to FloatArray")

    override fun asIntArray(): IntArray = data.copyOf()

    override fun asShortArray(): ShortArray = throw UnsupportedOperationException("Cannot convert DoubleMemoryStorage to ShortArray")

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

}

class ShortMemoryStorage(override val data: ShortArray) : MemoryStorage<Short>() {

    override var size: Int = data.size
    override var indices: IntRange = 0 until size
    override var lastIndex: Int = data.lastIndex

    override fun copyOf(): MemoryStorage<Short> = ShortMemoryStorage(data.copyOf())

    override fun asBooleanArray(): BooleanArray = throw UnsupportedOperationException("Cannot convert DoubleMemoryStorage to BooleanArray")

    override fun asDoubleArray(): DoubleArray = throw UnsupportedOperationException("Cannot convert DoubleMemoryStorage to DoubleArray")

    override fun asFloatArray(): FloatArray = throw UnsupportedOperationException("Cannot convert DoubleMemoryStorage to FloatArray")

    override fun asIntArray(): IntArray = throw UnsupportedOperationException("Cannot convert DoubleMemoryStorage to IntArray")

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

}
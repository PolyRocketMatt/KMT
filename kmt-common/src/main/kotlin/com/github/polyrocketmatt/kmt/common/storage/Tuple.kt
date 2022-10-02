package com.github.polyrocketmatt.kmt.common.storage

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

    open override fun plusAssign(other: MutableMemoryStorage<N>) = throw UnsupportedOperationException("Addition is not supported for Tuple<${data[0]!!::class.simpleName}>")

    open override fun minusAssign(other: MutableMemoryStorage<N>) = throw UnsupportedOperationException("Subtraction is not supported for Tuple<${data[0]!!::class.simpleName}>")

    open override fun timesAssign(other: MutableMemoryStorage<N>) = throw UnsupportedOperationException("Multiplication is not supported for Tuple<${data[0]!!::class.simpleName}>")

    open override fun divAssign(other: MutableMemoryStorage<N>) = throw UnsupportedOperationException("Division is not supported for Tuple<${data[0]!!::class.simpleName}>")
}

open class Tuple2<T>(data: Array<T>) : Tuple<T>(data) {

    init { require(data.size == 2) { "Tuple2 must have 2 elements" } }

    var x: T = data[0]
    var y: T = data[1]

}

open class Tuple3<T>(data: Array<T>) : Tuple<T>(data) {

    init { require(data.size == 3) { "Tuple2 must have 2 elements" } }

    var x: T = data[0]
    var y: T = data[1]
    var z: T = data[2]

}

open class Tuple4<T>(data: Array<T>) : Tuple<T>(data) {

    init { require(data.size == 4) { "Tuple2 must have 2 elements" } }

    var x: T = data[0]
    var y: T = data[1]
    var z: T = data[2]
    var w: T = data[3]

}
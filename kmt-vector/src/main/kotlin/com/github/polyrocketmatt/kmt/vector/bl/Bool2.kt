package com.github.polyrocketmatt.kmt.vector.bl

import com.github.polyrocketmatt.kmt.common.storage.Tuple2
import com.github.polyrocketmatt.kmt.vector.Swizzle2
import com.github.polyrocketmatt.kmt.vector.Vector
import com.github.polyrocketmatt.kmt.vector.db.Double2
import com.github.polyrocketmatt.kmt.vector.db.DoubleVector
import com.github.polyrocketmatt.kmt.vector.fl.Float2
import com.github.polyrocketmatt.kmt.vector.it.Int2
import com.github.polyrocketmatt.kmt.vector.sh.Short2

class Bool2(x: Boolean, y: Boolean) : Tuple2<Boolean>(arrayOf(x, y)), BooleanVector, Swizzle2 {

    constructor() : this(false, false)
    constructor(other: Bool2) : this(other.x, other.y)
    constructor(x: Boolean) : this(x, x)

    operator fun plus(other: Bool2): Bool2 = Bool2(x || other.x, y || other.y)
    operator fun times(other: Bool2): Bool2 = Bool2(x && other.x, y && other.y)

    override fun plus(value: Boolean): Bool2 = Bool2(x || value, y || value)
    override fun minus(value: Boolean): Bool2 = throw UnsupportedOperationException("Cannot subtract a boolean from a boolean vector")
    override fun times(value: Boolean): Bool2 = Bool2(x && value, y && value)
    override fun div(value: Boolean): Bool2 = throw UnsupportedOperationException("Cannot divide a boolean vector by a boolean")

    override fun plusAssign(value: Boolean) { x = x || value; y = y || value }
    override fun minusAssign(value: Boolean) = throw UnsupportedOperationException("Cannot subtract a boolean from a boolean vector")
    override fun timesAssign(value: Boolean) { x = x && value; y = y && value }
    override fun divAssign(value: Boolean) = throw UnsupportedOperationException("Cannot divide a boolean vector by a boolean")

    override fun length(): Float = throw UnsupportedOperationException("Cannot get length of a boolean vector")
    override fun lengthDouble(): Double = throw UnsupportedOperationException("Cannot get length of a boolean vector")
    override fun lengthSq(): Boolean = throw UnsupportedOperationException("Cannot get squared length of a boolean vector")
    override fun max(): Boolean = if (x) true else y
    override fun min(): Boolean = if (!x) false else !y
    override fun sum(): Boolean = x || y
    override fun diff(): Boolean = throw UnsupportedOperationException("Cannot get difference of a boolean vector")
    override fun product(): Boolean = x && y
    override fun div(): Boolean = throw UnsupportedOperationException("Cannot get division of a boolean vector")
    override fun normalized(): Vector<Float> = throw UnsupportedOperationException("Cannot normalize a boolean vector")

    override fun dist(other: Vector<Boolean>): Float = throw UnsupportedOperationException("Cannot get distance between boolean vectors")
    override fun distSq(other: Vector<Boolean>): Float = throw UnsupportedOperationException("Cannot get squared distance between boolean vectors")
    override fun dot(other: Vector<Boolean>): Float = throw UnsupportedOperationException("Cannot get dot product of boolean vectors")
    override fun sdot(): Boolean = throw UnsupportedOperationException("Cannot get dot product of boolean vector with itself")
    override fun unaryMinus(): Bool2 = Bool2(!x, !y)

    override fun abs(): Bool2 = Bool2(x, y)
    override fun avg(): Float = throw UnsupportedOperationException("Cannot get average of a boolean vector")
    override fun min(other: Vector<Boolean>): Bool2 {
        return if (other is Bool2)
            Bool2(x && other.x, y && other.y)
        else
            throw IllegalArgumentException("Other must be a Bool2")
    }
    override fun max(other: Vector<Boolean>): Bool2 {
        return if (other is Bool2)
            Bool2(x || other.x, y || other.y)
        else
            throw IllegalArgumentException("Other must be a Bool2")
    }
    override fun isIn(min: Boolean, max: Boolean): Vector<Boolean> = throw UnsupportedOperationException("Cannot check if a boolean vector is in a range")
    override fun intPow(n: Int): Vector<Boolean> = throw UnsupportedOperationException("Cannot raise a boolean vector to a power")

    override fun sin(): DoubleVector = throw UnsupportedOperationException("Cannot get sine of a boolean vector")
    override fun cos(): DoubleVector = throw UnsupportedOperationException("Cannot get cosine of a boolean vector")
    override fun tan(): DoubleVector = throw UnsupportedOperationException("Cannot get tangent of a boolean vector")

    override fun asFloat(): Float2 = Float2(if (x) 1f else 0f, if (y) 1f else 0f)
    override fun asDouble(): Double2 = Double2(if (x) 1.0 else 0.0, if (y) 1.0 else 0.0)
    override fun asInt(): Int2 = Int2(if (x) 1 else 0, if (y) 1 else 0)
    override fun asShort(): Short2 = Short2(if (x) 1 else 0, if (y) 1 else 0)

    override fun xy(): Bool2 = Bool2(x, y)
    override fun yx(): Bool2 = Bool2(y, x)
    override fun xx(): Bool2 = Bool2(x, x)
    override fun yy(): Bool2 = Bool2(y, y)

    override fun copyOf(): Bool2 = Bool2(x, y)
}

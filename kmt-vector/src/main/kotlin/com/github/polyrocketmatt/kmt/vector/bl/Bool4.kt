package com.github.polyrocketmatt.kmt.vector.bl

import com.github.polyrocketmatt.kmt.common.storage.Tuple4
import com.github.polyrocketmatt.kmt.common.utils.complies
import com.github.polyrocketmatt.kmt.matrix.BooleanMatrix
import com.github.polyrocketmatt.kmt.matrix.Matrix
import com.github.polyrocketmatt.kmt.matrix.toMatrix
import com.github.polyrocketmatt.kmt.vector.Swizzle4
import com.github.polyrocketmatt.kmt.vector.Vector
import com.github.polyrocketmatt.kmt.vector.db.Double4
import com.github.polyrocketmatt.kmt.vector.db.DoubleVector
import com.github.polyrocketmatt.kmt.vector.fl.Float4
import com.github.polyrocketmatt.kmt.vector.it.Int4
import com.github.polyrocketmatt.kmt.vector.sh.Short4
import java.lang.UnsupportedOperationException

fun BooleanMatrix.toBool4(): Bool4 {
    complies("Cannot create a Bool4 from a BooleanMatrix with ${this.data.size} elements!") { this.data.size == 4 }
    return Bool4(this.data[0], this.data[1], this.data[2], this.data[3])
}

class Bool4(x: Boolean, y: Boolean, z: Boolean, w: Boolean) : Tuple4<Boolean>(arrayOf(x, y, z, w)), BooleanVector, Swizzle4 {

    constructor() : this(false, false, false, false)
    constructor(other: Bool4) : this(other.x, other.y, other.z, other.w)
    constructor(x: Boolean) : this(x, x, x, x)

    operator fun plus(other: Bool4): Bool4 = Bool4(x || other.x, y || other.y, z || other.z, w || other.w)
    operator fun times(other: Bool4): Bool4 = Bool4(x && other.x, y && other.y, z && other.z, w && other.w)

    override fun plus(value: Boolean): Bool4 = Bool4(x || value, y || value, z || value, w || value)

    override fun minus(value: Boolean): Bool4 = throw UnsupportedOperationException("Cannot subtract a boolean from a boolean vector")

    override fun times(value: Boolean): Bool4 = Bool4(x && value, y && value, z && value, w && value)

    override fun div(value: Boolean): Bool4 = throw UnsupportedOperationException("Cannot divide a boolean from a boolean vector")

    override fun plusAssign(value: Boolean) { x = x || value; y = y || value; z = z || value; w = w || value }

    override fun minusAssign(value: Boolean) = throw UnsupportedOperationException("Cannot subtract a boolean from a boolean vector")

    override fun timesAssign(value: Boolean) { x = x && value; y = y && value; z = z && value; w = w && value }
    override fun divAssign(value: Boolean) = throw UnsupportedOperationException("Cannot divide a boolean from a boolean vector")

    override fun length(): Float = throw UnsupportedOperationException("Cannot get length of a boolean vector")
    override fun lengthDouble(): Double = throw UnsupportedOperationException("Cannot get length of a boolean vector")
    override fun lengthSq(): Boolean = throw UnsupportedOperationException("Cannot get squared length of a boolean vector")
    override fun max(): Boolean = if (x) true else if (y) true else if (z) true else w
    override fun min(): Boolean = if (!x) false else if (!y) false else if (!z) false else !w
    override fun sum(): Boolean = x || y || z || w
    override fun diff(): Boolean = throw UnsupportedOperationException("Cannot get difference of a boolean vector")
    override fun product(): Boolean = x && y && z && w
    override fun div(): Boolean = throw UnsupportedOperationException("Cannot get division of a boolean vector")
    override fun normalized(): Vector<Float> = throw UnsupportedOperationException("Cannot normalize a boolean vector")

    override fun dist(other: Vector<Boolean>): Float = throw UnsupportedOperationException("Cannot get distance between boolean vectors")
    override fun distSq(other: Vector<Boolean>): Float = throw UnsupportedOperationException("Cannot get squared distance between boolean vectors")
    override fun dot(other: Vector<Boolean>): Float = throw UnsupportedOperationException("Cannot get dot product of boolean vectors")
    override fun sdot(): Boolean = throw UnsupportedOperationException("Cannot get dot product of boolean vector with itself")
    override fun unaryMinus(): Bool4 = Bool4(!x, !y, !z, !w)

    override fun abs(): Bool4 = Bool4(x, y, z, w)
    override fun avg(): Float = throw UnsupportedOperationException("Cannot get average of a boolean vector")
    override fun min(other: Vector<Boolean>): Bool4 {
        return if (other is Bool4)
            Bool4(x && other.x, y && other.y, z && other.z, w && other.w)
        else
            throw IllegalArgumentException("Other must be a Bool4")
    }
    override fun max(other: Vector<Boolean>): Bool4 {
        return if (other is Bool4)
            Bool4(x || other.x, y || other.y, z || other.z, w || other.w)
        else
            throw IllegalArgumentException("Other must be a Bool4")
    }
    override fun isIn(min: Boolean, max: Boolean): Vector<Boolean> = throw UnsupportedOperationException("Cannot check if a boolean vector is in a range")
    override fun intPow(n: Int): Vector<Boolean> = throw UnsupportedOperationException("Cannot raise a boolean vector to a power")

    override fun sin(): DoubleVector = throw UnsupportedOperationException("Cannot get sine of a boolean vector")
    override fun cos(): DoubleVector = throw UnsupportedOperationException("Cannot get cosine of a boolean vector")
    override fun tan(): DoubleVector = throw UnsupportedOperationException("Cannot get tangent of a boolean vector")

    override fun asFloat(): Float4 = Float4(if (x) 1f else 0f, if (y) 1f else 0f, if (z) 1f else 0f, if (w) 1f else 0f)
    override fun asDouble(): Double4 = Double4(if (x) 1.0 else 0.0, if (y) 1.0 else 0.0, if (z) 1.0 else 0.0, if (w) 1.0 else 0.0)
    override fun asInt(): Int4 = Int4(if (x) 1 else 0, if (y) 1 else 0, if (z) 1 else 0, if (w) 1 else 0)
    override fun asShort(): Short4 = Short4(if (x) 1 else 0, if (y) 1 else 0, if (z) 1 else 0, if (w) 1 else 0)
    override fun asRowMatrix(): BooleanMatrix = data.toMatrix(intArrayOf(1, 4))
    override fun asColumnMatrix(): BooleanMatrix = data.toMatrix(intArrayOf(4, 1))

    override fun xy(): Bool2 = Bool2(x, y)
    override fun xz(): Bool2 = Bool2(x, z)
    override fun xw(): Bool2 = Bool2(x, w)
    override fun yx(): Bool2 = Bool2(y, x)
    override fun yz(): Bool2 = Bool2(y, z)
    override fun yw(): Bool2 = Bool2(y, w)
    override fun zx(): Bool2 = Bool2(z, x)
    override fun zy(): Bool2 = Bool2(z, y)
    override fun zw(): Bool2 = Bool2(z, w)
    override fun wx(): Bool2 = Bool2(w, x)
    override fun wy(): Bool2 = Bool2(w, y)
    override fun wz(): Bool2 = Bool2(w, z)
    override fun xyz(): Bool3 = Bool3(x, y, z)
    override fun xyw(): Bool3 = Bool3(x, y, w)
    override fun xzy(): Bool3 = Bool3(x, z, y)
    override fun xzw(): Bool3 = Bool3(x, z, w)
    override fun xwy(): Bool3 = Bool3(x, w, y)
    override fun xwz(): Bool3 = Bool3(x, w, z)
    override fun yxz(): Bool3 = Bool3(y, x, z)
    override fun yxw(): Bool3 = Bool3(y, x, w)
    override fun yzx(): Bool3 = Bool3(y, z, x)
    override fun yzw(): Bool3 = Bool3(y, z, w)
    override fun ywx(): Bool3 = Bool3(y, w, x)
    override fun ywz(): Bool3 = Bool3(y, w, z)
    override fun zxy(): Bool3 = Bool3(z, x, y)
    override fun zxw(): Bool3 = Bool3(z, x, w)
    override fun zyx(): Bool3 = Bool3(z, y, x)
    override fun zyw(): Bool3 = Bool3(z, y, w)
    override fun zwx(): Bool3 = Bool3(z, w, x)
    override fun zwy(): Bool3 = Bool3(z, w, y)
    override fun wxy(): Bool3 = Bool3(w, x, y)
    override fun wxz(): Bool3 = Bool3(w, x, z)
    override fun wyx(): Bool3 = Bool3(w, y, x)
    override fun wyz(): Bool3 = Bool3(w, y, z)
    override fun wzx(): Bool3 = Bool3(w, z, x)
    override fun wzy(): Bool3 = Bool3(w, z, y)
    override fun xyzw(): Bool4 = this
    override fun xywz(): Bool4 = Bool4(x, y, w, z)
    override fun xzyw(): Bool4 = Bool4(x, z, y, w)
    override fun xzwy(): Bool4 = Bool4(x, z, w, y)
    override fun xwyz(): Bool4 = Bool4(x, w, y, z)
    override fun xwzy(): Bool4 = Bool4(x, w, z, y)
    override fun yxzw(): Bool4 = Bool4(y, x, z, w)
    override fun yxwz(): Bool4 = Bool4(y, x, w, z)
    override fun yzxw(): Bool4 = Bool4(y, z, x, w)
    override fun yzwx(): Bool4 = Bool4(y, z, w, x)
    override fun ywxz(): Bool4 = Bool4(y, w, x, z)
    override fun ywzx(): Bool4 = Bool4(y, w, z, x)
    override fun zxyw(): Bool4 = Bool4(z, x, y, w)
    override fun zxwy(): Bool4 = Bool4(z, x, w, y)
    override fun zyxw(): Bool4 = Bool4(z, y, x, w)
    override fun zywx(): Bool4 = Bool4(z, y, w, x)
    override fun zwxy(): Bool4 = Bool4(z, w, x, y)
    override fun zwyx(): Bool4 = Bool4(z, w, y, x)
    override fun wxyz(): Bool4 = Bool4(w, x, y, z)
    override fun wxzy(): Bool4 = Bool4(w, x, z, y)
    override fun wyxz(): Bool4 = Bool4(w, y, x, z)
    override fun wyzx(): Bool4 = Bool4(w, y, z, x)
    override fun wzxy(): Bool4 = Bool4(w, z, x, y)
    override fun wzyx(): Bool4 = Bool4(w, z, y, x)
    override fun xxxx(): Bool4 = Bool4(x, x, x, x)
    override fun yyyy(): Bool4 = Bool4(y, y, y, y)
    override fun zzzz(): Bool4 = Bool4(z, z, z, z)
    override fun wwww(): Bool4 = Bool4(w, w, w, w)

    override fun copyOf(): Bool4 = Bool4(x, y, z, w)
}

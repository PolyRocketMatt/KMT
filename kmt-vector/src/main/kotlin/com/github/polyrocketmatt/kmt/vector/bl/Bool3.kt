package com.github.polyrocketmatt.kmt.vector.bl

import com.github.polyrocketmatt.kmt.common.storage.Tuple3
import com.github.polyrocketmatt.kmt.common.utils.complies
import com.github.polyrocketmatt.kmt.matrix.BooleanMatrix
import com.github.polyrocketmatt.kmt.matrix.DoubleMatrix
import com.github.polyrocketmatt.kmt.matrix.toMatrix
import com.github.polyrocketmatt.kmt.vector.Swizzle3
import com.github.polyrocketmatt.kmt.vector.Vector
import com.github.polyrocketmatt.kmt.vector.db.Double3
import com.github.polyrocketmatt.kmt.vector.db.DoubleVector
import com.github.polyrocketmatt.kmt.vector.fl.Float3
import com.github.polyrocketmatt.kmt.vector.it.Int3
import com.github.polyrocketmatt.kmt.vector.sh.Short3
import java.lang.UnsupportedOperationException

fun BooleanMatrix.toBool3(): Bool3 {
    complies("Cannot create a Bool3 from a BooleanMatrix with ${this.data.size} elements!") { this.data.size == 3 }
    return Bool3(this.data[0], this.data[1], this.data[2])
}

class Bool3(x: Boolean, y: Boolean, z: Boolean) : Tuple3<Boolean>(arrayOf(x, y, z)), BooleanVector, Swizzle3 {

    constructor() : this(false, false, false)
    constructor(other: Bool3) : this(other.x, other.y, other.z)
    constructor(x: Boolean) : this(x, x, x)

    operator fun plus(other: Bool3) = Bool3(x || other.x, y || other.y, z || other.z)
    operator fun times(other: Bool3) = Bool3(x && other.x, y && other.y, z && other.z)

    override fun plus(value: Boolean): Bool3 = Bool3(x || value, y || value, z || value)
    override fun minus(value: Boolean): Bool3 = throw UnsupportedOperationException("Cannot subtract a boolean from a boolean vector")
    override fun times(value: Boolean): Bool3 = Bool3(x && value, y && value, z && value)
    override fun div(value: Boolean): Bool3 = throw UnsupportedOperationException("Cannot divide a boolean vector by a boolean")

    override fun plusAssign(value: Boolean) { x = x || value; y = y || value; z = z || value }
    override fun minusAssign(value: Boolean) = throw UnsupportedOperationException("Cannot subtract a boolean from a boolean vector")
    override fun timesAssign(value: Boolean) { x = x && value; y = y && value; z = z && value }
    override fun divAssign(value: Boolean) = throw UnsupportedOperationException("Cannot divide a boolean vector by a boolean")

    override fun length(): Float = throw UnsupportedOperationException("Cannot get length of a boolean vector")
    override fun lengthDouble(): Double = throw UnsupportedOperationException("Cannot get length of a boolean vector")
    override fun lengthSq(): Boolean = throw UnsupportedOperationException("Cannot get squared length of a boolean vector")
    override fun max(): Boolean = if (x) true else if (y) true else z
    override fun min(): Boolean = if (!x) false else if (!y) false else !z
    override fun sum(): Boolean = x || y || z
    override fun diff(): Boolean = throw UnsupportedOperationException("Cannot get difference of a boolean vector")
    override fun product(): Boolean = x && y && z
    override fun div(): Boolean = throw UnsupportedOperationException("Cannot get division of a boolean vector")
    override fun normalized(): Vector<Float> = throw UnsupportedOperationException("Cannot normalize a boolean vector")

    override fun dist(other: Vector<Boolean>): Float = throw UnsupportedOperationException("Cannot get distance between boolean vectors")
    override fun distSq(other: Vector<Boolean>): Float = throw UnsupportedOperationException("Cannot get squared distance between boolean vectors")
    override fun dot(other: Vector<Boolean>): Float = throw UnsupportedOperationException("Cannot get dot product of boolean vectors")
    override fun sdot(): Boolean = throw UnsupportedOperationException("Cannot get dot product of boolean vector with itself")
    override fun unaryMinus(): Bool3 = Bool3(!x, !y, !z)

    override fun abs(): Bool3 = Bool3(x, y, z)
    override fun avg(): Float = throw UnsupportedOperationException("Cannot get average of a boolean vector")
    override fun min(other: Vector<Boolean>): Bool3 {
        return if (other is Bool3)
            Bool3(x && other.x, y && other.y, z && other.z)
        else
            throw IllegalArgumentException("Other must be a Bool3")
    }
    override fun max(other: Vector<Boolean>): Bool3 {
        return if (other is Bool3)
            Bool3(x || other.x, y || other.y, z || other.z)
        else
            throw IllegalArgumentException("Other must be a Bool3")
    }
    override fun isIn(min: Boolean, max: Boolean): Vector<Boolean> = throw UnsupportedOperationException("Cannot check if a boolean vector is in a range")
    override fun intPow(n: Int): Vector<Boolean> = throw UnsupportedOperationException("Cannot raise a boolean vector to a power")

    override fun sin(): DoubleVector = throw UnsupportedOperationException("Cannot get sine of a boolean vector")
    override fun cos(): DoubleVector = throw UnsupportedOperationException("Cannot get cosine of a boolean vector")
    override fun tan(): DoubleVector = throw UnsupportedOperationException("Cannot get tangent of a boolean vector")

    override fun asFloat(): Float3 = Float3(if (x) 1f else 0f, if (y) 1f else 0f, if (z) 1f else 0f)
    override fun asDouble(): Double3 = Double3(if (x) 1.0 else 0.0, if (y) 1.0 else 0.0, if (z) 1.0 else 0.0)
    override fun asInt(): Int3 = Int3(if (x) 1 else 0, if (y) 1 else 0, if (z) 1 else 0)
    override fun asShort(): Short3 = Short3(if (x) 1 else 0, if (y) 1 else 0, if (z) 1 else 0)
    override fun asRowMatrix(): BooleanMatrix = data.toMatrix(intArrayOf(1, 3))
    override fun asColumnMatrix(): BooleanMatrix = data.toMatrix(intArrayOf(3, 1))

    override fun xy(): Bool2 = Bool2(x, y)
    override fun yz(): Bool2 = Bool2(y, z)
    override fun xz(): Bool2 = Bool2(x, z)
    override fun yx(): Bool2 = Bool2(y, x)
    override fun zy(): Bool2 = Bool2(z, y)
    override fun zx(): Bool2 = Bool2(z, x)
    override fun xyz(): Bool3 = Bool3(x, y, z)
    override fun xzy(): Bool3 = Bool3(x, z, y)
    override fun yxz(): Bool3 = Bool3(y, x, z)
    override fun yzx(): Bool3 = Bool3(y, z, x)
    override fun zxy(): Bool3 = Bool3(z, x, y)
    override fun zyx(): Bool3 = Bool3(z, y, x)
    override fun xxx(): Bool3 = Bool3(x, x, x)
    override fun yyy(): Bool3 = Bool3(y, y, y)
    override fun zzz(): Bool3 = Bool3(z, z, z)

    override fun copyOf(): Bool3 = Bool3(x, y, z)
}

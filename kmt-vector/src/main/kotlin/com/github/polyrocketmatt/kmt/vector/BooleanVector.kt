package com.github.polyrocketmatt.kmt.vector

import com.github.polyrocketmatt.kmt.common.storage.Tuple2
import com.github.polyrocketmatt.kmt.common.storage.Tuple3
import com.github.polyrocketmatt.kmt.common.storage.Tuple4
import com.github.polyrocketmatt.kmt.common.utils.complies
import com.github.polyrocketmatt.kmt.matrix.BooleanMatrix
import com.github.polyrocketmatt.kmt.matrix.Matrix
import com.github.polyrocketmatt.kmt.matrix.toMatrix

fun BooleanVector.float() = this.asFloat()
fun BooleanVector.double() = this.asDouble()
fun BooleanVector.int() = this.asInt()
fun BooleanVector.short() = this.asShort()

/**
 * Convert a boolean matrix to a boolean vector.
 *
 * @return A boolean vector whose components are the elements of the matrix.
 * @throws IllegalArgumentException if the matrix is not a 2x1 or 1x2 matrix.
 */
fun BooleanMatrix.toBool2(): Bool2 {
    complies("Cannot create a Bool2 from a BooleanMatrix with ${this.data.size} elements!") { this.data.size == 2 }
    return Bool2(this.data[0], this.data[1])
}

/**
 * Convert a boolean matrix to a boolean vector.
 *
 * @return A boolean vector whose components are the elements of the matrix.
 * @throws IllegalArgumentException if the matrix is not a 3x1 or 1x3 matrix.
 */
fun BooleanMatrix.toBool3(): Bool3 {
    complies("Cannot create a Bool3 from a BooleanMatrix with ${this.data.size} elements!") { this.data.size == 3 }
    return Bool3(this.data[0], this.data[1], this.data[2])
}

/**
 * Convert a boolean matrix to a boolean vector.
 *
 * @return A boolean vector whose components are the elements of the matrix.
 * @throws IllegalArgumentException if the matrix does contain have 4 elements.
 */
fun BooleanMatrix.toBool4(): Bool4 {
    complies("Cannot create a Bool4 from a BooleanMatrix with ${this.data.size} elements!") { this.data.size == 4 }
    return Bool4(this.data[0], this.data[1], this.data[2], this.data[3])
}

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents an n-dimensional vector of booleans.
 */
interface BooleanVector : Vector<Boolean>, Matrix<Boolean> {

    /**
     * Get the vector as a floating point vector.
     *
     * @return The vector as a floating point vector.
     */
    fun asFloat(): FloatVector

    /**
     * Get the vector as a double vector.
     *
     * @return The vector as a double vector.
     */
    fun asDouble(): DoubleVector

    /**
     * Get the vector as an integer vector.
     *
     * @return The vector as an integer vector.
     */
    fun asInt(): IntVector

    /**
     * Get the vector as a short vector.
     *
     * @return The vector as a short vector.
     */
    fun asShort(): ShortVector

    /**
     * Get the vector as a boolean row matrix.
     *
     * @return The vector as a boolean row matrix.
     */
    fun asRowMatrix(): BooleanMatrix

    /**
     * Get the vector as a boolean column matrix.
     *
     * @return The vector as a boolean column matrix.
     */
    fun asColumnMatrix(): BooleanMatrix
}

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
    override fun asRowMatrix(): BooleanMatrix = data.toMatrix(intArrayOf(1, 2))
    override fun asColumnMatrix(): BooleanMatrix = data.toMatrix(intArrayOf(2, 1))

    override fun xy(): Bool2 = Bool2(x, y)
    override fun yx(): Bool2 = Bool2(y, x)
    override fun xx(): Bool2 = Bool2(x, x)
    override fun yy(): Bool2 = Bool2(y, y)

    override fun copyOf(): Bool2 = Bool2(x, y)

    @Deprecated("Use operator instead", ReplaceWith("vector[i]"))
    override fun get(i: Int): Boolean = data[i]
    override fun get(row: Int, col: Int): Boolean = throw UnsupportedOperationException("Bool2 is considered a vector")

    @Deprecated("Use operator instead", ReplaceWith("vector[i] = value"))
    override fun set(i: Int, value: Boolean) = when (i) {
        0 -> x = value
        1 -> y = value
        else -> throw IndexOutOfBoundsException("Index $i is out of bounds for Bool2")
    }
    override fun set(row: Int, col: Int, value: Boolean) = throw UnsupportedOperationException("Bool2 is considered a vector")
}

class Bool3(x: Boolean, y: Boolean, z: Boolean) : Tuple3<Boolean>(arrayOf(x, y, z)), BooleanVector, Swizzle3 {

    constructor() : this(false, false, false)
    constructor(other: Bool3) : this(other.x, other.y, other.z)
    constructor(x: Boolean) : this(x, x, x)

    operator fun plus(other: Bool3) = Bool3(x || other.x, y || other.y, z || other.z)
    operator fun times(other: Bool3) = Bool3(x && other.x, y && other.y, z && other.z)

    override fun plus(value: Boolean): Bool3 = Bool3(x || value, y || value, z || value)
    override fun minus(value: Boolean): Bool3 = throw java.lang.UnsupportedOperationException("Cannot subtract a boolean from a boolean vector")
    override fun times(value: Boolean): Bool3 = Bool3(x && value, y && value, z && value)
    override fun div(value: Boolean): Bool3 = throw java.lang.UnsupportedOperationException("Cannot divide a boolean vector by a boolean")

    override fun plusAssign(value: Boolean) { x = x || value; y = y || value; z = z || value }
    override fun minusAssign(value: Boolean) = throw java.lang.UnsupportedOperationException("Cannot subtract a boolean from a boolean vector")
    override fun timesAssign(value: Boolean) { x = x && value; y = y && value; z = z && value }
    override fun divAssign(value: Boolean) = throw java.lang.UnsupportedOperationException("Cannot divide a boolean vector by a boolean")

    override fun length(): Float = throw java.lang.UnsupportedOperationException("Cannot get length of a boolean vector")
    override fun lengthDouble(): Double = throw java.lang.UnsupportedOperationException("Cannot get length of a boolean vector")
    override fun lengthSq(): Boolean = throw java.lang.UnsupportedOperationException("Cannot get squared length of a boolean vector")
    override fun max(): Boolean = if (x) true else if (y) true else z
    override fun min(): Boolean = if (!x) false else if (!y) false else !z
    override fun sum(): Boolean = x || y || z
    override fun diff(): Boolean = throw java.lang.UnsupportedOperationException("Cannot get difference of a boolean vector")
    override fun product(): Boolean = x && y && z
    override fun div(): Boolean = throw java.lang.UnsupportedOperationException("Cannot get division of a boolean vector")
    override fun normalized(): Vector<Float> = throw java.lang.UnsupportedOperationException("Cannot normalize a boolean vector")

    override fun dist(other: Vector<Boolean>): Float = throw java.lang.UnsupportedOperationException("Cannot get distance between boolean vectors")
    override fun distSq(other: Vector<Boolean>): Float = throw java.lang.UnsupportedOperationException("Cannot get squared distance between boolean vectors")
    override fun dot(other: Vector<Boolean>): Float = throw java.lang.UnsupportedOperationException("Cannot get dot product of boolean vectors")
    override fun sdot(): Boolean = throw java.lang.UnsupportedOperationException("Cannot get dot product of boolean vector with itself")
    override fun unaryMinus(): Bool3 = Bool3(!x, !y, !z)

    override fun abs(): Bool3 = Bool3(x, y, z)
    override fun avg(): Float = throw java.lang.UnsupportedOperationException("Cannot get average of a boolean vector")
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
    override fun isIn(min: Boolean, max: Boolean): Vector<Boolean> = throw java.lang.UnsupportedOperationException("Cannot check if a boolean vector is in a range")
    override fun intPow(n: Int): Vector<Boolean> = throw java.lang.UnsupportedOperationException("Cannot raise a boolean vector to a power")

    override fun sin(): DoubleVector = throw java.lang.UnsupportedOperationException("Cannot get sine of a boolean vector")
    override fun cos(): DoubleVector = throw java.lang.UnsupportedOperationException("Cannot get cosine of a boolean vector")
    override fun tan(): DoubleVector = throw java.lang.UnsupportedOperationException("Cannot get tangent of a boolean vector")

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

    @Deprecated("Use operator instead", ReplaceWith("vector[i]"))
    override fun get(i: Int): Boolean = data[i]
    override fun get(row: Int, col: Int): Boolean = throw java.lang.UnsupportedOperationException("Bool3 is considered a vector")

    @Deprecated("Use operator instead", ReplaceWith("vector[i] = value"))
    override fun set(i: Int, value: Boolean) = when (i) {
        0 -> x = value
        1 -> y = value
        2 -> z = value
        else -> throw IndexOutOfBoundsException("Index $i is out of bounds for Bool3")
    }
    override fun set(row: Int, col: Int, value: Boolean) = throw java.lang.UnsupportedOperationException("Bool3 is considered a vector")
}

class Bool4(x: Boolean, y: Boolean, z: Boolean, w: Boolean) : Tuple4<Boolean>(arrayOf(x, y, z, w)), BooleanVector,
    Swizzle4 {

    constructor() : this(false, false, false, false)
    constructor(other: Bool4) : this(other.x, other.y, other.z, other.w)
    constructor(x: Boolean) : this(x, x, x, x)

    operator fun plus(other: Bool4): Bool4 = Bool4(x || other.x, y || other.y, z || other.z, w || other.w)
    operator fun times(other: Bool4): Bool4 = Bool4(x && other.x, y && other.y, z && other.z, w && other.w)

    override fun plus(value: Boolean): Bool4 = Bool4(x || value, y || value, z || value, w || value)

    override fun minus(value: Boolean): Bool4 = throw java.lang.UnsupportedOperationException("Cannot subtract a boolean from a boolean vector")

    override fun times(value: Boolean): Bool4 = Bool4(x && value, y && value, z && value, w && value)

    override fun div(value: Boolean): Bool4 = throw java.lang.UnsupportedOperationException("Cannot divide a boolean from a boolean vector")

    override fun plusAssign(value: Boolean) { x = x || value; y = y || value; z = z || value; w = w || value }

    override fun minusAssign(value: Boolean) = throw java.lang.UnsupportedOperationException("Cannot subtract a boolean from a boolean vector")

    override fun timesAssign(value: Boolean) { x = x && value; y = y && value; z = z && value; w = w && value }
    override fun divAssign(value: Boolean) = throw java.lang.UnsupportedOperationException("Cannot divide a boolean from a boolean vector")

    override fun length(): Float = throw java.lang.UnsupportedOperationException("Cannot get length of a boolean vector")
    override fun lengthDouble(): Double = throw java.lang.UnsupportedOperationException("Cannot get length of a boolean vector")
    override fun lengthSq(): Boolean = throw java.lang.UnsupportedOperationException("Cannot get squared length of a boolean vector")
    override fun max(): Boolean = if (x) true else if (y) true else if (z) true else w
    override fun min(): Boolean = if (!x) false else if (!y) false else if (!z) false else !w
    override fun sum(): Boolean = x || y || z || w
    override fun diff(): Boolean = throw java.lang.UnsupportedOperationException("Cannot get difference of a boolean vector")
    override fun product(): Boolean = x && y && z && w
    override fun div(): Boolean = throw java.lang.UnsupportedOperationException("Cannot get division of a boolean vector")
    override fun normalized(): Vector<Float> = throw java.lang.UnsupportedOperationException("Cannot normalize a boolean vector")

    override fun dist(other: Vector<Boolean>): Float = throw java.lang.UnsupportedOperationException("Cannot get distance between boolean vectors")
    override fun distSq(other: Vector<Boolean>): Float = throw java.lang.UnsupportedOperationException("Cannot get squared distance between boolean vectors")
    override fun dot(other: Vector<Boolean>): Float = throw java.lang.UnsupportedOperationException("Cannot get dot product of boolean vectors")
    override fun sdot(): Boolean = throw java.lang.UnsupportedOperationException("Cannot get dot product of boolean vector with itself")
    override fun unaryMinus(): Bool4 = Bool4(!x, !y, !z, !w)

    override fun abs(): Bool4 = Bool4(x, y, z, w)
    override fun avg(): Float = throw java.lang.UnsupportedOperationException("Cannot get average of a boolean vector")
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
    override fun isIn(min: Boolean, max: Boolean): Vector<Boolean> = throw java.lang.UnsupportedOperationException("Cannot check if a boolean vector is in a range")
    override fun intPow(n: Int): Vector<Boolean> = throw java.lang.UnsupportedOperationException("Cannot raise a boolean vector to a power")

    override fun sin(): DoubleVector = throw java.lang.UnsupportedOperationException("Cannot get sine of a boolean vector")
    override fun cos(): DoubleVector = throw java.lang.UnsupportedOperationException("Cannot get cosine of a boolean vector")
    override fun tan(): DoubleVector = throw java.lang.UnsupportedOperationException("Cannot get tangent of a boolean vector")

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

    @Deprecated("Use operator instead", ReplaceWith("vector[i]"))
    override fun get(i: Int): Boolean = data[i]
    override fun get(row: Int, col: Int): Boolean = throw java.lang.UnsupportedOperationException("Bool4 is considered a vector")

    @Deprecated("Use operator instead", ReplaceWith("vector[i] = value"))
    override fun set(i: Int, value: Boolean) = when (i) {
        0 -> x = value
        1 -> y = value
        2 -> z = value
        3 -> w = value
        else -> throw IndexOutOfBoundsException("Index $i is out of bounds for Bool4")
    }
    override fun set(row: Int, col: Int, value: Boolean) = throw java.lang.UnsupportedOperationException("Bool4 is considered a vector")
}

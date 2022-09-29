package com.github.polyrocketmatt.kmt.vector.bl

import com.github.polyrocketmatt.kmt.vector.Vector
import com.github.polyrocketmatt.kmt.vector.db.DoubleVector
import com.github.polyrocketmatt.kmt.vector.fl.FloatVector
import com.github.polyrocketmatt.kmt.vector.it.IntVector
import com.github.polyrocketmatt.kmt.vector.sh.ShortVector

fun BooleanVector.float() = this.asFloat()
fun BooleanVector.double() = this.asDouble()
fun BooleanVector.int() = this.asInt()
fun BooleanVector.short() = this.asShort()

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Represents a mutable, n-dimensional vector of booleans.
 */
abstract class BooleanVector : Vector<Boolean> {

    /**
     * Get the vector as a floating point vector.
     *
     * @return The vector as a floating point vector.
     */
    abstract fun asFloat(): FloatVector

    /**
     * Get the vector as a double vector.
     *
     * @return The vector as a double vector.
     */
    abstract fun asDouble(): DoubleVector

    /**
     * Get the vector as an integer vector.
     *
     * @return The vector as an integer vector.
     */
    abstract fun asInt(): IntVector

    /**
     * Get the vector as a short vector.
     *
     * @return The vector as a short vector.
     */
    abstract fun asShort(): ShortVector

}
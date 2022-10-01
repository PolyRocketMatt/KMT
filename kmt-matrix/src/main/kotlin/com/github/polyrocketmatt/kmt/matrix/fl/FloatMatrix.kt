package com.github.polyrocketmatt.kmt.matrix.fl

import com.github.polyrocketmatt.kmt.matrix.Matrix
import com.github.polyrocketmatt.kmt.vector.it.IntVector

abstract class FloatMatrix : Matrix<Float> {

    abstract fun where(predicate: (IntVector) -> Float): FloatMatrix

}
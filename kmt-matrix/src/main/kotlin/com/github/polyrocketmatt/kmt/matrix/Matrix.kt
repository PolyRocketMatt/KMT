package com.github.polyrocketmatt.kmt.matrix

interface Matrix<T> {

    operator fun plus(other: Matrix<T>): Matrix<T>
    operator fun minus(other: Matrix<T>): Matrix<T>
    operator fun times(other: Matrix<T>): Matrix<T>
    operator fun div(other: Matrix<T>): Matrix<T>

    operator fun plusAssign(other: Matrix<T>)
    operator fun minusAssign(other: Matrix<T>)
    operator fun timesAssign(other: Matrix<T>)
    operator fun divAssign(other: Matrix<T>)

    fun transpose(): Matrix<T>
}
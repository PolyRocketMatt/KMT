package com.github.polyrocketmatt.kmt.matrix

interface Matrix<T> {

    operator fun get(i: Int): T
    operator fun get(row: Int, col: Int): T

    operator fun set(i: Int, value: T)
    operator fun set(row: Int, col: Int, value: T)

    operator fun plus(value: T): Matrix<T>
    operator fun minus(value: T): Matrix<T>
    operator fun times(value: T): Matrix<T>
    operator fun div(value: T): Matrix<T>

    operator fun plusAssign(value: T)
    operator fun minusAssign(value: T)
    operator fun timesAssign(value: T)
    operator fun divAssign(value: T)

    fun transpose(): Matrix<T>

}
package com.github.polyrocketmatt.kmt.linalg

import com.github.polyrocketmatt.kmt.common.storage.tupleOf

fun main() {
    val v1 = tupleOf(1, 0, 0)
    val v2 = tupleOf(0, 1, 0)
    val v3 = tupleOf(0, 1, 0 )
    val basis = Basis(v1, v2, v3)

    println("YAY")
}

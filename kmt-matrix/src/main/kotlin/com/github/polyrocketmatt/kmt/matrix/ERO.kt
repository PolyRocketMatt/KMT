package com.github.polyrocketmatt.kmt.matrix

enum class ERO {

    SWAP,
    MULTIPLY,
    ADD

}

data class ElementaryOperation<T>(
    val type: ERO,
    val row1: Int,
    val row2: Int,
    val scalar: T
) {

    override fun toString(): String = "Operation[${type.name}, $row1, $row2, $scalar]"

}
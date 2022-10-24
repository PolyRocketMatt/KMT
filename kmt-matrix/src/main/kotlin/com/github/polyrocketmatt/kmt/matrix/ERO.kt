package com.github.polyrocketmatt.kmt.matrix

enum class ERO {

    SWAP,
    MULTIPLY,
    ADD

}

data class ElementaryOperation(
    val type: ERO,
    val row1: Int,
    val row2: Int,
    val scalar: Double
) {

    override fun toString(): String = "Operation[${type.name}, $row1, $row2, $scalar]"

}
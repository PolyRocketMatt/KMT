package com.github.polyrocketmatt.kmt.group

import com.github.polyrocketmatt.kmt.group.group.Monoid

fun main() {
    val array = IntArray(10000) { it }

    println("Checking integrity")
    val t = System.currentTimeMillis()
    val monoid = Monoid(0, Int::plus, array.toSet())
    println("Took ${System.currentTimeMillis() - t}ms")
}
package com.github.polyrocketmatt.kmt.function.node

@FunctionalInterface
interface DifferentiableNode {

    fun differentiate(): Node

}
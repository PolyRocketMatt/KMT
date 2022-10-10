package com.github.polyrocketmatt.kmt.function.node

@FunctionalInterface
interface IntegrableNode {

    fun integrate(): Node

}
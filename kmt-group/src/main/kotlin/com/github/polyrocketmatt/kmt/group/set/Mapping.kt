package com.github.polyrocketmatt.kmt.group.set

@FunctionalInterface
interface Mapping<T> {

    fun <K> map(map: (T) -> K): SimpleSet<K>
}

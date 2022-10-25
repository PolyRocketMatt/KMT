package com.github.polyrocketmatt.group.set

@FunctionalInterface
interface Mapping<T> {

    fun <K> map(map: (T) -> K): SimpleSet<K>
}

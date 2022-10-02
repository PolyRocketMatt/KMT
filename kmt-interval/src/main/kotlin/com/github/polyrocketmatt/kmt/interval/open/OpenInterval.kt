package com.github.polyrocketmatt.kmt.interval.open

import com.github.polyrocketmatt.kmt.interval.Interval
import com.github.polyrocketmatt.kmt.interval.closed.ClosedInterval

interface OpenInterval<T> : Interval<T> {

    fun withoutEdge(): ClosedInterval<T>

}

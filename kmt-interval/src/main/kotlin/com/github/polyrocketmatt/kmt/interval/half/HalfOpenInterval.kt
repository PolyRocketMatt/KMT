package com.github.polyrocketmatt.kmt.interval.half

import com.github.polyrocketmatt.kmt.interval.Interval
import com.github.polyrocketmatt.kmt.interval.closed.ClosedInterval

interface HalfOpenInterval<T> : Interval<T> {

    fun withoutEdge(): ClosedInterval<T>
}

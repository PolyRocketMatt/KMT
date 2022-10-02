package com.github.polyrocketmatt.kmt.common.utils

import java.text.DecimalFormatSymbols

fun Double.decimalPlaces(): Int {
    val str = this.toString()
    val parts = str.split(DecimalFormatSymbols.getInstance().groupingSeparator)
    return if (parts.size == 2) parts[1].length else 0
}

fun Float.decimalPlaces(): Int {
    val str = this.toString()
    val parts = str.split(DecimalFormatSymbols.getInstance().groupingSeparator)
    return if (parts.size == 2) parts[1].length else 0
}
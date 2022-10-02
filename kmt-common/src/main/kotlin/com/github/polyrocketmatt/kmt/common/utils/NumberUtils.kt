/*
 * KMT, Kotlin Math Toolkit
 * Copyright (C) Matthias Kovacic <matthias.kovacic@student.kuleuven.be>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.polyrocketmatt.kmt.common.utils

import java.text.DecimalFormatSymbols

/**
 * Get the amount of decimal places of a number.
 *
 * @return The amount of decimal places.
 */
fun Double.decimalPlaces(): Int {
    val str = this.toString()
    val parts = str.split(DecimalFormatSymbols.getInstance().groupingSeparator)
    return if (parts.size == 2) parts[1].length else 0
}

/**
 * Get the amount of decimal places of a number.
 *
 * @return The amount of decimal places.
 */
fun Float.decimalPlaces(): Int {
    val str = this.toString()
    val parts = str.split(DecimalFormatSymbols.getInstance().groupingSeparator)
    return if (parts.size == 2) parts[1].length else 0
}

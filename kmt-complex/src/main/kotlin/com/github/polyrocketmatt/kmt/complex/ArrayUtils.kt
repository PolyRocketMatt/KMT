package com.github.polyrocketmatt.kmt.complex

/**
 * Find the index of the element in the array, given the condition function.
 *
 * @param condition The condition function to check.
 * @return The index of the element, or -1 if not found.
 */
fun Array<Complex>.indexByCondition(base: Complex, condition: (cIdx: Int, current: Complex, idx: Int, value: Complex) -> Boolean): Int {
    if (this.isEmpty())
        return -1

    var index = -1
    var element = base
    for (i in 0 until this.size) {
        if (condition(index, element, i, this[i])) {
            element = this[i]
            index = i
        }
    }

    return index
}
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

package com.github.polyrocketmatt.kmt.vector

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Defines swizzling operations for 3D vectors.
 */
interface Swizzle3 {

    fun xy(): Swizzle2

    fun yz(): Swizzle2

    fun xz(): Swizzle2

    fun yx(): Swizzle2

    fun zy(): Swizzle2

    fun zx(): Swizzle2

    fun xyz(): Swizzle3

    fun xzy(): Swizzle3

    fun yxz(): Swizzle3

    fun yzx(): Swizzle3

    fun zxy(): Swizzle3

    fun zyx(): Swizzle3

    fun xxx(): Swizzle3

    fun yyy(): Swizzle3

    fun zzz(): Swizzle3

}
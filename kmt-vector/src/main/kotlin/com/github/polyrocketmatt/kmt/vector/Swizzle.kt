package com.github.polyrocketmatt.kmt.vector

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Defines swizzling operations for 2D vectors.
 */
interface Swizzle2 {

    fun xy(): Swizzle2

    fun yx(): Swizzle2

    fun xx(): Swizzle2

    fun yy(): Swizzle2
}

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

/**
 * @author Matthias Kovacic
 * @since 0.0.1
 *
 * Defines swizzling operations for 4D vectors.
 */
interface Swizzle4 {

    fun xy(): Swizzle2

    fun xz(): Swizzle2

    fun xw(): Swizzle2

    fun yx(): Swizzle2

    fun yz(): Swizzle2

    fun yw(): Swizzle2

    fun zx(): Swizzle2

    fun zy(): Swizzle2

    fun zw(): Swizzle2

    fun wx(): Swizzle2

    fun wy(): Swizzle2

    fun wz(): Swizzle2

    fun xyz(): Swizzle3

    fun xyw(): Swizzle3

    fun xzy(): Swizzle3

    fun xzw(): Swizzle3

    fun xwy(): Swizzle3

    fun xwz(): Swizzle3

    fun yxz(): Swizzle3

    fun yxw(): Swizzle3

    fun yzx(): Swizzle3

    fun yzw(): Swizzle3

    fun ywx(): Swizzle3

    fun ywz(): Swizzle3

    fun zxy(): Swizzle3

    fun zxw(): Swizzle3

    fun zyx(): Swizzle3

    fun zyw(): Swizzle3

    fun zwx(): Swizzle3

    fun zwy(): Swizzle3

    fun wxy(): Swizzle3

    fun wxz(): Swizzle3

    fun wyx(): Swizzle3

    fun wyz(): Swizzle3

    fun wzx(): Swizzle3

    fun wzy(): Swizzle3

    fun xyzw(): Swizzle4

    fun xywz(): Swizzle4

    fun xzyw(): Swizzle4

    fun xzwy(): Swizzle4

    fun xwyz(): Swizzle4

    fun xwzy(): Swizzle4

    fun yxzw(): Swizzle4

    fun yxwz(): Swizzle4

    fun yzxw(): Swizzle4

    fun yzwx(): Swizzle4

    fun ywxz(): Swizzle4

    fun ywzx(): Swizzle4

    fun zxyw(): Swizzle4

    fun zxwy(): Swizzle4

    fun zyxw(): Swizzle4

    fun zywx(): Swizzle4

    fun zwxy(): Swizzle4

    fun zwyx(): Swizzle4

    fun wxyz(): Swizzle4

    fun wxzy(): Swizzle4

    fun wyxz(): Swizzle4

    fun wyzx(): Swizzle4

    fun wzxy(): Swizzle4

    fun wzyx(): Swizzle4

    fun xxxx(): Swizzle4

    fun yyyy(): Swizzle4

    fun zzzz(): Swizzle4

    fun wwww(): Swizzle4
}

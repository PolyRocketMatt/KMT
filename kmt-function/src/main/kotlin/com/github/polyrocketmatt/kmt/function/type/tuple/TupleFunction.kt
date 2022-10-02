package com.github.polyrocketmatt.kmt.function.type.tuple

import com.github.polyrocketmatt.kmt.common.storage.Tuple
import com.github.polyrocketmatt.kmt.function.Function

abstract class TupleFunction<T>(arity: Int) : Function<Tuple<T>>(arity)
package com.github.polyrocketmatt.kmt.common.annotation

/**
 * Reference tag to refer to a certain paper, author or other source.
 *
 * @param reference: the reference referred to
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CLASS,
    AnnotationTarget.FIELD,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.CONSTRUCTOR, AnnotationTarget.LOCAL_VARIABLE,
)
annotation class Ref(val reference: String)

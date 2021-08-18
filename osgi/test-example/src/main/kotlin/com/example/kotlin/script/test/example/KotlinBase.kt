package com.example.kotlin.script.test.example

import com.example.kotlin.script.test.api.Blob

@Suppress("unused")
open class KotlinBase {
    open val baseNullable: Any? = null
    open val baseNonNullable: Any = Any()
    protected open val protectedBaseNullable: Any? = null

    fun examine(blobs: Array<Blob>) {
        println(blobs)
    }
}

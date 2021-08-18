package com.example.kotlin.script.test.example

import com.example.kotlin.script.test.api.KotlinApi

interface ExtendedKotlinApi : KotlinApi {
    val neverNull: String

    val listOfItems: List<String>

    override fun anything(): List<Any?>
}

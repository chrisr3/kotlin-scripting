package com.example.scripting

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.lang.reflect.Method

class KotlinScriptTest {
    private val scriptor = KotlinScriptor()

    @Test
    fun testSimpleScript() {
        val result = scriptor.execute<Int>("""
        |    val x = 123
        |    val y = 1000
        |    x + y
        |""".trimMargin())
        assertEquals(1123, result)
    }

    @Test
    fun testPrintToStdOut() {
        scriptor.execute<Unit>("""
        |    println("Hello World!")
        |""".trimMargin())
    }

    @Test
    fun testGetClass() {
        val result = scriptor.execute<Class<*>>("this::class.java")
        assertNotNull(result)
    }

    @Test
    fun testGetMethods() {
        val result = scriptor.execute<Array<Method>>("this::class.java.methods")
        assertThat(result).isNotEmpty
    }

    @Test
    fun testScriptContext() {
        for (scope in scriptor.context.scopes) {
            val bindings = scriptor.context.getBindings(scope)
            assertThat(bindings).isEmpty()
        }
    }
}

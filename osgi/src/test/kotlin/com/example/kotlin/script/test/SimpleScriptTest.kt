package com.example.kotlin.script.test

import com.example.scripting.KotlinScriptor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// See https://github.com/xafero/dynkt for explanation of "cannot extract ..." error:
//    cannot extract '/org/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment$Companion.class'
//    from 'bundle://fd36403c-318d-481a-b98a-7c4e6204eed4_1.0:1/org/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment$Companion.class'
// Need to patch IntelliJ's PathManager class to understand `bundle://` URLs.
class SimpleScriptTest {
    private val scriptor = KotlinScriptor()

    @Test
    fun runSimpleScript() {
        val result = scriptor.execute<Int>("""
        |    val x = 123
        |    val y = 1000
        |    x + y
        |""".trimMargin())
        assertEquals(1123, result)
    }

    @Test
    fun runTrivialScript() {
        val result = scriptor.execute<Int>("10")
        assertEquals(10, result)
    }

    @Test
    fun testPrintToStdOut() {
        scriptor.execute<Unit>("""
        |    println("Hello OSGi World!")
        |""".trimMargin())
    }
}

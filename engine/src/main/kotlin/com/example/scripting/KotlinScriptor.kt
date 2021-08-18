package com.example.scripting

import javax.script.ScriptContext
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class KotlinScriptor(classLoader: ClassLoader) {
    constructor() : this(ClassLoader.getSystemClassLoader())

    private val engine: ScriptEngine = ScriptEngineManager(classLoader).getEngineByExtension("kts")

    val context: ScriptContext = engine.context

    inline fun <reified T> execute(script: String) = execute(script, T::class.java)

    fun <T> execute(script: String, returnType: Class<T>): T? {
        return kotlin.runCatching {
            returnType.cast(engine.eval(script))
        }.getOrNull()
    }
}

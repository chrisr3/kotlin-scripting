package com.example.scripting

import aQute.bnd.annotation.Resolution.MANDATORY
import aQute.bnd.annotation.spi.ServiceConsumer
import aQute.bnd.annotation.spi.ServiceProvider
import javax.script.ScriptContext
import javax.script.ScriptEngine
import javax.script.ScriptEngineFactory
import javax.script.ScriptEngineManager
import org.jetbrains.kotlin.script.jsr223.KotlinJsr223JvmLocalScriptEngineFactory

@ServiceProvider(ScriptEngineFactory::class, register = KotlinJsr223JvmLocalScriptEngineFactory::class)
@ServiceConsumer(ScriptEngineFactory::class, resolution = MANDATORY)
class KotlinScriptor(classLoader: ClassLoader) {
    constructor() : this(KotlinScriptor::class.java.classLoader)

    private val engine: ScriptEngine = ScriptEngineManager(classLoader).getEngineByExtension("kts") ?: throw IllegalStateException("No KTS engine found")

    val context: ScriptContext
        get() = engine.context

    inline fun <reified T> execute(script: String) = execute(script, T::class.java)

    fun <T> execute(script: String, returnType: Class<T>): T? {
        return kotlin.runCatching {
            returnType.cast(engine.eval(script))
        }.getOrNull()
    }
}

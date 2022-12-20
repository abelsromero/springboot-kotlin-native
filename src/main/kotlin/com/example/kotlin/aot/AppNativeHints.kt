package com.example.kotlin.aot

import com.example.kotlin.HelloController
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aot.hint.MemberCategory
import org.springframework.aot.hint.RuntimeHints
import org.springframework.aot.hint.RuntimeHintsRegistrar

class AppNativeHints : RuntimeHintsRegistrar {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    override fun registerHints(hints: RuntimeHints, classLoader: ClassLoader?) {
        log.info("Registering types")

        hints.resources()
                .registerPattern("openapi-example.json")

        // Kotlin private methods are not detected and need to be registered.
        // Because they are not actual "Method" type, but KCallable
        hints.reflection()
                .registerType(HelloController::class.java, MemberCategory.INVOKE_DECLARED_METHODS)

    }
}


package com.example.demokotlinnative.aot

import com.example.demokotlinnative.HelloController
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aot.hint.MemberCategory
import org.springframework.aot.hint.RuntimeHints
import org.springframework.aot.hint.RuntimeHintsRegistrar

class AppNativeHints : RuntimeHintsRegistrar {

    val log: Logger = LoggerFactory.getLogger(javaClass)

    override fun registerHints(hints: RuntimeHints, classLoader: ClassLoader?) {
        log.info("Registering types")

        // Kotlin private methods are not detected and need to be registered.
        // These are not exist actual "Method" type, they are KCallable
        hints.reflection()
                .registerType(HelloController::class.java, *MemberCategory.values())
    }

}
//
//fun main(args: Array<String>) {
//    val runtimeHints = RuntimeHints()
//    AppNativeHints().registerHints(runtimeHints, null);
//}


package com.example.demokotlinnative

import com.example.demokotlinnative.aot.AppNativeHints
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ImportRuntimeHints

@SpringBootApplication
@ImportRuntimeHints(AppNativeHints::class)
class DemoKotlinNativeApplication

fun main(args: Array<String>) {
    runApplication<DemoKotlinNativeApplication>(*args)
}

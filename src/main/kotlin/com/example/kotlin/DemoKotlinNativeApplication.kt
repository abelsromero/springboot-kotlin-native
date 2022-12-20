package com.example.kotlin

import com.example.kotlin.aot.AppNativeHints
import com.example.kotlin.aot.ReflectionsRuntimeHintsRegistrar
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ImportRuntimeHints

@SpringBootApplication
//@ImportRuntimeHints(AppNativeHints::class, ReflectionsRuntimeHintsRegistrar::class)
class DemoKotlinNativeApplication

fun main(args: Array<String>) {
    runApplication<DemoKotlinNativeApplication>(*args)
}

package com.example.kotlin

import org.springframework.stereotype.Component

@Component
class HelloService {

    fun hello(name: String) = format(name)

    private fun format(text: String): String {
        return "Hello $text"
    }
}
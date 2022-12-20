package com.example.demokotlinnative

import com.example.demokotlinnative.openpi.OpenApiParser
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(val parser: OpenApiParser) {

    val log: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/parse")
    fun hello(): String {
        val text = HelloController::class.java.getResource("/openapi-example.json").readText()
        val parsed = parser.parse(text)
        return parsed.toString()
    }

    @GetMapping("/hello/{username}")
    fun hello(@PathVariable(required = true) username: String): ResponseMessage {
        log.info("Input(s): $username")
        return formatBody(username)
    }

    private fun formatBody(value: String) = ResponseMessage(value)

    data class ResponseMessage(val message: String)
}

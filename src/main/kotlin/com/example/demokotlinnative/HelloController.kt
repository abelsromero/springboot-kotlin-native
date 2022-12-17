package com.example.demokotlinnative

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    val log: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/hello/{username}")
    fun hello(@PathVariable(required = true) username: String): ResponseMessage {
        log.info("Input(s): $username")
        return formatBody(username)
    }

    private fun formatBody(value: String) = ResponseMessage(value)

    data class ResponseMessage(val message: String)
}

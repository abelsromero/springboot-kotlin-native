package com.example.demokotlinnative

import com.example.kotlin.openpi.OpenApiParser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class OpenApiParserTest(@Autowired val parser: OpenApiParser) {

    @Test
    fun shouldParse() {
        val text = OpenApiParserTest::class.java.getResource("/openapi-example.json").readText()
        val parsed = parser.parse(text)

        assertThat(parsed.openapi).isEqualTo("3.0.1")
    }

}

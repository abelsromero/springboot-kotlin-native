package com.example.demokotlinnative.openpi

import io.swagger.parser.OpenAPIParser
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.parser.core.models.ParseOptions
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class OpenApiParser {

	val log: Logger = LoggerFactory.getLogger(javaClass)

	fun parse(content: String): OpenAPI {

		val parseResult = OpenAPIParser().readContents(content, emptyList(), ParseOptions())

		if (parseResult.messages.isNotEmpty()) {
			log.info("Found errors: ${parseResult.messages}")

			throw IllegalArgumentException("Error(s) found: ${parseResult.messages}")
		}

		return parseResult.openAPI
	}

}

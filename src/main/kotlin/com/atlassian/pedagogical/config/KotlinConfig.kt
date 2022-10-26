package com.atlassian.pedagogical.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    KotlinImportServiceConfig::class,
    KotlinExportServiceConfig::class,
)
open class KotlinConfig {

    @Bean
    open fun objectMapper(): ObjectMapper = ObjectMapper().registerKotlinModule()
}
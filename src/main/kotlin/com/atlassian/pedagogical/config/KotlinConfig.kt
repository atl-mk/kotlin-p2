package com.atlassian.pedagogical.config

import com.atlassian.activeobjects.external.ActiveObjects
import com.atlassian.pedagogical.ao.dao.DefaultSampleEntityDao
import com.atlassian.sal.api.transaction.TransactionTemplate
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

    @Bean
    open fun defaultSampleEntityDao(
        activeObjects: ActiveObjects,
        transactionTemplate: TransactionTemplate
    ): DefaultSampleEntityDao = DefaultSampleEntityDao(activeObjects, transactionTemplate)
}
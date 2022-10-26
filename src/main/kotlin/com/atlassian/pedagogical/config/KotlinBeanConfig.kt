package com.atlassian.pedagogical.config

import com.atlassian.activeobjects.external.ActiveObjects
import com.atlassian.pedagogical.Foo
import com.atlassian.pedagogical.FooImpl
import com.atlassian.pedagogical.ao.dao.DefaultSampleEntityDao
import com.atlassian.pedagogical.service.DefaultPluginSettingsService
import com.atlassian.pedagogical.service.PluginSettingsService
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory
import com.atlassian.sal.api.transaction.TransactionTemplate
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    KotlinImportServiceConfig::class,
)
open class KotlinBeanConfig {

    @Bean
    open fun objectMapper(): ObjectMapper = ObjectMapper().registerKotlinModule()

    @Bean
    open fun sampleEntityDao(
        activeObjects: ActiveObjects,
        transactionTemplate: TransactionTemplate
    ): DefaultSampleEntityDao = DefaultSampleEntityDao(activeObjects, transactionTemplate)

    @Bean
    open fun foo(): Foo = FooImpl()

    @Bean
    open fun sampleService(
        pluginSettingsFactory: PluginSettingsFactory,
        objectMapper: ObjectMapper
    ): PluginSettingsService = DefaultPluginSettingsService(pluginSettingsFactory, objectMapper)

}
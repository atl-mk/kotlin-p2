package com.atlassian.pedagogical.config

import com.atlassian.pedagogical.Foo
import com.atlassian.pedagogical.FooImpl
import com.atlassian.plugins.osgi.javaconfig.ExportOptions.`as`
import com.atlassian.plugins.osgi.javaconfig.OsgiServices.exportOsgiService
import com.atlassian.plugins.osgi.javaconfig.OsgiServices.importOsgiService
import com.atlassian.sal.api.ApplicationProperties
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.osgi.framework.ServiceRegistration
import org.springframework.beans.factory.FactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class KotlinConfig {

    @Bean
    open fun applicationProperties(): ApplicationProperties = importOsgiService(ApplicationProperties::class.java)

    @Bean
    open fun pluginSettingsFactory(): PluginSettingsFactory = importOsgiService(PluginSettingsFactory::class.java)

    @Bean
    open fun foo(): Foo = FooImpl()

    @Bean
    open fun exportFooService(foo: Foo): FactoryBean<ServiceRegistration<*>> =
        exportOsgiService(foo, `as`(Foo::class.java))

    @Bean
    open fun objectMapper(): ObjectMapper = ObjectMapper().registerKotlinModule()
}
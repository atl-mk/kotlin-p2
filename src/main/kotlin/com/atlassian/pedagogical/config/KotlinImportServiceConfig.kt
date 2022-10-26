package com.atlassian.pedagogical.config

import com.atlassian.plugins.osgi.javaconfig.OsgiServices.importOsgiService
import com.atlassian.sal.api.ApplicationProperties
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory
import com.atlassian.soy.renderer.SoyTemplateRenderer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class KotlinImportServiceConfig {

    @Bean
    open fun applicationProperties(): ApplicationProperties = importOsgiService(ApplicationProperties::class.java)

    @Bean
    open fun pluginSettingsFactory(): PluginSettingsFactory = importOsgiService(PluginSettingsFactory::class.java)

    @Bean
    open fun soyTemplateRenderer(): SoyTemplateRenderer = importOsgiService(SoyTemplateRenderer::class.java)
}
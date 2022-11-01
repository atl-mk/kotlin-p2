package com.atlassian.pedagogical.config

import com.atlassian.pedagogical.Foo
import com.atlassian.pedagogical.service.PluginSettingsService
import com.atlassian.plugins.osgi.javaconfig.ExportOptions.`as`
import com.atlassian.plugins.osgi.javaconfig.OsgiServices.exportOsgiService
import org.osgi.framework.ServiceRegistration
import org.springframework.beans.factory.FactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    KotlinBeanConfig::class,
)
open class KotlinExportServiceConfig {

    @Bean
    open fun exportFooService(foo: Foo): FactoryBean<ServiceRegistration<*>> =
        exportOsgiService(foo, `as`(Foo::class.java))

    @Bean
    open fun exportPluginSettingsService(pluginSettingsService: PluginSettingsService): FactoryBean<ServiceRegistration<*>> =
        exportOsgiService(pluginSettingsService, `as`(PluginSettingsService::class.java))
}
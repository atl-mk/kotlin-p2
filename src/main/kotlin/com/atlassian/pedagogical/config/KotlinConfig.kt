package com.atlassian.pedagogical.config

import com.atlassian.pedagogical.Foo
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport
import com.atlassian.plugins.osgi.javaconfig.ExportOptions
import com.atlassian.plugins.osgi.javaconfig.OsgiServices.exportOsgiService
import com.atlassian.sal.api.ApplicationProperties
import org.osgi.framework.ServiceRegistration
import org.springframework.beans.factory.FactoryBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class KotlinConfig @Autowired constructor(
    @ComponentImport private val applicationProperties: ApplicationProperties
) {

    // This is really redundant as we can just Autowire external services on demand
    @Bean
    open fun applicationProperties(): ApplicationProperties {
        return applicationProperties
    }

    @Bean
    open fun exportFooService(foo: Foo?): FactoryBean<ServiceRegistration<*>?>? {
        return foo?.let { exportOsgiService(it, ExportOptions.`as`(Foo::class.java)) }
    }
}
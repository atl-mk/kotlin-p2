package com.atlassian.pedagogical.config

import com.atlassian.pedagogical.Foo
import com.atlassian.plugins.osgi.javaconfig.ExportOptions
import com.atlassian.plugins.osgi.javaconfig.OsgiServices.exportOsgiService
import com.atlassian.plugins.osgi.javaconfig.OsgiServices.importOsgiService
import com.atlassian.sal.api.ApplicationProperties
import org.osgi.framework.ServiceRegistration
import org.springframework.beans.factory.FactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class KotlinConfig {
    //TODO fix importing from Kotlin
//    @Bean
//    open fun applicationProperties() : ApplicationProperties {
//        return importOsgiService(ApplicationProperties::class.java)
//    }

    @Bean
    open fun exportFooService(foo: Foo?): FactoryBean<ServiceRegistration<*>?>? {
        return foo?.let { exportOsgiService(it, ExportOptions.`as`(Foo::class.java)) }
    }
}
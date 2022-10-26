package com.atlassian.pedagogical.config

import com.atlassian.pedagogical.Foo
import com.atlassian.pedagogical.FooImpl
import com.atlassian.plugins.osgi.javaconfig.ExportOptions.`as`
import com.atlassian.plugins.osgi.javaconfig.OsgiServices.exportOsgiService
import org.osgi.framework.ServiceRegistration
import org.springframework.beans.factory.FactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class KotlinExportServiceConfig {

    @Bean
    open fun foo(): Foo = FooImpl()

    @Bean
    open fun exportFooService(foo: Foo): FactoryBean<ServiceRegistration<*>> =
        exportOsgiService(foo, `as`(Foo::class.java))
}
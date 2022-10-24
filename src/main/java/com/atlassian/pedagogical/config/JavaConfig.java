package com.atlassian.pedagogical.config;

import com.atlassian.pedagogical.Bar;
import com.atlassian.pedagogical.BarImpl;
import com.atlassian.sal.api.ApplicationProperties;
import org.osgi.framework.ServiceRegistration;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.atlassian.plugins.osgi.javaconfig.ExportOptions.as;
import static com.atlassian.plugins.osgi.javaconfig.OsgiServices.exportOsgiService;
import static com.atlassian.plugins.osgi.javaconfig.OsgiServices.importOsgiService;

@Configuration
public class JavaConfig {

    @Bean
    public Bar bar() {
        return new BarImpl();
    }

    @SuppressWarnings("rawtypes")
    @Bean
    public FactoryBean<ServiceRegistration> exportOtherService(final Bar bar) {
        return exportOsgiService(bar, as(Bar.class));
    }

    @Bean
    public ApplicationProperties applicationProperties() {
        return importOsgiService(ApplicationProperties.class);
    }
}

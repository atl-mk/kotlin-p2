package com.atlassian.pedagogical.config;

import com.atlassian.pedagogical.Foo;
import com.atlassian.pedagogical.FooImpl;
import com.atlassian.pedagogical.Other;
import com.atlassian.plugins.osgi.javaconfig.configs.beans.ModuleFactoryBean;
import com.atlassian.plugins.osgi.javaconfig.configs.beans.PluginAccessorBean;
import com.atlassian.sal.api.ApplicationProperties;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static com.atlassian.plugins.osgi.javaconfig.ExportOptions.as;
import static com.atlassian.plugins.osgi.javaconfig.OsgiServices.exportOsgiService;
import static com.atlassian.plugins.osgi.javaconfig.OsgiServices.importOsgiService;

@Configuration
@Import({
        ModuleFactoryBean.class,
        PluginAccessorBean.class
})
public class Config {
    private static final Logger log = LoggerFactory.getLogger(Config.class);

    @Bean
    public Other other() {
        final Other other = new Other();
        log.info("HEY THERE");
        return other;
    }

    @Bean
    public Foo foo(final Other other)
    {
        log.error("yeet",other);
        final Foo foo = new FooImpl();
        log.info("HEY THERE 2222");
        return foo;
    }

    // TODO exporting from Java breaks the plugin
//    @Bean
//    public FactoryBean<ServiceRegistration> exportOtherService(final Other other) {
//        return exportOsgiService(other, as(Other.class));
//    }

    // TODO - We don't understand why this doesn't work
    @Bean
    public ApplicationProperties applicationProperties() {
        return importOsgiService(ApplicationProperties.class);
    }
}

package com.atlassian.pedagogical.config;

import com.atlassian.pedagogical.Foo;
import com.atlassian.pedagogical.FooImple;
import com.atlassian.pedagogical.Other;
import com.atlassian.sal.api.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.atlassian.plugins.osgi.javaconfig.OsgiServices.importOsgiService;

@Configuration
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
        final Foo foo = new FooImple();
        log.info("HEY THERE 2222");
        return foo;
    }

//    @Bean
//    public FactoryBean<ServiceRegistration> exportFooService(final Foo foo) {
//        return exportOsgiService(foo, as(Foo.class));
//    }

    // TODO - We don't understand why this doesn't work
    @Bean
    public ApplicationProperties applicationProperties() {
        return importOsgiService(ApplicationProperties.class);
    }
}

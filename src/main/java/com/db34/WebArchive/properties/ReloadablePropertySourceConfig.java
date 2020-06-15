package com.db34.WebArchive.properties;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;

@Configuration
public class ReloadablePropertySourceConfig {
 
    private ConfigurableEnvironment env;
 
    public ReloadablePropertySourceConfig(@Autowired ConfigurableEnvironment env) {
        this.env = env;
        System.out.println("creating ReloadablePropertySourceConfig");
    }
 
    @Bean
    @ConditionalOnProperty(name = "spring.config.location", matchIfMissing = false)
    public ReloadablePropertySource reloadablePropertySource(PropertiesConfiguration properties) {
        ReloadablePropertySource ret = new ReloadablePropertySource("dynamic", properties);
        MutablePropertySources sources = env.getPropertySources();
        System.out.println("ReloadablePropertySourceConfig::reloadablePropertySource > created source");
        sources.addFirst(ret);
        return ret;
    }
}
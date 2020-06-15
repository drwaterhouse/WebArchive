package com.db34.WebArchive.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentConfigBean {
 
    private Environment environment;
 
    public EnvironmentConfigBean(@Autowired Environment environment) {
        this.environment = environment;
    }
 
    public String getColor() {
        return environment.getProperty("application.theme.color");
    }
}

package com.db34.WebArchive.properties;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.configuration.PropertiesConfiguration;

@SuppressWarnings("serial")
public class ReloadableProperties extends Properties {
    private PropertiesConfiguration propertiesConfiguration;
 
    public ReloadableProperties(PropertiesConfiguration propertiesConfiguration) throws IOException {
        super.load(new FileReader(propertiesConfiguration.getFile()));
        this.propertiesConfiguration = propertiesConfiguration;
        System.out.println("created ReloadableProperties");
    }
   
    @Override
    public String getProperty(String key) {
        String val = propertiesConfiguration.getString(key);
        super.setProperty(key, val);
        return val;
    }
     
    // other overrides
}

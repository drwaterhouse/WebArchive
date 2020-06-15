package com.db34.WebArchive.properties;

import java.io.IOException;

import org.springframework.core.env.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

// see https://www.baeldung.com/spring-reloading-properties
/**
 * 
 * @author Markus Müller-Ulrich
 *
 * The PropertySourceFactory is a factory for a PropertySource. The default implementation used 
 * is the DefaultPropertySourceFactory, which creates ResourcePropertySource instances.
 * Writing a custom implementation requires implementing a single method, createPropertySource. 
 * The custom implementation needs to do 2 things:
 * - Load the given resource into a java.util.Properties object
 * - Create a PropertySource wrapping the loaded properties
 */
public class ReloadablePropertySourceFactory extends DefaultPropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String s, EncodedResource encodedResource)
      throws IOException {
        Resource internal = encodedResource.getResource();
        if (internal instanceof FileSystemResource)
            return new ReloadablePropertySource(s, ((FileSystemResource) internal)
              .getPath());
        if (internal instanceof FileUrlResource)
            return new ReloadablePropertySource(s, ((FileUrlResource) internal)
              .getURL()
              .getPath());
        System.out.println("created ReloadablePropertySourceFactory");
        return super.createPropertySource(s, encodedResource);
    }
}

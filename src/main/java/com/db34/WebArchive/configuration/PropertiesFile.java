package com.db34.WebArchive.configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {

	Properties prop = null;

	public PropertiesFile(final String fileName) {
		InputStream inputStream = null;
		try {
			prop = new Properties();
			inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + fileName + "' not found in the classpath");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public String getPropValue(final String propName) throws IOException {
		String propValue = null;
		try {
			if (prop.containsKey(propName)) {
				propValue = prop.getProperty("propName");
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		return propValue;
	}
}
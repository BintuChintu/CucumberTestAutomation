/*
 * *Author : RaviKumar Mogulluru
 */
package com.test.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.test.pages.AmazonTestPage;

public class ConfigFileReader {
	
	private Properties properties;
	private final String propertyFilePath= "\\src\\test\\resources\\config\\config.properties";
	private static final Logger log = LogManager.getLogger(ConfigFileReader.class);
	
	public ConfigFileReader(){
		
		FileReader reader;
		try {
			reader = new FileReader(System.getProperty("user.dir")+propertyFilePath);
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Config.properties not found at " + propertyFilePath);
		}		
	}
	
	public String readProperty(String property) {
		String value = properties.getProperty(property);
		if(value != null) 
			return value;
		else 
			throw new RuntimeException(property+" not specified in the Config.properties file.");
	}
}

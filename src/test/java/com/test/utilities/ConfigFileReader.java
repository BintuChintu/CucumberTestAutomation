package com.test.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	
	private Properties properties;
	private final String propertyFilePath= "\\src\\test\\resources\\config\\config.properties";

	
	public ConfigFileReader(){
		//BufferedReader reader;
		FileReader reader;
		try {
			//reader = new BufferedReader(new FileReader(System.getProperty("user.dir")+propertyFilePath));
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
	
//	public String getDriverPath(){
//		String driverPath = properties.getProperty("driverPath");
//		if(driverPath!= null) return driverPath;
//		else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");		
//	}
//	
//	public long getImplicitlyWait() {		
//		String implicitlyWait = properties.getProperty("implicitlyWait");
//		if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
//		else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");		
//	}
	
	public String readProperty(String property) {
		String value = properties.getProperty(property);
		if(value != null) 
			return value;
		else 
			throw new RuntimeException(property+" not specified in the Config.properties file.");
	}
}

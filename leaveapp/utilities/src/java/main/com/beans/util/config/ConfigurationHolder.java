package com.beans.util.config;

import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;

public class ConfigurationHolder {
	private CombinedConfiguration config = null;
	public ConfigurationHolder() throws ConfigurationException {
		DefaultConfigurationBuilder builder = new DefaultConfigurationBuilder("config/main.xml");
		config = builder.getConfiguration(true);
	}
	
	
	public String getString(String key) {
		return config.getString(key);
	}
	
	public int getInt(String key) {
		return config.getInt(key);
	}
	
	public boolean getBoolean(String key) {
		return config.getBoolean(key);
	}
}

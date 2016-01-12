package me.cameronwitcher.upsilon.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class XmlFile {
	
	private Properties properties = new Properties();
	public XmlFile(File file) {
		try {
			properties.loadFromXML(new FileInputStream(file));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public String getProperty(String property){
		return properties.getProperty(property);
	}

}

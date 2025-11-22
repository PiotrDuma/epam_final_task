package com.github.Piotr_Duma.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationPropertiesReader {

  private static Properties properties;

  static {
    properties = new Properties();
    try (InputStream input = ApplicationPropertiesReader.class.getClassLoader()
        .getResourceAsStream("application.properties")) {

      if (input != null) {
        properties.load(input);
      }

    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public static String getProperty(String key) {
    return properties.getProperty(key);
  }
}
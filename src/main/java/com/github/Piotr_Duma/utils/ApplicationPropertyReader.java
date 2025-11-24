package com.github.Piotr_Duma.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ApplicationPropertyReader is a class for reading configuration data specified as application
 * variables. The maven build (-D flag) properties have higher priority than the variables defined
 * in the application.properties file.
 */
public class ApplicationPropertyReader {
  private static final Logger log = LoggerFactory.getLogger(ApplicationPropertyReader.class);
  private static final String MESSAGE = "Failed to load 'application.properties' file.";
  private static final String MESSAGE_PROPERTY = "Failed to load property with given name: %s";
  private static final Properties properties;

  static {
    properties = new Properties();
    try (InputStream input = ApplicationPropertyReader.class.getClassLoader()
        .getResourceAsStream("application.properties")) {

      if (input != null) {
        properties.load(input);
      }

    } catch (IOException ex) {
      log.error(MESSAGE);
      ex.printStackTrace();
    }
  }

  /**
   * returns string value of given system property defined as maven flag -D param
   * (higher priority) or application.properties param (lower priority).
   *
   * @param key name of the variable field
   * @return string value of variable
   * @throws ApplicationPropertyException when loaded variable with given field name is null
   */
  public static String getProperty(String key) throws ApplicationPropertyException{
    //check system property
    String systemValue = System.getProperty(key);
    if (systemValue != null && !systemValue.isEmpty()) {
      return systemValue;
    }
    String property = properties.getProperty(key);
    if(property == null){
      log.error(String.format(MESSAGE_PROPERTY, key));
      throw new ApplicationPropertyException(key);
    }
    return property;
  }
}
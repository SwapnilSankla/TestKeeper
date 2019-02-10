package com.testThresholdChecker;

import java.io.IOException;
import java.util.Properties;

public class Configuration {
  public Properties get() {
    Properties properties = new Properties();
    try {
      properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
    return properties;
  }
}

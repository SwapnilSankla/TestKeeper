package com.testThresholdChecker.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "testsuite")
public class TestSuite {
  @JacksonXmlProperty(localName = "failures")
  private String numberOfFailingTests;

  @JacksonXmlProperty(localName = "skipped")
  private String numberOfSkippedTests;

  @JacksonXmlProperty(localName = "errors")
  private String numberOfTestsThrownError;

  public int getNumberOfFailingTests() {
    return Integer.parseInt(numberOfFailingTests) + Integer.parseInt(numberOfTestsThrownError);
  }

  public int getNumberOfSkippedTests() {
    return Integer.parseInt(numberOfSkippedTests);
  }
}

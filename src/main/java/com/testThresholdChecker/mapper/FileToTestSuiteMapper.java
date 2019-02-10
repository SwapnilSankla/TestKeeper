package com.testThresholdChecker.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.testThresholdChecker.model.TestSuite;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class FileToTestSuiteMapper {
  public static Optional<TestSuite> map(File report) {
    ObjectMapper objectMapper = new XmlMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    try {
      return Optional.of(objectMapper.readValue(report, TestSuite.class));
    } catch (IOException e) {
      return Optional.empty();
    }
  }
}

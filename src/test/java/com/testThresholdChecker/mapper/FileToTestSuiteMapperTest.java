package com.testThresholdChecker.mapper;

import com.testThresholdChecker.model.TestSuite;
import com.testThresholdChecker.model.XmlReportFile;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Optional;

import static org.hamcrest.core.Is.is;

public class FileToTestSuiteMapperTest {
  @Test
  public void map_returnsTestSuite_whenAppropriateTestReportIsPassed() throws FileNotFoundException {
    XmlReportFile reportFile = new XmlReportFile("src/test/resources/surefire-dummy-reports/happy/TEST-com.testThresholdChecker.HelloWorldTest.xml");
    Assert.assertTrue(FileToTestSuiteMapper.map(reportFile).isPresent());
  }

  @Test
  public void map_returnsEmpty_whenInAppropriateTestReportIsPassed() {
    Optional<TestSuite> testSuite = FileToTestSuiteMapper.map(new XmlReportFile("src/main/scripts"));
    Assert.assertThat(testSuite, is(Optional.empty()));
  }
}
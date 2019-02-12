package com.testThresholdChecker;

import com.testThresholdChecker.mapper.FileToTestSuiteMapper;
import com.testThresholdChecker.model.TestSuite;
import com.testThresholdChecker.model.XmlReportFile;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Function;

import static java.lang.Integer.parseInt;

public class TestThresholdChecker {
  public static void main(String[] args) throws FailingTestsThresholdExceededException,
      SkippedTestsThresholdExceededException,
      InvalidXmlReportDirectoryException,
      InvalidArgumentsException {
    if(args.length == 0) {
      throw new InvalidArgumentsException("");
    }
    new TestThresholdChecker().throwExceptionIfThresholdsAreCrossed(new XmlReportFile(args[0]).getTestReports());
  }

  private void throwExceptionIfThresholdsAreCrossed(List<File> xmlReports) throws FailingTestsThresholdExceededException, SkippedTestsThresholdExceededException {
    Properties properties = new Configuration().get();
    throwExceptionIfFailingTestsCrossesThreshold(xmlReports, properties);
    throwExceptionIfSkippedTestsCrossesThreshold(xmlReports, properties);
  }

  private void throwExceptionIfFailingTestsCrossesThreshold(List<File> xmlReports, Properties properties) throws FailingTestsThresholdExceededException {
    Integer numberFailingOfTests = getCount(xmlReports, TestSuite::getNumberOfFailingTests);

    int maxNumberAllowedFailingTests = parseInt(properties.getProperty("MAX_NUMBER_ALLOWED_FAILING_TESTS"));
    if (numberFailingOfTests > maxNumberAllowedFailingTests) {
      throw new FailingTestsThresholdExceededException(formatExceptionMessage
          (FailingTestsThresholdExceededException.class, maxNumberAllowedFailingTests, numberFailingOfTests));
    }
  }

  private void throwExceptionIfSkippedTestsCrossesThreshold(List<File> xmlReports, Properties properties) throws SkippedTestsThresholdExceededException {
    Integer numberOfSkippedTests = getCount(xmlReports, TestSuite::getNumberOfSkippedTests);

    int maxNumberAllowedSkippedTests = parseInt(properties.getProperty("MAX_NUMBER_ALLOWED_SKIPPED_TESTS"));
    if (numberOfSkippedTests > maxNumberAllowedSkippedTests) {
      throw new SkippedTestsThresholdExceededException(formatExceptionMessage
          (SkippedTestsThresholdExceededException.class, numberOfSkippedTests, maxNumberAllowedSkippedTests));
    }
  }

  private String formatExceptionMessage(Class clazz, int threshold, int currentCount) {
    return String.format("%s: Limit is %s current count is %s", clazz.getName(), threshold, currentCount);
  }

  private Integer getCount(List<File> xmlReports, Function<TestSuite, Integer> countFunction) {
    return xmlReports
        .stream()
        .map(FileToTestSuiteMapper::map)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(countFunction)
        .reduce(0, Integer::sum);
  }
}

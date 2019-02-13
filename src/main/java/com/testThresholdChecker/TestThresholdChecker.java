package com.testThresholdChecker;

import com.testThresholdChecker.mapper.FileToTestSuiteMapper;
import com.testThresholdChecker.model.TestSuite;
import com.testThresholdChecker.model.ThresholdCheckerConfiguration;
import com.testThresholdChecker.model.ThresholdCheckerConfiguration.ThresholdCheckerConfigurationBuilder;
import com.testThresholdChecker.model.XmlReportFile;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class TestThresholdChecker {
  public static void main(String[] args) throws FailingTestsThresholdExceededException,
      SkippedTestsThresholdExceededException,
      InvalidXmlReportDirectoryException,
      InvalidArgumentsException {
    String argument = args != null && args.length > 0 ? args[0] : "";
    ThresholdCheckerConfiguration thresholdCheckerConfiguration = new ThresholdCheckerConfigurationBuilder().with
        (argument).build();
    new TestThresholdChecker().throwExceptionIfThresholdsAreCrossed(thresholdCheckerConfiguration);
  }

  private void throwExceptionIfThresholdsAreCrossed(ThresholdCheckerConfiguration thresholdCheckerConfiguration) throws
      FailingTestsThresholdExceededException, SkippedTestsThresholdExceededException, InvalidXmlReportDirectoryException {
    List<File> xmlReports = new XmlReportFile(thresholdCheckerConfiguration.getPath()).getTestReports();
    throwExceptionIfFailingTestsCrossesThreshold(xmlReports, thresholdCheckerConfiguration.getMaxNumberAllowedFailingTests());
    throwExceptionIfSkippedTestsCrossesThreshold(xmlReports, thresholdCheckerConfiguration.getMaxNumberAllowedSkippedTests());
  }

  private void throwExceptionIfFailingTestsCrossesThreshold(List<File> xmlReports, int maxNumberAllowedFailingTests) throws
      FailingTestsThresholdExceededException {
    Integer numberFailingOfTests = getCount(xmlReports, TestSuite::getNumberOfFailingTests);

    if (numberFailingOfTests > maxNumberAllowedFailingTests) {
      throw new FailingTestsThresholdExceededException(formatExceptionMessage
          (FailingTestsThresholdExceededException.class, maxNumberAllowedFailingTests, numberFailingOfTests));
    }
  }

  private void throwExceptionIfSkippedTestsCrossesThreshold(List<File> xmlReports, int maxNumberAllowedSkippedTests) throws SkippedTestsThresholdExceededException {
    Integer numberOfSkippedTests = getCount(xmlReports, TestSuite::getNumberOfSkippedTests);

    if (numberOfSkippedTests > maxNumberAllowedSkippedTests) {
      throw new SkippedTestsThresholdExceededException(formatExceptionMessage
          (SkippedTestsThresholdExceededException.class, maxNumberAllowedSkippedTests, numberOfSkippedTests));
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

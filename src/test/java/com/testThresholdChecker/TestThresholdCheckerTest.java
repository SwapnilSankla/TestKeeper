package com.testThresholdChecker;

import org.junit.Test;

public class TestThresholdCheckerTest {
  @Test
  public void main_noExceptionIsThrown_whenThresholdsAreMet() throws FailingTestsThresholdExceededException,
      SkippedTestsThresholdExceededException, InvalidXmlReportDirectoryException {
    String[] args = { "src/test/resources/surefire-dummy-reports/happy" };
    TestThresholdChecker.main(args);
  }

  @Test(expected = FailingTestsThresholdExceededException.class)
  public void main_throwsFailingTestsThresholdExceededException_forExceedingFailingThreshold() throws
      FailingTestsThresholdExceededException, SkippedTestsThresholdExceededException, InvalidXmlReportDirectoryException {
    String[] args = { "src/test/resources/surefire-dummy-reports/failing" };
    TestThresholdChecker.main(args);
  }

  @Test(expected = SkippedTestsThresholdExceededException.class)
  public void main_throwsSkippedTestsThresholdExceededException_forExceedingFailingThreshold() throws
      FailingTestsThresholdExceededException, SkippedTestsThresholdExceededException, InvalidXmlReportDirectoryException {
    String[] args = { "src/test/resources/surefire-dummy-reports/skipped" };
    TestThresholdChecker.main(args);
  }

  @Test(expected = InvalidXmlReportDirectoryException.class)
  public void main_throwsInvalidXmlReportDirectoryException_forInvalidDirectory() throws
      FailingTestsThresholdExceededException, SkippedTestsThresholdExceededException, InvalidXmlReportDirectoryException {
    String[] args = { "" };
    TestThresholdChecker.main(args);
  }
}
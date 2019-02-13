package com.testThresholdChecker;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestThresholdCheckerTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void main_noExceptionIsThrown_whenThresholdsAreMet() throws FailingTestsThresholdExceededException,
      SkippedTestsThresholdExceededException, InvalidXmlReportDirectoryException, InvalidArgumentsException {
    String[] args = { "src/test/resources/surefire-dummy-reports/happy" };
    TestThresholdChecker.main(args);
  }

  @Test
  public void main_throwsFailingTestsThresholdExceededException_forExceedingFailingThreshold() throws
      InvalidXmlReportDirectoryException, InvalidArgumentsException, FailingTestsThresholdExceededException, SkippedTestsThresholdExceededException {
    String[] args = { "src/test/resources/surefire-dummy-reports/failing" };
    expectedException.expect(FailingTestsThresholdExceededException.class);
    expectedException.expectMessage("com.testThresholdChecker.FailingTestsThresholdExceededException: Limit is 1 current count is 2");
    TestThresholdChecker.main(args);
  }

  @Test
  public void main_throwsSkippedTestsThresholdExceededException_forExceedingFailingThreshold() throws
      FailingTestsThresholdExceededException, SkippedTestsThresholdExceededException, InvalidXmlReportDirectoryException, InvalidArgumentsException {
    String[] args = { "src/test/resources/surefire-dummy-reports/skipped" };
    expectedException.expect(SkippedTestsThresholdExceededException.class);
    expectedException.expectMessage("com.testThresholdChecker.SkippedTestsThresholdExceededException: Limit is 1 current count is 2");
    TestThresholdChecker.main(args);
  }

  @Test(expected = InvalidXmlReportDirectoryException.class)
  public void main_throwsInvalidXmlReportDirectoryException_forInvalidDirectory() throws
      FailingTestsThresholdExceededException, SkippedTestsThresholdExceededException, InvalidXmlReportDirectoryException, InvalidArgumentsException {
    String[] args = { "ppp" };
    TestThresholdChecker.main(args);
  }

  @Test
  public void main_throwsFailingTestsException_forGivenThreshold() throws
      FailingTestsThresholdExceededException, SkippedTestsThresholdExceededException,
      InvalidXmlReportDirectoryException, InvalidArgumentsException {
    String[] args = { "src/test/resources/surefire-dummy-reports/happy 0"};
    expectedException.expect(FailingTestsThresholdExceededException.class);
    expectedException.expectMessage("com.testThresholdChecker.FailingTestsThresholdExceededException: Limit is 0 " +
        "current count is 1");
    TestThresholdChecker.main(args);
  }

  @Test
  public void main_throwsSkippedTestsTestsException_forGivenThreshold() throws
      FailingTestsThresholdExceededException, SkippedTestsThresholdExceededException,
      InvalidXmlReportDirectoryException, InvalidArgumentsException {
    String[] args = { "src/test/resources/surefire-dummy-reports/happy 5 0" };
    expectedException.expect(SkippedTestsThresholdExceededException.class);
    expectedException.expectMessage("com.testThresholdChecker.SkippedTestsThresholdExceededException: Limit is 0 " +
        "current count is 1");
    TestThresholdChecker.main(args);
  }
}
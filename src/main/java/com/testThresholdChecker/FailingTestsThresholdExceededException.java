package com.testThresholdChecker;

public class FailingTestsThresholdExceededException extends Throwable {
  public FailingTestsThresholdExceededException(String s) {
    super(s);
  }
}

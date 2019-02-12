package com.testThresholdChecker.model;

import com.testThresholdChecker.Configuration;
import com.testThresholdChecker.InvalidArgumentsException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.Optional;
import java.util.Properties;

import static java.lang.Integer.parseInt;

@Value
@AllArgsConstructor
public class ThresholdCheckerConfiguration {
  private final String path;
  private final Integer maxNumberAllowedFailingTests;
  private final Integer maxNumberAllowedSkippedTests;

  @Getter
  public static class ThresholdCheckerConfigurationBuilder {
    private Optional<String> path = Optional.empty();
    private Optional<Integer> maxNumberAllowedFailingTests = Optional.empty();
    private Optional<Integer> maxNumberAllowedSkippedTests = Optional.empty();

    public ThresholdCheckerConfigurationBuilder with(String argument) throws InvalidArgumentsException {
      if(argument.isEmpty()) {
        return this;
      }
      String[] arguments = argument.split(" ");
      this.path = Optional.of(arguments[0]);
      if (arguments.length >= 2) {
        this.maxNumberAllowedFailingTests = Optional.of(parseInt(arguments[1]));
        if (arguments.length >= 3) {
          this.maxNumberAllowedSkippedTests = Optional.of(parseInt(arguments[2]));
        }
      }
      return this;

    }

    public ThresholdCheckerConfiguration build() {
      Properties properties = new Configuration().get();
      return new ThresholdCheckerConfiguration(
          path.orElse(properties.getProperty("DEFAULT_REPORT_PATH")),
          maxNumberAllowedFailingTests.orElse(parseInt(properties.getProperty("MAX_NUMBER_ALLOWED_FAILING_TESTS"))),
          maxNumberAllowedSkippedTests.orElse(parseInt(properties.getProperty("MAX_NUMBER_ALLOWED_SKIPPED_TESTS")))
      );
    }
  }
}

package com.testThresholdChecker.model;

import com.testThresholdChecker.InvalidArgumentsException;
import com.testThresholdChecker.model.ThresholdCheckerConfiguration.ThresholdCheckerConfigurationBuilder;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class ThresholdCheckerConfigurationBuilderTest {
  @Test
  public void with_createsBuilderWithPath_whenArgsContains1Element() throws InvalidArgumentsException {
    ThresholdCheckerConfigurationBuilder builder = new ThresholdCheckerConfigurationBuilder().with("path");
    Assert.assertThat(builder.getPath().get(), is("path"));
    Assert.assertFalse(builder.getMaxNumberAllowedFailingTests().isPresent());
    Assert.assertFalse(builder.getMaxNumberAllowedSkippedTests().isPresent());
  }

  @Test
  public void with_createsBuilderWithPathAndFailingThreshold_whenArgsContains2Elements() throws
      InvalidArgumentsException {
    ThresholdCheckerConfigurationBuilder builder = new ThresholdCheckerConfigurationBuilder().with("path 1");
    Assert.assertThat(builder.getPath().get(), is("path"));
    Assert.assertThat(builder.getMaxNumberAllowedFailingTests().get(), is(1));
    Assert.assertFalse(builder.getMaxNumberAllowedSkippedTests().isPresent());
  }

  @Test
  public void with_createsBuilderWithPathAndFailingAndSkippedThreshold_whenArgsContains3Elements() throws
      InvalidArgumentsException {
    ThresholdCheckerConfigurationBuilder builder = new ThresholdCheckerConfigurationBuilder().with("path 1 2");
    Assert.assertThat(builder.getPath().get(), is("path"));
    Assert.assertThat(builder.getMaxNumberAllowedFailingTests().get(), is(1));
    Assert.assertThat(builder.getMaxNumberAllowedSkippedTests().get(), is(2));
  }

  @Test
  public void with_createsBuilderWithPathAndFailingAndSkippedThreshold_whenArgsContainsMoreThan3Elements() throws
      InvalidArgumentsException {
    ThresholdCheckerConfigurationBuilder builder = new ThresholdCheckerConfigurationBuilder().with("path 1 2 3");
    Assert.assertThat(builder.getPath().get(), is("path"));
    Assert.assertThat(builder.getMaxNumberAllowedFailingTests().get(), is(1));
    Assert.assertThat(builder.getMaxNumberAllowedSkippedTests().get(), is(2));
  }

  @Test
  public void build_returnsCorrectConfiguration_fromBuilderState() throws InvalidArgumentsException {
    ThresholdCheckerConfiguration configuration  = new ThresholdCheckerConfigurationBuilder().with("path 1 2 3").build();
    Assert.assertThat(configuration.getPath(), is("path"));
    Assert.assertThat(configuration.getMaxNumberAllowedFailingTests(), is(1));
    Assert.assertThat(configuration.getMaxNumberAllowedSkippedTests(), is(2));
  }

  @Test
  public void build_returnsCorrectConfiguration_fromBuilderStateAndConfiguration() throws InvalidArgumentsException {
    ThresholdCheckerConfiguration configuration  = new ThresholdCheckerConfigurationBuilder().with("path").build();
    Assert.assertThat(configuration.getPath(), is("path"));
    Assert.assertThat(configuration.getMaxNumberAllowedFailingTests(), is(1));
    Assert.assertThat(configuration.getMaxNumberAllowedSkippedTests(), is(1));
  }

  @Test
  public void build_returnsCorrectConfiguration_fromBuilderStateAndConfiguration1() throws InvalidArgumentsException {
    ThresholdCheckerConfiguration configuration  = new ThresholdCheckerConfigurationBuilder().with("path 0")
      .build();
    Assert.assertThat(configuration.getPath(), is("path"));
    Assert.assertThat(configuration.getMaxNumberAllowedFailingTests(), is(0));
    Assert.assertThat(configuration.getMaxNumberAllowedSkippedTests(), is(1));
  }

  @Test
  public void build_returnsCorrectConfiguration_fromBuilderStateAndConfiguration2() throws InvalidArgumentsException {
    ThresholdCheckerConfiguration configuration  = new ThresholdCheckerConfigurationBuilder().with("").build();
    Assert.assertThat(configuration.getPath(), is("target/dummy-surefire-reports"));
    Assert.assertThat(configuration.getMaxNumberAllowedFailingTests(), is(1));
    Assert.assertThat(configuration.getMaxNumberAllowedSkippedTests(), is(1));
  }
}
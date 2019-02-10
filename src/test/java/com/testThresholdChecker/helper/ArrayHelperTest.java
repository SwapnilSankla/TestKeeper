package com.testThresholdChecker.helper;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;

public class ArrayHelperTest {
  @Test
  public void toList_returnsList_forGivenNonEmptyArray() {
    Integer[] arr = new Integer[1];
    ArrayHelper<Integer> arrayHelper = new ArrayHelper<>();
    List<Integer> integers = arrayHelper.toList(arr);
    Assert.assertThat(integers.size(), is(1));
  }

  @Test
  public void toList_returnsEmptyList_forGivenEmptyArray() {
    Integer[] arr = new Integer[0];
    ArrayHelper<Integer> arrayHelper = new ArrayHelper<>();
    List<Integer> integers = arrayHelper.toList(arr);
    Assert.assertThat(integers.size(), is(0));
  }
}
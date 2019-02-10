package com.testThresholdChecker.helper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayHelper<T> {
  public T[] getOrDefault(T[] arr) {
    return arr != null ? arr : (T[]) new Object[0];
  }

  public List<T> toList(T[] arr) {
    return Arrays.stream(arr).collect(Collectors.toList());
  }
}

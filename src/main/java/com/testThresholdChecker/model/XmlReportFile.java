package com.testThresholdChecker.model;

import com.testThresholdChecker.InvalidXmlReportDirectoryException;
import com.testThresholdChecker.helper.ArrayHelper;

import java.io.File;
import java.util.List;

public class XmlReportFile extends File {

  private final ArrayHelper<File> fileArrayHelper = new ArrayHelper<>();

  public XmlReportFile(String pathname) {
    super(pathname);
  }

  public List<File> getTestReports() throws InvalidXmlReportDirectoryException {
    File[] arr = listFiles(this::xmlTestReportFilter);
    if(arr == null) {
      throw new InvalidXmlReportDirectoryException("Invalid directory: " + getName());
    }
    return fileArrayHelper.toList(fileArrayHelper.getOrDefault(arr));
  }

  private boolean xmlTestReportFilter(File pathname) {
    return pathname.isFile() && pathname.getName().endsWith(".xml");
  }
}

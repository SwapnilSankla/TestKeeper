# TestKeeper

[![Build Status](https://travis-ci.org/SwapnilSankla/TestThresholdChecker.svg?branch=master)](https://travis-ci.org/SwapnilSankla/TestThresholdChecker)

Many a times we do development on top of the code written by someone else. 
Sometimes such code has tests, sometimes it doesn't. However it is also a common scenario when tests are written but not maintained. 

*If you are working on such codebase then please read ahead.*

* Just enabling the tests is not enough as many tests start failing immediately.
* Fixing the failing or unmaintained tests in one go is not practical. However you would still want to have a control that at least going forward we should not add any failing test and also not break the tests which were passing earlier.
* So basically we need a threshold mechanism where you specify the maximum test which are allowed to fail/skip, if threshold is exceeded then build should fail otherwise it should go through.
* Eventually this threshold can be brought down to zero.

This repository is a simple utility which allows you to achieve the above goal for Java codebase where surefire-reports are already generated. It is built with Maven support.

Run `validateTestReports.sh` script under scripts folder as below.

`validateTestReports.sh <SurefireReportDirectoryPath> <MaxAllowedFailingTests> <MaxAllowedSkippedTests>`

Parameter              | Default Value            | Mandatory 
-----------------------|--------------------------|------------
Path                   | /target/surefire-reports | false
MaxAllowedFailingTests | 0                        | false
MaxAllowedSkippedTests | 0                        | false

End goal is to release as a maven plugin.


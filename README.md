# Test Threshold Checker

[![Build Status](https://travis-ci.org/SwapnilSankla/TestThresholdChecker.svg?branch=master)](https://travis-ci.org/SwapnilSankla/TestThresholdChecker)

There are many occasions when we are supposed to do development on top of the code written by someone else. 
Sometimes such code has tests, sometimes it doesn't. However it is also a common scenario when tests are written but not maintained.

*If you are working on codebase then please read ahead.*

* Fixing the failing or unmaintained tests in one go is not practical. However you would still need a control that at least going forward 
we should not add failing any test and also not break the tests which were passing earlier.
* So basically we need a threshold mechanism where you specify the maximum test which are allowed to fail/skip, if threshold is exceeded
then build should fail otherwise it should go through.

This repository is a simple utility which allows you to achieve the above goal for Java codebase where surefire-reports are already
generated. It is built with Maven support.

Currently the thresholds are specified as application configuratoin however going forward these can be stored as environment variables

Run `validateTestReports.sh` script under scripts folder with path of the surefire-reports and that's it!



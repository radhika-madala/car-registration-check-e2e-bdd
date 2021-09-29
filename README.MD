car-tax-check-test

This project currently works with chrome version 94.X but it can be extended to any browser if needed
Application is configured to run in headless mode.
Headless configuration provided in application.properties

To run the tests:

a) Using maven command: mvn test -Dtest=RunCucumberTest

b) Run using Junit in IDE (Eclipse/IntelliJ) : Run the RunCucumberTest.java as Junit test

Note: If the Chrome browser driver is not already trusted in local Mac, it may complain about, need to trust.

Note: All the code developed and tested in macbook

Chrome browser version used: 94.0.4606.61 (Official Build) (x86_64)


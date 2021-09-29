@ALL
Feature: As a user I would like to check car details

    @CAR_CHECK_WITH_DATA_FILE
    Scenario Outline: Validate given car details in test file are correct against the output
      Given user has the test data file "<inputFile>"
      When user submits car registration numbers to car tax check website
      Then the outcome should match to the contents of this file "<outputFile>"
      Examples:
      |inputFile         |outputFile         |
      |data/car_input.txt|data/car_output.txt|
Feature: this is profile test case , info of user will return with profile service . service will be mock in this tests

  Scenario: get profile of user
    When user call getProfile service for ssn "0013376071"
    Then service will return profile of user with ssn "0013376071"
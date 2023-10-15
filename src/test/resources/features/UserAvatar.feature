Feature: test for save custom user avatar and delete it,

  Scenario: client will save a new user avatar
    Given user create new user avatar for ssn "0013376071"
    When client calls save service
    Then service will save user avatar

  Scenario: user will get avatar
    When user calls get avatar for ssn "0013376071"
    Then service will return userAvatar formula for ssn "0013376071"

  Scenario: user wants for remove avatar
    When user calls delete service for ssn "0013376071"
    Then service will delete user avatar


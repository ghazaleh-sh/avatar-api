Feature: test default avatar service  , default avatars is list fo avatars that is defaults for all users , so user can edit and customize each of theme and save it for own . this feature file will save a default avatar and get list of it

  Scenario: admin will create a new default avatar
    Given admin create a new default avatar
    When admin call save service
    Then service will save default avatar and return true

  Scenario: users get list of default avatars
    When  user call get default avatar service
    Then service will return list of default avatars with 1 item
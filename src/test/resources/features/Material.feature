Feature: test material services , materials is items that can create avatars with it . this feature will test get list of materials and get file of materials

  Scenario: client wants to save materials
    Given the client create materials
    When the client call save material service
    Then the client will get true and materials will save in database


  Scenario: client wants to get list of materials
    When the client calls /get_material_list
    Then Size of items in Material is 1
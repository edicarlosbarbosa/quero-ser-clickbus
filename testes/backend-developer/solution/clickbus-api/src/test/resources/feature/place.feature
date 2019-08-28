@Place
Feature: CRUD for Place API

  Scenario: Creating a place
    When I try to create a place
    Then The service returns the place

  Scenario: Geting a specific place
    Given That there is a recorded place
    When I try to get the place by id
    Then The service returns the place

  Scenario: Editing a place
    Given That there is a recorded place
    When I try to update the place
    Then The service returns the place

  Scenario: Deleting a place
    Given That there is a recorded place
    When I try to delete the place by id
    Then the service will not find the place

  Scenario: Listing places and filter by name
    Given That there are 5 registered places
    And That there is a recorded place with name Teste
    When I get the list of places
    Then the service returns a place list with name Teste

  Scenario: Listing places without filter
    Given That there are 5 registered places
    When I get the list of places without filter
    Then the service returns a place list without filter
Feature: Arkivmeta API Integration Test

  Background:
    * url url

  Scenario: Arkiv API /metadata/arketyper - get all arketyper

    Given path '/metadata/arketyper'
    When method get
    Then status 200
    
    # should be an array of strings with size 10
    # https://karatelabs.github.io/karate/#schema-validation
    And match response == '#[10] #string'
Feature: Arkivmeta API Integration Test

  Background:
    * url url
    
  Scenario: Arkiv API /metadata/daekningsomraader - get all daekningsomraader

    Given path '/metadata/daekningsomraader'
    When method get
    Then status 200
    
    # should be an array of strings with size 14
    # https://karatelabs.github.io/karate/#schema-validation
    And match response == '#[14] #string'


  Scenario: Arkiv API /metadata/daekningsomraader - search daekningsomraader insensitive

    Given path '/metadata/daekningsomraader'
    And param daekningsomraade = 'island'
    When method get
    Then status 200
    
    # should be an array of strings with size 1
    # https://karatelabs.github.io/karate/#schema-validation
    And match response == '#[1] #string'
    And match response == ["Island"]
    And def firstDaekningsomraader = response

    Given path '/metadata/daekningsomraader'
    And param daekningsomraade = 'ISLAND'
    When method get
    Then status 200

    And match response == firstDaekningsomraader

  
  Scenario: Arkiv API /metadata/daekningsomraader - search containing daekningsomraader

    Given path '/metadata/daekningsomraader'
    And param daekningsomraade = 'land'
    When method get
    Then status 200
    
    # should be an array of strings with size 3
    # https://karatelabs.github.io/karate/#schema-validation
    And match response == '#[3] #string'
    And match response ==  ["Grønland","Island","Sønderjylland"]
Feature: Historiske Dokumenter API Integration Test

  Background:
    * url url

  Scenario: Get all herredsnavn ASC

    Given path '/metadata/herredsnavn'
    When method get
    Then status 200

    # should be an array of strings with size 10
    # https://karatelabs.github.io/karate/#schema-validation
    And match response == '#array'
    And match response ==
    """
    [
        "arbejdsjournaler",
        "hartkornsekstrakter",
        "inge Lehman og kollegaer fra Geodaetisk Institut",
        "kortfortegnelser",
        "oversigt over Generalstabens opmaalinger (1809-1899)",
        "sogneprotokoller"
    ]
    """

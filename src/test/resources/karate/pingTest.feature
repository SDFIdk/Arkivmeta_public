Feature: Arkivmeta API Integration Test

  Background:
    * url url


  Scenario: Arkiv API is running

    Given path '/ping'
    When method get
    Then status 200
    And match response ==
    """
      {
        "message":"PONG"
      }
    """
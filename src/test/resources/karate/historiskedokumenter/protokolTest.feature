Feature: Historiske Dokumenter API Integration Test

  Background:
    * url url


  Scenario: GET - Returns the 100 first json objects of all historiske dokumenter

    Given path '/protokol'
    When method get
    Then status 200
    And match response.dokumenter == '#[100]'

  Scenario: POST - Returns the 100 first json objects of all historiske dokumenter

    Given path '/protokol'
    When method post
    Then status 200
    And match response.dokumenter == '#[100]'

  Scenario: GET - Search not existing sognenavn

    Given path '/protokol'
    And param sognenavn = 'findes ikke'
    When method get
    Then status 200
    And match response.total == 0

  Scenario: POST - Search not existing sognenavn

    Given path '/protokol'
    And request
    """
    {
        "sognenavn": "findes ikke"
    }
    """
    When method post
    Then status 200
    And match response.total == 0

  Scenario: GET - Search sognenavn insensitive

    Given path '/protokol'
    And param sognenavn = 'dronninglund'
    When method get
    Then status 200
    # match the response with the keys from the json objects
    And match response == { total: '#present', dokumenter: '#present' }
    # assigning response to lower
    Then def lower = response

    Given path '/protokol'
    And param sognenavn = 'DRONNINGLUND'
    When method get
    Then status 200
    # match the new response match with the variable lower
    And match response == lower

  Scenario: POST - Search sognenavn insensitive

    Given path '/protokol'
    And request
    """
    {
      "sognenavn": "dronninglund"
    }
    """
    When method post
    Then status 200
    # match the response with the keys from the json objects
    And match response == { total: '#present', dokumenter: '#present' }
    # assigning response to lower
    Then def lower = response

    Given path '/protokol'
    And request
    """
    {
      "sognenavn": "DRONNINGLUND"
    }
    """
    When method post
    Then status 200
    # match the new response match with the variable lower
    And match response == lower


  Scenario: Search mulitple dokumentsamling

    Given path '/protokol'
    And param dokumentsamling = 'sogneprotokoller'
    When method get
    Then status 200
    # match the response with the keys from the json objects
    And match response == { total: '#present', dokumenter: '#present' }
    # assigning response.total to firstKortvaerkTotal
    Then def firstDokumentsamlingTotal = response.total

    Given path '/protokol'
    And param dokumentsamling = 'hartkornsekstrakter'
    When method get
    Then status 200
    # match the response with the keys from the json objects
    And match response == { total: '#present', dokumenter: '#present' }
    # assigning response.total to secondKortvaerkTotal
    Then def secondDokumentsamlingTotal = response.total

    Given path '/protokol'
    # Use the delimiter way right now, because switchboards does not understand string arrays
    And param dokumentsamling = 'sogneprotokoller'
    And param dokumentsamling = 'hartkornsekstrakter'
    When method get
    Then status 200
    # match the response with the keys from the json objects
    And match response == { total: '#present', dokumenter: '#present' }
    Then match response.total == firstKortvaerkTotal
    Then match response.total == secondKortvaerkTotal

  Scenario: Search with geometry

    Given path '/protokol'
    And param geometri = 'POINT(667450 6163387)'
    When method get
    Then status 200
    And match response.dokumenter == '#[3]'

  Scenario: Limit -1

    Given path '/protokol'
    And param limit = -1
    When method get
    Then status 400
    And match response ==
    """
    {
      "status": "BAD_REQUEST",
      "message": "getProtokol.limit: must be greater than or equal to 1",
      "errors": [
      "jakarta.validation.ConstraintViolationException: getProtokol.limit: must be greater than or equal to 1"
      ]
    }
    """

  Scenario: Limit 1000

    Given path '/protokol'
    And param limit = 1001
    When method get
    Then status 400
    And match response ==
    """
    {
        "status": "BAD_REQUEST",
        "message": "getProtokol.limit: must be less than or equal to 1000",
        "errors": [
            "jakarta.validation.ConstraintViolationException: getProtokol.limit: must be less than or equal to 1000"
        ]
    }
    """

  Scenario: direction casesensitive

    Given path '/protokol'
    And param direction = 'ASC'
    When method get
    Then status 400
    And match response ==
    """
    {
        "status": "BAD_REQUEST",
        "message": "getProtokol.direction: must match \"asc|desc\"",
        "errors": [
            "jakarta.validation.ConstraintViolationException: getProtokol.direction: must match \"asc|desc\""
        ]
    }
    """

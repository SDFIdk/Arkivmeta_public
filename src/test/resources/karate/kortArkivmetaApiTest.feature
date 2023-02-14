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

  
  Scenario: Arkiv API /kort - returns the 100 first json objects of all korts

    Given path '/kort'
    When method get
    Then status 200
    And match response.kort == '#[100]'

  
  Scenario: Arkiv API /kort - postmethod that returns the 10 first json objects of all korts

    Given path '/kort'
    And header Accept = 'application/json'
    # https://intuit.github.io/karate/#header
    # Note that Content-Type had to be enclosed in quotes in the JSON above because the “-” (hyphen character) would cause problems otherwise.
    And configure headers = { 'token': 'b82bfa76a9e21f3f7a6adebad1d40702', 'Content-Type': 'application/json' }
    And request { daekningsomraade: ['Sønderjylland', 'Slesvig'], limit: 15, sort: 'daekningsomraade', direction: 'asc' }
    When method post
    Then status 200
    And match response.kort == '#[15]'
    And match response.kort[*].daekningsomraade contains deep ['Danmark', 'Slesvig']

  
  Scenario: Arkiv API /kort - search not existing title

    Given path '/kort'
    And param titel = 'findes ikke'
    When method get
    Then status 200
    And match response.total == 0

  
  Scenario: Arkiv API /kort - search titel insensitive
    # This test does not match but why??

    Given path '/kort'
    And param titel = 'øster'
    And param sort = 'titel'
    When method get
    Then status 200
    # match the response with the keys from the json objects
    And match response == { total: '#present', kort: '#present' }
    # assigning response to lower
    Then def lower = response

    Given path '/kort'
    And param titel = 'Øster'
    And param sort = 'titel'
    When method get
    Then status 200
    # match the new response match with the variable lower
    And match response == lower

  
  Scenario: Arkiv API /kort - search mulitple kortvaerks

    Given path '/kort'
    And param kortvaerk = 'Videnskabernes Selskab'
    When method get
    Then status 200
    # match the response with the keys from the json objects
    And match response == { total: '#present', kort: '#present' }
    # assigning response.total to firstKortvaerkTotal
    Then def firstKortvaerkTotal = response.total

    Given path '/kort'
    And param kortvaerk = 'Videnskabernes Selskab, koncept'
    When method get
    Then status 200
    # match the response with the keys from the json objects
    And match response == { total: '#present', kort: '#present' }
    # assigning response.total to secondKortvaerkTotal
    Then def secondKortvaerkTotal = response.total

    Given path '/kort'
    # Use the delimiter way right now, because switchboards does not understand string arrays
    And param kortvaerk = 'Videnskabernes Selskab|Videnskabernes Selskab, koncept'
    When method get
    Then status 200
    # match the response with the keys from the json objects
    And match response == { total: '#present', kort: '#present' }
    Then match response.total == firstKortvaerkTotal + secondKortvaerkTotal

  
  Scenario: Arkiv API /kort - search maalestok

    Given path '/kort'
    And param maalestok = '1:250000'
    When method get
    Then status 200
    # assigning
    And def maalestok = '1:250000'

    # Check if 1:250000 match the value in the JSON array maalestok 
    # https://intuit.github.io/karate/#match-contains-deep
    Then match response.kort[*].maalestok contains deep maalestok

  
  Scenario: Arkiv API /kort - search with gaeldendefra and gaeldendetil

    Given path '/kort'
    And param gaeldendefra = '2000'
    And param gaeldendetil = '2000'
    When method get
    Then status 200
    And def gaeldendefra = '2000'
    And def gaeldendetil = '2000'
    And def responseGaeldendeFra = get response.kort[*].gaeldendefra
    And def responseGaeldendeTil = get response.kort[*].gaeldendetil

    # https://intuit.github.io/karate/#match-contains-deep

    Then match responseGaeldendeTil contains deep '#? _ >= gaeldendefra'
    And match responseGaeldendeFra contains deep '#? _ <= gaeldendetil'

  
  Scenario: Arkiv API /kort - search with gaeldendefra

    Given path '/kort'
    And param gaeldendefra = '2000'
    When method get
    Then status 200
    And def gaeldendefra = '2000'
    And def responseGaeldendeFra = get response.kort[*].gaeldendefra
    And def responseGaeldendeTil = get response.kort[*].gaeldendetil

    # https://intuit.github.io/karate/#match-contains-deep

    And match responseGaeldendeFra contains deep '#? _ >= gaeldendefra || _ < gaeldendefra && responseGaeldendeTil >= gaeldendefra'

  
  Scenario: Arkiv API /kort - search with gaeldendetil

    Given path '/kort'
    And param gaeldendetil = '1800'
    When method get
    Then status 200
    And def gaeldendetil = '1800'
    And def responseGaeldendeFra = get response.kort[*].gaeldendefra
    And def responseGaeldendeTil = get response.kort[*].gaeldendetil

    # https://intuit.github.io/karate/#match-contains-deep

    Then match responseGaeldendeTil contains deep '#? _ <= gaeldendetil || _ > gaeldendetil && responseGaeldendeFra <= gaeldendetil'


  Scenario: Arkiv API /kort - search with geometry

    Given path '/kort'
    And param geometri = 'POINT(12.226727129244841 55.86164621853605)'
    When method get
    Then status 200
    And match response.kort == '#[100]'
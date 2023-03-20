Feature: Arkivmeta API Integration Test

  Background:
    * url url

  
  Scenario: Arkiv API /metadata/kortvaerker - check if all kortvaerker is represented

    Given path '/metadata/arketyper/kortvaerker'
    When method get
    Then status 200
    And def allKortvaerker = get response[*].kortvaerker
    And def convertedArrayOfAllKortvaerker = []
    And def fun = function(i){ karate.appendTo(convertedArrayOfAllKortvaerker, i) }
    And karate.forEach(allKortvaerker, fun)

    Given path '/metadata/kortvaerker'
    When method get
    Then status 200
    And def responeKortvaerker = response

    Then match responeKortvaerker contains only convertedArrayOfAllKortvaerker

  
  Scenario: Arkiv API /metadata/kortvaerker - limit returned kortvaerker with arketype matrikelkort

    Given path '/metadata/arketyper/kortvaerker'
    When method get
    Then status 200
    And def matrikelkortKortvaerker = get response[5].kortvaerker

    Given path '/metadata/kortvaerker'
    And param arketype = 'matrikelkort'
    When method get
    Then status 200
    And def responeKortvaerker = response
    Then match responeKortvaerker contains only matrikelkortKortvaerker


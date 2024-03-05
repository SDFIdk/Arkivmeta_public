Feature: Historiske Dokumenter API Integration Test

  Background:
    * url url

  Scenario: Arkiv API /dokument/{arketype}/{id}' - get dokument by id

    Given path 'dokument/andetdokument/500'
    When method get
    Then status 200

    # Does the returned kort match?
    And match response ==
    """
    {
       "id":"andetdokument/500",
       "arketype":"andetdokument",
       "titel":"Elbo",
       "alternativtitel":null,
       "registreringfra":"2022-09-27T15:13:00",
       "registreringtil":null,
       "uniktdokumentnavn":"Elbo AJ_116.pdf arbejdsjournaler",
       "stinavn":"\\51 - matrikelkort samt tilknyttede\\51-99 arbejdsjournaler\\ARBEJDSJOURNALER\\ARBEJDSJOURNALER",
       "bemaerkning":"116",
       "geometri":null,
       "omraade":"Danmark",
       "filer":[
          "https://api.dataforsyningen.dk/rest/arkivkort/iiif/3/LzUxIC0gbWF0cmlrZWxrb3J0IHNhbXQgdGlsa255dHRlZGUvNTEtOTkgYXJiZWpkc2pvdXJuYWxlci9BUkJFSkRTSk9VUk5BTEVSL0FSQkVKRFNKT1VSTkFMRVIvQUpfMTE2LnBkZg=="
       ],
       "datatype":"pdf",
       "filtype":"journal",
       "dokumentsamling":"arbejdsjournaler",
       "herredsnavn":null,
       "herredsnummer":null,
       "protokoltype":null,
       "sogneid":null,
       "sognenavn":null
    }
    """
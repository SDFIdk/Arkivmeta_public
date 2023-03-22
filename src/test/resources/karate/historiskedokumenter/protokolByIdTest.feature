Feature: Historiske Dokumenter API Integration Test

  Background:
    * url url


  Scenario: Arkiv API /protokol/{dokumentsamling}/{id}' - get protokol by id

    Given path 'protokol/andetdokument/500'
    When method get
    Then status 200

    # Does the returned kort match?
    And match response ==
    """
    {
        "id": "andetdokument/500",
        "arketype": "andetdokument",
        "titel": "Elbo",
        "registreringfra": "2022-09-27T15:13:00",
        "uniktdokumentnavn": "Elbo AJ_116.pdf arbejdsjournaler",
        "stinavn": "\\51 - matrikelkort samt tilknyttede\\51-99 arbejdsjournaler\\ARBEJDSJOURNALER\\ARBEJDSJOURNALER",
        "filer": [
            "https://api.dataforsyningen.dk/rest/arkivkort/iiif/3/LzUxIC0gbWF0cmlrZWxrb3J0IHNhbXQgdGlsa255dHRlZGUvNTEtOTkgYXJiZWpkc2pvdXJuYWxlci9BUkJFSkRTSk9VUk5BTEVSL0FSQkVKRFNKT1VSTkFMRVIvQUpfMTE2LnBkZg=="
        ],
        "dokumentsamling": "arbejdsjournaler",
        "bemaerkning": "116",
        "datatype": "pdf",
        "filtype": "journal"
    }
    """
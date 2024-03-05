Feature: Arkivmeta API Integration Test

  Background:
    * url url

  
  Scenario: Arkiv API /kort/{arketype}/{id}' - get kort by id

    Given path '/kort/historiskeflyfoto/73236'
    When method get
    Then status 200
    
    # Does the returned kort match?
    And match response ==
    """
    {
       "id":"historiskeflyfoto/73236",
       "alternativtitel":null,
       "arketype":"historiskeflyfoto",
       "bemaerkning":null,
       "daekningsomraade":[
          "Danmark"
       ],
       "filer":[
          "https://api.dataforsyningen.dk/rest/arkivkort/iiif/3/Lzc5IC0gZmx5Zm90byBHcsO4bmxhbmQvSUdJUyAyMDE5LzQ3My9jb21wcmVzc2VkXzEwLy9ERDIwMl82NjUxLmpwZw=="
       ],
       "gaeldendefra":1950,
       "gaeldendetil":1975,
       "geometri":null,
       "kortbladnummer":null,
       "kortvaerk":"Flyfoto 1940-1960",
       "maalestok":"Ukendt",
       "originalkortprojektion":null,
       "originalehjoernekoordinater":null,
       "registreringfra":"2021-08-06T17:12:53",
       "registreringtil":null,
       "titel":"flyfoto 1940-1960 rute D202 foto nr 6651",
       "uniktkortnavn":"flyfoto 1940-1960 rute D202 foto nr 6651 DD202_6651.jpg 1950",
       "daasenummer":"473",
       "farveskalatype":"sort-hvid 8 bit",
       "flyvehoejde":2440.0,
       "flyverute":"D202",
       "fotocenterxkoordinat":null,
       "fotocenterykoordinat":null,
       "fotonummer":"6651",
       "fototid":null,
       "fotovinkel":"skråfoto",
       "kameraid":"Kameranr: 541",
       "producent":null
    }
    """
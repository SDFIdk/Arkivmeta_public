Feature: Arkivmeta API Integration Test

  Background:
    * url url

  
  Scenario: Arkiv API /kort/{id}' - get kort by id

    Given path '/kort/4567d2e9-4e33-46a9-8638-04a1af229778'
    When method get
    Then status 200
    
    # Does the returned kort match?
    And match response ==
    """
    {
        "id": "4567d2e9-4e33-46a9-8638-04a1af229778",
        "kortgruppe": "Tematiske Kort",
        "titel": "Nyborg",
        "alternativtitel": "D242, D243",
        "originalkortprojektion": null,
        "bemaerkning": "Bog: Sydjylland/m Fyn.",
        "gaeldendeperiode_gaeldendefra": 1957,
        "gaeldendeperiode_gaeldendetil": 1982,
        "geometri": "SRID=4326;MULTIPOLYGON(((10.673453 55.4056,10.672441 55.27029,10.968673 55.269207,10.970705 55.404514,10.673453 55.4056)))",
        "maalestok": "1:0000",
        "kortbladnummer": "'A 3618'",
        "kortvaerk": "DK flyfoto ruteoversigt 1961-1970",
        "daekningsomraade": [
            "Danmark"
        ],
        "filer": [
            "https://api.dataforsyningen.dk/rest/arkivkort/iiif/3/Lzg3IC0gZmx5Zm90byBydXRlb3ZlcnNpZ3QgRGFubWFyay84Ny0wNSBydXRlb3ZlcnNpZ3Rza29ydCAxOTYxLTE5NzAvNTA4L3JvXzM2MThfNC5qcGVn"
        ],
        "aarfordata": null,
        "aarforenkeltrettelser": [],
        "aarforfotografering": null,
        "aarforfotogrametriskudtegning": null,
        "aarforhenlaeggelse": null,
        "aarforkompleteteretimarken": null,
        "aarforkortproeve": null,
        "aarforlineaerrettelse": null,
        "aarformaalt": null,
        "aarforopmaalingsluttet": null,
        "aarforpunktgrundlag": null,
        "aarforrettelse": [],
        "aarfortopografi": null,
        "aarforudarbejdelse": null,
        "aarforudarbejdetmateriale": 1957,
        "aarforudgivelse": null,
        "aarforudskiftning": null,
        "aarforudtegning": null,
        "aarforvejdata": null,
        "daasenummer": null,
        "farveskalatype": null,
        "flyvehoejde": null,
        "flyverute": null,
        "fotonummer": null,
        "fototid": null,
        "fotovinkel": null,
        "kameraid": null,
        "kortart": null,
        "opmaaltaf": null,
        "plannr": null,
        "producent": null,
        "tegner": null,
        "udgiver": null,
        "udskiftetaf": null,
        "version": null
    }
    """
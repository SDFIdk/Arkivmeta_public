Feature: Arkivmeta API Integration Test

  Background:
    * url url


  Scenario: Return openapi specification

    Given path '/v3/api-docs'
    When method get
    Then status 200
    And match response ==
    """
    {
      "openapi": "3.1.0",
      "info": {
        "title": "Arkivmeta",
        "description": "APIet __Arkivmeta__ giver adgang til at søge i metadata for en større samling historiske kort, dokumenter og benytte resultatet til at fremvise det skannede materiale.\n\nTil adgang benyttes Dataforsyningens brugeradgang som ved andre tjenester.\n\nStier til kortfiler følger [IIIF specifikationen](https://iiif.io/) og kan vises med en viser, der understøtter dette.\n",
        "version": "3.0.0"
      },
      "servers": [
        {
          "url": "https://api.dataforsyningen.dk/rest/arkivmeta_test/v3",
          "description": "Generated server url"
        }
      ],
      "security": [
        {
          "HeaderToken": []
        },
        {
          "QueryToken": []
        }
      ],
      "tags": [
        {
          "name": "KortApi",
          "description": "Kort metadata API"
        },
        {
          "name": "DokumentApi",
          "description": "Dokument metadata API"
        }
      ],
      "paths": {
        "/kort": {
          "get": {
            "tags": [
              "KortApi"
            ],
            "summary": "Liste af kort der matcher søgekriterierne",
            "description": "Hvis gaeldendeperiode_gaeldendefra og gaeldendeperiode_gaeldendetil bliver brugt samtidig, er det alle kort, der er indenfor gyldighedsperioden eller har været gældende fra eller gældende til, i perioden",
            "operationId": "getKort",
            "parameters": [
              {
                "name": "daekningsomraade",
                "in": "query",
                "description": "Dækningsområde, se /metadata/daekningsomraader. Hvis der ønskes at søge på flere dækningsområde på en gang, skal man adskille hvert søgekriterie ved at bruge `,`. Eksempel: `daekningsomraade=Slesvig,Danmark`",
                "required": false,
                "schema": {
                  "type": "array",
                  "description": "Geografisk område, som kortet dækker helt eller delvist. For eksempel Danmark, Grønland. Et kort kan have flere dækningsområder.For prøvekort og lignende vil dækningsområdet kunne angives som intet.",
                  "items": {
                    "type": "string"
                  }
                }
              },
              {
                "name": "direction",
                "in": "query",
                "description": "Sorteringsretning, `asc` for stigende, `desc` for faldende",
                "required": false,
                "schema": {
                  "pattern": "asc|desc",
                  "type": "string",
                  "description": "Sorteringsretning, `asc` for stigende, `desc` for faldende",
                  "default": "asc"
                }
              },
              {
                "name": "fritekstsoegning",
                "in": "query",
                "description": "Fritekstsøgning",
                "required": false,
                "schema": {
                  "type": "string",
                  "description": "Fritekstsøgning"
                }
              },
              {
                "name": "gaeldendeperiode_gaeldendefra",
                "in": "query",
                "description": "Starttid for kortets gyldighedsperiode. Angives i hele år, eksempel `1966`. Gyldighedsperiodens starttid er et korts trykke-, tegne-, optage- eller opmålingsår – dvs. det år hvor kortet kan siges at være nyeste kort.",
                "required": false,
                "schema": {
                  "type": "integer",
                  "description": "Starttid for kortets gyldighedsperiode. Angives i hele år, eksempel `1966`. Gyldighedsperiodens starttid er et korts trykke-, tegne-, optage- eller opmålingsår – dvs. det år hvor kortet kan siges at være nyeste kort.",
                  "format": "int32"
                }
              },
              {
                "name": "gaeldendeperiode_gaeldendetil",
                "in": "query",
                "description": "Sluttid for kortets gyldighedsperiode. Angives i hele år, eksempel `1966`. Typisk fordi kortet erstattes af et nyere. Hvis der ikke er fundet en specifik gældende til periode angives et årstal der ligger 50 år efter gældende fra.",
                "required": false,
                "schema": {
                  "type": "integer",
                  "description": "Sluttid for kortets gyldighedsperiode. Angives i hele år, eksempel `1966`. Typisk fordi kortet erstattes af et nyere. Hvis der ikke er fundet en specifik gældende til periode angives et årstal der ligger 50 år efter gældende fra.",
                  "format": "int32"
                }
              },
              {
                "name": "geometri",
                "in": "query",
                "description": "Geometri angives som WKT med SRS = EPSG:4326. Det geografiske område, ofte en polygon, som kortet ligger indenfor.",
                "required": false,
                "schema": {
                  "type": "string",
                  "description": "Geometri angives som WKT med SRS = EPSG:4326. Det geografiske område, ofte en polygon, som kortet ligger indenfor."
                }
              },
              {
                "name": "kortbladnummer",
                "in": "query",
                "description": "Angivelse af geografisk område, f.eks. indenfor kortbladsinddelingen eller ejerlav. For topografiske kort er inddelingen et overordnet grid hvorimod matrikelkort er inddelt efter ejerlav. Kan også være f.eks. administrative inddelinger.",
                "required": false,
                "schema": {
                  "type": "string",
                  "description": "Angivelse af geografisk område, f.eks. indenfor kortbladsinddelingen eller ejerlav. For topografiske kort er inddelingen et overordnet grid hvorimod matrikelkort er inddelt efter ejerlav. Kan også være f.eks. administrative inddelinger."
                }
              },
              {
                "name": "kortgruppe",
                "in": "query",
                "description": "Kortgruppe. Hvis der ønskes at søge på flere kortgrupper på en gang, skal man adskille hvert søgekriterie ved at bruge komma `,`. Eksempel: `kortgruppe=matrikelkort,centimeterkort.`",
                "required": false,
                "schema": {
                  "type": "array",
                  "description": "Kortets kortgruppe.",
                  "items": {
                    "type": "string"
                  }
                }
              },
              {
                "name": "limit",
                "in": "query",
                "description": "Sidestørrelse, dvs. hvor mange poster pr. side",
                "required": false,
                "schema": {
                  "maximum": 1000,
                  "minimum": 1,
                  "type": "integer",
                  "description": "Sidestørrelse, dvs. hvor mange poster pr. side",
                  "format": "int32",
                  "default": 100
                }
              },
              {
                "name": "maalestok",
                "in": "query",
                "description": "Målestoksforhold. Hvis der ønskes at søge på flere målestoksforhold på en gang, skal man adskille hvert søgekriterie ved at bruge `,`. Eksempel: `maalestok=1:40000,1:180000`",
                "required": false,
                "schema": {
                  "type": "array",
                  "description": "Størrelsesforholdet mellem landskabet og kortets repræsentation heraf.",
                  "items": {
                    "type": "string"
                  }
                }
              },
              {
                "name": "offset",
                "in": "query",
                "description": "Offset, dvs. fra hvilken post",
                "required": false,
                "schema": {
                  "type": "integer",
                  "description": "Offset, dvs. fra hvilken post",
                  "format": "int32",
                  "default": 0
                }
              },
              {
                "name": "sort",
                "in": "query",
                "description": "Sorteringsfelt, kan sortere på følgende typer: kortgruppe, gaeldendeperiode_gaeldendefra, gaeldendeperiode_gaeldendetil, kortvaerk, maalestok, titel",
                "required": false,
                "schema": {
                  "type": "string",
                  "description": "Sorteringsfelt, kan sortere på følgende typer: kortgruppe, gaeldendeperiode_gaeldendefra, gaeldendeperiode_gaeldendetil, kortvaerk, maalestok, titel"
                }
              },
              {
                "name": "tegner",
                "in": "query",
                "description": "Tegner på kortet.",
                "required": false,
                "schema": {
                  "type": "string",
                  "description": "Tegner på kortet."
                }
              },
              {
                "name": "titel",
                "in": "query",
                "description": "Titlen på kortet.",
                "required": false,
                "schema": {
                  "type": "string",
                  "description": "Titlen på kortet."
                }
              },
              {
                "name": "kortvaerk",
                "in": "query",
                "description": "Kortværk. Hvis der ønskes at søge på flere kortværker på en gang, skal man angive `kortvaerk` query parameteren for hvert eneste en kortværk man vil søge efter.Eksempel: `kortvaerk=Trap, tegnede kort`&`kortvaerk=Mejer`",
                "required": false,
                "content": {
                  "*/*": {
                    "schema": {
                      "type": "array",
                      "items": {
                        "type": "string"
                      }
                    }
                  }
                }
              }
            ],
            "responses": {
              "200": {
                "description": "Successful Operation",
                "content": {
                  "application/json": {
                    "schema": {
                      "$ref": "#/components/schemas/KortResult"
                    }
                  }
                }
              },
              "401": {
                "description": "Authentication Failure"
              },
              "404": {
                "description": "Not found"
              }
            }
          },
          "post": {
            "tags": [
              "KortApi"
            ],
            "summary": "Liste af kort der matcher søgekriterierne",
            "description": "Hvis gaeldendeperiode_gaeldendefra og gaeldendeperiode_gaeldendetil bliver brugt samtidig, er det alle kort, der er indenfor gyldighedsperioden eller har været gældende fra eller gældende til, i perioden",
            "operationId": "postKort",
            "parameters": [
              {
                "name": "daekningsomraade",
                "in": "query",
                "description": "Dækningsområde, se /metadata/daekningsomraader. Hvis der ønskes at søge på flere dækningsområde på en gang, skal man adskille hvert søgekriterie ved at bruge `,`. Eksempel: `daekningsomraade=Slesvig,Danmark`",
                "required": false,
                "schema": {
                  "type": "array",
                  "description": "Geografisk område, som kortet dækker helt eller delvist. For eksempel Danmark, Grønland. Et kort kan have flere dækningsområder.For prøvekort og lignende vil dækningsområdet kunne angives som intet.",
                  "items": {
                    "type": "string"
                  }
                }
              },
              {
                "name": "direction",
                "in": "query",
                "description": "Sorteringsretning, `asc` for stigende, `desc` for faldende",
                "required": false,
                "schema": {
                  "pattern": "asc|desc",
                  "type": "string",
                  "description": "Sorteringsretning, `asc` for stigende, `desc` for faldende",
                  "default": "asc"
                }
              },
              {
                "name": "fritekstsoegning",
                "in": "query",
                "description": "Fritekstsøgning",
                "required": false,
                "schema": {
                  "type": "string",
                  "description": "Fritekstsøgning"
                }
              },
              {
                "name": "gaeldendeperiode_gaeldendefra",
                "in": "query",
                "description": "Starttid for kortets gyldighedsperiode. Angives i hele år, eksempel `1966`. Gyldighedsperiodens starttid er et korts trykke-, tegne-, optage- eller opmålingsår – dvs. det år hvor kortet kan siges at være nyeste kort.",
                "required": false,
                "schema": {
                  "type": "integer",
                  "description": "Starttid for kortets gyldighedsperiode. Angives i hele år, eksempel `1966`. Gyldighedsperiodens starttid er et korts trykke-, tegne-, optage- eller opmålingsår – dvs. det år hvor kortet kan siges at være nyeste kort.",
                  "format": "int32"
                }
              },
              {
                "name": "gaeldendeperiode_gaeldendetil",
                "in": "query",
                "description": "Sluttid for kortets gyldighedsperiode. Angives i hele år, eksempel `1966`. Typisk fordi kortet erstattes af et nyere. Hvis der ikke er fundet en specifik gældende til periode angives et årstal der ligger 50 år efter gældende fra.",
                "required": false,
                "schema": {
                  "type": "integer",
                  "description": "Sluttid for kortets gyldighedsperiode. Angives i hele år, eksempel `1966`. Typisk fordi kortet erstattes af et nyere. Hvis der ikke er fundet en specifik gældende til periode angives et årstal der ligger 50 år efter gældende fra.",
                  "format": "int32"
                }
              },
              {
                "name": "geometri",
                "in": "query",
                "description": "Geometri angives som WKT med SRS = EPSG:4326. Det geografiske område, ofte en polygon, som kortet ligger indenfor.",
                "required": false,
                "schema": {
                  "type": "string",
                  "description": "Geometri angives som WKT med SRS = EPSG:4326. Det geografiske område, ofte en polygon, som kortet ligger indenfor."
                }
              },
              {
                "name": "kortbladnummer",
                "in": "query",
                "description": "Angivelse af geografisk område, f.eks. indenfor kortbladsinddelingen eller ejerlav. For topografiske kort er inddelingen et overordnet grid hvorimod matrikelkort er inddelt efter ejerlav. Kan også være f.eks. administrative inddelinger.",
                "required": false,
                "schema": {
                  "type": "string",
                  "description": "Angivelse af geografisk område, f.eks. indenfor kortbladsinddelingen eller ejerlav. For topografiske kort er inddelingen et overordnet grid hvorimod matrikelkort er inddelt efter ejerlav. Kan også være f.eks. administrative inddelinger."
                }
              },
              {
                "name": "kortgruppe",
                "in": "query",
                "description": "Kortgruppe. Hvis der ønskes at søge på flere kortgrupper på en gang, skal man adskille hvert søgekriterie ved at bruge komma `,`. Eksempel: `kortgruppe=matrikelkort,centimeterkort.`",
                "required": false,
                "schema": {
                  "type": "array",
                  "description": "Kortets kortgruppe.",
                  "items": {
                    "type": "string"
                  }
                }
              },
              {
                "name": "limit",
                "in": "query",
                "description": "Sidestørrelse, dvs. hvor mange poster pr. side",
                "required": false,
                "schema": {
                  "maximum": 1000,
                  "minimum": 1,
                  "type": "integer",
                  "description": "Sidestørrelse, dvs. hvor mange poster pr. side",
                  "format": "int32",
                  "default": 100
                }
              },
              {
                "name": "maalestok",
                "in": "query",
                "description": "Målestoksforhold. Hvis der ønskes at søge på flere målestoksforhold på en gang, skal man adskille hvert søgekriterie ved at bruge `,`. Eksempel: `maalestok=1:40000,1:180000`",
                "required": false,
                "schema": {
                  "type": "array",
                  "description": "Størrelsesforholdet mellem landskabet og kortets repræsentation heraf.",
                  "items": {
                    "type": "string"
                  }
                }
              },
              {
                "name": "offset",
                "in": "query",
                "description": "Offset, dvs. fra hvilken post",
                "required": false,
                "schema": {
                  "type": "integer",
                  "description": "Offset, dvs. fra hvilken post",
                  "format": "int32",
                  "default": 0
                }
              },
              {
                "name": "sort",
                "in": "query",
                "description": "Sorteringsfelt, kan sortere på følgende typer: kortgruppe, gaeldendeperiode_gaeldendefra, gaeldendeperiode_gaeldendetil, kortvaerk, maalestok, titel",
                "required": false,
                "schema": {
                  "type": "string",
                  "description": "Sorteringsfelt, kan sortere på følgende typer: kortgruppe, gaeldendeperiode_gaeldendefra, gaeldendeperiode_gaeldendetil, kortvaerk, maalestok, titel"
                }
              },
              {
                "name": "tegner",
                "in": "query",
                "description": "Tegner på kortet.",
                "required": false,
                "schema": {
                  "type": "string",
                  "description": "Tegner på kortet."
                }
              },
              {
                "name": "titel",
                "in": "query",
                "description": "Titlen på kortet.",
                "required": false,
                "schema": {
                  "type": "string",
                  "description": "Titlen på kortet."
                }
              },
              {
                "name": "kortvaerk",
                "in": "query",
                "description": "Kortværk. Hvis der ønskes at søge på flere kortværker på en gang, skal man angive `kortvaerk` query parameteren for hvert eneste en kortværk man vil søge efter.Eksempel: `kortvaerk=Trap, tegnede kort`&`kortvaerk=Mejer`",
                "required": false,
                "content": {
                  "*/*": {
                    "schema": {
                      "type": "array",
                      "items": {
                        "type": "string"
                      }
                    }
                  }
                }
              }
            ],
            "responses": {
              "200": {
                "description": "Successful Operation",
                "content": {
                  "application/json": {
                    "schema": {
                      "$ref": "#/components/schemas/KortResult"
                    }
                  }
                }
              },
              "401": {
                "description": "Authentication Failure"
              },
              "404": {
                "description": "Not found"
              }
            }
          }
        },
        "/dokument": {
          "get": {
            "tags": [
              "DokumentApi"
            ],
            "summary": "Liste af dokumenter der matcher søgekriterierne",
            "description": "Disse er parametrerne i DokumentParam",
            "operationId": "getDokument",
            "parameters": [
              {
                "name": "direction",
                "in": "query",
                "description": "Sorteringsretning, `asc` for stigende, `desc` for faldende",
                "required": false,
                "schema": {
                  "pattern": "asc|desc",
                  "type": "string",
                  "description": "Sorteringsretning, `asc` for stigende, `desc` for faldende",
                  "default": "asc"
                }
              },
              {
                "name": "fritekstsoegning",
                "in": "query",
                "description": "Fritekstsøgning",
                "required": false,
                "schema": {
                  "type": "string",
                  "description": "Fritekstsøgning"
                }
              },
              {
                "name": "geometri",
                "in": "query",
                "description": "Geometri angives som WKT med SRS = EPSG:4326. Det geografiske område, ofte en polygon, som kortet ligger indenfor.",
                "required": false,
                "schema": {
                  "type": "string",
                  "description": "Geometri angives som WKT med SRS = EPSG:4326. Det geografiske område, ofte en polygon, som kortet ligger indenfor."
                }
              },
              {
                "name": "herredsnavn",
                "in": "query",
                "description": "Herredets navn.",
                "required": false,
                "schema": {
                  "type": "string",
                  "description": "Herredets navn."
                }
              },
              {
                "name": "herredsnummer",
                "in": "query",
                "description": "Herredets nummer.",
                "required": false,
                "schema": {
                  "type": "integer",
                  "description": "Herredets nummer.",
                  "format": "int32"
                }
              },
              {
                "name": "kortgruppe",
                "in": "query",
                "required": false,
                "schema": {
                  "type": "array",
                  "description": "De dokumenttyper, der skal vises. En kommasepareret liste af typer. Eksempel: `Hartkornsekstrakt, Sogneprotokol.`",
                  "items": {
                    "type": "string"
                  }
                }
              },
              {
                "name": "limit",
                "in": "query",
                "description": "Sidestørrelse, dvs. hvor mange poster pr. side",
                "required": false,
                "schema": {
                  "maximum": 1000,
                  "minimum": 1,
                  "type": "integer",
                  "description": "Sidestørrelse, dvs. hvor mange poster pr. side",
                  "format": "int32",
                  "default": 100
                }
              },
              {
                "name": "offset",
                "in": "query",
                "description": "Offset, dvs. fra hvilken post",
                "required": false,
                "schema": {
                  "minimum": 0,
                  "type": "integer",
                  "description": "Offset, dvs. fra hvilken post",
                  "format": "int32",
                  "default": 0
                }
              },
              {
                "name": "sogneid",
                "in": "query",
                "description": "Sogneid.",
                "required": false,
                "schema": {
                  "type": "integer",
                  "description": "Sogneid.",
                  "format": "int32"
                }
              },
              {
                "name": "sognenavn",
                "in": "query",
                "description": "Sognenavn.",
                "required": false,
                "schema": {
                  "type": "string",
                  "description": "Sognenavn."
                }
              },
              {
                "name": "sort",
                "in": "query",
                "description": "Sorteringsfelt, kan sortere på følgende typer: kortgruppe, herredsnavn, herredsnummer, sogneid, sognenavn, titel",
                "required": false,
                "schema": {
                  "type": "string",
                  "description": "Sorteringsfelt, kan sortere på følgende typer: kortgruppe, herredsnavn, herredsnummer, sogneid, sognenavn, titel"
                }
              },
              {
                "name": "titel",
                "in": "query",
                "description": "Titel på dokumentet.",
                "required": false,
                "schema": {
                  "type": "string",
                  "description": "Titel på dokumentet."
                }
              }
            ],
            "responses": {
              "200": {
                "description": "Successful Operation",
                "content": {
                  "application/json": {
                    "schema": {
                      "$ref": "#/components/schemas/DokumentResult"
                    }
                  }
                }
              },
              "401": {
                "description": "Authentication Failure"
              },
              "404": {
                "description": "Not found"
              }
            }
          },
          "post": {
            "tags": [
              "DokumentApi"
            ],
            "summary": "Liste af dokumenter der matcher søgekriterierne",
            "description": "Disse er parametrerne i DokumentParam",
            "operationId": "postDokument",
            "requestBody": {
              "content": {
                "application/json": {
                  "schema": {
                    "$ref": "#/components/schemas/DokumentParam"
                  }
                }
              },
              "required": true
            },
            "responses": {
              "200": {
                "description": "Successful Operation",
                "content": {
                  "application/json": {
                    "schema": {
                      "$ref": "#/components/schemas/DokumentResult"
                    }
                  }
                }
              },
              "401": {
                "description": "Authentication Failure"
              },
              "404": {
                "description": "Not found"
              }
            }
          }
        },
        "/ping": {
          "get": {
            "tags": [
              "Liveliness/Readiness",
              "KortApi"
            ],
            "summary": "ping",
            "description": "Liveliness/readiness probe.",
            "operationId": "ping",
            "responses": {
              "200": {
                "description": "Success",
                "content": {
                  "application/json": {
                    "schema": {
                      "type": "string"
                    }
                  }
                }
              }
            }
          }
        },
        "/metadata/sognenavn": {
          "get": {
            "tags": [
              "DokumentApi"
            ],
            "summary": "Hent sognenavne",
            "description": "Leverer en liste af sognenavne",
            "operationId": "getSognenavn",
            "responses": {
              "200": {
                "description": "Successful Operation",
                "content": {
                  "application/json": {
                    "schema": {
                      "type": "array",
                      "items": {
                        "type": "string"
                      }
                    }
                  }
                }
              },
              "401": {
                "description": "Authentication Failure"
              },
              "404": {
                "description": "Not found"
              }
            }
          }
        },
        "/metadata/maalestok": {
          "get": {
            "tags": [
              "KortApi"
            ],
            "summary": "Hent målestoksforhold",
            "description": "Leverer en liste af målestoksforhold",
            "operationId": "maalestok",
            "parameters": [
              {
                "name": "maalestok",
                "in": "query",
                "description": "Filtrer med søgestreng",
                "required": false,
                "schema": {
                  "type": "string",
                  "default": ""
                }
              }
            ],
            "responses": {
              "200": {
                "description": "Successful Operation",
                "content": {
                  "application/json": {
                    "schema": {
                      "type": "array",
                      "items": {
                        "type": "string"
                      }
                    }
                  }
                }
              },
              "401": {
                "description": "Authentication Failure"
              },
              "404": {
                "description": "Not found"
              }
            }
          }
        },
        "/metadata/kortgrupper/kortvaerker": {
          "get": {
            "tags": [
              "KortApi"
            ],
            "summary": "Hent kortgrupper med underliggende kortværker",
            "description": "Leverer en liste af tilgængelige kortgrupper, med deres tilhørende kortværker",
            "operationId": "kortgrupperWithKortvaerker",
            "responses": {
              "200": {
                "description": "Successful Operation",
                "content": {
                  "application/json": {
                    "schema": {
                      "type": "array",
                      "items": {
                        "$ref": "#/components/schemas/KortgruppeWithKortvaerkerDto"
                      }
                    }
                  }
                }
              },
              "401": {
                "description": "Authentication Failure"
              },
              "404": {
                "description": "Not found"
              }
            }
          }
        },
        "/metadata/herredsnavn": {
          "get": {
            "tags": [
              "DokumentApi"
            ],
            "summary": "Hent herredsnavne",
            "description": "Leverer en liste af herredsnavne",
            "operationId": "getHerredsnavn",
            "responses": {
              "200": {
                "description": "Successful Operation",
                "content": {
                  "application/json": {
                    "schema": {
                      "type": "array",
                      "items": {
                        "type": "string"
                      }
                    }
                  }
                }
              },
              "401": {
                "description": "Authentication Failure"
              },
              "404": {
                "description": "Not found"
              }
            }
          }
        },
        "/metadata/dokumentsamling": {
          "get": {
            "tags": [
              "DokumentApi"
            ],
            "summary": "Hent dokumentsamlinger",
            "description": "Leverer en liste af dokumentsamlinger",
            "operationId": "getDokumentSamling",
            "responses": {
              "200": {
                "description": "Successful Operation",
                "content": {
                  "application/json": {
                    "schema": {
                      "type": "array",
                      "items": {
                        "type": "string"
                      }
                    }
                  }
                }
              },
              "401": {
                "description": "Authentication Failure"
              },
              "404": {
                "description": "Not found"
              }
            }
          }
        },
        "/metadata/daekningsomraader": {
          "get": {
            "tags": [
              "KortApi"
            ],
            "summary": "Hent dækningsområder",
            "description": "Leverer en liste af dækningsområder",
            "operationId": "daekningsomraade",
            "parameters": [
              {
                "name": "daekningsomraade",
                "in": "query",
                "description": "Filtrer med søgestreng",
                "required": false,
                "schema": {
                  "type": "string",
                  "default": ""
                }
              }
            ],
            "responses": {
              "200": {
                "description": "Successful Operation",
                "content": {
                  "application/json": {
                    "schema": {
                      "type": "array",
                      "items": {
                        "type": "string"
                      }
                    }
                  }
                }
              },
              "401": {
                "description": "Authentication Failure"
              },
              "404": {
                "description": "Not found"
              }
            }
          }
        },
        "/kort/{id}": {
          "get": {
            "tags": [
              "KortApi"
            ],
            "summary": "Vis kort ud fra unik id",
            "operationId": "kortById",
            "parameters": [
              {
                "name": "id",
                "in": "path",
                "description": "id",
                "required": true,
                "schema": {
                  "type": "string",
                  "format": "uuid"
                }
              }
            ],
            "responses": {
              "200": {
                "description": "Successful Operation",
                "content": {
                  "application/json": {
                    "schema": {
                      "$ref": "#/components/schemas/KortDto"
                    }
                  }
                }
              },
              "401": {
                "description": "Authentication Failure"
              },
              "404": {
                "description": "Not found"
              }
            }
          }
        },
        "/dokument/{id}": {
          "get": {
            "tags": [
              "DokumentApi"
            ],
            "summary": "Find dokument ud fra unik id",
            "operationId": "dokumentById",
            "parameters": [
              {
                "name": "id",
                "in": "path",
                "description": "id",
                "required": true,
                "schema": {
                  "type": "string",
                  "format": "uuid"
                }
              }
            ],
            "responses": {
              "200": {
                "description": "Successful Operation",
                "content": {
                  "application/json": {
                    "schema": {
                      "$ref": "#/components/schemas/DokumentDto"
                    }
                  }
                }
              },
              "401": {
                "description": "Authentication Failure"
              },
              "404": {
                "description": "Not found"
              }
            }
          }
        }
      },
      "components": {
        "schemas": {
          "KortDto": {
            "type": "object",
            "properties": {
              "id": {
                "type": "string",
                "description": "Unik id for kortet.",
                "format": "uuid"
              },
              "kortgruppe": {
                "type": "string",
                "description": "Kortets kortgruppe."
              },
              "titel": {
                "type": "string",
                "description": "Titlen på kortet."
              },
              "alternativtitel": {
                "type": "string",
                "description": "En eventuel anden titel kortet kan have."
              },
              "originalkortprojektion": {
                "type": "string",
                "description": "Den projektion (afbildning af jorden på en plan flade) det oprindelige kort blev defineret i, f.eks. GS for Generalstabens projektion."
              },
              "bemaerkning": {
                "type": "string",
                "description": "Yderligere kommentarer, f.eks. vedr. fremstilling eller placering ved kendt gods eller lign."
              },
              "gaeldendeperiode_gaeldendefra": {
                "type": "number",
                "description": "Starttid for kortets gyldighedsperiode. Angives i hele år. Gyldighedsperiodens starttid er et korts trykke-, tegne-, optage- eller opmålingsår – dvs. det år hvor kortet kan siges at være nyeste kort."
              },
              "gaeldendeperiode_gaeldendetil": {
                "type": "number",
                "description": "Sluttid for kortets gyldighedsperiode. Angives i hele år. Typisk fordi kortet erstattes af et nyere. Hvis der ikke er fundet en specifik gældende til periode angives et årstal der ligger 50 år efter gældende fra."
              },
              "geometri": {
                "type": "string",
                "description": "Det geografiske område, ofte en polygon, som kortet ligger indenfor. WKT med SRS = EPSG:4326"
              },
              "maalestok": {
                "type": "string",
                "description": "Størrelsesforholdet mellem landskabet og kortets repræsentation heraf."
              },
              "kortbladnummer": {
                "type": "string",
                "description": "Angivelse af geografisk område, f.eks. indenfor kortbladsinddelingen eller ejerlav. For topografiske kort er inddelingen et overordnet grid hvorimod matrikelkort er indelt efter ejerlav. Kan også være f.eks. administrative inddelinger."
              },
              "kortvaerk": {
                "type": "string",
                "description": "Navnet på en logisk samling af skannede kort som kortet hører til, f.eks. atlasblade."
              },
              "daekningsomraade": {
                "type": "array",
                "description": "En liste af geografisk områder, som kortet dækker helt eller delvist. For eksempel Danmark, Grønland. Et kort kan have flere dækningsområder.For prøvekort og lignende vil dækningsområdet kunne angives som intet.",
                "items": {
                  "type": "string"
                }
              },
              "filer": {
                "type": "array",
                "description": "En liste af URL-stier til kortfiler efter IIIF-specifikationen.",
                "items": {
                  "type": "string"
                }
              },
              "aarfordata": {
                "type": "number",
                "description": "Kort udarbejdet på grundlag af data fra dette år eller data sammenstillet i dette år."
              },
              "aarforenkeltrettelser": {
                "type": "array",
                "description": "En liste af år for opdatering af kortet med enkelte rettelser, f.eks. en ny bro.",
                "items": {
                  "type": "number"
                }
              },
              "aarforfotografering": {
                "type": "number",
                "description": "År for optagelse af flyfoto til brug for produktion af kortet."
              },
              "aarforfotogrametriskudtegning": {
                "type": "number",
                "description": "År hvor kortet er lavet på basis af flyfoto."
              },
              "aarforhenlaeggelse": {
                "type": "number",
                "description": "Arkivteknisk betegnelse for kort der er gået i arkiv. 9999 anvendes for ukendt år."
              },
              "aarforkompleteteretimarken": {
                "type": "number",
                "description": "År, hvor kortet er opdateret efter opmåling i marken."
              },
              "aarforkortproeve": {
                "type": "number",
                "description": "Anvendes pt ikke."
              },
              "aarforlineaerrettelse": {
                "type": "number",
                "description": "År for rettelser på grundlag af lodret fotografering."
              },
              "aarformaalt": {
                "type": "number",
                "description": "Det år den oprindelige opmåling til kortet blev afsluttet. Kortet kan senere være nymålt eller rettet."
              },
              "aarforopmaalingsluttet": {
                "type": "number",
                "description": "År for opmåling af matrikelkortet. Hvis kortet er opmålt i flere etaper, er det afslutningen på sidste etape."
              },
              "aarforpunktgrundlag": {
                "type": "number",
                "description": "År hvor kortets punktgrundlag er skabt eller opdateret."
              },
              "aarforrettelse": {
                "type": "array",
                "description": "En liste af år for opdatering af kortet med rettelser, typisk efter at kontrolmålinger er udført.",
                "items": {
                  "type": "number"
                }
              },
              "aarfortopografi": {
                "type": "number",
                "description": "År hvor kortets topografiske elementer er kortlagt eller opdateret."
              },
              "aarforudarbejdelse": {
                "type": "number",
                "description": "Året hvor udarbejdelsen blev afsluttet første gang."
              },
              "aarforudarbejdetmateriale": {
                "type": "number",
                "description": "Året hvor kortet blev færdigtegnet."
              },
              "aarforudgivelse": {
                "type": "number",
                "description": "Det år kortet blev udgivet eller trykt. Kortene er udgivet efter deres opmåling og eventuelt genudgivet som følge af rettelser eller at kortet blev udsolgt."
              },
              "aarforudskiftning": {
                "type": "number",
                "description": "År for udskiftningen af den pågældende landsby eller ejerlav."
              },
              "aarforudtegning": {
                "type": "number",
                "description": "År for udtegning i 1:200000 på grundlag af skråfotografering."
              },
              "aarforvejdata": {
                "type": "number",
                "description": "År hvor kortet er opdateret med vejdata."
              },
              "daasenummer": {
                "type": "string",
                "description": "Nummeret på dåsen, som indeholder billedets originalnegativ."
              },
              "farveskalatype": {
                "type": "string",
                "description": "Farveskema anvendt i det digitale billede, f.eks. sort/hvid 8 bit."
              },
              "flyvehoejde": {
                "type": "number",
                "description": "Flyvehøjden ved optagelsen af billedet.",
                "format": "double"
              },
              "flyverute": {
                "type": "string",
                "description": "Navnet på flyets flyverute. Der er optaget ét eller flere fotos pr flyverute."
              },
              "fotonummer": {
                "type": "string",
                "description": "Fortløbende nummerering af flyfotos. Nummeret er unikt indenfor en flyverute eller evt. kun indenfor et flyfotograferingsprojekt bestående af flere flyveruter."
              },
              "fototid": {
                "type": "string",
                "description": "Tidspunkt for optagelse af flyvefotoet. Tiden består typisk af år, måned, dag og tidspunkt.",
                "format": "date-time"
              },
              "fotovinkel": {
                "type": "string",
                "description": "Beskrivelse af vinkel mod jordoverfladen for flyfotografiet, f.eks. lodfoto eller skråfoto."
              },
              "kameraid": {
                "type": "string",
                "description": "Kameratype eller identifikation af kameraet, der er anvendt til flyfotograferingen."
              },
              "kortart": {
                "type": "string",
                "description": "Oplysninger vedr. produktionen af kortet, f.eks. om det er aktivt, henlagt el.lign."
              },
              "opmaaltaf": {
                "type": "string",
                "description": "Navn på person der har opmålt og/eller tegnet kortet."
              },
              "plannr": {
                "type": "string",
                "description": "Plannummer for matrikelkortet. Plannummer anvendes hvis kort over et ejerlav er fordelt på flere kortblade."
              },
              "producent": {
                "type": "string",
                "description": "Navn på den organisation der har forestået flyfotograferingen."
              },
              "tegner": {
                "type": "string",
                "description": "Navn på personen der har tegnet kortet, kortet kan være opmålt af en eller flere opmålere, evt. også af tegneren."
              },
              "udgiver": {
                "type": "string",
                "description": "Navn på den organisation der har produceret kortet."
              },
              "udskiftetaf": {
                "type": "string",
                "description": "Navn på person der har forestået udskiftningen af en landsby eller ejerlav."
              },
              "version": {
                "type": "string",
                "description": "Versionsnummer for kortet. Et kort kan være udgivet i flere versioner."
              }
            }
          },
          "KortResult": {
            "type": "object",
            "properties": {
              "total": {
                "type": "integer",
                "description": "Totalt antal af kort i listen.",
                "format": "int64"
              },
              "kort": {
                "type": "array",
                "description": "Liste med kort.",
                "items": {
                  "$ref": "#/components/schemas/KortDto"
                }
              }
            }
          },
          "DokumentDto": {
            "type": "object",
            "properties": {
              "id": {
                "type": "string",
                "description": "Unik id for dokumentet."
              },
              "kortgruppe": {
                "type": "string",
                "description": "Dokumenters kortgruppe."
              },
              "titel": {
                "type": "string",
                "description": "Dokumenters titel."
              },
              "alternativtitel": {
                "type": "string",
                "description": "Typisk på formen sp+nummer"
              },
              "bemaerkning": {
                "type": "string",
                "description": "Bemærkning til dokumentet"
              },
              "geometri": {
                "type": "string",
                "description": "Det geografiske område, ofte en polygon, som kortet ligger indenfor. WKT med SRS = EPSG:4326"
              },
              "daekningsomraade": {
                "type": "array",
                "description": "En liste af geografisk områder, som dokumentet dækker helt eller delvist. For eksempel Danmark, Slesvig. ",
                "items": {
                  "type": "string"
                }
              },
              "filer": {
                "type": "array",
                "description": "En liste af URL-stier til kortfiler efter IIIF-specifikationen.",
                "items": {
                  "type": "string"
                }
              },
              "datatype": {
                "type": "string",
                "description": "Hvilket data format dokumentet er i"
              },
              "filtype": {
                "type": "string",
                "description": "fortegnelse"
              },
              "dokumentsamling": {
                "type": "string",
                "description": "Typen af dokumenterne."
              },
              "herredsnavn": {
                "type": "string",
                "description": "Navn på herredet"
              },
              "herredsnummer": {
                "type": "integer",
                "description": "Nummeret på herredet",
                "format": "int64"
              },
              "protokoltype": {
                "type": "string",
                "description": "Gældende eller fra før udskiftning"
              },
              "sogneid": {
                "type": "array",
                "description": "En liste af numre på sogne dokumentet tilhører",
                "items": {
                  "type": "integer",
                  "format": "int64"
                }
              },
              "sognenavn": {
                "type": "array",
                "description": "En liste af navne på sogne dokumentet tilhører",
                "items": {
                  "type": "string"
                }
              }
            }
          },
          "DokumentResult": {
            "type": "object",
            "properties": {
              "total": {
                "type": "integer",
                "description": "Totalt antal af dokumenter i listen.",
                "format": "int64"
              },
              "dokumenter": {
                "type": "array",
                "description": "Liste med dokumenter.",
                "items": {
                  "$ref": "#/components/schemas/DokumentDto"
                }
              }
            }
          },
          "DokumentParam": {
            "type": "object",
            "properties": {
              "direction": {
                "pattern": "asc|desc",
                "type": "string",
                "description": "Sorteringsretning, `asc` for stigende, `desc` for faldende",
                "default": "asc"
              },
              "fritekstsoegning": {
                "type": "string",
                "description": "Fritekstsøgning"
              },
              "geometri": {
                "type": "string",
                "description": "Geometri angives som WKT med SRS = EPSG:4326. Det geografiske område, ofte en polygon, som kortet ligger indenfor."
              },
              "herredsnavn": {
                "type": "string",
                "description": "Herredets navn."
              },
              "herredsnummer": {
                "type": "integer",
                "description": "Herredets nummer.",
                "format": "int32"
              },
              "kortgruppe": {
                "type": "array",
                "description": "De dokumenttyper, der skal vises. En kommasepareret liste af typer. Eksempel: `Hartkornsekstrakt, Sogneprotokol.`",
                "items": {
                  "type": "string"
                }
              },
              "limit": {
                "maximum": 1000,
                "minimum": 1,
                "type": "integer",
                "description": "Sidestørrelse, dvs. hvor mange poster pr. side",
                "format": "int32",
                "default": 100
              },
              "offset": {
                "minimum": 0,
                "type": "integer",
                "description": "Offset, dvs. fra hvilken post",
                "format": "int32",
                "default": 0
              },
              "sogneid": {
                "type": "integer",
                "description": "Sogneid.",
                "format": "int32"
              },
              "sognenavn": {
                "type": "string",
                "description": "Sognenavn."
              },
              "sort": {
                "type": "string",
                "description": "Sorteringsfelt, kan sortere på følgende typer: kortgruppe, herredsnavn, herredsnummer, sogneid, sognenavn, titel"
              },
              "titel": {
                "type": "string",
                "description": "Titel på dokumentet."
              }
            }
          },
          "KortgruppeWithKortvaerkerDto": {
            "type": "object",
            "properties": {
              "kortgruppe": {
                "type": "string",
                "description": "Kortgruppe."
              },
              "kortvaerker": {
                "type": "array",
                "description": "Liste af logiske samlinger af skannede kort som tilhører kortgruppen.",
                "items": {
                  "type": "string"
                }
              }
            }
          }
        },
        "securitySchemes": {
          "HeaderToken": {
            "type": "apiKey",
            "name": "token",
            "in": "header"
          },
          "QueryToken": {
            "type": "apiKey",
            "name": "token",
            "in": "query"
          }
        }
      }
    }
    """
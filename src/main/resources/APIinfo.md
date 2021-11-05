## API til skannede historiske kort

APIet __Arkivmeta__ giver adgang til at søge i metadata for en større samling historiske kort og benytte resultatet til at fremvise det skannede materiale. 

Til adgang benyttes Kortforsyningens brugeradgang som ved andre tjenester.

Stier til kortfiler følger [IIIF specifikationen](https://iiif.io/) og kan vises med en viser, der understøtter dette.

#### Historik
__1.0.11__, _16. september 2021_
* Understøtte nu Cache-Control headers med max-age og etag. Hvis clienten tilføjer header "If-None-Match" og der ingen ændring er i data, svarer API'et med HTTP koden 304.

__1.0.10__, _15. september 2021_
* Ved udstilling af arketyper på /metadata/arketyper returneres der nu også, hvilke kortværker der tilhører hver arketype

__1.0.9__, _2. september 2021_
* Ved søgning i kort kan der nu søges på tegner
* Ved udstilling af kortværker på /metadata/kortvaerker kan der angives en arketype, og få returneret de kortværker, der hører til den arketype

__1.0.8__, _1. september 2021_
* Ved søgning i kort kan der nu angives gældende fra og gældende til i stedet for gyldighedsår

__1.0.7__, _30. august 2021_
* Udstilling af titler på /metadata/titler er fjernet fra API'et

__1.0.6__, _27. august 2021_
* Ved søgning i kort kan der nu sorteres på arketype
* Ved søgning i kort kan der nu angives mere end en arketype, dækningsområde, kortværk og målestok
* Ved søgning i kort kan der søges efter titler

__1.0.5__, _2. juli 2021_
* Ved søgning i kort kan der nu angives arketype

__1.0.4__, _30. juni 2021_
* Understøttelse af OpenAPI (OAS 3)
* Udstilling af arketyper på /metadata/arketyper

__1.0.3__, _28. november 2019_
* Geometrisøgninger returnerer nu alle kort hvis geometri blot berører den søgte (intersekterer)
* Opdateret underliggende biblioteker

__1.0.2__, _31. oktober 2019_
* Rettet og tilføjet dokumentation af individuelle korttypers ekstra metadatafelter
* Rettet en fejl i geometrisøgninger, hvor der blev returneret kort indenfor den søgte geometri. Nu returneres kort der indeholder den søgte geometri som tiltænkt

__1.0.1__, _25. oktober 2019_
* Versalfølsomme søgninger på dækningsområde og titel
* Tilføjet dokumentation af flere fælles felter

__1.0.0__, _26. september 2019_
* Første release

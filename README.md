# ArkivMeta

REST API til søgning i metadata til brug for udstilling af SDFEs og GSTs skannede kort.

Baseret på Spring Boot, Hibernate og OpenAPI. Skrevet i Java.

## Breaking change to v3:

### Dokument
`GET` and `POST` `/dokument`
Changes in query parameter: 
`dokumentsamling` has changed name to `kortgruppe`.
`limit` has changed datatype from string to integer.
`offset` has changed datatype from string to integer.

Changes in response:
`arketype` has changed name to `korgruppe`.
`registreringfra` is removed.
`registreringtil` is removed.
`uniktdokumentnavn` is removed.
`stinavn` is removed.
`omraade` has changed name to `daekningsomraade` and has changed datatype from string to list of strings.
`sogneid` has changed datatype from integer to list of integer.
`sognenavn` has changed datatype from string to list of strings.

`GET /dokument/{arketype}/{id}`
Has changed to `/dokument/{id}`

Changes in response:
`arketype` has changed name to `korgruppe`.
`registreringfra` is removed.
`registreringtil` is removed.
`uniktdokumentnavn` is removed.
`stinavn` is removed.
`omraade` has changed name to `daekningsomraade` and has changed datatype from string to list of strings.
`sogneid` has changed datatype from integer to list of integer.
`sognenavn` has changed datatype from string to list of strings.

### Kort

`GET` and `POST` `/kort`
Changes in query parameter:
`arketype` has changed name to `korgruppe`.
`gaeldendefra` has changed name to `gaeldendeperiode_gaeldendefra` and has changed datatype from string to integer.
`gaeldendetil` has changed name to `gaeldendeperiode_gaeldendetil` and has changed datatype from string to integer.
`limit` has changed datatype from string to integer.
`offset` has changed datatype from string to integer.

Changes in response:
`arketype` has changed name to `korgruppe`.
`gaeldendefra` has changed name to `gaeldendeperiode_gaeldendefra`.
`gaeldendetil` has changed name to `gaeldendeperiode_gaeldendetil`.
`limit` has changed datatype from string to integer.
`offset` has changed datatype from string to integer.
`orginalkortprojektion` is removed.
`originalehjoernekoordinater` is removed.
`aarforadministrativerettelser` is removed.
`aarforfotorekogrettelser` is removed.
`aarforrevision` is removed.
`aarforrevisonafnavnemm` is removed.
`kortdimensioner` is removed.
`loebenummer` is removed.
`rytterdistriktid` is removed.
`soeregion` is removed.
`stedbetegnelse` is removed.
`registreringfra` is removed.
`registreringtil` is removed.
`stinavn` is removed.
`uniktkortnavn` is removed.

`GET /kort/{arketype}/{id}`
Has changed to `/kort/{id}`.

Changes in response:
`arketype` has changed name to `korgruppe`.
`gaeldendefra` has changed name to `gaeldendeperiode_gaeldendefra`.
`gaeldendetil` has changed name to `gaeldendeperiode_gaeldendetil`.
`limit` has changed datatype from string to integer.
`offset` has changed datatype from string to integer.
`orginalkortprojektion` is removed.
`originalehjoernekoordinater` is removed.
`aarforadministrativerettelser` is removed.
`aarforfotorekogrettelser` is removed.
`aarforrevision` is removed.
`aarforrevisonafnavnemm` is removed.
`kortdimensioner` is removed.
`loebenummer` is removed.
`rytterdistriktid` is removed.
`soeregion` is removed.
`stedbetegnelse` is removed.
`registreringfra` is removed.
`registreringtil` is removed.
`stinavn` is removed.
`uniktkortnavn` is removed.

`GET /metadata/arketyper`
Is removed. Use `/metadata/kortgrupper/kortvaerker`.

`GET /metadata/arketyper/kortvaerker`
Has changed to `/metadata/kortgrupper/kortvaerker`.

`GET /metadata/daekningsomraader`
Is removed. Use `/metadata/kortgrupper/kortvaerker`.

## Breaking change to v2:

### kortgruppe

Man skal ikke længere angive flere værdier med `|` som seperator men i stedet for bruge komma `,`.

### daekningsomraade

Man skal ikke længere angive flere værdier med `|` som seperator men i stedet for bruge komma `,`.

### gaeldendeperiode_gaeldendefra

Har ikke længere end default værdi, så hvis man ikke angiver et årstal, søges der for alle år

### gaeldendeperiode_gaeldendetil

Har ikke længere end default værdi, så hvis man ikke angiver et årstal, søges der for alle år

### kortvaerk

Hvis man ønsker at angive mere end et kortværk der skal søges efter, skal man i stedet angive `kortvaerk` query
parameteren for hvert eneste en kortværk man vil søge efter.

### maalestok

Man skal ikke længere angive flere værdier med `|` som seperator men i stedet for bruge komma `,`.

### pagesize

Er blevet ændret til `limit`.
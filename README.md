# ArkivMeta

REST API til søgning i metadata til brug for udstilling af SDFEs og GSTs skannede kort.

Baseret på Spring Boot, Hibernate og OpenAPI. Skrevet i Java.

## Breaking change to v3:

### Dokument
#### GET and POST /dokument

##### Changes in query parameter
###### renaming
- `dokumentsamling` -> `kortgruppe`

###### datatypes changes
- `limit`: string -> integer
- `offset`: string -> integer

##### Changes in response
###### renaming
- `arketype` -> `korgruppe`
- `omraade` -> `daekningsomraade`

###### datatypes changes
- `daekningsomraade`: string -> list of strings
- `sogneid`: integer -> list of integer
- `sognenavn`: string -> list of strings

###### deleted
- `registreringfra`
- `registreringtil`
- `uniktdokumentnavn`
- `stinavn`


#### GET /dokument/{arketype}/{id}
Has changed to `/dokument/{id}`

##### Changes in response
###### renaming
- `arketype` -> `korgruppe`
- `omraade` -> `daekningsomraade`

###### datatypes changes
- `daekningsomraade`: string -> list of strings
- `sogneid`: integer -> list of integer
- `sognenavn`: string -> list of strings

###### deleted
- `registreringfra`
- `registreringtil`
- `uniktdokumentnavn`
- `stinavn`


### Kort

#### GET and POST /kort

##### Changes in query parameter
###### renaming
- `arketype` -> `korgruppe`
- `gaeldendefra` -> `gaeldendeperiode_gaeldendefra`
- `gaeldendetil` -> `gaeldendeperiode_gaeldendetil`

###### datatypes changes
- `gaeldendeperiode_gaeldendefra`: string -> integer
- `gaeldendeperiode_gaeldendetil`: string -> integer
- `limit`: string -> integer
- `offset`: string -> integer

##### Changes in response
###### renaming
- `arketype` -> `korgruppe`
- `gaeldendefra` -> `gaeldendeperiode_gaeldendefra`.
- `gaeldendetil` -> `gaeldendeperiode_gaeldendetil`.

###### deleted
- `orginalkortprojektion`
- `originalehjoernekoordinater`
- `aarforadministrativerettelser`
- `aarforfotorekogrettelser`
- `aarforrevision`
- `aarforrevisonafnavnemm`
- `kortdimensioner`
- `loebenummer`
- `rytterdistriktid`
- `soeregion`
- `stedbetegnelse`
- `registreringfra`
- `registreringtil`
- `stinavn`
- `uniktkortnavn`


#### GET /kort/{arketype}/{id}
Has changed to `/kort/{id}`

##### Changes in response
###### renaming
- `arketype` -> `korgruppe`
- `gaeldendefra` -> `gaeldendeperiode_gaeldendefra`.
- `gaeldendetil` -> `gaeldendeperiode_gaeldendetil`.

###### deleted
- `orginalkortprojektion`
- `originalehjoernekoordinater`
- `aarforadministrativerettelser`
- `aarforfotorekogrettelser`
- `aarforrevision`
- `aarforrevisonafnavnemm`
- `kortdimensioner`
- `loebenummer`
- `rytterdistriktid`
- `soeregion`
- `stedbetegnelse`
- `registreringfra`
- `registreringtil`
- `stinavn`
- `uniktkortnavn`

#### GET /metadata/arketyper
Deleted - use `/metadata/kortgrupper/kortvaerker`

#### GET /metadata/arketyper/kortvaerker
Has changed to `/metadata/kortgrupper/kortvaerker`

#### GET /metadata/daekningsomraader
Deleted - use `/metadata/kortgrupper/kortvaerker`

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
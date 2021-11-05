package dk.dataforsyningen.arkivmeta.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public class LandoekonomiskekortDto extends KortDto
{
    @Schema(description = "Det år den oprindelige opmåling til kortet blev afsluttet. Kortet kan senere være nymålt eller rettet.")
    private Integer aarformaalt;

    @Schema(description = "Navn på personen der har tegnet kortet, kortet kan være opmålt af en eller flere opmålere, evt. også af tegneren.")
    private String tegner;

    public LandoekonomiskekortDto()
    {
    }

    public LandoekonomiskekortDto(String id,
                                  String alternativtitel,
                                  String arketype,
                                  String bemaerkning,
                                  List<String> daekningsomraade,
                                  List<String> filer,
                                  Integer gaeldendefra,
                                  Integer gaeldendetil,
                                  String geometri,
                                  String kortbladnummer,
                                  String kortvaerk,
                                  String maalestok,
                                  String originalkortprojektion,
                                  String originalehjoernekoordinater,
                                  LocalDateTime registreringfra,
                                  LocalDateTime registreringtil,
                                  String titel,
                                  String uniktkortnavn,
                                  Integer aarformaalt,
                                  String tegner)
    {
        super(id,
              alternativtitel,
              arketype,
              bemaerkning,
              daekningsomraade,
              filer,
              gaeldendefra,
              gaeldendetil,
              geometri,
              kortbladnummer,
              kortvaerk,
              maalestok,
              originalkortprojektion,
              originalehjoernekoordinater,
              registreringfra,
              registreringtil,
              titel,
              uniktkortnavn);
        this.aarformaalt = aarformaalt;
        this.tegner = tegner;
    }

    public Integer getAarformaalt()
    {
        return aarformaalt;
    }

    public void setAarformaalt(Integer aarformaalt)
    {
        this.aarformaalt = aarformaalt;
    }

    public String getTegner()
    {
        return tegner;
    }

    public void setTegner(String tegner)
    {
        this.tegner = tegner;
    }
}

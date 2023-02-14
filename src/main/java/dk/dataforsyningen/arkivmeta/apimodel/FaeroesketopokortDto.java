package dk.dataforsyningen.arkivmeta.apimodel;

import dk.dataforsyningen.arkivmeta.enums.Arketype;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

public class FaeroesketopokortDto extends KortDto {
  @Schema(description = "Det år den oprindelige opmåling til kortet blev afsluttet. Kortet kan senere være nymålt eller rettet.")
  private Integer aarformaalt;

  @Schema(description = "Det år kortet blev udgivet eller trykt. Kortene er udgivet efter deres opmåling og eventuelt genudgivet som følge af rettelser eller at kortet blev udsolgt.")
  private Integer aarforudgivelse;

  @Schema(description = "Versionsnummer for kortet. Et kort kan være udgivet i flere versioner.")
  private String version;

  public FaeroesketopokortDto() {
  }

  public FaeroesketopokortDto(String id,
                              String alternativtitel,
                              Arketype arketype,
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
                              Integer aarforudgivelse,
                              String version) {
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
    this.aarforudgivelse = aarforudgivelse;
    this.version = version;
  }

  public Integer getAarformaalt() {
    return aarformaalt;
  }

  public void setAarformaalt(Integer aarformaalt) {
    this.aarformaalt = aarformaalt;
  }

  public Integer getAarforudgivelse() {
    return aarforudgivelse;
  }

  public void setAarforudgivelse(Integer aarforudgivelse) {
    this.aarforudgivelse = aarforudgivelse;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }
}

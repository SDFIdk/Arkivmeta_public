package dk.dataforsyningen.arkivmeta.apimodel;

import dk.dataforsyningen.arkivmeta.enums.Arketype;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

public class GroenlandtopokortDto extends KortDto {
  @Schema(description = "År for optagelse af flyfoto til brug for produktion af kortet.")
  private Integer aarforfotografering;

  @Schema(description = "År for rettelser på grundlag af lodret fotografering.")
  private Integer aarforlineaerrettelse;

  @Schema(description = "Det år den oprindelige opmåling til kortet blev afsluttet. Kortet kan senere være nymålt eller rettet.")
  private Integer aarformaalt;

  @Schema(description = "År hvor kortets punktgrundlag er skabt eller opdateret.")
  private Integer aarforpunktgrundlag;

  @Schema(description = "År for revision af bebyggelsesnavne og andre topografiske enkeltheder.")
  private Integer aarforrevisonafnavnemm;

  @Schema(description = "År hvor kortets topografiske elementer er kortlagt eller opdateret.")
  private Integer aarfortopografi;

  @Schema(description = "Det år kortet blev udgivet eller trykt. Kortene er udgivet efter deres opmåling og eventuelt genudgivet som følge af rettelser eller at kortet blev udsolgt")
  private Integer aarforudgivelse;

  @Schema(description = "År for udtegning i 1:200000 på grundlag af skråfotografering.")
  private Integer aarforudtegning;

  @Schema(description = "Versionsnummer for kortet. Et kort kan være udgivet i flere versioner.")
  private String version;

  public GroenlandtopokortDto() {
  }

  public GroenlandtopokortDto(String id,
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
                              Integer aarforfotografering,
                              Integer aarforlineaerrettelse,
                              Integer aarformaalt,
                              Integer aarforpunktgrundlag,
                              Integer aarforrevisonafnavnemm,
                              Integer aarfortopografi,
                              Integer aarforudgivelse,
                              Integer aarforudtegning,
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
    this.aarforfotografering = aarforfotografering;
    this.aarforlineaerrettelse = aarforlineaerrettelse;
    this.aarformaalt = aarformaalt;
    this.aarforpunktgrundlag = aarforpunktgrundlag;
    this.aarforrevisonafnavnemm = aarforrevisonafnavnemm;
    this.aarfortopografi = aarfortopografi;
    this.aarforudgivelse = aarforudgivelse;
    this.aarforudtegning = aarforudtegning;
    this.version = version;
  }

  public Integer getAarforfotografering() {
    return aarforfotografering;
  }

  public void setAarforfotografering(Integer aarforfotografering) {
    this.aarforfotografering = aarforfotografering;
  }

  public Integer getAarforlineaerrettelse() {
    return aarforlineaerrettelse;
  }

  public void setAarforlineaerrettelse(Integer aarforlineaerrettelse) {
    this.aarforlineaerrettelse = aarforlineaerrettelse;
  }

  public Integer getAarformaalt() {
    return aarformaalt;
  }

  public void setAarformaalt(Integer aarformaalt) {
    this.aarformaalt = aarformaalt;
  }

  public Integer getAarforpunktgrundlag() {
    return aarforpunktgrundlag;
  }

  public void setAarforpunktgrundlag(Integer aarforpunktgrundlag) {
    this.aarforpunktgrundlag = aarforpunktgrundlag;
  }

  public Integer getAarforrevisonafnavnemm() {
    return aarforrevisonafnavnemm;
  }

  public void setAarforrevisonafnavnemm(Integer aarforrevisonafnavnemm) {
    this.aarforrevisonafnavnemm = aarforrevisonafnavnemm;
  }

  public Integer getAarfortopografi() {
    return aarfortopografi;
  }

  public void setAarfortopografi(Integer aarfortopografi) {
    this.aarfortopografi = aarfortopografi;
  }

  public Integer getAarforudgivelse() {
    return aarforudgivelse;
  }

  public void setAarforudgivelse(Integer aarforudgivelse) {
    this.aarforudgivelse = aarforudgivelse;
  }

  public Integer getAarforudtegning() {
    return aarforudtegning;
  }

  public void setAarforudtegning(Integer aarforudtegning) {
    this.aarforudtegning = aarforudtegning;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }
}

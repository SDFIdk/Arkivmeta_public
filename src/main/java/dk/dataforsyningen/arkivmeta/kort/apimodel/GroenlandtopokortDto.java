package dk.dataforsyningen.arkivmeta.kort.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class GroenlandtopokortDto extends KortDto {
  @Schema(description = "År for optagelse af flyfoto til brug for produktion af kortet.")
  private BigDecimal aarforfotografering;

  @Schema(description = "År for rettelser på grundlag af lodret fotografering.")
  private BigDecimal aarforlineaerrettelse;

  @Schema(description = "Det år den oprindelige opmåling til kortet blev afsluttet. Kortet kan senere være nymålt eller rettet.")
  private BigDecimal aarformaalt;

  @Schema(description = "År hvor kortets punktgrundlag er skabt eller opdateret.")
  private BigDecimal aarforpunktgrundlag;

  @Schema(description = "År for revision af bebyggelsesnavne og andre topografiske enkeltheder.")
  private BigDecimal aarforrevisonafnavnemm;

  @Schema(description = "År hvor kortets topografiske elementer er kortlagt eller opdateret.")
  private BigDecimal aarfortopografi;

  @Schema(description = "Det år kortet blev udgivet eller trykt. Kortene er udgivet efter deres opmåling og eventuelt genudgivet som følge af rettelser eller at kortet blev udsolgt")
  private BigDecimal aarforudgivelse;

  @Schema(description = "År for udtegning i 1:200000 på grundlag af skråfotografering.")
  private BigDecimal aarforudtegning;

  @Schema(description = "Versionsnummer for kortet. Et kort kan være udgivet i flere versioner.")
  private String version;

  public GroenlandtopokortDto() {
  }

  public GroenlandtopokortDto(String id,
                              String alternativtitel,
                              String arketype,
                              String bemaerkning,
                              List<String> daekningsomraade,
                              List<String> filer,
                              BigDecimal gaeldendefra,
                              BigDecimal gaeldendetil,
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
                              BigDecimal aarforfotografering,
                              BigDecimal aarforlineaerrettelse,
                              BigDecimal aarformaalt,
                              BigDecimal aarforpunktgrundlag,
                              BigDecimal aarforrevisonafnavnemm,
                              BigDecimal aarfortopografi,
                              BigDecimal aarforudgivelse,
                              BigDecimal aarforudtegning,
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

  public BigDecimal getAarforfotografering() {
    return aarforfotografering;
  }

  public void setAarforfotografering(BigDecimal aarforfotografering) {
    this.aarforfotografering = aarforfotografering;
  }

  public BigDecimal getAarforlineaerrettelse() {
    return aarforlineaerrettelse;
  }

  public void setAarforlineaerrettelse(BigDecimal aarforlineaerrettelse) {
    this.aarforlineaerrettelse = aarforlineaerrettelse;
  }

  public BigDecimal getAarformaalt() {
    return aarformaalt;
  }

  public void setAarformaalt(BigDecimal aarformaalt) {
    this.aarformaalt = aarformaalt;
  }

  public BigDecimal getAarforpunktgrundlag() {
    return aarforpunktgrundlag;
  }

  public void setAarforpunktgrundlag(BigDecimal aarforpunktgrundlag) {
    this.aarforpunktgrundlag = aarforpunktgrundlag;
  }

  public BigDecimal getAarforrevisonafnavnemm() {
    return aarforrevisonafnavnemm;
  }

  public void setAarforrevisonafnavnemm(BigDecimal aarforrevisonafnavnemm) {
    this.aarforrevisonafnavnemm = aarforrevisonafnavnemm;
  }

  public BigDecimal getAarfortopografi() {
    return aarfortopografi;
  }

  public void setAarfortopografi(BigDecimal aarfortopografi) {
    this.aarfortopografi = aarfortopografi;
  }

  public BigDecimal getAarforudgivelse() {
    return aarforudgivelse;
  }

  public void setAarforudgivelse(BigDecimal aarforudgivelse) {
    this.aarforudgivelse = aarforudgivelse;
  }

  public BigDecimal getAarforudtegning() {
    return aarforudtegning;
  }

  public void setAarforudtegning(BigDecimal aarforudtegning) {
    this.aarforudtegning = aarforudtegning;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }
}

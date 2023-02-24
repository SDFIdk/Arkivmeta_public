package dk.dataforsyningen.arkivmeta.kort.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class AeldretopografiskekortDto extends KortDto {
  @Schema(description = "Det år den oprindelige opmåling til kortet blev afsluttet. Kortet kan senere være nymålt eller rettet.")
  private BigDecimal aarformaalt;

  @Schema(description = "Det år kortet blev udgivet eller trykt. Kortene er udgivet efter deres opmåling og eventuelt genudgivet som følge af rettelser eller at kortet blev udsolgt.")
  private BigDecimal aarforudgivelse;

  @Schema(description = "Kendt geografisk sted for at hjælpe, hvis titlen er et mindre kendt sted.")
  private String stedbetegnelse;

  @Schema(description = "Navn på personen der har tegnet kortet, kortet kan være opmålt af en eller flere opmålere, evt. også af tegneren.")
  private String tegner;

  @Schema(description = "Navn på den organisation der har produceret kortet.")
  private String udgiver;

  public AeldretopografiskekortDto() {
  }

  public AeldretopografiskekortDto(String id,
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
                                   BigDecimal aarformaalt,
                                   BigDecimal aarforudgivelse,
                                   String stedbetegnelse,
                                   String tegner, String udgiver) {
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
    this.stedbetegnelse = stedbetegnelse;
    this.tegner = tegner;
    this.udgiver = udgiver;
  }

  public BigDecimal getAarformaalt() {
    return aarformaalt;
  }

  public void setAarformaalt(BigDecimal aarformaalt) {
    this.aarformaalt = aarformaalt;
  }

  public BigDecimal getAarforudgivelse() {
    return aarforudgivelse;
  }

  public void setAarforudgivelse(BigDecimal aarforudgivelse) {
    this.aarforudgivelse = aarforudgivelse;
  }

  public String getStedbetegnelse() {
    return stedbetegnelse;
  }

  public void setStedbetegnelse(String stedbetegnelse) {
    this.stedbetegnelse = stedbetegnelse;
  }

  public String getTegner() {
    return tegner;
  }

  public void setTegner(String tegner) {
    this.tegner = tegner;
  }

  public String getUdgiver() {
    return udgiver;
  }

  public void setUdgiver(String udgiver) {
    this.udgiver = udgiver;
  }
}

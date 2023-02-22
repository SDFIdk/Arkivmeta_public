package dk.dataforsyningen.arkivmeta.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TematiskekortDto extends KortDto {
  @Schema(description = "Året hvor kortet blev færdigtegnet.")
  private BigDecimal aarforudarbejdetmateriale;

  @Schema(description = "Det år kortet blev udgivet eller trykt. Kortene er udgivet efter deres opmåling og eventuelt genudgivet som følge af rettelser eller at kortet blev udsolgt.")
  private BigDecimal aarforudgivelse;

  public TematiskekortDto() {
  }

  public TematiskekortDto(String id,
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
                          BigDecimal aarforudarbejdetmateriale,
                          BigDecimal aarforudgivelse) {
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
    this.aarforudarbejdetmateriale = aarforudarbejdetmateriale;
    this.aarforudgivelse = aarforudgivelse;
  }

  public BigDecimal getAarforudarbejdetmateriale() {
    return aarforudarbejdetmateriale;
  }

  public void setAarforudarbejdetmateriale(BigDecimal aarforudarbejdetmateriale) {
    this.aarforudarbejdetmateriale = aarforudarbejdetmateriale;
  }

  public BigDecimal getAarforudgivelse() {
    return aarforudgivelse;
  }

  public void setAarforudgivelse(BigDecimal aarforudgivelse) {
    this.aarforudgivelse = aarforudgivelse;
  }
}

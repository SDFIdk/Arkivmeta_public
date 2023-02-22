package dk.dataforsyningen.arkivmeta.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CentimeterkortDto extends KortDto {
  @Schema(description = "Året hvor administrative grænser er rettet på kortet.")
  private BigDecimal aarforadministrativerettelser;

  @Schema(description = "Kort udarbejdet på grundlag af data fra dette år eller data sammenstillet i dette år.")
  private BigDecimal aarfordata;

  @Schema(description = "År hvor kortet er lavet på basis af flyfoto.")
  private BigDecimal aarforfotogrametriskudtegning;

  @Schema(description = "År hvor kortet er opdateret på basis af flyfoto.")
  private BigDecimal aarforfotorekogrettelser;

  @Schema(description = "År, hvor kortet er opdateret efter opmåling i marken.")
  private BigDecimal aarforkompleteteretimarken;

  @Schema(description = "Det år den oprindelige opmåling til kortet blev afsluttet.")
  private BigDecimal aarformaalt;

  @Schema(description = "År for revision af kortet. En revision er typisk en kvalitetskontrol.")
  private BigDecimal aarforrevision;

  @Schema(description = "Året hvor udarbejdelsen blev afsluttet første gang.")
  private BigDecimal aarforudarbejdelse;

  @Schema(description = "Det år kortet blev udgivet eller trykt. Kortene er udgivet efter deres opmåling og eventuelt genudgivet som følge af rettelser eller at kortet blev udsolgt.")
  private BigDecimal aarforudgivelse;

  @Schema(description = "År hvor kortet er opdateret med vejdata.")
  private BigDecimal aarforvejdata;

  @Schema(description = "Versionsnummer for kortet. Et kort kan være udgivet i flere versioner.")
  private String version;

  public CentimeterkortDto() {
  }

  public CentimeterkortDto(String id,
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
                           BigDecimal aarforadministrativerettelser,
                           BigDecimal aarfordata,
                           BigDecimal aarforfotogrametriskudtegning,
                           BigDecimal aarforfotorekogrettelser,
                           BigDecimal aarforkompleteteretimarken,
                           BigDecimal aarformaalt,
                           BigDecimal aarforrevision,
                           BigDecimal aarforudarbejdelse,
                           BigDecimal aarforudgivelse,
                           BigDecimal aarforvejdata,
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
    this.aarforadministrativerettelser = aarforadministrativerettelser;
    this.aarfordata = aarfordata;
    this.aarforfotogrametriskudtegning = aarforfotogrametriskudtegning;
    this.aarforfotorekogrettelser = aarforfotorekogrettelser;
    this.aarforkompleteteretimarken = aarforkompleteteretimarken;
    this.aarformaalt = aarformaalt;
    this.aarforrevision = aarforrevision;
    this.aarforudarbejdelse = aarforudarbejdelse;
    this.aarforudgivelse = aarforudgivelse;
    this.aarforvejdata = aarforvejdata;
    this.version = version;
  }

  public BigDecimal getAarforadministrativerettelser() {
    return aarforadministrativerettelser;
  }

  public void setAarforadministrativerettelser(BigDecimal aarforadministrativerettelser) {
    this.aarforadministrativerettelser = aarforadministrativerettelser;
  }

  public BigDecimal getAarfordata() {
    return aarfordata;
  }

  public void setAarfordata(BigDecimal aarfordata) {
    this.aarfordata = aarfordata;
  }

  public BigDecimal getAarforfotogrametriskudtegning() {
    return aarforfotogrametriskudtegning;
  }

  public void setAarforfotogrametriskudtegning(BigDecimal aarforfotogrametriskudtegning) {
    this.aarforfotogrametriskudtegning = aarforfotogrametriskudtegning;
  }

  public BigDecimal getAarforfotorekogrettelser() {
    return aarforfotorekogrettelser;
  }

  public void setAarforfotorekogrettelser(BigDecimal aarforfotorekogrettelser) {
    this.aarforfotorekogrettelser = aarforfotorekogrettelser;
  }

  public BigDecimal getAarforkompleteteretimarken() {
    return aarforkompleteteretimarken;
  }

  public void setAarforkompleteteretimarken(BigDecimal aarforkompleteteretimarken) {
    this.aarforkompleteteretimarken = aarforkompleteteretimarken;
  }

  public BigDecimal getAarformaalt() {
    return aarformaalt;
  }

  public void setAarformaalt(BigDecimal aarformaalt) {
    this.aarformaalt = aarformaalt;
  }

  public BigDecimal getAarforrevision() {
    return aarforrevision;
  }

  public void setAarforrevision(BigDecimal aarforrevision) {
    this.aarforrevision = aarforrevision;
  }

  public BigDecimal getAarforudarbejdelse() {
    return aarforudarbejdelse;
  }

  public void setAarforudarbejdelse(BigDecimal aarforudarbejdelse) {
    this.aarforudarbejdelse = aarforudarbejdelse;
  }

  public BigDecimal getAarforudgivelse() {
    return aarforudgivelse;
  }

  public void setAarforudgivelse(BigDecimal aarforudgivelse) {
    this.aarforudgivelse = aarforudgivelse;
  }

  public BigDecimal getAarforvejdata() {
    return aarforvejdata;
  }

  public void setAarforvejdata(BigDecimal aarforvejdata) {
    this.aarforvejdata = aarforvejdata;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }
}

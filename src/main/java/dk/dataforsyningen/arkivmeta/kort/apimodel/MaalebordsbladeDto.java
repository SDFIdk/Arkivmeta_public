package dk.dataforsyningen.arkivmeta.kort.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class MaalebordsbladeDto extends KortDto {
  @Schema(description = "Kort udarbejdet på grundlag af data fra dette år eller data sammenstillet i dette år.")
  private BigDecimal aarfordata;

  @Schema(description = "År for opdatering af kortet med enkelte rettelser, f.eks. en ny bro.")
  private BigDecimal aarforenkeltrettelser;

  @Schema(description = "Det år den oprindelige opmåling til kortet blev afsluttet. Kortet kan senere være nymålt eller rettet.")
  private BigDecimal aarformaalt;

  @Schema(description = "År for opmåling af matrikelkortet. Hvis kortet er opmålt i flere etaper, er det afslutningen på sidste etape.")
  private BigDecimal aarforopmaalingsluttet;

  @Schema(description = "År for opdatering af kortet med rettelser, typisk efter at kontrolmålinger er udført.")
  private BigDecimal aarforrettelse;

  @Schema(description = "Året hvor udarbejdelsen blev afsluttet første gang.")
  private BigDecimal aarforudarbejdelse;

  @Schema(description = "Det år kortet blev udgivet eller trykt. Kortene er udgivet efter deres opmåling og eventuelt genudgivet som følge af rettelser eller at kortet blev udsolgt.")
  private BigDecimal aarforudgivelse;

  @Schema(description = "År hvor kortet er opdateret med vejdata.")
  private BigDecimal aarforvejdata;

  @Schema(description = "Navn på personen der har tegnet kortet, kortet kan være opmålt af en eller flere opmålere, evt. også af tegneren.")
  private String tegner;

  @Schema(description = "Versionsnummer for kortet. Et kort kan være udgivet i flere versioner.")
  private String version;

  public MaalebordsbladeDto() {
  }

  public MaalebordsbladeDto(String id,
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
                            BigDecimal aarfordata,
                            BigDecimal aarforenkeltrettelser,
                            BigDecimal aarformaalt,
                            BigDecimal aarforopmaalingsluttet,
                            BigDecimal aarforrettelse,
                            BigDecimal aarforudarbejdelse,
                            BigDecimal aarforudgivelse,
                            BigDecimal aarforvejdata,
                            String tegner,
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
    this.aarfordata = aarfordata;
    this.aarforenkeltrettelser = aarforenkeltrettelser;
    this.aarformaalt = aarformaalt;
    this.aarforopmaalingsluttet = aarforopmaalingsluttet;
    this.aarforrettelse = aarforrettelse;
    this.aarforudarbejdelse = aarforudarbejdelse;
    this.aarforudgivelse = aarforudgivelse;
    this.aarforvejdata = aarforvejdata;
    this.tegner = tegner;
    this.version = version;
  }

  public BigDecimal getAarfordata() {
    return aarfordata;
  }

  public void setAarfordata(BigDecimal aarfordata) {
    this.aarfordata = aarfordata;
  }

  public BigDecimal getAarforenkeltrettelser() {
    return aarforenkeltrettelser;
  }

  public void setAarforenkeltrettelser(BigDecimal aarforenkeltrettelser) {
    this.aarforenkeltrettelser = aarforenkeltrettelser;
  }

  public BigDecimal getAarformaalt() {
    return aarformaalt;
  }

  public void setAarformaalt(BigDecimal aarformaalt) {
    this.aarformaalt = aarformaalt;
  }

  public BigDecimal getAarforopmaalingsluttet() {
    return aarforopmaalingsluttet;
  }

  public void setAarforopmaalingsluttet(BigDecimal aarforopmaalingsluttet) {
    this.aarforopmaalingsluttet = aarforopmaalingsluttet;
  }

  public BigDecimal getAarforrettelse() {
    return aarforrettelse;
  }

  public void setAarforrettelse(BigDecimal aarforrettelse) {
    this.aarforrettelse = aarforrettelse;
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

  public String getTegner() {
    return tegner;
  }

  public void setTegner(String tegner) {
    this.tegner = tegner;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }
}

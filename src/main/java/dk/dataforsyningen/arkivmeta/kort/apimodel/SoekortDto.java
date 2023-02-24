package dk.dataforsyningen.arkivmeta.kort.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class SoekortDto extends KortDto {
  @Schema(description = "Arkivteknisk betegnelse for kort der er gået i arkiv. 9999 anvendes for ukendt år.")
  private BigDecimal aarforhenlaeggelse;

  @Schema(description = "Det år den oprindelige opmåling til kortet blev afsluttet. Kortet kan senere være nymålt eller rettet.")
  private BigDecimal aarformaalt;

  @Schema(description = "Det år kortet blev udgivet eller trykt. Kortene er udgivet efter deres opmåling og eventuelt genudgivet som følge af rettelser eller at kortet blev udsolgt.")
  private BigDecimal aarforudgivelse;

  @Schema(description = "Oplysninger vedr. produktionen af kortet, f.eks. om det er aktivt, henlagt el.lign.")
  private String kortart;

  @Schema(description = "Angivelse af af område. Kan evt. anvendes til farvandsangivelse.")
  private String soeomraade;

  @Schema(description = "Navn på personen der har tegnet kortet, kortet kan være opmålt af en eller flere opmålere, evt. også af tegneren.")
  private String tegner;

  @Schema(description = "Navn på den organisation der har produceret kortet.")
  private String udgiver;

  public SoekortDto() {
  }

  public SoekortDto(String id,
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
                    BigDecimal aarforhenlaeggelse,
                    BigDecimal aarformaalt,
                    BigDecimal aarforudgivelse,
                    String kortart,
                    String soeomraade,
                    String tegner,
                    String udgiver) {
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
    this.aarforhenlaeggelse = aarforhenlaeggelse;
    this.aarformaalt = aarformaalt;
    this.aarforudgivelse = aarforudgivelse;
    this.kortart = kortart;
    this.soeomraade = soeomraade;
    this.tegner = tegner;
    this.udgiver = udgiver;
  }

  public BigDecimal getAarforhenlaeggelse() {
    return aarforhenlaeggelse;
  }

  public void setAarforhenlaeggelse(BigDecimal aarforhenlaeggelse) {
    this.aarforhenlaeggelse = aarforhenlaeggelse;
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

  public String getKortart() {
    return kortart;
  }

  public void setKortart(String kortart) {
    this.kortart = kortart;
  }

  public String getSoeomraade() {
    return soeomraade;
  }

  public void setSoeomraade(String soeomraade) {
    this.soeomraade = soeomraade;
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

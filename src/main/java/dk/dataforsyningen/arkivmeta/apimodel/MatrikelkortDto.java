package dk.dataforsyningen.arkivmeta.apimodel;

import dk.dataforsyningen.arkivmeta.enums.Arketype;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

public class MatrikelkortDto extends KortDto {
  @Schema(description = "Anvendes pt ikke.")
  private Integer aarforkortproeve;

  @Schema(description = "År for opmåling af matrikelkortet. Hvis kortet er opmålt i flere etaper, er det afslutningen på sidste etape.")
  private Integer aarforopmaalingsluttet;

  @Schema(description = "År for udskiftningen af den pågældende landsby eller ejerlav.")
  private Integer aarforudskiftning;

  @Schema(description = "Oplysninger vedr. produktionen af kortet, f.eks. om det er aktivt, henlagt el.lign.")
  private String kortart;

  @Schema(description = "Anvendes pt ikke.")
  private String kortdimensioner;

  @Schema(description = "Navn på person der har opmålt og/eller tegnet kortet.")
  private String opmaaltaf;

  @Schema(description = "Plannummer for matrikelkortet. Plannummer anvendes hvis kort over et ejerlav er fordelt på flere kortblade.")
  private String plannr;

  @Schema(description = "Intern nummerering af Rytterdistriktskort.")
  private Long rytterdistriktid;

  @Schema(description = "Navn på person der har forestået udskiftningen af en landsby eller ejerlav.")
  private String udskiftetaf;

  public MatrikelkortDto() {
  }

  public MatrikelkortDto(String id,
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
                         Integer aarforkortproeve,
                         Integer aarforopmaalingsluttet,
                         Integer aarforudskiftning,
                         String kortart,
                         String kortdimensioner,
                         String opmaaltaf,
                         String plannr,
                         Long rytterdistriktid,
                         String udskiftetaf) {
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
    this.aarforkortproeve = aarforkortproeve;
    this.aarforopmaalingsluttet = aarforopmaalingsluttet;
    this.aarforudskiftning = aarforudskiftning;
    this.kortart = kortart;
    this.kortdimensioner = kortdimensioner;
    this.opmaaltaf = opmaaltaf;
    this.plannr = plannr;
    this.rytterdistriktid = rytterdistriktid;
    this.udskiftetaf = udskiftetaf;
  }

  public Integer getAarforkortproeve() {
    return aarforkortproeve;
  }

  public void setAarforkortproeve(Integer aarforkortproeve) {
    this.aarforkortproeve = aarforkortproeve;
  }

  public Integer getAarforopmaalingsluttet() {
    return aarforopmaalingsluttet;
  }

  public void setAarforopmaalingsluttet(Integer aarforopmaalingsluttet) {
    this.aarforopmaalingsluttet = aarforopmaalingsluttet;
  }

  public Integer getAarforudskiftning() {
    return aarforudskiftning;
  }

  public void setAarforudskiftning(Integer aarforudskiftning) {
    this.aarforudskiftning = aarforudskiftning;
  }

  public String getKortart() {
    return kortart;
  }

  public void setKortart(String kortart) {
    this.kortart = kortart;
  }

  public String getKortdimensioner() {
    return kortdimensioner;
  }

  public void setKortdimensioner(String kortdimensioner) {
    this.kortdimensioner = kortdimensioner;
  }

  public String getOpmaaltaf() {
    return opmaaltaf;
  }

  public void setOpmaaltaf(String opmaaltaf) {
    this.opmaaltaf = opmaaltaf;
  }

  public String getPlannr() {
    return plannr;
  }

  public void setPlannr(String plannr) {
    this.plannr = plannr;
  }

  public Long getRytterdistriktid() {
    return rytterdistriktid;
  }

  public void setRytterdistriktid(Long rytterdistriktid) {
    this.rytterdistriktid = rytterdistriktid;
  }

  public String getUdskiftetaf() {
    return udskiftetaf;
  }

  public void setUdskiftetaf(String udskiftetaf) {
    this.udskiftetaf = udskiftetaf;
  }
}

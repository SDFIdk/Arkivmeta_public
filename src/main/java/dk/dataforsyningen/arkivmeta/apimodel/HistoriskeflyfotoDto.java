package dk.dataforsyningen.arkivmeta.apimodel;

import dk.dataforsyningen.arkivmeta.enums.Arketype;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

public class HistoriskeflyfotoDto extends KortDto {
  @Schema(description = "Nummeret på dåsen, som indeholder billedets originalnegativ.")
  private String daasenummer;

  @Schema(description = "Farveskema anvendt i det digitale billede, f.eks. sort/hvid 8 bit.")
  private String farveskalatype;

  @Schema(description = "Flyvehøjden ved optagelsen af billedet.")
  private Double flyvehoejde;

  @Schema(description = "Navnet på flyets flyverute. Der er optaget ét eller flere fotos pr flyverute.")
  private String flyverute;

  @Schema(description = "Geografisk koordinat for centrum af flyfoto. X-koordinaten.")
  private Double fotocenterxkoordinat;

  @Schema(description = "Geografisk koordinat for centrum af flyfoto. Y-koordinaten.")
  private Double fotocenterykoordinat;

  @Schema(description = "Fortløbende nummerering af flyfotos. Nummeret er unikt indenfor en flyverute eller evt. kun indenfor et flyfotograferingsprojekt bestående af flere flyveruter.")
  private String fotonummer;

  @Schema(description = "Tidspunkt for optagelse af flyvefotoet. Tiden består typisk af år, måned, dag og tidspunkt.")
  private LocalDateTime fototid;

  @Schema(description = "Beskrivelse af vinkel mod jordoverfladen for flyfotografiet, f.eks. lodfoto eller skråfoto.")
  private String fotovinkel;

  @Schema(description = "Kameratype eller identifikation af kameraet, der er anvendt til flyfotograferingen.")
  private String kameraid;

  @Schema(description = "Navn på den organisation der har forestået flyfotograferingen.")
  private String producent;

  public HistoriskeflyfotoDto() {
  }

  public HistoriskeflyfotoDto(String id,
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
                              String daasenummer,
                              String farveskalatype,
                              Double flyvehoejde,
                              String flyverute,
                              Double fotocenterxkoordinat,
                              Double fotocenterykoordinat,
                              String fotonummer,
                              LocalDateTime fototid,
                              String fotovinkel,
                              String kameraid,
                              String producent) {
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
    this.daasenummer = daasenummer;
    this.farveskalatype = farveskalatype;
    this.flyvehoejde = flyvehoejde;
    this.flyverute = flyverute;
    this.fotocenterxkoordinat = fotocenterxkoordinat;
    this.fotocenterykoordinat = fotocenterykoordinat;
    this.fotonummer = fotonummer;
    this.fototid = fototid;
    this.fotovinkel = fotovinkel;
    this.kameraid = kameraid;
    this.producent = producent;
  }

  public String getDaasenummer() {
    return daasenummer;
  }

  public void setDaasenummer(String daasenummer) {
    this.daasenummer = daasenummer;
  }

  public String getFarveskalatype() {
    return farveskalatype;
  }

  public void setFarveskalatype(String farveskalatype) {
    this.farveskalatype = farveskalatype;
  }

  public Double getFlyvehoejde() {
    return flyvehoejde;
  }

  public void setFlyvehoejde(Double flyvehoejde) {
    this.flyvehoejde = flyvehoejde;
  }

  public String getFlyverute() {
    return flyverute;
  }

  public void setFlyverute(String flyverute) {
    this.flyverute = flyverute;
  }

  public Double getFotocenterxkoordinat() {
    return fotocenterxkoordinat;
  }

  public void setFotocenterxkoordinat(Double fotocenterxkoordinat) {
    this.fotocenterxkoordinat = fotocenterxkoordinat;
  }

  public Double getFotocenterykoordinat() {
    return fotocenterykoordinat;
  }

  public void setFotocenterykoordinat(Double fotocenterykoordinat) {
    this.fotocenterykoordinat = fotocenterykoordinat;
  }

  public String getFotonummer() {
    return fotonummer;
  }

  public void setFotonummer(String fotonummer) {
    this.fotonummer = fotonummer;
  }

  public LocalDateTime getFototid() {
    return fototid;
  }

  public void setFototid(LocalDateTime fototid) {
    this.fototid = fototid;
  }

  public String getFotovinkel() {
    return fotovinkel;
  }

  public void setFotovinkel(String fotovinkel) {
    this.fotovinkel = fotovinkel;
  }

  public String getKameraid() {
    return kameraid;
  }

  public void setKameraid(String kameraid) {
    this.kameraid = kameraid;
  }

  public String getProducent() {
    return producent;
  }

  public void setProducent(String producent) {
    this.producent = producent;
  }
}

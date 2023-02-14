package dk.dataforsyningen.arkivmeta.datamodel;

import java.time.LocalDateTime;
import org.locationtech.jts.geom.Geometry;

public class HistoriskeflyfotoDB extends KortDB {
  private String daasenummer;
  private String farveskalatype;
  private Double flyvehoejde;
  private String flyverute;
  private Double fotocenterxkoordinat;
  private Double fotocenterykoordinat;
  private String fotonummer;
  private LocalDateTime fototid;
  private String fotovinkel;
  private String kameraid;
  private String producent;

  public HistoriskeflyfotoDB() {
  }

  public HistoriskeflyfotoDB(String id,
                             String alternativtitel,
                             String arketype,
                             String bemaerkning,
                             String daekningsomraade,
                             String filer,
                             Integer gaeldendeperiode_gaeldendefra,
                             Integer gaeldendeperiode_gaeldendetil,
                             Geometry geometri,
                             String kortbladnummer,
                             String kortvaerk,
                             String maalestok,
                             Long objectid,
                             String orginalkortprojektion,
                             String originalehjoernekoordinater,
                             LocalDateTime registreringfra,
                             LocalDateTime registreringtil,
                             String stinavn,
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
        gaeldendeperiode_gaeldendefra,
        gaeldendeperiode_gaeldendetil,
        geometri,
        kortbladnummer,
        kortvaerk,
        maalestok,
        objectid,
        orginalkortprojektion,
        originalehjoernekoordinater,
        registreringfra,
        registreringtil,
        stinavn,
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
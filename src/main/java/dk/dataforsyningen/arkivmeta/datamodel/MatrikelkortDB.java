package dk.dataforsyningen.arkivmeta.datamodel;

import java.time.LocalDateTime;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import org.locationtech.jts.geom.Geometry;

@Entity
@DiscriminatorValue("matrikelkort")
public class MatrikelkortDB extends KortDB {
  private Integer aarforkortproeve;
  private Integer aarforopmaalingsluttet;
  private Integer aarforudskiftning;
  private String kortart;
  private String kortdimensioner;
  private String opmaaltaf;
  private String plannr;
  private Long rytterdistriktid;
  private String udskiftetaf;

  public MatrikelkortDB() {
  }

  public MatrikelkortDB(String id,
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
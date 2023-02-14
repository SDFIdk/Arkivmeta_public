package dk.dataforsyningen.arkivmeta.datamodel;

import java.time.LocalDateTime;
import org.locationtech.jts.geom.Geometry;

public class MaalebordsbladeDB extends KortDB {
  private Integer aarfordata;
  private Integer aarforenkeltrettelser;
  private Integer aarformaalt;
  private Integer aarforopmaalingsluttet;
  private Integer aarforrettelse;
  private Integer aarforudarbejdelse;
  private Integer aarforudgivelse;
  private Integer aarforvejdata;
  private String tegner;
  private String version;

  public MaalebordsbladeDB() {
  }

  public MaalebordsbladeDB(String id,
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
                           Integer aarfordata,
                           Integer aarforenkeltrettelser,
                           Integer aarformaalt,
                           Integer aarforopmaalingsluttet,
                           Integer aarforrettelse,
                           Integer aarforudarbejdelse,
                           Integer aarforudgivelse,
                           Integer aarforvejdata,
                           String tegner,
                           String version) {
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

  public Integer getAarfordata() {
    return aarfordata;
  }

  public void setAarfordata(Integer aarfordata) {
    this.aarfordata = aarfordata;
  }

  public Integer getAarforenkeltrettelser() {
    return aarforenkeltrettelser;
  }

  public void setAarforenkeltrettelser(Integer aarforenkeltrettelser) {
    this.aarforenkeltrettelser = aarforenkeltrettelser;
  }

  public Integer getAarformaalt() {
    return aarformaalt;
  }

  public void setAarformaalt(Integer aarformaalt) {
    this.aarformaalt = aarformaalt;
  }

  public Integer getAarforopmaalingsluttet() {
    return aarforopmaalingsluttet;
  }

  public void setAarforopmaalingsluttet(Integer aarforopmaalingsluttet) {
    this.aarforopmaalingsluttet = aarforopmaalingsluttet;
  }

  public Integer getAarforrettelse() {
    return aarforrettelse;
  }

  public void setAarforrettelse(Integer aarforrettelse) {
    this.aarforrettelse = aarforrettelse;
  }

  public Integer getAarforudarbejdelse() {
    return aarforudarbejdelse;
  }

  public void setAarforudarbejdelse(Integer aarforudarbejdelse) {
    this.aarforudarbejdelse = aarforudarbejdelse;
  }

  public Integer getAarforudgivelse() {
    return aarforudgivelse;
  }

  public void setAarforudgivelse(Integer aarforudgivelse) {
    this.aarforudgivelse = aarforudgivelse;
  }

  public Integer getAarforvejdata() {
    return aarforvejdata;
  }

  public void setAarforvejdata(Integer aarforvejdata) {
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
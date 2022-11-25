package dk.dataforsyningen.arkivmeta.datamodel;

import java.time.LocalDateTime;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import org.locationtech.jts.geom.Geometry;

@Entity
@DiscriminatorValue("centimeterkort")
public class CentimeterkortDB extends KortDB {
  private Integer aarforadministrativerettelser;
  private Integer aarfordata;
  private Integer aarforfotogrametriskudtegning;
  private Integer aarforfotorekogrettelser;
  private Integer aarforkompleteteretimarken;
  private Integer aarformaalt;
  private Integer aarforrevision;
  private Integer aarforudarbejdelse;
  private Integer aarforudgivelse;
  private Integer aarforvejdata;
  private String version;

  public CentimeterkortDB() {
  }

  public CentimeterkortDB(String id,
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
                          Integer aarforadministrativerettelser,
                          Integer aarfordata,
                          Integer aarforfotogrametriskudtegning,
                          Integer aarforfotorekogrettelser,
                          Integer aarforkompleteteretimarken,
                          Integer aarformaalt,
                          Integer aarforrevision,
                          Integer aarforudarbejdelse,
                          Integer aarforudgivelse,
                          Integer aarforvejdata,
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

  public Integer getAarforadministrativerettelser() {
    return aarforadministrativerettelser;
  }

  public void setAarforadministrativerettelser(Integer aarforadministrativerettelser) {
    this.aarforadministrativerettelser = aarforadministrativerettelser;
  }

  public Integer getAarfordata() {
    return aarfordata;
  }

  public void setAarfordata(Integer aarfordata) {
    this.aarfordata = aarfordata;
  }

  public Integer getAarforfotogrametriskudtegning() {
    return aarforfotogrametriskudtegning;
  }

  public void setAarforfotogrametriskudtegning(Integer aarforfotogrametriskudtegning) {
    this.aarforfotogrametriskudtegning = aarforfotogrametriskudtegning;
  }

  public Integer getAarforfotorekogrettelser() {
    return aarforfotorekogrettelser;
  }

  public void setAarforfotorekogrettelser(Integer aarforfotorekogrettelser) {
    this.aarforfotorekogrettelser = aarforfotorekogrettelser;
  }

  public Integer getAarforkompleteteretimarken() {
    return aarforkompleteteretimarken;
  }

  public void setAarforkompleteteretimarken(Integer aarforkompleteteretimarken) {
    this.aarforkompleteteretimarken = aarforkompleteteretimarken;
  }

  public Integer getAarformaalt() {
    return aarformaalt;
  }

  public void setAarformaalt(Integer aarformaalt) {
    this.aarformaalt = aarformaalt;
  }

  public Integer getAarforrevision() {
    return aarforrevision;
  }

  public void setAarforrevision(Integer aarforrevision) {
    this.aarforrevision = aarforrevision;
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

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }
}
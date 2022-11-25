package dk.dataforsyningen.arkivmeta.datamodel;

import java.time.LocalDateTime;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import org.locationtech.jts.geom.Geometry;

@Entity
@DiscriminatorValue("groenlandtopokort")
public class GroenlandtopokortDB extends KortDB {
  private Integer aarforfotografering;
  private Integer aarforlineaerrettelse;
  private Integer aarformaalt;
  private Integer aarforpunktgrundlag;
  private Integer aarforrevisonafnavnemm;
  private Integer aarfortopografi;
  private Integer aarforudgivelse;
  private Integer aarforudtegning;
  private String version;

  public GroenlandtopokortDB() {
  }

  public GroenlandtopokortDB(String id,
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
                             Integer aarforfotografering,
                             Integer aarforlineaerrettelse,
                             Integer aarformaalt,
                             Integer aarforpunktgrundlag,
                             Integer aarforrevisonafnavnemm,
                             Integer aarfortopografi,
                             Integer aarforudgivelse,
                             Integer aarforudtegning,
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
    this.aarforfotografering = aarforfotografering;
    this.aarforlineaerrettelse = aarforlineaerrettelse;
    this.aarformaalt = aarformaalt;
    this.aarforpunktgrundlag = aarforpunktgrundlag;
    this.aarforrevisonafnavnemm = aarforrevisonafnavnemm;
    this.aarfortopografi = aarfortopografi;
    this.aarforudgivelse = aarforudgivelse;
    this.aarforudtegning = aarforudtegning;
    this.version = version;
  }

  public Integer getAarforfotografering() {
    return aarforfotografering;
  }

  public void setAarforfotografering(Integer aarforfotografering) {
    this.aarforfotografering = aarforfotografering;
  }

  public Integer getAarforlineaerrettelse() {
    return aarforlineaerrettelse;
  }

  public void setAarforlineaerrettelse(Integer aarforlineaerrettelse) {
    this.aarforlineaerrettelse = aarforlineaerrettelse;
  }

  public Integer getAarformaalt() {
    return aarformaalt;
  }

  public void setAarformaalt(Integer aarformaalt) {
    this.aarformaalt = aarformaalt;
  }

  public Integer getAarforpunktgrundlag() {
    return aarforpunktgrundlag;
  }

  public void setAarforpunktgrundlag(Integer aarforpunktgrundlag) {
    this.aarforpunktgrundlag = aarforpunktgrundlag;
  }

  public Integer getAarforrevisonafnavnemm() {
    return aarforrevisonafnavnemm;
  }

  public void setAarforrevisonafnavnemm(Integer aarforrevisonafnavnemm) {
    this.aarforrevisonafnavnemm = aarforrevisonafnavnemm;
  }

  public Integer getAarfortopografi() {
    return aarfortopografi;
  }

  public void setAarfortopografi(Integer aarfortopografi) {
    this.aarfortopografi = aarfortopografi;
  }

  public Integer getAarforudgivelse() {
    return aarforudgivelse;
  }

  public void setAarforudgivelse(Integer aarforudgivelse) {
    this.aarforudgivelse = aarforudgivelse;
  }

  public Integer getAarforudtegning() {
    return aarforudtegning;
  }

  public void setAarforudtegning(Integer aarforudtegning) {
    this.aarforudtegning = aarforudtegning;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }
}
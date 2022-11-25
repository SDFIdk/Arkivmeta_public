package dk.dataforsyningen.arkivmeta.datamodel;

import java.time.LocalDateTime;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import org.locationtech.jts.geom.Geometry;

@Entity
@DiscriminatorValue("soekort")
public class SoekortDB extends KortDB {
  private Integer aarforhenlaeggelse;
  private Integer aarformaalt;
  private Integer aarforudgivelse;
  private String kortart;
  private String soeomraade;
  private String tegner;
  private String udgiver;

  public SoekortDB() {
  }

  public SoekortDB(String id,
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
                   Integer aarforhenlaeggelse,
                   Integer aarformaalt,
                   Integer aarforudgivelse,
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
    this.aarforhenlaeggelse = aarforhenlaeggelse;
    this.aarformaalt = aarformaalt;
    this.aarforudgivelse = aarforudgivelse;
    this.kortart = kortart;
    this.soeomraade = soeomraade;
    this.tegner = tegner;
    this.udgiver = udgiver;
  }

  public Integer getAarforhenlaeggelse() {
    return aarforhenlaeggelse;
  }

  public void setAarforhenlaeggelse(Integer aarforhenlaeggelse) {
    this.aarforhenlaeggelse = aarforhenlaeggelse;
  }

  public Integer getAarformaalt() {
    return aarformaalt;
  }

  public void setAarformaalt(Integer aarformaalt) {
    this.aarformaalt = aarformaalt;
  }

  public Integer getAarforudgivelse() {
    return aarforudgivelse;
  }

  public void setAarforudgivelse(Integer aarforudgivelse) {
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
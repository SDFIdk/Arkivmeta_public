package dk.dataforsyningen.arkivmeta.datamodel;

import java.time.LocalDateTime;
import org.locationtech.jts.geom.Geometry;

public class FaeroesketopokortDB extends KortDB {
  private Integer aarformaalt;
  private Integer aarforudgivelse;
  private String version;

  public FaeroesketopokortDB() {
  }

  public FaeroesketopokortDB(String id,
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
                             Integer aarformaalt,
                             Integer aarforudgivelse,
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
    this.aarformaalt = aarformaalt;
    this.aarforudgivelse = aarforudgivelse;
    this.version = version;
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

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }
}
package dk.dataforsyningen.arkivmeta.datamodel;

import java.time.LocalDateTime;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import org.locationtech.jts.geom.Geometry;

@Entity
@DiscriminatorValue("tematiskekort")
public class TematiskekortDB extends KortDB {
  private Integer aarforudarbejdetmateriale;
  private Integer aarforudgivelse;

  public TematiskekortDB() {
  }

  public TematiskekortDB(String id,
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
                         Integer aarforudarbejdetmateriale,
                         Integer aarforudgivelse) {
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
    this.aarforudarbejdetmateriale = aarforudarbejdetmateriale;
    this.aarforudgivelse = aarforudgivelse;
  }

  public Integer getAarforudarbejdetmateriale() {
    return aarforudarbejdetmateriale;
  }

  public void setAarforudarbejdetmateriale(Integer aarforudarbejdetmateriale) {
    this.aarforudarbejdetmateriale = aarforudarbejdetmateriale;
  }

  public Integer getAarforudgivelse() {
    return aarforudgivelse;
  }

  public void setAarforudgivelse(Integer aarforudgivelse) {
    this.aarforudgivelse = aarforudgivelse;
  }
}
package dk.dataforsyningen.arkivmeta.datamodel;

import java.io.Serializable;
import java.time.LocalDateTime;
import org.locationtech.jts.geom.Geometry;

/**
 * Abstract Superclass
 * id the composed id of arketype and objectid to get an unique id
 */
//@Entity
//@Table(name = "arkivmeta", schema = "arkivmeta")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "arketype", discriminatorType = DiscriminatorType.STRING)
//@Immutable
public abstract class KortDB implements Serializable {
  protected String id = "";
  protected String alternativtitel;
  protected String arketype;
  protected String bemaerkning;
  protected String daekningsomraade;
  protected String filer;
  protected Integer gaeldendeperiode_gaeldendefra;
  protected Integer gaeldendeperiode_gaeldendetil;
  protected Geometry geometri;
  protected String kortbladnummer;
  protected String kortvaerk;
  protected String maalestok;
  protected Long objectid;
  protected String orginalkortprojektion;
  protected String originalehjoernekoordinater;
  protected LocalDateTime registreringfra;
  protected LocalDateTime registreringtil;
  protected String stinavn;
  protected String titel;
  protected String uniktkortnavn;

  public KortDB() {
  }

  public KortDB(String id,
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
                String uniktkortnavn) {
    this.id = id;
    this.alternativtitel = alternativtitel;
    this.arketype = arketype;
    this.bemaerkning = bemaerkning;
    this.daekningsomraade = daekningsomraade;
    this.filer = filer;
    this.gaeldendeperiode_gaeldendefra = gaeldendeperiode_gaeldendefra;
    this.gaeldendeperiode_gaeldendetil = gaeldendeperiode_gaeldendetil;
    this.geometri = geometri;
    this.kortbladnummer = kortbladnummer;
    this.kortvaerk = kortvaerk;
    this.maalestok = maalestok;
    this.objectid = objectid;
    this.orginalkortprojektion = orginalkortprojektion;
    this.originalehjoernekoordinater = originalehjoernekoordinater;
    this.registreringfra = registreringfra;
    this.registreringtil = registreringtil;
    this.stinavn = stinavn;
    this.titel = titel;
    this.uniktkortnavn = uniktkortnavn;
  }

  public String getId() {
    return id;
  }

  public String getAlternativtitel() {
    return alternativtitel;
  }

  public String getArketype() {
    return arketype;
  }

  public String getBemaerkning() {
    return bemaerkning;
  }

  public String getDaekningsomraade() {
    return daekningsomraade;
  }

  public String getFiler() {
    return filer;
  }

  public Integer getGaeldendeperiode_gaeldendefra() {
    return gaeldendeperiode_gaeldendefra;
  }

  public Integer getGaeldendeperiode_gaeldendetil() {
    return gaeldendeperiode_gaeldendetil;
  }

  public Geometry getGeometri() {
    return geometri;
  }

  public String getKortbladnummer() {
    return kortbladnummer;
  }

  public String getKortvaerk() {
    return kortvaerk;
  }

  public String getMaalestok() {
    return maalestok;
  }

  public Long getObjectid() {
    return objectid;
  }

  public String getOrginalkortprojektion() {
    return orginalkortprojektion;
  }

  public String getOriginalehjoernekoordinater() {
    return originalehjoernekoordinater;
  }

  public LocalDateTime getRegistreringfra() {
    return registreringfra;
  }

  public LocalDateTime getRegistreringtil() {
    return registreringtil;
  }

  public String getStinavn() {
    return stinavn;
  }

  public String getTitel() {
    return titel;
  }

  public String getUniktkortnavn() {
    return uniktkortnavn;
  }
}
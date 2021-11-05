package dk.dataforsyningen.arkivmeta.datamodel;

import org.locationtech.jts.geom.Geometry;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("aeldretopografiskekort")
public class AeldretopografiskekortDB extends KortDB
{
    private Integer aarformaalt;
    private Integer aarforudgivelse;
    private String stedbetegnelse;
    private String tegner;
    private String udgiver;

    public AeldretopografiskekortDB()
    {
    }

    public AeldretopografiskekortDB(String id,
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
                                    String stedbetegnelse,
                                    String tegner,
                                    String udgiver)
    {
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
        this.stedbetegnelse = stedbetegnelse;
        this.tegner = tegner;
        this.udgiver = udgiver;
    }

    public Integer getAarformaalt()
    {
        return aarformaalt;
    }

    public void setAarformaalt(Integer aarformaalt)
    {
        this.aarformaalt = aarformaalt;
    }

    public Integer getAarforudgivelse()
    {
        return aarforudgivelse;
    }

    public void setAarforudgivelse(Integer aarforudgivelse)
    {
        this.aarforudgivelse = aarforudgivelse;
    }

    public String getStedbetegnelse()
    {
        return stedbetegnelse;
    }

    public void setStedbetegnelse(String stedbetegnelse)
    {
        this.stedbetegnelse = stedbetegnelse;
    }

    public String getTegner()
    {
        return tegner;
    }

    public void setTegner(String tegner)
    {
        this.tegner = tegner;
    }

    public String getUdgiver()
    {
        return udgiver;
    }

    public void setUdgiver(String udgiver)
    {
        this.udgiver = udgiver;
    }
}
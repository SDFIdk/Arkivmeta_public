package dk.dataforsyningen.arkivmeta.datamodel;

import org.locationtech.jts.geom.Geometry;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("landoekonomiskekort")
public class LandoekonomiskekortDB extends KortDB
{
    private Integer aarformaalt;
    private String tegner;

    public LandoekonomiskekortDB()
    {
    }

    public LandoekonomiskekortDB(String id,
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
                                 String tegner)
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
        this.tegner = tegner;
    }

    public Integer getAarformaalt()
    {
        return aarformaalt;
    }

    public void setAarformaalt(Integer aarformaalt)
    {
        this.aarformaalt = aarformaalt;
    }

    public String getTegner()
    {
        return tegner;
    }

    public void setTegner(String tegner)
    {
        this.tegner = tegner;
    }
}
package dk.dataforsyningen.arkivmeta.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public class CentimeterkortDto extends KortDto
{
    @Schema(description = "Året hvor administrative grænser er rettet på kortet.")
    private Integer aarforadministrativerettelser;

    @Schema(description = "Kort udarbejdet på grundlag af data fra dette år eller data sammenstillet i dette år.")
    private Integer aarfordata;

    @Schema(description = "År hvor kortet er lavet på basis af flyfoto.")
    private Integer aarforfotogrametriskudtegning;

    @Schema(description = "År hvor kortet er opdateret på basis af flyfoto.")
    private Integer aarforfotorekogrettelser;

    @Schema(description = "År, hvor kortet er opdateret efter opmåling i marken.")
    private Integer aarforkompleteteretimarken;

    @Schema(description = "Det år den oprindelige opmåling til kortet blev afsluttet.")
    private Integer aarformaalt;

    @Schema(description = "År for revision af kortet. En revision er typisk en kvalitetskontrol.")
    private Integer aarforrevision;

    @Schema(description = "Året hvor udarbejdelsen blev afsluttet første gang.")
    private Integer aarforudarbejdelse;

    @Schema(description = "Det år kortet blev udgivet eller trykt. Kortene er udgivet efter deres opmåling og eventuelt genudgivet som følge af rettelser eller at kortet blev udsolgt.")
    private Integer aarforudgivelse;

    @Schema(description = "År hvor kortet er opdateret med vejdata.")
    private Integer aarforvejdata;

    @Schema(description = "Versionsnummer for kortet. Et kort kan være udgivet i flere versioner.")
    private String version;

    public CentimeterkortDto()
    {
    }

    public CentimeterkortDto(String id,
                             String alternativtitel,
                             String arketype,
                             String bemaerkning,
                             List<String> daekningsomraade,
                             List<String> filer,
                             Integer gaeldendefra,
                             Integer gaeldendetil,
                             String geometri,
                             String kortbladnummer,
                             String kortvaerk,
                             String maalestok,
                             String originalkortprojektion,
                             String originalehjoernekoordinater,
                             LocalDateTime registreringfra,
                             LocalDateTime registreringtil,
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
                             String version)
    {
        super(id,
              alternativtitel,
              arketype,
              bemaerkning,
              daekningsomraade,
              filer,
              gaeldendefra,
              gaeldendetil,
              geometri,
              kortbladnummer,
              kortvaerk,
              maalestok,
              originalkortprojektion,
              originalehjoernekoordinater,
              registreringfra,
              registreringtil,
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

    public Integer getAarforadministrativerettelser()
    {
        return aarforadministrativerettelser;
    }

    public void setAarforadministrativerettelser(Integer aarforadministrativerettelser)
    {
        this.aarforadministrativerettelser = aarforadministrativerettelser;
    }

    public Integer getAarfordata()
    {
        return aarfordata;
    }

    public void setAarfordata(Integer aarfordata)
    {
        this.aarfordata = aarfordata;
    }

    public Integer getAarforfotogrametriskudtegning()
    {
        return aarforfotogrametriskudtegning;
    }

    public void setAarforfotogrametriskudtegning(Integer aarforfotogrametriskudtegning)
    {
        this.aarforfotogrametriskudtegning = aarforfotogrametriskudtegning;
    }

    public Integer getAarforfotorekogrettelser()
    {
        return aarforfotorekogrettelser;
    }

    public void setAarforfotorekogrettelser(Integer aarforfotorekogrettelser)
    {
        this.aarforfotorekogrettelser = aarforfotorekogrettelser;
    }

    public Integer getAarforkompleteteretimarken()
    {
        return aarforkompleteteretimarken;
    }

    public void setAarforkompleteteretimarken(Integer aarforkompleteteretimarken)
    {
        this.aarforkompleteteretimarken = aarforkompleteteretimarken;
    }

    public Integer getAarformaalt()
    {
        return aarformaalt;
    }

    public void setAarformaalt(Integer aarformaalt)
    {
        this.aarformaalt = aarformaalt;
    }

    public Integer getAarforrevision()
    {
        return aarforrevision;
    }

    public void setAarforrevision(Integer aarforrevision)
    {
        this.aarforrevision = aarforrevision;
    }

    public Integer getAarforudarbejdelse()
    {
        return aarforudarbejdelse;
    }

    public void setAarforudarbejdelse(Integer aarforudarbejdelse)
    {
        this.aarforudarbejdelse = aarforudarbejdelse;
    }

    public Integer getAarforudgivelse()
    {
        return aarforudgivelse;
    }

    public void setAarforudgivelse(Integer aarforudgivelse)
    {
        this.aarforudgivelse = aarforudgivelse;
    }

    public Integer getAarforvejdata()
    {
        return aarforvejdata;
    }

    public void setAarforvejdata(Integer aarforvejdata)
    {
        this.aarforvejdata = aarforvejdata;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }
}

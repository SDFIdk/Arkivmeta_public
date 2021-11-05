package dk.dataforsyningen.arkivmeta.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public class MaalebordsbladeDto extends KortDto
{
    @Schema(description = "Kort udarbejdet på grundlag af data fra dette år eller data sammenstillet i dette år.")
    private Integer aarfordata;

    @Schema(description = "År for opdatering af kortet med enkelte rettelser, f.eks. en ny bro.")
    private Integer aarforenkeltrettelser;

    @Schema(description = "Det år den oprindelige opmåling til kortet blev afsluttet. Kortet kan senere være nymålt eller rettet.")
    private Integer aarformaalt;

    @Schema(description = "År for opmåling af matrikelkortet. Hvis kortet er opmålt i flere etaper, er det afslutningen på sidste etape.")
    private Integer aarforopmaalingsluttet;

    @Schema(description = "År for opdatering af kortet med rettelser, typisk efter at kontrolmålinger er udført.")
    private Integer aarforrettelse;

    @Schema(description = "Året hvor udarbejdelsen blev afsluttet første gang.")
    private Integer aarforudarbejdelse;

    @Schema(description = "Det år kortet blev udgivet eller trykt. Kortene er udgivet efter deres opmåling og eventuelt genudgivet som følge af rettelser eller at kortet blev udsolgt.")
    private Integer aarforudgivelse;

    @Schema(description = "År hvor kortet er opdateret med vejdata.")
    private Integer aarforvejdata;

    @Schema(description = "Navn på personen der har tegnet kortet, kortet kan være opmålt af en eller flere opmålere, evt. også af tegneren.")
    private String tegner;

    @Schema(description = "Versionsnummer for kortet. Et kort kan være udgivet i flere versioner.")
    private String version;

    public MaalebordsbladeDto()
    {
    }

    public MaalebordsbladeDto(String id,
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
                              Integer aarfordata,
                              Integer aarforenkeltrettelser,
                              Integer aarformaalt,
                              Integer aarforopmaalingsluttet,
                              Integer aarforrettelse,
                              Integer aarforudarbejdelse,
                              Integer aarforudgivelse,
                              Integer aarforvejdata,
                              String tegner,
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

    public Integer getAarfordata()
    {
        return aarfordata;
    }

    public void setAarfordata(Integer aarfordata)
    {
        this.aarfordata = aarfordata;
    }

    public Integer getAarforenkeltrettelser()
    {
        return aarforenkeltrettelser;
    }

    public void setAarforenkeltrettelser(Integer aarforenkeltrettelser)
    {
        this.aarforenkeltrettelser = aarforenkeltrettelser;
    }

    public Integer getAarformaalt()
    {
        return aarformaalt;
    }

    public void setAarformaalt(Integer aarformaalt)
    {
        this.aarformaalt = aarformaalt;
    }

    public Integer getAarforopmaalingsluttet()
    {
        return aarforopmaalingsluttet;
    }

    public void setAarforopmaalingsluttet(Integer aarforopmaalingsluttet)
    {
        this.aarforopmaalingsluttet = aarforopmaalingsluttet;
    }

    public Integer getAarforrettelse()
    {
        return aarforrettelse;
    }

    public void setAarforrettelse(Integer aarforrettelse)
    {
        this.aarforrettelse = aarforrettelse;
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

    public String getTegner()
    {
        return tegner;
    }

    public void setTegner(String tegner)
    {
        this.tegner = tegner;
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

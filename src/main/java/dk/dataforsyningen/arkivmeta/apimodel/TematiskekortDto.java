package dk.dataforsyningen.arkivmeta.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public class TematiskekortDto extends KortDto
{
    @Schema(description = "Året hvor kortet blev færdigtegnet.")
    private Integer aarforudarbejdetmateriale;

    @Schema(description = "Det år kortet blev udgivet eller trykt. Kortene er udgivet efter deres opmåling og eventuelt genudgivet som følge af rettelser eller at kortet blev udsolgt.")
    private Integer aarforudgivelse;

    public TematiskekortDto()
    {
    }

    public TematiskekortDto(String id,
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
                            Integer aarforudarbejdetmateriale,
                            Integer aarforudgivelse)
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
        this.aarforudarbejdetmateriale = aarforudarbejdetmateriale;
        this.aarforudgivelse = aarforudgivelse;
    }

    public Integer getAarforudarbejdetmateriale()
    {
        return aarforudarbejdetmateriale;
    }

    public void setAarforudarbejdetmateriale(Integer aarforudarbejdetmateriale)
    {
        this.aarforudarbejdetmateriale = aarforudarbejdetmateriale;
    }

    public Integer getAarforudgivelse()
    {
        return aarforudgivelse;
    }

    public void setAarforudgivelse(Integer aarforudgivelse)
    {
        this.aarforudgivelse = aarforudgivelse;
    }
}

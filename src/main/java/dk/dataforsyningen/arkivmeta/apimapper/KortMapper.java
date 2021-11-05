package dk.dataforsyningen.arkivmeta.apimapper;

import dk.dataforsyningen.arkivmeta.apimodel.*;
import dk.dataforsyningen.arkivmeta.datamodel.*;
import org.springframework.stereotype.Component;

@Component
public class KortMapper
{
    private final MapperDaekningsomraade mapperDaekningsomraade;
    private final MapperFiler mapperFiler;
    private final MapperGeometri mapperGeometri;

    public KortMapper(MapperDaekningsomraade mapperDaekningsomraade,
                      MapperFiler mapperFiler, MapperGeometri mapperGeometri)
    {
        this.mapperDaekningsomraade = mapperDaekningsomraade;
        this.mapperFiler = mapperFiler;
        this.mapperGeometri = mapperGeometri;
    }

    public KortDto map(KortDB kort)
    {
        if (kort instanceof AeldretopografiskekortDB)
        {
            return mapAeldretopografiskekortDto((AeldretopografiskekortDB) kort);
        }
        if (kort instanceof CentimeterkortDB)
        {
            return mapCentimeterkortDto((CentimeterkortDB) kort);
        }
        if (kort instanceof FaeroesketopokortDB)
        {
            return mapFaeroesketopokortDto((FaeroesketopokortDB) kort);
        }
        if (kort instanceof GroenlandtopokortDB)
        {
            return mapGroenlandtopokortDto((GroenlandtopokortDB) kort);
        }
        if (kort instanceof HistoriskeflyfotoDB)
        {
            return mapHistoriskeflyfotoDto((HistoriskeflyfotoDB) kort);
        }
        if (kort instanceof LandoekonomiskekortDB)
        {
            return mapLandoekonomiskekortDto((LandoekonomiskekortDB) kort);
        }
        if (kort instanceof MaalebordsbladeDB)
        {
            return mapMaalebordsbladeDto((MaalebordsbladeDB) kort);
        }
        if (kort instanceof MatrikelkortDB)
        {
            return mapMatrikelkortDto((MatrikelkortDB) kort);
        }
        if (kort instanceof SoekortDB)
        {
            return mapSoekortDto((SoekortDB) kort);
        }
        if (kort instanceof TematiskekortDB)
        {
            return mapTematiskekortDto((TematiskekortDB) kort);
        }

        throw new IllegalArgumentException("Ukendt arketype");
    }

    public <T extends KortDto> T map(KortDB db, T dto)
    {
        dto.setAlternativtitel(db.getAlternativtitel());
        dto.setArketype(db.getArketype());
        dto.setBemaerkning(db.getBemaerkning());
        dto.setDaekningsomraade(mapperDaekningsomraade.mapDaekningsomraade(db.getDaekningsomraade()));
        dto.setFiler(mapperFiler.mapFiler(db.getFiler()));
        dto.setGaeldendefra(db.getGaeldendeperiode_gaeldendefra());
        dto.setGaeldendetil(db.getGaeldendeperiode_gaeldendetil());
        dto.setGeometri(mapperGeometri.mapGeometri(db.getGeometri()));
        dto.setId(db.getId());
        dto.setKortbladnummer(db.getKortbladnummer());
        dto.setKortvaerk(db.getKortvaerk());
        dto.setMaalestok(db.getMaalestok());
        dto.setOriginalehjoernekoordinater(db.getOriginalehjoernekoordinater());
        dto.setOriginalehjoernekoordinater(db.getOriginalehjoernekoordinater());
        dto.setRegistreringfra(db.getRegistreringfra());
        dto.setRegistreringtil(db.getRegistreringtil());
        dto.setTitel(db.getTitel());
        dto.setUniktkortnavn(db.getUniktkortnavn());
        return dto;
    }

    /**
     * Map AeldretopografiskekortDB to AeldretopografiskekortDto
     *
     * @param db
     * @return
     */
    private AeldretopografiskekortDto mapAeldretopografiskekortDto(AeldretopografiskekortDB db)
    {
        // Mapper db og giver en ny instans med af AeldretopografiskekortDto
        AeldretopografiskekortDto dto = map(db, new AeldretopografiskekortDto());

        // AeldretopografiskekortDto har nogle felter der skal sættes ud over hvad KortDto har
        dto.setAarformaalt((db.getAarformaalt()));
        dto.setAarforudgivelse(db.getAarforudgivelse());
        dto.setStedbetegnelse(db.getStedbetegnelse());
        dto.setTegner(db.getTegner());
        dto.setUdgiver(db.getUdgiver());

        return dto;
    }

    private CentimeterkortDto mapCentimeterkortDto(CentimeterkortDB db)
    {
        // Mapper db og giver en ny instans med af CentimeterkortDto
        CentimeterkortDto dto = map(db, new CentimeterkortDto());

        dto.setAarforadministrativerettelser(db.getAarforadministrativerettelser());
        dto.setAarfordata(db.getAarfordata());
        dto.setAarforfotogrametriskudtegning(db.getAarforfotogrametriskudtegning());
        dto.setAarforfotorekogrettelser(db.getAarforfotorekogrettelser());
        dto.setAarforkompleteteretimarken(db.getAarforkompleteteretimarken());
        dto.setAarformaalt(db.getAarformaalt());
        dto.setAarforrevision(db.getAarforrevision());
        dto.setAarforudarbejdelse(db.getAarforudarbejdelse());
        dto.setAarforudgivelse(db.getAarforudgivelse());
        dto.setAarforvejdata(db.getAarforvejdata());
        dto.setVersion(db.getVersion());

        return dto;
    }

    private FaeroesketopokortDto mapFaeroesketopokortDto(FaeroesketopokortDB db)
    {
        // Mapper db og giver en ny instans med af FaeroesketopokortDto
        FaeroesketopokortDto dto = map(db, new FaeroesketopokortDto());

        dto.setAarformaalt(db.getAarformaalt());
        dto.setAarforudgivelse(db.getAarforudgivelse());
        dto.setVersion(db.getVersion());

        return dto;
    }

    private GroenlandtopokortDto mapGroenlandtopokortDto(GroenlandtopokortDB db)
    {
        // Mapper db og giver en ny instans med af GroenlandtopokortDto
        GroenlandtopokortDto dto = map(db, new GroenlandtopokortDto());

        dto.setAarforfotografering(db.getAarforfotografering());
        dto.setAarforlineaerrettelse(db.getAarforlineaerrettelse());
        dto.setAarformaalt(db.getAarformaalt());
        dto.setAarforpunktgrundlag(db.getAarforpunktgrundlag());
        dto.setAarforrevisonafnavnemm(db.getAarforrevisonafnavnemm());
        dto.setAarfortopografi(db.getAarfortopografi());
        dto.setAarforudgivelse(db.getAarforudgivelse());
        dto.setAarforudtegning(db.getAarforudtegning());
        dto.setVersion(db.getVersion());

        return dto;
    }

    private HistoriskeflyfotoDto mapHistoriskeflyfotoDto(HistoriskeflyfotoDB db)
    {
        // Mapper db og giver en ny instans med af HistoriskeflyfotoDto
        HistoriskeflyfotoDto dto = map(db, new HistoriskeflyfotoDto());

        dto.setDaasenummer(db.getDaasenummer());
        dto.setFarveskalatype(db.getFarveskalatype());
        dto.setFlyvehoejde(db.getFlyvehoejde());
        dto.setFlyverute(db.getFlyverute());
        dto.setFotocenterxkoordinat(db.getFotocenterxkoordinat());
        dto.setFotocenterykoordinat(db.getFotocenterykoordinat());
        dto.setFotonummer(db.getFotonummer());
        dto.setFototid(db.getFototid());
        dto.setFotovinkel(db.getFotovinkel());
        dto.setKameraid(db.getKameraid());
        dto.setProducent(db.getProducent());

        return dto;
    }

    private LandoekonomiskekortDto mapLandoekonomiskekortDto(LandoekonomiskekortDB db)
    {
        // Mapper db og giver en ny instans med af LandoekonomiskekortDto
        LandoekonomiskekortDto dto = map(db, new LandoekonomiskekortDto());

        dto.setAarformaalt(db.getAarformaalt());
        dto.setTegner(db.getTegner());

        return dto;
    }

    private MaalebordsbladeDto mapMaalebordsbladeDto(MaalebordsbladeDB db)
    {
        // Mapper db og giver en ny instans med af MaalebordsbladeDto
        MaalebordsbladeDto dto = map(db, new MaalebordsbladeDto());

        dto.setAarfordata(db.getAarfordata());
        dto.setAarforenkeltrettelser(db.getAarforenkeltrettelser());
        dto.setAarformaalt(db.getAarformaalt());
        dto.setAarforopmaalingsluttet(db.getAarforopmaalingsluttet());
        dto.setAarforrettelse(db.getAarforrettelse());
        dto.setAarforudarbejdelse(db.getAarforudarbejdelse());
        dto.setAarforudgivelse(db.getAarforudgivelse());
        dto.setAarforvejdata(db.getAarforvejdata());
        dto.setTegner(db.getTegner());
        dto.setVersion(db.getVersion());

        return dto;
    }

    private MatrikelkortDto mapMatrikelkortDto(MatrikelkortDB db)
    {
        // Mapper db og giver en ny instans med af MatrikelkortDto
        MatrikelkortDto dto = map(db, new MatrikelkortDto());

        dto.setAarforkortproeve(db.getAarforkortproeve());
        dto.setAarforopmaalingsluttet(db.getAarforopmaalingsluttet());
        dto.setAarforudskiftning(db.getAarforudskiftning());
        dto.setKortart(db.getKortart());
        dto.setKortdimensioner(db.getKortdimensioner());
        dto.setOpmaaltaf(db.getOpmaaltaf());
        dto.setPlannr(db.getPlannr());
        dto.setRytterdistriktid(db.getRytterdistriktid());
        dto.setUdskiftetaf(db.getUdskiftetaf());

        return dto;
    }

    private SoekortDto mapSoekortDto(SoekortDB db)
    {
        // Mapper db og giver en ny instans med af SoekortDto
        SoekortDto dto = map(db, new SoekortDto());

        dto.setAarforhenlaeggelse(db.getAarforhenlaeggelse());
        dto.setAarformaalt((db.getAarformaalt()));
        dto.setAarforudgivelse(db.getAarforudgivelse());
        dto.setKortart(db.getKortart());
        dto.setSoeomraade(db.getSoeomraade());
        dto.setTegner(db.getTegner());
        dto.setUdgiver(db.getUdgiver());

        return dto;
    }

    private TematiskekortDto mapTematiskekortDto(TematiskekortDB db)
    {
        // Mapper db og giver en ny instans med af TematiskekortDto
        TematiskekortDto dto = map(db, new TematiskekortDto());

        dto.setAarforudarbejdetmateriale(db.getAarforudarbejdetmateriale());
        dto.setAarforudgivelse(db.getAarforudgivelse());

        return dto;
    }
}

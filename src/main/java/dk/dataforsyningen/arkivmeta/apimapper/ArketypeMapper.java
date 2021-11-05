package dk.dataforsyningen.arkivmeta.apimapper;

import dk.dataforsyningen.arkivmeta.apimodel.ArketypeDto;
import dk.dataforsyningen.arkivmeta.datamodel.ArketypeDB;
import org.springframework.stereotype.Component;

@Component
public class ArketypeMapper
{
    private final MapperKortvaerk mapperKortvaerk;

    public ArketypeMapper(MapperKortvaerk mapperKortvaerk)
    {
        this.mapperKortvaerk = mapperKortvaerk;
    }

    public ArketypeDto arketypeToArketypeDto(ArketypeDB db)
    {
        ArketypeDto dto = new ArketypeDto();
        dto.setArketype(db.getArketype().toLowerCase());
        dto.setArkenavn(db.getArkenavn());
        dto.setKortvaerker(mapperKortvaerk.mapKortvaerk(db.getKortvaerk()));
        return dto;
    }
}

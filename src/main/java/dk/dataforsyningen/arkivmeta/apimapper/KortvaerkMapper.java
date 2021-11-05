package dk.dataforsyningen.arkivmeta.apimapper;

import dk.dataforsyningen.arkivmeta.apimodel.KortvaerkDto;
import dk.dataforsyningen.arkivmeta.datamodel.KortvaerkDB;

public class KortvaerkMapper
{
    public KortvaerkDto kortvaerkToKortvaerkDto(KortvaerkDB db)
    {
        KortvaerkDto dto = new KortvaerkDto(db.getKortvaerk());
        dto.setArkenavn(db.getArkenavn());
        dto.setArketype(db.getArketype());
        dto.setKortvaerk(db.getKortvaerk());

        return dto;
    }
}

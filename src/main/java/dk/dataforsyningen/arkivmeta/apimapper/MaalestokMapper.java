package dk.dataforsyningen.arkivmeta.apimapper;

import dk.dataforsyningen.arkivmeta.apimodel.MaalestokDto;
import dk.dataforsyningen.arkivmeta.datamodel.MaalestokDB;

public class MaalestokMapper
{
    public MaalestokDto maalestokToMaalestokDto(MaalestokDB db)
    {
        MaalestokDto dto = new MaalestokDto(db.getMaalestok());
        return dto;
    }
}

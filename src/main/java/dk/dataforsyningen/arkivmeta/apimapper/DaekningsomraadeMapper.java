package dk.dataforsyningen.arkivmeta.apimapper;

import dk.dataforsyningen.arkivmeta.apimodel.DaekningsomraadeDto;
import dk.dataforsyningen.arkivmeta.datamodel.DaekningsomraadeDB;
import org.springframework.stereotype.Component;

@Component
public class DaekningsomraadeMapper {
  public DaekningsomraadeDto daekningsomraadeToDaekningsomraadeDto(DaekningsomraadeDB db) {
    DaekningsomraadeDto dto = new DaekningsomraadeDto(db.getDaekningsomraade());

    return dto;
  }
}

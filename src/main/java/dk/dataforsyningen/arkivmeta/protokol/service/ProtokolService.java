package dk.dataforsyningen.arkivmeta.protokol.service;

import dk.dataforsyningen.arkivmeta.kort.apimodel.ArketypeDto;
import dk.dataforsyningen.arkivmeta.kort.apimodel.KortDto;
import dk.dataforsyningen.arkivmeta.kort.dao.IKortDao;
import dk.dataforsyningen.arkivmeta.protokol.apimodel.ProtokolDto;
import dk.dataforsyningen.arkivmeta.protokol.dao.IProtokolDao;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.locationtech.jts.geom.Geometry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProtokolService implements IProtokolService {
  private final IProtokolDao iProtokolDao;

  public ProtokolService(@Qualifier("protokolDao") IProtokolDao iProtokolDao) {
    this.iProtokolDao = iProtokolDao;
  }
/*
  @Cacheable("protokoller")
  public List<ProtokolDto> getAllProtokoller() {
    return iProtokolDao.getAllProtokoller();
  }
*/
  /**
   * Kort's id in the database consists of it's arketype and id. There can be given incorrect arketype and/or id, so
   * there is used Optional to take care if the result is null.
   * Because Gravitee does not set X-Forwarded-* headers it's needed to manually set them.
   * Need to replace the url return from concatXFHeaders and add /iiif/3 to the url because of Cantaloupe
   *
   * @param arketype
   * @param id
   * @return kortDto with the matching datamodel of the kort
   */



  @Override
  public ProtokolDto getProtokolById(String arketype, String id) {
    String searchId = arketype + "/" + id;

    Optional<ProtokolDto> returnedProtokol = iProtokolDao.getProtokolById(searchId);

    return returnedProtokol.orElseThrow(
        () -> new NoSuchElementException("Ingen protokol matchede det givne id: " + searchId)
    );
  }

/*
  //@Override
  public List<ProtokolDto> getAllProtokoller(List<String> arketype, String daekningsomraade,
                                  String fritekstsoegning, Integer gaeldendefra,
                                  Integer gaeldendetil, Geometry area, String kortbladnummer,
                                  String kortvaerk, List<String> maalestok, String tegner,
                                  String titel, int limit, int offset, String sort,
                                  String direction) {
    return arkivmetaJdbi.withExtension(IProtokolDao.class,
            dao -> dao.getAllProtokoller(arketype, daekningsomraade, fritekstsoegning, gaeldendefra,
                    gaeldendetil, area, kortbladnummer, kortvaerk, maalestok, tegner, titel, limit, offset,
                    sort, direction));
  }


 */
}

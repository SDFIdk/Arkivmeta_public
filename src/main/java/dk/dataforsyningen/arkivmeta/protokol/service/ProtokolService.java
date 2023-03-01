package dk.dataforsyningen.arkivmeta.protokol.service;

import dk.dataforsyningen.arkivmeta.protokol.apimodel.ProtokolDto;
import dk.dataforsyningen.arkivmeta.protokol.dao.IProtokolDao;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProtokolService implements IProtokolService {
  private final IProtokolDao iProtokolDao;

  public ProtokolService(@Qualifier("protokolDao") IProtokolDao iProtokolDao) {
    this.iProtokolDao = iProtokolDao;
  }

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
}

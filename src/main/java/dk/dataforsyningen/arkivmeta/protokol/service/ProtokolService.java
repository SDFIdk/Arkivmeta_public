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

  @Override
  public ProtokolDto getProtokolById(String arketype, String id) {
    String searchId = arketype + "/" + id;

    Optional<ProtokolDto> returnedProtokol = iProtokolDao.getProtokolById(searchId);

    return returnedProtokol.orElseThrow(
        () -> new NoSuchElementException("Ingen protokol matchede det givne id: " + searchId)
    );
  }
}

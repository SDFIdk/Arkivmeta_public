package dk.dataforsyningen.arkivmeta.protokol.dao;

import dk.dataforsyningen.arkivmeta.protokol.apimodel.ProtokolDto;
import java.util.Optional;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;

public class ProtokolDao implements IProtokolDao {
  private final Jdbi protokolJdbi;

  @Autowired
  public ProtokolDao(Jdbi protokolJdbi) {
    this.protokolJdbi = protokolJdbi;
  }

  @Override
  public Optional<ProtokolDto> getProtokolById(String id) {
    return protokolJdbi.withExtension(IProtokolDao.class, dao -> dao.getProtokolById(id));
  }
}

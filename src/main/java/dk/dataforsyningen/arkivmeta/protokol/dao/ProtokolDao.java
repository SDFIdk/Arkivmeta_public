package dk.dataforsyningen.arkivmeta.protokol.dao;

import dk.dataforsyningen.arkivmeta.protokol.apimodel.ProtokolDto;
import java.util.Optional;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ProtokolDao implements IProtokolDao {
  @Qualifier("arkivmetaJdbi")
  private final Jdbi arkivmetaJdbi;

  @Autowired
  public ProtokolDao(@Qualifier("arkivmetaJdbi") Jdbi arkivmetaJdbi) {
    this.arkivmetaJdbi = arkivmetaJdbi;
  }

  @Override
  public Optional<ProtokolDto> getProtokolById(String id) {
    return arkivmetaJdbi.withExtension(IProtokolDao.class, dao -> dao.getProtokolById(id));
  }
}

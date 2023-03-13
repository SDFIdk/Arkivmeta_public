package dk.dataforsyningen.arkivmeta.protokol.dao;

import dk.dataforsyningen.arkivmeta.protokol.apimodel.ProtokolDto;
import org.jdbi.v3.core.Jdbi;
import org.locationtech.jts.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

  // @Override
  public List<ProtokolDto> getAllProtokoller(
          String herredsnavn, int herredsnummer, String sognenavn ,int sogneid,
          Geometry area,
          List<String> dokumentsamling,
          int limit,
          int offset,
          String sort,
          String direction) {
    return arkivmetaJdbi.withExtension(IProtokolDao.class,
            dao -> dao.getAllProtokoller(herredsnavn, herredsnummer, sognenavn, sogneid, area,
                    dokumentsamling, limit, offset, sort, direction));
  }

  // @Override
  public Long getCount(
          String herredsnavn, int herredsnummer, String sognenavn, int sogneid,
          Geometry area,
          List<String> dokumentsamling,
          int limit,
          int offset,
          String sort,
          String direction) {
    return arkivmetaJdbi.withExtension(IProtokolDao.class,
            dao -> dao.getCount(herredsnavn, herredsnummer, sognenavn, sogneid, area,
                    dokumentsamling, limit, offset, sort, direction));
  }

}

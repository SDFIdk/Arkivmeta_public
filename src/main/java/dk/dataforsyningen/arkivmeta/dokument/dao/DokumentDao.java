package dk.dataforsyningen.arkivmeta.dokument.dao;

import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentDto;
import org.jdbi.v3.core.Jdbi;
import org.locationtech.jts.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DokumentDao implements IDokumentDao {
  @Qualifier("arkivmetaJdbi")
  private final Jdbi arkivmetaJdbi;

  @Autowired
  public DokumentDao(@Qualifier("arkivmetaJdbi") Jdbi arkivmetaJdbi) {
    this.arkivmetaJdbi = arkivmetaJdbi;
  }

  @Override
  public Optional<DokumentDto> getDokumentById(String id) {
    return arkivmetaJdbi.withExtension(IDokumentDao.class, dao -> dao.getDokumentById(id));
  }

  // @Override
  public List<DokumentDto> getAllDokumenter(
          String herredsnavn, Integer herredsnummer, String sognenavn, Integer sogneid,
          Geometry area,
          List<String> dokumentsamling,
          int limit,
          int offset,
          String sort,
          String direction) {
    return arkivmetaJdbi.withExtension(IDokumentDao.class,
            dao -> dao.getAllDokumenter(herredsnavn, herredsnummer, sognenavn, sogneid, area,
                    dokumentsamling, limit, offset, sort, direction));
  }

  // @Override
  public Long getCount(
          String herredsnavn, Integer herredsnummer, String sognenavn, Integer sogneid,
          Geometry area,
          List<String> dokumentsamling,
          int limit,
          int offset,
          String sort,
          String direction) {
    return arkivmetaJdbi.withExtension(IDokumentDao.class,
            dao -> dao.getCount(herredsnavn, herredsnummer, sognenavn, sogneid, area,
                    dokumentsamling, limit, offset, sort, direction));
  }

}

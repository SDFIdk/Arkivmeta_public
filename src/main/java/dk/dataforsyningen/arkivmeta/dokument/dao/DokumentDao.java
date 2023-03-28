package dk.dataforsyningen.arkivmeta.dokument.dao;

import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentDto;
import java.util.List;
import java.util.Optional;
import org.jdbi.v3.core.Jdbi;
import org.locationtech.jts.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

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

  @Override
  public List<DokumentDto> getAllDokumenter(
      List<String> dokumentsamling,
      Geometry area,
      String herredsnavn,
      Integer herredsnummer,
      Integer sogneid,
      String sognenavn,
      String direction,
      String sort,
      int limit,
      int offset
  ) {
    return arkivmetaJdbi.withExtension(IDokumentDao.class,
        dao -> dao.getAllDokumenter(dokumentsamling, area, herredsnavn, herredsnummer, sogneid,
            sognenavn, direction, sort, limit, offset));
  }

  @Override
  public Long getCount(
      List<String> dokumentsamling,
      Geometry area,
      String herredsnavn, 
      Integer herredsnummer,
      Integer sogneid,
      String sognenavn,
      int limit,
      int offset) {
    return arkivmetaJdbi.withExtension(IDokumentDao.class,
        dao -> dao.getCount(dokumentsamling, area, herredsnavn, herredsnummer, sogneid, sognenavn,
            limit, offset));
  }

}

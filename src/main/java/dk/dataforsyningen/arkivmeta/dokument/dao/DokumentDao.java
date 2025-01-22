package dk.dataforsyningen.arkivmeta.dokument.dao;

import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentDto;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
  public List<String> getDokumentSamling() {
    return arkivmetaJdbi.withExtension(IDokumentDao.class, dao -> dao.getDokumentSamling());
  }

  @Override
  public List<String> getHerredsnavn() {
    return arkivmetaJdbi.withExtension(IDokumentDao.class, dao -> dao.getHerredsnavn());
  }

  @Override
  public List<String> getSognenavn() {
    return arkivmetaJdbi.withExtension(IDokumentDao.class, dao -> dao.getSognenavn());
  }

  @Override
  public Optional<DokumentDto> getDokumentById(UUID id) {
    return arkivmetaJdbi.withExtension(IDokumentDao.class, dao -> dao.getDokumentById(id));
  }

  @Override
  public List<DokumentDto> getAllDokumenter(
      Geometry area,
      String direction,
      String fritekstsoegning,
      String herredsnavn,
      Integer herredsnummer,
      List<String> kortgruppe,
      int limit,
      int offset,
      Integer sogneid,
      String sognenavn,
      String sort,
      String titel
      ) {
    return arkivmetaJdbi.withExtension(IDokumentDao.class,
        dao -> dao.getAllDokumenter(area, direction, fritekstsoegning, herredsnavn, herredsnummer, kortgruppe, limit, offset, sogneid,
            sognenavn, sort, titel));
  }

  @Override
  public Long getCount(
      Geometry area,
      String fritekstsoegning,
      String herredsnavn,
      Integer herredsnummer,
      List<String> kortgruppe,
      Integer sogneid,
      String sognenavn,
      String titel) {
    return arkivmetaJdbi.withExtension(IDokumentDao.class,
        dao -> dao.getCount(area, fritekstsoegning, herredsnavn, herredsnummer, kortgruppe, sogneid, sognenavn,
            titel));
  }
}

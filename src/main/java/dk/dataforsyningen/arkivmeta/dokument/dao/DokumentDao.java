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
  public List<String> getDokumentSamling() {
    return arkivmetaJdbi.withExtension(IDokumentDao.class, dao -> dao.getDokumentSamling());
  }

  @Override
  public List<String> getSognenavn() {
    return arkivmetaJdbi.withExtension(IDokumentDao.class, dao -> dao.getSognenavn());
  }

  @Override
  public Optional<DokumentDto> getDokumentById(String id) {
    return arkivmetaJdbi.withExtension(IDokumentDao.class, dao -> dao.getDokumentById(id));
  }

  @Override
  public List<DokumentDto> getAllDokumenter(
      List<String> dokumentsamling,
      String fritekstsoegning,
      Geometry area,
      String herredsnavn,
      Integer herredsnummer,
      Integer sogneid,
      String sognenavn,
      String titel,
      String direction,
      String sort,
      int limit,
      int offset) {
    return arkivmetaJdbi.withExtension(IDokumentDao.class,
        dao -> dao.getAllDokumenter(dokumentsamling, fritekstsoegning, area, herredsnavn, herredsnummer, sogneid,
            sognenavn, titel, direction, sort, limit, offset));
  }

  @Override
  public Long getCount(
      List<String> dokumentsamling,
      String fritekstsoegning,
      Geometry area,
      String herredsnavn,
      Integer herredsnummer,
      Integer sogneid,
      String sognenavn,
      String titel,
      int limit,
      int offset) {
    return arkivmetaJdbi.withExtension(IDokumentDao.class,
        dao -> dao.getCount(dokumentsamling, fritekstsoegning, area, herredsnavn, herredsnummer, sogneid, sognenavn,
            titel, limit, offset));
  }
}

package dk.dataforsyningen.arkivmeta.dokument.service;

import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentDto;
import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentParam;
import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentResult;
import dk.dataforsyningen.arkivmeta.dokument.dao.IDokumentDao;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DokumentService implements IDokumentService {
  private final IDokumentDao iDokumentDao;

  public DokumentService(@Qualifier("dokumentDao") IDokumentDao iDokumentDao) {
    this.iDokumentDao = iDokumentDao;
  }

  @Cacheable(cacheNames = "dokumentsamling")
  @Override
  public List<String> getDokumentSamling() {
    return iDokumentDao.getDokumentSamling();
  }

  @Cacheable(cacheNames = "herredsnavn")
  @Override
  public List<String> getHerredsnavn() {
    return iDokumentDao.getHerredsnavn();
  }

  @Cacheable(cacheNames = "sognenavn")
  @Override
  public List<String> getSognenavn() {
    return iDokumentDao.getSognenavn();
  }

  /**
   * Dokument's UUID.
   * There can be given incorrect id, so there is used Optional to take care if the result is null.
   * Because Gravitee does not set X-Forwarded-* headers it's needed to manually set them.
   * Need to replace the url return from concatXFHeaders and add /iiif/3 to the url because of Cantaloupe
   *
   * @param id
   * @return DokumentDto with the matching datamodel of the dokument
   */
  @Override
  public DokumentDto getDokumentById(UUID id) {

    Optional<DokumentDto> returnedDokument = iDokumentDao.getDokumentById(id);

    return returnedDokument.orElseThrow(
        () -> new NoSuchElementException("Ingen dokument matchede det givne id: " + id)
    );
  }

  /**
   * @param dokumentParam
   * @return the object with a list of kort that matched users requirements (up to 1000) and a total of how many
   * match result there was in total
   */
  @Cacheable(cacheNames = "dokument", key = "#dokumentParam")
  @Override
  public DokumentResult getDokumentResult(DokumentParam dokumentParam) {
    Geometry area = new GeometryFactory().createGeometry(null);
    if (StringUtils.isNotBlank(dokumentParam.getGeometri())) {
      try {
        area = new WKTReader().read(dokumentParam.getGeometri());
      } catch (ParseException parseException) {
        throw new IllegalArgumentException(
            "Could not read geometry. Should be a WKT with SRS = EPSG:4326", parseException);
      }
    }

    List<DokumentDto> dokumentDtoList = iDokumentDao.getAllDokumenter(
        area,
        dokumentParam.getDirection(),
        dokumentParam.getFritekstsoegning(),
        dokumentParam.getHerredsnavn(),
        dokumentParam.getHerredsnummer(),
        dokumentParam.getDokumentsamling(),
        dokumentParam.getLimit(),
        dokumentParam.getOffset(),
        dokumentParam.getSogneid(),
        dokumentParam.getSognenavn(),
        dokumentParam.getSort(),
        dokumentParam.getTitel());

    long count;

    if (dokumentDtoList.size() >= dokumentParam.getLimit()) {
      count = iDokumentDao.getCount(
          area,
          dokumentParam.getFritekstsoegning(),
          dokumentParam.getHerredsnavn(),
          dokumentParam.getHerredsnummer(),
          dokumentParam.getDokumentsamling(),
          dokumentParam.getSogneid(),
          dokumentParam.getSognenavn(),
          dokumentParam.getTitel());
    } else {
      count = dokumentDtoList.size();
    }

    DokumentResult dokumentResult = new DokumentResult(count, dokumentDtoList);
    return dokumentResult;
  }
}

package dk.dataforsyningen.arkivmeta.dokument.service;

import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentDto;
import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentParam;
import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentResult;
import dk.dataforsyningen.arkivmeta.dokument.dao.IDokumentDao;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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

  /**
   * Protokol's id in the database consists of it's arketype and id. There can be given incorrect arketype and/or id, so
   * there is used Optional to take care if the result is null.
   * Because Gravitee does not set X-Forwarded-* headers it's needed to manually set them.
   * Need to replace the url return from concatXFHeaders and add /iiif/3 to the url because of Cantaloupe
   *
   * @param arketype
   * @param id
   * @return DokumentDto with the matching datamodel of the dokument
   */
  @Override
  public DokumentDto getDokumentById(String arketype, String id) {
    String searchId = arketype + "/" + id;

    Optional<DokumentDto> returnedProtokol = iDokumentDao.getDokumentById(searchId);

    return returnedProtokol.orElseThrow(
        () -> new NoSuchElementException("Ingen dokument matchede det givne id: " + searchId)
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
        dokumentParam.getDokumentsamling(),
        dokumentParam.getFritekstsoegning(),
        area,
        dokumentParam.getHerredsnavn(),
        dokumentParam.getHerredsnummer(),
        dokumentParam.getSogneid(),
        dokumentParam.getSognenavn(),
        dokumentParam.getTitel(),
        dokumentParam.getDirection(),
        dokumentParam.getSort(),
        dokumentParam.getLimit(),
        dokumentParam.getOffset());

    long count;

    if (dokumentDtoList.size() >= dokumentParam.getLimit()) {
      count = iDokumentDao.getCount(
          dokumentParam.getDokumentsamling(),
          dokumentParam.getFritekstsoegning(),
          area,
          dokumentParam.getHerredsnavn(),
          dokumentParam.getHerredsnummer(),
          dokumentParam.getSogneid(),
          dokumentParam.getSognenavn(),
          dokumentParam.getTitel(),
          dokumentParam.getLimit(),
          dokumentParam.getOffset());
    } else {
      count = dokumentDtoList.size();
    }

    DokumentResult dokumentResult = new DokumentResult(count, dokumentDtoList);
    return dokumentResult;
  }
}

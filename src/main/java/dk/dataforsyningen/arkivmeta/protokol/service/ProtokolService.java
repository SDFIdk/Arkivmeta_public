package dk.dataforsyningen.arkivmeta.protokol.service;

import dk.dataforsyningen.arkivmeta.protokol.apimodel.ProtokolDto;
import dk.dataforsyningen.arkivmeta.protokol.apimodel.ProtokolParam;
import dk.dataforsyningen.arkivmeta.protokol.apimodel.ProtokolResult;
import dk.dataforsyningen.arkivmeta.protokol.dao.IProtokolDao;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProtokolService implements IProtokolService {
  private final IProtokolDao iProtokolDao;

  public ProtokolService(@Qualifier("protokolDao") IProtokolDao iProtokolDao) {
    this.iProtokolDao = iProtokolDao;
  }

  /**
   * Protokol's id in the database consists of it's arketype and id. There can be given incorrect arketype and/or id, so
   * there is used Optional to take care if the result is null.
   * Because Gravitee does not set X-Forwarded-* headers it's needed to manually set them.
   * Need to replace the url return from concatXFHeaders and add /iiif/3 to the url because of Cantaloupe
   *
   * @param arketype
   * @param id
   * @return ProtokolDto with the matching datamodel of the protokol
   */


  @Override
  public ProtokolDto getProtokolById(String arketype, String id) {
    String searchId = arketype + "/" + id;

    Optional<ProtokolDto> returnedProtokol = iProtokolDao.getProtokolById(searchId);

    return returnedProtokol.orElseThrow(
            () -> new NoSuchElementException("Ingen protokol matchede det givne id: " + searchId)
    );
  }

  /**
   *
   * @param protokolParam
   * @return the object with a list of kort that matched users requirements (up to 1000) and a total of how many
   * match result there was in total
   */
  @Cacheable(cacheNames = "protokol", key = "#protokolParam")
  public ProtokolResult getProtokolResult(ProtokolParam protokolParam) {
    Geometry area = new GeometryFactory().createGeometry(null);
    if (StringUtils.isNotBlank(protokolParam.getGeometri())) {
      try {
        area = new WKTReader().read(protokolParam.getGeometri());
      } catch (ParseException parseException) {
        throw new IllegalArgumentException(
                "Could not read geometry. Should be a WKT with SRS = EPSG:4326", parseException);
      }
    }

    List<ProtokolDto> protokolDtoList = iProtokolDao.getAllProtokoller(
            protokolParam.getHerredsnavn(),
            protokolParam.getHerredsnummer(),
            protokolParam.getSognenavn(),
            protokolParam.getSogneid(),
            area,
            protokolParam.getDokumentsamling(),
            protokolParam.getLimit(),
            protokolParam.getOffset(),
            protokolParam.getSort(),
            protokolParam.getDirection());


    long count;

    if (protokolDtoList.size() >= protokolParam.getLimit()) {
      count = iProtokolDao.getCount( protokolParam.getHerredsnavn(),
              protokolParam.getHerredsnummer(),
              protokolParam.getSognenavn(),
              protokolParam.getSogneid(),
              area,
              protokolParam.getDokumentsamling(),
              protokolParam.getLimit(),
              protokolParam.getOffset(),
              protokolParam.getSort(),
              protokolParam.getDirection());
    } else {
      count = protokolDtoList.size();
    }

    ProtokolResult protokolResult = new ProtokolResult(count, protokolDtoList);
    return protokolResult;
  }

}

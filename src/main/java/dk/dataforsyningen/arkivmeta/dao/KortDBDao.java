package dk.dataforsyningen.arkivmeta.dao;

import dk.dataforsyningen.arkivmeta.apimodel.KortDto;
import java.util.List;
import java.util.Optional;
import org.jdbi.v3.core.Jdbi;
import org.locationtech.jts.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class KortDBDao implements IKortDBDao {

  @Qualifier("arkivmetaJdbi")
  private final Jdbi arkivmetaJdbi;

  @Autowired
  public KortDBDao(@Qualifier("arkivmetaJdbi") Jdbi arkivmetaJdbi) {
    this.arkivmetaJdbi = arkivmetaJdbi;
  }

  @Override
  public Optional<KortDto> getKortById(String id) {
    return arkivmetaJdbi.withExtension(IKortDBDao.class, dao -> dao.getKortById(id));
  }

  @Override
  public List<KortDto> getAllKort(List<String> arketype, String daekningsomraade,
                                  String fritekstsoegning, Integer gaeldendefra,
                                  Integer gaeldendetil, Geometry area, String kortbladnummer,
                                  String kortvaerk, List<String> maalestok, String tegner,
                                  String titel, int limit, int offset, String sort,
                                  String direction) {
    return arkivmetaJdbi.withExtension(IKortDBDao.class,
        dao -> dao.getAllKort(arketype, daekningsomraade, fritekstsoegning, gaeldendefra,
            gaeldendetil, area, kortbladnummer, kortvaerk, maalestok, tegner, titel, limit, offset,
            sort, direction));
  }

  @Override
  public Long getCount(List<String> arketype, String daekningsomraade,
                       String fritekstsoegning, Integer gaeldendefra,
                       Integer gaeldendetil, Geometry area, String kortbladnummer,
                       String kortvaerk, List<String> maalestok, String tegner,
                       String titel, int limit, int offset) {
    return arkivmetaJdbi.withExtension(IKortDBDao.class,
        dao -> dao.getCount(arketype, daekningsomraade, fritekstsoegning, gaeldendefra,
            gaeldendetil, area, kortbladnummer, kortvaerk, maalestok, tegner, titel, limit,
            offset));
  }
}

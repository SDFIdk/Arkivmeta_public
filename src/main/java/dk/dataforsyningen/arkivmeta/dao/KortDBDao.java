package dk.dataforsyningen.arkivmeta.dao;

import dk.dataforsyningen.arkivmeta.apimodel.KortDto;
import dk.dataforsyningen.arkivmeta.apimodel.KortParam;
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

//  @Override
//  public List<KortDto> getAllKort(List<String> arketype, List<String> daekningsomraade,
//                                  String direction, String fritekstsoegning, int gaeldendefra,
//                                  int gaeldendetil, Geometry area, String kortbladnummer,
//                                  List<String> kortvaerk, List<String> maalestok, int offset,
//                                  int limit, String sort, String tegner, String titel) {
//    return arkivmetaJdbi.withExtension(IKortDBDao.class,
//        dao -> dao.getAllKort(
//            arketype, daekningsomraade, direction, fritekstsoegning, gaeldendefra, gaeldendetil,
//            area, kortbladnummer, kortvaerk, maalestok, offset, limit, sort, tegner, titel));
//  }

  @Override
  public List<KortDto> getAllKort(List<String> arketype, String daekningsomraade,
                                  String fritekstsoegning, Integer gaeldendefra,
                                  Integer gaeldendetil, Geometry area, String kortbladnummer,
                                  String kortvaerk, List<String> maalestok, int limit, int offset, String sort, String direction) {
    return arkivmetaJdbi.withExtension(IKortDBDao.class,
        dao -> dao.getAllKort(arketype, daekningsomraade, fritekstsoegning, gaeldendefra,
            gaeldendetil, area, kortbladnummer, kortvaerk, maalestok, limit, offset, sort, direction));
  }

  @Override
  public Long getCount(KortParam kortParam, Geometry area) {
    return arkivmetaJdbi.withExtension(IKortDBDao.class, dao -> dao.getCount(kortParam, area));
  }
}

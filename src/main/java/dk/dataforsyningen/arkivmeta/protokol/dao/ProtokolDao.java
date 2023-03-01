package dk.dataforsyningen.arkivmeta.protokol.dao;

import dk.dataforsyningen.arkivmeta.protokol.dao.IProtokolDao;
import dk.dataforsyningen.arkivmeta.protokol.apimodel.ProtokolDto;

import java.util.List;
import java.util.Optional;
import org.jdbi.v3.core.Jdbi;
import org.locationtech.jts.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

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
/*
  // @Override
  public List<ProtokolDto> getAllProtokoller(List<String> arketype,
                                  String fritekstsoegning, Integer gaeldendefra,
                                  Integer gaeldendetil, Geometry area,
                                  String titel, int limit, int offset, String sort,
                                  String direction) {
    return arkivmetaJdbi.withExtension(IProtokolDao.class,
            dao -> dao.getAllProtokoller(arketype, daekningsomraade, fritekstsoegning, gaeldendefra,
                    gaeldendetil, area, kortbladnummer, kortvaerk, maalestok, tegner, titel, limit, offset,
                    sort, direction));
  }

  // @Override
  public Long getCount(List<String> arketype, String daekningsomraade,
                       String fritekstsoegning, Integer gaeldendefra,
                       Integer gaeldendetil, Geometry area, String kortbladnummer,
                       String kortvaerk, List<String> maalestok, String tegner,
                       String titel, int limit, int offset) {
    return arkivmetaJdbi.withExtension(IKortDao.class,
            dao -> dao.getCount(arketype, daekningsomraade, fritekstsoegning, gaeldendefra,
                    gaeldendetil, area, kortbladnummer, kortvaerk, maalestok, tegner, titel, limit,
                    offset));
  }
*/

}

package dk.dataforsyningen.arkivmeta.dao;

import dk.dataforsyningen.arkivmeta.apimodel.MaalestokDto;
import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class MaalestokDao implements IMaalestokDao {

  @Qualifier("arkivmetaJdbi")
  private final Jdbi arkivmetaJdbi;

  @Autowired
  public MaalestokDao(@Qualifier("arkivmetaJdbi") Jdbi arkivmetaJdbi) {
    this.arkivmetaJdbi = arkivmetaJdbi;
  }

  @Override
  public List<MaalestokDto> getMaalestok(String maalestok) {
    return arkivmetaJdbi.withExtension(IMaalestokDao.class, dao -> dao.getMaalestok(maalestok));
  }
}

package dk.dataforsyningen.arkivmeta.dao;

import dk.dataforsyningen.arkivmeta.apimodel.KortvaerkDto;
import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class KortvaerkerDao implements IKortvaerkerDao {

  @Qualifier("arkivmetaJdbi")
  private final Jdbi arkivmetaJdbi;

  @Autowired
  public KortvaerkerDao(@Qualifier("arkivmetaJdbi") Jdbi arkivmetaJdbi) {
    this.arkivmetaJdbi = arkivmetaJdbi;
  }

  @Override
  public List<KortvaerkDto> getArketypeAndKortvaerk(String arketype, String kortvaerk) {
    return arkivmetaJdbi.withExtension(IKortvaerkerDao.class,
        dao -> dao.getArketypeAndKortvaerk(arketype, kortvaerk));
  }

  @Override
  public List<KortvaerkDto> getKortvaerk(String kortvaerk) {
    return arkivmetaJdbi.withExtension(IKortvaerkerDao.class, dao -> dao.getKortvaerk(kortvaerk));
  }
}

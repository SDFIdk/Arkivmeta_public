package dk.dataforsyningen.arkivmeta.kort.dao;

import dk.dataforsyningen.arkivmeta.kort.apimodel.DaekningsomraadeDto;
import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DaekningsomraadeDao implements IDaekningsomraadeDao {
  @Qualifier("arkivmetaJdbi")
  private final Jdbi arkivmetaJdbi;

  @Autowired
  public DaekningsomraadeDao(@Qualifier("arkivmetaJdbi") Jdbi arkivmetaJdbi) {
    this.arkivmetaJdbi = arkivmetaJdbi;
  }

  @Override
  public List<DaekningsomraadeDto> getDaekningsomraade(String daekningsomraade) {
    return arkivmetaJdbi.withExtension(IDaekningsomraadeDao.class,
        dao -> dao.getDaekningsomraade(daekningsomraade));
  }
}

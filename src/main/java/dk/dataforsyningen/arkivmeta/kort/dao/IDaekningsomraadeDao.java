package dk.dataforsyningen.arkivmeta.kort.dao;

import dk.dataforsyningen.arkivmeta.kort.apimapper.DaekningsomraadeMapper;
import dk.dataforsyningen.arkivmeta.kort.apimodel.DaekningsomraadeDto;
import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface IDaekningsomraadeDao {

  /**
   * @RegisterRowMapper use the registered mapper to map the select columns from the database to GetOrderDto
   * https://jdbi.org/#_registerrowmapper
   */
  @SqlQuery("""
      SELECT daekningomraade
      FROM arkivmeta_latest.a_daekningomraade
      WHERE (:daekningsomraade IS NULL OR daekningomraade ilike '%' || :daekningsomraade || '%')
      """)
  @RegisterRowMapper(DaekningsomraadeMapper.class)
  List<DaekningsomraadeDto> getDaekningsomraade(String daekningsomraade);
}


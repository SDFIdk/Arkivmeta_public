package dk.dataforsyningen.arkivmeta.kort.dao;

import dk.dataforsyningen.arkivmeta.kort.apimapper.MaalestokMapper;
import dk.dataforsyningen.arkivmeta.kort.apimodel.MaalestokDto;
import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface IMaalestokDao {

  /**
   * @RegisterRowMapper use the registered mapper to map the select columns from the database to GetOrderDto
   * https://jdbi.org/#_registerrowmapper
   * https://jdbi.org/#_getgeneratedkeys
   * https://jdbi.org/#_timestamped
   */
  @SqlQuery("""
      SELECT maalestok
      FROM arkivmeta_latest.a_maalestok
      WHERE (:maalestok IS NULL OR maalestok ilike '%' || :maalestok || '%')
      """)
  @RegisterRowMapper(MaalestokMapper.class)
  List<MaalestokDto> getMaalestok(String maalestok);
}

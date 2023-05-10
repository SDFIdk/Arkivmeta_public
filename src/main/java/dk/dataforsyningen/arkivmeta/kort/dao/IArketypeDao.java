package dk.dataforsyningen.arkivmeta.kort.dao;

import dk.dataforsyningen.arkivmeta.kort.apimapper.ArketypeMapper;
import dk.dataforsyningen.arkivmeta.kort.apimodel.ArketypeDto;
import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface IArketypeDao {
  /**
   * @RegisterRowMapper use the registered mapper to map the select columns from the database to ArketypeDto
   * https://jdbi.org/#_registerrowmapper
   */
  @SqlQuery("""
      SELECT arketype, arkenavn, kortvaerk
      FROM arkivmeta.a_rel_arketype_agg_kortvaerker
      """)
  @RegisterRowMapper(ArketypeMapper.class)
  List<ArketypeDto> getAllArketyper();
}

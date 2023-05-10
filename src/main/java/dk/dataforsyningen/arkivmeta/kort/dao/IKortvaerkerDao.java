package dk.dataforsyningen.arkivmeta.kort.dao;

import dk.dataforsyningen.arkivmeta.kort.apimapper.KortvaerkMapper;
import dk.dataforsyningen.arkivmeta.kort.apimodel.KortvaerkDto;
import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface IKortvaerkerDao {

  /**
   * @RegisterRowMapper use the registered mapper to map the select columns from the database to KortvaerkDto
   * https://jdbi.org/#_registerrowmapper
   */
  @SqlQuery("""
      SELECT arketype, arkenavn, kortvaerk
      FROM arkivmeta_latest.a_rel_arketype
      WHERE (:arketype IS NULL OR arketype ilike '%' || :arketype || '%')
      AND (:kortvaerk IS NULL OR kortvaerk ilike '%' || :kortvaerk || '%')
      """)
  @RegisterRowMapper(KortvaerkMapper.class)
  List<KortvaerkDto> getArketypeAndKortvaerk(String arketype, String kortvaerk);

  /**
   * @RegisterRowMapper use the registered mapper to map the select columns from the database to KortvaerkDto
   * https://jdbi.org/#_registerrowmapper
   */
  @SqlQuery("""
      SELECT arketype, arkenavn, kortvaerk
      FROM arkivmeta_latest.a_rel_arketype
      WHERE (:kortvaerk IS NULL OR kortvaerk ilike '%' || :kortvaerk || '%')
      """)
  @RegisterRowMapper(KortvaerkMapper.class)
  List<KortvaerkDto> getKortvaerk(String kortvaerk);
}

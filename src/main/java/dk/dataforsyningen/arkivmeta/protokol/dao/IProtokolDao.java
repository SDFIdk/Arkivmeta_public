package dk.dataforsyningen.arkivmeta.protokol.dao;

import dk.dataforsyningen.arkivmeta.configuration.LogSqlFactory;
import dk.dataforsyningen.arkivmeta.protokol.apimapper.ProtokolDtoMapper;
import dk.dataforsyningen.arkivmeta.protokol.apimodel.ProtokolDto;
import java.util.Optional;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

@LogSqlFactory
public interface IProtokolDao {

  /**
   * @RegisterRowMapper use the registered mapper to map the select columns from the database to GetOrderDto
   * https://jdbi.org/#_registerrowmapper
   * https://jdbi.org/#_getgeneratedkeys
   * https://jdbi.org/#_timestamped
   */
  @SqlQuery("""
      SELECT
          *
      FROM
          arkivmeta.protokoller.protokoller p
      WHERE
          p.id = :id
  """)
  @RegisterRowMapper(ProtokolDtoMapper.class)
  Optional<ProtokolDto> getProtokolById(@Bind("id") String id);

}

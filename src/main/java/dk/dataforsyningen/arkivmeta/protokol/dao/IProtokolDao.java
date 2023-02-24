package dk.dataforsyningen.arkivmeta.protokol.dao;

import dk.dataforsyningen.arkivmeta.protokol.apimapper.ProtokolDtoMapper;
import dk.dataforsyningen.arkivmeta.protokol.apimodel.ProtokolDto;
import java.util.Optional;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface IProtokolDao {
  @SqlQuery("""
      SELECT
          *
      FROM
          arkivmeta.protokoller
      WHERE
          id = :id
  """)
  @RegisterRowMapper(ProtokolDtoMapper.class)
  Optional<ProtokolDto> getProtokolById(@Bind("id") String id);

}

package dk.dataforsyningen.arkivmeta.protokol.apimapper;

import dk.dataforsyningen.arkivmeta.protokol.apimodel.ProtokolDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class ProtokolDtoMapper implements RowMapper<ProtokolDto> {

  @Override
  public ProtokolDto map(ResultSet rs, StatementContext ctx) throws SQLException {
    return null;
  }
}

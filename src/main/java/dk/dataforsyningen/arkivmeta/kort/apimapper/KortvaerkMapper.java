package dk.dataforsyningen.arkivmeta.kort.apimapper;

import dk.dataforsyningen.arkivmeta.kort.apimodel.KortvaerkDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class KortvaerkMapper implements RowMapper<KortvaerkDto> {

  public KortvaerkDto map(ResultSet rs, StatementContext ctx) throws SQLException {
    KortvaerkDto dto = new KortvaerkDto();
    dto.setArkenavn(rs.getString("arkenavn"));
    dto.setArketype(rs.getString("arketype"));
    dto.setKortvaerk(rs.getString("kortvaerk"));

    return dto;
  }
}

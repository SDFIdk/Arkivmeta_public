package dk.dataforsyningen.arkivmeta.apimapper;

import dk.dataforsyningen.arkivmeta.apimodel.KortvaerkDto;
import dk.dataforsyningen.arkivmeta.datamodel.KortvaerkDB;
import dk.dataforsyningen.arkivmeta.enums.Arketype;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class KortvaerkMapper implements RowMapper<KortvaerkDto> {

  public KortvaerkDto map(ResultSet rs, StatementContext ctx) throws SQLException {
    KortvaerkDto dto = new KortvaerkDto();
    dto.setArkenavn(rs.getString("arkenavn"));
    dto.setArketype(Arketype.valueOf(rs.getString("arketype")));
    dto.setKortvaerk(rs.getString("kortvaerk"));

    return dto;
  }
}

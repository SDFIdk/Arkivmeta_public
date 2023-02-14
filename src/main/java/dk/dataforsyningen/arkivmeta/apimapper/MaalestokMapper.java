package dk.dataforsyningen.arkivmeta.apimapper;

import dk.dataforsyningen.arkivmeta.apimodel.MaalestokDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class MaalestokMapper implements RowMapper<MaalestokDto> {

  public MaalestokDto map(ResultSet rs, StatementContext ctx) throws SQLException {
    MaalestokDto dto = new MaalestokDto();
    dto.setMaalestok(rs.getString("maalestok"));

    return dto;
  }
}
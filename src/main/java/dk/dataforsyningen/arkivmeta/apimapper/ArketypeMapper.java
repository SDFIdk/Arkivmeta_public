package dk.dataforsyningen.arkivmeta.apimapper;

import dk.dataforsyningen.arkivmeta.apimodel.ArketypeDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class ArketypeMapper implements RowMapper<ArketypeDto> {

  /**
   * The mapper must have a default constructor https://jdbi.org/#_registerrowmapper
   */
  public ArketypeMapper() {
  }

  @Override
  public ArketypeDto map(ResultSet rs, StatementContext ctx) throws SQLException {
    ArketypeDto dto = new ArketypeDto();
    dto.setArketype(rs.getString("arketype").toLowerCase());
    dto.setArkenavn(rs.getString("arkenavn"));

    MapperKortvaerk mapperKortvaerk = new MapperKortvaerk();
    dto.setKortvaerker(mapperKortvaerk.mapKortvaerk(rs.getString("kortvaerk")));
    return dto;
  }
}
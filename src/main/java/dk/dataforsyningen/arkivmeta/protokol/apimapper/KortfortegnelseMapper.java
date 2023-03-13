package dk.dataforsyningen.arkivmeta.protokol.apimapper;

import org.jdbi.v3.core.mapper.RowMapper;
import dk.dataforsyningen.arkivmeta.protokol.apimodel.KortfortegnelseDto;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KortfortegnelseMapper implements RowMapper<KortfortegnelseDto> {

    /**
     * The mapper must have a default constructor https://jdbi.org/#_registerrowmapper
     */
    public KortfortegnelseMapper() { }

    @Override
    public KortfortegnelseDto map(ResultSet rs, StatementContext ctx) throws SQLException {
        KortfortegnelseDto dto = new KortfortegnelseDto();

        dto.setDatatype(rs.getString("datatype"));
        dto.setFiltype(rs.getString("filtype"));

        return dto;
    }
}

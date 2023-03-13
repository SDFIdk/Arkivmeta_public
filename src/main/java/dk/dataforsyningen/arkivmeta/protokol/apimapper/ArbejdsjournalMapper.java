package dk.dataforsyningen.arkivmeta.protokol.apimapper;

import org.jdbi.v3.core.mapper.RowMapper;
import dk.dataforsyningen.arkivmeta.protokol.apimodel.ArbejdsjournalDto;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArbejdsjournalMapper implements RowMapper<ArbejdsjournalDto> {

    /**
     * The mapper must have a default constructor https://jdbi.org/#_registerrowmapper
     */
    public ArbejdsjournalMapper() { }

    @Override
    public ArbejdsjournalDto map(ResultSet rs, StatementContext ctx) throws SQLException {
        ArbejdsjournalDto dto = new ArbejdsjournalDto();
        dto.setBemaerkning(rs.getString("bemaerkning").toLowerCase());
        dto.setDatatype(rs.getString("datatype"));
        dto.setFiltype(rs.getString("filtype"));

        return dto;
    }
}

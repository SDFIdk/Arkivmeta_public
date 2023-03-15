package dk.dataforsyningen.arkivmeta.protokol.apimapper;

import dk.dataforsyningen.arkivmeta.mapperhelper.MapperGeometri;
import org.jdbi.v3.core.mapper.RowMapper;
import dk.dataforsyningen.arkivmeta.protokol.apimodel.SogneprotokolDto;
import org.jdbi.v3.core.statement.StatementContext;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKBReader;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SogneprotokolMapper implements RowMapper<SogneprotokolDto> {

    /**
     * The mapper must have a default constructor https://jdbi.org/#_registerrowmapper
     */
    public SogneprotokolMapper() { }

    @Override
    public SogneprotokolDto map(ResultSet rs, StatementContext ctx) throws SQLException {
        SogneprotokolDto dto = new SogneprotokolDto();
        MapperGeometri mapperGeometri = new MapperGeometri();

        dto.setAlternativtitel(rs.getString("alternativtitel"));
        if (rs.getString("geometri") != null) {
            // Geometri in database is the database geomtry, but JDBI does not have map/converter for
            // that datatype, so we fetch it as a String, convert it to datatype Geometry
            byte[] bytes = hexStringToByteArray(rs.getString("geometri"));

            Geometry geometry = (deserialize(bytes));

            // Then we take the geomtry and convert it to String as WKT formattet geomtry
            dto.setGeometri(mapperGeometri.mapGeometri(geometry));
        } else {
            dto.setGeometri(mapperGeometri.mapGeometri(null));
        }
        dto.setHerredsnavn(rs.getString("herredsnavn"));
        dto.setProtokoltype(rs.getString("protokoltype"));
        dto.setSognenavn(rs.getString("sognenavn"));
        dto.setSogneid(rs.getBigDecimal("sogneid"));

        return dto;
    }

    private static Geometry deserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        try {
            WKBReader reader = new WKBReader(new GeometryFactory());
            return reader.read(bytes);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] =
                    (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}

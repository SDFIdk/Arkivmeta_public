package dk.dataforsyningen.arkivmeta.dokument.apimapper;

import dk.dataforsyningen.arkivmeta.mapperhelper.MapperFiler;
import dk.dataforsyningen.arkivmeta.mapperhelper.MapperGeometri;
import dk.dataforsyningen.arkivmeta.dokument.apimodel.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKBReader;

public class DokumentDtoMapper implements RowMapper<DokumentDto> {

    /**
     * The mapper must have a default constructor https://jdbi.org/#_registerrowmapper
     */
    public DokumentDtoMapper() {
    }

    @Override
    public DokumentDto map(ResultSet rs, StatementContext ctx) throws SQLException {
        DokumentDto dto = new DokumentDto();
        MapperFiler mapperFiler = new MapperFiler();
        MapperGeometri mapperGeometri = new MapperGeometri();

        dto.setId(rs.getString("id"));
        dto.setArketype(rs.getString("arketype"));
        dto.setTitel(rs.getString("titel"));
        dto.setAlternativtitel(rs.getString("alternativtitel"));
        dto.setRegistreringfra(rs.getTimestamp("registreringfra").toLocalDateTime());
        if (rs.getTimestamp("registreringtil") != null) {
            dto.setRegistreringtil(rs.getTimestamp("registreringtil").toLocalDateTime());
        } else {
            dto.setRegistreringtil(null);
        }
        dto.setUniktdokumentnavn(rs.getString("uniktdokumentnavn"));
        dto.setStinavn(rs.getString("stinavn"));
        dto.setBemaerkning(rs.getString("bemaerkning"));
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
        dto.setOmraade(rs.getString("omraade"));
        dto.setFiler(mapperFiler.mapFiler(rs.getString("filer")));
        dto.setDatatype(rs.getString("datatype"));
        dto.setFiltype(rs.getString("filtype"));
        dto.setDokumentsamling(rs.getString("dokumentsamling"));
        dto.setHerredsnavn(rs.getString("herredsnavn"));
        dto.setHerredsnummer((Integer) rs.getObject("herredsnummer"));
        dto.setProtokoltype(rs.getString("protokoltype"));
        dto.setSogneid((Integer) rs.getObject("sogneid"));
        dto.setSognenavn(rs.getString("sognenavn"));

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

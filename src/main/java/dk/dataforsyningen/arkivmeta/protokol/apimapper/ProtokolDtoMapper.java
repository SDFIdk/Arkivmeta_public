package dk.dataforsyningen.arkivmeta.protokol.apimapper;

import dk.dataforsyningen.arkivmeta.enums.Dokumentsamling;
import dk.dataforsyningen.arkivmeta.protokol.apimapper.MapperDaekningsomraade;
import dk.dataforsyningen.arkivmeta.kort.apimodel.AeldretopografiskekortDto;
import dk.dataforsyningen.arkivmeta.kort.apimodel.KortDto;
import dk.dataforsyningen.arkivmeta.mapperhelper.MapperFiler;
import dk.dataforsyningen.arkivmeta.mapperhelper.MapperGeometri;
import dk.dataforsyningen.arkivmeta.protokol.apimodel.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKBReader;

public class ProtokolDtoMapper implements RowMapper<ProtokolDto> {

    /**
     * The mapper must have a default constructor https://jdbi.org/#_registerrowmapper
     */
    public ProtokolDtoMapper() {
    }

    @Override
    public ProtokolDto map(ResultSet rs, StatementContext ctx) throws SQLException {
        String arketype = rs.getString("arketype").toUpperCase();

        Dokumentsamling type = Dokumentsamling.valueOf(arketype);

        if (type == Dokumentsamling.ANDETDOKUMENT) {
            return mapAndetDokumentDto(rs, ctx);
        }
        if (type == Dokumentsamling.HARTKORNSEKSTRAKT) {
            return mapHartkornsEkstraktDto(rs, ctx);
        }
        if (type == Dokumentsamling.SOGNEPROTOKOL) {
            return mapSogneprotokolDto(rs, ctx);
        }
        throw new IllegalStateException("Could not resolve mapping strategy for object");
    }

    public <T extends ProtokolDto> T mapProtokolDto(ResultSet rs, StatementContext ctx, T dto)
            throws SQLException {
        MapperFiler mapperFiler = new MapperFiler();
        MapperGeometri mapperGeometri = new MapperGeometri();
        dto.setArketype(rs.getString("arketype"));

        dto.setId(rs.getString("id"));
        dto.setArketype(rs.getString("arketype"));
        dto.setTitel(rs.getString("titel"));
        dto.setRegistreringfra(rs.getTimestamp("registreringfra").toLocalDateTime());
        if (rs.getTimestamp("registreringtil") != null) {
            dto.setRegistreringtil(rs.getTimestamp("registreringtil").toLocalDateTime());
        } else {
            dto.setRegistreringtil(null);
        }
        dto.setUniktdokumentnavn(rs.getString("uniktdokumentnavn"));
        dto.setStinavn(rs.getString("stinavn"));
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
        dto.setFiler(mapperFiler.mapFiler(rs.getString("filer")));
        dto.setDokumentsamling(rs.getString("dokumentsamling"));
        return dto;
    }

    public AndetDokumentDto mapAndetDokumentDto(ResultSet rs, StatementContext ctx)
            throws SQLException {
        // Mapper db og giver en ny instans med af AndetDokumentDto
        AndetDokumentDto dto = mapProtokolDto(rs, ctx, new AndetDokumentDto());

        // AndetDokumentDto har nogle felter der skal sættes ud over hvad ProtokolDto har
        dto.setDatatype(rs.getString("datatype"));
        dto.setFiltype(rs.getString("filtype"));

        return dto;
    }

    public HartkornsEkstraktDto mapHartkornsEkstraktDto(ResultSet rs, StatementContext ctx)
            throws SQLException {
        // Mapper db og giver en ny instans med af AeldretopografiskekortDto
        HartkornsEkstraktDto dto = mapProtokolDto(rs, ctx, new HartkornsEkstraktDto());

        // HartkornsEkstraktDto har nogle felter der skal sættes ud over hvad ProtokolDto har
        dto.setHerredsnavn(rs.getString("herredsnavn"));
        dto.setProtokoltype(rs.getString("protokoltype"));
        return dto;
    }

    public SogneprotokolDto mapSogneprotokolDto(ResultSet rs, StatementContext ctx)
            throws SQLException {
        // Mapper db og giver en ny instans med af AeldretopografiskekortDto
        SogneprotokolDto dto = mapProtokolDto(rs, ctx, new SogneprotokolDto());

        // SogneprotokolDto har nogle felter der skal sættes ud over hvad KortDto har
        dto.setAlternativtitel(rs.getString("alternativtitel"));
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

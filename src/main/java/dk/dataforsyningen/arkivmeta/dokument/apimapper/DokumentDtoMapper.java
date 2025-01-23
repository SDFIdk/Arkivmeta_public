package dk.dataforsyningen.arkivmeta.dokument.apimapper;

import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentDto;
import dk.dataforsyningen.arkivmeta.mapperhelper.MapperFiler;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class DokumentDtoMapper implements RowMapper<DokumentDto> {

  /**
   * The mapper must have a default constructor https://jdbi.org/#_registerrowmapper
   */
  public DokumentDtoMapper() {
  }

  /**
   * To support null values from the database, getObject is used as getLong uses primitive double data type that can not be null
   * https://stackoverflow.com/questions/9090077/how-to-check-if-a-double-is-null
   *
   * @param rs
   * @param ctx
   * @return
   * @throws SQLException
   */
  @Override
  public DokumentDto map(ResultSet rs, StatementContext ctx) throws SQLException {
    DokumentDto dokumentDto = new DokumentDto();
    MapperFiler mapperFiler = new MapperFiler();

    dokumentDto.setId(rs.getString("id"));
    dokumentDto.setKortgruppe(rs.getString("kortgruppe"));
    dokumentDto.setTitel(rs.getString("titel"));
    dokumentDto.setAlternativtitel(rs.getString("alternativtitel"));
    dokumentDto.setBemaerkning(rs.getString("bemaerkning"));
    dokumentDto.setGeometri(rs.getString("geometri"));

    Array sqlArrayDaekningsomraade = rs.getArray("daekningsomraade");
    String[] arrayDaekningsomraade = (String[]) sqlArrayDaekningsomraade.getArray();

    dokumentDto.setDaekningsomraade(Arrays.asList(arrayDaekningsomraade));

    Array sqlArrayFiler = rs.getArray("filer");
    String[] arrayFiler = (String[]) sqlArrayFiler.getArray();

    dokumentDto.setFiler(mapperFiler.mapFiler(arrayFiler));

    dokumentDto.setDatatype(rs.getString("datatype"));
    dokumentDto.setFiltype(rs.getString("filtype"));
    dokumentDto.setDokumentsamling(rs.getString("dokumentsamling"));
    dokumentDto.setHerredsnavn(rs.getString("herredsnavn"));
    dokumentDto.setHerredsnummer((Long) rs.getObject("herredsnummer"));
    dokumentDto.setProtokoltype(rs.getString("protokoltype"));

    Array sqlArraySogneid = rs.getArray("sogneid");
    Long[] arraySogneid = (Long[]) sqlArraySogneid.getArray();

    dokumentDto.setSogneid(Arrays.asList(arraySogneid));

    Array sqlArraySognenavn = rs.getArray("sognenavn");
    String[] arraySognenavn = (String[]) sqlArraySognenavn.getArray();

    dokumentDto.setSognenavn(Arrays.asList(arraySognenavn));

    return dokumentDto;
  }
}

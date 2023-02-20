package dk.dataforsyningen.arkivmeta.apimapper;

import dk.dataforsyningen.arkivmeta.apimodel.AeldretopografiskekortDto;
import dk.dataforsyningen.arkivmeta.apimodel.CentimeterkortDto;
import dk.dataforsyningen.arkivmeta.apimodel.FaeroesketopokortDto;
import dk.dataforsyningen.arkivmeta.apimodel.GroenlandtopokortDto;
import dk.dataforsyningen.arkivmeta.apimodel.HistoriskeflyfotoDto;
import dk.dataforsyningen.arkivmeta.apimodel.KortDto;
import dk.dataforsyningen.arkivmeta.apimodel.LandoekonomiskekortDto;
import dk.dataforsyningen.arkivmeta.apimodel.MaalebordsbladeDto;
import dk.dataforsyningen.arkivmeta.apimodel.MatrikelkortDto;
import dk.dataforsyningen.arkivmeta.apimodel.SoekortDto;
import dk.dataforsyningen.arkivmeta.apimodel.TematiskekortDto;
import dk.dataforsyningen.arkivmeta.enums.Arketype;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKBReader;

// RowMapper is a functional interface, which maps the current row of a JDBC ResultSet to a mapped
// type. Row mappers are invoked once for each row in the result set.
public class KortDtoMapper implements RowMapper<KortDto> {

  /**
   * The mapper must have a default constructor https://jdbi.org/#_registerrowmapper
   */
  public KortDtoMapper() {
  }

  @Override
  public KortDto map(ResultSet rs, StatementContext ctx) throws SQLException {
    Arketype type = Arketype.valueOf(rs.getString("arketype"));
    if (type == Arketype.aeldretopografiskekort) {
      return mapAeldretopografiskekortDto(rs, ctx);
    }
    if (type == Arketype.centimeterkort) {
      return mapCentimeterkortDto(rs, ctx);
    }
    if (type == Arketype.faeroesketopokort) {
      return mapFaeroesketopokortDto(rs, ctx);
    }
    if (type == Arketype.groenlandtopokort) {
      return mapGroenlandtopokortDto(rs, ctx);
    }
    if (type == Arketype.historiskeflyfoto) {
      return mapHistoriskeflyfotoDto(rs, ctx);
    }
    if (type == Arketype.landoekonomiskekort) {
      return mapLandoekonomiskekortDto(rs, ctx);
    }
    if (type == Arketype.maalebordsblade) {
      return mapMaalebordsbladeDto(rs, ctx);
    }
    if (type == Arketype.matrikelkort) {
      return mapMatrikelkortDto(rs, ctx);
    }
    if (type == Arketype.soekort) {
      return mapSoekortDto(rs, ctx);
    }
    if (type == Arketype.tematiskekort) {
      return mapTematiskekortDto(rs, ctx);
    }

    throw new IllegalStateException("Could not resolve mapping strategy for object");
  }

  public <T extends KortDto> T mapKortDto(ResultSet rs, StatementContext ctx, T dto)
      throws SQLException {
    MapperDaekningsomraade mapperDaekningsomraade = new MapperDaekningsomraade();
    MapperFiler mapperFiler = new MapperFiler();
    MapperGeometri mapperGeometri = new MapperGeometri();

    dto.setAlternativtitel(rs.getString("alternativtitel"));
    dto.setArketype(Arketype.valueOf(rs.getString("arketype")));
    dto.setBemaerkning(rs.getString("bemaerkning"));

    dto.setDaekningsomraade(
        mapperDaekningsomraade.mapDaekningsomraade(rs.getString("daekningsomraade")));
    dto.setFiler(mapperFiler.mapFiler(rs.getString("filer")));
    dto.setGaeldendefra(rs.getInt("gaeldendeperiode_gaeldendefra"));
    dto.setGaeldendetil(rs.getInt("gaeldendeperiode_gaeldendetil"));
    if (rs.getString("geometri") != null) {
      byte[] bytes = hexStringToByteArray(rs.getString("geometri"));

      Geometry test = (deserialize(bytes));
      dto.setGeometri(mapperGeometri.mapGeometri(test));
    } else {
      dto.setGeometri(mapperGeometri.mapGeometri(null));
    }

    dto.setId(rs.getString("id"));
    dto.setKortbladnummer(rs.getString("kortbladnummer"));
    dto.setKortvaerk(rs.getString("kortvaerk"));
    dto.setMaalestok(rs.getString("maalestok"));
    dto.setOriginalkortprojektion(rs.getString("orginalkortprojektion"));
    dto.setOriginalehjoernekoordinater(rs.getString("originalehjoernekoordinater"));
    dto.setRegistreringfra(rs.getTimestamp("registreringfra").toLocalDateTime());
    if (rs.getTimestamp("registreringtil") != null) {
      dto.setRegistreringtil(rs.getTimestamp("registreringtil").toLocalDateTime());
    } else {
      dto.setRegistreringtil(null);
    }
    dto.setTitel(rs.getString("titel"));
    dto.setUniktkortnavn(rs.getString("uniktkortnavn"));
    return dto;
  }

  /**
   * Map AeldretopografiskekortDB to AeldretopografiskekortDto
   *
   * @param
   * @return
   */
  public AeldretopografiskekortDto mapAeldretopografiskekortDto(ResultSet rs, StatementContext ctx)
      throws SQLException {
    // Mapper db og giver en ny instans med af AeldretopografiskekortDto
    AeldretopografiskekortDto dto = mapKortDto(rs, ctx, new AeldretopografiskekortDto());

    // AeldretopografiskekortDto har nogle felter der skal sættes ud over hvad KortDto har
    dto.setAarformaalt(rs.getInt("aarformaalt"));
    dto.setAarforudgivelse(rs.getInt("aarforudgivelse"));
    dto.setStedbetegnelse(rs.getString("stedbetegnelse"));
    dto.setTegner(rs.getString("tegner"));
    dto.setUdgiver(rs.getString("udgiver"));

    return dto;
  }

  private CentimeterkortDto mapCentimeterkortDto(ResultSet rs, StatementContext ctx)
      throws SQLException {
    // Mapper db og giver en ny instans med af CentimeterkortDto
    CentimeterkortDto dto = mapKortDto(rs, ctx, new CentimeterkortDto());

    dto.setAarforadministrativerettelser(rs.getInt("aarforadministrativerettelser"));
    dto.setAarfordata(rs.getInt("aarfordata"));
    dto.setAarforfotogrametriskudtegning(rs.getInt("aarforfotogrametriskudtegning"));
    dto.setAarforfotorekogrettelser(rs.getInt("aarforfotorekogrettelser"));
    dto.setAarforkompleteteretimarken(rs.getInt("aarforkompleteteretimarken"));
    dto.setAarformaalt(rs.getInt("aarformaalt"));
    dto.setAarforrevision(rs.getInt("aarforrevision"));
    dto.setAarforudarbejdelse(rs.getInt("aarforudarbejdelse"));
    dto.setAarforudgivelse(rs.getInt("aarforudgivelse"));
    dto.setAarforvejdata(rs.getInt("aarforvejdata"));
    dto.setVersion(rs.getString("version"));

    return dto;
  }

  private FaeroesketopokortDto mapFaeroesketopokortDto(ResultSet rs, StatementContext ctx)
      throws SQLException {
    // Mapper db og giver en ny instans med af FaeroesketopokortDto
    FaeroesketopokortDto dto = mapKortDto(rs, ctx, new FaeroesketopokortDto());

    dto.setAarformaalt(rs.getInt("aarformaalt"));
    dto.setAarforudgivelse(rs.getInt("aarforudgivelse"));
    dto.setVersion(rs.getString("version"));

    return dto;
  }

  private GroenlandtopokortDto mapGroenlandtopokortDto(ResultSet rs, StatementContext ctx)
      throws SQLException {
    // Mapper db og giver en ny instans med af GroenlandtopokortDto
    GroenlandtopokortDto dto = mapKortDto(rs, ctx, new GroenlandtopokortDto());

    dto.setAarforfotografering(rs.getInt("aarforfotografering"));
    dto.setAarforlineaerrettelse(rs.getInt("aarforlineaerrettelse"));
    dto.setAarformaalt(rs.getInt("aarformaalt"));
    dto.setAarforpunktgrundlag(rs.getInt("aarforpunktgrundlag"));
    dto.setAarforrevisonafnavnemm(rs.getInt("aarforrevisonafnavnemm"));
    dto.setAarfortopografi(rs.getInt("aarfortopografi"));
    dto.setAarforudgivelse(rs.getInt("aarforudgivelse"));
    dto.setAarforudtegning(rs.getInt("aarforudtegning"));
    dto.setVersion(rs.getString("version"));

    return dto;
  }

  private HistoriskeflyfotoDto mapHistoriskeflyfotoDto(ResultSet rs, StatementContext ctx)
      throws SQLException {
    // Mapper db og giver en ny instans med af HistoriskeflyfotoDto
    HistoriskeflyfotoDto dto = mapKortDto(rs, ctx, new HistoriskeflyfotoDto());

    dto.setDaasenummer(rs.getString("daasenummer"));
    dto.setFarveskalatype(rs.getString("farveskalatype"));
    dto.setFlyvehoejde(rs.getDouble("flyvehoejde"));
    dto.setFlyverute(rs.getString("flyverute"));
    dto.setFotocenterxkoordinat(rs.getDouble("fotocenterxkoordinat"));
    dto.setFotocenterykoordinat(rs.getDouble("fotocenterykoordinat"));
    dto.setFotonummer(rs.getString("fotonummer"));

    if (rs.getTimestamp("fototid") != null) {
      dto.setFototid(rs.getTimestamp("fototid").toLocalDateTime());
    } else {
      dto.setFototid(null);
    }

    dto.setFotovinkel(rs.getString("fotovinkel"));
    dto.setKameraid(rs.getString("kameraid"));
    dto.setProducent(rs.getString("producent"));

    return dto;
  }

  private LandoekonomiskekortDto mapLandoekonomiskekortDto(ResultSet rs, StatementContext ctx)
      throws SQLException {
    // Mapper db og giver en ny instans med af LandoekonomiskekortDto
    LandoekonomiskekortDto dto = mapKortDto(rs, ctx, new LandoekonomiskekortDto());

    dto.setAarformaalt(rs.getInt("aarformaalt"));
    dto.setTegner(rs.getString("tegner"));

    return dto;
  }

  private MaalebordsbladeDto mapMaalebordsbladeDto(ResultSet rs, StatementContext ctx)
      throws SQLException {
    // Mapper db og giver en ny instans med af MaalebordsbladeDto
    MaalebordsbladeDto dto = mapKortDto(rs, ctx, new MaalebordsbladeDto());

    dto.setAarfordata(rs.getInt("aarfordata"));
    dto.setAarforenkeltrettelser(rs.getInt("aarforenkeltrettelser"));
    dto.setAarformaalt(rs.getInt("aarformaalt"));
    dto.setAarforopmaalingsluttet(rs.getInt("aarforopmaalingsluttet"));
    dto.setAarforrettelse(rs.getInt("aarforrettelse"));
    dto.setAarforudarbejdelse(rs.getInt("aarforudarbejdelse"));
    dto.setAarforudgivelse(rs.getInt("aarforudgivelse"));
    dto.setAarforvejdata(rs.getInt("aarforvejdata"));
    dto.setTegner(rs.getString("tegner"));
    dto.setVersion(rs.getString("version"));

    return dto;
  }

  private MatrikelkortDto mapMatrikelkortDto(ResultSet rs, StatementContext ctx)
      throws SQLException {
    // Mapper db og giver en ny instans med af MatrikelkortDto
    MatrikelkortDto dto = mapKortDto(rs, ctx, new MatrikelkortDto());

    dto.setAarforkortproeve(rs.getInt("aarforkortproeve"));
    dto.setAarforopmaalingsluttet(rs.getInt("aarforopmaalingsluttet"));
    dto.setAarforudskiftning(rs.getInt("aarforudskiftning"));
    dto.setKortart(rs.getString("kortart"));
    dto.setKortdimensioner(rs.getString("kortdimensioner"));
    dto.setOpmaaltaf(rs.getString("opmaaltaf"));
    dto.setPlannr(rs.getString("plannr"));
    dto.setRytterdistriktid(rs.getLong("rytterdistriktid"));
    dto.setUdskiftetaf(rs.getString("udskiftetaf"));

    return dto;
  }

  private SoekortDto mapSoekortDto(ResultSet rs, StatementContext ctx) throws SQLException {
    // Mapper db og giver en ny instans med af SoekortDto
    SoekortDto dto = mapKortDto(rs, ctx, new SoekortDto());

    dto.setAarforhenlaeggelse(rs.getInt("aarforhenlaeggelse"));
    dto.setAarformaalt(rs.getInt("aarformaalt"));
    dto.setAarforudgivelse(rs.getInt("aarforudgivelse"));
    dto.setKortart(rs.getString("kortart"));
    dto.setSoeomraade(rs.getString("soeomraade"));
    dto.setTegner(rs.getString("tegner"));
    dto.setUdgiver(rs.getString("udgiver"));

    return dto;
  }

  private TematiskekortDto mapTematiskekortDto(ResultSet rs, StatementContext ctx)
      throws SQLException {
    // Mapper db og giver en ny instans med af TematiskekortDto
    TematiskekortDto dto = mapKortDto(rs, ctx, new TematiskekortDto());

    dto.setAarforudarbejdetmateriale(rs.getInt("aarforudarbejdetmateriale"));
    dto.setAarforudgivelse(rs.getInt("aarforudgivelse"));

    return dto;
  }

  /**
   * Deserializes a geometry in the WKB format.
   *
   * @param bytes A byte array representing a {@link Geometry} in WKB format or null.
   * @return The deserialized object or null if the byte array was null.
   */
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

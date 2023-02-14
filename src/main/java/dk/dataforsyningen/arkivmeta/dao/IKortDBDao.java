package dk.dataforsyningen.arkivmeta.dao;

import dk.dataforsyningen.arkivmeta.apimapper.KortDtoMapper;
import dk.dataforsyningen.arkivmeta.apimodel.KortDto;
import dk.dataforsyningen.arkivmeta.apimodel.KortParam;
import dk.dataforsyningen.arkivmeta.configuration.LogSqlFactory;
import java.util.List;
import java.util.Optional;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindFields;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.locationtech.jts.geom.Geometry;
import org.springframework.lang.Nullable;

@LogSqlFactory
public interface IKortDBDao {

  /**
   * To @BindList we need to use < instead of :
   * https://jdbi.org/#_binding_arguments_2
   *
   * @RegisterRowMapper use the registered mapper to map the select columns from the database to GetOrderDto
   * https://jdbi.org/#_registerrowmapper
   * https://jdbi.org/#_getgeneratedkeys
   * https://jdbi.org/#_timestamped
   */
  @SqlQuery("""
      SELECT
          id,
          objectid,
          arketype,
          titel,
          alternativtitel,
          registreringfra,
          registreringtil,
          uniktkortnavn,
          stinavn,
          orginalkortprojektion,
          originalehjoernekoordinater,
          bemaerkning,
          gaeldendeperiode_gaeldendefra,
          gaeldendeperiode_gaeldendetil,
          geometri,
          maalestok,
          kortbladnummer,
          kortvaerk,
          daekningsomraade,
          filer,
          aarforadministrativerettelser,
          aarfordata,
          aarforenkeltrettelser,
          aarforfotografering,
          aarforfotogrametriskudtegning,
          aarforfotorekogrettelser,
          aarforhenlaeggelse,
          aarforkompleteteretimarken,
          aarforkortproeve,
          aarforlineaerrettelse,
          aarformaalt,
          aarforopmaalingsluttet,
          aarforpunktgrundlag,
          aarforrettelse,
          aarforrevision,
          aarforrevisonafnavnemm,
          aarfortopografi,
          aarforudarbejdelse,
          aarforudarbejdetmateriale,
          aarforudgivelse,
          aarforudskiftning,
          aarforudtegning,
          aarforvejdata,
          daasenummer,
          farveskalatype,
          flyvehoejde,
          flyverute,
          fotocenterxkoordinat,
          fotocenterykoordinat,
          fotonummer,
          fototid,
          fotovinkel,
          kameraid,
          kortart,
          kortdimensioner,
          loebenummer,
          opmaaltaf,
          plannr,
          producent,
          rytterdistriktid,
          soeomraade,
          soeregion,
          stedbetegnelse,
          tegner,
          udgiver,
          udskiftetaf,
          VERSION
      FROM
          arkivmeta.arkivmeta
      WHERE
          id ILIKE :id
      """)
  @RegisterRowMapper(KortDtoMapper.class)
  Optional<KortDto> getKortById(@Bind("id") String id);


  /**
   * To @BindList we need to use < instead of :
   * https://jdbi.org/#_binding_arguments_2
   *
   * @RegisterRowMapper use the registered mapper to map the select columns from the database to GetOrderDto
   * https://jdbi.org/#_registerrowmapper
   * https://jdbi.org/#_getgeneratedkeys
   * https://jdbi.org/#_timestamped
   */
//  @SqlQuery("""
//      SELECT
//          id,
//          objectid,
//          arketype,
//          titel,
//          alternativtitel,
//          registreringfra,
//          registreringtil,
//          uniktkortnavn,
//          stinavn,
//          orginalkortprojektion,
//          originalehjoernekoordinater,
//          bemaerkning,
//          gaeldendeperiode_gaeldendefra,
//          gaeldendeperiode_gaeldendetil,
//          geometri,
//          maalestok,
//          kortbladnummer,
//          kortvaerk,
//          daekningsomraade,
//          filer,
//          aarforadministrativerettelser,
//          aarfordata,
//          aarforenkeltrettelser,
//          aarforfotografering,
//          aarforfotogrametriskudtegning,
//          aarforfotorekogrettelser,
//          aarforhenlaeggelse,
//          aarforkompleteteretimarken,
//          aarforkortproeve,
//          aarforlineaerrettelse,
//          aarformaalt,
//          aarforopmaalingsluttet,
//          aarforpunktgrundlag,
//          aarforrettelse,
//          aarforrevision,
//          aarforrevisonafnavnemm,
//          aarfortopografi,
//          aarforudarbejdelse,
//          aarforudarbejdetmateriale,
//          aarforudgivelse,
//          aarforudskiftning,
//          aarforudtegning,
//          aarforvejdata,
//          daasenummer,
//          farveskalatype,
//          flyvehoejde,
//          flyverute,
//          fotocenterxkoordinat,
//          fotocenterykoordinat,
//          fotonummer,
//          fototid,
//          fotovinkel,
//          kameraid,
//          kortart,
//          kortdimensioner,
//          loebenummer,
//          opmaaltaf,
//          plannr,
//          producent,
//          rytterdistriktid,
//          soeomraade,
//          soeregion,
//          stedbetegnelse,
//          tegner,
//          udgiver,
//          udskiftetaf,
//          VERSION
//      FROM
//          arkivmeta.arkivmeta
//      WHERE
//          (<arketype> IS NULL
//              OR arketype IN (<arketype>))
//          AND (<daekningsomraade> IS NULL
//              OR daekningsomraade IN (<daekningsomraade>))
//          AND (:kortbladnummer IS NULL
//              OR kortbladnummer ILIKE :kortbladnummer)
//          AND (<maalestok> IS NULL
//              OR maalestok IN (<maalestok>))
//          AND (:tegner IS NULL
//              OR tegner ILIKE :tegner)
//          AND (:titel IS NULL
//              OR titel ILIKE :titel)
//          AND (:fritekstsoegning IS NULL
//              OR fritekstsoegning @@ plainto_tsquery('danish', :fritekstsoegning))
//          AND (<kortvaerk> IS NULL
//              OR kortvaerk IN (<kortvaerk>))
//          AND
//          (:gaeldendefra >= gaeldendeperiode_gaeldendefra
//              AND :gaeldendefra <= gaeldendeperiode_gaeldendetil)
//          OR (:gaeldendetil >= gaeldendeperiode_gaeldendefra
//              AND :gaeldendetil <= gaeldendeperiode_gaeldendetil)
//          -- If 'til' is not defined assume user want everything after the 'fra' year
//          OR (:gaeldendetil IS NULL
//              AND :gaeldendefra >= gaeldendeperiode_gaeldendetil)
//          -- If 'fra' is not defined assume user want everything before the 'til' year
//          OR (:gaeldendefra IS NULL
//              AND :gaeldendetil >= gaeldendeperiode_gaeldendefra)
//          AND (:area IS NULL
//              OR ST_Intersects(geometri,
//              ST_SetSRID(CAST(:area AS geometry),
//              4326)))
//      ORDER BY
//          id || ' ' || :direction
//      LIMIT :limit
//      OFFSET :offset
//      """)
//  @RegisterRowMapper(KortDtoMapper.class)
//  List<KortDto> getAllKort(
//      @BindList(value = "arketype", onEmpty = BindList.EmptyHandling.NULL_STRING) List<String> arketype,
//      @BindList(value = "daekningsomraade", onEmpty = BindList.EmptyHandling.NULL_STRING) List<String> daekningsomraade,
//      @Bind("direction") String direction, @Bind("fritekstsoegning") String fritekstsoegning,
//      @Bind("gaeldendefra") int gaeldendefra, @Bind("gaeldendetil") int gaeldendetil,
//      @Bind("area") Geometry area, @Bind("kortbladnummer") String kortbladnummer,
//      @BindList(value = "kortvaerk", onEmpty = BindList.EmptyHandling.NULL_STRING) List<String> kortvaerk,
//      @BindList(value = "maalestok", onEmpty = BindList.EmptyHandling.NULL_STRING) List<String> maalestok,
//      @Bind("limit") int offset, @Bind("offset") int limit, @Bind("sort") String sort,
//      @Bind("tegner") String tegner, @Bind("titel") String titel);

  @SqlQuery("""
      SELECT
        *
      FROM
          arkivmeta.arkivmeta
      WHERE
          ((<arketype>) IS NULL
              OR arketype IN (<arketype>))
          AND (:daekningsomraade IS NULL
              -- How to use SIMILAR TO instead of like, when the parameter value can be a list
              -- https://stackoverflow.com/questions/4928054/postgresql-wildcard-like-for-any-of-a-list-of-words
              OR lower(daekningsomraade) SIMILAR TO lower('%(' || :daekningsomraade || ')%'))
          AND (:fritekstsoegning IS NULL
              OR fritekstsoegning @@ plainto_tsquery('danish', :fritekstsoegning))
          AND
              CASE
                  -- If 'fra' is defined and 'til' is defined assume user want everything in between, and what is overlapping with 'til's
                  WHEN (:gaeldendefra IS NOT NULL AND :gaeldendetil IS NOT NULL)
                      THEN (gaeldendeperiode_gaeldendetil >= :gaeldendefra AND gaeldendeperiode_gaeldendefra <= :gaeldendetil)
                  -- If 'fra' is defined assume user want everything after the 'fra' year
                  WHEN (:gaeldendefra IS NOT NULL)
                      THEN (gaeldendeperiode_gaeldendetil >= :gaeldendefra)
                  -- If 'til' is defined assume user want everything before the 'til' year
                  WHEN (:gaeldendetil IS NOT NULL)
                      THEN (gaeldendeperiode_gaeldendefra <= :gaeldendetil)
                  -- If the user did not define a year
                  ELSE
                      1 = 1
              END
          AND (:area IS NULL
              OR ST_Intersects(geometri,
              ST_SetSRID(CAST(:area AS geometry),
              4326)))
          AND (:kortbladnummer IS NULL
              OR kortbladnummer ILIKE '%' || :kortbladnummer || '%')
          AND (:kortvaerk IS NULL
              OR lower(kortvaerk) SIMILAR TO lower('%(' || :kortvaerk || ')%'))
          AND ((<maalestok>) IS NULL
              OR maalestok IN (<maalestok>))
      ORDER BY
          id :direction,
          :sort :direction
      LIMIT :limit
      OFFSET :offset
      """)
  @RegisterRowMapper(KortDtoMapper.class)
  List<KortDto> getAllKort(
      @BindList(value = "arketype", onEmpty = BindList.EmptyHandling.NULL_STRING) List<String> arketype,
      @Bind("daekningsomraade") String daekningsomraade,
      @Bind("fritekstsoegning") String fritekstsoegning,
      @Bind("gaeldendefra") Integer gaeldendefra, @Bind("gaeldendetil") Integer gaeldendetil,
      @Bind("area") Geometry area, @Bind("kortbladnummer") String kortbladnummer,
      @Bind("kortvaerk") String kortvaerk,
      @BindList(value = "maalestok", onEmpty = BindList.EmptyHandling.NULL_STRING) List<String> maalestok,
      @Bind("limit") int limit,
      @Bind("offset") int offset,
      @Bind("sort") String sort,
      @Bind("direction") String direction);

  /**
   * To @BindList we need to use < instead of :
   * https://jdbi.org/#_binding_arguments_2
   *
   * @RegisterRowMapper use the registered mapper to map the select columns from the database to GetOrderDto
   * https://jdbi.org/#_registerrowmapper
   * https://jdbi.org/#_getgeneratedkeys
   * https://jdbi.org/#_timestamped
   */
  @SqlQuery("""
      SELECT
        COUNT(*)
      FROM
          arkivmeta.arkivmeta
      WHERE
          ((<arketype>) IS NULL
              OR arketype IN (<arketype>))
          AND ((<daekningsomraade>) IS NULL
              OR daekningsomraade IN (<daekningsomraade>))
          AND (:kortbladnummer IS NULL
              OR kortbladnummer ILIKE :kortbladnummer)
          AND ((<maalestok>) IS NULL
              OR maalestok IN (<maalestok>))
          AND (:tegner IS NULL
              OR tegner ILIKE :tegner)
          AND (:titel IS NULL
              OR titel ILIKE :titel)
          AND (:fritekstsoegning IS NULL
              OR fritekstsoegning @@ plainto_tsquery('danish', :fritekstsoegning))
          AND ((<kortvaerk>) IS NULL
              OR kortvaerk IN (<kortvaerk>))
          AND
          (:gaeldendefra >= gaeldendeperiode_gaeldendefra
              AND :gaeldendefra <= gaeldendeperiode_gaeldendetil)
          OR (:gaeldendetil >= gaeldendeperiode_gaeldendefra
              AND :gaeldendetil <= gaeldendeperiode_gaeldendetil)
          -- If 'til' is not defined assume user want everything after the 'fra' year
          OR (:gaeldendetil IS NULL
              AND :gaeldendefra >= gaeldendeperiode_gaeldendetil)
          -- If 'fra' is not defined assume user want everything before the 'til' year
          OR (:gaeldendefra IS NULL
              AND :gaeldendetil >= gaeldendeperiode_gaeldendefra)
          AND (:area IS NULL
              OR ST_Intersects(geometri,
              ST_SetSRID(CAST(:area AS geometry),
              4326)))
      ORDER BY
          id || ' ' || :direction,
          CASE WHEN :arketype THEN arketype END || ' ' || :direction,
          CASE WHEN :daekningsomraade THEN daekningsomraade END || ' ' || :direction,
          CASE WHEN :gaeldendefra THEN gaeldendeperiode_gaeldendefra END || ' ' || :direction,
          CASE WHEN :gaeldendetil THEN gaeldendeperiode_gaeldendetil END || ' ' || :direction,
          CASE WHEN :kortvaerk THEN kortvaerk END || ' ' || :direction,
          CASE WHEN :maalestok THEN maalestok END || ' ' || :direction,
          CASE WHEN :titel THEN titel END || ' ' || :direction
      LIMIT :limit
      OFFSET :offset
      """)
  Long getCount(@BindFields() KortParam kortParam, @Bind("area") Geometry area);
}
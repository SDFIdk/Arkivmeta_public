package dk.dataforsyningen.arkivmeta.kort.dao;

import dk.dataforsyningen.arkivmeta.kort.apimapper.KortDtoMapper;
import dk.dataforsyningen.arkivmeta.kort.apimodel.KortDto;
import java.util.List;
import java.util.Optional;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.locationtech.jts.geom.Geometry;

// For printing sql statements
//@LogSqlFactory
public interface IKortDBDao {

  /**
   * @RegisterRowMapper use the registered mapper to map the select columns from the database to GetOrderDto
   * https://jdbi.org/#_registerrowmapper
   * https://jdbi.org/#_getgeneratedkeys
   * https://jdbi.org/#_timestamped
   */
  @SqlQuery("""
      SELECT
          *
      FROM
          arkivmeta.arkivmeta
      WHERE
          id = :id
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
          AND (:tegner IS NULL
              OR tegner ILIKE :tegner)
          AND (:titel IS NULL
              OR titel ILIKE :titel)
      ORDER BY
          -- Sql statements can not take user values and use them as column name. So we need to make
          -- a match with a CASE to map the user value to the correct column name.
          -- We also need to split ASC and DESC because it is SQL feature and can not be a given
          -- user value
          CASE
              WHEN (:direction = 'asc' AND :sort = 'arketype') THEN arketype
              WHEN (:direction = 'asc' AND :sort = 'daekningsomraade') THEN daekningsomraade
              -- We don't know why we need to cast gaeldendeperiode_gaeldendefra and
              -- gaeldendeperiode_gaeldendetil to varchar when it is the column name we are interested in
              WHEN (:direction = 'asc' AND :sort = 'gaeldendefra') THEN gaeldendeperiode_gaeldendefra::varchar
              WHEN (:direction = 'asc' AND :sort = 'gaeldendetil') THEN gaeldendeperiode_gaeldendetil::varchar
              WHEN (:direction = 'asc' AND :sort = 'gaeldendefra') THEN gaeldendeperiode_gaeldendefra::varchar
              WHEN (:direction = 'asc' AND :sort = 'kortvaerk') THEN kortvaerk
              WHEN (:direction = 'asc' AND :sort = 'maalestok') THEN maalestok
              WHEN (:direction = 'asc' AND :sort = 'titel') THEN titel
          END ASC,
          CASE
              WHEN (:direction = 'desc' AND :sort = 'arketype') THEN arketype
              WHEN (:direction = 'desc' AND :sort = 'daekningsomraade') THEN daekningsomraade
              -- We don't know why we need to cast gaeldendeperiode_gaeldendefra and
              -- gaeldendeperiode_gaeldendetil to varchar when it is the column name we are interested in
              WHEN (:direction = 'desc' AND :sort = 'gaeldendefra') THEN gaeldendeperiode_gaeldendefra::varchar
              WHEN (:direction = 'desc' AND :sort = 'gaeldendetil') THEN gaeldendeperiode_gaeldendetil::varchar
              WHEN (:direction = 'desc' AND :sort = 'gaeldendefra') THEN gaeldendeperiode_gaeldendefra::varchar
              WHEN (:direction = 'desc' AND :sort = 'kortvaerk') THEN kortvaerk
              WHEN (:direction = 'desc' AND :sort = 'maalestok') THEN maalestok
              WHEN (:direction = 'desc' AND :sort = 'titel') THEN titel
          END DESC,
          CASE
              WHEN :fritekstsoegning IS NOT NULL THEN ts_rank(fritekstsoegning, plainto_tsquery('danish', :fritekstsoegning))
          END ASC,
          -- There should always be an order by on id for consistent result because we have limit
          -- and offset
          CASE
              WHEN :direction = 'asc' THEN id
          END ASC,
          CASE
              WHEN :direction = 'desc' THEN id
          END DESC
      LIMIT :limit
      OFFSET :offset
      """)
  @RegisterRowMapper(KortDtoMapper.class)
  List<KortDto> getAllKort(
      // We need to be able to pass an empty list to the sql, but JDBI as default throws an exception
      // So we need to change @BindList emptyhandling to give a String with "null"
      // https://github.com/jdbi/jdbi/issues/1131
      // https://jdbi.org/apidocs/org/jdbi/v3/sqlobject/customizer/BindList.EmptyHandling.html#NULL_STRING
      @BindList(value = "arketype", onEmpty = BindList.EmptyHandling.NULL_STRING)
          List<String> arketype,
      @Bind("daekningsomraade") String daekningsomraade,
      @Bind("fritekstsoegning") String fritekstsoegning,
      @Bind("gaeldendefra") Integer gaeldendefra, @Bind("gaeldendetil") Integer gaeldendetil,
      @Bind("area") Geometry area, @Bind("kortbladnummer") String kortbladnummer,
      @Bind("kortvaerk") String kortvaerk,
      @BindList(value = "maalestok", onEmpty = BindList.EmptyHandling.NULL_STRING)
          List<String> maalestok,
      @Bind("tegner") String tegner,
      @Bind("titel") String titel,
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
          AND (:tegner IS NULL
              OR tegner ILIKE :tegner)
          AND (:titel IS NULL
              OR titel ILIKE :titel)
      LIMIT :limit
      OFFSET :offset
      """)
  Long getCount(
      // We need to be able to pass an empty list to the sql, but JDBI as default throws an exception
      // So we need to change @BindList emptyhandling to give a String with "null"
      // https://github.com/jdbi/jdbi/issues/1131
      // https://jdbi.org/apidocs/org/jdbi/v3/sqlobject/customizer/BindList.EmptyHandling.html#NULL_STRING
      @BindList(value = "arketype", onEmpty = BindList.EmptyHandling.NULL_STRING)
          List<String> arketype,
      @Bind("daekningsomraade") String daekningsomraade,
      @Bind("fritekstsoegning") String fritekstsoegning,
      @Bind("gaeldendefra") Integer gaeldendefra, @Bind("gaeldendetil") Integer gaeldendetil,
      @Bind("area") Geometry area, @Bind("kortbladnummer") String kortbladnummer,
      @Bind("kortvaerk") String kortvaerk,
      @BindList(value = "maalestok", onEmpty = BindList.EmptyHandling.NULL_STRING)
          List<String> maalestok,
      @Bind("tegner") String tegner,
      @Bind("titel") String titel,
      @Bind("limit") int limit,
      @Bind("offset") int offset);
}
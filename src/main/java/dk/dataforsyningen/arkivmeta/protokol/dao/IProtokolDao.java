package dk.dataforsyningen.arkivmeta.protokol.dao;

import dk.dataforsyningen.arkivmeta.configuration.LogSqlFactory;
import dk.dataforsyningen.arkivmeta.kort.apimapper.KortDtoMapper;
import dk.dataforsyningen.arkivmeta.protokol.apimapper.ProtokolDtoMapper;
import dk.dataforsyningen.arkivmeta.protokol.apimodel.ProtokolDto;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.locationtech.jts.geom.Geometry;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@LogSqlFactory
public interface IProtokolDao {

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
          arkivmeta.protokoller.protokoller p
      WHERE
          p.id = :id
  """)
  @RegisterRowMapper(ProtokolDtoMapper.class)
  Optional<ProtokolDto> getProtokolById(@Bind("id") String id);


  /**
   * To @BindList we need to use < instead of :
   * https://jdbi.org/#_binding_arguments_2
   *
   * @RegisterRowMapper use the registered mapper to map the select columns from the database to GetOrderDto
   * https://jdbi.org/#_registerrowmapper
   * https://jdbi.org/#_getgeneratedkeys
   * https://jdbi.org/#_timestamped
   */
  @SqlQuery( """
      SELECT
          *
      FROM
          arkivmeta.protokoller.protokoller
      WHERE
          (:herredsnavn IS NULL
              OR herredsnavn ILIKE '%' || :herredsnavn || '%')
          AND (:herredsnummer  IS NULL
              OR herredsnummer  = :herredsnummer)
          AND (:sognenavn IS NULL
              OR sognenavn ILIKE :sognenavn)
          AND (:sogneid IS NULL
              OR sogneid = :sogneid)
          AND  (:area IS NULL
              OR ST_Intersects(geometri,
              ST_SetSRID(CAST(:area AS geometry),
              4326)))
          AND ((<dokumentsamling>) IS NULL
              OR dokumentsamling IN (<dokumentsamling>))
             ORDER BY
          -- Sql statements can not take user values and use them as column name. So we need to make
          -- a match with a CASE to map the user value to the correct column name.
          -- We also need to split ASC and DESC because it is SQL feature and can not be a given
          -- user value
          CASE
              WHEN (:direction = 'asc' AND :sort = 'herredsnavn') THEN herredsnavn
              WHEN (:direction = 'asc' AND :sort = 'herredsnummer') THEN herredsnummer::varchar
              WHEN (:direction = 'asc' AND :sort = 'sognenavn') THEN sognenavn
              WHEN (:direction = 'asc' AND :sort = 'sogneid') THEN sogneid::varchar
              WHEN (:direction = 'asc' AND :sort = 'dokumentsamling') THEN dokumentsamling
          END ASC,
          CASE
              WHEN (:direction = 'desc' AND :sort = 'herredsnavn') THEN herredsnavn
              WHEN (:direction = 'desc' AND :sort = 'herredsnummer') THEN herredsnummer::varchar
              WHEN (:direction = 'desc' AND :sort = 'sognenavn') THEN sognenavn
              WHEN (:direction = 'desc' AND :sort = 'sogneid') THEN sogneid::varchar
              WHEN (:direction = 'desc' AND :sort = 'dokumentsamling') THEN dokumentsamling
          END DESC,
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
  @RegisterRowMapper(ProtokolDtoMapper.class)
  List<ProtokolDto> getAllProtokoller(
          @Bind("herredsnavn") String herredsnavn,
          @Bind("herredsnummer") Integer herredsnummer,
          @Bind("sognenavn") String sognenavn,
          @Bind("sogneid") Integer sogneid,
          @Bind("area") Geometry area,
          @BindList(value = "dokumentsamling", onEmpty = BindList.EmptyHandling.NULL_STRING)
              List<String> dokumentsamling,
          @Bind("limit") int limit,
          @Bind("offset") int offset,
          @Bind("sort") String sort,
          @Bind("direction") String direction);


  @SqlQuery("""
      SELECT
          COUNT(*)
      FROM
          arkivmeta.protokoller.protokoller
      WHERE
           (:herredsnavn IS NULL
              OR herredsnavn ILIKE '%' || :herredsnavn || '%')
          AND (:herredsnummer  IS NULL
              OR herredsnummer  ILIKE :herredsnummer::integer)
          AND (:sognenavn IS NULL
              OR sognenavn ILIKE :sognenavn)
          AND (:sogneid IS NULL
              OR sogneid ILIKE :sogneid)
          AND (:area IS NULL
              OR ST_Intersects(geometri,
              ST_SetSRID(CAST(:area AS geometry),
              4326)))
          AND ((<dokumentsamling>) IS NULL
              OR dokumentsamling IN (<dokumentsamling>))
             ORDER BY
          -- Sql statements can not take user values and use them as column name. So we need to make
          -- a match with a CASE to map the user value to the correct column name.
          -- We also need to split ASC and DESC because it is SQL feature and can not be a given
          -- user value
          CASE
              WHEN (:direction = 'asc' AND :sort = 'herredsnavn') THEN herredsnavn
              WHEN (:direction = 'asc' AND :sort = 'herredsnummer') THEN herredsnummer::varchar
              WHEN (:direction = 'asc' AND :sort = 'sognenavn') THEN sognenavn
              WHEN (:direction = 'asc' AND :sort = 'sogneid') THEN sogneid::varchar
              WHEN (:direction = 'asc' AND :sort = 'dokumentsamling') THEN dokumentsamling
          END ASC,
          CASE
              WHEN (:direction = 'desc' AND :sort = 'herredsnavn') THEN herredsnavn
              WHEN (:direction = 'desc' AND :sort = 'herredsnummer') THEN herredsnummer::varchar
              WHEN (:direction = 'desc' AND :sort = 'sognenavn') THEN sognenavn
              WHEN (:direction = 'desc' AND :sort = 'sogneid') THEN sogneid::varchar
              WHEN (:direction = 'desc' AND :sort = 'dokumentsamling') THEN dokumentsamling
          END DESC,
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
  @RegisterRowMapper(ProtokolDtoMapper.class)
  Long getCount(
          @Bind("herredsnavn") String herredsnavn,
          @Bind("herredsnummer") Integer herredsnummer,
          @Bind("sognenavn") String sognenavn,
          @Bind("sogneid") Integer sogneid,
          @Bind("area") Geometry area,
          @BindList(value = "dokumentsamling", onEmpty = BindList.EmptyHandling.NULL_STRING)
              List<String> dokumentsamling,
          @Bind("limit") int limit,
          @Bind("offset") int offset,
          @Bind("sort") String sort,
          @Bind("direction") String direction);
}


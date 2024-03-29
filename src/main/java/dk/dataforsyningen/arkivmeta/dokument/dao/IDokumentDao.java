package dk.dataforsyningen.arkivmeta.dokument.dao;

import dk.dataforsyningen.arkivmeta.configuration.LogSqlFactory;
import dk.dataforsyningen.arkivmeta.dokument.apimapper.DokumentDtoMapper;
import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentDto;
import java.util.List;
import java.util.Optional;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.locationtech.jts.geom.Geometry;

@LogSqlFactory
public interface IDokumentDao {

  @SqlQuery("""
          SELECT
             DISTINCT dokumentsamling
          FROM
             arkivmeta.protokoller.protokoller p
          ORDER BY 
            dokumentsamling ASC
      """)
  List<String> getDokumentSamling();

  @SqlQuery("""
          SELECT
             DISTINCT herredsnavn
          FROM
             arkivmeta.protokoller.protokoller p
          WHERE
            dokumentsamling = 'sogneprotokoller'
          ORDER BY
            herredsnavn ASC 
      """)
  List<String> getHerredsnavn();

  @SqlQuery("""
          SELECT
             DISTINCT sognenavn
          FROM
             arkivmeta.protokoller.protokoller p
          WHERE
            dokumentsamling = 'sogneprotokoller'
          ORDER BY
            sognenavn ASC
      """)
  List<String> getSognenavn();

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
  @RegisterRowMapper(DokumentDtoMapper.class)
  Optional<DokumentDto> getDokumentById(@Bind("id") String id);

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
          arkivmeta.protokoller.protokoller
      WHERE
          ((<dokumentsamling>) IS NULL
              OR dokumentsamling IN (<dokumentsamling>))
          AND (:fritekstsoegning IS NULL
              OR fritekstsoegning @@ plainto_tsquery('simple', :fritekstsoegning))
          AND (:area IS NULL
              OR ST_Intersects(geometri,
              ST_SetSRID(CAST(:area AS geometry),
              4326)))
          AND (:herredsnavn IS NULL
              OR herredsnavn ILIKE '%' || :herredsnavn || '%')
          AND (:herredsnummer  IS NULL
              OR herredsnummer  = :herredsnummer)
          AND (:sognenavn IS NULL
              OR sognenavn ILIKE :sognenavn)
          AND (:sogneid IS NULL
              OR sogneid = :sogneid)
          AND (:titel IS NULL
              OR titel ILIKE :titel)
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
              WHEN (:direction = 'asc' AND :sort = 'titel') THEN titel 
          END ASC,
          CASE
              WHEN (:direction = 'desc' AND :sort = 'herredsnavn') THEN herredsnavn
              WHEN (:direction = 'desc' AND :sort = 'herredsnummer') THEN herredsnummer::varchar
              WHEN (:direction = 'desc' AND :sort = 'sognenavn') THEN sognenavn
              WHEN (:direction = 'desc' AND :sort = 'sogneid') THEN sogneid::varchar
              WHEN (:direction = 'desc' AND :sort = 'dokumentsamling') THEN dokumentsamling
              WHEN (:direction = 'desc' AND :sort = 'titel') THEN titel 
          END DESC,
          CASE
              WHEN :fritekstsoegning IS NOT NULL THEN ts_rank(fritekstsoegning, plainto_tsquery('simple', :fritekstsoegning))
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
  @RegisterRowMapper(DokumentDtoMapper.class)
  List<DokumentDto> getAllDokumenter(
      @BindList(value = "dokumentsamling", onEmpty = BindList.EmptyHandling.NULL_STRING)
          List<String> dokumentsamling,
      @Bind("fritekstsoegning") String fritekstsoegning,
      @Bind("area") Geometry area,
      @Bind("herredsnavn") String herredsnavn,
      @Bind("herredsnummer") Integer herredsnummer,
      @Bind("sogneid") Integer sogneid,
      @Bind("sognenavn") String sognenavn,
      @Bind("titel") String titel,
      @Bind("direction") String direction,
      @Bind("sort") String sort,
      @Bind("limit") int limit,
      @Bind("offset") int offset);


  @SqlQuery("""
      SELECT
          COUNT(*)
      FROM
          arkivmeta.protokoller.protokoller
      WHERE
          ((<dokumentsamling>) IS NULL
              OR dokumentsamling IN (<dokumentsamling>))
          AND (:fritekstsoegning IS NULL
              OR fritekstsoegning @@ plainto_tsquery('simple', :fritekstsoegning))
          AND (:area IS NULL
              OR ST_Intersects(geometri,
              ST_SetSRID(CAST(:area AS geometry),
              4326)))
          AND (:herredsnavn IS NULL
              OR herredsnavn ILIKE '%' || :herredsnavn || '%')
          AND (:herredsnummer  IS NULL
              OR herredsnummer  = :herredsnummer)
          AND (:sognenavn IS NULL
              OR sognenavn ILIKE :sognenavn)
          AND (:sogneid IS NULL
              OR sogneid = :sogneid)
          AND (:titel IS NULL
              OR titel ILIKE :titel)
      """)
  @RegisterRowMapper(DokumentDtoMapper.class)
  Long getCount(
      @BindList(value = "dokumentsamling", onEmpty = BindList.EmptyHandling.NULL_STRING)
          List<String> dokumentsamling,
      @Bind("fritekstsoegning") String fritekstsoegning,
      @Bind("area") Geometry area,
      @Bind("herredsnavn") String herredsnavn,
      @Bind("herredsnummer") Integer herredsnummer,
      @Bind("sogneid") Integer sogneid,
      @Bind("sognenavn") String sognenavn,
      @Bind("titel") String titel);
}


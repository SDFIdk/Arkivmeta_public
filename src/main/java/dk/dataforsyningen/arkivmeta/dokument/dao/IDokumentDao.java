package dk.dataforsyningen.arkivmeta.dokument.dao;

import dk.dataforsyningen.arkivmeta.configuration.LogSqlFactory;
import dk.dataforsyningen.arkivmeta.dokument.apimapper.DokumentDtoMapper;
import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentDto;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
              DISTINCT array_to_string(sognenavn, ',') as sognenavn
          FROM
              historiskedokumenter.historiskedokumenter
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
              id,
              kortgruppe,
              titel,
              alternativtitel,
              bemaerkning,
              ST_AsEWKT(geometri) AS geometri,
              daekningsomraade,
              filer,
              "datatype",
              filtype,
              dokumentsamling,
              herredsnavn,
              herredsnummer,
              protokoltype,
              sogneid,
              sognenavn
          FROM
              historiskedokumenter.historiskedokumenter
          WHERE
              id = :id
      """)
  @RegisterRowMapper(DokumentDtoMapper.class)
  Optional<DokumentDto> getDokumentById(@Bind("id") UUID id);

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
          kortgruppe,
          titel,
          alternativtitel,
          bemaerkning,
          ST_AsEWKT(geometri) AS geometri,
          daekningsomraade,
          filer,
          "datatype",
          filtype,
          dokumentsamling,
          herredsnavn,
          herredsnummer,
          protokoltype,
          sogneid,
          sognenavn
      FROM
          historiskedokumenter.historiskedokumenter
      WHERE
          ((<kortgruppe>) IS NULL
              OR kortgruppe IN (<kortgruppe>))
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
              OR lower(sognenavn::VARCHAR) SIMILAR TO lower('%(' || :sognenavn || ')%'))
          AND (:sogneid IS NULL
              OR lower(sogneid::VARCHAR) SIMILAR TO lower('%(' || :sogneid || ')%'))
          AND (:titel IS NULL
              OR titel ILIKE :titel)
      ORDER BY
          -- Sql statements can not take user values and use them as column name. So we need to make
          -- a match with a CASE to map the user value to the correct column name.
          -- We also need to split ASC and DESC because it is SQL feature and can not be a given
          -- user value
          CASE
              WHEN (:direction = 'asc' AND :sort = 'kortgruppe') THEN kortgruppe
              WHEN (:direction = 'asc' AND :sort = 'herredsnavn') THEN herredsnavn
              WHEN (:direction = 'asc' AND :sort = 'herredsnummer') THEN herredsnummer::varchar
              WHEN (:direction = 'asc' AND :sort = 'titel') THEN titel
          END ASC,
          CASE
              WHEN (:direction = 'desc' AND :sort = 'kortgruppe') THEN kortgruppe
              WHEN (:direction = 'desc' AND :sort = 'herredsnavn') THEN herredsnavn
              WHEN (:direction = 'desc' AND :sort = 'herredsnummer') THEN herredsnummer::varchar
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
      @Bind("area") Geometry area,
      @Bind("direction") String direction,
      @Bind("fritekstsoegning") String fritekstsoegning,
      @Bind("herredsnavn") String herredsnavn,
      @Bind("herredsnummer") Integer herredsnummer,
      @BindList(value = "kortgruppe", onEmpty = BindList.EmptyHandling.NULL_STRING)
      List<String> kortgruppe,
      @Bind("limit") int limit,
      @Bind("offset") int offset,
      @Bind("sogneid") Integer sogneid,
      @Bind("sognenavn") String sognenavn,
      @Bind("sort") String sort,
      @Bind("titel") String titel);


  @SqlQuery("""
      SELECT
          COUNT(*)
      FROM
          historiskedokumenter.historiskedokumenter
      WHERE
          ((<kortgruppe>) IS NULL
              OR kortgruppe IN (<kortgruppe>))
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
              OR lower(sognenavn::VARCHAR) SIMILAR TO lower('%(' || :sognenavn || ')%'))
          AND (:sogneid IS NULL
              OR lower(sogneid::VARCHAR) SIMILAR TO lower('%(' || :sogneid || ')%'))
          AND (:titel IS NULL
              OR titel ILIKE :titel)
      """)
  @RegisterRowMapper(DokumentDtoMapper.class)
  Long getCount(
      @Bind("area") Geometry area,
      @Bind("fritekstsoegning") String fritekstsoegning,
      @Bind("herredsnavn") String herredsnavn,
      @Bind("herredsnummer") Integer herredsnummer,
      @BindList(value = "kortgruppe", onEmpty = BindList.EmptyHandling.NULL_STRING)
      List<String> kortgruppe,
      @Bind("sogneid") Integer sogneid,
      @Bind("sognenavn") String sognenavn,
      @Bind("titel") String titel);
}


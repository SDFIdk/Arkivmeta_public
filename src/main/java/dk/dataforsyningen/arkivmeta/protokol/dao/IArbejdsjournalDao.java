package dk.dataforsyningen.arkivmeta.protokol.dao;

import dk.dataforsyningen.arkivmeta.protokol.apimapper.ArbejdsjournalMapper;
import dk.dataforsyningen.arkivmeta.protokol.apimodel.ArbejdsjournalDto;
import java.util.List;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface IArbejdsjournalDao {
    /**
     * @RegisterRowMapper use the registered mapper to map the select columns from the database to GetOrderDto
     * https://jdbi.org/#_registerrowmapper
     * https://jdbi.org/#_getgeneratedkeys
     * https://jdbi.org/#_timestamped
     */
    @SqlQuery("""
      SELECT arketype, arkenavn, kortvaerk
      FROM arkivmeta.a_rel_arketype_agg_kortvaerker
      """)
    @RegisterRowMapper(ArbejdsjournalMapper.class)
    List<ArbejdsjournalDto> getAllArbejdsjournaler();
}

package dk.dataforsyningen.arkivmeta.protokol.dao;

import dk.dataforsyningen.arkivmeta.protokol.apimodel.ArbejdsjournalDto;
import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ArbejdsjournalDao implements IArbejdsjournalDao {

    @Qualifier("protokolJdbi")
    private final Jdbi arkivmetaJdbi;

    @Autowired
    public ArbejdsjournalDao(@Qualifier("arkivmetaJdbi") Jdbi protokolJdbi) {
        this.arkivmetaJdbi = protokolJdbi;
    }

    @Override
    public List<ArbejdsjournalDto> getAllArbejdsjournaler() {
        return arkivmetaJdbi.withExtension(IArbejdsjournalDao.class, dao -> dao.getAllArbejdsjournaler());
    }
}

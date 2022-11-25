package dk.dataforsyningen.arkivmeta.repository;

import dk.dataforsyningen.arkivmeta.apimodel.DaekningsomraadeDto;
import dk.dataforsyningen.arkivmeta.datamodel.DaekningsomraadeDB;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDaekningsomraadeRepository extends JpaRepository<DaekningsomraadeDB, String> {
  List<DaekningsomraadeDto> findByDaekningsomraadeContainingIgnoreCase(String daekningsomraade);
}


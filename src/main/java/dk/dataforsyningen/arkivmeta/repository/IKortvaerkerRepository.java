package dk.dataforsyningen.arkivmeta.repository;

import dk.dataforsyningen.arkivmeta.apimodel.KortvaerkDto;
import dk.dataforsyningen.arkivmeta.datamodel.KortvaerkDB;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IKortvaerkerRepository extends JpaRepository<KortvaerkDB, String> {
  List<KortvaerkDto> findByArketypeAndKortvaerkContainingIgnoreCase(String arketype,
                                                                    String kortvaerk);

  List<KortvaerkDto> findByKortvaerkContainingIgnoreCase(String kortvaerk);
}

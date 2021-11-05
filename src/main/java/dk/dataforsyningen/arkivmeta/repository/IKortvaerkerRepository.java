package dk.dataforsyningen.arkivmeta.repository;

import dk.dataforsyningen.arkivmeta.apimodel.KortvaerkDto;
import dk.dataforsyningen.arkivmeta.datamodel.KortvaerkDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IKortvaerkerRepository extends JpaRepository<KortvaerkDB, String>
{
    List<KortvaerkDto> findByArketypeAndKortvaerkContainingIgnoreCase(String arketype, String kortvaerk);

    List<KortvaerkDto> findByKortvaerkContainingIgnoreCase(String kortvaerk);
}

package dk.dataforsyningen.arkivmeta.repository;

import dk.dataforsyningen.arkivmeta.apimodel.MaalestokDto;
import dk.dataforsyningen.arkivmeta.datamodel.MaalestokDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMaalestokRepository extends JpaRepository<MaalestokDB, String>
{
    List<MaalestokDto> findByMaalestokContainingIgnoreCase(String maalestok);
}

package dk.dataforsyningen.arkivmeta.repository;

import dk.dataforsyningen.arkivmeta.apimodel.MaalestokDto;
import dk.dataforsyningen.arkivmeta.datamodel.MaalestokDB;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMaalestokRepository extends JpaRepository<MaalestokDB, String> {
  List<MaalestokDto> findByMaalestokContainingIgnoreCase(String maalestok);
}

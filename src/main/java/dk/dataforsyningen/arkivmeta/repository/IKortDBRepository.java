package dk.dataforsyningen.arkivmeta.repository;

import dk.dataforsyningen.arkivmeta.datamodel.KortDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IKortDBRepository extends JpaRepository<KortDB, String> {
}
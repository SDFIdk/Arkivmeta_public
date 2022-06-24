package dk.dataforsyningen.arkivmeta.repository;

import dk.dataforsyningen.arkivmeta.datamodel.ArketypeDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArketypeRepository extends JpaRepository<ArketypeDB, String>
{
    /**
     * If we want to search on arketypes
     * WHERE clause: If there is given an arkenavn then add it to the query
     * @param arkenavn
     * @return
     */
//    @Query("SELECT DISTINCT a.arkenavn FROM Arketype a WHERE (:arkenavn is null or a.arkenavn =:arkenavn) ORDER BY a.arkenavn")
//    List<String> findAllUniqueArkenavn(String arkenavn);
}

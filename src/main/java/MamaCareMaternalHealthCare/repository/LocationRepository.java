package MamaCareMaternalHealthCare.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import MamaCareMaternalHealthCare.model.ELocationType;
import MamaCareMaternalHealthCare.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {

    Optional<Location> findByCode(String code);
    List<Location> findByType(ELocationType type);
    List<Location> findByParentCode(String parentCode);
    List<Location> findByName(String name);
    List<Location> findByTypeAndName(ELocationType type, String name);
    List<Location> findByTypeAndParentCode(ELocationType type, String parentCode);
    List<Location> findByNameStartsWith(String name);

    Boolean existsByCode(String code);
    
}

package MamaCareMaternalHealthCare.repository;

import java.util.UUID;
import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import MamaCareMaternalHealthCare.model.ELocationType;
import MamaCareMaternalHealthCare.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {

    Optional<Location> findByCode(String code);
    List<Location> findByType(ELocationType type);

    Boolean existsByCode(String code);
}

package MamaCareMaternalHealthCare.repository;

import java.util.UUID;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import MamaCareMaternalHealthCare.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, UUID> {

    Optional<Specialization> findByName(String name);
}

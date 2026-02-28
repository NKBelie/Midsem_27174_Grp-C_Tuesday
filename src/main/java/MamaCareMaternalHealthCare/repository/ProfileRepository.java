package MamaCareMaternalHealthCare.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import MamaCareMaternalHealthCare.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {

}

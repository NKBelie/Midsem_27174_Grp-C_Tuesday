package MamaCareMaternalHealthCare.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import MamaCareMaternalHealthCare.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    Optional<Profile> findById(UUID id);
    List<Profile> findByFullName(String fullName);
    List<Profile> findByEmail(String email);
    List<Profile> findByPhoneNumber(String phoneNumber);
    List<Profile> findByAddress(String address);
}

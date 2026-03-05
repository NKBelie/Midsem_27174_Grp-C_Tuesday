package MamaCareMaternalHealthCare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import MamaCareMaternalHealthCare.model.EUserType;
import MamaCareMaternalHealthCare.model.Location;
import MamaCareMaternalHealthCare.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
    List<User> findByRole(EUserType role);
    List<User> findByLocation(Location location);
    List<User> findByEmailAndPassword(String email, String password);
    List<User> findByRoleAndLocation(EUserType role, Location location);
    List<User> findByFullName(String fullName);
    List<User> findByFullNameAndRole(String fullName,EUserType role);
    List<User> findByFullNameAndRoleAndLocation(String fullName, EUserType role, Location location);

}

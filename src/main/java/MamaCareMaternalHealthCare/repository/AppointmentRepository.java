package MamaCareMaternalHealthCare.repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import MamaCareMaternalHealthCare.model.Appointment;
import MamaCareMaternalHealthCare.model.User;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    List<Appointment> findByMother(User mother);
    List<Appointment> findByDoctor(User doctor);
    List<Appointment> findByMotherAndDoctor(User mother, User doctor);
}

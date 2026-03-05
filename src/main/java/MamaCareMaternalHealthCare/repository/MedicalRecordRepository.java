package MamaCareMaternalHealthCare.repository;

import java.util.UUID;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import MamaCareMaternalHealthCare.model.MedicalRecord;
import MamaCareMaternalHealthCare.model.User;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, UUID> {

    List<MedicalRecord> findByMother(User mother);
    List<MedicalRecord> findByDoctor(User doctor);
    List<MedicalRecord> findByMotherAndDoctor(User mother, User doctor);
    List<MedicalRecord> findByRecordDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<MedicalRecord> findByMotherAndRecordDateBetween(User mother, LocalDateTime startDate, LocalDateTime endDate);
    List<MedicalRecord> findByDoctorAndRecordDateBetween(User doctor, LocalDateTime startDate, LocalDateTime endDate);
    List<MedicalRecord> findByRecordDate(LocalDateTime recordDate);

    Optional<MedicalRecord> findById(UUID id);

}

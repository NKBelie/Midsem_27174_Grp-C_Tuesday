package MamaCareMaternalHealthCare.repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import MamaCareMaternalHealthCare.model.MedicalRecord;
import MamaCareMaternalHealthCare.model.User;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, UUID> {

    List<MedicalRecord> findByMother(User mother);
    List<MedicalRecord> findByDoctor(User doctor);

}

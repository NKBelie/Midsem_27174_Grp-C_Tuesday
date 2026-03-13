package MamaCareMaternalHealthCare.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
=======
>>>>>>> a2470a44733f71f8e08a7236f5259ae3a3616b11
import org.springframework.stereotype.Service;

import MamaCareMaternalHealthCare.model.MedicalRecord;
import MamaCareMaternalHealthCare.model.User;
import MamaCareMaternalHealthCare.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    // Add Medical Record
    public String addMedicalRecord(MedicalRecord medicalRecord){

        if(medicalRecord.getId() != null){
            Optional<MedicalRecord> existMedicalRecord =
                    medicalRecordRepository.findById(medicalRecord.getId());

            if(existMedicalRecord.isPresent()){
                return "Medical Record with id " + medicalRecord.getId() + " already exists";
            }
        }

        medicalRecordRepository.save(medicalRecord);
        return "Medical Record added successfully";
    }
<<<<<<< HEAD
    public Page<MedicalRecord> getMedicalRecordsPagination(Pageable pageable){
        return medicalRecordRepository.findAll(pageable);
    }

    public List<MedicalRecord> getMedicalRecordsSorted(String field){
       return medicalRecordRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }
=======

>>>>>>> a2470a44733f71f8e08a7236f5259ae3a3616b11
    // Get All Medical Records
    public List<MedicalRecord> getAllMedicalRecords(){
        return medicalRecordRepository.findAll();
    }

    // Get Medical Record by ID
    public Optional<MedicalRecord> getMedicalRecordById(UUID id){
        return medicalRecordRepository.findById(id);
    }

    // Get records by Mother
    public List<MedicalRecord> getRecordsByMother(User mother){
        return medicalRecordRepository.findByMother(mother);
    }

    // Get records by Doctor
    public List<MedicalRecord> getRecordsByDoctor(User doctor){
        return medicalRecordRepository.findByDoctor(doctor);
    }

    // Get records by Mother and Doctor
    public List<MedicalRecord> getRecordsByMotherAndDoctor(User mother, User doctor){
        return medicalRecordRepository.findByMotherAndDoctor(mother, doctor);
    }

    // Get records between dates
    public List<MedicalRecord> getRecordsBetweenDates(LocalDateTime startDate, LocalDateTime endDate){
        return medicalRecordRepository.findByRecordDateBetween(startDate, endDate);
    }

    // Get records for a mother between dates
    public List<MedicalRecord> getMotherRecordsBetweenDates(User mother, LocalDateTime startDate, LocalDateTime endDate){
        return medicalRecordRepository.findByMotherAndRecordDateBetween(mother, startDate, endDate);
    }

    // Update Medical Record
    public String updateMedicalRecord(UUID id, MedicalRecord updatedRecord){

        Optional<MedicalRecord> existingRecord = medicalRecordRepository.findById(id);

        if(existingRecord.isPresent()){
            MedicalRecord record = existingRecord.get();

            record.setDiagnosis(updatedRecord.getDiagnosis());
            //record.setTreatment(updatedRecord.getTreatment());
            record.setRecordDate(updatedRecord.getRecordDate());

            medicalRecordRepository.save(record);

            return "Medical record updated successfully";
        }else{
            return "Medical record not found";
        }
    }

    // Delete Medical Record
    public String deleteMedicalRecord(UUID id){

        Optional<MedicalRecord> existingRecord = medicalRecordRepository.findById(id);

        if(existingRecord.isPresent()){
            medicalRecordRepository.deleteById(id);
            return "Medical record deleted successfully";
        }else{
            return "Medical record not found";
        }
    }

}
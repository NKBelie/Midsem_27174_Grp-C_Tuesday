package MamaCareMaternalHealthCare.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import MamaCareMaternalHealthCare.model.MedicalRecord;
import MamaCareMaternalHealthCare.model.User;
import MamaCareMaternalHealthCare.service.MedicalRecordService;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    // Add Medical Record
    @PostMapping("/add")
    public String addMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        return medicalRecordService.addMedicalRecord(medicalRecord);
    }

    // Get all records
    @GetMapping("/allRecord")
    public List<MedicalRecord> getAllMedicalRecords(){
        return medicalRecordService.getAllMedicalRecords();
    }

    // Get record by ID
    @GetMapping("/{id}")
    public Optional<MedicalRecord> getMedicalRecordById(@PathVariable UUID id){
        return medicalRecordService.getMedicalRecordById(id);
    }

    // Get records by mother
    @PostMapping("/mother")
    public List<MedicalRecord> getRecordsByMother(@RequestBody User mother){
        return medicalRecordService.getRecordsByMother(mother);
    }

    // Get records by doctor
    @PostMapping("/doctor")
    public List<MedicalRecord> getRecordsByDoctor(@RequestBody User doctor){
        return medicalRecordService.getRecordsByDoctor(doctor);
    }

    // Get records by mother and doctor
    @PostMapping("/motherAndDoctor")
    public List<MedicalRecord> getRecordsByMotherAndDoctor(@RequestBody User mother, @RequestBody User doctor){
        return medicalRecordService.getRecordsByMotherAndDoctor(mother, doctor);
    }

    // Get records between dates
    @GetMapping("/betweenDates")
    public List<MedicalRecord> getRecordsBetweenDates(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate){

        return medicalRecordService.getRecordsBetweenDates(startDate, endDate);
    }

    // Update medical record
    @PutMapping("/update/{id}")
    public String updateMedicalRecord(@PathVariable UUID id, @RequestBody MedicalRecord record){
        return medicalRecordService.updateMedicalRecord(id, record);
    }

    // Delete medical record
    @DeleteMapping("/delete/{id}")
    public String deleteMedicalRecord(@PathVariable UUID id){
        return medicalRecordService.deleteMedicalRecord(id);
    }
    @GetMapping("/pagination")
    public ResponseEntity<?> getMedicalRecords(Pageable pageable){
        return ResponseEntity.ok(medicalRecordService.getMedicalRecordsPagination(pageable));
    }

    @GetMapping("/sort/{field}")
    public ResponseEntity<?> sortMedicalRecords(@PathVariable String field){
        return ResponseEntity.ok(medicalRecordService.getMedicalRecordsSorted(field));
    }
}
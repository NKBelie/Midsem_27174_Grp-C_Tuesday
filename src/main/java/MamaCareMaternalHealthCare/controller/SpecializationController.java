package MamaCareMaternalHealthCare.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import MamaCareMaternalHealthCare.model.Specialization;
import MamaCareMaternalHealthCare.service.SpecializationService;

@RestController
@RequestMapping("/api/specializations")
public class SpecializationController {

    @Autowired
    private SpecializationService specializationService;

    // Save Specialization
    @PostMapping("/save")
    public ResponseEntity<?> saveSpecialization(@RequestBody Specialization specialization){
        Specialization savedSpecialization = specializationService.saveSpecialization(specialization);
        return ResponseEntity.ok(savedSpecialization);
    }

    // Get Specialization by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getSpecializationById(@PathVariable UUID id){
        Specialization specialization = specializationService.getSpecializationById(id);
        return ResponseEntity.ok(specialization);
    }

    // Get All Specializations
    @GetMapping("/all")
    public ResponseEntity<?> getAllSpecializations(){
        List<Specialization> specializations = specializationService.getAllSpecializations();
        return ResponseEntity.ok(specializations);
    }

    // Update Specialization
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSpecialization(@RequestBody Specialization specialization, @PathVariable UUID id){
        String message = specializationService.updateSpecialization(specialization, id);
        return ResponseEntity.ok(message);
    }

    // Delete Specialization
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSpecialization(@PathVariable UUID id){
        String message = specializationService.deleteSpecialization(id);
        return ResponseEntity.ok(message);
    }
}
package MamaCareMaternalHealthCare.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MamaCareMaternalHealthCare.model.Specialization;
import MamaCareMaternalHealthCare.repository.SpecializationRepository;

@Service
public class SpecializationService {

    @Autowired
    private SpecializationRepository specializationRepository;
    
    public Specialization saveSpecialization(Specialization specialization) {
        if (specialization.getId() != null) {
            Specialization existingSpecialization = specializationRepository.findById(specialization.getId()).orElse(null);
            if (existingSpecialization != null) {
                existingSpecialization.setName(specialization.getName());
                return specializationRepository.save(existingSpecialization);
            } else {
                throw new RuntimeException("Specialization with ID " + specialization.getId() + " not found.");
            }
        } else {
            return specializationRepository.save(specialization);
        }
    }

    public Specialization getSpecializationById(UUID specializationId) {
        return specializationRepository.findById(specializationId)
                .orElseThrow(() -> new RuntimeException("Specialization with ID " + specializationId + " not found."));
    }

    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }

    public String updateSpecialization(Specialization specialization, UUID specializationId) {
        Specialization existingSpecialization = specializationRepository.findById(specializationId).orElse(null);
        if (existingSpecialization != null) {
            existingSpecialization.setName(specialization.getName());
            specializationRepository.save(existingSpecialization);
            return "Specialization updated successfully.";
        } else {
            return "Specialization with ID " + specializationId + " not found.";
        }
    }
    public String deleteSpecialization(UUID specializationId) {
        Specialization existingSpecialization = specializationRepository.findById(specializationId).orElse(null);
        if (existingSpecialization != null) {
            specializationRepository.delete(existingSpecialization);
            return "Specialization deleted successfully.";
        } else {
            return "Specialization with ID " + specializationId + " not found.";
        }
    }
}
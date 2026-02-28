package MamaCareMaternalHealthCare.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medical_record")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String diagnosis;
    private String prescription;
    private LocalDateTime recordDate;

    @ManyToOne
    @JoinColumn(name = "mother_id")
    private User mother;

    @ManyToOne
    @JoinColumn(name ="doctor_id")
    private User doctor;

    // Getters and Setters
    public UUID getId() {
        return id;
    }
    public void setId(UUID id){
        this.id = id;
    }
    public String getDiagnosis() {
        return diagnosis;
    }
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    public String prescription(){
        return prescription;
    }
    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }
    public LocalDateTime getRecordDate() {
        return recordDate;
    }
    public void setRecordDate(LocalDateTime recordDate) {
        this.recordDate = recordDate;
    }
    public User getMother() {
        return mother;
    }
    public void setMother(User mother) {
        this.mother = mother;
    }
    public User getDoctor() {
        return doctor;
    }
    public void setDoctor (User doctor) {
        this.doctor = doctor;
    }
}

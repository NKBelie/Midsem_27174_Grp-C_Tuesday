package MamaCareMaternalHealthCare.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import MamaCareMaternalHealthCare.model.Appointment;
import MamaCareMaternalHealthCare.repository.AppointmentRepository;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRep;

    public Appointment saveAppointment(Appointment appointment) {
        if (appointment.getId() != null) {
            Appointment existingAppointment = appointmentRep.findById(appointment.getId()).orElse(null);
            if (existingAppointment != null) {
                existingAppointment.setAppointmentDateTime(appointment.getAppointmentDateTime());
                existingAppointment.setDoctor(appointment.getDoctor());
                existingAppointment.setMother(appointment.getMother());
                return appointmentRep.save(existingAppointment);
            } else {
                throw new RuntimeException("Appointment with ID " + appointment.getId() + " not found.");
            }
        } else {
        return appointmentRep.save(appointment);
        }
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRep.findAll();
    }
    
    public Optional<Appointment> getAppointmentById(UUID id) {
        return appointmentRep.findById(id);
    }
    public String deleteAppointmentById(UUID id) {
        appointmentRep.deleteById(id);
        return "Appointment with id " + id + " has been deleted.";
    }

}

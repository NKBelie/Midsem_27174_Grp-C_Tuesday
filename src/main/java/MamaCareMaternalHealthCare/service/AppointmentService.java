package MamaCareMaternalHealthCare.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import MamaCareMaternalHealthCare.model.Appointment;
import MamaCareMaternalHealthCare.model.User;
import MamaCareMaternalHealthCare.repository.AppointmentRepository;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRep;

    // Save Appointment
    public Appointment saveAppointment(Appointment appointment) {

        if (appointment.getId() != null) {

            Appointment existingAppointment =
                    appointmentRep.findById(appointment.getId()).orElse(null);

            if (existingAppointment != null) {

                existingAppointment.setAppointmentDateTime(
                        appointment.getAppointmentDateTime());

                existingAppointment.setDoctor(appointment.getDoctor());
                existingAppointment.setMother(appointment.getMother());

                return appointmentRep.save(existingAppointment);

            } else {
                throw new RuntimeException(
                        "Appointment with ID " + appointment.getId() + " not found.");
            }

        } else {
            return appointmentRep.save(appointment);
        }
    }
    public Page<Appointment> getAppointmentsPagination(Pageable pageable){
        return appointmentRep.findAll(pageable);
    }

    public List<Appointment> getAppointmentsSorted(String field){
        return appointmentRep.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    // Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRep.findAll();
    }

    // Get appointment by ID
    public Optional<Appointment> getAppointmentById(UUID id) {
        return appointmentRep.findById(id);
    }

    // Update appointment
    public String updateAppointment(UUID id, Appointment appointment) {

        Optional<Appointment> existingAppointment = appointmentRep.findById(id);

        if (existingAppointment.isPresent()) {

            Appointment ap = existingAppointment.get();

            ap.setAppointmentDateTime(appointment.getAppointmentDateTime());
            ap.setDoctor(appointment.getDoctor());
            ap.setMother(appointment.getMother());

            appointmentRep.save(ap);

            return "Appointment updated successfully.";

        } else {
            return "Appointment with id " + id + " not found.";
        }
    }

    // Delete appointment
    public String deleteAppointmentById(UUID id) {

        if (appointmentRep.existsById(id)) {
            appointmentRep.deleteById(id);
            return "Appointment with id " + id + " has been deleted.";
        } else {
            return "Appointment with id " + id + " not found.";
        }
    }

    // Find appointments by Doctor
    public List<Appointment> getAppointmentsByDoctor(User doctor) {
        return appointmentRep.findByDoctor(doctor);
    }

    // Find appointments by Mother
    public List<Appointment> getAppointmentsByMother(User mother) {
        return appointmentRep.findByMother(mother);
    }

}
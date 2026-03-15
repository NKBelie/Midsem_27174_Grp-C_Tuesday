package MamaCareMaternalHealthCare.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import MamaCareMaternalHealthCare.model.Appointment;
import MamaCareMaternalHealthCare.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Create Appointment
    @PostMapping("/save")
    public ResponseEntity<?> saveAppointment(@RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.saveAppointment(appointment));
    }

    // Get all appointments
    @GetMapping("/allAppointment")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    // Get appointment by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable UUID id) {

        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);

        if (appointment.isPresent()) {
            return ResponseEntity.ok(appointment.get());
        }

        return ResponseEntity.badRequest().body("Appointment not found");
    }

    // Update appointment
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAppointment(
            @PathVariable UUID id,
            @RequestBody Appointment appointment) {

        return ResponseEntity.ok(
                appointmentService.updateAppointment(id, appointment));
    }

    // Delete appointment
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable UUID id) {
        return ResponseEntity.ok(
                appointmentService.deleteAppointmentById(id));
    }
   
    @GetMapping("/pagination")
    public ResponseEntity<?> getAppointments(Pageable pageable){
        return ResponseEntity.ok(
               appointmentService.getAppointmentsPagination(pageable));
    }

    @GetMapping("/sort/{field}")
    public ResponseEntity<?> sortAppointments(@PathVariable String field){
        return ResponseEntity.ok(
               appointmentService.getAppointmentsSorted(field));
    }

}
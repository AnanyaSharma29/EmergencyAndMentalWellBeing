package com.EmergencyAndMentalWellBeing.Backend.Controller;

import com.EmergencyAndMentalWellBeing.Backend.Model.Appointment;
import com.EmergencyAndMentalWellBeing.Backend.Model.AppointmentStatus;
import com.EmergencyAndMentalWellBeing.Backend.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Endpoint to get all pending appointments for a consultant
    @GetMapping("/pending/{consultantEmail}")
    public ResponseEntity<List<Appointment>> getPendingAppointments(@PathVariable String consultantEmail) {
        List<Appointment> appointments = appointmentService.getPendingAppointmentsForConsultant(consultantEmail);
        if (appointments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/accepted/{consultantEmail}")
    public ResponseEntity<List<Appointment>> getAcceptedAppointments(@PathVariable String consultantEmail) {
        List<Appointment> appointments = appointmentService.getAcceptedAppointmentsForConsultant(consultantEmail);
        if (appointments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/status")
    public ResponseEntity<List<Appointment>> getAppointmentsByUserEmail(@RequestParam String email) {
        List<Appointment> appointments = appointmentService.getAppointmentsByEmail(email);
        if (appointments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(appointments);
    }


    // Endpoint to update appointment status
    @PostMapping("/status/{consultantEmail}")
    public ResponseEntity<Appointment> updateAppointmentStatus(
            @PathVariable String consultantEmail,
            @RequestParam AppointmentStatus status) {
        try {
            Appointment updatedAppointment = appointmentService.updateAppointmentStatusByEmail(consultantEmail, status);
            return ResponseEntity.ok(updatedAppointment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @PostMapping("/book")
    public ResponseEntity<Appointment> bookAppointment(@RequestBody Appointment appointment) {
        try {
            Appointment createdAppointment = appointmentService.createAppointment(appointment);
            return ResponseEntity.ok(createdAppointment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}

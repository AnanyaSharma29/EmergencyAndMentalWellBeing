package com.EmergencyAndMentalWellBeing.Backend.Service;

import com.EmergencyAndMentalWellBeing.Backend.Model.Appointment;
import com.EmergencyAndMentalWellBeing.Backend.Model.AppointmentStatus;
import com.EmergencyAndMentalWellBeing.Backend.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Create a new appointment request
    public Appointment createAppointment(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.PENDING); // Set the initial status to PENDING
        return appointmentRepository.save(appointment);
    }

    // Get all pending appointments for a consultant by email
    public List<Appointment> getPendingAppointmentsForConsultant(String consultantEmail) {
        return appointmentRepository.findByConsultantEmailAndStatus(consultantEmail, AppointmentStatus.PENDING);
    }

    public List<Appointment> getAcceptedAppointmentsForConsultant(String consultantEmail) {
        return appointmentRepository.findByConsultantEmailAndStatus(consultantEmail, AppointmentStatus.ACCEPTED);
    }

    // Update appointment status (Accepted or Rejected)
    public Appointment updateAppointmentStatusByEmail(String consultantEmail, AppointmentStatus status) {
        List<Appointment> appointments = appointmentRepository.findByConsultantEmailAndStatus(consultantEmail, AppointmentStatus.PENDING);

        if (appointments.isEmpty()) {
            throw new RuntimeException("No pending appointments found for consultant email: " + consultantEmail);
        }

        // Update the status of the first matching appointment
        Appointment appointmentToUpdate = appointments.get(0);
        appointmentToUpdate.setStatus(status);
        return appointmentRepository.save(appointmentToUpdate);
    }

    public List<Appointment> getAppointmentsByEmail(String email) {
        return appointmentRepository.findByUserEmail(email);
    }


}

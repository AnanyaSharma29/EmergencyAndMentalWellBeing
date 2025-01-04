package com.EmergencyAndMentalWellBeing.Backend.Repository;


import com.EmergencyAndMentalWellBeing.Backend.Model.Appointment;
import com.EmergencyAndMentalWellBeing.Backend.Model.AppointmentStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    public Optional<Appointment> findById(String id);

    List<Appointment> findByConsultantEmailAndStatus(String consultantEmail, AppointmentStatus status);

    List<Appointment> findByUserEmail(String email);
}

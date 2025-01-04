package com.EmergencyAndMentalWellBeing.Backend.Service;

import com.EmergencyAndMentalWellBeing.Backend.Model.Consultant;
import com.EmergencyAndMentalWellBeing.Backend.Repository.ConsultantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultantService {

    @Autowired
    private ConsultantRepo consultantRepository;

    // Consultant Registration
    public String registerConsultant(Consultant consultant) {
        if (consultant.getFirstname() == null || consultant.getFirstname().isEmpty() ||
                consultant.getLastname() == null || consultant.getLastname().isEmpty() ||
                consultant.getEmail() == null || consultant.getEmail().isEmpty() ||
                consultant.getPhonenumber() == null || consultant.getPhonenumber().isEmpty() ||
                consultant.getPassword() == null || consultant.getPassword().isEmpty() ||
                consultant.getConfirmPassword() == null || consultant.getConfirmPassword().isEmpty() ||
                consultant.getSpecialization() == null || consultant.getSpecialization().isEmpty() ||
                consultant.getLicenseNumber() == null || consultant.getLicenseNumber().isEmpty()) {
            return "All fields are required.";
        }

        // Check if email already exists
        if (consultantRepository.findByEmail(consultant.getEmail()) != null) {
            return "Email already exists.";
        }

        // Check if passwords match
        if (!consultant.getPassword().equals(consultant.getConfirmPassword())) {
            return "Passwords do not match.";
        }

        // Save the consultant to the database
        consultantRepository.save(consultant);
        return "Consultant registered successfully.";
    }

    // Consultant Login
    public String loginConsultant(String email, String password) {
        Consultant consultant = consultantRepository.findByEmail(email);

        if (consultant == null || !consultant.getPassword().equals(password)) {
            return "Invalid email or password.";
        }

        return "Consultant login successful.";
    }

    // Update consultant details
    public String updateConsultant(String email, Consultant updatedConsultant) {
        // Find consultant by email
        Consultant existingConsultant = consultantRepository.findByEmail(email);

        if (existingConsultant == null) {
            return "Consultant not found.";
        }

        // Update fields
        if (updatedConsultant.getFirstname() != null && !updatedConsultant.getFirstname().isEmpty()) {
            existingConsultant.setFirstname(updatedConsultant.getFirstname());
        }
        if (updatedConsultant.getLastname() != null && !updatedConsultant.getLastname().isEmpty()) {
            existingConsultant.setLastname(updatedConsultant.getLastname());
        }
        if (updatedConsultant.getPhonenumber() != null && !updatedConsultant.getPhonenumber().isEmpty()) {
            existingConsultant.setPhonenumber(updatedConsultant.getPhonenumber());
        }
        if (updatedConsultant.getEmail() != null && !updatedConsultant.getEmail().isEmpty()) {
            existingConsultant.setEmail(updatedConsultant.getEmail());
        }
        if (updatedConsultant.getSpecialization() != null && !updatedConsultant.getSpecialization().isEmpty()) {
            existingConsultant.setSpecialization(updatedConsultant.getSpecialization());
        }
        if (updatedConsultant.getLicenseNumber() != null && !updatedConsultant.getLicenseNumber().isEmpty()) {
            existingConsultant.setLicenseNumber(updatedConsultant.getLicenseNumber());
        }

        // Save updated consultant to the database
        consultantRepository.save(existingConsultant);
        return "Consultant details updated successfully.";
    }
}

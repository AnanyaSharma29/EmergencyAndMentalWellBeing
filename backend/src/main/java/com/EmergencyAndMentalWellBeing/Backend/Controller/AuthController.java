package com.EmergencyAndMentalWellBeing.Backend.Controller;

import com.EmergencyAndMentalWellBeing.Backend.Model.Users;
import com.EmergencyAndMentalWellBeing.Backend.Model.Consultant;
import com.EmergencyAndMentalWellBeing.Backend.Repository.UserRepo;
import com.EmergencyAndMentalWellBeing.Backend.Repository.ConsultantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ConsultantRepo consultantRepository;

    // User Registration API
    @PostMapping("/register/user")
    public ResponseEntity<?> registerUser(@RequestBody Users user) {
        // Check if all required fields are present
        if (user.getFirstname() == null || user.getFirstname().isEmpty() ||
                user.getLastname() == null || user.getLastname().isEmpty() ||
                user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getPhonenumber() == null || user.getPhonenumber().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty() ||
                user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("All fields are required.");
        }

        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already exists.");
        }

        // Check if passwords match
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match.");
        }

        // Save the user to the database
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully.");
    }

    // Consultant Registration API
    @PostMapping("/register/consultant")
    public ResponseEntity<?> registerConsultant(@RequestBody Consultant consultant) {
        // Check if all required fields are present
        if (consultant.getFirstname() == null || consultant.getFirstname().isEmpty() ||
                consultant.getLastname() == null || consultant.getLastname().isEmpty() ||
                consultant.getEmail() == null || consultant.getEmail().isEmpty() ||
                consultant.getPhonenumber() == null || consultant.getPhonenumber().isEmpty() ||
                consultant.getPassword() == null || consultant.getPassword().isEmpty() ||
                consultant.getConfirmPassword() == null || consultant.getConfirmPassword().isEmpty() ||
                consultant.getSpecialization() == null || consultant.getSpecialization().isEmpty() ||
                consultant.getLicenseNumber() == null || consultant.getLicenseNumber().isEmpty()) {
            return ResponseEntity.badRequest().body("All fields are required.");
        }

        // Check if email already exists
        if (consultantRepository.findByEmail(consultant.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already exists.");
        }

        // Check if passwords match
        if (!consultant.getPassword().equals(consultant.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match.");
        }

        // Save the consultant to the database
        consultantRepository.save(consultant);
        return ResponseEntity.ok("Consultant registered successfully.");
    }

    // Login API for User
    @PostMapping("/login/user")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginDetails) {
        String email = loginDetails.get("email");
        String password = loginDetails.get("password");

        // Find user by email
        Users user = userRepository.findByEmail(email);

        if (user == null || !user.getPassword().equals(password)) {
            return ResponseEntity.badRequest().body("Invalid email or password.");
        }

        // Successful login for user
        Map<String, String> response = new HashMap<>();
        response.put("message", "User login successful.");

        return ResponseEntity.ok(response);
    }

    // Login API for Consultant
    @PostMapping("/login/consultant")
    public ResponseEntity<?> loginConsultant(@RequestBody Map<String, String> loginDetails) {
        String email = loginDetails.get("email");
        String password = loginDetails.get("password");

        // Find consultant by email
        Consultant consultant = consultantRepository.findByEmail(email);

        if (consultant == null || !consultant.getPassword().equals(password)) {
            return ResponseEntity.badRequest().body("Invalid email or password.");
        }

        // Successful login for consultant
        Map<String, String> response = new HashMap<>();
        response.put("message", "Consultant login successful.");
        response.put("specialization", consultant.getSpecialization());
        response.put("licenseNumber", consultant.getLicenseNumber());

        return ResponseEntity.ok(response);
    }
}

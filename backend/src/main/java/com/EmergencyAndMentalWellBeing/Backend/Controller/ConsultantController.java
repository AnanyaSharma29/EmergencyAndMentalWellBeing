package com.EmergencyAndMentalWellBeing.Backend.Controller;

import com.EmergencyAndMentalWellBeing.Backend.Model.Consultant;
import com.EmergencyAndMentalWellBeing.Backend.Repository.ConsultantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/consultants")
public class ConsultantController {

    @Autowired
    private ConsultantRepo consultantRepo;

    // Endpoint to fetch all consultants
    @GetMapping("/all")
    public List<Consultant> getAllConsultants() {
        return consultantRepo.findAll();  // Retrieve all consultants from MongoDB
    }

    // Endpoint to fetch a consultant by email
    @GetMapping("/email/{email}")
    public ResponseEntity<Consultant> getConsultantByEmail(@PathVariable String email) {
        Consultant consultant = consultantRepo.findByEmail(email); // Find by email
        if (consultant != null) {
            return ResponseEntity.ok(consultant);  // Return the consultant if found
        } else {
            return ResponseEntity.notFound().build();  // Return 404 if consultant not found
        }
    }
}

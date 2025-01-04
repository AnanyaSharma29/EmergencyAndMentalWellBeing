package com.EmergencyAndMentalWellBeing.Backend.Controller;
import com.EmergencyAndMentalWellBeing.Backend.Model.AssessmentRequest;

import com.EmergencyAndMentalWellBeing.Backend.Model.Users;
import com.EmergencyAndMentalWellBeing.Backend.Service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assessment")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    // Add a new assessment score
    @PostMapping("/{email}")
    public ResponseEntity<String> addScore(@PathVariable String email, @RequestBody AssessmentRequest request) {
        Users updatedUser = assessmentService.saveAssessmentScore(email, request.getTotalScore(), request.getFeedback());
        if (updatedUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Assessment score added successfully for user: " + email);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User not found with email: " + email);
    }

    // Get all assessment scores
    @GetMapping("/{email}")
    public ResponseEntity<Object> getScores(@PathVariable String email) {
        Users user = assessmentService.getAssessmentScores(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User not found with email: " + email);
    }
}

package com.EmergencyAndMentalWellBeing.Backend.Controller;

import com.EmergencyAndMentalWellBeing.Backend.Model.Subscription;
import com.EmergencyAndMentalWellBeing.Backend.Service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody String email) {
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("Email address is required.");
        }

        // Subscribe the user
        Subscription subscription = subscriptionService.subscribe(email);
        if (subscription != null) {
            return ResponseEntity.ok("You have successfully subscribed to receive motivational thoughts!");
        } else {
            return ResponseEntity.status(500).body("Something went wrong. Please try again later.");
        }
    }
}

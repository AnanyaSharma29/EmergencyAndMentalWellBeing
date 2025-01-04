package com.EmergencyAndMentalWellBeing.Backend.Controller;
import com.EmergencyAndMentalWellBeing.Backend.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    // Endpoint to send SOS alert
    @PostMapping("/sendSOS")
    public String sendSOSAlert(@RequestParam String email, @RequestParam double lat, @RequestParam double lon) {
        emailService.sendSOSAlertEmail(email, lat, lon);
        return "SOS alert sent to: " + email;
    }

    // Endpoint to send daily motivational thought
    @PostMapping("/sendMotivationalThought")
    public String sendMotivationalThought(@RequestParam String email, @RequestParam String thought) {
        emailService.sendMotivationalThought(email, thought);
        return "Motivational thought sent to: " + email;
    }
}

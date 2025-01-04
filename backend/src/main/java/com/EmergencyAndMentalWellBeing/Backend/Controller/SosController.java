package com.EmergencyAndMentalWellBeing.Backend.Controller;

import com.EmergencyAndMentalWellBeing.Backend.Service.SosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/send-sos")
public class SosController {

    @Autowired
    private SosService sosService;

    @GetMapping
    public ResponseEntity<String> sendSos(
            @RequestParam String phoneNumber,
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam String address) {

        try {
            boolean success = sosService.sendSosSms(phoneNumber, latitude, longitude, address);
            if (success) {
                return ResponseEntity.ok("SOS alert sent successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send SOS alert");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending SOS alert");
        }
    }
}
//public class SosController {
//
//    @Autowired
//    private SosService sosService;
//
//    @GetMapping("/send-sos")
//    public ResponseEntity<String> sendSos(@RequestParam String phoneNumber) {
//        System.out.println("Phone number from request: " + phoneNumber);  // Log it to check if it's correct
//        boolean success = sosService.sendSosSms(phoneNumber);
//        if (success) {
//            return ResponseEntity.ok("SOS alert sent!");
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send SOS alert. Please try again.");
//        }
//    }
//
//}

package com.EmergencyAndMentalWellBeing.Backend.Controller;

import com.EmergencyAndMentalWellBeing.Backend.Model.Contact;
import com.EmergencyAndMentalWellBeing.Backend.Service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/emergency-contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    // 1. Get All Contacts for a Specific User
    @GetMapping("/{userEmail}")
    public ResponseEntity<List<Contact>> getContactsForUser(@PathVariable String userEmail) {
        List<Contact> contacts = contactService.getContactsForUser(userEmail);
        if (contacts.isEmpty()) {
            log.warn("No contacts found for user with email: {}", userEmail);
            return ResponseEntity.noContent().build();
        }
        log.info("Fetched {} contacts for user: {}", contacts.size(), userEmail);
        return ResponseEntity.ok(contacts);
    }

    // 2. Add a New Contact for a Specific User
    @PostMapping("/{userEmail}")
    public ResponseEntity<Contact> addContact(@PathVariable String userEmail, @RequestBody Contact contact) {
        // Custom Validation
        if (contact == null || contact.getName().isEmpty() || contact.getContactNumber().length() < 10 || !contact.getEmail().contains("@")) {
            log.warn("Invalid contact data received: {}", contact);
            return ResponseEntity.badRequest().body(null);
        }

        contact.setUserEmail(userEmail);  // Associate the contact with the userEmail
        Contact savedContact = contactService.addContact(contact);
        log.info("New contact added for user {}: {}", userEmail, contact.getName());
        return ResponseEntity.ok(savedContact);
    }

    // 3. Delete a Contact for a Specific User
    @DeleteMapping("/{userEmail}/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable String userEmail, @PathVariable String id) {
        boolean isDeleted = contactService.deleteContact(userEmail, id);
        if (isDeleted) {
            log.info("Contact with ID {} deleted successfully for user {}", id, userEmail);
            return ResponseEntity.ok("Contact deleted successfully.");
        } else {
            log.warn("Contact with ID {} not found or could not be deleted for user {}", id, userEmail);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found or could not be deleted.");
        }
    }

    // 4. Send SOS Alert to All Contacts of a Specific User
    @PostMapping("/send-sos/{userEmail}")
    public ResponseEntity<String> sendSosAlertToContacts(@PathVariable String userEmail, @RequestBody String message) {
        if (message == null || message.trim().isEmpty()) {
            log.warn("SOS message is empty.");
            return ResponseEntity.badRequest().body("Message cannot be empty.");
        }

        contactService.sendSosToUserContacts(userEmail, message);
        log.info("SOS message sent to all contacts of user {}", userEmail);
        return ResponseEntity.ok("SOS sent to all emergency contacts.");
    }
}

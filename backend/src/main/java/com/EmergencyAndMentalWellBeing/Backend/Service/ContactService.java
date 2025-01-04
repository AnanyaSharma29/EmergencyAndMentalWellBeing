package com.EmergencyAndMentalWellBeing.Backend.Service;

import com.EmergencyAndMentalWellBeing.Backend.Model.Contact;
import com.EmergencyAndMentalWellBeing.Backend.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    // Get all contacts
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    // Get contacts for a specific user based on their email
    public List<Contact> getContactsForUser(String userEmail) {
        return contactRepository.findByUserEmail(userEmail);  // Assumes findByUserEmail is correctly defined in the repository
    }

    // Add new contact
    public Contact addContact(Contact contact) {
        if (contact == null || contact.getName() == null || contact.getContactNumber() == null || contact.getEmail() == null) {
            throw new IllegalArgumentException("Invalid contact data: All fields are required.");
        }

        if (isInvalidPhoneNumber(contact.getContactNumber())) {
            throw new IllegalArgumentException("Invalid phone number format.");
        }

        try {
            return contactRepository.save(contact);
        } catch (Exception e) {
            throw new RuntimeException("Database error occurred while saving the contact.");
        }
    }

    // Delete contact by ID and user email
    public boolean deleteContact(String userEmail, String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty.");
        }

        return contactRepository.findById(id)
                .filter(contact -> contact.getUserEmail().equals(userEmail))  // Check if the contact belongs to the user
                .map(contact -> {
                    contactRepository.delete(contact);
                    return true;
                })
                .orElse(false);
    }

    // Dummy method for sending an SOS message to all contacts
    public String sendSosToUserContacts(String userEmail, String sosMessage) {
        if (sosMessage == null || sosMessage.trim().isEmpty()) {
            throw new IllegalArgumentException("SOS message cannot be empty.");
        }

        List<Contact> contacts = getContactsForUser(userEmail);  // Fetch contacts for the user
        if (contacts.isEmpty()) {
            return "No contacts available for this user.";
        }

        for (Contact contact : contacts) {
            String contactNumber = contact.getContactNumber();
            if (isInvalidPhoneNumber(contactNumber)) {
                continue;
            }

            // Simulate sending the SOS message
        }

        return "SOS message simulation: Sent to all available contacts.";
    }

    // Utility method to validate phone numbers
    private boolean isInvalidPhoneNumber(String phoneNumber) {
        return phoneNumber == null || !phoneNumber.matches("\\d{10,15}");
    }

}

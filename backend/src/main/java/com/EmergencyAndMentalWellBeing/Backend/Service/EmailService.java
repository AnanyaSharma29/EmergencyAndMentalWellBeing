package com.EmergencyAndMentalWellBeing.Backend.Service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    // Method to send SOS Alert Email
    public void sendSOSAlertEmail(String to, double lat, double lon) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("SOS Alert");

        // Create the Google Maps link
        String googleMapsLink = "https://www.google.com/maps?q=" + lat + "," + lon;

        // Construct the message with the location and Google Maps link
        String messageBody = "SOS Alert! Emergency at the following location:\n" +
                "Latitude: " + lat + "\n" +
                "Longitude: " + lon + "\n\n" +
                "View the location on Google Maps: " + googleMapsLink;

        message.setText(messageBody);
        emailSender.send(message); // Send the SOS alert email
    }

    // Method to send Motivational Thought Email
    public void sendMotivationalThought(String toEmail, String thought) {
        try {
            // Create a MimeMessage to send the email
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);  // 'true' enables multipart/HTML content

            // Set up the email details
            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject("Your Daily Motivational Thought");
            helper.setText(thought, true);  // true allows HTML content if needed

            // Send the email
            emailSender.send(message); // Send the message
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

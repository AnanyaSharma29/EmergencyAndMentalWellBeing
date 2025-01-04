package com.EmergencyAndMentalWellBeing.Backend.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SosService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    public boolean sendSosSms(String phoneNumber, double latitude, double longitude, String address) {
        try {
            Twilio.init(accountSid, authToken);

            // Log the phone number and additional details for debugging
            System.out.println("Phone number received: " + phoneNumber);
            System.out.println("Location received: Latitude=" + latitude + ", Longitude=" + longitude);
            System.out.println("Address received: " + address);

            // Ensure the phone number starts with the '+' sign (if not, prepend it)
            if (!phoneNumber.startsWith("+")) {
                phoneNumber = "+" + phoneNumber; // Add '+' if missing
            }

            // Construct the message body with Google Maps link
            String googleMapsLink = String.format("https://www.google.com/maps?q=%f,%f", latitude, longitude);
            String messageBody = String.format(
                    "I'm in emergency! Address: %s, Latitude: %.4f, Longitude: %.4f. View location: %s",
                    address, latitude, longitude, googleMapsLink);

            // Create and send the message
            Message message = Message.creator(
                    new PhoneNumber(phoneNumber),  // recipient's phone number (from the request)
                    new PhoneNumber(twilioPhoneNumber),  // your Twilio phone number
                    messageBody  // SMS body
            ).create();

            System.out.println("SOS Alert sent successfully! Message SID: " + message.getSid());
            return true;
        } catch (Exception e) {
            System.err.println("Error sending SOS alert: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
























//public class SosService {
//
//    @Value("${twilio.account.sid}")
//    private String accountSid;
//
//    @Value("${twilio.auth.token}")
//    private String authToken;
//
//    @Value("${twilio.phone.number}")
//    private String twilioPhoneNumber;
//
//    public boolean sendSosSms(String phoneNumber) {
//        try {
//            Twilio.init(accountSid, authToken);
//
//            // Log the phone number to confirm it's being passed correctly
//            System.out.println("Phone number received: " + phoneNumber);
//
//            // Make sure the phone number starts with the '+' sign (if not, prepend it)
//            if (!phoneNumber.startsWith("+")) {
//                phoneNumber = "+" + phoneNumber;  // Add '+' if missing
//            }
//
//            // Create and send the message
//            Message message = Message.creator(
//                    new PhoneNumber(phoneNumber),  // recipient's phone number (from the request)
//                    new PhoneNumber(twilioPhoneNumber),  // your Twilio phone number
//                    "I'm in emergency!"  // SMS body
//            ).create();
//
//            System.out.println("SOS Alert sent successfully! Message SID: " + message.getSid());
//            return true;
//        } catch (Exception e) {
//            System.err.println("Error sending SOS alert: " + e.getMessage());
//            e.printStackTrace();
//            return false;
//        }
//    }
//}

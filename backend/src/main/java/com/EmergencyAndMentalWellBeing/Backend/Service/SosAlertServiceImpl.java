//package com.EmergencyAndMentalWellBeing.Backend.Service;
//
//import com.EmergencyAndMentalWellBeing.Backend.Model.SosAlert;
//import com.EmergencyAndMentalWellBeing.Backend.Service.SosAlertService;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//
//@Service
//public class SosAlertServiceImpl implements SosAlertService {
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
//    @Override
//    public boolean sendSosNotification(SosAlert sosAlert) {
//        try {
//            Twilio.init(accountSid, authToken);
//
//            // Include location in the SOS message if latitude and longitude are provided
//            String messageBody = sosAlert.getMessage();
//            if (sosAlert.getLatitude() != null && sosAlert.getLongitude() != null) {
//                messageBody += "\nLocation: https://www.google.com/maps?q="
//                        + sosAlert.getLatitude() + "," + sosAlert.getLongitude();
//            }
//
//            Message message = Message.creator(
//                    new com.twilio.type.PhoneNumber(sosAlert.getPhoneNumber()),
//                    new com.twilio.type.PhoneNumber(twilioPhoneNumber),
//                    messageBody
//            ).create();
//
//            System.out.println("SOS Alert sent successfully! Message SID: " + message.getSid());
//            return true;
//        } catch (Exception e) {
//            System.err.println("Error sending SOS alert: " + e.getMessage());
//            return false;
//        }
//    }
//
//}

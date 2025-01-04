package com.EmergencyAndMentalWellBeing.Backend.Service;

import com.EmergencyAndMentalWellBeing.Backend.Model.Users;
import com.EmergencyAndMentalWellBeing.Backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduledEmailService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepo userRepository;  // Assuming you have a UserRepository to fetch users

    // This method will run every day at 8:00 AM to send a motivational email
    @Scheduled(cron = "0 0 8 * * ?") // This cron expression means every day at 8 AM
    public void sendDailyMotivationalThought() {
        // Get all subscribed users from the database
        List<Users> users = userRepository.findAll();  // Replace this with your actual user fetching logic

        // Define the motivational thought
        String thought = "Keep pushing forward! You're doing great!";

        // Loop over each user and send the email
        for (Users user : users) {
            String email = user.getEmail();  // Get user's email
            emailService.sendMotivationalThought(email, thought);  // Send email to the user
        }
    }
}

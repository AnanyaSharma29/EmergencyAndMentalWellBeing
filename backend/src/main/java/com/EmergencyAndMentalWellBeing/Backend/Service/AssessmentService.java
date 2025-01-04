package com.EmergencyAndMentalWellBeing.Backend.Service;

import com.EmergencyAndMentalWellBeing.Backend.Model.Users;
import com.EmergencyAndMentalWellBeing.Backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class AssessmentService {
    @Autowired
    private UserRepo userRepo;

    /**
     * Save an assessment score for a user.
     *
     * @param email      The email of the user.
     * @param totalScore The total score of the assessment.
     * @param feedback   Feedback related to the assessment.
     * @return The updated user object after saving the score or null if the user doesn't exist.
     */
    public Users saveAssessmentScore(String email, int totalScore, String feedback) {
        Users user = userRepo.findByEmail(email);
        if (user != null) {
            // Create a new assessment score with LocalDateTime for the date.
            Users.AssessmentScore newScore = new Users.AssessmentScore(
                    totalScore,
                    feedback,
                    LocalDateTime.now() // Use LocalDateTime directly
            );
            // Add the new score to the user's list of scores.
            user.getAssessmentScores().add(newScore);
            // Save the updated user object to the repository.
            return userRepo.save(user);
        }
        return null; // Return null if the user is not found.
    }

    /**
     * Retrieve assessment scores for a user.
     *
     * @param email The email of the user.
     * @return The user object containing their assessment scores or null if the user doesn't exist.
     */
    public Users getAssessmentScores(String email) {
        return userRepo.findByEmail(email);
    }
}

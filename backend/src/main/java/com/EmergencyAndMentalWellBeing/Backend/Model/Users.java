package com.EmergencyAndMentalWellBeing.Backend.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Document(collection = "Users")
public class Users {

    // Getters and setters for user fields
    @Id
    private String userId;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String phonenumber;
    private String password;
    private String confirmPassword;

    // Getters and setters for assessment scores
    // Field to store self-assessment scores
    private List<AssessmentScore> assessmentScores = new ArrayList<>();

    // Inner class to represent individual assessment scores
    public static class AssessmentScore {
        private int totalScore;
        private String feedback;
        private LocalDateTime timestamp;

        // Constructors


        public AssessmentScore(int totalScore, String feedback, LocalDateTime timestamp) {
            this.totalScore = totalScore;
            this.feedback = feedback;
            this.timestamp = timestamp;
        }

        // Getters and setters
        public int getTotalScore() { return totalScore; }
        public void setTotalScore(int totalScore) { this.totalScore = totalScore; }

        public String getFeedback() { return feedback; }
        public void setFeedback(String feedback) { this.feedback = feedback; }

        public LocalDateTime getTimestamp() { return timestamp; }
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    }

    // Constructors
    public Users() {}

    public Users(String firstname, String lastname, String username, String email, String phonenumber, String password, String confirmPassword) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

}

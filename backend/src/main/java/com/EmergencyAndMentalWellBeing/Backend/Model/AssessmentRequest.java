package com.EmergencyAndMentalWellBeing.Backend.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "score")
public class AssessmentRequest {
    private int totalScore;
    private String feedback;

    // Constructor
    public AssessmentRequest() {}

    // Getters and Setters
    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}


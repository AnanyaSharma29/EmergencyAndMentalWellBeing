package com.EmergencyAndMentalWellBeing.Backend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "appointments")
public class Appointment {

    @Id
    private String id;

    private String userId;
    private String consultantEmail;  // Consultant's email to identify the consultant
    private LocalDate date;
    private LocalTime time;

    private AppointmentStatus status; // PENDING, ACCEPTED, REJECTED
    private String name;



    private String userEmail;
    // Constructors, Getters and Setters
    public Appointment() {}

    public Appointment(String userId,String userEmail,String name, String consultantEmail, LocalDate date, LocalTime time, AppointmentStatus status) {
        this.userId = userId;
        this.consultantEmail = consultantEmail;
        this.date = date;
        this.time = time;
        this.status = status;
        this.name = name;
        this.userEmail = userEmail;
    }

    // Getters and setters

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getConsultantEmail() {  // Fixed method name
        return consultantEmail;
    }

    public void setConsultantEmail(String consultantEmail) {  // Fixed method name
        this.consultantEmail = consultantEmail;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}

package com.EmergencyAndMentalWellBeing.Backend.Service;

import com.EmergencyAndMentalWellBeing.Backend.Model.Users;
import com.EmergencyAndMentalWellBeing.Backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    // User Registration
    public String registerUser(Users user) {
        if (user.getFirstname() == null || user.getFirstname().isEmpty() ||
                user.getLastname() == null || user.getLastname().isEmpty() ||
                user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getPhonenumber() == null || user.getPhonenumber().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty() ||
                user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
            return "All fields are required.";
        }

        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "Email already exists.";
        }

        // Check if passwords match
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "Passwords do not match.";
        }

        // Save the user to the database
        userRepository.save(user);
        return "User registered successfully.";
    }

    // User Login
    public String loginUser(String email, String password) {
        Users user = userRepository.findByEmail(email);

        if (user == null || !user.getPassword().equals(password)) {
            return "Invalid email or password.";
        }

        return "User login successful.";
    }
    // Update user details
    public String updateUser(String email, Users updatedUser) {
        // Find user by email
        Users existingUser = userRepository.findByEmail(email);

        if (existingUser == null) {
            return "User not found.";
        }

        // Update fields
        if (updatedUser.getFirstname() != null && !updatedUser.getFirstname().isEmpty()) {
            existingUser.setFirstname(updatedUser.getFirstname());
        }
        if (updatedUser.getLastname() != null && !updatedUser.getLastname().isEmpty()) {
            existingUser.setLastname(updatedUser.getLastname());
        }
        if (updatedUser.getPhonenumber() != null && !updatedUser.getPhonenumber().isEmpty()) {
            existingUser.setPhonenumber(updatedUser.getPhonenumber());
        }
        if (updatedUser.getEmail() != null && !updatedUser.getEmail().isEmpty()) {
            existingUser.setEmail(updatedUser.getEmail());
        }

        // Save updated user to the database
        userRepository.save(existingUser);
        return "User details updated successfully.";
    }

    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email); // Assumes you have a repository method for this
    }

}

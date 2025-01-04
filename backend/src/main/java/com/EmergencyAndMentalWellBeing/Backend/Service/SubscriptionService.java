package com.EmergencyAndMentalWellBeing.Backend.Service;
import com.EmergencyAndMentalWellBeing.Backend.Model.Subscription;
import com.EmergencyAndMentalWellBeing.Backend.Repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public Subscription subscribe(String email) {
        // Create a new subscription entry
        Subscription subscription = new Subscription(email);
        return subscriptionRepository.save(subscription);
    }
}

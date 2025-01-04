package com.EmergencyAndMentalWellBeing.Backend.Repository;


import com.EmergencyAndMentalWellBeing.Backend.Model.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> {
    // Additional query methods if needed can be added here
}

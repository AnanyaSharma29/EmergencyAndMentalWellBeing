package com.EmergencyAndMentalWellBeing.Backend.Repository;

import com.EmergencyAndMentalWellBeing.Backend.Model.Consultant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultantRepo extends MongoRepository<Consultant, String> {
    Consultant findByEmail(String email);
}

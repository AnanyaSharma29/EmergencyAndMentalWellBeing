package com.EmergencyAndMentalWellBeing.Backend.Repository;

import com.EmergencyAndMentalWellBeing.Backend.Model.ExerciseTask;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExerciseTaskRepository extends MongoRepository<ExerciseTask, String> {
}

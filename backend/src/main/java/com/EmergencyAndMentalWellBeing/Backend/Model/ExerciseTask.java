package com.EmergencyAndMentalWellBeing.Backend.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "exercise_tasks")
public class ExerciseTask {

    private String id;
    private String name;
    private String description;
    private boolean done;

    // Getters and Setters

}

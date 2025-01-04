package com.EmergencyAndMentalWellBeing.Backend.Service;

import com.EmergencyAndMentalWellBeing.Backend.Model.ExerciseTask;
import com.EmergencyAndMentalWellBeing.Backend.Repository.ExerciseTaskRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseTaskService {

    @Autowired
    private ExerciseTaskRepository exerciseTaskRepository;

    public List<ExerciseTask> getAllTasks() {
        return exerciseTaskRepository.findAll();
    }

    public ExerciseTask addTask(ExerciseTask task) {
        return exerciseTaskRepository.save(task);
    }

    public ExerciseTask updateTask(String id, ExerciseTask task) {
        Optional<ExerciseTask> existingTask = exerciseTaskRepository.findById(id);
        if (existingTask.isPresent()) {
            ExerciseTask updatedTask = existingTask.get();
            updatedTask.setName(task.getName());
            updatedTask.setDescription(task.getDescription());
            return exerciseTaskRepository.save(updatedTask);
        }
        return null;
    }

    public ExerciseTask updateTaskStatus(String id, boolean doneStatus) {
        Optional<ExerciseTask> task = exerciseTaskRepository.findById(id);
        if (task.isPresent()) {
            ExerciseTask updatedTask = task.get();
            updatedTask.setDone(doneStatus);
            return exerciseTaskRepository.save(updatedTask);
        }
        return null;
    }

    public void deleteTask(String id) {
        exerciseTaskRepository.deleteById(id);
    }
}
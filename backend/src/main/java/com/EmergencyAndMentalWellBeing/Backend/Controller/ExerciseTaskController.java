package com.EmergencyAndMentalWellBeing.Backend.Controller;

import com.EmergencyAndMentalWellBeing.Backend.Model.ExerciseTask;
import com.EmergencyAndMentalWellBeing.Backend.Service.ExerciseTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercise-tasks")
@CrossOrigin(origins = "http://localhost:3000")  // Allow CORS from React frontend
public class ExerciseTaskController {

    @Autowired
    private ExerciseTaskService exerciseTaskService;

    @GetMapping
    public List<ExerciseTask> getAllTasks() {
        return exerciseTaskService.getAllTasks();
    }

    @PostMapping
    public ExerciseTask addTask(@RequestBody ExerciseTask task) {
        return exerciseTaskService.addTask(task);
    }

    @PutMapping("/{id}")
    public ExerciseTask updateTask(@PathVariable String id, @RequestBody ExerciseTask task) {
        return exerciseTaskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        exerciseTaskService.deleteTask(id);
    }
}

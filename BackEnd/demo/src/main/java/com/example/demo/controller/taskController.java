package com.example.demo.controller;

import com.example.demo.model.RepeatingTasks;
import com.example.demo.model.TaskResponseDTO;
import com.example.demo.services.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:5173")
public class taskController {
    private final Service service;

    public taskController(Service service){ this.service = service;}

    @GetMapping
    public List<TaskResponseDTO> getTasks()
    {
        return service.getAllTasksWithDays();
    }

    @PostMapping
    public ResponseEntity<RepeatingTasks> addTask(@RequestBody RepeatingTasks tasks)
    {
        service.AddTask(tasks);
        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping("/{titleName}")
    public ResponseEntity<String> removeTasks(@PathVariable String titleName)
    {
        service.removeTask(titleName);
        return ResponseEntity.ok(titleName);
    }

    @PatchMapping("/{changedTitle}/{oldTitle}")
    public ResponseEntity<String> updateTaskTitle(@PathVariable String changedTitle, @PathVariable String oldTitle)
    {
        service.changeTaskTitleTasks(changedTitle, oldTitle);
        return ResponseEntity.ok(changedTitle);
    }

    @PatchMapping("/{day}")
    public ResponseEntity<String> resetStatus(@PathVariable String day)
    {
        service.resetStatus(day);
        return ResponseEntity.ok(day);
    }
}

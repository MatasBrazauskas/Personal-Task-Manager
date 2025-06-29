package com.example.demo.controller;

import com.example.demo.model.TaskCompletionDTO;
import com.example.demo.model.Tasks;
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

    @GetMapping("/{day}")
    public List<TaskCompletionDTO> getTasksToComplete(@PathVariable String day)
    {
        return service.getTasksToComplete(day);
    }

    @PostMapping
    public ResponseEntity<Tasks> addTask(@RequestBody Tasks tasks)
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
}

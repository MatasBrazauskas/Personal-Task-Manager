package com.example.demo.controller;

import com.example.demo.model.RepeatingTasks;
import com.example.demo.model.TaskResponseDTO;
import com.example.demo.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:5173")
public class taskController {
    private final TaskService taskService;

    public taskController(TaskService taskService){ this.taskService = taskService;}

    @GetMapping
    public List<TaskResponseDTO> getTasks()
    {
        return taskService.getAllTasksWithDays();
    }

    @PostMapping
    public ResponseEntity<RepeatingTasks> addTask(@RequestBody RepeatingTasks tasks)
    {
        taskService.AddTask(tasks);
        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping("/{titleName}")
    public ResponseEntity<String> removeTasks(@PathVariable String titleName)
    {
        taskService.removeTask(titleName);
        return ResponseEntity.ok(titleName);
    }

    @PatchMapping("/{changedTitle}/{oldTitle}")
    public ResponseEntity<String> updateTaskTitle(@PathVariable String changedTitle, @PathVariable String oldTitle)
    {
        taskService.changeTaskTitleTasks(changedTitle, oldTitle);
        return ResponseEntity.ok(changedTitle);
    }
}

package com.example.demo.controller;

import com.example.demo.model.Entities;
import com.example.demo.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Entities> getTasks() {
        return taskRepository.getAllTasks();
    }

    @GetMapping("/exist")
    public boolean isTitleDuplicate(@RequestParam("title") String title){
        return taskRepository.existsByTitle(title) > 0;
    }

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody Entities task) {
        System.out.println(task.getDueDate());
        taskRepository.insertTask(task.getTitle(), task.getStatus(), task.getDueDate());
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/{title}")
    public void deleteTask(@PathVariable String title)
    {
        taskRepository.deleteByTitle(title);
    }
}

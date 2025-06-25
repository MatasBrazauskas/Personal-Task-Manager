package com.example.demo.controller;

import com.example.demo.model.TaskDays;
import com.example.demo.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.repository.daysRepository;
import com.example.demo.model.daysAdditionEntity;
import java.util.List;

@RestController
@RequestMapping("/days")
@CrossOrigin(origins = "http://localhost:5173")
public class daysController
{
    private final daysRepository daysRepo;
    private final TaskService taskService;

    public daysController(daysRepository daysRepo, TaskService taskService)
    {
        this.daysRepo = daysRepo;
        this.taskService = taskService;
    }

    @GetMapping
    List<TaskDays> getDays(String titleName){
        return daysRepo.findByTitle(titleName);
    }

    @PostMapping
    void addDays(@RequestBody daysAdditionEntity daysAdditionDTO){
        taskService.AddDays(daysAdditionDTO);
    }

    @DeleteMapping("/{titleName}")
    public ResponseEntity<String> removeTasks(@PathVariable String titleName)
    {
        System.out.println(titleName);
        taskService.removeDays(titleName);
        return ResponseEntity.ok(titleName);
    }

    @PatchMapping("/{changedTitle}/{oldTitle}")
    public ResponseEntity<String> updateTaskTitle(@PathVariable String changedTitle, @PathVariable String oldTitle)
    {
        taskService.changeTaskTitleDays(changedTitle, oldTitle);
        return ResponseEntity.ok(changedTitle);
    }
}

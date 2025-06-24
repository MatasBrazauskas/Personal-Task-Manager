package com.example.demo.controller;

import com.example.demo.model.TaskDays;
import com.example.demo.services.TaskService;
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
        System.out.println(daysAdditionDTO.toString());
        taskService.AddDays(daysAdditionDTO);
    }
}

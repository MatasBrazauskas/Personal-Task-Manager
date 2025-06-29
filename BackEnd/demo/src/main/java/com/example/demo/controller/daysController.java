package com.example.demo.controller;

import com.example.demo.model.Days;
import com.example.demo.services.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.DaysResponceDTO;
import java.util.List;

@RestController
@RequestMapping("/days")
@CrossOrigin(origins = "http://localhost:5173")
public class daysController
{
    private final Service service;

    public daysController(Service service)
    {
        this.service = service;
    }

    @GetMapping
    List<Days> getDays(){
        return service.getDays();
    }

    @PostMapping
    void addDays(@RequestBody DaysResponceDTO daysAdditionDTO){
        service.AddDays(daysAdditionDTO);
    }

    @DeleteMapping("/{titleName}")
    public ResponseEntity<String> removeTasks(@PathVariable String titleName)
    {
        service.removeDays(titleName);
        return ResponseEntity.ok(titleName);
    }

    @PatchMapping("/{changedTitle}/{oldTitle}")
    public ResponseEntity<String> updateTaskTitle(@PathVariable String changedTitle, @PathVariable String oldTitle)
    {
        service.changeTaskTitleDays(changedTitle, oldTitle);
        return ResponseEntity.ok(changedTitle);
    }

    @PatchMapping("/{day}/status-reset")
    public ResponseEntity<String> resetStatus(@PathVariable String day)
    {
        service.resetStatus(day);
        return ResponseEntity.ok(day);
    }

    @PatchMapping("/status/{day}/{task}")
    public ResponseEntity<String> updateStatus(@PathVariable String day, @PathVariable String task)
    {
        service.updateStatus(day, task);
        return ResponseEntity.ok(day);
    }
}

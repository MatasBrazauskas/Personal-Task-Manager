package com.example.demo.services;

import com.example.demo.model.TaskResponseDTO;
import com.example.demo.model.RepeatingTasks;
import com.example.demo.model.daysAdditionEntity;
import java.util.List;

public interface TaskService
{
    List<TaskResponseDTO> getAllTasksWithDays();
    void AddTask(RepeatingTasks tasks);
    void AddDays(daysAdditionEntity days);
}

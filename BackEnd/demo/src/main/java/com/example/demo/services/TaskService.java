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
    void removeDays(String titleName);
    void removeTask(String titleName);
    void changeTaskTitleTasks(String changedTitle, String oldTitle);
    void changeTaskTitleDays(String changedTitle, String oldTitle);
}

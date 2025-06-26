package com.example.demo.services;

import com.example.demo.model.RepeatingTasks;
import com.example.demo.model.TaskDays;
import com.example.demo.model.TaskResponseDTO;
import com.example.demo.repository.taskRepository;
import com.example.demo.repository.daysRepository;
import com.example.demo.model.daysAdditionEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class Service {
    private final taskRepository taskRepository;
    private final daysRepository daysRepository;

    public Service(taskRepository taskRepo, daysRepository daysRepo)
    {
        this.taskRepository = taskRepo;
        this.daysRepository = daysRepo;
    }

    public List<TaskDays> getDays()
    {
        return daysRepository.getAllDays();
    }

    public List<TaskResponseDTO> getAllTasksWithDays()
    {
        List<RepeatingTasks> tasks = taskRepository.getAllTasks();
        return tasks.stream()
                .map(task -> {
                    List<TaskDays> taskDays = daysRepository.findByTitle(task.getTitle());
                    return new TaskResponseDTO(task, taskDays);
                })
                .collect(Collectors.toList());

    }

    @Transactional
    public void AddTask(RepeatingTasks tasks) {
        Optional<RepeatingTasks> existingTask = taskRepository.findByTitle(tasks.getTitle());

        if (existingTask.isEmpty()) {
            taskRepository.insertTask(tasks.getTitle(), tasks.getDescription(), tasks.getDate(), tasks.getStatus());
        }
    }

    @Transactional
    public void AddDays(daysAdditionEntity days){
        for(String i : days.getWeek_days()){
            daysRepository.addDay(days.getTitle(), i);
        }
    }

    @Transactional
    public void removeDays(String titleName){
        daysRepository.deleteByTitle(titleName);
    }

    @Transactional
    public void removeTask(String titleName){
        taskRepository.deleteByTitle(titleName);
    }

    @Transactional
    public void changeTaskTitleTasks(String changedTitle, String oldTitle)
    {
        taskRepository.updateTitles(changedTitle, oldTitle);
    }

    @Transactional
    public void changeTaskTitleDays(String changedTitle, String oldTitle)
    {
        daysRepository.updateTitles(changedTitle, oldTitle);
    }

    @Transactional
    public void resetStatus(String day)
    {
        List<RepeatingTasks> taskList = taskRepository.getAllTasks();
        List<TaskDays> daysList = daysRepository.getAllDays();

        for(RepeatingTasks i : taskList)
        {
            List<TaskDays> taskDays = daysList.stream().filter(td -> td.getTask().equals(i.getTitle())).collect(Collectors.toList());
            if(taskDays.stream().anyMatch(td -> td.getWeekDay().equals(day)))
            {

            }
        }
    }
}

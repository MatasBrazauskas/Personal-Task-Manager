package com.example.demo.services;

import com.example.demo.model.RepeatingTasks;
import com.example.demo.model.TaskDays;
import com.example.demo.model.TaskResponseDTO;
import com.example.demo.repository.taskRepository;
import com.example.demo.repository.daysRepository;
import com.example.demo.model.daysAdditionEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{
    private final taskRepository taskRepository;
    private final daysRepository daysRepository;

    public TaskServiceImpl(taskRepository taskRepo, daysRepository daysRepo)
    {
        this.taskRepository = taskRepo;
        this.daysRepository = daysRepo;
    }

    @Override
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

    @Override
    @Transactional
    public void AddTask(RepeatingTasks tasks) {
        Optional<RepeatingTasks> existingTask = taskRepository.findByTitle(tasks.getTitle());

        if (existingTask.isEmpty()) {
            taskRepository.insertTask(tasks.getTitle(), tasks.getDescription(), tasks.getDate(), tasks.getStatus());
        }
    }

    @Override
    @Transactional
    public void AddDays(daysAdditionEntity days){
        for(String i : days.getWeek_days()){
            daysRepository.addDay(days.getTitle(), i);
        }
    }

    @Override
    @Transactional
    public void removeDays(String titleName){
        daysRepository.deleteByTitle(titleName);
    }

    @Override
    @Transactional
    public void removeTask(String titleName){
        taskRepository.deleteByTitle(titleName);
    }

    @Override
    @Transactional
    public void changeTaskTitleTasks(String changedTitle, String oldTitle)
    {
        taskRepository.updateTitles(changedTitle, oldTitle);
    }

    @Override
    @Transactional
    public void changeTaskTitleDays(String changedTitle, String oldTitle)
    {
        daysRepository.updateTitles(changedTitle, oldTitle);
    }
}

package com.example.demo.services;

import com.example.demo.model.Tasks;
import com.example.demo.model.Days;
import com.example.demo.model.TaskResponseDTO;
import com.example.demo.repository.taskRepository;
import com.example.demo.repository.daysRepository;
import com.example.demo.model.DaysResponceDTO;
import com.example.demo.model.TaskCompletionDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public List<Days> getDays()
    {
        return daysRepository.getAllDays();
    }

    public List<TaskResponseDTO> getAllTasksWithDays()
    {
        List<Tasks> tasks = taskRepository.getAllTasks();
        return tasks.stream()
                .map(task -> {
                    List<Days> taskDays = daysRepository.findByTitle(task.getTitle());
                    return new TaskResponseDTO(task, taskDays);
                })
                .collect(Collectors.toList());

    }

    @Transactional
    public void AddTask(Tasks tasks) {
        Optional<Tasks> existingTask = taskRepository.findByTitle(tasks.getTitle());

        if (existingTask.isEmpty()) {
            taskRepository.insertTask(tasks.getTitle(), tasks.getDescription(), tasks.getDate());
        }
    }

    @Transactional
    public void AddDays(DaysResponceDTO days){
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
        daysRepository.resetStatus(day);
    }

    @Transactional
    public List<TaskCompletionDTO> getTasksToComplete(String day)
    {
        return taskRepository.findTasksToComplete(day);
        /*var taskList = taskRepository.getAllTasks();
        var daysList = daysRepository.getAllDays().stream().
                filter(td -> td.getWeekDay().equals(day)).toList();

        List<TaskCompletionDTO> tasksToComplete = new ArrayList<>();
        for(Tasks i : taskList)
        {
            int index = daysList.indexOf(i);
            if(index != -1)
            {
                tasksToComplete.add(new TaskCompletionDTO(i.getTitle(), daysList.get(index).getStatus()));
            }
        }

        System.out.println(tasksToComplete.size());

        return tasksToComplete;*/
    }

    @Transactional
    public void updateStatus(String day, String task)
    {
        daysRepository.updateStatus(day, task);
    }
}

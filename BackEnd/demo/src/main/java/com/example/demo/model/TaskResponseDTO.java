package com.example.demo.model;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TaskResponseDTO
{
    private String title;
    private LocalDate date;
    private List<String> days;

    public void setTitle(String title) {this.title = title;}
    public void setDescription(LocalDate date) {this.date = date;}
    public void setDays(List<String> days) {this.days = days;}

    public String getTitle() {return title;}
    public LocalDate getDate() {return date;}
    public List<String> getDays() {return days;}

    public TaskResponseDTO(RepeatingTasks repeatingTasks, List<TaskDays> taskDays)
    {
        this.title = repeatingTasks.getTitle();
        this.date = repeatingTasks.getDate();
        this.days = taskDays.stream().map(td -> td.getWeekDay()).collect(Collectors.toList());
    }
}
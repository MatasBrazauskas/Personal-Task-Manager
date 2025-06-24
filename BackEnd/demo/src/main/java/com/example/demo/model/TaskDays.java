package com.example.demo.model;
import jakarta.persistence.*;

import java.time.DayOfWeek;

@Entity
@Table(name = "days")
public class TaskDays
{
    public enum DayOfWeek {
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "task", nullable = false, columnDefinition = "NVARCHAR(255)")
    private String task;

    @Enumerated(EnumType.STRING)
    @Column(name = "week_day", nullable = false)
    private DayOfWeek weekDay;

    public TaskDays(){}

    public String getTask(){return task;}
    public String getWeekDay(){return weekDay.toString();}

    public void setTask(String task){this.task = task;}
    public void setWeekDay(DayOfWeek weekDay){this.weekDay = weekDay;}
}

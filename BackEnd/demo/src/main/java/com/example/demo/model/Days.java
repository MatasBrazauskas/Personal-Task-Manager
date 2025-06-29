package com.example.demo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "days")
public class Days
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "task", nullable = false, columnDefinition = "NVARCHAR(255)")
    private String task;

    @Column(name = "week_day", nullable = false, columnDefinition = "NVARCHAR(255)")
    private String weekDay;

    @Column(name = "status", nullable = false, columnDefinition = "BOOLEAN")
    private boolean status;

    public Days(){}

    public String getTask(){return task;}
    public String getWeekDay(){return weekDay.toString();}
    public boolean getStatus(){return status;}

    public void setTask(String task){this.task = task;}
    public void setWeekDay(String weekDay){this.weekDay = weekDay;}
    public void setStatus(boolean status){this.status = status;}
}

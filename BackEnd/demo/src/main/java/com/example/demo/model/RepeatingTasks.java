package com.example.demo.model;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class RepeatingTasks
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", unique = true, nullable = false, columnDefinition = "NVARCHAR(255)")
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "NVARCHAR(510)")
    private String description;

    @Column(name = "date", nullable = false, columnDefinition = "DATE")
    private LocalDate date;

    @Column(name = "status", nullable = false, columnDefinition = "BOOLEAN")
    private boolean status;

    public RepeatingTasks() {}

    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public LocalDate getDate() {return date;}
    public boolean getStatus() {return status;}

    public void setTitle(String title) {this.title = title;}
    public void setDescription(String description) {this.description = description;}
    public void setDate(LocalDate date) {this.date = date;}
    public void setStatus(boolean status) {this.status = status;}
}

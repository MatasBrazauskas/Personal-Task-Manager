package com.example.demo.model;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Tasks
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, columnDefinition = "NVARCHAR(255)")
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "NVARCHAR(510)")
    private String description;

    @Column(name = "date", nullable = false, columnDefinition = "DATE")
    private LocalDate date;

    public Tasks() {}

    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public LocalDate getDate() {return date;}

    public void setTitle(String title) {this.title = title;}
    public void setDescription(String description) {this.description = description;}
    public void setDate(LocalDate date) {this.date = date;}

}

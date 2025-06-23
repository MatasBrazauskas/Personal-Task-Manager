package com.example.demo.model;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "repeting_tasks")
public class ReapetingTasks
{
    private static final int charLen = 255;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", unique = true, nullable = false, columnDefinition = "NVARCHAR(255)")
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "NVARCHAR(510)")
    private String description;

    @Column(name = "date_of_creation", nullable = false, columnDefinition = "DATE")
    private LocalDate dateOfCreation;

    @Column(name = "status", nullable = false, columnDefinition = "BOOLEAN")
    private boolean status;

    public ReapetingTasks() {}

    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public LocalDate getDateOfCreation() {return dateOfCreation;}
    public boolean isStatus() {return status;}

    public void setTitle(String title) {this.title = title;}
    public void setDescription(String description) {this.description = description;}
    public void setDateOfCreation(LocalDate dateOfCreation) {this.dateOfCreation = dateOfCreation;}
    public void setStatus(boolean status) {this.status = status;}
}

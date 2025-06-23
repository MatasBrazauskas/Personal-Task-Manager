package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Entities {
    public Entities(){}

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", columnDefinition = "VARCHAR(255)", nullable = false)
    private String title;
    @Column(name = "status", columnDefinition = "BOOLEAN", nullable = false)
    private boolean status;
    @Column(name = "due_date", columnDefinition = "DATE", nullable = false)
    private LocalDate dueDate;

    public long getID() {return id;}
    public String getTitle(){return  title;}

    public void SetTitle(String title){ this.title = title;}
    public void SetID(long id){this.id = id;}

    public boolean getStatus(){return status;}
    public void setStatus(boolean status){this.status = status;}

    public LocalDate getDueDate(){return dueDate;}
    public void setDueDate(LocalDate dueDate){this.dueDate = dueDate;}
}

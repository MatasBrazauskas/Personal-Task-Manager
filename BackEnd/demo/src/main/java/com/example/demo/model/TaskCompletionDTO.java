package com.example.demo.model;

public class TaskCompletionDTO
{
    private String title;
    private boolean status;

    public TaskCompletionDTO(String title, boolean status)
    {
        this.title = title;
        this.status = status;
    }

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public boolean getStatus() {return status;}
    public void setStatus(boolean status) {this.status = status;}
}

package com.example.demo.model;

import java.util.List;

public class DaysResponceDTO
{
    private String title;
    private List<String> week_days;

    public DaysResponceDTO(String title, List<String> week_days)
    {
        this.title = title;
        this.week_days = week_days;
    }

    public DaysResponceDTO(){}

    public String getTitle(){return this.title;}
    public List<String> getWeek_days(){return this.week_days;}

    public void setTitle(String title) {this.title = title;}
    public void setWeek_days(List<String> arr){this.week_days = arr;}
}

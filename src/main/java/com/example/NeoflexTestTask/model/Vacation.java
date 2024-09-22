package com.example.NeoflexTestTask.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
* Созадние класса для пользовательского ввода и представления его в нормализованном виде
* */
public class Vacation {

    private Double salary;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date date;
    private Integer days;

    public Double getSalary() { return salary; }

    public Integer getDays(){ return days; }

    public Date getDate() { return date; }

    public void setSalary(Double salary) { this.salary = salary; }

    public void setDate(Date date) { this.date = date; }

    public void setDays(Integer days) { this.days = days; }
}
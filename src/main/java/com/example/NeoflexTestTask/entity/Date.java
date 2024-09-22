package com.example.NeoflexTestTask.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Создание Entity для CRUD операций над датами праздников
 * */
@Entity
public class Date {

    @Id
    @GeneratedValue
    private int id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd.MM.yyyy")
    private java.util.Date date;

    public Date(String date) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("dd.MM.yyyy");
        this.date = parser.parse(date);
    }

    public Date(){
        this.date = null;
    }

    public int getId() {
        return id;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Date{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
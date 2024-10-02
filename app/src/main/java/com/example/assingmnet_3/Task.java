package com.example.assingmnet_3;


public class Task {
    String title, description, dueDate, tags;
    int priority;

    public Task(String title, String description, String dueDate, int priority, String tags) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.tags = tags;
    }
}
package com.example.MODEL;

import java.time.LocalDate;

public class Task {

    private Long id;
    private String title, username, description;
    private LocalDate dueDate;
    private boolean status;

    protected Task() {

    }

    // Constructor con ID // Constructor with ID
    public Task(long id, String title, String username, String description, LocalDate dueDate, boolean isDone) {
        super();
        this.id = id;
        this.title = title;
        this.username = username;
        this.description = description;
        this.dueDate = dueDate;
        this.status = isDone;
    }

    // Constructor sin ID //Constructor without ID
    public Task(String title, String username, String description, LocalDate dueDate, boolean isDone) {
        super();
        this.title = title;
        this.username = username;
        this.description = description;
        this.dueDate = dueDate;
        this.status = isDone;
    }

    //Getters&Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }


    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)(id ^ (id >>> 32));
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        if (!id.equals(other.id))
            return false;
        return true;
    }
}

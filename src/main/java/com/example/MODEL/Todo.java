package com.example.MODEL;

import java.time.LocalDate;

public class Todo {

    private Long id; // Identificador único del Todo
    private String title; // Título del Todo
    private String username; // Nombre de usuario asociado al Todo
    private String description; // Descripción del Todo
    private LocalDate targetDate; // Fecha objetivo del Todo
    private boolean status; // Estado del Todo

    protected Todo() {

    }

    // Constructor para inicializar un Todo con ID
    public Todo(long id, String title, String username, String description, LocalDate targetDate, boolean isDone) {
        super();
        this.id = id;
        this.title = title;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.status = isDone;
    }

    // Constructor para inicializar un Todo sin ID
    public Todo(String title, String username, String description, LocalDate targetDate, boolean isDone) {
        super();
        this.title = title;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.status = isDone;
    }

    // Getter para el ID del Todo
    public Long getId() {
        return id;
    }

    // Setter para el ID del Todo
    public void setId(Long id) {
        this.id = id;
    }

    // Getter para el título del Todo
    public String getTitle() {
        return title;
    }

    // Setter para el título del Todo
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter para el nombre de usuario asociado al Todo
    public String getUsername() {
        return username;
    }

    // Setter para el nombre de usuario asociado al Todo
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter para la descripción del Todo
    public String getDescription() {
        return description;
    }

    // Setter para la descripción del Todo
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter para la fecha objetivo del Todo
    public LocalDate getTargetDate() {
        return targetDate;
    }

    // Setter para la fecha objetivo del Todo
    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    // Getter para el estado del Todo
    public boolean getStatus() {
        return status;
    }

    // Setter para el estado del Todo
    public void setStatus(boolean status) {
        this.status = status;
    }

    // Método para calcular el hashCode del Todo
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)(id ^ (id >>> 32));
        return result;
    }

    // Método para comparar si dos objetos Todo son iguales
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Todo other = (Todo) obj;
        if (!id.equals(other.id))
            return false;
        return true;
    }
}

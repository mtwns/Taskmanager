package com.example.MODEL;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String firstName; // Nombre del usuario
    private String lastName; // Apellido del usuario
    private String username; // Nombre de usuario
    private String password; // Contraseña del usuario

    private String city; // City

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // Getter para el nombre del usuario
    public String getFirstName() {
        return firstName;
    }

    // Setter para el nombre del usuario
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter para el apellido del usuario
    public String getLastName() {
        return lastName;
    }

    // Setter para el apellido del usuario
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter para el nombre de usuario
    public String getUsername() {
        return username;
    }

    // Setter para el nombre de usuario
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter para la contraseña del usuario
    public String getPassword() {
        return password;
    }

    // Setter para la contraseña del usuario
    public void setPassword(String password) {
        this.password = password;
    }
}

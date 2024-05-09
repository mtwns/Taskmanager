package com.example.MODEL;

import java.io.Serializable;

public class LoginBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username; // Nombre de usuario
    private String password; // Contraseña

    // Getter para el nombre de usuario
    public String getUsername() {
        return username;
    }

    // Setter para el nombre de usuario
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter para la contraseña
    public String getPassword() {
        return password;
    }

    // Setter para la contraseña
    public void setPassword(String password) {
        this.password = password;
    }
}

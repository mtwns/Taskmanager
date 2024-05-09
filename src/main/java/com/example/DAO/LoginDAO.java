package com.example.DAO;

import com.example.MODEL.LoginBean;
import com.example.UTILS.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    // Método para validar las credenciales de inicio de sesión
    public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
        boolean status = false;

        // Cargar el controlador JDBC para MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establecer la conexión a la base de datos y ejecutar la consulta SQL
        try (Connection connection = JDBCUtil.getConnection();
             // Paso 2: Crear una declaración utilizando el objeto de conexión
             PreparedStatement preparedStatement = connection
                     .prepareStatement("select * from users where username = ? and password = ? ")) {
            // Establecer los parámetros en la consulta preparada
            preparedStatement.setString(1, loginBean.getUsername());
            preparedStatement.setString(2, loginBean.getPassword());

            // Imprimir la consulta SQL para verificarla
            System.out.println("SQL Query: " + preparedStatement);

            // Ejecutar la consulta y obtener el resultado
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                // Si hay al menos una fila en el resultado, establecer el estado como verdadero
                status = true;
                // Imprimir mensaje indicando que el inicio de sesión fue exitoso
                System.out.println("Login successful");
            } else {
                // Imprimir mensaje indicando que el inicio de sesión falló
                System.out.println("Login failed");
            }

        } catch (SQLException e) {
            // Procesar excepción SQL
            JDBCUtil.printSQLException(e);
        }
        return status;
    }

}

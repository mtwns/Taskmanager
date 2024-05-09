package com.example.DAO;

import com.example.MODEL.User;
import com.example.UTILS.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {


     //Registra un nuevo empleado en la base de datos.

    public int registerEmployee(User employee) throws ClassNotFoundException {
        // Consulta SQL preparada para insertar un nuevo usuario en la tabla "users"
        String INSERT_USERS_SQL = "INSERT INTO users" +
                "  (first_name, last_name, username, password, city) VALUES " +
                " (?, ?, ?, ?, ?);";

        int result = 0;
        try (Connection connection = JDBCUtil.getConnection();
             // Paso 2: Crear una declaración utilizando el objeto de conexión
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getUsername());
            preparedStatement.setString(4, employee.getPassword());
            preparedStatement.setString(5,employee.getCity());

            System.out.println(preparedStatement);
            // Paso 3: Ejecutar la consulta o la actualización de la consulta
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // Procesa la excepción SQL
            JDBCUtil.printSQLException(e);
        }
        return result;
    }
}

package com.example.DAO;

import com.example.MODEL.User;
import com.example.UTILS.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {


    //Crea un nuevo usuario //Create a new user
    public int registerEmployee(User user) throws ClassNotFoundException {


        //Sentencia previa de creacion de usuario // PreparedStatement
        String insertNewUser = "INSERT INTO users" + "  (first_name, last_name, username, password, city) VALUES " + " (?, ?, ?, ?, ?);";

        int result = 0;


        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertNewUser)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getCity());

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            JDBCUtil.printSQLException(e);
        }
        return result;
    }
}

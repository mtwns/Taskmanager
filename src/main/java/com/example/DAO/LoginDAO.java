package com.example.DAO;

import com.example.MODEL.Login;
import com.example.UTILS.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    public boolean validate(Login login) throws ClassNotFoundException {
        boolean status = false;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("select * from users where username = ? and password = ? ")) {
            preparedStatement.setString(1, login.getUsername());
            preparedStatement.setString(2, login.getPassword());

            System.out.println("SQL Query: " + preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                status = true;
                System.out.println("Login successful");
            } else {
                System.out.println("Login failed");
            }

        } catch (SQLException e) {
            JDBCUtil.printSQLException(e);
        }
        return status;
    }

}

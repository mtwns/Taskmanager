package com.example.UTILS;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class JDBCUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/taskmanager";
    private static final String Username = "root";
    private static final String Password = "0296";


    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Carga el controlador JDBC para MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establece la conexión a la base de datos
            connection = DriverManager.getConnection(URL, Username, Password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public static Date getSQLDate(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }

    public static LocalDate getUtilDate(Date sqlDate) {
        return sqlDate.toLocalDate();
    }
}

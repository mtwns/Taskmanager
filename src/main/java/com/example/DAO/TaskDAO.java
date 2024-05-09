package com.example.DAO;

import com.example.MODEL.Task;
import com.example.UTILS.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO implements TaskInterface {

    // Consultas SQL preparadas //PreparedStatements
    private static final String INSERT_TODOS_SQL = "INSERT INTO todos" + "  (title, username, description, target_date,  is_done) VALUES " + " (?, ?, ?, ?, ?);";
    private static final String SELECT_TODO_BY_ID = "select id,title,username,description,target_date,is_done from todos where id =?";
    private static final String SELECT_ALL_TODOS = "select * from todos";
    private static final String DELETE_TODO_BY_ID = "delete from todos where id = ?;";
    private static final String UPDATE_TODO = "update todos set title = ?, username= ?, description =?, target_date =?, is_done = ? where id = ?;";

    public TaskDAO() {}

    @Override
    public void insertTask(Task task) throws SQLException {
        System.out.println(INSERT_TODOS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = JDBCUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODOS_SQL)) {
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getUsername());
            preparedStatement.setString(3, task.getDescription());
            preparedStatement.setDate(4, JDBCUtil.getSQLDate(task.getDueDate()));
            preparedStatement.setBoolean(5, task.getStatus());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtil.printSQLException(exception);
        }
    }

    @Override
    public Task selectTask(long todoId) {
        Task task = null;
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TODO_BY_ID)) {
            preparedStatement.setLong(1, todoId);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                String username = rs.getString("username");
                String description = rs.getString("description");
                LocalDate dueDate = rs.getDate("target_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                task = new Task(id, title, username, description, dueDate, isDone);
            }
        } catch (SQLException exception) {
            JDBCUtil.printSQLException(exception);
        }
        return task;
    }

    @Override
    public List <Task> selectAllTasks() {
        List <Task> tasks = new ArrayList < > ();

        try (Connection connection = JDBCUtil.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TODOS)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                String username = rs.getString("username");
                String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("target_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                tasks.add(new Task(id, title, username, description, targetDate, isDone));
            }
        } catch (SQLException exception) {
            JDBCUtil.printSQLException(exception);
        }
        return tasks;
    }

    @Override
    public boolean deleteTask(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = JDBCUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_TODO_BY_ID)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateTask(Task task) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = JDBCUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_TODO)) {
            statement.setString(1, task.getTitle());
            statement.setString(2, task.getUsername());
            statement.setString(3, task.getDescription());
            statement.setDate(4, JDBCUtil.getSQLDate(task.getDueDate()));
            statement.setBoolean(5, task.getStatus());
            statement.setLong(6, task.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}

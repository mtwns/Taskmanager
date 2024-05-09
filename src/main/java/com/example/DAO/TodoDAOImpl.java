package com.example.DAO;

import com.example.MODEL.Todo;
import com.example.UTILS.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoDAOImpl implements TodoDAO {

    // Consultas SQL preparadas
    private static final String INSERT_TODOS_SQL = "INSERT INTO todos" +
            "  (title, username, description, target_date,  is_done) VALUES " + " (?, ?, ?, ?, ?);";
    private static final String SELECT_TODO_BY_ID = "select id,title,username,description,target_date,is_done from todos where id =?";
    private static final String SELECT_ALL_TODOS = "select * from todos";
    private static final String DELETE_TODO_BY_ID = "delete from todos where id = ?;";
    private static final String UPDATE_TODO = "update todos set title = ?, username= ?, description =?, target_date =?, is_done = ? where id = ?;";

    public TodoDAOImpl() {}

    // Método para insertar un nuevo Todo en la base de datos
    @Override
    public void insertTodo(Todo todo) throws SQLException {
        System.out.println(INSERT_TODOS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = JDBCUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODOS_SQL)) {
            preparedStatement.setString(1, todo.getTitle());
            preparedStatement.setString(2, todo.getUsername());
            preparedStatement.setString(3, todo.getDescription());
            preparedStatement.setDate(4, JDBCUtil.getSQLDate(todo.getTargetDate()));
            preparedStatement.setBoolean(5, todo.getStatus());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtil.printSQLException(exception);
        }
    }

    // Método para seleccionar un Todo por su ID
    @Override
    public Todo selectTodo(long todoId) {
        Todo todo = null;
        // Paso 1: Establecer una conexión
        try (Connection connection = JDBCUtil.getConnection();
             // Paso 2: Crear una declaración utilizando el objeto de conexión
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TODO_BY_ID)) {
            preparedStatement.setLong(1, todoId);
            System.out.println(preparedStatement);
            // Paso 3: Ejecutar la consulta o la actualización de la consulta
            ResultSet rs = preparedStatement.executeQuery();

            // Paso 4: Procesar el objeto ResultSet
            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                String username = rs.getString("username");
                String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("target_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                todo = new Todo(id, title, username, description, targetDate, isDone);
            }
        } catch (SQLException exception) {
            JDBCUtil.printSQLException(exception);
        }
        return todo;
    }

    // Método para seleccionar todos los Todos de la base de datos
    @Override
    public List < Todo > selectAllTodos() {

        // usando try-with-resources para evitar el cierre de recursos (código boilerplate)
        List < Todo > todos = new ArrayList < > ();

        // Paso 1: Establecer una conexión
        try (Connection connection = JDBCUtil.getConnection();

             // Paso 2: Crear una declaración utilizando el objeto de conexión
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TODOS)) {
            System.out.println(preparedStatement);
            // Paso 3: Ejecutar la consulta o la actualización de la consulta
            ResultSet rs = preparedStatement.executeQuery();

            // Paso 4: Procesar el objeto ResultSet
            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                String username = rs.getString("username");
                String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("target_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                todos.add(new Todo(id, title, username, description, targetDate, isDone));
            }
        } catch (SQLException exception) {
            JDBCUtil.printSQLException(exception);
        }
        return todos;
    }

    // Método para eliminar un Todo de la base de datos por su ID
    @Override
    public boolean deleteTodo(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = JDBCUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_TODO_BY_ID)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    // Método para actualizar un Todo en la base de datos
    @Override
    public boolean updateTodo(Todo todo) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = JDBCUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_TODO)) {
            statement.setString(1, todo.getTitle());
            statement.setString(2, todo.getUsername());
            statement.setString(3, todo.getDescription());
            statement.setDate(4, JDBCUtil.getSQLDate(todo.getTargetDate()));
            statement.setBoolean(5, todo.getStatus());
            statement.setLong(6, todo.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}

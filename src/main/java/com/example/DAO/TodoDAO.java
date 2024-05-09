package com.example.DAO;

import com.example.MODEL.Todo;

import java.sql.SQLException;
import java.util.List;

public interface TodoDAO {

    /**
     * Método para insertar un nuevo Todo en la base de datos.
     */
    void insertTodo(Todo todo) throws SQLException;

    /**
     * Método para seleccionar un Todo por su ID.
     */
    Todo selectTodo(long todoId);

    /**
     * Método para seleccionar todos los Todos de la base de datos.
     */
    List<Todo> selectAllTodos();

    /**
     * Método para eliminar un Todo de la base de datos por su ID.
     */
    boolean deleteTodo(int id) throws SQLException;

    /**
     * Método para actualizar un Todo en la base de datos.
     */
    boolean updateTodo(Todo todo) throws SQLException;

}

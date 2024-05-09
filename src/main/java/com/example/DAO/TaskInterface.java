package com.example.DAO;

import com.example.MODEL.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskInterface {

    /**
     * Método para insertar un nuevo Todo en la base de datos.
     */
    void insertTask(Task task) throws SQLException;

    /**
     * Método para seleccionar un Todo por su ID.
     */
    Task selectTask(long todoId);

    /**
     * Método para seleccionar todos los Todos de la base de datos.
     */
    List<Task> selectAllTasks();

    /**
     * Método para eliminar un Todo de la base de datos por su ID.
     */
    boolean deleteTask(int id) throws SQLException;

    /**
     * Método para actualizar un Todo en la base de datos.
     */
    boolean updateTask(Task task) throws SQLException;

}

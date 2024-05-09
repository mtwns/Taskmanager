package com.example.DAO;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.MODEL.Login;
import com.example.UTILS.JDBCUtil;

public class LoginDAOTest {

    @Test
    public void testValidate() throws SQLException, ClassNotFoundException {
        // Mock de objetos necesarios (Connection y PreparedStatement)
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        // Mock de JDBCUtil.getConnection() para devolver el objeto de conexión simulado
        when(JDBCUtil.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true); // Simular resultado de autenticación exitosa

        // Crear instancia de LoginDAO
        LoginDAO loginDAO = new LoginDAO();

        // Datos de inicio de sesión de prueba
        Login testLogin = new Login();

        // Llamar al método validate
        boolean result = loginDAO.validate(testLogin);

        // Verificar que se haya llamado a los métodos apropiados en el PreparedStatement
        verify(mockStatement).setString(1, "johndoe");
        verify(mockStatement).setString(2, "password");
        verify(mockStatement).executeQuery(); // Verificar la ejecución de la consulta

        // Verificar que el resultado sea el esperado (true para éxito)
        assertTrue(result);
    }
}

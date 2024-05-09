package com.example.CONTROLLER;

import java.io.IOException;

import com.example.DAO.LoginDAO;
import com.example.MODEL.Login;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDAO loginDAO;

    public void init() {
        loginDAO = new LoginDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login/login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        authenticate(request, response);
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Login login = new Login();
        login.setUsername(username);
        login.setPassword(password);

        try {
            if (loginDAO.validate(login)) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("task/task-list.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("login/login.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

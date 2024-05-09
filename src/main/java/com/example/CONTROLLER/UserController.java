package com.example.CONTROLLER;

import java.io.IOException;

import com.example.DAO.UserDAO;
import com.example.MODEL.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/register")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDao;

    public void init() {
        userDao = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        register(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("register/register.jsp");
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String city = request.getParameter("city");

        User employee = new User();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setCity(city);

        try {
            int result = userDao.registerEmployee(employee);
            if (result == 1) {
                request.setAttribute("NOTIFICATION", "¡Usuario registrado correctamente!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("register/register.jsp");
        dispatcher.forward(request, response);
    }
}

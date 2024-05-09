package com.example.CONTROLLER;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.example.DAO.TaskInterface;
import com.example.DAO.TaskDAO;
import com.example.MODEL.Task;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/")
public class TaskController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TaskInterface taskInterface;

    public void init() {
        taskInterface = new TaskDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertTask(request, response);
                    break;
                case "/delete":
                    deleteTask(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateTask(request, response);
                    break;
                case "/list":
                    listTask(request, response);
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("task/task-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Task existingTask = taskInterface.selectTask(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("task/task-form.jsp");
        request.setAttribute("todo", existingTask);
        dispatcher.forward(request, response);
    }

    private void insertTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String title = request.getParameter("title");
        String username = request.getParameter("username");
        String description = request.getParameter("description");
        boolean isDone = Boolean.parseBoolean(request.getParameter("isDone"));
        Task newTask = new Task(title, username, description, LocalDate.now(), isDone);
        taskInterface.insertTask(newTask);
        response.sendRedirect("list");
    }

    private void updateTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String username = request.getParameter("username");
        String description = request.getParameter("description");
        LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
        boolean isDone = Boolean.parseBoolean(request.getParameter("isDone"));
        Task updateTask = new Task(id, title, username, description, targetDate, isDone);
        taskInterface.updateTask(updateTask);
        response.sendRedirect("list");
    }

    private void deleteTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        taskInterface.deleteTask(id);
        response.sendRedirect("list");
    }

    private void listTask(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Task> listTask = taskInterface.selectAllTasks();
        request.setAttribute("listTodo", listTask);
        RequestDispatcher dispatcher = request.getRequestDispatcher("task/task-list.jsp");
        dispatcher.forward(request, response);
    }
}

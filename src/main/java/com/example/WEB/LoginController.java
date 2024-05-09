package com.example.WEB;
import java.io.IOException;
import com.example.DAO.LoginDAO;
import com.example.MODEL.LoginBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDAO loginDAO;

    // Método init() para inicializar el LoginDAO
    public void init() {
        loginDAO = new LoginDAO();
    }

    // Método doGet() para manejar las solicitudes GET
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige a la página de inicio de sesión (login.jsp)
        response.sendRedirect("login/login.jsp");
    }

    // Método doPost() para manejar las solicitudes POST
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        authenticate(request, response);
    }

    // Método para autenticar al usuario
    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        try {
            if (loginDAO.validate(loginBean)) {
                // Si la autenticación es exitosa, reenvía al usuario a la lista de tareas (todo-list.jsp)
                RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
                dispatcher.forward(request, response);
            } else {
                // Si la autenticación falla, crea una nueva sesión y redirige al usuario de vuelta a la página de inicio de sesión (login.jsp)
                response.sendRedirect("login/login.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

package com.railway.servlets;

import com.railway.dao.UserDAO;
import com.railway.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user-registration")
public class UserRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate the input
        if (name == null || name.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
            response.sendRedirect("userRegister.jsp?error=1"); // Redirect back to the registration page with an error message
            return;
        }

        // Check if the email is already registered
        if (UserDAO.isUserExists(email)) {
            response.sendRedirect("userRegister.jsp?error=2"); // Redirect back to the registration page with an error message
            return;
        }

        // Create a new User object
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);

        // Save the new user to the database
        UserDAO.saveUser(newUser);

        response.sendRedirect("userLogin.jsp?registrationSuccess=1"); // Redirect to the login page with a success message
    }
}

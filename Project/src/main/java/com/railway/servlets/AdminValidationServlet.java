package com.railway.servlets;

import com.railway.dao.AdminDAO;
import com.railway.dao.RailwayCrossingDAO;
import com.railway.models.Admin;
import com.railway.models.RailwayCrossing;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin-validation")
public class AdminValidationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate the input
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            response.sendRedirect("adminLogin.jsp"); // Redirect back to the login page if the input is invalid
            return;
        }

        // Check if the admin details in the database are valid (admin exists with the provided email and password)
        if (AdminDAO.isAdminExists(email, password)) {
            // If the admin is authenticated, get the admin details from the database
            Admin admin = AdminDAO.getAdminByEmail(email);
            List<RailwayCrossing> crossings = RailwayCrossingDAO.getAllRailwayCrossings();

            // Set the admin details in the session
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin);
            request.setAttribute("crossings", crossings);
            request.getRequestDispatcher("AdminHomepage.jsp").forward(request, response);
        } else {
            response.sendRedirect("adminLogin.jsp?error=1"); // Redirect back to the login page with an error message
        }
    }
}

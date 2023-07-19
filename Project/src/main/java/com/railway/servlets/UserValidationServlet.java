package com.railway.servlets;

import com.railway.dao.UserDAO;
import com.railway.models.User;
import com.railway.dao.FavoriteCrossingDAO;
import com.railway.dao.RailwayCrossingDAO;
import com.railway.models.RailwayCrossing;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/user-validation")
public class UserValidationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate the input
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            response.sendRedirect("userLogin.jsp"); // Redirect back to the login page if the input is invalid
            return;
        }

        // Check if the user details in the database are valid (user exists with the provided email and password)
        
        if (UserDAO.isUser(email, password)) {
            // If the user is authenticated, get the user details from the database
            User user = UserDAO.getUserByEmail(email);
            
            HttpSession session = request.getSession();
            session.setAttribute("user_id", user.getId());

            // Fetch all railway crossings from the database
            List<RailwayCrossing> crossings = RailwayCrossingDAO.getAllRailwayCrossings();
            
            String activeTab = request.getParameter("tab");
            if (activeTab != null && activeTab.equals("favoriteCrossings")) {
                // Fetch the user's favorite crossings from the database
                List<RailwayCrossing> favoriteCrossings = FavoriteCrossingDAO.getFavoriteCrossingsByUserId(user.getUserId());

                // Pass the user's favorite crossings to the JSP page
                request.setAttribute("favoriteCrossings", favoriteCrossings);
            }
            
            // Pass the railway crossings list and user details to the JSP page
            request.setAttribute("crossings", crossings);
            request.setAttribute("user", user);
            request.getRequestDispatcher("publicDashboard.jsp").forward(request, response);
        } else {
            response.sendRedirect("userLogin.jsp?error=1"); // Redirect back to the login page with an error message
        }
    }
}

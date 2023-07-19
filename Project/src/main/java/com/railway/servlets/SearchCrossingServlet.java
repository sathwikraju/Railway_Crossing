package com.railway.servlets;

import com.railway.dao.RailwayCrossingDAO;
import com.railway.models.RailwayCrossing;
import com.railway.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/SearchCrossingServlet")
public class SearchCrossingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the search string from the request
        String searchString = request.getParameter("search");

        // Search railway crossings by the search string
        List<RailwayCrossing> crossings = RailwayCrossingDAO.searchRailwayCrossingsByName(searchString);

        // Get the user object from the session
        User user = (User) request.getSession().getAttribute("user");

        // Pass the search results and user object to the JSP page
        request.setAttribute("crossings", crossings);
        request.setAttribute("user", user);
        request.getRequestDispatcher("publicDashboard.jsp").forward(request, response);
    }
}

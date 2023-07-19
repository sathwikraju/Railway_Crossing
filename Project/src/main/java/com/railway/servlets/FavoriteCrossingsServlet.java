package com.railway.servlets;

import com.railway.dao.FavoriteCrossingDAO;
import com.railway.models.RailwayCrossing;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/FavoriteCrossingsServlet")
public class FavoriteCrossingsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user_id from the session
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("user_id");

        // Retrieve the list of favorite crossings for the given user from the database
        List<RailwayCrossing> favoriteCrossings = FavoriteCrossingDAO.getFavoriteCrossingsByUserId(userId);

        // Pass the list of favorite crossings to the JSP page
        request.setAttribute("favoriteCrossings", favoriteCrossings);
        request.getRequestDispatcher("FavoriteCrossings.jsp").forward(request, response);
    }
}

package com.railway.servlets;

import com.railway.dao.FavoriteCrossingDAO;
import com.railway.dao.RailwayCrossingDAO;
import com.railway.models.FavoriteCrossing;
import com.railway.models.RailwayCrossing;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MarkFavoriteServlet")
public class MarkFavoriteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String crossingIdString = request.getParameter("crossingId");

        if (crossingIdString != null) {
            try {
                int crossingId = Integer.parseInt(crossingIdString);
                // Get the RailwayCrossing object from the database based on the crossingId
                RailwayCrossing crossing = RailwayCrossingDAO.getRailwayCrossingById(crossingId);
                if (crossing != null) {
                    // Get the current user's ID (assuming it's stored in the session as an attribute)
                    // Modify the code below to get the user ID from the appropriate source.
                    int userId = (int) request.getSession().getAttribute("user_id");

                    // Create a new FavoriteCrossing object with the RailwayCrossing object and the user ID
                    FavoriteCrossing favoriteCrossing = new FavoriteCrossing(crossing, userId);

                    // Save the favorite crossing to the database
                    FavoriteCrossingDAO.saveFavoriteCrossing(favoriteCrossing);

                    // Redirect back to the public dashboard after marking the crossing as favorite
                    response.sendRedirect("publicDashboard.jsp");
                } else {
                    response.sendRedirect("publicDashboard.jsp?error=1");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("publicDashboard.jsp?error=1");
            }
        }
    }
}

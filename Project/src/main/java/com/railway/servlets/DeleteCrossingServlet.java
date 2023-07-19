package com.railway.servlets;

import com.railway.dao.RailwayCrossingDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Delete_crossing")
public class DeleteCrossingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String crossingIdParam = request.getParameter("crossingId");

        if (crossingIdParam != null && !crossingIdParam.isEmpty()) {
            try {
                int crossingId = Integer.parseInt(crossingIdParam);

                // Call the DAO method to delete the railway crossing
                RailwayCrossingDAO.deleteRailwayCrossing(crossingId);

                // Redirect back to the admin home page after successful deletion
                response.sendRedirect("AdminHomepage.jsp");
            } catch (NumberFormatException e) {
                response.sendRedirect("AdminHomepage.jsp"); // Redirect back to home page if crossingId is not a valid integer
            }
        } else {
            response.sendRedirect("AdminHomepage.jsp"); // Redirect back to home page if crossingId is not provided
        }
    }
}

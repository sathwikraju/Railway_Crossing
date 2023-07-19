package com.railway.servlets;

import com.railway.dao.RailwayCrossingDAO;
import com.railway.models.RailwayCrossing;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateCrossingsServlet")
public class UpdateCrossingsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int crossingId = Integer.parseInt(request.getParameter("crossingId"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String landmark = request.getParameter("landmark");
        String trainSchedules = request.getParameter("trainSchedules");
        String personInCharge = request.getParameter("personInCharge");
        String statusStr = request.getParameter("status");

        // Validate the input and perform the update if valid
        if (name != null && !name.isEmpty() && address != null && !address.isEmpty() &&
            landmark != null && !landmark.isEmpty() && trainSchedules != null && !trainSchedules.isEmpty() &&
            personInCharge != null && !personInCharge.isEmpty() && statusStr != null) {
            
            try {
                // Convert the status string to the Status enum
                RailwayCrossing.Status status = RailwayCrossing.Status.valueOf(statusStr);

                // Get the existing RailwayCrossing object from the database
                RailwayCrossing crossing = RailwayCrossingDAO.getRailwayCrossingById(crossingId);
                if (crossing != null) {
                    // Update the crossing object with the new data
                    crossing.setName(name);
                    crossing.setAddress(address);
                    crossing.setLandmark(landmark);
                    crossing.setTrainSchedules(trainSchedules);
                    crossing.setPersonInCharge(personInCharge);
                    crossing.setStatus(status);

                    // Update the crossing in the database
                    RailwayCrossingDAO.updateRailwayCrossing(crossing);

                    // Redirect back to the admin home page after successful update
                    response.sendRedirect("AdminHomepage.jsp");
                    return;
                }
            } catch (IllegalArgumentException e) {
                // Handle any potential exceptions related to parsing data or converting enum
                e.printStackTrace();
            }
        }

        // If there's an error or invalid data, redirect back to the update form
        response.sendRedirect("updateForm.jsp?crossingId=" + crossingId);
    }

}

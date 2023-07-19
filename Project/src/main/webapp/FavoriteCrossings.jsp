<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Favorite Crossings</title>
     <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        /* CSS for each crossing container */
        .crossing-container {
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 10px;
            background-color: #f9f9f9;
            border-radius: 5px;
        }

        /* CSS for green status */
        .status-green {
            color: #4CAF50;
        }

        /* CSS for red status */
        .status-red {
            color: #FF0000;
        }

        .tab {
            display: none;
        }

        .active-tab {
            display: block;
        }



        .logout-button {
            position: absolute;
            top: 10px;
            right: 10px;
            padding: 10px;
            background-color: #f44336;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1>Favorite Crossings</h1>
    <!-- Display the favorite crossings as containers -->
    <!-- Logout button -->
    <a class="logout-button" href="LogoutServlet">Logout</a>
    <div class="container">

        <!-- Loop through the list of favorite crossings and display them as containers -->
        <c:forEach items="${favoriteCrossings}" var="crossing">
            <div class="crossing-container">
                <h2>${crossing.name}</h2>
                <p><strong>Address:</strong> ${crossing.address}</p>
                <p><strong>Landmark:</strong> ${crossing.landmark}</p>
                <p><strong>Train Schedules:</strong> ${crossing.trainSchedules}</p>
                <p><strong>Person in Charge:</strong> ${crossing.personInCharge}</p>
                <p><strong>Status:</strong>
                    <span class="${crossing.status == 'open' ? 'status-green' : 'status-red'}">${crossing.status}</span>
                </p>
            </div>
        </c:forEach>
    </div>
</body>
</html>

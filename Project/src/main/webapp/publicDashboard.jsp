<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Public Dashboard</title>
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

        .search-box-container {
            padding: 10px;
            background-color: #f9f9f9;
            border-radius: 5px;
            margin-bottom: 10px;
        }

        .search-box {
            width: 250px;
            padding: 5px;
            margin-right: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .search-button {
            padding: 5px 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
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
        .mark-favorite-button {
            padding: 5px 10px;
            background-color: #2196F3;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 10px;
        }

        .empty-message {
            text-align: center;
            font-style: italic;
            color: #999;
        }
    </style>
</head>
<body>
   <h1>Public Dashboard</h1> 
    <!-- Logout button -->
    <a class="logout-button" href="LogoutServlet">Logout</a>
    <div class="search-box-container">
        <!-- Search Railway Crossing by Name -->
        <form action="SearchCrossingServlet" method="GET">
            <label for="search">Search Railway Crossing:</label>
            <input type="text" id="search" name="search" class="search-box" required>
            <input type="submit" value="Search" class="search-button">
        </form>
    </div>

    <div class="container">
        
					<form action="FavoriteCrossingsServlet" method="GET">
                        <input type="hidden" name="user_id" value="${user.id}">
                        <button type="submit" class="ViewFavorites">View Favorites</button>
                    </form>
        <!-- Tab: All Railway Crossings -->
        <div class="tab active-tab" id="allCrossings">
            <!-- Loop through the list of railway crossings and display them as containers -->
            <c:forEach items="${crossings}" var="crossing">
                <div class="crossing-container">
                    <h2>${crossing.name}</h2>
                    <p><strong>Address:</strong> ${crossing.address}</p>
                    <p><strong>Landmark:</strong> ${crossing.landmark}</p>
                    <p><strong>Train Schedules:</strong> ${crossing.trainSchedules}</p>
                    <p><strong>Person in Charge:</strong> ${crossing.personInCharge}</p>
                    <p><strong>Status:</strong>
                        <span class="${crossing.status == 'open' ? 'status-green' : 'status-red'}">${crossing.status}</span>
                    </p>
                    <form action="MarkFavoriteServlet" method="POST">
                        <input type="hidden" name="crossingId" value="${crossing.id}">
                        <button type="submit" class="mark-favorite-button">Mark as Favorite</button>
                    </form>
                </div>
            </c:forEach>
            <c:if test="${empty favoriteCrossings}">
                <div class="empty-message">
                    You have no favorite crossings.
                </div>
            </c:if>
        </div>
    </div>
</body>
</html>

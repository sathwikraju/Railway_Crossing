<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Home Page</title>
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

        /* CSS for table */
        table {
            width: 100%;
            border-collapse: collapse;
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

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>Welcome, <c:out value="${admin.name}" />!</h1>
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
        <h2>Railway Crossings List</h2>
        <table>
            <tr>
                <th>Name</th>
                <th>Address</th>
                <th>Landmark</th>
                <th>Train Schedules</th>
                <th>Person in Charge</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            <c:forEach items="${crossings}" var="crossing">
                <tr>
                    <td>${crossing.name}</td>
                    <td>${crossing.address}</td>
                    <td>${crossing.landmark}</td>
                    <td>${crossing.trainSchedules}</td>
                    <td>${crossing.personInCharge}</td>
                    <td>
                        <span class="${crossing.status == 'open' ? 'status-green' : 'status-red'}">${crossing.status}</span>
                    </td>
                    <td>
                    
                    <a href="updateForm.jsp?crossingId=${crossing.id}">Update</a>
                    <a href="updateForm.jsp?crossingId=${crossing.id}">Update</a>
                    
              
                        <form action="Delete_crossing" method="POST">
                            <input type="hidden" name="crossingId" value="${crossing.id}">
                            <button type="submit" class="delete-button">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>

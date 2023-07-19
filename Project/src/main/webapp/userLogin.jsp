<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h1>User Login</h1>
        <c:if test="${param.error == '1'}">
        <p style="color: red;">Invalid email or password. Please try again.</p>
    </c:if>
        <form action="user-validation" method="post">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br><br>

            <br>

            <input type="submit" value="Login" class="login-btn">
        </form>
		<p>Are you a admin? <a href="adminLogin.jsp">Login</a></p>
        <p>Don't have an account? <a href="userRegister.jsp">Create New Account</a></p>
    </div>
</body>
</html>

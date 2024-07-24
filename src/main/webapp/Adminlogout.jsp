<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
</head>
<body>
    <c:choose>
        <c:when test="${not empty sessionScope.adminName}">
            <h1>Welcome, ${sessionScope.adminName}</h1>
            <!-- Admin dashboard content -->
            <a href="AdminLogout">Logout</a>
        </c:when>
        <c:otherwise>
            <h1>You are not logged in.</h1>
            <a href="login.jsp">Go to Login</a>
        </c:otherwise>
    </c:choose>
</body>
</html>

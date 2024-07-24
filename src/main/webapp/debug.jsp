<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Debug Information</title>
    <!-- Include any necessary styles or scripts -->
</head>
<body>
    <h2>Debug Information</h2>
    <p><strong>Admin Username from Session:</strong> <%= session.getAttribute("adminUsername") %></p>
    <!-- Add more debug information as needed -->
</body>
</html>

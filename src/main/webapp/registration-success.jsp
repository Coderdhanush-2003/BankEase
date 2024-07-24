<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Successful</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<style>
    .container {
        margin-top: 50px;
        text-align: center;
    }
    .btn {
        margin-top: 20px;
    }
</style>
</head>
<body>
    <div class="container">
        <h2>Registration Successful</h2>
        <p>Your Email Id: <%= request.getParameter("email") %></p>
        <p>Your Account No: <%= request.getParameter("accountNo") %></p>
        <p>Your Password: <%= request.getParameter("password") %></p>
        <a href="adminDashboard" class="btn btn-primary">Return to Dashboard</a>
    </div>
</body>
</html>

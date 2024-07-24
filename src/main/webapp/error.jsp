<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f0f0f0;
        }
        .error-container {
            margin-top: 50px;
            padding: 20px;
            background-color: #ffffff;
            border: 1px solid #cccccc;
            border-radius: 5px;
            width: 50%;
            margin-left: auto;
            margin-right: auto;
        }
        h2 {
            color: #cc0000;
        }
        .error-message {
            color: #cc0000;
            margin-top: 10px;
        }
        .return-link {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h2>Registration Error</h2>
        <p>An error occurred during registration:</p>
        <p class="error-message"><%= request.getAttribute("errorMessage") %></p>
        <a href="NewRegistration.jsp" class="return-link">Return to Registration Form</a>
    </div>
</body>
</html>

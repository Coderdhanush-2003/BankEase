<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
<%-- sessionCheck.jsp --%>
<%
    if (session.getAttribute("adminName") == null) {
        response.sendRedirect("login.jsp");
    }
%>
</body>
</html>
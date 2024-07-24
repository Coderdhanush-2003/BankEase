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
    if (session.getAttribute("customerName") == null) {
        response.sendRedirect("Customer.jsp");
    }
%>
</body>
</html>
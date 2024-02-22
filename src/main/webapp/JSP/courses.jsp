<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Courses</title>
    <link rel="stylesheet" href="CSS/style.css">
</head>
<body>
    <%@ include file="fragments/navbar.jsp" %>
    <h1>Welcome to courses!</h1><br>
    Show all available courses here<br><br>
    <div class="tables">
        <table>
            <tr>
                <th>Name</th>
                <th>YHP</th>
                <th>Description</th>
            </tr>
            <c:forEach items="${courseList}" var="dataPoint">
                <tr>
                    <td>${dataPoint[0]}</td>
                    <td>${dataPoint[1]}</td>
                    <td>${dataPoint[2]}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
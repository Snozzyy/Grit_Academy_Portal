<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Log in</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/style.css">
    </head>
    <body>
        <%@ include file="fragments/navbar.jsp" %>
        <div class="form">
            <form action="/login" method="post">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username"><br>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password"><br>
                <label for="student">Student</label>
                <input type="radio" id="student" name="user_type" value="student" checked>
                <label for="teacher">Teacher</label>
                <input type="radio" id="teacher" name="user_type" value="teacher"><br>
                <input type="submit" value="Log in">
            </form>
        </div>
    </body>
</html>

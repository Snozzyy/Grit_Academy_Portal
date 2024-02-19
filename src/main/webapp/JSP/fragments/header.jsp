<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
    <div class="navbar">
        <a href="/">Home</a>
        <a href="/courses">Courses</a>
        <c:choose>
            <c:when test="${applicationScope.stateType == 'anonymous'}">
                <a href="/login">Log in</a>
            </c:when>
            <c:when test="${applicationScope.stateType == 'confirmed'}">
                <a href="/user">User Page</a>
                <form action="/login" method="post">
                    <input type="submit" name="logout" id="logout" value="Log out">
                </form>
            </c:when>
        </c:choose>
    </div>
</header>
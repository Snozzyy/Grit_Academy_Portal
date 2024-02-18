<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
    <div class="navbar">
        <a href="/">Home</a>
        <a href="/courses">Courses</a>
        <c:choose>
            <c:when test="${applicationScope.userState == 'anonymous'}">
                <a href="/login">Log in</a>
            </c:when>
            <c:when test="${applicationScope.userState == 'confirmed'}">
                <a href="/user">User Page</a>
            </c:when>
        </c:choose>
    </div>
</header>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="navbar">
    <nav>
        <a href="index.jsp">Home</a>
        <a href="/courses">Courses</a>
        <c:choose>
            <c:when test="${userBean.stateType == 'confirmed'}">
                <a href="/user">User page</a>
                <c:choose>
                    <c:when test="${userBean.privilegeType == 'admin'}">
                        <a href="/register">Register</a>
                    </c:when>
                </c:choose>
                <form action="/logout" method="post">
                    <input type="submit" name="logout" id="logout" value="Log out">
                </form>
            </c:when>

            <c:otherwise>
                <a href="/login">Log in</a>
            </c:otherwise>
        </c:choose>
    </nav>
</div>
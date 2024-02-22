<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>Your courses</h1>
<div class="tables">
    <table>
        <caption>Your courses</caption>
        <tr>
            <th>Name</th>
            <th>YHP</th>
            <th>Description</th>
        </tr>
        <c:forEach items="${data}" var="dataPoint">
            <tr>
                <td>${dataPoint[0]}</td>
                <td>${dataPoint[1]}</td>
                <td>${dataPoint[2]}</td>
            </tr>
        </c:forEach>
    </table><br>
</div>

<form action="/user" method="post">
    <select name="course" id="course">
        <c:forEach items="${data}" var="dataPoint">
            <option value="${dataPoint[0]}">${dataPoint[0]}</td>
        </c:forEach>
    </select>
    <input type="submit" value="Show">
</form>

<div class="tables">
    <c:choose>
        <c:when test="${courseData != null}">
            <table>
                <caption>${courseName}</caption>
                <tr>
                    <th>Name</th>
                    <th>Role</th>
                </tr>
                <c:forEach items="${courseData}" var="dataPoint">
                    <tr>
                        <td>${dataPoint[0]} ${dataPoint[1]}</td>
                        <td>${dataPoint[2]}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
    </c:choose>
</div>
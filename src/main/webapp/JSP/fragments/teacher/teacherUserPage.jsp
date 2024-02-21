<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="tables">
    <h1>All students</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Town</th>
            <th>E-mail</th>
            <th>Phone number</th>
        </tr>
        <c:forEach items="${studentsData}" var="dataPoint">
            <tr>
                <td>${dataPoint[0]}</td>
                <td>${dataPoint[1]} ${dataPoint[2]}</td>
                <td>${dataPoint[3]}</td>
                <td>${dataPoint[4]}</td>
                <td>${dataPoint[5]}</td>
            </tr>
        </c:forEach>
    </table><br>
</div>

<form action="/user" method="post">
    <select name="student" id="student">
        <c:forEach items="${studentsData}" var="dataPoint">
            <option value="${dataPoint[0]}">${dataPoint[0]} - ${dataPoint[1]} ${dataPoint[2]}</option>
        </c:forEach>
    </select> 
    <input type="submit" value="Show">
</form>


<div class="tables">
    <h1>All courses</h1>
    <table>
        <tr>
            <th>Name</th>
            <th>YHP</th>
            <th>Description</th>
        </tr>
        <c:forEach items="${coursesData}" var="dataPoint">
            <tr>
                <td>${dataPoint[0]}</td>
                <td>${dataPoint[1]}</td>
                <td>${dataPoint[2]}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<form action="/user" method="post">
    <select name="course" id="course">
        <c:forEach items="${coursesData}" var="dataPoint">
            <option value="${dataPoint[0]}">${dataPoint[0]}</option>
        </c:forEach>
    </select> 
<input type="submit" value="Show">
</form>

<br>
<div class="tables">
    <c:choose>
        <c:when test="${studentData != null}">
            <h2>Info about student</h2>
            <table>
                <tr>
                    <th>Name</th>
                    <th>YHP</th>
                    <th>Description</th>
                </tr>
                <c:forEach items="${studentData}" var="dataPoint">
                    <tr>
                        <td>${dataPoint[0]}</td>
                        <td>${dataPoint[1]}</td>
                        <td>${dataPoint[2]}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:when test="${courseData != null}">
            <h2>${courseName}</h2>
            <table>
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